package com.example.naeundemo.textcompletion;

import com.example.naeundemo.textcompletion.dto.CompletionRequestDto;
import com.example.naeundemo.textcompletion.dto.CompletionResponseDto;
import com.example.naeundemo.textcompletion.dto.HopefulAIResponse;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TextCompletionService {
    private final HopefulAiService hopefulAiService;

    private static final List<String> templates = List.of(
            "\n\n Summarise the above text.",
            "\n\n TL;DR:",
            "\n\n Rewrite the above in a few short sentences."
    );

    public TextCompletionService(HopefulAiService hopefulAiService) {
        this.hopefulAiService = hopefulAiService;
    }

    public CompletionResponseDto summarise(CompletionRequestDto request) {
        List<String> choices = templates.stream()
                .map(line -> request.getInput() + line)
                .map(line -> {
                            HopefulAIResponse result = hopefulAiService.makePostRequest(line, "summarise");
                            return result.getText();
                        }
                ).toList();

        return new CompletionResponseDto(choices);
    }
}
