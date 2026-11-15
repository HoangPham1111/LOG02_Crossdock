package vn.crossdock;
import org.springframework.boot.ApplicationArguments;import org.springframework.boot.ApplicationRunner;import org.springframework.stereotype.Component;import org.springframework.beans.factory.annotation.Value;import org.springframework.security.crypto.password.PasswordEncoder;import org.springframework.transaction.annotation.Transactional;import java.nio.charset.StandardCharsets;
@Component public class Seed implements ApplicationRunner {
 final Db db;final PasswordEncoder encoder;final boolean enabled;
 public Seed(Db d,PasswordEncoder e,@Value("${app.seed}")boolean enabled){db=d;encoder=e;this.enabled=enabled;}
 @Override @Transactional public void run(ApplicationArguments args)throws Exception{db.lock();if(enabled&&db.count("select count(*) from nguoi_dung")==0){try(var in=getClass().getResourceAsStream("/db/seed.sql")){String sql=new String(in.readAllBytes(),StandardCharsets.UTF_8).replace("{PASSWORD}",encoder.encode("Demo@12345"));for(String statement:sql.split(";"))if(!statement.isBlank())db.jdbc.execute(statement);}}}
}
