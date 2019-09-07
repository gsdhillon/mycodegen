-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.36-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema sampleweb
--

CREATE DATABASE IF NOT EXISTS sampleweb;
USE sampleweb;

--
-- Definition of table `app_record`
--

DROP TABLE IF EXISTS `app_record`;
CREATE TABLE `app_record` (
  `form_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `apply_date` datetime NOT NULL,
  `unit_code` int(10) unsigned NOT NULL,
  `emp_no` int(10) unsigned NOT NULL,
  `name` varchar(100) CHARACTER SET latin1 NOT NULL,
  `desig` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `data1` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `data2` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `status` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `remarks` varchar(500) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`form_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `app_record`
--

/*!40000 ALTER TABLE `app_record` DISABLE KEYS */;
INSERT INTO `app_record` (`form_id`,`apply_date`,`unit_code`,`emp_no`,`name`,`desig`,`data1`,`data2`,`status`,`remarks`) VALUES 
 (1,'2019-08-22 00:00:00',66,24196,'Gurmee Singh','SO/F','data1','data2','PendingAtRecommender','some remark');
/*!40000 ALTER TABLE `app_record` ENABLE KEYS */;


--
-- Definition of table `app_visitors`
--

DROP TABLE IF EXISTS `app_visitors`;
CREATE TABLE `app_visitors` (
  `form_id` int(10) unsigned NOT NULL,
  `s_no` int(10) unsigned NOT NULL,
  `name` varchar(100) NOT NULL,
  `dob` date DEFAULT NULL,
  `gender` varchar(10) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `photo` longblob,
  PRIMARY KEY (`form_id`,`s_no`),
  CONSTRAINT `FK_app_visitors_1` FOREIGN KEY (`form_id`) REFERENCES `app_record` (`form_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `app_visitors`
--

/*!40000 ALTER TABLE `app_visitors` DISABLE KEYS */;
INSERT INTO `app_visitors` (`form_id`,`s_no`,`name`,`dob`,`gender`,`email`,`photo`) VALUES 
 (1,1,'Ajaybir Singh','2006-11-14','Male','jbir.dhillon@gmail.com',NULL);
/*!40000 ALTER TABLE `app_visitors` ENABLE KEYS */;


--
-- Definition of table `detail`
--

DROP TABLE IF EXISTS `detail`;
CREATE TABLE `detail` (
  `mid` int(10) unsigned NOT NULL,
  `sno` int(10) unsigned NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`mid`,`sno`),
  CONSTRAINT `FK_detail_1` FOREIGN KEY (`mid`) REFERENCES `master` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detail`
--

/*!40000 ALTER TABLE `detail` DISABLE KEYS */;
INSERT INTO `detail` (`mid`,`sno`,`name`) VALUES 
 (1,1,'X1'),
 (1,2,'X2'),
 (1,3,'X3'),
 (2,1,'X1'),
 (2,2,'X2'),
 (2,3,'X3'),
 (3,1,'X1'),
 (3,2,'X2'),
 (3,3,'X3'),
 (4,1,'X1'),
 (4,2,'X2'),
 (4,3,'X3'),
 (5,1,'X1'),
 (5,2,'X2'),
 (5,3,'X3');
/*!40000 ALTER TABLE `detail` ENABLE KEYS */;


--
-- Definition of table `master`
--

DROP TABLE IF EXISTS `master`;
CREATE TABLE `master` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master`
--

/*!40000 ALTER TABLE `master` DISABLE KEYS */;
INSERT INTO `master` (`id`,`name`) VALUES 
 (1,'X'),
 (2,'X'),
 (3,'X'),
 (4,'X'),
 (5,'X');
/*!40000 ALTER TABLE `master` ENABLE KEYS */;


--
-- Definition of table `pki_pro_applicant`
--

DROP TABLE IF EXISTS `pki_pro_applicant`;
CREATE TABLE `pki_pro_applicant` (
  `FORM_ID` int(10) unsigned NOT NULL,
  `EMP_NO` int(10) unsigned NOT NULL,
  `NAME` varchar(45) NOT NULL,
  `DIVISISION_NO` varchar(5) NOT NULL,
  `DIVISION_FULL_NAME` varchar(45) NOT NULL,
  `DESIG_SHORT_NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`FORM_ID`,`EMP_NO`) USING BTREE,
  CONSTRAINT `FK_pki_applicant_1` FOREIGN KEY (`FORM_ID`) REFERENCES `pki_pro_record` (`FORM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pki_pro_applicant`
--

/*!40000 ALTER TABLE `pki_pro_applicant` DISABLE KEYS */;
INSERT INTO `pki_pro_applicant` (`FORM_ID`,`EMP_NO`,`NAME`,`DIVISISION_NO`,`DIVISION_FULL_NAME`,`DESIG_SHORT_NAME`) VALUES 
 (1,24196,'Gurmeet Singh','304','Computer Division','SO/F');
/*!40000 ALTER TABLE `pki_pro_applicant` ENABLE KEYS */;


--
-- Definition of table `pki_pro_record`
--

DROP TABLE IF EXISTS `pki_pro_record`;
CREATE TABLE `pki_pro_record` (
  `FORM_ID` int(12) unsigned NOT NULL AUTO_INCREMENT,
  `APPLY_DATE` date NOT NULL,
  `DATE_OF_VISIT` date NOT NULL,
  `EXPECTED_TIME` varchar(8) CHARACTER SET latin1 NOT NULL,
  `PURPOSE` varchar(200) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`FORM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pki_pro_record`
--

/*!40000 ALTER TABLE `pki_pro_record` DISABLE KEYS */;
INSERT INTO `pki_pro_record` (`FORM_ID`,`APPLY_DATE`,`DATE_OF_VISIT`,`EXPECTED_TIME`,`PURPOSE`) VALUES 
 (1,'2019-08-24','2019-08-25','09:00:00','Purpose of visit');
/*!40000 ALTER TABLE `pki_pro_record` ENABLE KEYS */;


--
-- Definition of table `pki_pro_visitor`
--

DROP TABLE IF EXISTS `pki_pro_visitor`;
CREATE TABLE `pki_pro_visitor` (
  `FORM_ID` int(12) unsigned NOT NULL,
  `S_NO` smallint(5) unsigned NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `PHONE_NO` varchar(20) DEFAULT NULL,
  `ORG` varchar(100) DEFAULT NULL,
  `DESIG` varchar(100) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  PRIMARY KEY (`FORM_ID`,`S_NO`),
  CONSTRAINT `FK_pki_pro_visitors_1` FOREIGN KEY (`FORM_ID`) REFERENCES `pki_pro_record` (`FORM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pki_pro_visitor`
--

/*!40000 ALTER TABLE `pki_pro_visitor` DISABLE KEYS */;
INSERT INTO `pki_pro_visitor` (`FORM_ID`,`S_NO`,`NAME`,`PHONE_NO`,`ORG`,`DESIG`,`DOB`) VALUES 
 (1,1,'Ajaybir Singh','9869117976','AECS-04','Student','2006-11-14');
/*!40000 ALTER TABLE `pki_pro_visitor` ENABLE KEYS */;


--
-- Definition of table `pki_pro_visitor_address`
--

DROP TABLE IF EXISTS `pki_pro_visitor_address`;
CREATE TABLE `pki_pro_visitor_address` (
  `form_id` int(10) unsigned NOT NULL,
  `vis_sno` smallint(5) unsigned NOT NULL,
  `s_no` smallint(5) unsigned NOT NULL,
  `address_type` varchar(45) NOT NULL,
  `house` varchar(45) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `pin` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`form_id`,`vis_sno`,`s_no`),
  CONSTRAINT `FK_pki_pro_address_1` FOREIGN KEY (`form_id`, `vis_sno`) REFERENCES `pki_pro_visitor` (`FORM_ID`, `S_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pki_pro_visitor_address`
--

/*!40000 ALTER TABLE `pki_pro_visitor_address` DISABLE KEYS */;
INSERT INTO `pki_pro_visitor_address` (`form_id`,`vis_sno`,`s_no`,`address_type`,`house`,`street`,`city`,`state`,`pin`,`country`) VALUES 
 (1,1,1,'Permanent','s/o Gurmeet Singh','Vill-Burjwali,P.O.-20GG','Sriganganagar','Rajasthan','335001','India'),
 (1,1,2,'Local','604, Akashdeep','Anushakti Nagar','Mumbai','Maharahtra','400094','India');
/*!40000 ALTER TABLE `pki_pro_visitor_address` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
