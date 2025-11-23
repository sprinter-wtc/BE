package com.example.studyspot.timer.controller;

import com.example.studyspot.common.api.ResponseEntityGenerator;
import com.example.studyspot.common.api.SuccessBody;
import com.example.studyspot.timer.dto.*;
import com.example.studyspot.timer.service.TimerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timers")
@RequiredArgsConstructor
public class TimerController {
    private final TimerService timerService;

    @PostMapping
    public ResponseEntity<SuccessBody<CreateTimerResponse>> createTimer(
            @Valid @RequestBody CreateTimerRequest body
    ){
        CreateTimerResponse created = timerService.createTimer(body);
        return ResponseEntityGenerator.success(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<SuccessBody<ReadTimersResponse>> getAllTimers(){
        List<ReadTimerResponse> allTimers = timerService.getAllTimers();
        return ResponseEntityGenerator.success(new ReadTimersResponse(allTimers),HttpStatus.OK);
    }

    @PostMapping("/{timerId}/logs")
    public ResponseEntity<SuccessBody<CreateLogResponse>> saveLog(
            @PathVariable Long timerId,
            @Valid @RequestBody CreateLogRequest body
            ){
        CreateLogResponse created = timerService.createLog(
                new CreateLogCommand(timerId, body.startAt(), body.endAt(), body.focusDuration())
        );

        return ResponseEntityGenerator.success(created, HttpStatus.CREATED);
    }

}
