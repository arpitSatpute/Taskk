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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository usersRepository;
    private final ModelMapper modelMapper;

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<TaskDTO> getTask(Long userId) {
        User user = usersRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        List<Task> taskList = taskRepository.findByUserId(user.getId());
        return taskList.stream()
                .map(task -> modelMapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
    }

    public List<TaskDTO> getTaskByStatus(Status status, Long userId) {
        User user = usersRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        List<Task> list = taskRepository.findByTaskStatusAndUserId(status, userId);
        return list.stream()
                .map(task -> modelMapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
    }


    public TaskDTO addTask(TaskDTO taskDTO) {
        Task task = modelMapper.map(taskDTO, Task.class);
        Task savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask, TaskDTO.class);
    }

    public TaskDTO updateTask(TaskDTO taskDTO) {
        Task task = taskRepository.findById(taskDTO.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskDTO.getTaskId()));
        task.setTaskName(taskDTO.getTaskName());
        task.setTaskDescription(taskDTO.getTaskDescription());
        task.setTaskStatus(taskDTO.getTaskStatus());
        Task updatedTask = taskRepository.save(task);
        return modelMapper.map(updatedTask, TaskDTO.class);
    }
}