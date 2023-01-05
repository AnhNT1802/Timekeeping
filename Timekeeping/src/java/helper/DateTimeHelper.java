/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author NTA-PC
 */
public class DateTimeHelper {

    public static int getDayOfMonth(Date datetime) {
        LocalDate localDate = datetime.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return localDate.getDayOfMonth();
    }

    public static Timestamp getTimeStamp(Date date) {
        return new Timestamp(date.getTime());
    }

    public static int getDayOfWeek(Date datetime) {
        LocalDate localDate = datetime.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return localDate.getDayOfWeek().getValue();
    }

    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        Date newDate = calendar.getTime();
        return newDate;
    }

    public static Date addMonths(Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        Date newDate = calendar.getTime();
        return newDate;
    }

    public static Date removeTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date newDate = calendar.getTime();
        return newDate;
    }

    public static ArrayList<Date> getDates(Date from, Date to) {
        ArrayList<Date> dates = new ArrayList<>();
        int count = 0;
        while (true) {
            Date item = addDays(from, count);
            dates.add(item);
            count++;
            if (item.getTime() == to.getTime()) {
                break;
            }
        }
        return dates;
    }

    public static Month getMonth(Date datetime) {
        LocalDate localDate = datetime.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return localDate.getMonth();
    }
    
    public static Date getDateFrom(Timestamp timestamp) {
        return new Date(timestamp.getTime());
    }
    
}
