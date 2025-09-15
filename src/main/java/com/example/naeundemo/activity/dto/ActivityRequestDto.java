package com.example.naeundemo.activity.dto;


import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequestDto {
    @NotNull
    private String model;
    @NotNull
    private LocalDate since;
}
