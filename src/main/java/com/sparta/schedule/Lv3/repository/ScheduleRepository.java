package com.sparta.schedule.Lv3.repository;

import com.sparta.schedule.Lv3.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
