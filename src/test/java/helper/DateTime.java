/*Upendra */

package helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class DateTime {

  public static SimpleDateFormat MMDDFormat = new SimpleDateFormat("MM/dd/yyyy");
  public static SimpleDateFormat DDMMYYYYFormat = new SimpleDateFormat("dd/MM/yyyy");
  public static SimpleDateFormat UKDateFormat = new SimpleDateFormat("dd MMM yyyy");
  public static SimpleDateFormat USDateFormat = new SimpleDateFormat("MMM d, yyyy");
  public static SimpleDateFormat MMDYYYYFormat = new SimpleDateFormat("MM d, yyyy");
  public static SimpleDateFormat batchUploadDateFormat = new SimpleDateFormat("M/d/yyyy");
  public static SimpleDateFormat DDMMFormat = new SimpleDateFormat("dd-MM-yyyy");
  public static SimpleDateFormat DateAndTimeFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
  public static SimpleDateFormat TimeFormat = new SimpleDateFormat("hh:mm:ss a");
  public static TimeZone PSTTimezone = TimeZone.getTimeZone("PST");
  public static SimpleDateFormat adjudicateHistoryDateAndTimeFormat =
      new SimpleDateFormat("MMMM d, yyyy");
  public static long startTime, endTime;

  /**
   * Get Current PST Date in Month dd, yyyy format.
   *
   * @return
   */
  public String getCurrentUKDateFormat() {
    UKDateFormat.setTimeZone(PSTTimezone);
    return DDMMFormat.format(new Date()).replace("-", "/");
  }

  public String getDateinMMMDYYYYFormat(String date) throws ParseException {
    Date dateinDateFormat = DDMMFormat.parse(date);
    return USDateFormat.format(dateinDateFormat);
  }

  public String getCurrentUSDateFormat() {
    USDateFormat.setTimeZone(PSTTimezone);
    return USDateFormat.format(new Date());
  }

  public String getBatchUploadDateFormat() {
    batchUploadDateFormat.setTimeZone(PSTTimezone);
    return batchUploadDateFormat.format(new Date());
  }

  public String getBatchUploadDateFormatMMDDYY() {
    DDMMYYYYFormat.setTimeZone(PSTTimezone);
    return DDMMYYYYFormat.format(new Date());
  }

  public String getCurrentPSTDate() {
    MMDDFormat.setTimeZone(PSTTimezone);
    return MMDDFormat.format(new Date());
  }

  public String getLastWeekDate() {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DAY_OF_MONTH, -7);
    return MMDDFormat.format(cal.getTime());
  }

  public String getadjudicateHistoryDateAndTimeFormat() {
    adjudicateHistoryDateAndTimeFormat.setTimeZone(PSTTimezone);
    return adjudicateHistoryDateAndTimeFormat.format(new Date());
  }

  public Date convertStringToDate(String date) throws ParseException {
    Date dateinDateFormat = new SimpleDateFormat("MM/dd/yyyy").parse(date);
    return dateinDateFormat;
  }

  public String getTimeFormat() {
    TimeFormat.setTimeZone(PSTTimezone);
    return TimeFormat.format(new Date());
  }

  public String getMMDYYYYFormat() {
    MMDYYYYFormat.setTimeZone(PSTTimezone);
    return TimeFormat.format(new Date());
  }

  public void getStartTime() {
    startTime = System.currentTimeMillis();
  }

  public void getEndTime() {
    endTime = System.currentTimeMillis();
  }

  public long compareTimeDifference() {
    System.out.println("Start time: " + startTime + "  ------ End time :" + endTime);
    return (endTime - startTime) / 1000;
  }

  public void verifyUSDateFormat(String date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
    try {
      LocalDate dateFormat = formatter.parse(date, LocalDate::from);
    } catch (DateTimeParseException e) {
      System.out.println(date + " date format is not available in US date format.");
    }
  }
}
