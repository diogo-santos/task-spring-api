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

    @PostMapping("/user/{userId}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create task", notes = "Create a single task in the system", nickname = "createTask")
    public Task create(@PathVariable final Long userId, @RequestBody @Validated final CreateTaskDto taskDto) {
        Task task = new Task();
        task.setUserId(userId);
        task.setDescription(taskDto.getDescription());
        task.setChecked(false);
        return this.taskRepo.save(task);
    }

    @PutMapping("/user/{userId}/tasks/{taskId}/status")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update task status", notes = "Check/Uncheck a task", nickname = "updateTaskStatus")
    public void updateStatus(@PathVariable final Long userId, @PathVariable final Long taskId) {
        Optional<TaskDto> taskOptional = taskRepo.findByIdAndUserId(taskId, userId);
        taskOptional.ifPresent(task ->
            taskRepo.updateTaskStatus(task.getId())
        );
    }

    @DeleteMapping("/user/{userId}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete task", notes = "Delete a task", nickname = "deleteTask")
    public void delete(@PathVariable final Long userId, @PathVariable final Long taskId) {
        Optional<TaskDto> taskOptional = taskRepo.findByIdAndUserId(taskId, userId);
        taskOptional.ifPresent(task ->
                taskRepo.deleteById(task.getId())
        );
    }
}