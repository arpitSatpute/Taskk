package com.arpit.taskk.dto;

import com.arpit.taskk.entity.enums.Status;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private Long taskId;
    private String taskName;
    private String taskDescription;
    private Status taskStatus;
    private Long userId;
}