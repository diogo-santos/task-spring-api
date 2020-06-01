package com.task.service;

import com.task.service.dao.Task;
import com.task.service.dao.TaskRepository;
import com.task.service.domain.CreateTaskDto;
import com.task.service.domain.TaskDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/")
@Api(value = "tasks", tags = "tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepo;

    @GetMapping("/user/{userId}/tasks")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all tasks by user Id", notes = "Get all tasks by user Id", nickname = "getTasksByUserId")
    public List<TaskDto> findByUserId(@PathVariable final Long userId) {
        return this.taskRepo.findAllByUserId(userId);
    }
}