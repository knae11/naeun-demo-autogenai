package com.example.naeundemo.activity.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ActivityPageResponseDto {
    private List<ActivityDto> content;
    private PaginationResponse pagination;
}
