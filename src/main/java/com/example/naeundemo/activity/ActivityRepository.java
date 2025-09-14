package com.example.naeundemo.activity;

import com.example.naeundemo.activity.domain.Activity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findAllByTimeGreaterThanEqualOrderByTimeDesc(LocalDate time);
}
