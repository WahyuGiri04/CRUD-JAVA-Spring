package com.exampl.employee.controller;

import com.exampl.employee.model.Course;
import com.exampl.employee.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CourseController {

    private final CourseService service;

    public CourseController (CourseService service){
        this.service = service;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "courseName", "asc", model);
    }

    @GetMapping("/add")
    public String showNewCourseForm(Model model) {
        Course Course = new Course();
        model.addAttribute("course", Course);
        return "Course/new_course";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute("course") Course course) {
        // save Course to database
        String result = service.save(course);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {

        Course course = service.getById(id);
        model.addAttribute("course", course);
        return "Course/update_course";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable (value = "id") long id) {

        String result = service.delete(id);
        return "redirect:/";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Course> page = service.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Course> listCourses = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listCourses", listCourses);
        return "Course/index";
    }
}
