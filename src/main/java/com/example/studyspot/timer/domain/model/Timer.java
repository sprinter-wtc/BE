package com.example.studyspot.timer.domain.model;

import com.example.studyspot.review.dto.CreateReviewCommand;
import com.example.studyspot.timer.dto.CreateTimerRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Entity
@Table(name="timers")
public class Timer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="uuser_id")
    private Long uuserId;
    @Column(name="duration",nullable = false)
    private Long duration;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="color",nullable = false)
    private String color;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "timer_id")
    private List<Log> logs = new ArrayList<>();

    public void addLog(Log log){
        logs.add(log);
    }

    public List<Log> getLogs(){
        return logs;
    }

    protected Timer(){

    }

    private Timer(Long id, Long uuserId, Long duration, String name, String color) {
        this.id = id;
        this.uuserId = uuserId;
        this.duration = duration;
        this.name = name;
        this.color = color;
    }

    public static Timer from(Long id, Long uuserId, CreateTimerRequest request){
        return new Timer(
                id,
                uuserId,
                request.duration(),
                request.name(),
                request.color()
        );
    }


}
