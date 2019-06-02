package com.example.task.web;

import com.example.task.model.TaskUploadCmd;
import com.example.task.service.TaskService;
import com.example.task.util.ImageUtil;
import com.example.task.util.PropertyReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.ResponseEntity.status;

@Controller
@Validated
public class PreviewController {
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private PropertyReader reader;
    
    @Value("${max.file.upload.limit}")
    private Integer uploadLimit;
    
    @PostMapping(value = "/preview", headers = ("content-type=*/*"))
    public @ResponseBody
    ResponseEntity previewTask(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "content") String content,
            @RequestPart(value = "pic", required = false) MultipartFile file) throws IOException {
        
        if (username == null) {
            return status(HttpStatus.BAD_REQUEST).body(reader.toLocale("error.username"));
        }
        if (email == null) {
            return status(HttpStatus.BAD_REQUEST).body(reader.toLocale("error.email"));
        }
        if (content == null) {
            return status(HttpStatus.BAD_REQUEST).body(reader.toLocale("error.content"));
        }
        if (file != null) {
            
            if (file.getSize() > uploadLimit) {
                return status(HttpStatus.BAD_REQUEST).body(reader.toLocale("error.file.size"));
            }
            if (!ImageUtil.isValidImageFormat(file)) {
                return status(HttpStatus.BAD_REQUEST).body(reader.toLocale("error.file.invalid.format"));
            }
        }
        
        TaskUploadCmd task = new TaskUploadCmd();
        task.setUsername(username);
        task.setEmail(email);
        task.setContent(content);
        task.setFile(file);
        return new ResponseEntity<>(taskService.preview(task), HttpStatus.OK);
    }
}