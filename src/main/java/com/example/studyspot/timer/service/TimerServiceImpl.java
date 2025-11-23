package com.example.studyspot.timer.service;

import com.example.studyspot.common.exception.StudySpotException;
import com.example.studyspot.timer.domain.model.Log;
import com.example.studyspot.timer.domain.model.Timer;
import com.example.studyspot.timer.dto.*;
import com.example.studyspot.timer.exception.TimerErrorType;
import com.example.studyspot.timer.repository.LogRepository;
import com.example.studyspot.timer.repository.TimerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingLong;

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
    public List<ReadTimerResponse> getAllTimers(){
        List<Timer> timers = timerRepository.findAll();
        List<ReadTimerResponse> found = timers.stream()
                .map(ReadTimerResponse::from)
                .toList();
        return found;
    }

    @Override
    @Transactional
    public ReadLogsOfTimer getDailyStudySummary(List<Long> timerIds, long start, long end) {
        //long->timestamp
        Timestamp startTS = Timestamp.from(Instant.ofEpochMilli(start));
        Timestamp endTS = Timestamp.from(Instant.ofEpochMilli(end));

        List<Timer> timers = timerRepository.findAllById(timerIds);

        List<DailyStudySummaryResponse> summaries = timers.stream()
                .map(timer -> buildSummaryForTimer(timer, startTS, endTS))
                .toList();


        return new ReadLogsOfTimer(summaries);
    }

    private DailyStudySummaryResponse buildSummaryForTimer(Timer timer,
                                                           Timestamp startTS,
                                                           Timestamp endTS) {
        List<Log> logs = logRepository.findByTimerIdAndStartAtBetween(timer.getId(),startTS,endTS);

        Map<LocalDate, Long> totalByDate = logs.stream()
                .collect(groupingBy(
                        log -> log.getStartAt()
                                .toLocalDateTime()
                                .toLocalDate(),
                        summingLong(Log::getFocusDuration)// Timestamp
                ));

        List<DailyStudyOfDayResponse> dailyList = totalByDate.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())  // 날짜 순 정렬
                .map(entry -> {
                    LocalDate date = entry.getKey();
                    long total = entry.getValue();

                    long yyyymmdd = convertToLongDate(date); // yyyyMMdd → long
                    char dayChar = convertDayOfWeek(date.getDayOfWeek()); // '월'

                    return new DailyStudyOfDayResponse(
                            yyyymmdd,
                            dayChar,
                            total
                    );
                })
                .toList();

        //전체 총 duration 계산
        long sumOfAll = dailyList.stream()
                .mapToLong(DailyStudyOfDayResponse::dailyTotalDuration)
                .sum();

        return new DailyStudySummaryResponse(
                timer.getId(),
                timer.getName(),
                sumOfAll,
                dailyList
        );

    }

    private long convertToLongDate(LocalDate date) {
        return date.getYear() * 10000L
                + date.getMonthValue() * 100
                + date.getDayOfMonth();
    }

    private char convertDayOfWeek(DayOfWeek day) {
        return switch (day) {
            case MONDAY    -> '월';
            case TUESDAY   -> '화';
            case WEDNESDAY -> '수';
            case THURSDAY  -> '목';
            case FRIDAY    -> '금';
            case SATURDAY  -> '토';
            case SUNDAY    -> '일';
        };
    }



    @Override
    @Transactional
    public CreateLogResponse createLog(CreateLogCommand createLogCommand){

        // 타이머 조회
        Timer timer = timerRepository.findById(createLogCommand.timerId())
                .orElseThrow(()-> new StudySpotException(TimerErrorType.TIMER_NOT_FOUND));

        // 로그 생성
        Log log = Log.from(
                createLogCommand
        );

        //연관관계 설정
        timer.addLog(log);

        //timer 저장 -> casecade.ALL 로 인해 log도 자동 함꼐 저장
        timerRepository.save(timer);

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
