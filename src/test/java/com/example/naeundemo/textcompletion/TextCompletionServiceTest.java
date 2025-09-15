package com.example.naeundemo.textcompletion;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.naeundemo.textcompletion.dto.AIResponse;
import com.example.naeundemo.textcompletion.dto.CompletionRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TextCompletionServiceTest {

    private HopefulAiService hopefulAiService;

    private RapidAiService rapidAiService;

    private TextCompletionService sut;

    @BeforeEach
    void setUp() {
        hopefulAiService = mock(HopefulAiService.class);
        rapidAiService = mock(RapidAiService.class);
        sut = new TextCompletionService(hopefulAiService, rapidAiService);
    }

    @DisplayName("summarise by model type-rapid")
    @Test
    void summariseRapid() {
        CompletionRequestDto rapidRequest = new CompletionRequestDto("rapid-ai", "sample input");

        AIResponse response = new AIResponse("answer");
        when(rapidAiService.makePostRequest(anyString(), anyString())).thenReturn(response);
        when(hopefulAiService.makePostRequest(anyString(), anyString())).thenReturn(response);

        sut.summarise(rapidRequest);
        verify(rapidAiService, times(3)).makePostRequest(anyString(), anyString());
        verify(hopefulAiService, never()).makePostRequest(anyString(), anyString());
    }

    @DisplayName("summarise by model type - hopeful")
    @Test
    void summariseHopeful() {
        CompletionRequestDto hopefulRequest = new CompletionRequestDto("hopeful-ai", "sample input");

        AIResponse response = new AIResponse("answer");
        when(rapidAiService.makePostRequest(anyString(), anyString())).thenReturn(response);
        when(hopefulAiService.makePostRequest(anyString(), anyString())).thenReturn(response);

        sut.summarise(hopefulRequest);
        verify(rapidAiService, never()).makePostRequest(anyString(), anyString());
        verify(hopefulAiService, times(3)).makePostRequest(anyString(), anyString());
    }
}
