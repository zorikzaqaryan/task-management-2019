package com.example.task.service;

import com.example.task.mapper.TaskMapper;
import com.example.task.model.TaskDto;
import com.example.task.model.TaskUploadCmd;
import com.example.task.repository.TaskRepository;
import com.example.task.repository.entity.Task;
import com.example.task.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    
    public Page<TaskDto> findAll(Pageable pageable) {
        if (!pageable.getSort().isSorted()) {
            pageable = PageRequest.
                    of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Order.desc("id")));
        }
        Page<Task> pages = taskRepository.findAll(pageable);
        List<TaskDto> dtos = TaskMapper.INSTANCE.taskListToDtos(pages.getContent());
        return new PageImpl<>(dtos, pageable, pages.getTotalElements());
        
    }
    
    
    public TaskDto save(TaskUploadCmd task) throws IOException {
        Task entity = TaskMapper.INSTANCE.taskDtoToTask(TaskMapper.INSTANCE.taskCmdToDto(task));
        byte[] bytes;
        if (!ImageUtil.isValidImageWidthAndHeight(task.getFile().getBytes())) {
            bytes = ImageUtil.resizeImage(task.getFile().getBytes(), task.getFile().getContentType());
        } else {
            bytes = task.getFile().getBytes();
        }
        entity.setImage(bytes);
        return TaskMapper.INSTANCE.taskToTaskDto(taskRepository.save(entity));
    }
    
    public TaskDto preview(TaskUploadCmd task) throws IOException {
        Task entity = TaskMapper.INSTANCE.taskDtoToTask(TaskMapper.INSTANCE.taskCmdToDto(task));
        byte[] bytes;
        if (task.getFile() != null) {
            if (!ImageUtil.isValidImageWidthAndHeight(task.getFile().getBytes())) {
                bytes = ImageUtil.resizeImage(task.getFile().getBytes(), task.getFile().getContentType());
            } else {
                bytes = task.getFile().getBytes();
            }
            entity.setImage(bytes);
        }
        
        
        return TaskMapper.INSTANCE.taskToTaskDto(entity);
    }
    
    public TaskDto getTaskById(Integer id) {
        return TaskMapper.INSTANCE.taskToTaskDto(taskRepository.getOne(id));
    }
    
    public void updateTaskContentAndEdited(String content, Boolean edited, int id) {
        taskRepository.updateTaskContentAndEdited(content,edited, id);
    }
}
