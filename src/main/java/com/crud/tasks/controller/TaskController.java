package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.exception.TaskNotFoundException;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/task")
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    @GetMapping
    List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @GetMapping("/{taskId}")
    TaskDto getTask(@PathVariable Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(service.getTask(taskId));
    }

    @DeleteMapping("/{taskId}")
    void deleteTask(@PathVariable Long taskId) throws EntityNotFoundException {
        service.deleteById(taskId);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    TaskDto updateTask(@RequestBody TaskDto taskDto) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(service.updateTask(taskMapper.mapToTask(taskDto)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    void createTask(@RequestBody TaskDto taskDto) {
        service.saveTask(taskMapper.mapToTask(taskDto));
    }

}