package com.swacorp.crew.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static com.swacorp.crew.pages.common.CommonFormats.MONTH_DAY_YEAR;

public class DateUtil {
    private final LocalDate date = LocalDate.now();
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(MONTH_DAY_YEAR);

    public String getCurrentDate() throws Exception {
        return date.format(dateFormat);
    }

    public String getCurrentYear() throws Exception {
        return Integer.toString(date.getYear());
    }

    public String getPastDate(int amount){
        /*Instant now = Instant.now();
        Instant pastDate = now.minus(1, ChronoUnit.DAYS);*/
        System.out.println("date.minusDays(amount).format(dateFormat);" +date.minusDays(amount).format(dateFormat));
        return date.minusDays(amount).format(dateFormat);
        //return pastDate.toString();
    }
}
