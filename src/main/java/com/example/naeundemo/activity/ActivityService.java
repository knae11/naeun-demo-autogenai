package com.example.naeundemo.activity;

import com.example.naeundemo.activity.domain.Activity;
import com.example.naeundemo.activity.dto.ActivityRequestDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> getHopefulAiUsage(ActivityRequestDto request) {
        LocalDateTime since = toDateTime(request.getSince());
        return activityRepository.findAllByModelAndTime(request.getModel(), since);
    }

    private LocalDateTime toDateTime(LocalDate date){
        return date.atStartOfDay();
    }

    public void recordActivity(String action, String model) {
        activityRepository.save(new Activity(action, model));
    }
}
