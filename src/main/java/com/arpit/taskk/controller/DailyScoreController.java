package com.arpit.taskk.controller;

import com.arpit.taskk.dto.DailyScoreDTO;
import com.arpit.taskk.services.DailyScoreService;
import com.arpit.taskk.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/dailyScore")
@RequiredArgsConstructor
public class DailyScoreController {

    private final UserService userService;
    private final DailyScoreService dailyScoreService;


    @GetMapping(path = "/getDailyScore")
    public ResponseEntity<DailyScoreDTO> getDailyScore() {

        return ResponseEntity.ok(dailyScoreService.getDailyScore());
    }

}
