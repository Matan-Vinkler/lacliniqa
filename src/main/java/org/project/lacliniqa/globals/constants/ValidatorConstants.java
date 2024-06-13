package org.project.lacliniqa.globals.constants;

public class ValidatorConstants {
    public static final String FORBIDDEN_SPACECHAR = " ";
    public static final String VALID_EMAIL_SYMBOL_1 = "@";
    public static final String VALID_EMAIL_SYMBOL_2 = ".";
    public static final String VALID_PHONE_REGEX = "^\\+?[0-9. ()-]*$";
    public static final int VALID_ID_LENGTH = 9;
    public static final int VALID_PASSWORD_MIN_LEN = 8;

    public static final String APPOINTMENT_DATEPICKER_FORMAT = "d MMM yyyy";
    public static final String APPOINTMENT_MYSQL_FORMAT = "YYYY-MM-dd";
    public static final String APPOINTMENT_TIME_FORMAT = "HH:mm:ss";

    public static final int MIN_HOUR = 0;
    public static final int MAX_HOUR = 23;
    public static final int MIN_MINUTE = 0;
    public static final int MAX_MINUTR = 59;
}
