package com.arpit.taskk.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DailyScoreDTO {

    private Long id;
    private BigDecimal currScore;
}
