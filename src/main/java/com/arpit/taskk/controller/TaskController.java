package com.arpit.taskk.controller;

import com.arpit.taskk.dto.TaskDTO;
import com.arpit.taskk.entity.enums.Status;
import com.arpit.taskk.services.TaskService;
import com.arpit.taskk.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@taskService.isOwnerOfTask(#id)")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @GetMapping("/get")
    public ResponseEntity<List<TaskDTO>> getTask() {
        Long userId = userService.getUserId();
        return ResponseEntity.ok(taskService.getTask(userId));
    }

    @GetMapping("/getByStatus/{status}/{userId}")
    @PreAuthorize("@userService.isOwner(#userId)")
    public ResponseEntity<List<TaskDTO>> getTaskByStatus(@PathVariable Status status, @PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getTaskByStatus(status, userId));
    }

    @PutMapping("/update/{taskId}")
    @PreAuthorize("@taskService.isOwnerOfTask(#taskId)")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.updateTask(taskId));
    }

    @PostMapping("/add")
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO taskDTO) {
        Long userId = userService.getUserId();
        return ResponseEntity.ok(taskService.addTask(taskDTO, userId));
    }



}