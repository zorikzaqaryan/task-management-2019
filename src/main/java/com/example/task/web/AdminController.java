package com.example.task.web;

import com.example.task.model.TaskUploadCmd;
import com.example.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final int DEFAULT_PAGE_SIZE = 3;
    @Autowired
    private TaskService taskService;
    
    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.GET)
    public String taskEditPageInit(@PathVariable("taskId") int taskId, Model model) {
        model.addAttribute("task", taskService.getTaskById(taskId));
        return "task_edit";
    }
    
    @PostMapping("/tasks/edit")
    public String taskEditPage(TaskUploadCmd task, Model model, @PageableDefault(size = DEFAULT_PAGE_SIZE) Pageable pageable) {
        taskService.updateTaskContentAndEdited(task.getContent(), task.getEdited(), task.getId());
        model.addAttribute("pages", taskService.findAll(pageable));
        model.addAttribute("task", new TaskUploadCmd());
        return "redirect:/tasks";
    }
}