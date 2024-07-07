package org.project.lacliniqa.globals.constants;

public class DBConstants {
    public static final String MYSQL_URL = "jdbc:mysql://localhost:3306/appdb";
    public static final String MYSQL_USERNAME = "root";
    public static final String MYSQL_PASSWORD = "root";
    public static final String SQLITE_URL = "jdbc:sqlite:db/backup.db";

    public static final String MYSQL_SIGNUP_USER_QUERY = "INSERT INTO `users` (`uid`,`email`,`fname`,`lname`,`phone`,`id`,`password`) VALUES (?,?,?,?,?,?,?);";
    public static final String MYSQL_LOGIN_USER_QUERY = "SELECT * FROM `users` WHERE `email`=? AND `password`=?;";
    public static final String MYSQL_GET_USER_QUERY = "SELECT * FROM `users` WHERE `uid`=?;";

    public static final String MYSQL_SAVE_APPOINTMENT_QUERY = "INSERT INTO `appointments`(`aid`,`userId`,`typeId`,`subtype`,`datetime`) VALUES (?,?,?,?,?);";
    public static final String MYSQL_GET_APPOINTMENTS_FROM_UID_QUERY = "SELECT * FROM `appointments` WHERE `userId`=? ORDER BY `datetime` DESC;";
    public static final String MYSQL_GET_UNPAID_APPOINTMENTS_FROM_UID_QUERY = "SELECT * FROM `appointments` WHERE `userId`=? AND `paid`=false ORDER BY `datetime` DESC;";
    public static final String MYSQL_GET_ALL_APPOINTMENTS_QUERY = "SELECT * FROM `appointments` ORDER BY `datetime` DESC;";
    public static final String MYSQL_DELETE_APPOINTMENT_QUERY = "DELETE FROM `appointments` WHERE `aid`=?;";
    public static final String MYSQL_UPDATE_APPOINTMENT_QUERY = "UPDATE `appointments` SET `userId`=?,`typeId`=?,`subtype`=?,`datetime`=?,`paid`=? WHERE `aid`=?;";

    public static final String MYSQL_GET_APPOINTMENT_TYPES_QUERY = "SELECT * FROM `appointment_types`;";
    public static final String MYSQL_GET_APPOINTMENT_TYPE_QUERY = "SELECT * FROM `appointment_types` WHERE `tid`=?;";
    public static final String MYSQL_SAVE_APPOINTMENT_TYPE_QUERY = "INSERT INTO `appointment_types` (`tid`,`typename`,`price`) VALUES (?,?,?);";

    public static final String MYSQL_GET_AVAILABLE_DATES_QUERY = "SELECT * FROM `available_dates`";
    public static final String MYSQL_SAVE_AVAILABLE_DATE_QUERY = "INSERT INTO `available_dates` (`did`,`datetime`) VALUES (?,?);";
    public static final String MYSQL_DELETE_DATE_QUERY = "DELETE FROM `available_dates` WHERE `datetime`=?;";
}
