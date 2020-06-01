package com.task.service.domain;

import java.time.LocalDateTime;

public interface TaskDto {
    Long getId();
    String getDescription();
    Boolean getChecked();
    LocalDateTime getLastUpdate();
}
