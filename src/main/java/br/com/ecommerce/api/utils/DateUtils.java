package br.com.ecommerce.api.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    private static final String WIN = "Windows";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static final SimpleDateFormat DATE_FORMATDDMMYYYYHHmmss = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
    private static final SimpleDateFormat DATE_FORMAT_HHMM = new SimpleDateFormat("HH:mm");
    private static final SimpleDateFormat DATE_FORMAT_DDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat DATE_FORMAT_YYYYMMDD = new SimpleDateFormat("yyyy/MM/dd");
    private static final DateTimeFormatter LOCAL_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final DateTimeFormatter LOCAL_DATE_FORMAT_DDMMYYYY = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final SimpleDateFormat DATE_MM_YYYY = new SimpleDateFormat("MM/yyyy");
    private static final DateTimeFormatter DATE_FORMAT_YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_FORMAT_YYYY_MM_DD_HH_mm = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
    private static final DateTimeFormatter DATE_FORMAT_YYYY_MM_DD_HH_mm_ss = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private DateUtils() {
    }

    public static String todayString() {
        Date today = new Date();
        Format formatter = new SimpleDateFormat("yyyy/MM/dd");
        return formatter.format(today);
    }

    public static LocalTime getLocalTime(String hour) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(hour, formatter);
    }

    public static String getTempDir() {
        return System.getProperty("java.io.tmpdir").concat(System.getProperty("os.name").contains(WIN) ? "" : "/");
    }

    public static String getDateFormatted(Date data) {
        return DATE_FORMAT.format(data);
    }

    public static String getDateFormattDDMMYYYYHHmmss(Date data) {
        return DATE_FORMATDDMMYYYYHHmmss.format(data);
    }

    public static String getDateFormattedHHMM(Date data) {
        return DATE_FORMAT_HHMM.format(data);
    }

    public static String getDateFormattedDDMMYYYYHHmmss(Date data) {
        return DATE_FORMAT.format(data);
    }

    public static String getDateFormattedDDMMYYYY(Date data) {
        return DATE_FORMAT_DDMMYYYY.format(data);
    }

    public static String getDateFormattedYYYYMMDD(Date data) {
        return DATE_FORMAT_YYYYMMDD.format(data);
    }

    public static String getDataFormattedMMYYYY(Date data) {
        return DATE_MM_YYYY.format(data);
    }

    public static String getLocalDateTimeFormatted(LocalDateTime dateTime) {
        return dateTime.format(LOCAL_DATE_FORMAT);
    }

    public static String getLocalDateTimeFormattedDDMMYYYY(LocalDateTime dateTime) {
        return dateTime.format(LOCAL_DATE_FORMAT_DDMMYYYY);
    }

    public static String getLocalDateTimeFormattedYYYYMMDD(LocalDateTime dateTime) {
        return dateTime.format(DATE_FORMAT_YYYY_MM_DD);
    }
    public static String getLocalDateTimeFormattedYYYYMMDDHHmm(LocalDateTime dateTime) {
        return dateTime.format(DATE_FORMAT_YYYY_MM_DD_HH_mm);
    }

    public static String getLocalDateTimeFormattedYYYYMMDDHHmmss(LocalDateTime dateTime) {
        return dateTime.format(DATE_FORMAT_YYYY_MM_DD_HH_mm_ss);
    }
}
