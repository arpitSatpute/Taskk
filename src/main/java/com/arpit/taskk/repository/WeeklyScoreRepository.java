package com.arpit.taskk.repository;

import com.arpit.taskk.entity.WeeklyScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeeklyScoreRepository extends JpaRepository<WeeklyScore, Long> {

    WeeklyScore findByUserId(Long userId);

}
