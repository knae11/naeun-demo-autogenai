package com.example.naeundemo.textcompletion;

import com.example.naeundemo.textcompletion.dto.AIResponse;
import com.example.naeundemo.textcompletion.dto.CompletionRequestDto;
import com.example.naeundemo.textcompletion.dto.CompletionResponseDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TextCompletionService {
    private static final List<String> templates = List.of(
            "\n\n Summarise the above text.",
            "\n\n TL;DR:",
            "\n\n Rewrite the above in a few short sentences."
    );
    private final HopefulAiService hopefulAiService;
    private final RapidAiService rapidAiService;

    public TextCompletionService(HopefulAiService hopefulAiService, RapidAiService rapidAiService) {
        this.hopefulAiService = hopefulAiService;
        this.rapidAiService = rapidAiService;
    }

    public CompletionResponseDto summarise(CompletionRequestDto request) {
        List<String> choices = templates.stream()
                .map(line -> request.getInput() + line)
                .map(line -> {
                            AIResponse result = makePostRequest(request.getModel(), line);
                            return result.text();
                        }
                ).toList();

        return new CompletionResponseDto(choices);
    }

    private AIResponse makePostRequest(String model, String line) {
        String action = "summarise";
        return switch (model) {
            case "hopeful-ai" -> hopefulAiService.makePostRequest(line, action);
            case "rapid-ai" -> rapidAiService.makePostRequest(line, action);
            default -> throw new IllegalArgumentException("Invalid model");
        };
    }
}
