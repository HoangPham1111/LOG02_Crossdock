package vn.crossdock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.*;
@Component public class Db {
 public final JdbcTemplate jdbc; public Db(JdbcTemplate j){jdbc=j;}
 public List<Map<String,Object>> rows(String sql,Object...p){return jdbc.queryForList(sql,p);}
 public Map<String,Object> one(String sql,Object...p){var r=rows(sql,p);if(r.isEmpty())throw new IllegalArgumentException("Không tìm thấy bản ghi hoặc bản ghi đã thay đổi");return r.getFirst();}
 public Map<String,Object> get(String table,long id){return one("select * from "+table+" where id=?",id);}
 public long count(String sql,Object...p){return jdbc.queryForObject(sql,Long.class,p);}
 public long insert(String sql,Object...p){return jdbc.queryForObject(sql+" returning id",Long.class,p);}
 public void run(String sql,Object...p){jdbc.update(sql,p);}
 public void lock(){jdbc.execute("select pg_advisory_xact_lock(20261113)");}
 public static long n(Map<String,Object>m,String k){Object v=m.get(k);return v==null?0:((Number)v).longValue();}
 public static double d(Map<String,Object>m,String k){Object v=m.get(k);return v==null?0:((Number)v).doubleValue();}
 public static String s(Map<String,Object>m,String k){return Objects.toString(m.get(k),"");}
 public static void require(boolean ok,String message){if(!ok)throw new IllegalArgumentException(message);}
}
