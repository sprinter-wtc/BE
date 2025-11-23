package com.example.studyspot.timer.controller;

import com.example.studyspot.common.annotation.UserId;
import com.example.studyspot.common.api.ResponseEntityGenerator;
import com.example.studyspot.common.api.SuccessBody;
import com.example.studyspot.timer.dto.*;
import com.example.studyspot.timer.service.TimerService;
import com.sun.net.httpserver.Authenticator;
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
            @Valid @RequestBody CreateTimerRequest body,
            @UserId Long userId
    ){
        CreateTimerResponse created = timerService.createTimer(body,userId);
        return ResponseEntityGenerator.success(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<SuccessBody<ReadTimersResponse>> getAllTimers(
            @UserId Long userId
    ){
        List<ReadTimerResponse> allTimers = timerService.getAllTimers(userId);
        return ResponseEntityGenerator.success(new ReadTimersResponse(allTimers),HttpStatus.OK);
    }

    @PostMapping("/{timerId}/logs")
    public ResponseEntity<SuccessBody<CreateLogResponse>> saveLog(
            @PathVariable Long timerId,
            @Valid @RequestBody CreateLogRequest body,
            @UserId Long userId
            ){
        CreateLogResponse created = timerService.createLog(
                new CreateLogCommand(timerId, body.startAt(), body.endAt(), body.focusDuration()),
                userId
        );

        return ResponseEntityGenerator.success(created, HttpStatus.CREATED);
    }

    @GetMapping("/logs")
    public ResponseEntity<SuccessBody<ReadLogsOfTimer>> getDailyStudySummaryOfTimer(
            @RequestParam List<Long> timerId,
            @RequestParam long start,
            @RequestParam long end,
            @RequestParam(defaultValue = "false") boolean withTotalTime,
            @UserId Long userId
    ){
        ReadLogsOfTimer dailyStudySummary = timerService.getDailyStudySummary(timerId, start, end, userId, withTotalTime);
        return ResponseEntityGenerator.success(dailyStudySummary, HttpStatus.OK);
    }

    @GetMapping("/details/logs")
    public ResponseEntity<SuccessBody<ReadDailyLogsResponse>> getDailyLogs(
            @RequestParam List<Long> timerId,
            @RequestParam long day,
            @UserId Long userId
    ){
        ReadDailyLogsResponse response = timerService.getDailyLogsOfDay(timerId, day, userId);
        return ResponseEntityGenerator.success(response, HttpStatus.OK);
    }

}
