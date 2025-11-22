package com.example.studyspot.user.util;

import lombok.experimental.UtilityClass;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class RandomNameGenerator {
    public static String generate(){
        LocalTime now = LocalTime.now();

        DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmmssSSS");
        return now.format(format);
    }
}
