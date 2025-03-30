package com.arpit.taskk.services;

import com.arpit.taskk.dto.DailyScoreDTO;
import com.arpit.taskk.entity.enums.Status;
import com.arpit.taskk.repository.DailyScoreRepository;
import com.arpit.taskk.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final UserService userService;
    private final TaskRepository taskRepository;
    private final DailyScoreRepository dailyScoreRepository;

    public List<BigDecimal> getTaskSummary() {
        Long userId = userService.getUserId();
        List<BigDecimal> ls = new ArrayList<>();
        ls.add(taskRepository.countByStatusAndUserId(Status.COMPLETED, userId));
        ls.add(taskRepository.countByStatusAndUserId(Status.PENDING, userId));
        ls.add(taskRepository.countByStatusAndUserId(Status.ONGOING, userId));
        return ls;
    }


}
