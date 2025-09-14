package com.example.naeundemo.activity;

import com.example.naeundemo.activity.domain.Activity;
import com.example.naeundemo.activity.dto.ActivityRequestDto;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityController {
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("usage/hopeful-ai")
    public ResponseEntity<List<Activity>> activity(@ModelAttribute ActivityRequestDto request) {
        return ResponseEntity.ok(activityService.getHopefulAiUsage(request));
    }
}
