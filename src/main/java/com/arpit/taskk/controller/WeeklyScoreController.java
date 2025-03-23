package com.arpit.taskk.controller;

import com.arpit.taskk.dto.WeeklyScoreDTO;
import com.arpit.taskk.entity.enums.Role;
import com.arpit.taskk.services.ScheduledService;
import com.arpit.taskk.services.WeeklyScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/weeklyScore")
@RequiredArgsConstructor
public class WeeklyScoreController {

    private final WeeklyScoreService weeklyScoreService;
    private final ScheduledService scheduledService;

    @GetMapping("/getWeeklyScore/{userId}")
    @PreAuthorize("@userService.isOwner(#userId)")
    public ResponseEntity<WeeklyScoreDTO> getWeeklyScore(@PathVariable Long userId) {
        return ResponseEntity.ok(weeklyScoreService.getWeeklyScore(userId));
    }





}
