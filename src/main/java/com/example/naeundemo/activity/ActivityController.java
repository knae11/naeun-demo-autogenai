package com.example.naeundemo.activity;

import com.example.naeundemo.activity.dto.ActivityPageResponseDto;
import com.example.naeundemo.activity.dto.ActivityRequestDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityController {
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("usage")
    public ResponseEntity<ActivityPageResponseDto> activity(@RequestParam(defaultValue = "10") Integer size,
                                                            @RequestParam(defaultValue = "1") Integer page,
                                                            @ModelAttribute ActivityRequestDto request) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return ResponseEntity.ok(activityService.getHopefulAiUsage(pageable, request));
    }
}
