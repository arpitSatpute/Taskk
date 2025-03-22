package com.arpit.taskk.controller;

import com.arpit.taskk.services.DailyScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/dailyScore")
@RequiredArgsConstructor
public class DailyScoreController {

    private final DailyScoreService dailyScoreService;


    @GetMapping(path = "/getDailyScore/{userId}")
    public ResponseEntity<BigDecimal> getDailyScore(@PathVariable Long userId) {
        return ResponseEntity.ok(dailyScoreService.getDailyScore(userId));
    }

}
