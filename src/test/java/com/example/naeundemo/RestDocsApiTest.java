package com.example.naeundemo;

import com.example.naeundemo.activity.ActivityController;
import com.example.naeundemo.activity.ActivityService;
import com.example.naeundemo.textcompletion.TextCompletionController;
import com.example.naeundemo.textcompletion.TextCompletionService;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.web.bind.annotation.RestController;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@WebMvcTest(controllers = {HelloWorldController.class, TextCompletionController.class, ActivityController.class})
@AutoConfigureMockMvc
@ExtendWith({RestDocumentationExtension.class})
@Import(RestDocsApiTest.RestDocsTestConfig.class)
public @interface RestDocsApiTest {

    @Configuration
    @ComponentScan(basePackages = "com.example.naeundemo", includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = RestController.class), useDefaultFilters = false)
    class RestDocsTestConfig {

        @Bean
        public ActivityService activityService() {
            return org.mockito.Mockito.mock(ActivityService.class);
        }

        @Bean
        public TextCompletionService textCompletionService() {
            return org.mockito.Mockito.mock(TextCompletionService.class);
        }
    }
}
