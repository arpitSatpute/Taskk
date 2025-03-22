package com.arpit.taskk.strategies;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DailyScoreCalculateStrategy {

    public BigDecimal calculateScore(BigDecimal completedTask, BigDecimal ongoingTask) {
        if (ongoingTask.compareTo(BigDecimal.ZERO) == 0) {
            if (completedTask.compareTo(BigDecimal.ZERO) == 0) {
                return BigDecimal.ZERO;
            } else {
                return BigDecimal.valueOf(100);
            }
        } else if (completedTask.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal total = completedTask.add(ongoingTask);
        BigDecimal score = completedTask.divide(total, 2, BigDecimal.ROUND_HALF_UP);
        return score.multiply(BigDecimal.valueOf(100));
    }
}