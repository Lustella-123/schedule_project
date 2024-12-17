package com.sparta.schedule.Lv7.repository;

import com.sparta.schedule.Lv7.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
