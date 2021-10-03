package helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


public class manageDates {
    public static String getTodayDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String dateIs = dtf.format(now);
        return dateIs;
    }

    public static String getDate(int passDayDifference) {
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        cal.add(Calendar.DATE, -passDayDifference);
        System.out.println(passDayDifference+" day's date is "+dateFormat.format(cal.getTime()));
        return dateFormat.format(cal.getTime());
    }

}
