-- Creating `appointment_types` table
DROP TABLE IF EXISTS `appointment_types`;
CREATE TABLE `appointment_types` (
  `tid` varchar(45) NOT NULL COMMENT 'Auto-generated appointment type ID.',
  `typename` varchar(45) NOT NULL COMMENT 'Appointment type name',
  `price` int DEFAULT '0' COMMENT 'The price of the appointment type',
  PRIMARY KEY (`tid`),
  UNIQUE KEY `tid_UNIQUE` (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Creating `appointments` table
DROP TABLE IF EXISTS `appointments`;
CREATE TABLE `appointments` (
  `aid` varchar(45) NOT NULL COMMENT 'Auto-generated appointment ID.',
  `userId` varchar(45) NOT NULL COMMENT 'ID of the owner user of the appointment.',
  `typeId` varchar(45) NOT NULL COMMENT 'Type ID of appointment',
  `subtype` varchar(45) NOT NULL COMMENT 'Subtype of appointment',
  `datetime` datetime NOT NULL COMMENT 'Date of appointment',
  `paid` tinyint DEFAULT '0',
  PRIMARY KEY (`aid`),
  UNIQUE KEY `aid_UNIQUE` (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Creating `available_dates` table
DROP TABLE IF EXISTS `available_dates`;
CREATE TABLE `available_dates` (
  `did` varchar(45) NOT NULL COMMENT 'Auto generated ID',
  `datetime` datetime NOT NULL COMMENT 'The actual datetime',
  PRIMARY KEY (`did`),
  UNIQUE KEY `datetime_UNIQUE` (`datetime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Creating `users` table
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uid` varchar(45) NOT NULL COMMENT 'Auto-generated User-ID',
  `email` varchar(45) NOT NULL COMMENT 'Email Address',
  `fname` varchar(45) NOT NULL COMMENT 'First Name',
  `lname` varchar(45) NOT NULL COMMENT 'Last Name',
  `phone` varchar(45) NOT NULL COMMENT 'Phone Number',
  `id` varchar(45) NOT NULL COMMENT 'Identity Number',
  `password` varchar(256) NOT NULL COMMENT 'Hashed in SHA-256 password',
  `is_admin` tinyint NOT NULL DEFAULT '0' COMMENT 'Is the user admin?',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `uid_UNIQUE` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Adding new admin user
-- email: admin@gmail.com
-- password: @admin12@
INSERT INTO `users` VALUES ('fffdd1b6-0698-42a4-9562-e1be1f2f35ae','admin@gmail.com','Adam','Admin','052-385-5598','123456789','sCxN+vduxnggM4pga94aQ00nXrAZOZydqkLJxxnyJRE=',1);
