package dasniko.authdemo.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        request.logout();
        return "redirect:/";
    }

}
