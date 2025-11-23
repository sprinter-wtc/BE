package com.example.studyspot.timer.service;

import com.example.studyspot.timer.domain.model.Timer;
import com.example.studyspot.timer.dto.CreateTimerRequest;
import com.example.studyspot.timer.dto.CreateTimerResponse;
import com.example.studyspot.timer.repository.TimerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimerServiceImpl implements TimerService{

    private final TimerRepository timerRepository;

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
}
