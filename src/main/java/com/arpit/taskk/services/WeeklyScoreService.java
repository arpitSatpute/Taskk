package com.arpit.taskk.services;


import com.arpit.taskk.dto.WeeklyScoreDTO;
import com.arpit.taskk.entity.User;
import com.arpit.taskk.entity.WeeklyScore;
import com.arpit.taskk.exceptions.ResourceNotFoundException;
import com.arpit.taskk.repository.UserRepository;
import com.arpit.taskk.repository.WeeklyScoreRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeeklyScoreService {

    private final WeeklyScoreRepository weeklyScoreRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;


    public WeeklyScoreDTO getWeeklyScore() {
        Long userId = userService.getUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        WeeklyScore weeklyScore = weeklyScoreRepository.findByUserId(user.getId());
        if (weeklyScore == null) {
            return null;
        }
        return modelMapper.map(weeklyScore, WeeklyScoreDTO.class);
    }


    public WeeklyScore createWeeklyScore(User savedUser) {
        WeeklyScore weeklyScore = WeeklyScore.builder()
                .user(savedUser)
                .day1(BigDecimal.ZERO)
                .day2(BigDecimal.ZERO)
                .day3(BigDecimal.ZERO)
                .day4(BigDecimal.ZERO)
                .day5(BigDecimal.ZERO)
                .day6(BigDecimal.ZERO)
                .day7(BigDecimal.ZERO)
                .build();
        return weeklyScoreRepository.save(weeklyScore);
    }






}