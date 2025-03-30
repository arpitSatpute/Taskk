package com.arpit.taskk.controller;

import com.arpit.taskk.dto.DailyScoreDTO;
import com.arpit.taskk.dto.WeeklyScoreDTO;
import com.arpit.taskk.services.DailyScoreService;
import com.arpit.taskk.services.DashboardService;
import com.arpit.taskk.services.TaskService;
import com.arpit.taskk.services.WeeklyScoreService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final TaskService taskService;
    private final DashboardService dashboardService;
    private final DailyScoreService dailyScoreService;
    private final WeeklyScoreService weeklyScoreService;

    @GetMapping("/get/taskSummary")
    public ResponseEntity<List<BigDecimal>> getTaskSummary() {
        return ResponseEntity.ok(dashboardService.getTaskSummary());
    }

    @GetMapping("/get/dailyScore")
    public ResponseEntity<DailyScoreDTO> getDailyScore() {
        return ResponseEntity.ok(dailyScoreService.getDailyScore());
    }

    @GetMapping("/get/weeklyScore")
    public ResponseEntity<WeeklyScoreDTO> getWeeklyScore() {
        return ResponseEntity.ok(weeklyScoreService.getWeeklyScore());
    }

}
