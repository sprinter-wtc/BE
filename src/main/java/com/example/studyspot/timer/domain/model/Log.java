package com.example.studyspot.timer.domain.model;

import com.example.studyspot.timer.dto.CreateLogCommand;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name="logs")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="day")
    private char day; //월, 화,..
    @Column(name="start_at")
    private Timestamp startAt;
    @Column(name="end_at")
    private Timestamp endAt;
    @Column(name="focus_duration")
    private Long focusDuration;

    protected Log(){

    }

    private Log( char day, Timestamp startAt, Timestamp endAt, Long focusDuration) {
        this.day = day;
        this.startAt = startAt;
        this.endAt = endAt;
        this.focusDuration = focusDuration;
    }

    public static Log from(char day, CreateLogCommand command){
        return new Log(
                day,
                Timestamp.from(Instant.ofEpochMilli(command.startAt())),
                Timestamp.from(Instant.ofEpochMilli(command.endAt())),
                command.focusDuration()
        );
    }
}
