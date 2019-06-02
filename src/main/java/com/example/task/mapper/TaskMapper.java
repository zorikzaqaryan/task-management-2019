package com.example.task.mapper;

import com.example.task.model.TaskDto;
import com.example.task.model.TaskUploadCmd;
import com.example.task.repository.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDto taskToTaskDto(Task entity);

    Task taskDtoToTask(TaskDto dto);

    List<TaskDto> taskListToDtos(List<Task> entity);

    @Named("customTaskMapper")
    default TaskDto taskCmdToDto(TaskUploadCmd in) throws IOException {
        TaskDto out = new TaskDto();
        out.setContent(in.getContent());
        out.setEmail(in.getEmail());
        if (in.getFile() != null) {
            out.setImage(in.getFile().getBytes());
        }
        out.setId(in.getId());
        out.setUsername(in.getUsername());
        return out;
    }
}
