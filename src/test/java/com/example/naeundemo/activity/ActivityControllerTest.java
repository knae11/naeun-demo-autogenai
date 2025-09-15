package com.example.naeundemo.activity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.naeundemo.RestDocsApiTest;
import com.example.naeundemo.activity.domain.Activity;
import com.example.naeundemo.activity.dto.ActivityDto;
import com.example.naeundemo.activity.dto.ActivityPageResponseDto;
import com.example.naeundemo.activity.dto.PaginationResponse;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RestDocsApiTest
class ActivityControllerTest {

    @Autowired
    private ActivityService activityService;

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
    void activity() throws Exception {
        // given
        Activity activity1 = new Activity(1L, "hopeful-ai", "summarise", LocalDateTime.now());
        Activity activity2 = new Activity(2L, "hopeful-ai", "summarise", LocalDateTime.now());
        List<Activity> list = List.of(activity1, activity2);
        Page<Activity> activities = new PageImpl<>(list, PageRequest.of(0, 5), 2);
        List<ActivityDto> activityDtos = activities.stream()
                .map(it -> new ActivityDto(it.getId(), it.getModel(), it.getAction(), it.getTime()))
                .toList();
        PaginationResponse pagination = new PaginationResponse(activities.getPageable().getPageNumber(),
                activities.getPageable().getPageSize(), activities.getTotalPages(), activities.getTotalElements(),
                activities.isLast());

        when(activityService.getHopefulAiUsage(any(), any())).thenReturn(
                new ActivityPageResponseDto(activityDtos, pagination));

        // when & then
        mockMvc.perform(get("/usage")
                        .param("model", "hopeful-ai")
                        .param("since", "2025-09-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document.document(
                        responseFields(
                                fieldWithPath("content[].id").description("Activity ID"),
                                fieldWithPath("content[].model").description("Model used"),
                                fieldWithPath("content[].action").description("Performed action"),
                                fieldWithPath("content[].time").description("Time of the activity"),
                                fieldWithPath("pagination.page").description("page"),
                                fieldWithPath("pagination.pageSize").description("page size"),
                                fieldWithPath("pagination.totalPages").description("total pages"),
                                fieldWithPath("pagination.totalElements").description("total elements size"),
                                fieldWithPath("pagination.last").description("boolean value - last pages")
                        )
                ));
    }

}
