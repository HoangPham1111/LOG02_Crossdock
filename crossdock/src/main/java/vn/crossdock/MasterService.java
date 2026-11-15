package vn.crossdock;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.*;import static vn.crossdock.Db.*;
@Service public class MasterService {
 private final Db db;private final PasswordEncoder encoder;
 public MasterService(Db d,PasswordEncoder e){db=d;encoder=e;}
 public static final Map<String,List<String>> FIELDS=Map.of(
 "nguoi_dung",List.of("code","name","email","role","active"),
 "khach_hang",List.of("code","name","phone","active"),
 "dia_diem_giao",List.of("code","name","customer_id","address","region","latitude","longitude","active"),
 "nha_cung_cap",List.of("code","name","phone","active"),
 "linh_kien",List.of("code","name","unit","active"),
 "xe",List.of("code","plate","region","capacity_kg","active"),
 "cong_kho",List.of("name","active"),
 "khu_cho",List.of("code","name","region","capacity","active"));
 public static boolean allowed(String table,String role){return FIELDS.containsKey(table)&&(Set.of("khach_hang","dia_diem_giao","nha_cung_cap").contains(table)?role.equals("DP"):role.equals("ADMIN"));}
 @Transactional public long save(String table,Map<String,Object> m,long actor){
 require(FIELDS.containsKey(table),"Danh mục không hợp lệ");db.lock();long id=m.containsKey("id")?Long.parseLong(m.get("id").toString()):0;
 Map<String,Object> old=id>0?db.get(table,id):Map.of();
 if(table.equals("cong_kho")){require(id>0,"Giữ nguyên 3 cổng nhập và 3 cổng xuất");require(db.count("select count(*) from luot_phuc_vu where gate_id=? and status in ('IN_GATE','WAITING')",id)==0,"Cổng đang có lịch hoặc đang phục vụ");}
 if(table.equals("nguoi_dung")&&id>0){require(id!=actor||("ADMIN".equals(s(m,"role"))&&Boolean.parseBoolean(s(m,"active"))),"Không được tự khóa hoặc hạ quyền tài khoản đang dùng");if("ADMIN".equals(s(old,"role"))&&(!"ADMIN".equals(s(m,"role"))||!Boolean.parseBoolean(s(m,"active"))))require(db.count("select count(*) from nguoi_dung where role='ADMIN' and active and id<>?",id)>0,"Phải còn ít nhất một quản trị viên");}
 if(table.equals("xe")){require(d(m,"capacity_kg")>0&&d(m,"capacity_kg")<=18000,"Tải trọng xe phải trong 0–18.000 kg");require(Set.of("MB","MT","MN").contains(s(m,"region"))&&s(m,"code").startsWith(s(m,"region")),"Mã xe phải bắt đầu bằng mã miền");if(id>0)require(db.count("select count(*) from chuyen_xuat where vehicle_id=? and status not in ('DONE','CANCELLED')",id)+db.count("select count(*) from chuyen_nhap where vehicle_id=? and status not in ('LEFT','CANCELLED')",id)==0,"Xe đang được sử dụng");}
 if(table.equals("khu_cho")&&id>0){long used=db.count("select count(*) from thung_hang where staging_id=?",id);require(d(m,"capacity")>=used,"Sức chứa không thể thấp hơn số thùng hiện có");require(used==0||(s(old,"region").equals(s(m,"region"))&&Boolean.parseBoolean(s(m,"active"))),"Khu còn hàng: không đổi miền hoặc ngừng sử dụng");}
 if(table.equals("dia_diem_giao")&&id>0)require(db.count("select count(*) from yeu_cau_giao where location_id=?",id)==0,"Địa điểm đã có yêu cầu giao: tạo địa điểm mới để giữ lịch sử");
 if(table.equals("linh_kien")&&id>0)require(db.count("select count(*) from chi_tiet_yeu_cau where part_id=?",id)==0||s(old,"unit").equals(s(m,"unit")),"Không đổi đơn vị linh kiện đã có đơn hàng");
 List<String> cols=new ArrayList<>(FIELDS.get(table));List<Object> args=new ArrayList<>();
 for(String c:cols){Object v=m.get(c);if(c.equals("active"))v=Boolean.parseBoolean(Objects.toString(v,"true"));else if(c.endsWith("_id")||c.equals("capacity"))v=Long.parseLong(Objects.toString(v,"0"));else if(Set.of("latitude","longitude","capacity_kg").contains(c))v=v==null||v.toString().isBlank()?null:new java.math.BigDecimal(v.toString());else {v=Objects.toString(v,"").trim();require(!v.toString().isBlank()||c.equals("phone"),"Thiếu thông tin: "+c);}args.add(v);}
 if(table.equals("nguoi_dung")&&(id==0||!s(m,"password").isBlank())){require(s(m,"password").length()>=8,"Mật khẩu ít nhất 8 ký tự");cols.add("password");args.add(encoder.encode(s(m,"password")));}
 if(id==0){return db.insert("insert into "+table+"("+String.join(",",cols)+") values("+String.join(",",Collections.nCopies(cols.size(),"?"))+")",args.toArray());}
 args.add(id);db.run("update "+table+" set "+String.join(",",cols.stream().map(c->c+"=?").toList())+" where id=?",args.toArray());return id;
 }
}
