package com.arpit.taskk.services;

import com.arpit.taskk.dto.DailyScoreDTO;
import com.arpit.taskk.entity.DailyScore;
import com.arpit.taskk.entity.User;
import com.arpit.taskk.entity.enums.Status;
import com.arpit.taskk.exceptions.ResourceNotFoundException;
import com.arpit.taskk.repository.DailyScoreRepository;
import com.arpit.taskk.repository.TaskRepository;
import com.arpit.taskk.repository.UserRepository;
import com.arpit.taskk.strategies.DailyScoreCalculateStrategy;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.arpit.taskk.entity.enums.Status.COMPLETED;

@Service
@RequiredArgsConstructor
public class DailyScoreService {

    private final TaskRepository taskRepository;
    private final DailyScoreCalculateStrategy scoreCalculate;
    private final DailyScoreRepository dailyScoreRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public DailyScoreDTO getDailyScore(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        BigDecimal completedTask = taskRepository.countByStatusAndUserId(Status.COMPLETED, userId);
        BigDecimal ongoingTask = taskRepository.countByStatusAndUserId(Status.ONGOING, userId);

        BigDecimal score =  scoreCalculate.calculateScore(completedTask, ongoingTask);

        DailyScore dailyScore = dailyScoreRepository.findByUserId(user.getId());
        if (dailyScore == null) {
            dailyScore = DailyScore.builder().currScore(BigDecimal.ZERO).user(user).build();
        }
        else {
            dailyScore.setCurrScore(score);
        }
        dailyScoreRepository.save(dailyScore);

       return modelMapper.map(dailyScore, DailyScoreDTO.class);
    }

    public DailyScore createDailyScore(User user) {
        DailyScore dailyScore = DailyScore.builder()
                .currScore(BigDecimal.ZERO)
                .user(user)
                .build();
        return dailyScoreRepository.save(dailyScore);
    }
}
