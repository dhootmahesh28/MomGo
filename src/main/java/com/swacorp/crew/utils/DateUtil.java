package com.swacorp.crew.utils;

import com.swacorp.crew.pages.constants.CommonFormats;
import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import static com.swacorp.crew.pages.constants.CommonConstants.DATE_CHANGE_SCH_TASK;
import static com.swacorp.crew.pages.constants.CommonConstants.DATE_CHANGE_UTILITY;

public class DateUtil {
    public static final Logger LOGGER = Logger.getLogger(DateUtil.class);
    private final LocalDate date = LocalDate.now();
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("ddMMMyyyy");


    public String getCurrentDate() {
        return date.format(dateFormat);
    }

    public String changeToSasiDateFormat(String dt) {
        String finalDate = null;
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

    public String getCurrentYear() {
        return Integer.toString(date.getYear());
    }

    public String getCurrentDay() {
        return Integer.toString(date.getDayOfMonth());
    }

    public String getDayFromDate(String date) {
        return Integer.toString(LocalDate.parse(date, dateFormat).getDayOfMonth());
    }

    public Boolean changeLocalDate(Integer months) {
        String dateFrom = getCurrentDate(CommonFormats.DAY_MONTHNAME_YEAR);
        Boolean dateChange = false;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, months);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CommonFormats.MM_DD_YY);
        String strExpectedDate = simpleDateFormat.format(new Date(cal.getTimeInMillis()));

        try (FileWriter writer = new FileWriter(DATE_CHANGE_UTILITY)) {
            writer.write("CreateObject(\"Shell.Application\").ShellExecute \"cmd.exe\", \"/c date " + strExpectedDate + "\", , \"runas\", 1");
            Runtime.getRuntime().exec("C:\\Windows\\System32\\schtasks.exe /run /tn "+ DATE_CHANGE_SCH_TASK);
            long startTime = System.currentTimeMillis();
            long endTime = startTime + (1000 * 60 * 1);
            while (System.currentTimeMillis() < endTime) {
                String dateTo = getCurrentDate(CommonFormats.DAY_MONTHNAME_YEAR);
                long monthsDiff = getMonthDiff(dateFrom, dateTo, CommonFormats.DAY_MONTHNAME_YEAR);
                if (monthsDiff == months) {
                    dateChange = true;
                    break;
                } else {
                    Thread.sleep(10000);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return dateChange;
    }

    public long getTimeDiff(String tripStartDate, String searchStartDate, String datePattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        long date1 = sdf.parse(tripStartDate).getTime();
        long date2 = sdf.parse(searchStartDate).getTime();

        return (date1 - date2)/1000;
    }

    public long getMonthDiff(String fromDate, String toDate, String datePattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        return ChronoUnit.MONTHS.between(LocalDate.parse(fromDate, formatter), LocalDate.parse(toDate, formatter));
    }

    public String formatDate(String strDate, String strActualFormat, String strReqFormat) throws ParseException {
        DateFormat format1 = new SimpleDateFormat(strActualFormat);
        Date dtDate = format1.parse(strDate);
        DateFormat format2 = new SimpleDateFormat(strReqFormat);
        return format2.format(dtDate);
    }

}
