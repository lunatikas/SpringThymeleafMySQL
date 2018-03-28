package lt.kaunascoding.web.controller;

import lt.kaunascoding.web.model.Duombaze;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentsController {
    @RequestMapping("/students")
    String atsakymas(Model model){
        Duombaze db = new Duombaze();
        model.addAttribute("list",db.getAllStudents());
        return "students";
    }
}
