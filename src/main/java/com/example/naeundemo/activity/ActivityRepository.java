package com.example.naeundemo.activity;

import com.example.naeundemo.activity.domain.Activity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    @Query("select a from Activity a where a.model = :model and a.time >= :time order by id desc")
    List<Activity> findAllByModelAndTime(@Param("model") String model, @Param("time") LocalDateTime time);
}
