package com.arpit.taskk.services;

import com.arpit.taskk.dto.TaskDTO;
import com.arpit.taskk.entity.Task;
import com.arpit.taskk.entity.User;
import com.arpit.taskk.entity.enums.Status;
import com.arpit.taskk.exceptions.ResourceNotFoundException;
import com.arpit.taskk.repository.TaskRepository;
import com.arpit.taskk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<TaskDTO> getTask(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        List<Task> taskList = taskRepository.findByUserId(user.getId());
        return taskList.stream()
                .map(task -> modelMapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
    }

    public List<TaskDTO> getTaskByStatus(Status status, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        List<Task> list = taskRepository.findByStatusAndUserId(status, userId);
        return list.stream()
                .map(task -> modelMapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
    }


    public TaskDTO addTask(TaskDTO taskDTO, Long userId) {
        Task task = Task.builder()
                .taskName(taskDTO.getTaskName())
                .taskDescription(taskDTO.getTaskDescription())
                .status(Status.ONGOING)
                .user(userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found")))
                .build();

        Task savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask, TaskDTO.class);
    }

    public TaskDTO updateTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));

        if(task.getStatus() == Status.PENDING) {
            deleteTask(taskId);
            return null;
        }
        task.setStatus(Status.COMPLETED);
        Task updatedTask = taskRepository.save(task);
        return modelMapper.map(updatedTask, TaskDTO.class);
    }

    public boolean isOwnerOfTask(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        return user.getEmail().equals(task.getUser().getEmail());
    }



}