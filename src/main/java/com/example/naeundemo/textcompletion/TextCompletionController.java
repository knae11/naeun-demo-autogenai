package com.example.naeundemo.textcompletion;

import com.example.naeundemo.textcompletion.dto.CompletionRequestDto;
import com.example.naeundemo.textcompletion.dto.CompletionResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextCompletionController {
    private final TextCompletionService textCompletionService;

    public TextCompletionController(TextCompletionService textCompletionService) {
        this.textCompletionService = textCompletionService;
    }

    @PostMapping("/summarise")
    public ResponseEntity<CompletionResponseDto> summarise(@RequestBody CompletionRequestDto completionRequestDto) {
        return ResponseEntity.ok(textCompletionService.summarise(completionRequestDto));
    }

}
