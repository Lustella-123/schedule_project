package com.sparta.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    public Schedule() {
    }

    public Schedule(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }

    public void update(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }
}
