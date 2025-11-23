package com.example.studyspot.user.util;

import lombok.experimental.UtilityClass;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class RandomNameGenerator {
    private static final String PREFIX = "열공 ";
    public static String generate(){
        LocalTime now = LocalTime.now();

        DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmmssSSS");
        return PREFIX + now.format(format);
    }
}
