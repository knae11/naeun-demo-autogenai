package com.example.naeundemo.textcompletion.dto;

import lombok.Getter;

@Getter
public class RapidAiResponse {
    private final String answer;

    public RapidAiResponse(String answer) {
        this.answer = answer;
    }
}
