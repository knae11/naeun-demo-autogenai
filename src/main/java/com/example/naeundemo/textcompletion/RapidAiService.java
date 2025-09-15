package com.example.naeundemo.textcompletion;

import com.example.naeundemo.activity.ActivityService;
import com.example.naeundemo.textcompletion.dto.AIResponse;
import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class RapidAiService implements AiService {

    private final ActivityService activityService;

    private static final int MIN_RESPONSE_TIME = 20;
    private static final int MAX_RESPONSE_TIME = 10;
    private static final int SUCCESS_RATE = 1;

    public RapidAiService(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Transactional
    public AIResponse makePostRequest(String prompt, String action) {
        activityService.recordActivity(action, "rapid-ai");
        String text = generateText(prompt);
        return new AIResponse(text);
    }

    private String generateText(String input) {
        double responseTime = MIN_RESPONSE_TIME + Math.random() * (MAX_RESPONSE_TIME - MIN_RESPONSE_TIME);

        try {
            if (Math.random() < SUCCESS_RATE) {
                Thread.sleep((long) responseTime);

                if (Objects.isNull(input) || input.isBlank()) {
                    return "";
                }
                String[] words = input.split(" ");
                int limit = Math.min(words.length, 20);
                return String.join(" ", Arrays.copyOfRange(words, 0, limit));
            } else {
                throw new RuntimeException("Text generation failed");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
