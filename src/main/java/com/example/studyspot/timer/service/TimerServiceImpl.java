package com.example.studyspot.timer.service;

import com.example.studyspot.common.exception.StudySpotException;
import com.example.studyspot.timer.domain.model.Log;
import com.example.studyspot.timer.domain.model.Timer;
import com.example.studyspot.timer.dto.CreateLogCommand;
import com.example.studyspot.timer.dto.CreateLogResponse;
import com.example.studyspot.timer.dto.CreateTimerRequest;
import com.example.studyspot.timer.dto.CreateTimerResponse;
import com.example.studyspot.timer.exception.TimerErrorType;
import com.example.studyspot.timer.repository.LogRepository;
import com.example.studyspot.timer.repository.TimerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TimerServiceImpl implements TimerService{

    private final TimerRepository timerRepository;
    private final LogRepository logRepository;

    @Override
    @Transactional
    public CreateTimerResponse createTimer(CreateTimerRequest createTimerRequest) {
        Timer timer = Timer.from(
                null,
                null,
                createTimerRequest
        );

        Timer saved = timerRepository.save(timer);

        return new CreateTimerResponse(saved.getId());
    }
    @Override
    @Transactional
    public CreateLogResponse createLog(CreateLogCommand createLogCommand){

        // 타이머 조회
        Timer timer = timerRepository.findById(createLogCommand.timerId())
                .orElseThrow(()-> new StudySpotException(TimerErrorType.TIMER_NOT_FOUND));

        // 로그 생성
        Log log = Log.from(
                getDayOfWeek(),
                createLogCommand
        );

        //연관관계 설정
        timer.addLog(log);

        //timer 저장 -> casecade.ALL 로 인해 log도 자동 함꼐 저장
        timerRepository.saveAndFlush(timer);

        //
        Log persisted = timer.getLogs().get(timer.getLogs().size() - 1);

        return new CreateLogResponse(persisted.getId());

    }

    private char getDayOfWeek(){
        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();

        char DayInKoeran = switch(dayOfWeek) {
            case MONDAY -> '월';
            case TUESDAY -> '화';
            case WEDNESDAY -> '수';
            case THURSDAY -> '목';
            case FRIDAY -> '금';
            case SATURDAY -> '토';
            case SUNDAY -> '일';
        };

        return DayInKoeran;
    }

}
