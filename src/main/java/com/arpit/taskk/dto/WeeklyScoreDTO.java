package com.arpit.taskk.dto;


import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeeklyScoreDTO {

    private Long id;
    private BigDecimal day1;
    private BigDecimal day2;
    private BigDecimal day3;
    private BigDecimal day4;
    private BigDecimal day5;
    private BigDecimal day6;
    private BigDecimal day7;

}
