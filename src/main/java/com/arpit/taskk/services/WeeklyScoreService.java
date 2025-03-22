package com.arpit.taskk.services;


import com.arpit.taskk.entity.User;
import com.arpit.taskk.entity.WeeklyScore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WeeklyScoreService {


    public WeeklyScore createWeeklyScore(User user) {
        WeeklyScore weeklyScore = WeeklyScore.builder()
                .day1(BigDecimal.ZERO)
                .day2(BigDecimal.ZERO)
                .day3(BigDecimal.ZERO)
                .day4(BigDecimal.ZERO)
                .day5(BigDecimal.ZERO)
                .day6(BigDecimal.ZERO)
                .day7(BigDecimal.ZERO)
                .user(user)
                .build();
        return weeklyScore;
    }
}
