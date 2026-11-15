package vn.crossdock;
import org.junit.jupiter.api.*;import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;import org.springframework.test.web.servlet.MockMvc;import org.springframework.transaction.annotation.Transactional;import org.springframework.mock.web.MockMultipartFile;
import java.util.*;import java.time.*;import java.nio.file.*;import static org.junit.jupiter.api.Assertions.*;import static vn.crossdock.Db.*;import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
@SpringBootTest @AutoConfigureMockMvc @Transactional @EnabledIfEnvironmentVariable(named="RUN_DB_TESTS",matches="true")
class WorkflowIntegrationTest {
 @Autowired Workflow w;@Autowired Db db;@Autowired ExcelService excel;@Autowired PdfService pdf;@Autowired MockMvc mvc;@Autowired MasterService master;
 Map<String,Object> p(Object...v){Map<String,Object> m=new HashMap<>();for(int i=0;i<v.length;i+=2)m.put((String)v[i],v[i+1]);return m;}
 long trip(long vehicle,String region){return ((Number)w.execute("trip",p("vehicle_id",vehicle,"region",region,"driver","Tài xế demo","planned_at",OffsetDateTime.now().toString()),3)).longValue();}
 long book(long tid,int gate,boolean input){return ((Number)w.execute("book",p("gate_id",gate,input?"inbound_id":"outbound_id",tid,"planned_start",OffsetDateTime.now().toString(),"planned_end",OffsetDateTime.now().plusHours(1).toString()),3)).longValue();}
 void allocate(long t,long r){w.execute("allocate",p("trip_id",t,"requirement_id",r),3);}
 void loadAll(long t){for(var a:db.rows("select a.id from phan_bo_thung a join diem_giao_chuyen s on s.id=a.stop_id where s.trip_id=? and a.status='ALLOCATED'",t))w.execute("load",p("id",n(a,"id")),2);}
 void dispatch(long t){w.execute("seal",p("id",t),3);long gate=book(t,4,false);w.execute("enter",p("id",gate),3);loadAll(t);w.execute("depart",p("id",t,"reason","Đáp ứng lịch sản xuất"),3);}
 @Test void loginAndAllRolePages()throws Exception{for(String u:List.of("admin","kho","dieuphoi"))mvc.perform(formLogin().user(u).password("Demo@12345")).andExpect(status().is3xxRedirection());mvc.perform(get("/").with(user("kho").roles("KHO"))).andExpect(status().isOk());mvc.perform(get("/warehouse").with(user("kho").roles("KHO"))).andExpect(status().isOk());mvc.perform(get("/dispatch").with(user("dieuphoi").roles("DP"))).andExpect(status().isOk());mvc.perform(get("/management").with(user("admin").roles("ADMIN"))).andExpect(status().isOk());}
 @Test void enforceRoleAndCsrf()throws Exception{mvc.perform(post("/api/action/depart").with(user("kho").roles("KHO")).with(csrf()).contentType("application/json").content("{\"id\":1}")).andExpect(status().isForbidden());mvc.perform(post("/api/action/order").with(user("dieuphoi").roles("DP")).contentType("application/json").content("{}")).andExpect(status().isForbidden());mvc.perform(get("/management").with(user("kho").roles("KHO"))).andExpect(status().isForbidden());}
 @Test void stateDoesNotExposePassword()throws Exception{mvc.perform(get("/api/state").with(user("admin").roles("ADMIN"))).andExpect(status().isOk()).andExpect(content().string(org.hamcrest.Matchers.not(org.hamcrest.Matchers.containsString("$2a$"))));}
 @Test void eightPlusTenFitsExactly(){long t=trip(1,"MB");allocate(t,1);allocate(t,2);assertEquals(18000,w.weight(t));assertEquals(2,db.count("select count(*) from diem_giao_chuyen where trip_id=?",t));}
 @Test void cannotTakeHalfOfTwelve(){long t=trip(1,"MB");allocate(t,1);assertThrows(IllegalArgumentException.class,()->allocate(t,3));assertEquals(8000,w.weight(t));}
 @Test void rejectsDifferentRegion(){long t=trip(1,"MB");assertThrows(IllegalArgumentException.class,()->allocate(t,5));}
 @Test void oversizedUsesDedicatedWholeBoxes(){long a=trip(1,"MB"),b=trip(2,"MB");allocate(a,4);allocate(b,4);assertEquals(15000,w.weight(a));assertEquals(10000,w.weight(b));assertThrows(IllegalArgumentException.class,()->allocate(a,1));}
 @Test void cannotAllocateSameBoxTwice(){long a=trip(1,"MB"),b=trip(2,"MB");allocate(a,1);assertThrows(IllegalArgumentException.class,()->allocate(b,1));}
 @Test void needsEverySku(){db.run("insert into chi_tiet_yeu_cau(requirement_id,part_id,quantity) values(1,2,1)");assertFalse(w.sufficient(1));assertThrows(IllegalArgumentException.class,()->allocate(trip(1,"MB"),1));}
 @Test void gateOverlapAndPhysicalQueue(){long first=book(2,1,true);assertThrows(IllegalArgumentException.class,()->book(3,1,true));long later=((Number)w.execute("book",p("gate_id",1,"inbound_id",3,"planned_start",OffsetDateTime.now().plusHours(2).toString(),"planned_end",OffsetDateTime.now().plusHours(3).toString()),3)).longValue();w.execute("enter",p("id",first),3);assertThrows(IllegalArgumentException.class,()->w.execute("enter",p("id",later),3));}
 @Test void cannotLoadBeforeTruckEnters(){long t=trip(1,"MB");allocate(t,1);w.execute("seal",p("id",t),3);long aid=db.count("select max(id) from phan_bo_thung");assertThrows(IllegalArgumentException.class,()->w.execute("load",p("id",aid),2));}
 @Test void dispatchRequiresAllBoxesAndReason(){long t=trip(1,"MB");allocate(t,1);w.execute("seal",p("id",t),3);w.execute("enter",p("id",book(t,4,false)),3);assertThrows(IllegalArgumentException.class,()->w.execute("depart",p("id",t),3));loadAll(t);assertThrows(IllegalArgumentException.class,()->w.execute("depart",p("id",t),3));w.execute("depart",p("id",t,"reason","Khách cần gấp"),3);assertEquals(0,db.count("select count(*) from luot_phuc_vu where outbound_id=? and status='IN_GATE'",t));assertEquals(0,db.count("select count(*) from thung_hang b join phan_bo_thung a on a.box_id=b.id join diem_giao_chuyen s on s.id=a.stop_id where s.trip_id=? and b.staging_id is not null",t));}
 @Test void excelPreviewImportAndDuplicate()throws Exception{var file=new MockMultipartFile("file","demo.xlsx","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",Files.readAllBytes(Path.of("demo-excel/01_nhap_du_10_thung.xlsx")));assertEquals(true,excel.importBoxes(2,file,true).get("ok"));assertEquals(0,db.count("select count(*) from thung_hang where lot_id=2"));assertEquals(true,excel.importBoxes(2,file,false).get("ok"));assertEquals(10,db.count("select count(*) from thung_hang where lot_id=2"));assertEquals(false,excel.importBoxes(2,file,false).get("ok"));}
 @Test void invalidExcelIsAtomic()throws Exception{for(String filename:List.of("04_loi_ma_va_so_luong.xlsx","05_loi_trung_ma_thung.xlsx")){var file=new MockMultipartFile("file",filename,"application/octet-stream",Files.readAllBytes(Path.of("demo-excel",filename)));assertEquals(false,excel.importBoxes(2,file,false).get("ok"));assertEquals(0,db.count("select count(*) from thung_hang where lot_id=2"));}}
 @Test void stagingCapacityAndRegion(){db.run("update thung_hang set status='ACCEPTED',staging_id=null where id=1");assertThrows(IllegalArgumentException.class,()->w.execute("stage",p("id",1,"staging_id",2),2));db.run("update khu_cho set capacity=1 where id=1");assertThrows(IllegalArgumentException.class,()->w.execute("stage",p("id",1,"staging_id",1),2));}
 @Test void partialDeliveryAndReturn(){long t=trip(3,"MN");allocate(t,5);dispatch(t);var aa=db.rows("select a.* from phan_bo_thung a join diem_giao_chuyen s on s.id=a.stop_id where s.trip_id=? order by a.id",t);long first=n(aa.getFirst(),"id"),second=n(aa.get(1),"id");w.execute("deliver",p("allocation_id",first,"status","DELIVERED","delivered_at",OffsetDateTime.now().toString()),3);assertEquals("ALLOCATED",s(db.get("yeu_cau_giao",5),"status"));w.execute("deliver",p("allocation_id",second,"status","FAILED","note","Khách chưa nhận","delivered_at",OffsetDateTime.now().toString()),3);assertEquals("SHIPPED",s(db.get("phan_bo_thung",second),"status"));w.execute("deliver",p("allocation_id",second,"status","RETURN_PENDING","note","Hoàn về kho","delivered_at",OffsetDateTime.now().toString()),3);long box=n(aa.get(1),"box_id");assertNull(db.get("thung_hang",box).get("staging_id"));w.execute("returnBox",p("id",box,"note","Đã nhận lại, nguyên thùng"),2);assertEquals("RETURNED",s(db.get("thung_hang",box),"status"));assertNull(db.get("thung_hang",box).get("staging_id"));}
 @Test void pdfContainsVietnamese()throws Exception{byte[] bytes=pdf.trip(1);try(var doc=org.apache.pdfbox.Loader.loadPDF(bytes)){String text=new org.apache.pdfbox.text.PDFTextStripper().getText(doc);assertTrue(text.contains("Bình Dương"));assertTrue(text.contains("Khách hàng"));assertTrue(text.contains("Trọng lượng"));}}
 @Test void protectLastAdmin(){var u=new HashMap<>(db.get("nguoi_dung",1));u.put("active",false);assertThrows(IllegalArgumentException.class,()->master.save("nguoi_dung",u,1));}

 @Test void endToEndExcelWarehouseHandoff()throws Exception{
 var file=new MockMultipartFile("file","demo.xlsx","application/octet-stream",Files.readAllBytes(Path.of("demo-excel/01_nhap_du_10_thung.xlsx")));
 excel.importBoxes(2,file,false);long visit=book(2,1,true);w.execute("enter",p("id",visit),3);
 var bs=db.rows("select * from thung_hang where lot_id=2 order by id");int i=0;
 for(var b:bs){w.execute("inspect",p("id",n(b,"id"),"quantity",10,"gross_kg",300,"result","ACCEPTED"),2);w.execute("stage",p("id",n(b,"id"),"staging_id",3),2);if(++i==7)assertFalse(w.sufficient(7));}
 assertTrue(w.sufficient(7));w.execute("unload",p("id",2),2);w.execute("leave",p("id",visit),3);assertEquals("LEFT",s(db.get("chuyen_nhap",2),"status"));long t=trip(3,"MN");allocate(t,7);assertEquals(3000,w.weight(t));
 }
 @Test void returnThenRedeliverCompletesOnlyAfterActualDelivery(){
 long t=trip(3,"MN");allocate(t,5);dispatch(t);var aa=db.rows("select a.* from phan_bo_thung a join diem_giao_chuyen s on s.id=a.stop_id where s.trip_id=? order by a.id",t);
 for(int i=0;i<aa.size();i++)w.execute("deliver",p("allocation_id",n(aa.get(i),"id"),"status",i==1?"RETURN_PENDING":"DELIVERED","note","Demo giao và hoàn","delivered_at",OffsetDateTime.now().toString()),3);
 long box=n(aa.get(1),"box_id");w.execute("returnBox",p("id",box,"note","Nhận nguyên thùng"),2);assertEquals("DONE",s(db.get("chuyen_xuat",t),"status"));
 w.execute("reinspect",p("id",box,"quantity",10,"gross_kg",1000,"result","ACCEPTED"),2);w.execute("stage",p("id",box,"staging_id",3),2);
 long again=trip(3,"MN");allocate(again,5);assertEquals(1000,w.weight(again));dispatch(again);long aid=db.count("select max(id) from phan_bo_thung where box_id=?",box);
 w.execute("deliver",p("allocation_id",aid,"status","DELIVERED","delivered_at",OffsetDateTime.now().toString()),3);assertEquals("DONE",s(db.get("yeu_cau_giao",5),"status"));assertEquals(1,db.count("select count(*) from phan_bo_thung where box_id=? and status='RETURNED'",box));
 }
 @Test void earlyConsentAndLateFlagPreserveRealResults(){
 long t=trip(3,"MN");allocate(t,5);dispatch(t);long aid=db.count("select max(a.id) from phan_bo_thung a join diem_giao_chuyen s on s.id=a.stop_id where s.trip_id=?",t);
 db.run("update yeu_cau_giao set earliest=current_date+1,latest=current_date+3 where id=5");
 var data=p("allocation_id",aid,"status","DELIVERED","delivered_at",OffsetDateTime.now().toString());assertThrows(IllegalArgumentException.class,()->w.execute("deliver",data,3));w.execute("consent",p("id",5,"reason","Khách xác nhận qua điện thoại"),3);w.execute("deliver",data,3);
 long second=db.count("select min(a.id) from phan_bo_thung a join diem_giao_chuyen s on s.id=a.stop_id where s.trip_id=?",t);db.run("update yeu_cau_giao set earliest=current_date-3,latest=current_date-1 where id=5");w.execute("deliver",p("allocation_id",second,"status","DELIVERED","delivered_at",OffsetDateTime.now().toString()),3);assertEquals(1,db.count("select count(*) from chi_tiet_giao where allocation_id=? and late",second));
 }

 @Test void renderAllPagesAndExportQaFixtures()throws Exception{
 Path dir=Path.of("target/ui-fixtures");Files.createDirectories(dir);
 for(String[] item:List.of(new String[]{"admin","ADMIN","/management"},new String[]{"kho","KHO","/warehouse"},new String[]{"dieuphoi","DP","/dispatch"})){
  String username=item[0],role=item[1];
  String json=mvc.perform(get("/api/state").with(user(username).roles(role))).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(java.nio.charset.StandardCharsets.UTF_8);Files.writeString(dir.resolve(username+".json"),json);
  for(String route:List.of("/",item[2],"/account")){
   String html=mvc.perform(get(route).with(user(username).roles(role))).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(java.nio.charset.StandardCharsets.UTF_8);
   Files.writeString(dir.resolve(username+(route.equals("/")?"-overview":route.replace('/','-'))+".html"),html);
  }
 }
 }
}
