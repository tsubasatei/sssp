/*
SQLyog Ultimate - MySQL GUI v8.2 
MySQL - 5.7.18-log : Database - spring
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`spring` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `spring`;

/*Table structure for table `sssp_departments` */

DROP TABLE IF EXISTS `sssp_departments`;

CREATE TABLE `sssp_departments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `sssp_departments` */

insert  into `sssp_departments`(`id`,`department_name`) values (1,'总裁办'),(2,'开发部'),(3,'后勤部'),(4,'公关部');

/*Table structure for table `sssp_employees` */

DROP TABLE IF EXISTS `sssp_employees`;

CREATE TABLE `sssp_employees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `birth` date DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1by80iowxwcoway8hwu8aytt8` (`department_id`),
  CONSTRAINT `FK_1by80iowxwcoway8hwu8aytt8` FOREIGN KEY (`department_id`) REFERENCES `sssp_departments` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `sssp_employees` */

insert  into `sssp_employees`(`id`,`birth`,`create_time`,`email`,`last_name`,`department_id`) values (2,'1994-08-29','2018-07-28 18:40:41','aaBv@163.com','bbBv',4),(3,'1995-07-28','2018-07-28 18:40:41','aa@163.com','cc',3),(4,'1996-07-28','2018-07-28 18:40:41','aa@163.com','dd',4),(5,'1997-07-28','2018-07-28 18:40:41','aa@163.com','ee',1),(6,'1998-07-28','2018-07-28 18:40:41','aa@163.com','ff',2),(7,'1999-07-28','2018-07-28 18:40:41','aa@163.com','gg',3),(8,'2019-02-21',NULL,'rcd@163.com','瑞创达',1),(10,'1993-03-04','2019-02-21 17:13:59','xt@163.com','xt2',3),(11,'1997-07-28','2019-02-21 17:55:28','aa@163.com','ee',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
