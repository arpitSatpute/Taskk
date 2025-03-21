package com.arpit.taskk.controller;

import com.arpit.taskk.dto.TaskDTO;
import com.arpit.taskk.entity.enums.Status;
import com.arpit.taskk.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<List<TaskDTO>> getTask(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getTask(userId));
    }

    @GetMapping("/getByStatus/{status}/{userId}")
    public ResponseEntity<List<TaskDTO>> getTaskByStatus(@PathVariable Status status, @PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getTaskByStatus(status, userId));
    }

    @PutMapping("/update")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.updateTask(taskDTO));
    }

    @PostMapping("/add")
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.addTask(taskDTO));
    }
}