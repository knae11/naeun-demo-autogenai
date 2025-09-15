package com.example.naeundemo.textcompletion;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.naeundemo.RestDocsApiTest;
import com.example.naeundemo.textcompletion.dto.CompletionRequestDto;
import com.example.naeundemo.textcompletion.dto.CompletionResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RestDocsApiTest
class TextCompletionControllerTest {
    @Autowired
    private TextCompletionService textCompletionService;
    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;
    private RestDocumentationResultHandler document;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        document = document(
                "{class-name}/{method-name}",
                preprocessResponse(prettyPrint())
        );
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(document)
                .build();
    }

    @Test
    void summarise() throws Exception {
        CompletionRequestDto request = new CompletionRequestDto("input sample");
        CompletionResponseDto response = new CompletionResponseDto(List.of("sample answer1", "sample answer2"));
        when(textCompletionService.summarise(any(CompletionRequestDto.class))).thenReturn(response);

        mockMvc.perform(post("/summarise")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document.document(
                        requestFields(
                                fieldWithPath("input").description("input text")
                        ),
                        responseFields(
                                fieldWithPath("choices").description("List of generated answers"),
                                fieldWithPath("choices[]").description("Single answer inside the list")
                        )
                ));
    }
}
