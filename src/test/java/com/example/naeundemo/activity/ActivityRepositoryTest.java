package com.example.naeundemo.activity;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.naeundemo.activity.domain.Activity;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
class ActivityRepositoryTest {
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private TestEntityManager entityManager;

    @AfterEach
    void tearDown() {
        entityManager.clear();
    }

    @DisplayName("findAll target model list and desc by id")
    @Test
    void findAllByModelAndTime() {
        // given
        LocalDateTime now = LocalDateTime.now();
        Activity older = new Activity("summarise", "hopeful-ai", now.minusSeconds(2));
        Activity newer = new Activity("summarise", "hopeful-ai", now.minusSeconds(1));
        Activity latest = new Activity("summarise", "hopeful-ai", now);
        Activity otherModel = new Activity("summarise", "rapid-ai", now);
        activityRepository.saveAll(List.of(older, newer, latest, otherModel));
        entityManager.flush();

        // when
        Page<Activity> result =
                activityRepository.findAllByModelAndTime(
                        "hopeful-ai",
                        now.minusDays(1),
                        PageRequest.of(0, 10)
                );

        // then
        assertThat(result.getContent()).hasSize(3);
        assertThat(result.getContent().get(0).getId()).isGreaterThan(result.getContent().get(1).getId());
        assertThat(result).extracting("model").containsOnly("hopeful-ai"); // 모델 필터 확인
    }

    @DisplayName("findAll pagination")
    @Test
    void findAllByModelAndTimePagination() {
        // given
        LocalDateTime now = LocalDateTime.now();

        Activity activity1 = new Activity("summarise", "hopeful-ai", now);
        Activity activity2 = new Activity("summarise", "hopeful-ai", now);
        Activity activity3 = new Activity("summarise", "hopeful-ai", now);
        Activity activity4 = new Activity("summarise", "hopeful-ai", now);
        Activity activity5 = new Activity("summarise", "hopeful-ai", now);
        Activity otherModel = new Activity("summarise", "rapid-ai", now);
        activityRepository.saveAll(List.of(activity1, activity2, activity3, activity4, activity5, otherModel));
        entityManager.flush();

        // when
        Page<Activity> result =
                activityRepository.findAllByModelAndTime(
                        "hopeful-ai",
                        now.minusDays(1),
                        PageRequest.of(1, 3)
                );

        // then
        assertThat(result.getContent()).hasSize(2);
        assertThat(result).extracting("model").containsOnly("hopeful-ai"); // 모델 필터 확인
    }
}
