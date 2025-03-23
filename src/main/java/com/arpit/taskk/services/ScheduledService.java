package com.arpit.taskk.services;

import com.arpit.taskk.entity.Task;
import com.arpit.taskk.entity.WeeklyScore;
import com.arpit.taskk.entity.enums.Status;
import com.arpit.taskk.repository.TaskRepository;
import com.arpit.taskk.repository.WeeklyScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduledService {

    private final WeeklyScoreRepository weeklyScoreRepository;
    private final TaskRepository taskRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateWeeklyScore() {
        List<WeeklyScore> weeklyScores = weeklyScoreRepository.findAll();
        for (WeeklyScore weeklyScore : weeklyScores) {
            weeklyScore.setDay7(weeklyScore.getDay6());
            weeklyScore.setDay6(weeklyScore.getDay5());
            weeklyScore.setDay5(weeklyScore.getDay4());
            weeklyScore.setDay4(weeklyScore.getDay3());
            weeklyScore.setDay3(weeklyScore.getDay2());
            weeklyScore.setDay2(weeklyScore.getDay1());
            weeklyScore.setDay1(BigDecimal.ZERO);
            weeklyScoreRepository.save(weeklyScore);
        }
    }

    @Scheduled(cron = "0 2 0 * * ?")
    public void updateTaskList() {
        taskRepository.deleteAllByStatus(Status.COMPLETED);
        List<Task> tasks = taskRepository.findAllByStatus(Status.ONGOING);
        for (Task task : tasks) {
            task.setStatus(Status.PENDING);
            taskRepository.save(task);
        }
    }

}
