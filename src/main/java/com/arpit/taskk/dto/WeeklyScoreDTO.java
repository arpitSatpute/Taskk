package com.arpit.taskk.dto;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeeklyScoreDTO {

    private Long id;
    private int day1;
    private int day2;
    private int day3;
    private int day4;
    private int day5;
    private int day6;
    private int day7;

}
