package com.example.studyspot.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TestController.class)
public class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //추후 커스텀 예외 정의 후 구현 예정
    @Test
    void 커스텀_예외_처리_테스트() throws Exception {

    }

    @Test
    @DisplayName("예상치 못한 예외에 대한 처리 테스트")
    void 예상치_못한_예외_처리_테스트() throws Exception {
        mockMvc.perform(get("/test/unexpected-exception"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("예상치 못한 문제가 발생했습니다."));
    }

    @Test
    @DisplayName("예외가 없는 정상 응답 테스트")
    void 정상_응답_테스트() throws Exception {
        mockMvc.perform(get("/test/no-exception"))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }
}
