package com.example.naeundemo.textcompletion.dto;

import lombok.Getter;

@Getter
public class HopefulAIResponse {
    private final String text;

    public HopefulAIResponse(String text) {
        this.text = text;
    }
}
