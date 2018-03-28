package lt.kaunascoding.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class IndexController {
    @RequestMapping("/")
    String index(Model model) {
        model.addAttribute("labas", "Veikiu!!!!");
        return "index";
    }
}
