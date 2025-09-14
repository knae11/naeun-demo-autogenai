package com.example.naeundemo.activity;

import com.example.naeundemo.activity.domain.Activity;
import com.example.naeundemo.activity.dto.ActivityRequestDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> getHopefulAiUsage(ActivityRequestDto request) {
        return activityRepository.findAllByTimeGreaterThanEqualOrderByTimeDesc(request.getSince());
    }

    public void recordActivity(String action, String model) {
        activityRepository.save(new Activity(action, model));
    }
}
