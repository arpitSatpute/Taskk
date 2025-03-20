package com.arpit.taskk.entity;

import com.arpit.taskk.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private String taskName;
    private String taskDescription;
    private Status taskStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}