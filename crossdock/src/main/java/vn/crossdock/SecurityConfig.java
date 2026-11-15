package vn.crossdock;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import vn.crossdock.domain.UserRepository;
@Configuration @EnableMethodSecurity
public class SecurityConfig {
 @Bean PasswordEncoder encoder(){return new BCryptPasswordEncoder();}
 @Bean UserDetailsService users(UserRepository repo){return code->repo.findByCode(code).map(u->User.withUsername(u.code).password(u.password).roles(u.role).disabled(!u.active).build()).orElseThrow(()->new UsernameNotFoundException("Không tìm thấy tài khoản"));}
 @Bean SecurityFilterChain chain(HttpSecurity h) throws Exception {
 h.authorizeHttpRequests(a->a.requestMatchers("/login","/css/**","/js/**","/fonts/**","/error").permitAll().requestMatchers("/management").hasRole("ADMIN").requestMatchers("/dispatch").hasRole("DP").requestMatchers("/warehouse").hasRole("KHO").anyRequest().authenticated())
 .formLogin(f->f.loginPage("/login").defaultSuccessUrl("/",true).permitAll()).logout(l->l.logoutSuccessUrl("/login?logout"));
 return h.build();
 }
}
