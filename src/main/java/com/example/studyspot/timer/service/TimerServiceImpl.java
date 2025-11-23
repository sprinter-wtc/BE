package com.example.studyspot.timer.service;

import com.example.studyspot.common.exception.StudySpotException;
import com.example.studyspot.timer.domain.model.Log;
import com.example.studyspot.timer.domain.model.Timer;
import com.example.studyspot.timer.dto.*;
import com.example.studyspot.timer.exception.TimerErrorType;
import com.example.studyspot.timer.repository.LogRepository;
import com.example.studyspot.timer.repository.TimerRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
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
    public CreateTimerResponse createTimer(CreateTimerRequest createTimerRequest, Long userId) {
        Timer timer = Timer.from(
                null,
                userId,
                createTimerRequest
        );

        Timer saved = timerRepository.save(timer);

        return new CreateTimerResponse(saved.getId());
    }

    @Override
    @Transactional
    public List<ReadTimerResponse> getAllTimers(Long userId){
        List<Timer> timers = timerRepository.findByUuserId(userId);

        List<ReadTimerResponse> found = timers.stream()
                .map(ReadTimerResponse::from)
                .toList();
        return found;
    }

    @Override
    @Transactional
    public ReadLogsOfTimer getDailyStudySummary(List<Long> timerIds, long start, long end, Long userId,boolean withTotalTime) {
        //long->timestamp
        Timestamp startTS = Timestamp.from(Instant.ofEpochMilli(start));
        Timestamp endTS = Timestamp.from(Instant.ofEpochMilli(end));

        List<Timer> timers = timerRepository.findAllById(timerIds);

        //userId가 맞지 않는 타이머가 하나라도 있는지 검사
        timers.stream()
                .filter(timer -> !timer.getUuserId().equals(userId))
                .findAny()
                .ifPresent(t->{
                    throw new StudySpotException(TimerErrorType.UNAUTHORIZED_TIMER_ACCESS);
                });

        List<DailyStudySummaryResponse> summaries = timers.stream()
                .map(timer -> buildSummaryForTimer(timer, startTS, endTS))
                .toList();

        if (withTotalTime==true){
            return new ReadLogsOfTimer(summaries, getTotalTimeOfDailyStudy(summaries));
        }

        return new ReadLogsOfTimer(summaries, null);
    }

    private TotalStudyOfDayResponse getTotalTimeOfDailyStudy(List<DailyStudySummaryResponse> summaries){
        List<DailyStudyOfDayResponse> allDays = summaries.stream()
                .flatMap(summary -> summary.durationOfDate().stream())
                .toList();

        Map<Long, Long> totalByDate = allDays.stream()
                .collect(groupingBy(
                        DailyStudyOfDayResponse::date,
                        summingLong(DailyStudyOfDayResponse::dailyTotalDuration)
                ));

        List<DailyStudyOfDayResponse> totalDurationOfDate = totalByDate.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // date(long) 기준 정렬
                .map(entry -> {
                    long dateLong = entry.getKey();
                    long dailyTotal = entry.getValue();

                    LocalDate localDate = parseLongDate(dateLong);
                    char dayChar = convertDayOfWeek(localDate.getDayOfWeek());

                    return new DailyStudyOfDayResponse(
                            dateLong,
                            dayChar,
                            dailyTotal
                    );
                })
                .toList();

        long totalDuration = totalDurationOfDate.stream()
                .mapToLong(DailyStudyOfDayResponse::dailyTotalDuration)
                .sum();

        TotalStudyOfDayResponse totalTime = new TotalStudyOfDayResponse(
                totalDuration,
                totalDurationOfDate
        );
        return totalTime;

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

    private LocalDate parseLongDate(long yyyymmdd) {
        int year  = (int) (yyyymmdd / 10000);
        int month = (int) ((yyyymmdd / 100) % 100);
        int day   = (int) (yyyymmdd % 100);
        return LocalDate.of(year, month, day);
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
    public CreateLogResponse createLog(CreateLogCommand createLogCommand, Long userId){

        // 타이머 조회
        Timer timer = timerRepository.findById(createLogCommand.timerId())
                .orElseThrow(()-> new StudySpotException(TimerErrorType.TIMER_NOT_FOUND));


        // 유저 인증
        if (!timer.getUuserId().equals(userId)){
            throw new StudySpotException(TimerErrorType.UNAUTHORIZED_TIMER_ACCESS);
        }

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

    @Override
    @Transactional(readOnly = true)
    public ReadDailyLogsResponse getDailyLogsOfDay(List<Long> timerIds, long day, Long userId) {

        LocalDate targetDate = parseLongDate(day);

        Timestamp startTS = Timestamp.valueOf(targetDate.atStartOfDay());
        Timestamp endTS = Timestamp.valueOf(targetDate.atTime(LocalTime.MAX));

        List<Timer> timers = timerRepository.findAllById(timerIds);

        timers.stream()
                .filter(t->!t.getUuserId().equals(userId))
                .findAny()
                .ifPresent(t->{
                    throw new StudySpotException(TimerErrorType.UNAUTHORIZED_TIMER_ACCESS);
                });

        //각 타이머별 시간 사이에 해당하는 로그 조회
        List<DailyLogsOfTimerResponse> dailyLogs = timers.stream()
                .map(timer -> buildDailyLogs(timer, startTS, endTS))
                .toList();

        return new ReadDailyLogsResponse(dailyLogs);
    }

    private DailyLogsOfTimerResponse buildDailyLogs(Timer timer,
                                                    Timestamp startTs,
                                                    Timestamp endTs){
        List<Log> logs = logRepository.findByTimerIdAndStartAtBetween(
                timer.getId(), startTs, endTs
        );

        List<SingleLogResponse> logDtos = logs.stream()
                .map(log-> new SingleLogResponse(
                        log.getStartAt().getTime(),
                        log.getEndAt().getTime(),
                        log.getFocusDuration()
                )).toList();

        long totalTime = logs.stream()
                .mapToLong(Log::getFocusDuration)
                .sum();

        return new DailyLogsOfTimerResponse(
                timer.getId(),
                timer.getName(),
                totalTime,
                logDtos
        );
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
