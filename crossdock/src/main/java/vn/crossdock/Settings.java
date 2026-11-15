package vn.crossdock;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import java.nio.file.*;import java.util.*;import java.io.*;
@Component public class Settings {
 private final Path file; private final Properties values=new Properties();
 public Settings(@Value("${app.settings-file}")String path)throws IOException{file=Path.of(path);values.setProperty("minLoadPercent","70");values.setProperty("maxStagingHours","24");values.setProperty("southDays","1"); if(Files.exists(file))try(var in=Files.newInputStream(file)){values.load(in);}}
 public synchronized int get(String k){return Integer.parseInt(values.getProperty(k));}
 public synchronized Map<String,Integer> all(){return Map.of("minLoadPercent",get("minLoadPercent"),"maxStagingHours",get("maxStagingHours"),"southDays",get("southDays"));}
 public synchronized void save(Map<String,Object> m)throws IOException {int load=Integer.parseInt(m.get("minLoadPercent").toString()),hours=Integer.parseInt(m.get("maxStagingHours").toString()),days=Integer.parseInt(m.get("southDays").toString());Db.require(load>=1&&load<=100&&hours>=1&&hours<=168&&days>=1&&days<=5,"Tham số ngoài phạm vi cho phép"); Properties next=new Properties();next.setProperty("minLoadPercent",""+load);next.setProperty("maxStagingHours",""+hours);next.setProperty("southDays",""+days); Files.createDirectories(file.toAbsolutePath().getParent());Path temp=file.resolveSibling(file.getFileName()+".tmp");try(var out=Files.newOutputStream(temp)){next.store(out,"CrossDock settings");}Files.move(temp,file,StandardCopyOption.REPLACE_EXISTING);values.putAll(next);}
}
