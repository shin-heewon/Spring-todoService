package com.ssg.springex.controller.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

//Failed to convert value of type 'java.lang.String' to required type 'java.time.LocalDate'; 
// 위 에러를 해결해주는 기능을 가짐.(날짜양식으로 전달된 파라미터를 실제 날짜 객체 타입으로 변환
public class LocalDateFormatter  implements Formatter<LocalDate> {

    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return DateTimeFormatter.ofPattern("yyyy-MM--dd").format(object);//패턴을 맞춰준 후 객체로 생성해 줌
    }
}
