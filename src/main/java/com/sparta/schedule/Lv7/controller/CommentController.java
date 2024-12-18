package com.sparta.schedule.Lv7.controller;

import com.sparta.schedule.Lv7.dto.CommentRequestDto;
import com.sparta.schedule.Lv7.dto.CommentResponseDto;
import com.sparta.schedule.Lv7.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import java.util.List;

@RestController
@RequestMapping("/schedule/{scheduleId}/comment")
@RequiredArgsConstructor
public class CommentController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long scheduleId, @RequestBody CommentRequestDto requestDto) {
        return new ResponseEntity<>(scheduleService.creatComment(scheduleId, requestDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getComment(@PathVariable Long scheduleId) {
        return new ResponseEntity<>(scheduleService.getComment(scheduleId), HttpStatus.OK);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long scheduleId,
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto requestDto) {
        return new ResponseEntity<>(scheduleService.updateComment(scheduleId, commentId, requestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long scheduleId,
            @PathVariable Long commentId) {
        scheduleService.deleteComment(scheduleId, commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}