package org.project.lacliniqa.globals.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.project.lacliniqa.globals.constants.ValidatorConstants.*;

public class DateFormatter {
    public static String formatToMysql(String pickerFormatDate, int hour, int minute) {
        LocalDate localDateObj = LocalDate.parse(pickerFormatDate, DateTimeFormatter.ofPattern(APPOINTMENT_DATEPICKER_FORMAT));
        String mysqlFormatDate = localDateObj.format(DateTimeFormatter.ofPattern(APPOINTMENT_MYSQL_FORMAT));

        SimpleDateFormat timeFormat = new SimpleDateFormat(APPOINTMENT_TIME_FORMAT);
        Date timeObj = new Date();
        timeObj.setHours(hour);
        timeObj.setMinutes(minute);
        timeObj.setSeconds(0);
        String timeFormatted = timeFormat.format(timeObj);

        return mysqlFormatDate + " " + timeFormatted;
    }
}
