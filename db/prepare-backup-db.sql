-- Creating `appointment_types` table
DROP TABLE IF EXISTS `appointment_types`;
CREATE TABLE `appointment_types`(
  `tid` varchar(45) NOT NULL UNIQUE PRIMARY KEY,
  `typename` varchar(45) NOT NULL,
  `price` int DEFAULT '0'
);

-- Creating `appointments` table
DROP TABLE IF EXISTS `appointments`;
CREATE TABLE `appointments` (
  `aid` varchar(45) NOT NULL UNIQUE PRIMARY KEY,
  `userId` varchar(45) NOT NULL,
  `typeId` varchar(45) NOT NULL,
  `subtype` varchar(45) NOT NULL,
  `datetime` datetime NOT NULL,
  `paid` tinyint DEFAULT '0'
);

-- Creating `available_dates` table
DROP TABLE IF EXISTS `available_dates`;
CREATE TABLE `available_dates` (
  `did` varchar(45) NOT NULL UNIQUE PRIMARY KEY,
  `datetime` datetime NOT NULL
);

-- Creating `users` table
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uid` varchar(45) NOT NULL UNIQUE PRIMARY KEY,
  `email` varchar(45) NOT NULL UNIQUE,
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `id` varchar(45) NOT NULL,
  `password` varchar(256) NOT NULL,
  `is_admin` tinyint NOT NULL DEFAULT '0'
);

-- Adding new admin user
-- email: admin@gmail.com
-- password: @admin12@
INSERT INTO `users` VALUES ('fffdd1b6-0698-42a4-9562-e1be1f2f35ae','admin@gmail.com','Adam','Admin','052-538-1648','123456789','sCxN+vduxnggM4pga94aQ00nXrAZOZydqkLJxxnyJRE=',1);