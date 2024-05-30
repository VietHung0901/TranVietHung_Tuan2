package com.example.ngay28.Controller;

import com.example.ngay28.Entities.Book;
import com.example.ngay28.Entities.Employer;
import com.example.ngay28.Services.BookService;
import com.example.ngay28.Services.EmployerService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/employers")
@RequiredArgsConstructor
public class EmployerController {
    private final EmployerService employerService;
    @GetMapping
    public String showAllEmployers(@NotNull Model model) {
        model.addAttribute("employers", employerService.getAllEmployers());
        return "Employer/list";
    }
    @GetMapping("/add")
    public String addEmployerForm(@NotNull Model model) {
        model.addAttribute("employer", new Employer());
        return "Employer/add";
    }
    @PostMapping("/add")
    public String addEmployer(@ModelAttribute("employer") Employer employer) {
        if(employerService.getEmployerById(employer.getId()).isEmpty())
            employerService.addEmployer(employer);
        return "redirect:/employers";
    }
    @GetMapping("/edit/{id}")
    public String editEmployerForm(@NotNull Model model, @PathVariable long id)
    {
        var employer = employerService.getEmployerById(id).orElse(null);
        model.addAttribute("employer", employer != null ? employer : new Employer());
        return "Employer/edit";
    }
    @PostMapping("/edit")
    public String editEmployer(@ModelAttribute("book") Employer employer) {
        employerService.updateEmployer(employer);
        return "redirect:/employers";
    }
    @GetMapping("/delete/{id}")
    public String deleteEmployer(@PathVariable long id) {
        if (employerService.getEmployerById(id).isPresent())
            employerService.deleteEmployerById(id);
        return "redirect:/employers";
    }
}
