package com.arpit.taskk.dto;

import com.arpit.taskk.entity.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {


    private Long taskId;

    @NotEmpty(message = "Task name cannot be empty")
    private String taskName;
    private String taskDescription;
    private Status taskStatus;

    private Long userId;
}