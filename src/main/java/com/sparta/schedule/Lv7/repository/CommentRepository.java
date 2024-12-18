package com.sparta.schedule.Lv7.repository;

import com.sparta.schedule.Lv7.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByScheduleId(Long scheduleId);
}