package vn.crossdock;
import org.springframework.stereotype.Service;import java.util.*;import static vn.crossdock.Db.*;
@Service public class QueryService {
 final Db db;final Workflow flow;final Settings settings;
 public QueryService(Db d,Workflow w,Settings s){db=d;flow=w;settings=s;}
 public Map<String,Object> state(String username){var user=db.one("select id,code,name,email,role from nguoi_dung where code=? and active",username);Map<String,Object> out=new LinkedHashMap<>();out.put("user",user);out.put("settings",settings.all());
 for(String t:List.of("khach_hang","dia_diem_giao","don_hang","yeu_cau_giao","linh_kien","chi_tiet_yeu_cau","nha_cung_cap","xac_nhan_cung_cap","xe","chuyen_nhap","lo_nhap","khu_cho","thung_hang","cong_kho","chuyen_xuat","luot_phuc_vu","diem_giao_chuyen","phan_bo_thung","ket_qua_giao_hang","chi_tiet_giao"))out.put(t,db.rows("select * from "+t+" order by id desc limit 2000"));
 if("ADMIN".equals(s(user,"role")))out.put("nguoi_dung",db.rows("select id,code,name,email,role,active from nguoi_dung order by id"));
 var progress=db.rows("select r.id,r.code,r.status,r.earliest,r.latest,l.region,l.name location_name,(select coalesce(sum(d.quantity),0) from chi_tiet_yeu_cau d where d.requirement_id=r.id) requested_qty,(select coalesce(sum(b.quantity),0) from thung_hang b join chi_tiet_yeu_cau d on d.id=b.detail_id where d.requirement_id=r.id and b.status in ('STAGED','ALLOCATED','LOADED','SHIPPED','DELIVERED')) staged_qty from yeu_cau_giao r join dia_diem_giao l on l.id=r.location_id order by r.latest,r.id");for(var r:progress)r.put("sufficient",flow.sufficient(n(r,"id")));out.put("progress",progress);
 out.put("capacity",db.rows("select k.*,count(b.id) used from khu_cho k left join thung_hang b on b.staging_id=k.id group by k.id order by k.id"));
 out.put("trip_load",db.rows("select t.id,t.code,t.status,x.capacity_kg,coalesce(sum(b.gross_kg) filter(where a.status<>'CANCELLED'),0) kg,count(a.id) filter(where a.status='LOADED') loaded,count(a.id) filter(where a.status='ALLOCATED') waiting from chuyen_xuat t join xe x on x.id=t.vehicle_id left join diem_giao_chuyen s on s.trip_id=t.id left join phan_bo_thung a on a.stop_id=s.id left join thung_hang b on b.id=a.box_id group by t.id,x.capacity_kg order by t.id desc"));
 out.put("report",Map.of("boxesInWarehouse",db.count("select count(*) from thung_hang where status in ('ACCEPTED','STAGED','ALLOCATED','LOADED','QUARANTINE','RETURNED')"),"deliveredBoxes",db.count("select count(*) from thung_hang where status='DELIVERED'"),"lateDeliveries",db.count("select count(*) from chi_tiet_giao where status='DELIVERED' and late"),"stagingWarnings",db.count("select count(*) from thung_hang where staging_id is not null and staged_at<now()-make_interval(hours=>?)",settings.get("maxStagingHours")),"failedAttempts",db.count("select count(*) from chi_tiet_giao where status='FAILED'")));
 out.put("updatedAt",java.time.OffsetDateTime.now().toString());return out;
 }
}
