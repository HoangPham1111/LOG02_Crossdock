package vn.crossdock;
import org.springframework.stereotype.Controller;import org.springframework.web.bind.annotation.*;import org.springframework.ui.Model;
import java.security.Principal;import java.util.*;
@Controller public class WebController {
 final Db db;public WebController(Db d){db=d;}
 @GetMapping("/login")String login(){return "login";}
 @GetMapping({"/","/management","/warehouse","/dispatch","/account"})String app(Principal p,Model model,jakarta.servlet.http.HttpServletRequest request){model.addAttribute("user",db.one("select id,code,name,email,role from nguoi_dung where code=?",p.getName()));model.addAttribute("page",request.getRequestURI());return "app";}
}
