package com.example.naeundemo.activity.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ActivityDto {
    private Long id;
    private String model;
    private String action;
    private LocalDateTime time;
}
