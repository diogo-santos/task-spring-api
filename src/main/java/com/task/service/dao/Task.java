package com.task.service.dao;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull
    private Long userId;
    @NotBlank
    private String description;
    @NotNull
    private Boolean checked;
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}