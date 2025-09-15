package com.example.naeundemo.textcompletion;

import com.example.naeundemo.textcompletion.dto.AIResponse;

public interface AiService {
    AIResponse makePostRequest(String prompt, String action);
}
