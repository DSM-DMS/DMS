-- MySQL dump 10.13  Distrib 5.6.28, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: dsm_dms
-- ------------------------------------------------------
-- Server version	5.6.28-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(20) NOT NULL,
  `password` varchar(300) NOT NULL,
  `session_key` varchar(300) DEFAULT NULL,
  `permission` tinyint(1) NOT NULL,
  PRIMARY KEY (`idx`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `afterschool_apply`
--

DROP TABLE IF EXISTS `afterschool_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `afterschool_apply` (
  `id` int(11) NOT NULL,
  `no` int(11) NOT NULL,
  KEY `id` (`id`),
  KEY `no` (`no`),
  CONSTRAINT `afterschool_apply_ibfk_1` FOREIGN KEY (`id`) REFERENCES `account` (`idx`),
  CONSTRAINT `afterschool_apply_ibfk_2` FOREIGN KEY (`no`) REFERENCES `afterschool_list` (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `afterschool_apply`
--

LOCK TABLES `afterschool_apply` WRITE;
/*!40000 ALTER TABLE `afterschool_apply` DISABLE KEYS */;
/*!40000 ALTER TABLE `afterschool_apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `afterschool_list`
--

DROP TABLE IF EXISTS `afterschool_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `afterschool_list` (
  `no` int(11) NOT NULL,
  `title` varchar(45) NOT NULL,
  `target` int(1) NOT NULL,
  `place` varchar(10) NOT NULL,
  `day` int(1) NOT NULL,
  `instructor` varchar(10) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='방과후 목록';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `afterschool_list`
--

LOCK TABLES `afterschool_list` WRITE;
/*!40000 ALTER TABLE `afterschool_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `afterschool_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_content`
--

DROP TABLE IF EXISTS `app_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_content` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) NOT NULL,
  `category` int(1) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` varchar(5000) NOT NULL,
  `writer` varchar(10) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='공지사항, 가정통신 등';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_content`
--

LOCK TABLES `app_content` WRITE;
/*!40000 ALTER TABLE `app_content` DISABLE KEYS */;
/*!40000 ALTER TABLE `app_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attachments`
--

DROP TABLE IF EXISTS `attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attachments` (
  `no` int(11) NOT NULL,
  `name` varchar(300) NOT NULL,
  `link` varchar(300) NOT NULL,
  KEY `no` (`no`),
  CONSTRAINT `attachments_ibfk_1` FOREIGN KEY (`no`) REFERENCES `app_content` (`no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='첨부파일';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachments`
--

LOCK TABLES `attachments` WRITE;
/*!40000 ALTER TABLE `attachments` DISABLE KEYS */;
/*!40000 ALTER TABLE `attachments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extension_apply`
--

DROP TABLE IF EXISTS `extension_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `extension_apply` (
  `id` int(11) NOT NULL,
  `class` int(1) NOT NULL,
  `seat` int(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='연장신청';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extension_apply`
--

LOCK TABLES `extension_apply` WRITE;
/*!40000 ALTER TABLE `extension_apply` DISABLE KEYS */;
/*!40000 ALTER TABLE `extension_apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facility_report`
--

DROP TABLE IF EXISTS `facility_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facility_report` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `room` int(11) NOT NULL,
  `write_date` datetime NOT NULL,
  `writer` varchar(10) NOT NULL,
  `result` varchar(100) DEFAULT NULL,
  `result_date` datetime DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='시설고장신고';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facility_report`
--

LOCK TABLES `facility_report` WRITE;
/*!40000 ALTER TABLE `facility_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `facility_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faq`
--

DROP TABLE IF EXISTS `faq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faq` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `content` varchar(5000) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='기숙사생활';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faq`
--

LOCK TABLES `faq` WRITE;
/*!40000 ALTER TABLE `faq` DISABLE KEYS */;
/*!40000 ALTER TABLE `faq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goingout_apply`
--

DROP TABLE IF EXISTS `goingout_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goingout_apply` (
  `id` int(11) NOT NULL,
  `dept_date` date NOT NULL,
  `reason` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='주말외출신청';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goingout_apply`
--

LOCK TABLES `goingout_apply` WRITE;
/*!40000 ALTER TABLE `goingout_apply` DISABLE KEYS */;
/*!40000 ALTER TABLE `goingout_apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meal`
--

DROP TABLE IF EXISTS `meal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meal` (
  `date` date NOT NULL,
  `breakfast` varchar(100) NOT NULL,
  `lunch` varchar(100) NOT NULL,
  `dinner` varchar(100) NOT NULL,
  `breakfast_allergy` varchar(100) NOT NULL,
  `lunch_allergy` varchar(100) NOT NULL,
  `dinner_allergy` varchar(100) NOT NULL,
  PRIMARY KEY (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='급식';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal`
--

LOCK TABLES `meal` WRITE;
/*!40000 ALTER TABLE `meal` DISABLE KEYS */;
/*!40000 ALTER TABLE `meal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merit_apply`
--

DROP TABLE IF EXISTS `merit_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `merit_apply` (
  `no` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `target` varchar(45) DEFAULT NULL,
  `content` varchar(500) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='상점신청';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merit_apply`
--

LOCK TABLES `merit_apply` WRITE;
/*!40000 ALTER TABLE `merit_apply` DISABLE KEYS */;
/*!40000 ALTER TABLE `merit_apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `content` varchar(5000) NOT NULL,
  `writer` varchar(10) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='공지사항';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission_data`
--

DROP TABLE IF EXISTS `permission_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission_data` (
  `number` int(11) NOT NULL,
  `teacher_s` tinyint(1) NOT NULL DEFAULT '0',
  `teacher_i` tinyint(1) NOT NULL DEFAULT '0',
  `teacher_e` tinyint(1) NOT NULL DEFAULT '0',
  `teacher_d` tinyint(1) NOT NULL DEFAULT '0',
  `student_s` tinyint(1) NOT NULL DEFAULT '0',
  `student_i` tinyint(1) NOT NULL DEFAULT '0',
  `student_e` tinyint(1) NOT NULL DEFAULT '0',
  `student_d` tinyint(1) NOT NULL DEFAULT '0',
  `score_list_s` tinyint(1) NOT NULL DEFAULT '0',
  `score_list_i` tinyint(1) NOT NULL DEFAULT '0',
  `score_list_e` tinyint(1) NOT NULL DEFAULT '0',
  `score_list_d` tinyint(1) NOT NULL DEFAULT '0',
  `student_score_s` tinyint(1) NOT NULL DEFAULT '0',
  `student_score_i` tinyint(1) NOT NULL DEFAULT '0',
  `student_score_e` tinyint(1) NOT NULL DEFAULT '0',
  `student_score_d` tinyint(1) NOT NULL DEFAULT '0',
  `grade_score_s` tinyint(1) NOT NULL DEFAULT '0',
  `grade_score_i` tinyint(1) NOT NULL DEFAULT '0',
  `grade_score_e` tinyint(1) NOT NULL DEFAULT '0',
  `grade_score_d` tinyint(1) NOT NULL DEFAULT '0',
  `teacher_score_s` tinyint(1) NOT NULL DEFAULT '0',
  `teacher_score_i` tinyint(1) NOT NULL DEFAULT '0',
  `teacher_score_e` tinyint(1) NOT NULL DEFAULT '0',
  `teacher_score_d` tinyint(1) NOT NULL DEFAULT '0',
  `score_statistics_control` tinyint(1) NOT NULL DEFAULT '0',
  `send_message` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`number`),
  CONSTRAINT `permission_data_ibfk_1` FOREIGN KEY (`number`) REFERENCES `teacher_account` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission_data`
--

LOCK TABLES `permission_data` WRITE;
/*!40000 ALTER TABLE `permission_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plan` (
  `year` int(4) NOT NULL,
  `month` int(2) NOT NULL,
  `data` varchar(5000) NOT NULL,
  PRIMARY KEY (`year`,`month`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
/*!40000 ALTER TABLE `plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qna`
--

DROP TABLE IF EXISTS `qna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qna` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `question_content` varchar(5000) NOT NULL,
  `question_date` datetime NOT NULL,
  `writer` varchar(20) NOT NULL,
  `answer_content` varchar(5000) DEFAULT NULL,
  `answer_date` datetime DEFAULT NULL,
  `privacy` tinyint(1) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='QNA';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qna`
--

LOCK TABLES `qna` WRITE;
/*!40000 ALTER TABLE `qna` DISABLE KEYS */;
/*!40000 ALTER TABLE `qna` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qna_comment`
--

DROP TABLE IF EXISTS `qna_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qna_comment` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `no` int(11) NOT NULL,
  `writer` varchar(10) NOT NULL,
  `comment_date` datetime NOT NULL,
  `content` varchar(300) NOT NULL,
  PRIMARY KEY (`idx`),
  KEY `faq_comment_ibfk_1` (`no`),
  CONSTRAINT `faq_comment_ibfk_1` FOREIGN KEY (`no`) REFERENCES `qna` (`no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='QNA 댓글';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qna_comment`
--

LOCK TABLES `qna_comment` WRITE;
/*!40000 ALTER TABLE `qna_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `qna_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rewardscore_apply`
--

DROP TABLE IF EXISTS `rewardscore_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rewardscore_apply` (
  `no` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `target` varchar(45) DEFAULT NULL,
  `content` varchar(500) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='상점신청';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rewardscore_apply`
--

LOCK TABLES `rewardscore_apply` WRITE;
/*!40000 ALTER TABLE `rewardscore_apply` DISABLE KEYS */;
/*!40000 ALTER TABLE `rewardscore_apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rule`
--

DROP TABLE IF EXISTS `rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rule` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `content` varchar(5000) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='기숙사규정';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rule`
--

LOCK TABLES `rule` WRITE;
/*!40000 ALTER TABLE `rule` DISABLE KEYS */;
/*!40000 ALTER TABLE `rule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stay_apply`
--

DROP TABLE IF EXISTS `stay_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stay_apply` (
  `id` int(11) NOT NULL,
  `value` int(1) NOT NULL,
  `date` varchar(10) NOT NULL DEFAULT 'all',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='잔류신청';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stay_apply`
--

LOCK TABLES `stay_apply` WRITE;
/*!40000 ALTER TABLE `stay_apply` DISABLE KEYS */;
/*!40000 ALTER TABLE `stay_apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_data`
--

DROP TABLE IF EXISTS `student_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_data` (
  `number` int(11) NOT NULL,
  `sex` tinyint(1) DEFAULT '1',
  `status` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `p_name` varchar(20) DEFAULT NULL,
  `p_phone` varchar(20) DEFAULT NULL,
  `merit` int(11) DEFAULT NULL,
  `demerit` int(11) DEFAULT NULL,
  PRIMARY KEY (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_data`
--

LOCK TABLES `student_data` WRITE;
/*!40000 ALTER TABLE `student_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_account`
--

DROP TABLE IF EXISTS `teacher_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_account` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(20) NOT NULL,
  `password` varchar(300) NOT NULL,
  `session_key` varchar(40) NOT NULL,
  `permission` int(11) NOT NULL DEFAULT '0',
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`idx`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_account`
--

LOCK TABLES `teacher_account` WRITE;
/*!40000 ALTER TABLE `teacher_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `teacher_account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-23  9:14:27
