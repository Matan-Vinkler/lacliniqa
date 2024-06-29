package org.project.lacliniqa.globals.constants;

public class BackupDBConstants {
    public static final String SQLITE_FECTH_USERS = "SELECT * FROM `users`;";
    public static final String MYSQL_INSERT_USERS = "INSERT INTO `users`(`uid`,`email`,`fname`,`lname`,`phone`,`id`,`password`,`is_admin`) VALUES ";
    public static final String MYSQL_INSERT_USERS_VALUES_FORMAT = "('%s','%s','%s','%s','%s','%s','%s',%d)";

    public static final String MYSQL_DROP_USERS = "DROP TABLE IF EXISTS `users`;";
    public static final String MYSQL_CREATE_USERS = "CREATE TABLE `users` (  `uid` varchar(45) NOT NULL COMMENT 'Auto-generated User-ID',  `email` varchar(45) NOT NULL COMMENT 'Email Address',  `fname` varchar(45) NOT NULL COMMENT 'First Name',  `lname` varchar(45) NOT NULL COMMENT 'Last Name',  `phone` varchar(45) NOT NULL COMMENT 'Phone Number',  `id` varchar(45) NOT NULL COMMENT 'Identity Number',  `password` varchar(256) NOT NULL COMMENT 'Hashed in SHA-256 password',  `is_admin` tinyint NOT NULL DEFAULT '0' COMMENT 'Is the user admin?',  PRIMARY KEY (`uid`),  UNIQUE KEY `email_UNIQUE` (`email`),  UNIQUE KEY `uid_UNIQUE` (`uid`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

    public static final String SQLITE_FECTH_DATES = "SELECT * FROM `available_dates`;";
    public static final String MYSQL_INSERT_DATES = "INSERT INTO `available_dates`(`did`,`datetime`) VALUES ";
    public static final String MYSQL_INSERT_DATES_VALUES_FORMAT = "('%s','%s')";

    public static final String MYSQL_DROP_DATES = "DROP TABLE IF EXISTS `available_dates`;";
    public static final String MYSQL_CREATE_DATES = "CREATE TABLE `available_dates` (`did` varchar(45) NOT NULL COMMENT 'Auto generated ID',`datetime` datetime NOT NULL COMMENT 'The actual datetime',PRIMARY KEY (`did`),UNIQUE KEY `datetime_UNIQUE` (`datetime`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

    public static final String SQLITE_FECTH_TYPES = "SELECT * FROM `appointment_types`;";
    public static final String MYSQL_INSERT_TYPES = "INSERT INTO `appointment_types` (`tid`, `typename`, `price`) VALUES ";
    public static final String MYSQL_INSERT_TYPES_VALUES_FORMAT = "('%s','%s',%d)";

    public static final String MYSQL_DROP_TYPES = "DROP TABLE IF EXISTS `appointment_types`;";
    public static final String MYSQL_CREATE_TYPES = "CREATE TABLE `appointment_types` (  `tid` varchar(45) NOT NULL COMMENT 'Auto-generated appointment type ID.',  `typename` varchar(45) NOT NULL COMMENT 'Appointment type name',  `price` int DEFAULT '0' COMMENT 'The price of the appointment type',  PRIMARY KEY (`tid`),  UNIQUE KEY `tid_UNIQUE` (`tid`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

    public static final String SQLITE_FECTH_APPTS = "SELECT * FROM `appointments`;";
    public static final String MYSQL_INSERT_APPTS = "INSERT INTO `appointments` (`aid`,`userId`,`typeId`,`subtype`,`datetime`,`paid`) VALUES ";
    public static final String MYSQL_INSERT_APPTS_VALUES_FORMAT = "('%s','%s','%s','%s','%s',%d)";

    public static final String MYSQL_DROP_APPTS = "DROP TABLE IF EXISTS `appointments`;";
    public static final String MYSQL_CREATE_APPTS = "CREATE TABLE `appointments` (  `aid` varchar(45) NOT NULL COMMENT 'Auto-generated appointment ID.',  `userId` varchar(45) NOT NULL COMMENT 'ID of the owner user of the appointment.',  `typeId` varchar(45) NOT NULL COMMENT 'Type ID of appointment',  `subtype` varchar(45) NOT NULL COMMENT 'Subtype of appointment',  `datetime` datetime NOT NULL COMMENT 'Date of appointment',  `paid` tinyint DEFAULT '0',  PRIMARY KEY (`aid`),  UNIQUE KEY `aid_UNIQUE` (`aid`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
}
