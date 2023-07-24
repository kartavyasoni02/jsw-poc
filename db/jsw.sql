/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.1.56-community : Database - jsw
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`jsw` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `jsw`;

/*Table structure for table `roles` */

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `ID` INT(6) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ROLENAME` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ROLENAME` (`ROLENAME`)
) ENGINE=INNODB  DEFAULT CHARSET=latin1;


/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `ID` INT(6) UNSIGNED NOT NULL AUTO_INCREMENT,
  `USERNAME` VARCHAR(50) NOT NULL,
  `PASSWORD` VARCHAR(200) NOT NULL,
  `ENABLED` TINYINT(1) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USERNAME` (`USERNAME`)
) ENGINE=INNODB  DEFAULT CHARSET=latin1;

/*Table structure for table `user_roles` */

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `USER_ID` INT(6) UNSIGNED NOT NULL,
  `ROLE_ID` INT(6) UNSIGNED NOT NULL,
  KEY `USER` (`USER_ID`),
  KEY `ROLE` (`ROLE_ID`),
  CONSTRAINT `ROLE` FOREIGN KEY (`ROLE_ID`) REFERENCES `roles` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `USER` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=latin1;


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

/*Table structure for table `user_roles` */

DROP TABLE IF EXISTS `dyna_plan`;

CREATE TABLE `dyna_plan` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DYNA_PLAN_NAME` varchar(50) DEFAULT NULL,
  `STATUS` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


/*Table structure for table `bof_scheduled_process` */
DROP TABLE IF EXISTS `bof_scheduled_process`;

CREATE TABLE `bof_scheduled_process` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PLAN_ID` int(11) DEFAULT NULL,
  `PROCESS_TIME` int(11) DEFAULT NULL,
  `STATUS` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `bof_plan_id` (`PLAN_ID`),
  CONSTRAINT `bof_plan_id` FOREIGN KEY (`PLAN_ID`) REFERENCES `dyna_plan` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `lhf_scheduled_process` */

DROP TABLE IF EXISTS `lhf_scheduled_process`;

CREATE TABLE `lhf_scheduled_process` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PLAN_ID` int(11) DEFAULT NULL,
  `PROCESS_TIME` int(11) DEFAULT NULL,
  `STATUS` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `lhf_plan_id` (`PLAN_ID`),
  CONSTRAINT `lhf_plan_id` FOREIGN KEY (`PLAN_ID`) REFERENCES `dyna_plan` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


/*Table structure for table `rhd_scheduled_process` */

DROP TABLE IF EXISTS `rhd_scheduled_process`;

CREATE TABLE `rhd_scheduled_process` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PLAN_ID` int(11) DEFAULT NULL,
  `PROCESS_TIME` int(11) DEFAULT NULL,
  `STATUS` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `rhf_plan_id` (`PLAN_ID`),
  CONSTRAINT `rhf_plan_id` FOREIGN KEY (`PLAN_ID`) REFERENCES `dyna_plan` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


/*Table structure for table `dyna_plan_details` */

DROP TABLE IF EXISTS `dyna_plan_details`;

CREATE TABLE `dyna_plan_details` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `JOB_ID` int(11) DEFAULT NULL,
  `FIER_TIME` datetime DEFAULT NULL,
  `PLAN_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `dyna_plan_id` (`PLAN_ID`),
  KEY `dyna_plan_job_id` (`JOB_ID`),
  CONSTRAINT `dyna_plan_id` FOREIGN KEY (`PLAN_ID`) REFERENCES `dyna_plan` (`ID`),
  CONSTRAINT `dyna_plan_job_id` FOREIGN KEY (`JOB_ID`) REFERENCES `job` (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Table structure for table `job` */
DROP TABLE IF EXISTS `job`;

CREATE TABLE `job` (
  `job_id` int(6) NOT NULL AUTO_INCREMENT,
  `jobName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `employee` */
DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee`
(
`EMPLOYEE_ID` INT(6) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` VARCHAR(50),
  `LAST_NAME` VARCHAR(50),
  `ADDRESS` VARCHAR(50),
  `CONTACT` VARCHAR(50),
  `EMAIL` VARCHAR(50),
PRIMARY KEY (`EMPLOYEE_ID`));

/*Data for the table `roles` */

insert  into `roles`(`ID`,`ROLENAME`) values (1,'ROLE_ADMIN'),(2,'ROLE_USER');

/*Data for the table `users` */

insert  into `users`(`ID`,`USERNAME`,`PASSWORD`,`ENABLED`) values (1,'admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918',1),(2,'user','04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb',1);


/*Data for the table `user_roles` */

insert  into `user_roles`(`USER_ID`,`ROLE_ID`) values (1,1),(2,2);

INSERT INTO `jsw`.`job` (`job_id`, `jobName`) VALUES (NULL, 'BOF'); 
INSERT INTO `jsw`.`job` (`job_id`, `jobName`) VALUES (NULL, 'LHF'); 
INSERT INTO `jsw`.`job` (`job_id`, `jobName`) VALUES (NULL, 'RHD'); 


ALTER TABLE `bof_scheduled_process` ADD COLUMN `FIRE_TIME` DATETIME NULL AFTER `PROCESS_TIME`; 
ALTER TABLE `lhf_scheduled_process` ADD COLUMN `FIRE_TIME` DATETIME NULL AFTER `PROCESS_TIME`; 
ALTER TABLE `rhd_scheduled_process` ADD COLUMN `FIRE_TIME` DATETIME NULL AFTER `PROCESS_TIME`; 
