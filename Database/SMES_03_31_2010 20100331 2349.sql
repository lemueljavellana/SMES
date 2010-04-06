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
INSERT INTO `bank` (`BANK_ID`,`BANK_NAME`,`ADDRESS`,`CONTACT_NUMBER`,`CREATED_DATE`,`CREATED_BY`,`MODIFIED_DATE`,`MODIFIED_BY`) VALUES 
 (2,'123','123123','123','2010-03-31',1,'2010-03-31',1),
 (4,'AlliedBank','1231','123123','2010-03-31',1,'2010-03-31',1),
 (5,'12','','','2010-03-31',1,'2010-03-31',1),
 (6,'Metro Bank','Koronadal City','123123','2010-03-31',1,'2010-03-31',1),
 (7,'BPI','Makait','123123','2010-03-31',1,'2010-03-31',1),
 (8,'PS Bank','Makati','123123','2010-03-31',1,'2010-03-31',1),
 (9,'Rural Bank of Makati','Makati','123123123','2010-03-31',1,'2010-03-31',1);
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;


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
  `PASSWORD` varchar(100) NOT NULL,
  `USER_NAME` varchar(45) NOT NULL,
  PRIMARY KEY  USING BTREE (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `smes_user`
--

/*!40000 ALTER TABLE `smes_user` DISABLE KEYS */;
INSERT INTO `smes_user` (`USER_ID`,`FIRST_NAME`,`MIDDLE_NAME`,`LAST_NAME`,`ADDRESS`,`CONTACT_NUMBER`,`PASSWORD`,`USER_NAME`) VALUES 
 (1,'Lemuel','Masa','Javellana','Makati','09228932980','lemuel','lemuelj');
/*!40000 ALTER TABLE `smes_user` ENABLE KEYS */;


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




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
