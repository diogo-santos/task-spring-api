package com.task.service.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateTaskDto {
    @NotBlank(message = "Please provide a description")
    private String description;
}