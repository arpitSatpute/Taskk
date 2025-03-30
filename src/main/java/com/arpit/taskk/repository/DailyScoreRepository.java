package com.arpit.taskk.repository;

import com.arpit.taskk.entity.DailyScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface DailyScoreRepository extends JpaRepository<DailyScore, Long> {

    DailyScore findByUserId(Long userId);

    BigDecimal findCurrScoreByUserId(Long userId);
}
