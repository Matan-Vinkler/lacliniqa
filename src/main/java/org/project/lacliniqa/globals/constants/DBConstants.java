package org.project.lacliniqa.globals.constants;

public class DBConstants {
    public static final String MYSQL_URL = "jdbc:mysql://localhost:3306/appdb";
    public static final String MYSQL_USERNAME = "root";
    public static final String MYSQL_PASSWORD = "root";

    public static final String MYSQL_INSERT_USER_QUERY = "INSERT INTO `users` (`uid`,`email`,`fname`,`lname`,`phone`,`id`,`password`) VALUES ('%s','%s','%s','%s','%s','%s','%s');";
    public static final String MYSQL_SELECT_USER_QUERY = "SELECT * FROM `users` WHERE `email`='%s' AND `password`='%s';";
}
