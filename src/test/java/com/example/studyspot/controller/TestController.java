package com.example.studyspot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    //추후 커스텀 예외 정의 후 구현 예정
    @GetMapping("/test/custom-exception")
    public void customException() throws Exception {

    }

    @GetMapping("/test/unexpected-exception")
    public void unexpectedException() throws Exception {
        throw new Exception("예상치 못한 예외");
    }

    @GetMapping("/test/no-exception")
    public ResponseEntity<String> noException() throws Exception {
        return ResponseEntity.ok("OK");
    }
}
