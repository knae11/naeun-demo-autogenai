package com.example.naeundemo.activity;

import com.example.naeundemo.activity.domain.Activity;
import com.example.naeundemo.activity.dto.ActivityDto;
import com.example.naeundemo.activity.dto.ActivityPageResponseDto;
import com.example.naeundemo.activity.dto.ActivityRequestDto;
import com.example.naeundemo.activity.dto.PaginationResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Transactional(readOnly = true)
    public ActivityPageResponseDto getHopefulAiUsage(Pageable pageable, ActivityRequestDto request) {
        LocalDateTime since = toDateTime(request.getSince());
        Page<Activity> activities = activityRepository.findAllByModelAndTime(request.getModel(), since, pageable);
        List<ActivityDto> activityDtos = activities.stream()
                .map(it -> new ActivityDto(it.getId(), it.getModel(), it.getAction(), it.getTime()))
                .toList();
        PaginationResponse pagination = new PaginationResponse(activities.getPageable().getPageNumber() + 1,
                activities.getPageable().getPageSize(), activities.getTotalPages(), activities.getTotalElements(),
                activities.isLast());

        return new ActivityPageResponseDto(activityDtos, pagination);
    }

    private LocalDateTime toDateTime(LocalDate date) {
        return date.atStartOfDay();
    }

    @Transactional
    public void recordActivity(String action, String model) {
        activityRepository.save(new Activity(action, model));
    }
}
