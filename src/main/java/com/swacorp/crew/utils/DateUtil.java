package com.swacorp.crew.utils;

import com.swacorp.crew.pages.common.CommonFormats;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import static com.swacorp.crew.pages.common.CommonFormats.MONTH_DAY_YEAR;

public class DateUtil {
    private final LocalDate date = LocalDate.now();
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("ddMMMyyyy");

    public String getCurrentDate() throws Exception {
        return date.format(dateFormat);
    }

    public String getCurrentDate(String format) throws Exception {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String strDate = sdf.format(cal.getTime());
        System.out.println("Current date in String Format: " + strDate);

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

    public long getTimeDiff(String tripStartDate, String searchStartDate, String datePattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        long date1 = sdf.parse(tripStartDate).getTime();
        long date2 = sdf.parse(searchStartDate).getTime();

        return (date1 - date2)/1000;
    }

    public boolean isBefore(String tripStartDate, String searchStartDate, String datePattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        Date date1 = sdf.parse(tripStartDate);
        Date date2 = sdf.parse(searchStartDate);
        if (date1.before(date2)){
            return true;
        }else{
            return false;
        }
    }
}
