package com.example.naeundemo.activity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaginationResponse {
    private int page;
    private int pageSize;
    private long totalPages;
    private long totalElements;
    private boolean last;
}
