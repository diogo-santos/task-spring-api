package com.task.service.dao;

import com.task.service.domain.TaskDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<TaskDto> findAllByUserId(Long userId);

    Optional<TaskDto> findByIdAndUserId(Long id, Long userId);

    default void updateTaskStatus(Long id) {
        this.findById(id).ifPresent(task -> {
            boolean checked = task.getChecked() == null || !task.getChecked();
            task.setChecked(checked);
            this.saveAndFlush(task);
        });
    }
}