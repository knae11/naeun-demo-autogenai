package com.example.naeundemo.textcompletion;

import com.example.naeundemo.activity.ActivityService;
import com.example.naeundemo.textcompletion.dto.AIResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class HopefulAiService implements AiService {
    private static final int MIN_RESPONSE_TIME = 50;
    private static final int MAX_RESPONSE_TIME = 100;
    private static final int SUCCESS_RATE = 1;

    private final ActivityService activityService;

    public HopefulAiService(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Transactional
    public AIResponse makePostRequest(String prompt, String action) {
        activityService.recordActivity(action, "hopeful-ai");
        String text = generateText(prompt);
        return new AIResponse(text);
    }

    private String generateText(String prompt) {
        double responseTime = MIN_RESPONSE_TIME + Math.random() * (MAX_RESPONSE_TIME - MIN_RESPONSE_TIME);

        try {
            if (Math.random() < SUCCESS_RATE) {
                Thread.sleep((long) responseTime);
                return "Generated dummy paragraph for input: " + prompt;
            } else {
                throw new RuntimeException("Text generation failed");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
