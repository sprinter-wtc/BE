package com.example.studyspot.cafe.domain.enums.tags;

public enum  NoiseLevel {
    LOW("조용함"),
    MEDIUM("적당한 소음"),
    HIGH("시끌벅적");

    private final String value;

    NoiseLevel(String value) {
        this.value = value;
    }
}
