package com.arpit.taskk.repository;

import com.arpit.taskk.entity.Task;
import com.arpit.taskk.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserId(Long userId);

    List<Task> findByTaskStatusAndUserId(Status status, Long userId);

    BigDecimal countByTaskStatusAndUserId(Status status, Long userId);
}