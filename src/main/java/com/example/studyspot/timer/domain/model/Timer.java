package com.example.studyspot.timer.domain.model;

import com.example.studyspot.review.dto.CreateReviewCommand;
import com.example.studyspot.timer.dto.CreateTimerRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Entity
@Table(name="timers")
public class Timer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="uuser_Id")
    private Long uuserId;
    @Column(name="duration",nullable = false)
    private Long duration;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="color",nullable = false)
    private String color;

    protected Timer(){

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
