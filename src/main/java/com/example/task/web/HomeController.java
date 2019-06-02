package com.example.task.web;

import com.example.task.exception.ImageUploadException;
import com.example.task.model.TaskUploadCmd;
import com.example.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class HomeController {
    private static final int DEFAULT_PAGE_SIZE = 3;
    private static final String USER_HOME_PAGE = "home";
    private static final String PAGES = "pages";
    
    @Autowired
    private TaskService taskService;
    
    @GetMapping("/tasks")
    public String home(@PageableDefault(size = DEFAULT_PAGE_SIZE) Pageable pageable, Model model) {
        model.addAttribute(PAGES, taskService.findAll(pageable));
        model.addAttribute("task", new TaskUploadCmd());
        return USER_HOME_PAGE;
    }
    
    @PostMapping("/tasks")
    public String saveTask(@PageableDefault(size = DEFAULT_PAGE_SIZE) Pageable pageable,
                           @Valid @ModelAttribute("task") TaskUploadCmd task, BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            model.addAttribute(task);
            model.addAttribute(PAGES, taskService.findAll(pageable));
            return USER_HOME_PAGE;
        }
        try {
            taskService.save(task);
        } catch (IOException e) {
            throw new ImageUploadException("Something went wrong", e);
        }
        model.addAttribute(PAGES, taskService.findAll(pageable));
        model.addAttribute("task", new TaskUploadCmd());
        return USER_HOME_PAGE;
    }
}