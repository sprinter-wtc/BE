package com.example.studyspot.timer.controller;

import com.example.studyspot.timer.dto.CreateTimerRequest;
import com.example.studyspot.timer.dto.CreateTimerResponse;
import com.example.studyspot.timer.service.TimerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/timers")
@RequiredArgsConstructor
public class TimerController {
    private final TimerService timerService;

    @PostMapping
    public ResponseEntity<CreateTimerResponse> createTimer(
            @Valid @RequestBody CreateTimerRequest body
    ){
        CreateTimerResponse created = timerService.createTimer(body);
        return ResponseEntity.status(201).body(created);
    }

}
