package org.project.lacliniqa.globals.constants;

public class DBConstants {
    public static final String MYSQL_URL = "jdbc:mysql://localhost:3306/appdb";
    public static final String MYSQL_USERNAME = "root";
    public static final String MYSQL_PASSWORD = "root";

    public static final String MYSQL_SIGNUP_USER_QUERY = "INSERT INTO `users` (`uid`,`email`,`fname`,`lname`,`phone`,`id`,`password`) VALUES (?,?,?,?,?,?,?);";
    public static final String MYSQL_LOGIN_USER_QUERY = "SELECT * FROM `users` WHERE `email`=? AND `password`=?;";
    public static final String MYSQL_GET_USER_QUERY = "SELECT * FROM `users` WHERE `uid`=?;";

    public static final String MYSQL_SAVE_APPOINTMENT_QUERY = "INSERT INTO `appointments`(`aid`,`userId`,`type`,`subtype`,`date`,`time`) VALUES (?,?,?,?,?,?);";
    public static final String MYSQL_GET_APPOINTMENTS_FROM_UID_QUERY = "SELECT * FROM `appointments` WHERE `userId`=? ORDER BY `date` DESC";

}
