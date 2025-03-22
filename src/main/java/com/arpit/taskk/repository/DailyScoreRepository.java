package com.arpit.taskk.repository;

import com.arpit.taskk.entity.DailyScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyScoreRepository extends JpaRepository<DailyScore, Long> {

    DailyScore findByUserId(Long userId);
}
