package lt.kaunascoding.web.controller;

import lt.kaunascoding.web.model.Duombaze;
import lt.kaunascoding.web.model.tables.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

@Controller
public class StudentsController {

    @GetMapping("/students")
    String atsakymas(
            Model model
    ) {
        Duombaze db = new Duombaze();
        model.addAttribute("studentForm", new Student());
        model.addAttribute("list", db.getAllStudents());
        return "students";
    }

    @PostMapping("/students")
    public String postAtsakymas(
            @ModelAttribute("studentForm") Student student,
            BindingResult result,
            Model model
    ) {
        Duombaze db = new Duombaze();
        if (!StringUtils.isEmpty(student.getName()) && !StringUtils.isEmpty(student.getSurname())) {
            db.insertStudent(student.getName(), student.getSurname(), student.getPhone(), student.getEmail());
        }
        model.addAttribute("list", db.getAllStudents());
        return "students";
    }

}
