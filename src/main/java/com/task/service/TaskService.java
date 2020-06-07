package com.task.service;

import com.task.service.dao.Task;
import com.task.service.dao.TaskRepository;
import com.task.service.domain.CreateTaskDto;
import com.task.service.domain.TaskDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepo;

    public TaskService(TaskRepository taskRepo) {
        this.taskRepo = taskRepo;
    }

    public List<TaskDto> findByUserId(final Long userId) {
        return this.taskRepo.findAllByUserId(userId);
    }

    public Task create(final Long userId, final CreateTaskDto taskDto) {
        Task task = new Task();
        task.setUserId(userId);
        task.setDescription(taskDto.getDescription());
        task.setChecked(false);
        return this.taskRepo.save(task);
    }

    public void updateStatus(final Long userId, final Long taskId) {
        Optional<TaskDto> taskOptional = taskRepo.findByIdAndUserId(taskId, userId);
        taskOptional.ifPresent(task ->
                this.taskRepo.updateStatus(task.getId())
        );
    }

    public void delete(final Long userId, final Long taskId) {
        Optional<TaskDto> taskOptional = taskRepo.findByIdAndUserId(taskId, userId);
        taskOptional.ifPresent(task ->
                this.taskRepo.deleteById(task.getId())
        );
    }
}
