-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.22-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema smes
--

CREATE DATABASE IF NOT EXISTS smes;
USE smes;

--
-- Temporary table structure for view `payment_account_view`
--
DROP TABLE IF EXISTS `payment_account_view`;
DROP VIEW IF EXISTS `payment_account_view`;
CREATE TABLE `payment_account_view` (
  `REFERENCE_ID` int(11) unsigned,
  `TRANSACTION_TYPE` varchar(7),
  `WITH_INTEREST` bigint(20),
  `REFERENCE_DATE` datetime,
  `REFERENCE_NUMBER` varchar(20),
  `DESCRIPTION` varchar(100),
  `AMOUNT` double,
  `COMPANY_ID` int(11) unsigned,
  `CUSTOMER_ID` int(11) unsigned,
  `CREATED_BY` int(11) unsigned,
  `CREATED_DATE` datetime,
  `MODIFIED_BY` int(11) unsigned,
  `MODIFIED_DATE` datetime
);

--
-- Definition of table `account`
--

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `ACCOUNT_ID` int(10) unsigned NOT NULL auto_increment,
  `ACCOUNT_TYPE_ID` int(10) unsigned NOT NULL,
  `ACCOUNT_DATE` timestamp NOT NULL default '0000-00-00 00:00:00',
  `REFERENCE_NUMBER` varchar(20) NOT NULL,
  `DESCRIPTION` varchar(100) default NULL,
  `AMOUNT` double NOT NULL,
  `CREATED_BY` int(10) unsigned NOT NULL,
  `CREATED_DATE` timestamp NOT NULL default '0000-00-00 00:00:00',
  `MODIFIED_BY` int(10) unsigned NOT NULL,
  `MODIFIED_DATE` timestamp NOT NULL default '0000-00-00 00:00:00',
  `COMPANY_ID` int(10) unsigned NOT NULL,
  `CUSTOMER_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`ACCOUNT_ID`),
  KEY `FK_account_ACCOUNT_TYPE` (`ACCOUNT_TYPE_ID`),
  KEY `FK_account_CREATED_BY` (`CREATED_BY`),
  KEY `FK_account_MODIFIED_BY` (`MODIFIED_BY`),
  KEY `FK_account_COMPANY` (`COMPANY_ID`),
  KEY `FK_account_CUSTOMER` (`CUSTOMER_ID`),
  CONSTRAINT `FK_account_ACCOUNT_TYPE` FOREIGN KEY (`ACCOUNT_TYPE_ID`) REFERENCES `account_type` (`ACCOUNT_TYPE_ID`),
  CONSTRAINT `FK_account_COMPANY` FOREIGN KEY (`COMPANY_ID`) REFERENCES `company` (`COMPANY_ID`),
  CONSTRAINT `FK_account_CREATED_BY` FOREIGN KEY (`CREATED_BY`) REFERENCES `smes_user` (`USER_ID`),
  CONSTRAINT `FK_account_CUSTOMER` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`),
  CONSTRAINT `FK_account_MODIFIED_BY` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `smes_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`ACCOUNT_ID`,`ACCOUNT_TYPE_ID`,`ACCOUNT_DATE`,`REFERENCE_NUMBER`,`DESCRIPTION`,`AMOUNT`,`CREATED_BY`,`CREATED_DATE`,`MODIFIED_BY`,`MODIFIED_DATE`,`COMPANY_ID`,`CUSTOMER_ID`) VALUES 
 (1,1,'2010-10-08 21:04:06','1231','test',10000,1,'2010-11-05 00:00:00',1,'2010-11-05 00:00:00',1,1),
 (2,1,'2010-11-02 00:00:00','213','Cahs',2500,1,'2010-11-09 00:00:00',1,'2010-11-05 00:00:00',1,1),
 (29,1,'2010-11-11 06:30:48','123454','12',2000,1,'2010-11-13 18:30:48',1,'2010-11-13 18:30:48',1,1),
 (30,1,'2010-11-17 10:37:21','1','test',20000,1,'2010-11-17 22:37:21',1,'2010-11-17 22:37:21',1,1);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;


--
-- Definition of table `account_type`
--

DROP TABLE IF EXISTS `account_type`;
CREATE TABLE `account_type` (
  `ACCOUNT_TYPE_ID` int(10) unsigned NOT NULL auto_increment,
  `NAME` varchar(45) NOT NULL,
  `CREATED_BY` int(10) unsigned NOT NULL,
  `CREATED_DATE` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `MODIFIED_BY` int(10) unsigned NOT NULL,
  `MODIFIED_DATE` timestamp NOT NULL default '0000-00-00 00:00:00',
  `COMPANY_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`ACCOUNT_TYPE_ID`),
  KEY `FK_ACCOUNT_TYPE_MODIFIED_BY` (`MODIFIED_BY`),
  KEY `FK_account_type_COMPANY` (`COMPANY_ID`),
  CONSTRAINT `FK_account_type_COMPANY` FOREIGN KEY (`COMPANY_ID`) REFERENCES `company` (`COMPANY_ID`),
  CONSTRAINT `FK_ACCOUNT_TYPE_CREATED_BY` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `smes_user` (`USER_ID`),
  CONSTRAINT `FK_ACCOUNT_TYPE_MODIFIED_BY` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `smes_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account_type`
--

/*!40000 ALTER TABLE `account_type` DISABLE KEYS */;
INSERT INTO `account_type` (`ACCOUNT_TYPE_ID`,`NAME`,`CREATED_BY`,`CREATED_DATE`,`MODIFIED_BY`,`MODIFIED_DATE`,`COMPANY_ID`) VALUES 
 (1,'Cash',1,'2010-11-01 00:00:00',1,'2010-11-01 00:00:00',1);
/*!40000 ALTER TABLE `account_type` ENABLE KEYS */;


--
-- Definition of table `bank`
--

DROP TABLE IF EXISTS `bank`;
CREATE TABLE `bank` (
  `BANK_ID` int(10) unsigned NOT NULL auto_increment,
  `BANK_NAME` varchar(45) NOT NULL,
  `ADDRESS` varchar(100) default NULL,
  `CONTACT_NUMBER` varchar(20) default NULL,
  `CREATED_DATE` date NOT NULL,
  `CREATED_BY` int(10) unsigned NOT NULL,
  `MODIFIED_DATE` date NOT NULL,
  `MODIFIED_BY` int(10) unsigned NOT NULL,
  PRIMARY KEY  USING BTREE (`BANK_ID`),
  KEY `CREATED_BY_FORIEGN_KEY` USING BTREE (`CREATED_BY`),
  KEY `MODIFIED_BY_FOREIGN_KEY` USING BTREE (`MODIFIED_BY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bank`
--

/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;


--
-- Definition of table `company`
--

DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `COMPANY_ID` int(10) unsigned NOT NULL auto_increment,
  `COMPANY_NAME` varchar(45) NOT NULL,
  `ADDRESS` varchar(100) NOT NULL,
  `CONTACT_NUMBER` varchar(20) NOT NULL,
  `CREATED_BY` int(10) unsigned NOT NULL,
  `CREATED_DATE` datetime NOT NULL,
  `MODIFIED_BY` int(10) unsigned NOT NULL,
  `MODIFIED_DATE` datetime NOT NULL,
  PRIMARY KEY  (`COMPANY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company`
--

/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` (`COMPANY_ID`,`COMPANY_NAME`,`ADDRESS`,`CONTACT_NUMBER`,`CREATED_BY`,`CREATED_DATE`,`MODIFIED_BY`,`MODIFIED_DATE`) VALUES 
 (1,'KOWII','ILOILO','09123123',1,'2010-11-01 00:00:00',1,'2010-11-01 00:00:00'),
 (2,'HINSTRO','METRO MANILA','1234123',1,'2010-11-01 00:00:00',0,'2010-11-01 00:00:00');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;


--
-- Definition of table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `CUSTOMER_ID` int(10) unsigned NOT NULL auto_increment,
  `COMPANY_ID` int(10) unsigned NOT NULL,
  `FIRST_NAME` varchar(45) NOT NULL,
  `MIDDLE_NAME` varchar(45) default NULL,
  `LAST_NAME` varchar(45) NOT NULL,
  `ADDRESS` varchar(150) NOT NULL,
  `CONTACT_NUMBER` varchar(45) NOT NULL,
  `CREATED_BY` int(10) unsigned NOT NULL,
  `CREATED_DATE` datetime NOT NULL,
  `MODIFIED_DATE` datetime NOT NULL,
  `MODIFIED_BY` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`CUSTOMER_ID`),
  KEY `FK_CAMPANY` (`COMPANY_ID`),
  KEY `FK_CUSTOMER_CREATED_BY` (`CREATED_BY`),
  KEY `FK_MODIFIED_BY` (`MODIFIED_BY`),
  CONSTRAINT `FK_CAMPANY` FOREIGN KEY (`COMPANY_ID`) REFERENCES `company` (`COMPANY_ID`),
  CONSTRAINT `FK_CUSTOMER_CREATED_BY` FOREIGN KEY (`CREATED_BY`) REFERENCES `smes_user` (`USER_ID`),
  CONSTRAINT `FK_MODIFIED_BY` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `smes_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`CUSTOMER_ID`,`COMPANY_ID`,`FIRST_NAME`,`MIDDLE_NAME`,`LAST_NAME`,`ADDRESS`,`CONTACT_NUMBER`,`CREATED_BY`,`CREATED_DATE`,`MODIFIED_DATE`,`MODIFIED_BY`) VALUES 
 (1,1,'Lemuel','Masa','Javellana','Metro Manila, Philippines','+639178220714',1,'2010-11-08 20:52:18','2010-11-08 20:52:18',1),
 (2,1,'Jenny','Pe','Kam','Iloilo','12312',1,'2010-10-19 00:24:16','2010-10-19 00:24:16',1),
 (4,1,'Jonel','Masa','Javellana','Koronadal City','123123',1,'2010-10-16 13:35:04','2010-10-16 13:35:04',1),
 (5,1,'Starlyn','Masa','Javellana','123123','12123',1,'2010-10-29 23:18:55','2010-10-29 23:18:55',1),
 (7,1,'Mommy','Masa','Javellana','123','123',1,'2010-10-16 01:22:03','2010-10-16 01:22:03',1),
 (12,1,'Samuel','Capacillo','Javellana','1123','123123',1,'2010-10-29 23:39:16','2010-10-29 23:39:16',1),
 (14,1,'Hazel','Masa','Javellana','123123','123123',1,'2010-11-03 21:12:26','2010-11-03 21:12:26',1),
 (15,1,'a','a','a','a','a',1,'2010-11-19 21:32:50','2010-11-19 21:32:50',1),
 (16,1,'b','b','b','b','b',1,'2010-11-19 21:32:56','2010-11-19 21:32:56',1),
 (17,1,'c','c','c','c','c',1,'2010-11-19 21:33:02','2010-11-19 21:33:02',1),
 (18,1,'d','d','d','d','d',1,'2010-11-19 21:33:07','2010-11-19 21:33:07',1),
 (19,1,'e','e','e','e','e',1,'2010-11-19 21:33:14','2010-11-19 21:33:14',1),
 (20,1,'f','f','f','f','f',1,'2010-11-19 21:33:20','2010-11-19 21:33:20',1),
 (21,1,'g','g','g','g','g',1,'2010-11-19 21:33:25','2010-11-19 21:33:25',1),
 (22,1,'i','i','i','i','i',1,'2010-11-19 21:33:31','2010-11-19 21:33:31',1),
 (23,1,'h','h','h','h','h',1,'2010-11-19 21:33:40','2010-11-19 21:33:40',1),
 (24,1,'j','j','j','j','j',1,'2010-11-21 22:46:44','2010-11-21 22:46:44',1),
 (25,1,'k','k','k','k','k',1,'2010-11-21 22:47:01','2010-11-21 22:47:01',1),
 (26,1,'l','l','l','l','l',1,'2010-11-21 22:47:55','2010-11-21 22:47:55',1),
 (27,1,'m','m','m','m','m',1,'2010-11-21 22:52:29','2010-11-21 22:52:29',1),
 (28,1,'n','n','n','n','n',1,'2010-11-23 21:16:07','2010-11-23 21:16:07',1),
 (29,1,'o','o','o','o','o',1,'2010-11-23 21:17:17','2010-11-23 21:17:17',1);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;


--
-- Definition of table `customer_account_preference`
--

DROP TABLE IF EXISTS `customer_account_preference`;
CREATE TABLE `customer_account_preference` (
  `CUSTOMER_ACCOUNT_PREFERENCE_ID` int(10) unsigned NOT NULL auto_increment,
  `CUSTOMER_ID` int(10) unsigned NOT NULL,
  `INTEREST` double NOT NULL,
  `CREATED_BY` int(10) unsigned NOT NULL,
  `CREATED_DATE` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `MODIFIED_BY` int(10) unsigned NOT NULL,
  `MODIFIED_DATE` timestamp NOT NULL default '0000-00-00 00:00:00',
  `MAXIMUM_AMOUNT` double NOT NULL,
  PRIMARY KEY  (`CUSTOMER_ACCOUNT_PREFERENCE_ID`),
  KEY `FK_CUSTOMER_ID` (`CUSTOMER_ID`),
  KEY `FK_CREATED_BY` (`CREATED_BY`),
  KEY `FK_UPDATED_BY` (`MODIFIED_BY`),
  CONSTRAINT `FK_CREATED_BY` FOREIGN KEY (`CREATED_BY`) REFERENCES `smes_user` (`USER_ID`),
  CONSTRAINT `FK_CUSTOMER_ID` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`),
  CONSTRAINT `FK_UPDATED_BY` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `smes_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer_account_preference`
--

/*!40000 ALTER TABLE `customer_account_preference` DISABLE KEYS */;
INSERT INTO `customer_account_preference` (`CUSTOMER_ACCOUNT_PREFERENCE_ID`,`CUSTOMER_ID`,`INTEREST`,`CREATED_BY`,`CREATED_DATE`,`MODIFIED_BY`,`MODIFIED_DATE`,`MAXIMUM_AMOUNT`) VALUES 
 (1,14,3,1,'2010-11-04 20:37:35',1,'2010-11-04 20:37:35',20000),
 (2,2,8,1,'2010-11-04 20:37:50',1,'2010-11-04 20:37:50',500000),
 (4,5,5,1,'2010-11-04 20:38:11',1,'2010-11-04 20:38:11',150000),
 (5,1,0,1,'2010-11-17 22:25:09',1,'2010-11-17 22:25:09',10000),
 (6,7,4,1,'2010-11-13 17:31:56',1,'2010-11-13 17:31:56',0),
 (7,12,4,1,'2010-11-04 20:38:05',1,'2010-11-04 20:38:05',50000),
 (8,4,2,1,'2010-11-03 22:07:00',1,'2010-11-03 22:07:00',0);
/*!40000 ALTER TABLE `customer_account_preference` ENABLE KEYS */;


--
-- Definition of table `deposit_log`
--

DROP TABLE IF EXISTS `deposit_log`;
CREATE TABLE `deposit_log` (
  `DEPOSIT_LOG_ID` int(10) unsigned NOT NULL auto_increment,
  `BANK_ID` int(10) unsigned NOT NULL,
  `DEPOSIT_DATE` datetime NOT NULL,
  `AMOUNT` double NOT NULL,
  `REMARKS` varchar(250) default NULL,
  `CREATED_DATE` datetime NOT NULL,
  `CREATED_BY` int(10) unsigned NOT NULL,
  `MODIFIED_DATE` datetime NOT NULL,
  `MODIFIED_BY` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`DEPOSIT_LOG_ID`),
  KEY `FK_deposit_log_1` (`BANK_ID`),
  KEY `FK_deposit_log_2` (`CREATED_BY`),
  KEY `FK_deposit_log_3` (`MODIFIED_BY`),
  CONSTRAINT `FK_deposit_log_1` FOREIGN KEY (`BANK_ID`) REFERENCES `bank` (`BANK_ID`),
  CONSTRAINT `FK_deposit_log_2` FOREIGN KEY (`CREATED_BY`) REFERENCES `smes_user` (`USER_ID`),
  CONSTRAINT `FK_deposit_log_3` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `smes_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `deposit_log`
--

/*!40000 ALTER TABLE `deposit_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `deposit_log` ENABLE KEYS */;


--
-- Definition of table `group`
--

DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `GROUP_ID` int(10) unsigned NOT NULL auto_increment,
  `GROUP_NAME` varchar(45) NOT NULL,
  `GROUP_DESCRIPTION` varchar(100) NOT NULL,
  PRIMARY KEY  (`GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `group`
--

/*!40000 ALTER TABLE `group` DISABLE KEYS */;
/*!40000 ALTER TABLE `group` ENABLE KEYS */;


--
-- Definition of table `payment`
--

DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `PAYMENT_ID` int(10) unsigned NOT NULL auto_increment,
  `PAYMENT_DATE` timestamp NOT NULL default '0000-00-00 00:00:00',
  `REFERENCE_NUMBER` varchar(10) NOT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  `AMOUNT` double NOT NULL,
  `CREATED_BY` int(10) unsigned NOT NULL,
  `CREATED_DATE` timestamp NOT NULL default '0000-00-00 00:00:00',
  `MODIFIED_BY` int(10) unsigned NOT NULL,
  `MODIFIED_DATE` timestamp NOT NULL default '0000-00-00 00:00:00',
  `COMPANY_ID` int(10) unsigned NOT NULL,
  `CUSTOMER_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`PAYMENT_ID`),
  KEY `FK_PAYMENT_MODIFIED_BY` (`MODIFIED_BY`),
  KEY `FK_PAYMENT_CREATED_BY` (`CREATED_BY`),
  KEY `FK_payment_company` (`COMPANY_ID`),
  KEY `FK_payment_CUSTOMER` (`CUSTOMER_ID`),
  CONSTRAINT `FK_payment_company` FOREIGN KEY (`COMPANY_ID`) REFERENCES `company` (`COMPANY_ID`),
  CONSTRAINT `FK_PAYMENT_CREATED_BY` FOREIGN KEY (`CREATED_BY`) REFERENCES `smes_user` (`USER_ID`),
  CONSTRAINT `FK_payment_CUSTOMER` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`),
  CONSTRAINT `FK_PAYMENT_MODIFIED_BY` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `smes_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment`
--

/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` (`PAYMENT_ID`,`PAYMENT_DATE`,`REFERENCE_NUMBER`,`DESCRIPTION`,`AMOUNT`,`CREATED_BY`,`CREATED_DATE`,`MODIFIED_BY`,`MODIFIED_DATE`,`COMPANY_ID`,`CUSTOMER_ID`) VALUES 
 (1,'2010-11-15 10:37:45','1234','payment',20000,1,'2010-11-17 22:37:45',1,'2010-11-17 22:37:45',1,1);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;


--
-- Definition of table `smes_user`
--

DROP TABLE IF EXISTS `smes_user`;
CREATE TABLE `smes_user` (
  `USER_ID` int(10) unsigned NOT NULL auto_increment,
  `FIRST_NAME` varchar(45) NOT NULL,
  `MIDDLE_NAME` varchar(45) default NULL,
  `LAST_NAME` varchar(45) NOT NULL,
  `ADDRESS` varchar(45) default NULL,
  `CONTACT_NUMBER` varchar(20) default NULL,
  `PASSWORD` blob NOT NULL,
  `USER_NAME` varchar(45) NOT NULL,
  `COMPANY_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY  USING BTREE (`USER_ID`),
  KEY `FK_smes_user_1` (`COMPANY_ID`),
  CONSTRAINT `FK_smes_user_1` FOREIGN KEY (`COMPANY_ID`) REFERENCES `company` (`COMPANY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `smes_user`
--

/*!40000 ALTER TABLE `smes_user` DISABLE KEYS */;
INSERT INTO `smes_user` (`USER_ID`,`FIRST_NAME`,`MIDDLE_NAME`,`LAST_NAME`,`ADDRESS`,`CONTACT_NUMBER`,`PASSWORD`,`USER_NAME`,`COMPANY_ID`) VALUES 
 (1,'Jenny','Pe','Kam','Iloilo','123132123',0x62336239383735663161613663346335333238346133646434373836623835306264643837616264,'jenk',1),
 (2,'Lemuel','Masa','Javellana','Paranaque','1234123',0x64306139313965346262376437666130313237643632346435323733383563333137393635326333,'lemuelj',2);
/*!40000 ALTER TABLE `smes_user` ENABLE KEYS */;


--
-- Definition of table `test`
--

DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `UserName` int(10) unsigned NOT NULL auto_increment,
  `Password` binary(120) NOT NULL,
  PRIMARY KEY  (`UserName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test`
--

/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` (`UserName`,`Password`) VALUES 
 (1,0x313131000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000);
/*!40000 ALTER TABLE `test` ENABLE KEYS */;


--
-- Definition of table `user_group`
--

DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `USER_GROUP_id` int(10) unsigned NOT NULL auto_increment,
  `GROUP_ID` int(10) unsigned NOT NULL,
  `USER_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`USER_GROUP_id`),
  KEY `FK_user_group_1` (`GROUP_ID`),
  KEY `FK_user_group_2` (`USER_ID`),
  CONSTRAINT `FK_user_group_1` FOREIGN KEY (`GROUP_ID`) REFERENCES `group` (`GROUP_ID`),
  CONSTRAINT `FK_user_group_2` FOREIGN KEY (`USER_ID`) REFERENCES `smes_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_group`
--

/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;


--
-- Definition of table `user_group_setting`
--

DROP TABLE IF EXISTS `user_group_setting`;
CREATE TABLE `user_group_setting` (
  `USER_GROUP_SETTING_ID` int(10) unsigned NOT NULL auto_increment,
  `GROUP_ID` int(10) unsigned NOT NULL,
  `MODULE_NAME` varchar(45) NOT NULL,
  `ALLOW` tinyint(1) NOT NULL,
  PRIMARY KEY  USING BTREE (`USER_GROUP_SETTING_ID`),
  KEY `FK_user_group_setting_1` (`GROUP_ID`),
  CONSTRAINT `FK_user_group_setting_1` FOREIGN KEY (`GROUP_ID`) REFERENCES `group` (`GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_group_setting`
--

/*!40000 ALTER TABLE `user_group_setting` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_group_setting` ENABLE KEYS */;


--
-- Definition of table `withdrawal_log`
--

DROP TABLE IF EXISTS `withdrawal_log`;
CREATE TABLE `withdrawal_log` (
  `WITHDRAWAL_LOG_ID` int(10) unsigned NOT NULL auto_increment,
  `WITHDRAWAL_DATE` date NOT NULL,
  `AMOUNT` double NOT NULL,
  `REMARKS` varchar(100) NOT NULL,
  `CREATED_BY` int(10) unsigned NOT NULL,
  `CREATED_DATE` datetime NOT NULL,
  `MODIFIED_BY` int(10) unsigned NOT NULL,
  `MODIFIED_DATE` datetime NOT NULL,
  `BANK_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY  USING BTREE (`WITHDRAWAL_LOG_ID`),
  KEY `FK_WITHDRAWAL_LOG_1` USING BTREE (`CREATED_BY`),
  KEY `FK_WITHDRAWAL_LOG_2` USING BTREE (`MODIFIED_BY`),
  KEY `FK_withdrawal_log_3` (`BANK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `withdrawal_log`
--

/*!40000 ALTER TABLE `withdrawal_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `withdrawal_log` ENABLE KEYS */;


--
-- Definition of view `payment_account_view`
--

DROP TABLE IF EXISTS `payment_account_view`;
DROP VIEW IF EXISTS `payment_account_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `payment_account_view` AS select `account`.`ACCOUNT_ID` AS `REFERENCE_ID`,_utf8'ACCOUNT' AS `TRANSACTION_TYPE`,1 AS `WITH_INTEREST`,`account`.`ACCOUNT_DATE` AS `REFERENCE_DATE`,`account`.`REFERENCE_NUMBER` AS `REFERENCE_NUMBER`,`account`.`DESCRIPTION` AS `DESCRIPTION`,`account`.`AMOUNT` AS `AMOUNT`,`account`.`COMPANY_ID` AS `COMPANY_ID`,`account`.`CUSTOMER_ID` AS `CUSTOMER_ID`,`account`.`CREATED_BY` AS `CREATED_BY`,`account`.`CREATED_DATE` AS `CREATED_DATE`,`account`.`MODIFIED_BY` AS `MODIFIED_BY`,`account`.`MODIFIED_DATE` AS `MODIFIED_DATE` from `account` union select `payment`.`PAYMENT_ID` AS `PAYMENT_ID`,_utf8'PAYMENT' AS `TRANSACTION_TYPE`,0 AS `WITH_INTEREST`,`payment`.`PAYMENT_DATE` AS `REFERENCE_DATE`,`payment`.`REFERENCE_NUMBER` AS `REFERENCE_NUMBER`,`payment`.`DESCRIPTION` AS `DESCRIPTION`,-(`payment`.`AMOUNT`) AS `-(AMOUNT)`,`payment`.`COMPANY_ID` AS `COMPANY_ID`,`payment`.`CUSTOMER_ID` AS `CUSTOMER_ID`,`payment`.`CREATED_BY` AS `CREATED_BY`,`payment`.`CREATED_DATE` AS `CREATED_DATE`,`payment`.`MODIFIED_BY` AS `MODIFIED_BY`,`payment`.`MODIFIED_DATE` AS `MODIFIED_DATE` from `payment` order by `REFERENCE_DATE`;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
