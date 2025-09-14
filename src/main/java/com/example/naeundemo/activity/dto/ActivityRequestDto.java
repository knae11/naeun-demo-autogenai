package com.example.naeundemo.activity.dto;


import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequestDto {
    private LocalDate since;
}
