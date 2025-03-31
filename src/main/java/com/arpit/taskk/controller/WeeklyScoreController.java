package com.arpit.taskk.controller;

import com.arpit.taskk.dto.WeeklyScoreDTO;

import com.arpit.taskk.services.WeeklyScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/weeklyScore")
@RequiredArgsConstructor
public class WeeklyScoreController {

    private final WeeklyScoreService weeklyScoreService;

    @GetMapping("/getWeeklyScore")
    public ResponseEntity<WeeklyScoreDTO> getWeeklyScore() {
        return ResponseEntity.ok(weeklyScoreService.getWeeklyScore());
    }





}
