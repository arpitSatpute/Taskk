package com.arpit.taskk.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeeklyScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private BigDecimal day1;
    private BigDecimal day2;
    private BigDecimal day3;
    private BigDecimal day4;
    private BigDecimal day5;
    private BigDecimal day6;
    private BigDecimal day7;

}
