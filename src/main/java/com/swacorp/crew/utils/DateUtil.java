package com.swacorp.crew.utils;

import com.swacorp.crew.pages.common.CommonFormats;

import java.io.FileWriter;
import java.io.IOException;
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

    public String changeToSasiDateFormat(String dt) throws ParseException{
        String finalDate = new String();
        switch(dt.substring(2,5)) {
            case "Feb" :
                finalDate = dt.substring(5,9)+"-"+"02"+"-"+dt.substring(0,2);
                // Statements
                break; // optional
            case "Mar" :
                finalDate = dt.substring(5,9)+"-"+"03"+"-"+dt.substring(0,2);
                // Statements
                break; // optional

            case "Apr" :
                // Statements
                finalDate = dt.substring(5,9)+"-"+"04"+"-"+dt.substring(0,2);
                break; // optional
            case "May" :
                // Statements
                finalDate = dt.substring(5,9)+"-"+"05"+"-"+dt.substring(0,2);
                break; // optional

            // You can have any number of case statements.
            default : // Optional
                // Statements
        }
        return finalDate;
    }
    public String getCurrentDate(String dateFormat) {
        String currentDate;
        currentDate =  new SimpleDateFormat(dateFormat).format(new Date());
        return currentDate;
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

    public void changeLocalDate(int months) throws IOException {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, months);
        SimpleDateFormat s = new SimpleDateFormat("MM-dd-yy");
        String strExpectedDate = s.format(new Date(cal.getTimeInMillis()));
        FileWriter writer = new FileWriter("C:\\Utility\\ChangeDate.vbs");
        writer.write("CreateObject(\"Shell.Application\").ShellExecute \"cmd.exe\", \"/c date "+ strExpectedDate + "\", , \"runas\", 1");
        writer.close();
        Runtime.getRuntime().exec("C:\\Windows\\System32\\schtasks.exe /run /tn RunVBS");
        System.out.println("New Date: " + date);
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
