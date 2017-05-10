-- MySQL dump 10.13  Distrib 5.6.33, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: dsm_dms
-- ------------------------------------------------------
-- Server version	5.6.33-0ubuntu0.14.04.1

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
  `uid` varchar(100) NOT NULL,
  `id` varchar(100) DEFAULT NULL,
  `password` varchar(300) NOT NULL,
  `session_key` varchar(100) DEFAULT NULL,
  `permission` tinyint(1) NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('testuid','second','asdf','testsession',1),('testuid2',NULL,'def',NULL,1);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `afterschool_apply`
--

DROP TABLE IF EXISTS `afterschool_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `afterschool_apply` (
  `id` varchar(20) NOT NULL,
  `no` int(11) NOT NULL,
  KEY `id` (`id`),
  KEY `afterschool_apply_ibfk_1` (`no`),
  CONSTRAINT `afterschool_apply_ibfk_1` FOREIGN KEY (`no`) REFERENCES `afterschool_list` (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `afterschool_apply`
--

LOCK TABLES `afterschool_apply` WRITE;
/*!40000 ALTER TABLE `afterschool_apply` DISABLE KEYS */;
INSERT INTO `afterschool_apply` VALUES ('test',1);
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
  `on_monday` tinyint(1) NOT NULL,
  `on_tuesday` tinyint(1) NOT NULL,
  `on_wednesday` tinyint(1) NOT NULL,
  `on_saturday` tinyint(1) NOT NULL,
  `instructor` varchar(10) NOT NULL,
  `personnel` int(11) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `afterschool_list`
--

LOCK TABLES `afterschool_list` WRITE;
/*!40000 ALTER TABLE `afterschool_list` DISABLE KEYS */;
INSERT INTO `afterschool_list` VALUES (1,'afterschool',3,'class',1,0,1,0,'yeongseok',20);
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
  `content` mediumtext NOT NULL,
  `writer` varchar(10) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=624 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_content`
--

LOCK TABLES `app_content` WRITE;
/*!40000 ALTER TABLE `app_content` DISABLE KEYS */;
INSERT INTO `app_content` VALUES (553,289,0,'행정실','<p> 2016학년도 정보보안실습실 네트워크 수업재료 구입 소액수의 견적제출 공고입니다.</p> \n<p> &nbsp;</p> \n<p> - 자세한 사항은 첨부파일 확인바랍니다.</p>','#**','2017-02-06 00:00:00'),(554,288,0,'홍보부','<p> 2017학년도 신입생 부트 캠프 일정</p> \n<p> &nbsp;</p> \n<p> 1. 일시 : 2017년 2월 15일(수) ~ 2월 17일(금)</p> \n<p> 2. 장소 : 속리산 유스타운</p> \n<p> 3. 출발일시 : 2월 15일 오전 9:00</p> \n<p> 4. 출발장소 : 대전역 동광장</p> \n<p> 5. 모집해제 일시 : 2월 17일(금) 오후2시(예정), 대전역 동광장</p> \n<p> 6. 준비물 : 각 실에 비누, 치약만 비치되어 있음. 개인이 쓸 세면도구(수건, 샴푸, 치솔 등 지참)</p> \n<p> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p> \n<p> &nbsp;</p> \n<p> &nbsp;</p>','#**','2017-02-06 00:00:00'),(555,287,0,'마이스터부','<p> 2017학년도 1학기 본교에 근무할 산학겸임교사를 다음과 같이 초빙하고자 합니다.</p> \n<p> &nbsp;</p> \n<p> 1. 채용 인원 : 산학겸임교사(시간제) 0명(각 교과별 1명)</p> \n<p> &nbsp;</p> \n<p> 2. 분야 : SW개발과, 임베디드SW과, 정보보안과분야 총 17교과</p> \n<p> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; * 여러 교과 중복 지원 가능</p> \n<p> &nbsp;</p> \n<p> 3. 근무기간 : 2017.03. ~ 2017.07.(본교 1학기 학사일정 기간)</p> \n<p> &nbsp;</p> \n<p> 4. 채용방법 : 서류전형 - 면접(각 단계 합격자 개별통지)</p> \n<p> &nbsp;</p> \n<p> 5. 원서접수 기간 : 2017.02.06.(월) ~ 2017.02.09.(목) 우편접수 가능</p> \n<p> &nbsp;</p> \n<p> 자세한 내용은 첨부파일을 참조하세요. 많은 관심과 지원 부탁드립니다.</p> \n<p> &nbsp;</p>','#**','2017-02-06 00:00:00'),(556,286,0,'EBS뉴스-본교(대덕소프트웨어마이스터고)방송','<p> EBS 뉴스: 대덕소프트웨어마이스터고등학교편 방송</p> \n<p> 연중기획&nbsp;\"4차 혁명시대 교육현장을 가다\"</p> \n<p> 방송일시 : 2017.01.25.(수)&nbsp; 오후 7시 30분</p> \n<p> 많은 시청바랍니다.</p> \n<p> &nbsp;</p> \n<p> 다시보기 --&gt;&nbsp;<strong><a href=\"http://news.ebs.co.kr/ebsnews/menu1/newsAllView/10627413/H?eduNewsYn=N&amp;newsFldDetlCd=\"><span style=\"font-size: 9pt;\">http://news.ebs.co.kr/ebsnews/menu1/newsAllView/10627413/H?eduNewsYn=N&amp;newsFldDetlCd=&nbsp;</span></a></strong></p>','교**','2017-01-25 00:00:00'),(557,285,0,'행정실','<p> 2016학년도 방과후교육비 정산내역을 공개합니다.</p> \n<p> &nbsp;</p> \n<p> - 자세한 사항은 첨부파일 확인바람</p>','#**','2017-01-24 00:00:00'),(558,284,0,'행정실','<p> 2016학년도 1학년 겨울방학 영어캠프운영비 정산내역을 공개합니다.</p> \n<p> &nbsp;</p> \n<p> - 자세한 사항은 첨부파일 확인바람</p>','#**','2017-01-24 00:00:00'),(559,283,0,'임채홍','<p> 2016학년도 겨울방학 방과후학교 만족도 조사 결과입니다.</p>','#**','2017-01-13 00:00:00'),(560,282,0,'학생부','<p> 2017학년도 대덕소프트웨어마이스터고등학교 배움터 지킴이 모집공고</p> \n<p> &nbsp;</p> \n<p> 1. 위촉인원 : 3명</p> \n<p> 2. 서류제출기간 : 2017.1.23.~2017.1.25. 16:00까지 시간 엄수</p> \n<p> 3. 접수방법 : 방문 접수</p> \n<p> 4. 접수처 : 대덕소프트웨어마이스터고 교무실(2층)</p>','#**','2017-01-11 00:00:00'),(561,281,0,'2017학년도기숙사관리위탁용역2단계입찰공고','<p> 2017학년도 기숙사 관리 위탁용역 2단계 입찰 공고입니다.</p> \n<p> &nbsp;</p> \n<p> - 자세한 사항은 첨부파일 확인바람</p>','행**','2017-01-06 00:00:00'),(562,279,0,'방과후학교','<p> 붙임파일은 2016학년도 2학기에 실시된 4차 방과후학교 만족도 조사 결과입니다.</p>','#**','2016-12-22 00:00:00'),(563,278,0,'마이스터부','<p> 2016 DSM JUMP UP CAMP 사전 조사서를 작성해 주세요.</p> \n<p> &nbsp;</p> \n<p style=\"margin-left:15.0pt;\"> ※ 작성기한 12.22.(목) 21:00, 보낼 곳 : modubang@naver.com</p> \n<p style=\"margin-left:15.0pt;\"> ※ 효율적인 지도 및 멘토링을 위하여, 담당 강사님께 참여자 본인의 현재 상태와 과정 참여 목표 및 계획을 정확히 알릴 수 있도록, 성실하게 답변을 작성해 주시기 바랍니다.</p>','#**','2016-12-20 00:00:00'),(564,277,0,'행정실','<p> 2016학년도 임베디드SW과 GENIVI &amp; OSEK 실습 기자재 구입 소액수의 견적제출 공고입니다.</p> \n<p> &nbsp;</p> \n<p> - 자세한 사항은 첨부파일 확인바랍니다.</p>','#**','2016-12-19 00:00:00'),(565,276,0,'김지은','<p> 전공동아리 운영 및 활동에 대한 결과 보고서 양식입니다.</p>','#**','2016-12-19 00:00:00'),(566,275,0,'행정실','<p> 2017학년도 2학년 국외현장체험학습 위탁용역 2단계 입찰 공고입니다.</p> \n<p> &nbsp;</p> \n<p> - 자세한 사항은 첨부파일을 참고해주시기 바랍니다.</p>','#**','2016-12-15 00:00:00'),(567,274,0,'행정실','<p> 2017학년도 신입생 부트캠프 위탁용역 2단계 입찰 공고입니다.</p> \n<p> &nbsp;</p> \n<p> - 자세한 사항은 첨부파일을 참고해주시기 바랍니다.</p>','#**','2016-12-15 00:00:00'),(568,273,0,'행정실','<p> 2016학년도 DSM JUMP UP CAMP 위탁용역 소액수의 견적제출 공고입니다.</p> \n<p> &nbsp;</p> \n<p> - 자세한 사항은 첨부파일을 참고해주시기 바랍니다.</p>','#**','2016-12-15 00:00:00'),(569,272,0,'임채홍','<p> 다음과 같이 겨울방학 중 방과후학교 자바 프로그램 위탁운영자 공모합니다.</p> \n<p> 1. 기간: 2017.1.2-1.13</p> \n<p> 2. 시간: 09:00-12:40</p> \n<p> 기타 자세한 사항은 붙임파일을 참고해주십시오.</p>','#**','2016-12-15 00:00:00'),(570,271,0,'겨울방학방과후학교프로그램위탁운영자공모(개인위탁)','<p> 1. 프로그램명: 파이썬</p> \n<p> 2. 모집인원: 1명</p> \n<p> 3. 기간: 2017년 1월 2일-1월 13일</p> \n<p> 4. 시간: 09:00-12:40</p> \n<p> 5. 수당: 시간 당 35,000원</p> \n<p> &nbsp;</p> \n<p> 기타 자세한 사항은 붙임을 참고하시기 바랍니다.</p>','임**','2016-12-12 00:00:00'),(571,269,0,'2016학년도임베디드SW과영상교육기자재구입입찰공고','<p> 2016학년도 임베디드SW과 영상교육 기자재 구입 입찰 공고입니다.</p> \n<p> &nbsp;</p> \n<p> - 자세한 사항은 별첨 공고문, 규격서 참고바랍니다.</p>','행**','2016-12-07 00:00:00'),(572,268,0,'마이스터부','<p> 2016학년도 동계방학 중&nbsp;독서인증 코디네이터 채용공고입니다.</p> \n<p> &nbsp;</p> \n<p> 자세한 사항은 첨부파일을 참고하시기 바랍니다.</p>','#**','2016-12-06 00:00:00'),(573,267,0,'[재학생에게DSM밴드안내와설문조사공지합니다.]','<p> 1. DSM 밴드 초대장 :</p> \n<p> &nbsp;</p> \n<p> &nbsp;&nbsp;&nbsp; <a href=\"http://band.us/n/a3acp0i5abgeM\">http://band.us/n/a3acp0i5abgeM</a></p> \n<p> &nbsp;</p> \n<p> &nbsp;</p> \n<p> 2. 학생설문조사</p> \n<p> &nbsp;</p> \n<p> &nbsp; &nbsp;&nbsp;<a href=\"http://www.meister.go.kr/jsp/w090/w_survey_student_201611_I01.jsp\" target=\"_blank\">http://www.meister.go.kr/jsp/w090/w_survey_student_201611_I01.jsp</a></p> \n<p> &nbsp;</p>','마**','2016-11-30 00:00:00'),(574,266,0,'양은정','<p> 1. 시험일시 : 2016년 12월 7일(수) ~ 12월 9일(금)</p> \n<p> &nbsp;</p>','#**','2016-11-30 00:00:00'),(575,265,0,'신입생사전교육','<p style=\"font-family: 나눔고딕, NanumGothic, NG, 굴림, Gulim, Helvetica, AppleGothic, sans-serif; margin-top: 0px; margin-right: 0px; margin-left: 0px; padding: 0px; color: rgb(68, 68, 68);\"> [신입생 사전교육 게시판 안내]</p> \n<p style=\"font-family: 나눔고딕, NanumGothic, NG, 굴림, Gulim, Helvetica, AppleGothic, sans-serif; margin-top: 0px; margin-right: 0px; margin-left: 0px; padding: 0px; color: rgb(68, 68, 68);\"> &nbsp;</p> \n<p style=\"font-family: 나눔고딕, NanumGothic, NG, 굴림, Gulim, Helvetica, AppleGothic, sans-serif; margin-top: 0px; margin-right: 0px; margin-left: 0px; padding: 0px; color: rgb(68, 68, 68);\"> [입학안내] &gt; [예비신입생] 안내 게시판에 게시하고 있습니다.</p> \n<p style=\"font-family: 나눔고딕, NanumGothic, NG, 굴림, Gulim, Helvetica, AppleGothic, sans-serif; margin-top: 0px; margin-right: 0px; margin-left: 0px; padding: 0px; color: rgb(68, 68, 68);\"> &nbsp;</p> \n<p style=\"font-family: 나눔고딕, NanumGothic, NG, 굴림, Gulim, Helvetica, AppleGothic, sans-serif; margin-top: 0px; margin-right: 0px; margin-left: 0px; padding: 0px; color: rgb(68, 68, 68);\"> http://dsm.hs.kr/freshman.brd?shell=/index.shell:12</p>','#**','2016-11-28 00:00:00'),(576,264,0,'','','','1999-01-01 00:00:00'),(577,263,0,'행정실','<p> 2016학년도 프로젝트실구축 및 실습실 리모델링 정보통신공사 소액수의 견적제출 공고입니다.</p> \n<p> &nbsp;</p> \n<p> * 자세한 사항은 첨부파일을 확인해주시기 바랍니다.</p>','#**','2016-11-22 00:00:00'),(578,262,0,'2016학년도프로젝트실구축및실습실리모델링전기공사소액수의견적제출공고','<p> 2016학년도 프로젝트실 구축 및 실습실 리모델링 전기공사 소액수의 견적제출 공고입니다.</p> \n<p> &nbsp;</p> \n<p> * 자세한 사항은 첨부파일을 확인해주시기 바랍니다.</p>','행**','2016-11-22 00:00:00'),(579,261,0,'2016학년도프로젝트실구축및실습실리모델링공사입찰공고','<p> 2016학년도 프로젝트실 구축 및 실습실 리모델링 공사 입찰 공고입니다.</p> \n<p> &nbsp;</p> \n<p> * 자세한 사항은 첨부(공고문)을 참조하여 주시기 바랍니다.</p>','행**','2016-11-17 00:00:00'),(580,259,0,'2017학년도신입생용노트북컴퓨터메모리구입소액수의견적제출공고','<p> 2017학년도 신입생 노트북컴퓨터 메모리 구입 소액수의 견적 제출 공고입니다.</p> \n<p> &nbsp;</p> \n<p> - 자세한 사항은 첨부파일(공고서 및 규격서) 참고바람</p>','행**','2016-11-08 00:00:00'),(581,258,0,'2016학년도전문교과용도서구매소액수의견적제출공고','<p> 2016학년도 전문교과용 도서 구매 소액수의 견적 제출 공고입니다.</p> \n<p> &nbsp;</p> \n<p> - 자세한 사항은 첨부파일 확인 바람</p>','행**','2016-11-08 00:00:00'),(582,257,0,'행정실','<p> 2016학년도 1학년 동계 영어캠프 위탁용역 입찰&nbsp; 재공고합니다.</p> \n<p> -&nbsp;사유: 2인 미만업체 접수로 재공고</p> \n<p> - 자세한 사항은 첨부파일 확인바람</p>','#**','2016-11-07 00:00:00'),(583,256,0,'2017학년도대덕소프트웨어마이스터고등학교교사선발공고','<p> 1. 2017학년도 대덕소프트웨어마이스터고등학교 교사 선발 공고 1부.</p> \n<p> 2. 2017학년도 교사 공모 지원자 제출서류(양식) 1부.</p>','교**','2016-10-27 00:00:00'),(584,255,0,'행정실','<p> 2016학년도 산학협력 홍보물품&nbsp;구입을 위하여 소액수의 견적제출 공고합니다.</p> \n<p> &nbsp;</p> \n<p> * 자세한 사항은 첨부파일 확인</p>','#**','2016-10-27 00:00:00'),(585,254,0,'행정실','<p> 2016학년도 도서실 도서 구매를 위하여&nbsp;첨부와 같이 소액수의 견적 제출 공고합니다.</p> \n<p> &nbsp;</p> \n<p> * 자세한 사항은 첨부파일 확인</p>','#**','2016-10-27 00:00:00'),(586,253,0,'TOPCIT사용자메뉴얼','<p> TOPCIT사용자 메뉴얼입니다.</p> \n<p> &nbsp;</p> \n<p> 응시 학생들은 꼭 읽어보세요</p>','마**','2016-10-26 00:00:00'),(587,252,0,'안소희','<p> 2016년 11월 1일부터~ 25일까지 학부모만족도 조사가 실시됩니다.</p> \n<p> &nbsp;</p> \n<p> <a href=\"http://www.eduro.go.kr에\">www.eduro.go.kr에</a>&nbsp;접속하셔서 참여부탁드립니다.</p> \n<p> &nbsp;</p> \n<p> 자세한 참여방법에 대한 안내는 첨부파일을 참고해 주세요~</p>','#**','2016-10-26 00:00:00'),(588,251,0,'행정실','<p> 2016학년도 1학년 동계 영어캠프 위탁용역 입찰을 첨부와 같이 공고합니다.</p> \n<p> &nbsp;</p> \n<p> - 자세한 사항은 첨부파일(입찰공고서, 과업설명서) 참조바람</p>','#**','2016-10-25 00:00:00'),(589,249,0,'2학기1,2학년중간고사시간표,3학년기말고사시간표및수험생유의사항','','이**','2016-10-06 00:00:00'),(590,248,0,'박정희','<p> 2016년 하반기 인재육성(성취) 장학생 선발계획입니다</p>','#**','2016-10-05 00:00:00'),(591,247,0,'교육지원부','<p style=\"text-align: center;\"> 대덕소프트웨어마이스터고등학교의 청탁금지법 공무수행사인 현황을 공개합니다.</p> \n<p style=\"text-align: center;\"> 관련 : 청탁금지법 제11조 1항</p>','#**','2016-10-05 00:00:00'),(592,246,0,'강지혜','<p> 방과후 아르바이트에 종사하는 청소년을 위한 산재보험제도에 대해서 안내드립니다.</p>','#**','2016-09-28 00:00:00'),(593,245,0,'학교홍보부','<p> <img alt=\"\" src=\"1.JPG?cid=smime_1_57df6a6f\" style=\"width: 597px; height: 862px;\"></p>','#**','2016-09-19 00:00:00'),(594,244,0,'2017학년도호남권입학설명회(2016.9.10.)상세일정안내','<p> <img alt=\"\" src=\"2016-09-10 호남권 입학설명회 시간계획 (공지)001.jpg?cid=smime_1_57d0f2bd\" style=\"width: 650px; height: 920px;\"></p>','학**','2016-09-05 00:00:00'),(595,243,0,'행정실','<p> 정부보조금 부정수급 집중신고기간 운영을 안내합니다.</p> \n<p> &nbsp;</p> \n<p> * 자세한 사항은 첨부파일을 확인하시기 바랍니다.</p> \n<p> &nbsp;</p> \n<p> &nbsp;</p>','#**','2016-09-05 00:00:00'),(596,242,0,'임채홍','<p> 첨부파일 참고하셔서 많은 응모 바랍니다.</p> \n<p> &nbsp;</p> \n<p> &nbsp;</p>','#**','2016-09-02 00:00:00'),(597,241,0,'행정실','','#**','2016-08-31 00:00:00'),(598,239,0,'학교홍보부','<p style=\"text-align: center;\"> <img alt=\"\" src=\"입학설명회 안내(수도권).JPG?cid=smime_1_57c38f6d\" style=\"width: 588px; height: 891px;\"></p>','#**','2016-08-29 00:00:00'),(599,238,0,'교육지원부','<p> 2016학년도 다면평가의 정량평가를 위한 세부 기준안입니다.</p> \n<p> &nbsp;</p> \n<p> 아래파일을 참고하세요~</p>','#**','2016-08-23 00:00:00'),(600,237,0,'마이스터지원인력채용공고(취업지원관)','<p> 자세한 내용은 첨부파일 확인하시기 바랍니다.</p>','특**','2016-08-20 00:00:00'),(601,236,0,'행정실','<p> 2016년 9월 학교급식 식재료 구매 소액수의 견적제출 공고</p> \n<p> &nbsp;</p> \n<p> 붙임: 공고서 5부.</p>','#**','2016-08-19 00:00:00'),(602,235,0,'2017학년도경기권입학설명회(2016.8.20.)상세일정안내','<p style=\"text-align: center;\"> &nbsp;<img alt=\"\" src=\"2016-08-20 경기권 입학설명회 시간계획 (공지)001.jpg?cid=smime_1_57b2706b\" style=\"width: 650px; height: 920px; border-width: 1px; border-style: solid;\"></p>','학**','2016-08-16 00:00:00'),(603,234,0,'교육연구부','<p> 여성가족부에서는 여름방학을 맞이하여 청소년들의 활동역량을 제고할 수 있도록 국립청소년수련원에서 여름캠프를 운영하며, 공공기관 및 청소년수련시설(청소년수련관, 수련원, 문화의 집 등)에서 이루어지는 다양한 체험활동 프로그램 정보를 쉽게 검색할 수 있도록 청소년활동정보서비스(www.youth.go.kr)를 통해 제공하고 있습니다.</p> \n<p> 이와 관련하여 학생 가족들은 여름방학 기간 동안 국립 및 민간수련시설 등에서 이루어지는 활동프로그램에 많은 참가 바랍니다.</p> \n<p> &nbsp;</p> \n<p> 가. 국립청소년수련원 여름캠프 정보 - 한국청소년활동진흥원 홈페이지(www.kywa.or.kr)메인화면에서 소개</p> \n<p> 나. 공공기관, 청소년수련시설?단체 프로그램 정보 - 청소년활동정보서비스(http://www.youth.go.kr)에서 검색 가능</p>','#**','2016-08-03 00:00:00'),(604,233,0,'2016년1학기청렴서한문발송','<p> 2016년 1학기 부패방지 청렴도 향상을 위한 업체 대상 청렴 서한문입니다.</p> \n<p> &nbsp;</p> \n<p> * 별첨 청렴 서한문 참조</p>','행**','2016-08-03 00:00:00'),(605,232,0,'마이스터부','<p> 2016학년도 2학기 본교에 근무할 산학겸임교사를 다음과 같이 초빙하고자 합니다.</p> \n<p> &nbsp;</p> \n<p> 1. 채용 인원 : 산학겸임교사(시간제) 0명(각 교과별 1명)</p> \n<p> 2. 분야 : 정보통신분야(JAVA, C++프로그래밍, 데이터베이스, 리눅스시스템프로그래밍, 임베디드시스템)</p> \n<p> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; * 여러 교과 중복 지원 가능</p> \n<p> 3. 근무기간 : 2016.08. ~ 2017.02. 주당 해당 교과시간(조정 가능)</p> \n<p> 4. 채용방법 : 서류전형-면접(각 단계 합격자 개별통지)</p> \n<p> 5. 원서접수 기간 : 2016.08.03.(수) ~ 2016.08.09.(화) 우편접수 가능</p> \n<p> &nbsp;</p> \n<p> 자세한 내용은 첨부파일을 참조하세요. 많은 관심과 지원 부탁드립니다.</p>','#**','2016-08-02 00:00:00'),(606,231,0,'방과후부','<p> 붙임은 2016학년도 여름방학 방과후학교 강좌별 학생 만족도 조사 결과입니다.</p>','#**','2016-07-29 00:00:00'),(607,229,0,'금일학교장비교체에따른업무등인터넷서비스중단안내','<p> &nbsp;</p> \n<p> 금일 우리학교 대전교육망서비스 장비교체작업에 따른 업무 등 모든 인터넷 서비스 중단안내드립니다.</p> \n<p> &nbsp;</p> \n<p> &nbsp;</p> \n<p style=\"text-align: center;\"> 아래 시간동안 학교홈페이지 등 모든 서비스가 중단되오니, 참고해 주시기 바랍니다.</p> \n<p style=\"text-align: center;\"> (인터넷 관련 모든업무 중단)</p> \n<p> &nbsp;</p> \n<p> &nbsp;</p> \n<p> <strong>- 중단일시 : 2016.7.28.(목) 13:00 ~ 17:00 [작업중 시간이 지연될 수 있습니다] </strong></p> \n<p> <strong>- 중단대상 : 학교의 내·외부 인터넷서비스 전체 (교사망, 학생망)</strong></p> \n<p> <strong>- 작업내용 : L2, L3스위치 교체 및 방화벽교체작업&nbsp;</strong></p> \n<p> &nbsp;</p> \n<p> &nbsp;</p> \n<p> ※ 홈페이지, 업무포털, 나이스, 에듀파인, 업무관리시스템, 대전교육포털, 사이버학습, 방과후학교, 독서교육종합지원 등 모든 인터넷 서비스</p>','관**','2016-07-28 00:00:00'),(608,228,0,'2016학년도여름방학교육기부프로그램포털사이트안내','<p> 2016학년도 여름방학에 진행되는 프로그램 관련&nbsp;안내 드립니다.</p> \n<p> &nbsp;</p> \n<p> 1. 제목: 교육기부포털 안내</p> \n<p> 2. 관련프로그램 안내 사이트: <a href=\"http://www.teachforkorea.go.kr\" target=\"_blank\">www.teachforkorea.go.kr</a></p> \n<p> 3. 내용:</p> \n<p> 이번 여름방학 동안 학생들이 꿈과 끼를 키우고, 생생한 체험을 통해 창의적 인재로 자라날 수 있도록 “2016학년도 여름방학 교육기부 프로그램“이 운영됩니다. 기업, 대학, 공공기관 등 우리 사회의 우수한 교육기부 기관들에서 양질의 교육기부 프로그램이 실시되며, 자유학기제 및 방과후교실(진로‧직업체험, 과학기술, 인문사회, 문화예술, ICT·SW, 스포츠레저, 교과학습 지원) 프로그램 등 알찬 프로그램이 준비되어 있으니, 다양하고 우수한 교육기부 프로그램에 학생들이 참가할 수 있도록 학부모님들의 많은 관심과 활용을 부탁드립니다.</p>','교**','2016-07-19 00:00:00'),(609,227,0,'행정실','<p> 2015학년도 수익자부담경비 운영 정산내역을 공개합니다.</p> \n<p> &nbsp;</p> \n<p> (* 첨부파일 참조 바람)</p>','#**','2016-07-19 00:00:00'),(610,226,0,'교육지원부','','#**','2016-07-15 00:00:00'),(611,225,0,'교육지원부','<p> 대덕소프트웨어마이스터고등학교 공고 제2016-70호</p> \n<p> &nbsp;</p> \n<p style=\"text-align: center\"> <span style=\"font-size: 12pt\">대덕소프트웨어마이스터고등학교 기간제교원 채용 공고</span></p> \n<p> &nbsp;</p> \n<p> 유능하고 성실한 선생님을 아래와 같이 채용하고자 합니다.</p> \n<p> &nbsp;</p> \n<p> 1. 전공 및 인원</p> \n<p> • 국어 : 1명</p> \n<p> • 전문상담 : 1명</p> \n<p> &nbsp;</p> \n<p> 2. 응시자격</p> \n<p> (1) 해당교과 중등 2급 이상 정교사 자격증 소지자</p> \n<p> (2) 근무기간 중 병역의무 이행과 관련 없는 자</p> \n<p> (3) 교육공무원 임용의 결격사유가 없는 자</p> \n<p> &nbsp;</p> \n<p> 3. 제출서류</p> \n<p> (1) 이력서 1부(A4 자유 양식 : 휴대전화번호 및 E-mail 포함, 주민번호 뒷자리 미포함)</p> \n<p> (2) 졸업증명서 1부</p> \n<p> (3) 교원자격증 사본 1부</p> \n<p> (4) 주민등록 초본 1부</p> \n<p> (5) 수업지도안 1부(고등학교 1학년 과정 1차시 분)</p> \n<p> (6) 교육경력증명서 1부(해당자에 한함)</p> \n<p> (7) 통장사본 1부(채용 확정 후 제출)</p> \n<p> (8) 채용신체검사서 1부(채용 확정 후 제출)</p> \n<p> &nbsp;</p> \n<p> 4. 전형방법</p> \n<p> (1) 1차 전형 : 서류 전형(1차 합격자에 한하여 2차 전형일정 해당자 개별통보)</p> \n<p> (2) 2차 전형 : 면접</p> \n<p> &nbsp;</p> \n<p> 5. 제출서류 접수처 및 제출기간</p> \n<p> (1) (34111) 대전광역시 유성구 가정북로 76(장동 23-9)</p> \n<p> 대덕소프트웨어마이스터고등학교 2층 제1교무실 교원업무경감팀 앞</p> \n<p> (2) <strong>2016. 07. 14.(목) ~ 2016. 7. 19.(화) 16시 30분</strong> 까지</p> \n<p> ※ 우편 또는 방문접수 (우편접수는 마감 전 도착 접수된 서류에 한 함)</p> \n<p> &nbsp;</p> \n<p> 6. 최종합격자 통보 : 이력서에 기재된 휴대전화 또는 연락 가능한 전화로 개별 통보함</p> \n<p> &nbsp;</p> \n<p> 7. 계약 기간</p> \n<p> (1) 국어 : <strong>2016년 8월 16일 ~ 2016년 12월 31일</strong></p> \n<p> (2) 전문상담 : <strong>2016년 8월 16일 ~ 2016년 11월 13일</strong></p> \n<p> &nbsp;</p> \n<p> 8. 기 타</p> \n<p> (1) 수업에서 마이스터고등학교 학생을 대상으로 한 토론식 수업, 학생발표 중심수업, 프로젝트 수업 등 학생중심수업을 수행하고, 평가에서 서술형평가를 적극적으로 수행할 수 있는 교사를 우대하고자 합니다.</p> \n<p> (2) 제출된 서류는 일체 반환하지 않으며, 합격이 된 경우라도 결격 사유가 발생 시는 합격을 취소합니다.</p> \n<p> (3) 교육경력증명서은 해당자에 한하며, 미제출자는 득점이 없습니다.</p> \n<p> (4) 기타 문의사항은 대덕소프트웨어마이스터고등학교(042-866-8812, 8822)에 문의하시기 바랍니다.</p> \n<p style=\"margin-left: 33.1pt\"> &nbsp;</p> \n<p style=\"text-align: center; margin-left: 33.1pt\"> 2016. 7. 14.</p> \n<p> &nbsp;</p> \n<p style=\"text-align: center\"> <span style=\"font-size: 12pt\">대 덕 소 프 트 웨 어 마 이 스 터 고 등 학 교 장</span></p>','#**','2016-07-14 00:00:00'),(612,224,0,'행정실','<p> 2016학년도 학생용 노트북 메모리 구매 및 설치 소액수의 견적 제출 공고입니다.</p> \n<p> &nbsp;</p> \n<p> 1. 건명: 2016학년도 학생용 노트북 메모리 구매 및 설치 소액수의 견적 제출 공고</p> \n<p> 2. 품명 및 규격: 노트북 메모리 8G(DDR3L 8G PC3-12800) 160개, 설치 포함</p> \n<p> 3. 기초금액: 6,400,000원</p> \n<p> 4. 입찰방법: 나라장터(G2B)</p> \n<p> 5. 입찰참가자격: 대전광역시 소재업체, 해당 물품&nbsp;생산.제조,판매 업체로서 납품 및 설치 가능업체</p> \n<p> 6. 견적서제출기간: 2016.7.13.(수) 14:00 ~ 2016.7.19.(화) 14:00</p> \n<p> 7. 개찰일시: 2016.7.19.(화) 15:00</p> \n<p> 8. 낙찰자 결정: 제한적최저가(낙찰하한율 90%) 낙찰</p> \n<p> 9. 기타&nbsp;자세한 내용은 별첨 서류 참조바람&nbsp;</p>','#**','2016-07-13 00:00:00'),(613,223,0,'윤경훈','','#**','2016-07-13 00:00:00'),(614,222,0,'임채홍','<p> 여름방학 중 방과후학교 프로그램 운영자(개인위탁)를&nbsp;아래 같이 공모합니다.</p> \n<p> 1. 모집인원: 3명(2학년수학, 프로그래밍 실무 1명, C언어 1명)</p> \n<p> 2. 접수기간: 2016.7.7~2016.7.11 16:30</p> \n<p> 3. 기타 사항: 붙임파일 참조</p>','#**','2016-07-07 00:00:00'),(615,221,0,'2017학년도신입생입학설명회(2016.7.9.)상세일정안내','<p style=\"text-align: center;\"> <img alt=\"\" src=\"입학설명회 안내.JPG?cid=smime_1_577c32ce\" style=\"width: 565px; height: 808px;\"></p>','학**','2016-07-06 00:00:00'),(616,219,0,'행정실','<p> 2016학년도 도서실 도서 구매 소액수의 견적 제출 공고 입니다.</p> \n<p> &nbsp;</p> \n<p> 1. 건명: 2016학년도 도서실 도서 구매 소액수의 견적 제출 공고</p> \n<p> 2. 구매목록: 도서 149종 349권(붙임 도서 목록 참조)</p> \n<p> 3. 기초금액: 5,748,200원</p> \n<p> 4. 입찰방법: G2B 전자입찰</p> \n<p> 5. 입찰참가자격: 사업자등록증상 서적 도.소매업 등록업체로서 주된 영업소의 소재지가 대전광역시에 있는 업체</p> \n<p> 6. 견적서제출기간: 2016.07.06.(수) 10:00 ~2016.07.11.(월) 10:00</p> \n<p> 7. 개찰일시: 2016.7.11.(월) 11:00</p> \n<p> 8. 낙찰자결정: 기초금액&nbsp;90%&nbsp;이상 최저가 낙찰</p> \n<p> 9. 자세한 사항은&nbsp;첨부파일 참고바람&nbsp;</p>','#**','2016-07-05 00:00:00'),(617,218,0,'지영란','','#**','2016-07-04 00:00:00'),(618,217,0,'여름방학중방과후학교프로그램위탁운영자(개인위탁)공모','<p> 붙임파일은 여름방학 중 방과후학교 프로그램 위탁운영자(개인위탁) 공모에 대한 사항입니다.</p> \n<p> 많은 관심 부탁드립니다.</p>','방**','2016-07-04 00:00:00'),(619,216,0,'마이스터부','<p> 2016년 본교 독서인증코디네이터를 채용하고자 합니다.</p> \n<p> 자세한 사항은 붙임 파일을 참고하세요.</p>','#**','2016-06-28 00:00:00'),(620,215,0,'양은정','<p> 1. 기간 : 2016. 6. 29(수) ~ 7. 01(금) (3일간)</p> \n<p> &nbsp;</p>','#**','2016-06-28 00:00:00'),(621,214,0,'학교홍보부','<p> <img alt=\"\" src=\"제2차 입학설명회 시간계획 (공지)001.jpg?cid=smime_1_575fe961\" style=\"width: 650px; height: 919px;\"></p>','#**','2016-06-14 00:00:00'),(622,213,0,'임채홍','<p> 붙임자료는 2016학년도 1학기&nbsp;방과후학교&nbsp;강좌별 학생 만족도 조사 결과입니다.</p>','#**','2016-06-03 00:00:00'),(623,212,0,'교무실무원대체인력채용공고','','행**','2016-06-02 00:00:00');
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
  CONSTRAINT `attachments_ibfk_1` FOREIGN KEY (`no`) REFERENCES `app_content` (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachments`
--

LOCK TABLES `attachments` WRITE;
/*!40000 ALTER TABLE `attachments` DISABLE KEYS */;
INSERT INTO `attachments` VALUES (555,'2017학년도1학기산학겸임교사채용공고.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=ad4d6e86608885bf7f37c8bf8f597c3b'),(557,'2016_수익자정산(방과후교육비).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=7ac4d31f9f5c544366bd97acea53662c'),(558,'2016_수익자정산(1학년영어캠프).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=787d134b1c3836b3a24eabb670f14d9b'),(559,'2016학년도겨울방학방과후학교만족도조사결과.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=9602edda29de981e4ff52bcfb41e7b0f'),(560,'배움터지킴이공고_2017_DSM.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=5d1d9bc9382044c905fb8a34f5ad510c'),(561,'[공고문]2017_기숙사관리위탁용역.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=4a9f666038ca7ee8235881349d7f8e40'),(561,'[과업설명서]2017_기숙사관리위탁용역.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=897291478efa01dce2727b85ab4277ac'),(562,'2016학년도4차방과후학교만족도조사결과.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=a8b1d3662f137f2d6daf3db9c350ba27'),(563,'dsm.jumpup.camp_2016w.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=c55b321fb82006d22f4269761a2e0e7b'),(564,'[공고문]GENIVIOSEK실습기자재.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=1fca3e6c278b4deea4bdf0733a549779'),(564,'[규격서]GENIVIOSEK실습기자재.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=e8ef367302efdb4c8fa70b61692b9581'),(565,'전공동아리활동결과보고서.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=04dbb2af8b446dde63ac41159cba0048'),(566,'[공고문]2017_2학년국외현장체험학습.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=7187e0a647175bd083aab0798e8e9b72'),(566,'[과업설명,특수조건]2017_2학년국외현장체험학습.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=dee3e923e21014bae9243f3dad41db54'),(567,'[공고문]2017_신입생부트캠프.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=dc96c9e7414bc0158d111d6b9790281c'),(567,'[과업설명서]2017_신입생부트캠프.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=4eacb5a8124b2720b1a336b32e1fe5d5'),(568,'[공고문]2016_JUMPUPCANP.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=7f17fb5cb4a207a0936f142ac1cf283b'),(568,'[과업설명서]2016_JUMPUPCAMP.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=2647d48b14cf1f914a12d2155c5946e3'),(569,'2016학년도방과후학교프로그램위탁운영자공모(개인위탁)_겨울방학_자바.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=1dcd1a2cc8c9030d97a5540c8a1070fd'),(570,'2016학년도방과후학교프로그램위탁운영자공모(개인위탁)_겨울방학.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=2810093c9645528e3d1a893cb4d4f6e1'),(571,'공고문.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=fd94e6230d2bc14587b359fa6e677210'),(571,'물품규격서(영상교육기기).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=9e8f76a14ffc16d679b01cc04a435282'),(572,'독서인증코디네이터채용공고(동계방학).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=6eb56a450eb76f1c676860c2066d6e9a'),(574,'20162학기기말고사학과코드및시간표.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=51dcd22d6ddff73064533592f586e588'),(574,'기말고사시간표001.jpg','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=f0dca5c429e826b23c18685e19e265a3'),(577,'공고문_통신.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=890d6f740ffb1ee880312fb46cf89309'),(578,'공고문_전기.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=2d516c769aa997606bd54ca73e05319d'),(579,'공고문.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=e534d8fbf259128e45a61a5c1bec0e72'),(580,'노트북메모리_공고문(2016.11.).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=f6d7f4c271938da6150b9114bf19b9fd'),(580,'노트북메모리_규격서(2016.11.).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=e947e0e19db9628931d3584482eeb0a4'),(581,'도서구매공고문(2016.11.).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=f4ea80181228d1d2bdffe4d8d472ab68'),(581,'도서구매특수조건(2016.11.).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=1f1376a020d3b535583ba1ca89fa296c'),(581,'(전문교과)도서구매목록.xlsx','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=32da0cc878a5282869f0e99b60ab9821'),(582,'[공고문-1]2016_1학년영어캠프위탁용역.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=41b78bebc4dfe2ebb5698afa83306ba1'),(582,'[과업설명서]2016_1학년영어캠프과업설명서.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=51403fd8c6c3f18f784e497f84b9569b'),(583,'2017학년도대덕소프트웨어마이스터고등학교교사선발공고.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=6f1463adcd5965e4f4213cad03fb40dd'),(583,'2017학년도교사공모지원자제출서류(양식).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=10a58c058aa39ab809f48d5831a654a7'),(584,'홍보용품공고문(2016.10.).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=ccf74950b4332548a5c704462675e2a1'),(584,'홍보용품과업지시서(2016.10.).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=c507e0d524520c3d34f166b8daaf86c6'),(585,'도서구매공고문(2016.10.).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=78ef1ea675d293e5da6158ae057f4c05'),(585,'도서구매특수조건(2016.10.).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=496230d21399a019aeb73fa85fef5d5d'),(585,'도서구매목록(2016.10.).xlsx','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=fed6a49c9e2ec816c27fffd7f98fda78'),(586,'[TIMS-2016-O2-4]사용자매뉴얼(ClientKO2.5).pdf','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=76eed0575124be552433af157f362137'),(587,'2016학년도교원능력개발평가학부모만족도조사안내가정통신문(20161026).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=fb3f6f14b35c9361834c95fdf055b29e'),(588,'[공고문]2016_1학년영어캠프위탁용역.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=603f54b570bfbba79161ad9393701fd7'),(588,'[과업설명서]2016_1학년영어캠프과업설명서.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=6d3e49d40318517b3fc13f7cb2ee961f'),(589,'20162학기시간표-홈페이지.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=7bb465aaadc6a090b30856771932e23c'),(589,'고사기간중학생유의사항.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=206ad13f913d6925cda4c84eed45ec36'),(590,'2016년하반기인재육성(성취)장학생선발공고-중,고,대.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=c60fee10339a4a52940498e0af552fdb'),(591,'청탁금지법공무수행사인현황(대덕소프트웨어마이스터고).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=eaa0249afdf61a80b310e74d23218212'),(592,'대전광역시교육청과학직업정보과_일하는청소년을위한산재보험가이드북.pdf','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=7464b8ee0edd1a5e56cd160f5280aa41'),(593,'2016-09-24영남권입학설명회시간계획(공지).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=ef6dca0ca1734e2a0c648a2f6465811d'),(594,'2016-09-10호남권입학설명회시간계획(공지).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=d60a3e47ec78e8bfdc6ee77e6da3c067'),(595,'정부보조금부정수급집중신고기간안내.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=8bee475d2886ab2a29d9437afe4ea752'),(596,'2016학년도방과후학교프로그램위탁운영자공모(개인위탁)_2.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=7be80236dde9dfba421b48dcdae13bb1'),(597,'2017학년도대덕SW마이스터고등학교교복학교주관입찰공고문.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=7810bbbf244fc5676aaf617baff5b056'),(597,'2017학년도대덕소프트웨어마이스터고등학교교복규격서.xlsx','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=6ef98a8904a9b2636387f366a5c596d5'),(598,'2016-09-03수도권입학설명회시간계획(공지).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=1ed7c3b8e3b9579a0691f23f7aea4f15'),(599,'대덕소프트웨어마이스터고등학교-2016년다면평가정량평가세부기준(20160823).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=8d88c2fdd59eb51e4e68729644d217ba'),(600,'마이스터지원인력채용공고(취업지원관).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=241077ecffc15385da9e8e93f6cc4f27'),(601,'공고서(김치류).pdf','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=a869cfeeef6d17e249fc9320a6945c42'),(601,'공고서(냉동수산류).pdf','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=8bb1a37130fe55e9b36712cdb8e635ba'),(601,'공고서(부식류).pdf','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=570766befc54f4aa24ca40d6a660ad69'),(601,'공고서(육류).pdf','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=a1522465131c6bebd7045bafd8950b45'),(601,'공고서(잡곡류).pdf','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=0e169d50c407f3f1e88b441765c8469b'),(602,'2016-08-20경기권입학설명회시간계획(공지).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=2be5a3d38c3510a5f9f4c0ebeab55d89'),(604,'2016청렴서한문(업체).pdf','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=9bc61a2ee2799d9da51ac92b54b0dd7c'),(605,'2016학년도2학기산학겸임교사채용공고.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=1bd65722817888b64a37f8ce3950adcd'),(606,'2016학년도여름방학방과후학교학생만족도조사결과.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=c68fda70b9603dcbe8b6933b9cd5974c'),(609,'2015_수익자정산공개.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=22ce6340a1c38c8d35f28ba7c1e27851'),(610,'2016학년도하계방학중학교운영계획(수정).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=99160c9db1443d22b09e109c56be73fb'),(612,'공고문.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=fb1d4d27b3778bdce95b35a5291dfeec'),(612,'구매규격서.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=6937f6c089b39e47a775ba8d7aece172'),(613,'학생생활지도제규정_대덕소프트웨어마이스터고(2016년도).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=8036a6599d72af0352f5ad40df59ab9b'),(614,'2016학년도방과후학교프로그램위탁운영자공모(개인위탁)_여름방학_1.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=5493f4615dad0b2d3f88942a095531ec'),(616,'도서구매입찰공고문(2016.7.).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=da7caac475142481eb74951005dcbb66'),(616,'도서구입목록(2016.7.).xlsx','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=4eb5ba226db169e2cadb90a6c34dacbe'),(616,'도서구입과업지시서(2016.7.).hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=c4f750eb94c89edfceb0984243cc166d'),(617,'7월공고(부식류).pdf','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=4cdeb3075c2633a6839731d161b816fb'),(617,'7월공고(육류).pdf','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=62370e38699bb9fab96ce65d4dab0fd2'),(617,'7월공고(냉동수산류).pdf','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=20d21eaf51001976d34bf82dc7c262b1'),(618,'2016학년도방과후학교프로그램위탁운영자공모(개인위탁)_여름방학.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=7629784b9f4894abe8c2b681032086b6'),(619,'2016독서인증코디네이터채용공고.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=ea91c29a21d83bc5604ba6d186ddadb4'),(620,'시간표001.jpg','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=0aa2df8badccee95924459fc35d7f735'),(622,'2016학년도1학기방과후학교학생만족도조사결과.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=24a2c58c58ac1fc80bc961706731e73c'),(622,'2016학년도1학기방과후학교학생만족도조사결과.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=f6bdf4c3330717ef4a5c0775f34a8431'),(623,'교무실무원(2016-6월)대체인력채용공고.hwp','http://dsmhs.djsch.kr/boardCnts/view.do/boardCnts/fileDown.do?m=0201&s=dsmhs&fileSeq=decb4964bc421d7c0a5e6a8c126e1a00');
/*!40000 ALTER TABLE `attachments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extension_apply`
--

DROP TABLE IF EXISTS `extension_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `extension_apply` (
  `class` int(1) NOT NULL,
  `seat` int(2) NOT NULL,
  `name` varchar(20) NOT NULL,
  `id` varchar(20) NOT NULL,
  PRIMARY KEY (`class`,`seat`),
  KEY `id` (`id`),
  CONSTRAINT `extension_apply_ibfk_1` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extension_apply`
--

LOCK TABLES `extension_apply` WRITE;
/*!40000 ALTER TABLE `extension_apply` DISABLE KEYS */;
/*!40000 ALTER TABLE `extension_apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extension_map`
--

DROP TABLE IF EXISTS `extension_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `extension_map` (
  `room` int(3) NOT NULL,
  `name` varchar(20) NOT NULL,
  `map` text NOT NULL,
  PRIMARY KEY (`room`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extension_map`
--

LOCK TABLES `extension_map` WRITE;
/*!40000 ALTER TABLE `extension_map` DISABLE KEYS */;
INSERT INTO `extension_map` VALUES (1,'가온실','[ [0, 1, 1, 0, 1, 1, 0], [0, 1, 1, 0, 1, 1, 0], [0, 1, 1, 0, 1, 1, 0], [0, 1, 1, 0, 1, 1, 0], [0, 1, 1, 0, 1, 1, 0] ]'),(2,'나온실','[ [0, 1, 0, 1, 1, 0, 1, 0], [0, 1, 0, 1, 1, 0, 1, 0], [0, 1, 0, 1, 1, 0, 1, 0], [0, 1, 0, 0, 0, 0, 1, 0], [0, 1, 1, 1, 1, 1, 1, 0] ]'),(3,'다온실','[ [0, 1, 1, 1, 1, 1, 0], [0, 0, 0, 0, 0, 0, 0],[0, 1, 1, 1, 1, 1, 0], [0, 0, 0, 0, 0, 0, 0],[0, 1, 1, 1, 1, 1, 0], [0, 0, 0, 0, 0, 0, 0] ]'),(4,'라온실','[ [0, 1, 1, 0, 0, 1, 1, 0], [0, 1, 1, 0, 0, 1, 1, 0], [0, 0, 0, 1, 1, 0, 0, 0], [0, 0, 0, 1, 1, 0, 0, 0], [0, 1, 1, 0, 0, 1, 1, 0], [0, 1, 1, 0, 0, 1, 1, 0] ]');
/*!40000 ALTER TABLE `extension_map` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facility_report`
--

LOCK TABLES `facility_report` WRITE;
/*!40000 ALTER TABLE `facility_report` DISABLE KEYS */;
INSERT INTO `facility_report` VALUES (8,'테스트 고장','더러움',308,'2017-02-23 15:28:12','',NULL,NULL),(9,'테스트2','없음',308,'2017-03-02 15:47:41','test',NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faq`
--

LOCK TABLES `faq` WRITE;
/*!40000 ALTER TABLE `faq` DISABLE KEYS */;
INSERT INTO `faq` VALUES (1,'왜 파라미터가 안감?','PostMan 갓');
/*!40000 ALTER TABLE `faq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goingout_apply`
--

DROP TABLE IF EXISTS `goingout_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goingout_apply` (
  `id` varchar(20) NOT NULL,
  `date` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goingout_apply`
--

LOCK TABLES `goingout_apply` WRITE;
/*!40000 ALTER TABLE `goingout_apply` DISABLE KEYS */;
INSERT INTO `goingout_apply` VALUES ('test',0),('test',1);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal`
--

LOCK TABLES `meal` WRITE;
/*!40000 ALTER TABLE `meal` DISABLE KEYS */;
INSERT INTO `meal` VALUES ('2016-03-01','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2016-03-02','[\"현미밥\",\"미역국\",\"파래돌김자반\",\"떡갈비데리야끼조림\",\"사각어묵볶음\",\"배추김치\"]','[\"현미밥\",\"버섯들깨손수제비국\",\"도토리묵/달래장\",\"봄동겉절이\",\"삼겹살매운구이\",\"보쌈김치9.13.\"]','[\"간장달걀볶음밥\",\"맑은우동국\",\"크림치즈찰깨스틱30\",\"석박지\",\"가스토갈릭프라이/케찹\"]','[\"5\",\"6\",\"9\",\"13\",\"2\",\"10\",\"12\",\"1\"]','[\"6\",\"9\",\"13\",\"5\",\"10\"]','[\"1\",\"5\",\"6\",\"9\",\"13\",\"2\",\"12\"]'),('2016-03-03','[\"사과\",\"계란토스트/딸기잼\",\"흰우유\",\"시리얼(첵스초코)\",\"황도샐러드\"]','[\"보리밥\",\"감자탕\",\"당면어묵볶음\",\"야채계란말이\",\"배추김치\",\"화인쿨파인애플맛180\"]','[\"보리밥\",\"김치국\",\"오리훈제\",\"부추양배추겨자소스무침\",\"생깻잎지\",\"야쿠르트\"]','[\"1\",\"2\",\"5\",\"6\",\"13\",\"11\"]','[\"5\",\"6\",\"9\",\"10\",\"13\",\"1\",\"8\"]','[\"5\",\"9\",\"13\",\"2\"]'),('2016-03-04','[\"흑미밥\",\"들깨감자국\",\"브로콜리초회\",\"돼지훈제굴소스볶음\",\"온두부/양념장\",\"깍두기\"]','[\"베이컨볶음밥\",\"유부맑은국\",\"씨리얼핫도그/케찹\",\"배추겉절이\",\"단무지무침\",\"떠먹는요구르트\"]','[\"흑미밥\",\"실파계란국\",\"생땅콩조림\",\"오징어떡볶음\",\"북경식꿔바로우\",\"배추김치\"]','[\"9\",\"13\",\"5\",\"6\",\"10\"]','[\"2\",\"5\",\"6\",\"10\",\"13\",\"1\",\"12\",\"9\"]','[\"1\",\"5\",\"6\",\"9\",\"13\",\"4\",\"2\",\"10\",\"12\"]'),('2016-03-05','[\"흰밥(쌀밥)\",\"바지락칼국수\",\"크런치통살치킨너겟/머스타드소1.2.5.6.13.\",\"우리밀꽈배기\",\"배추김치\",\"파인애플\"]','[\"잡곡밥\",\"콩나물무채국\",\"배추겉절이\",\"목살부추무침\",\"김치전1.5.6.9.13.\",\"포도쥬스185\"]','[\"급식이 없습니다.\"]','[\"5\",\"6\",\"13\",\"1\",\"2\",\"9\"]','[\"5\",\"6\",\"9\",\"13\",\"10\"]','[\"급식이 없습니다.\"]'),('2016-03-06','[\"치킨마요덮밥\",\"맑은장국\",\"스마일감자/케찹\",\"배추김치\",\"대추토마토\",\"감귤쥬스\"]','[\"잡곡밥\",\"육개장\",\"참떡갈비/소스1.2.5.6.10.12.13.\",\"알감자버터구이2.5.13.\",\"깍두기\",\"망고쥬스\"]','[\"급식이 없습니다.\"]','[\"1\",\"2\",\"5\",\"6\",\"9\",\"10\",\"13\",\"12\"]','[\"5\",\"6\",\"13\",\"9\"]','[\"급식이 없습니다.\"]'),('2016-03-07','[\"기장밥\",\"어묵국\",\"갈치무조림\",\"단무지무침\",\"안심비엔나조림\",\"배추김치\"]','[\"기장밥\",\"돈등뼈시래기조림\",\"냉이동태찌개\",\"비빔야채+만두\",\"깍두기\"]','[\"기장밥\",\"통배추된장국\",\"닭오븐구이\",\"배추겉절이\",\"탕평채\",\"고사리볶음5.6.\"]','[\"1\",\"5\",\"6\",\"9\",\"13\",\"2\",\"10\",\"12\"]','[\"5\",\"6\",\"9\",\"10\",\"13\",\"1\",\"4\"]','[\"5\",\"6\",\"9\",\"13\",\"1\"]'),('2016-03-08','[\"쇠고기양송이죽\",\"꼬마밤식빵35\",\"어린잎채소샐러드\",\"깍두기\",\"바나나\",\"사과쥬스185\"]','[\"율무밥\",\"순대국밥\",\"무생채9.13.\",\"취나물무침\",\"돈육데리야끼오븐구이\"]','[\"율무밥\",\"햄모듬찌개\",\"비름나물무침\",\"배추겉절이\",\"고기산적새송이볶음\",\"사천깐풍기\"]','[\"1\",\"2\",\"5\",\"6\",\"13\",\"9\"]','[\"6\",\"9\",\"10\",\"13\",\"5\"]','[\"2\",\"5\",\"6\",\"9\",\"10\",\"13\",\"1\",\"12\"]'),('2016-03-09','[\"차조밥\",\"떡국1.13.\",\"돼지갈비간장조림\",\"구멍어묵고추장볶음\",\"배추김치\",\"도시락김\"]','[\"차조밥\",\"근대된장국5.6.9.13.\",\"닭도리탕\",\"하루나무침\",\"김치볶음5.6.9.13.\",\"생깻잎지\"]','[\"흰밥(쌀밥)\",\"우동\",\"깍두기\",\"메이플피칸파이\",\"복숭아맛쥬시쿨180\",\"콘야채샐러드\"]','[\"5\",\"6\",\"10\",\"13\",\"1\",\"9\"]','[\"5\",\"6\",\"13\",\"9\"]','[\"1\",\"5\",\"6\",\"9\",\"13\",\"2\",\"11\"]'),('2016-03-10','[\"잡곡밥\",\"물만두국\",\"콩나물무침5.\",\"찹쌀콩멸치볶음\",\"찹스테이크파인조림\",\"백김치\"]','[\"잡곡밥\",\"들깨미역국\",\"무초절이\",\"오리로스구이\",\"배추속겉절이\",\"상추쌈/쌈장5.6.13.\"]','[\"잡곡밥\",\"소고기당면국\",\"솔방울오징어간장조림\",\"층층이등심돈까스/소스\",\"석박지\",\"찹쌀콩튀김\"]','[\"5\",\"1\",\"6\",\"10\",\"13\",\"2\",\"12\",\"9\"]','[\"5\",\"6\",\"9\",\"13\"]','[\"5\",\"6\",\"8\",\"9\",\"13\",\"1\",\"2\",\"10\",\"12\"]'),('2016-03-11','[\"꼬마치즈번40\",\"청양초잡채월병만두/초간장\",\"포도(레드글로브)\",\"흰우유\",\"시리얼(콘푸레이크)\"]','[\"비빔밥\",\"맑은장국\",\"달걀후라이\",\"오징어바/케찹\",\"깍두기\",\"비피더스\"]','[\"흰밥(쌀밥)\",\"건새우달걀탕\",\"참나물무침\",\"돈육김치볶음\",\"안심스팸구이/케찹\",\"백김치\"]','[\"1\",\"2\",\"5\",\"6\",\"13\",\"10\",\"12\"]','[\"1\",\"5\",\"6\",\"10\",\"13\",\"9\",\"12\",\"2\"]','[\"1\",\"5\",\"6\",\"9\",\"13\",\"10\",\"12\"]'),('2016-03-12','[\"흰밥(쌀밥)\",\"손수제비\",\"고구맛탕\",\"돌돌고기떡말이116\",\"배추김치\",\"요플레골드키위85\"]','[\"잡곡밥\",\"유부김치국\",\"배추겉절이\",\"느타리버섯잡채볶음\",\"치킨또띠아\",\"트로피칼후르츠컵쥬스\"]','[\"급식이 없습니다.\"]','[\"5\",\"6\",\"9\",\"13\",\"10\",\"2\"]','[\"5\",\"9\",\"13\",\"1\",\"6\",\"8\",\"2\",\"10\",\"12\"]','[\"급식이 없습니다.\"]'),('2016-03-13','[\"흰밥(쌀밥)\",\"수제오이피클\",\"스파게티\",\"감자후레이크링고로케\",\"수제베이컨피자\",\"딸기퓨레쥬스\"]','[\"잡곡밥\",\"감자고추장찌개\",\"발사믹야채샐러드\",\"오리간장볶음\",\"배추김치\",\"배(생과)\"]','[\"급식이 없습니다.\"]','[\"13\",\"1\",\"2\",\"5\",\"6\",\"10\",\"12\"]','[\"5\",\"6\",\"9\",\"10\",\"13\",\"1\"]','[\"급식이 없습니다.\"]'),('2016-03-14','[\"검정콩밥5.\",\"두부된장국\",\"수제왕만두/양념장\",\"명품동그랑땡전/케찹\",\"바삭김말이\",\"생깻잎지\"]','[\"검정콩밥5.\",\"시래기된장국\",\"배추겉절이\",\"골뱅이야채무침\",\"간장파닭\",\"마카롱쵸코맛15\"]','[\"검정콩밥5.\",\"소고기무매운국\",\"까르보나라미트볼\",\"미니방울순대볶음\",\"배추김치\",\"야쿠르트\"]','[\"5\",\"6\",\"9\",\"13\",\"1\",\"10\",\"2\",\"12\"]','[\"5\",\"6\",\"9\",\"13\",\"1\",\"2\",\"12\"]','[\"5\",\"6\",\"13\",\"1\",\"2\",\"10\",\"12\",\"9\"]'),('2016-03-15','[\"브로콜리스프\",\"우리밀허니브레드60\",\"맛감자구이/케찹\",\"깍두기\",\"사과\",\"흰우유\"]','[\"수수밥\",\"어묵매운탕\",\"오이지무침13.\",\"오징어숙회/초고추장\",\"메밀김치전병120\",\"열무김치\"]','[\"수수밥\",\"돼지갈비김치찌개\",\"배추겉절이\",\"들깨도라지볶음\",\"치킨링팝콘강정\",\"미니오코노미야끼\"]','[\"2\",\"5\",\"6\",\"13\",\"1\",\"12\",\"9\"]','[\"1\",\"5\",\"6\",\"9\",\"13\",\"2\",\"3\"]','[\"5\",\"6\",\"9\",\"10\",\"13\",\"1\",\"2\",\"12\",\"8\"]'),('2016-03-16','[\"통밀밥6.\",\"북어계란국\",\"실채도라지초무침\",\"스테이크볼조림\",\"스크램블에그\",\"배추김치\"]','[\"통밀밥6.\",\"콩나물김치국\",\"근대나물무침\",\"숙주오리훈제굴소스볶음\",\"납작군만두33\",\"깍두기\"]','[\"카레라이스\",\"핫도그/케찹\",\"배추김치\",\"오렌지쥬스185\",\"수수부꾸미\"]','[\"1\",\"5\",\"13\",\"6\",\"2\",\"10\",\"12\",\"9\"]','[\"5\",\"6\",\"9\",\"13\",\"1\",\"10\"]','[\"2\",\"5\",\"6\",\"10\",\"13\",\"1\",\"12\",\"9\"]'),('2016-03-17','[\"현미밥\",\"아귀탕\",\"연두부찜/양념장\",\"배추겉절이\",\"단무지무침\",\"돈육표고버섯볶음\"]','[\"현미밥\",\"낙지연포탕\",\"바베큐폭찹\",\"깻순나물볶음\",\"콩나물파채무침\",\"배추김치\"]','[\"현미밥\",\"해물볶음우동\",\"유부우동국\",\"배추겉절이\",\"명엽채볶음\",\"치킨치즈까스/타르타르소스\"]','[\"5\",\"6\",\"13\",\"9\",\"10\"]','[\"5\",\"6\",\"9\",\"13\",\"1\",\"2\",\"10\",\"12\"]','[\"5\",\"6\",\"9\",\"12\",\"13\",\"1\"]'),('2016-03-18','[\"식빵구이/딸기쨈\",\"토마토양상추샐러드/딸기드레싱\",\"딸기\",\"흰우유\",\"시리얼(코코볼)\"]','[\"김치알밥\",\"맑은장국\",\"웨지양념반달감자\",\"깍두기\",\"야쿠르트\",\"초코시럽생크림와플\"]','[\"흰밥(쌀밥)\",\"등뼈김치찌개\",\"우엉어묵볶음\",\"기름떡볶이\",\"백김치\",\"이탈리안스프링롤/케찹\"]','[\"1\",\"2\",\"5\",\"6\",\"13\",\"12\"]','[\"2\",\"5\",\"6\",\"9\",\"10\",\"13\",\"1\"]','[\"5\",\"6\",\"9\",\"10\",\"13\",\"1\",\"4\",\"2\",\"12\"]'),('2016-03-19','[\"잡곡밥\",\"냉이국5.6.13.\",\"무초절이\",\"삼겹살구이\",\"상추쌈/쌈장5.6.13.\",\"황도\"]','[\"잡곡밥\",\"미소국5.6.13.\",\"치즈닭갈비\",\"부추전/양념간장\",\"배추김치\",\"딸기\"]','[\"급식이 없습니다.\"]','[\"5\",\"13\",\"10\",\"11\"]','[\"5\",\"2\",\"6\",\"13\",\"1\",\"9\"]','[\"급식이 없습니다.\"]'),('2016-03-20','[\"잡곡밥\",\"단배추된장국\",\"돼지고기산적\",\"오징어링튀김\",\"깍두기\",\"야쿠르트\"]','[\"흰밥(쌀밥)\",\"장각삼계탕\",\"크링클컷감자/케찹\",\"석박지\",\"애플망고쥬스\",\"키위\"]','[\"급식이 없습니다.\"]','[\"5\",\"6\",\"9\",\"13\",\"1\",\"10\",\"12\",\"2\"]','[\"13\",\"1\",\"2\",\"5\",\"6\",\"12\",\"9\"]','[\"급식이 없습니다.\"]'),('2016-03-21','[\"보리밥\",\"새알심만두국\",\"납작당면매콤볶음\",\"치킨텐더/머스타드소스\",\"배추김치\",\"도시락김\"]','[\"보리밥\",\"부대찌개\",\"배추겉절이\",\"호두멸치볶음\",\"베이컨시금치프리타타\",\"우리밀라비올리30\"]','[\"보리밥\",\"건새우시금치국\",\"돈수육5.6.10.13.\",\"무말랭이무침\",\"보쌈김치9.13.\",\"상추쌈/쌈장5.6.13.\"]','[\"1\",\"5\",\"6\",\"9\",\"10\",\"13\",\"8\"]','[\"1\",\"2\",\"5\",\"6\",\"9\",\"10\",\"13\",\"12\"]','[\"5\",\"6\",\"9\",\"13\"]'),('2016-03-22','[\"대게살야채죽\",\"모닝빵/딸기잼\",\"깍두기\",\"망고야채샐러드/흑임자드레싱\",\"오렌지1/2\",\"흰우유\"]','[\"흑미밥\",\"북어감자국\",\"달래오이무침5.6.13.\",\"수제돈까스/소스\",\"바삭김말이강정\",\"배추김치\"]','[\"흑미밥\",\"닭배추국\",\"배추겉절이\",\"오징어가라아게(스틱)/소스1.5.6.13.\",\"알감자버터구이2.13.\",\"피자치즈왕납작군만두\"]','[\"5\",\"6\",\"8\",\"1\",\"2\",\"13\",\"9\",\"12\"]','[\"5\",\"6\",\"9\",\"13\",\"1\",\"2\",\"10\",\"12\"]','[\"5\",\"6\",\"13\",\"9\",\"1\",\"2\",\"10\",\"12\"]'),('2016-03-23','[\"기장밥\",\"참치김치찌개5.6.9.13.\",\"진미채간장볶음\",\"파래돌김자반\",\"떡갈비스틱구이21\",\"열무김치\"]','[\"흰밥(쌀밥)\",\"설렁탕&amp;소면1.5.6.13.\",\"유린기\",\"석박지\",\"배추속겉절이\",\"포도맛요플레\"]','[\"야채김가루볶음밥\",\"맑은우동국\",\"배추김치\",\"고르곤졸라사각피자50\",\"망고에빠진코코100\"]','[\"5\",\"6\",\"13\",\"1\",\"10\",\"12\",\"9\"]','[\"1\",\"5\",\"6\",\"12\",\"13\",\"9\",\"2\"]','[\"2\",\"5\",\"6\",\"10\",\"13\",\"1\",\"9\",\"12\"]'),('2016-03-24','[\"율무밥\",\"조랭이떡국\",\"닭정육간장조림\",\"시금치나물5.6.\",\"사각쥐포볶음\",\"배추김치\"]','[\"율무밥\",\"순두부찌개\",\"수제미트볼조림\",\"순대/소금6.10.\",\"미역줄기볶음\",\"깍두기\"]','[\"율무밥\",\"콩가루배추국\",\"양배추쌈/쌈장5.6.13.\",\"무생채9.13.\",\"오리불고기\",\"쥬시쿨자두맛180\"]','[\"1\",\"13\",\"5\",\"6\",\"9\"]','[\"5\",\"9\",\"10\",\"13\",\"1\",\"2\",\"6\",\"12\"]','[\"5\",\"6\",\"9\",\"13\",\"2\"]'),('2016-03-25','[\"흰밥(쌀밥)\",\"소고기미역국\",\"메추리알비엔나조림\",\"삼치조림\",\"배추겉절이\",\"골파김가루볶음\"]','[\"잔치국수\",\"배추겉절이\",\"꿀빵50\",\"연어핫바60\",\"야쿠르트\"]','[\"흰밥(쌀밥)\",\"김치황태국\",\"생땅콩조림\",\"배추겉절이\",\"우리팜라운드햄전/케찹\",\"떡갈비두부강정\"]','[\"5\",\"6\",\"9\",\"13\",\"1\",\"2\",\"10\"]','[\"1\",\"5\",\"6\",\"9\",\"13\",\"2\",\"4\"]','[\"5\",\"9\",\"13\",\"4\",\"6\",\"1\",\"2\",\"10\",\"12\"]'),('2016-03-26','[\"흰밥(쌀밥)\",\"알탕\",\"신당동떡볶이\",\"피자치즈롤까스90/소스\",\"배추김치\",\"딸기석류쥬스100\"]','[\"잡곡밥\",\"팽이버섯무채국5.6.13.\",\"오징어두루치기\",\"후라이드치킨\",\"깍두기\",\"멜론\"]','[\"급식이 없습니다.\"]','[\"5\",\"6\",\"9\",\"13\",\"1\",\"2\",\"8\",\"10\",\"12\"]','[\"5\",\"6\",\"13\",\"1\",\"2\",\"9\"]','[\"급식이 없습니다.\"]'),('2016-03-27','[\"잡곡밥\",\"청경채된장국\",\"등갈비찜\",\"치즈계란말이\",\"깍두기\",\"사과\"]','[\"잡곡밥\",\"감자달걀국\",\"배추겉절이\",\"진미채버터구이\",\"불폭탄소스돈육구이\",\"단호박부꾸미\"]','[\"급식이 없습니다.\"]','[\"5\",\"6\",\"9\",\"13\",\"10\",\"12\",\"1\",\"2\"]','[\"5\",\"1\",\"6\",\"9\",\"13\",\"2\",\"10\",\"12\"]','[\"급식이 없습니다.\"]'),('2016-03-28','[\"차조밥\",\"김치국\",\"떡사태찜\",\"햄감자채볶음\",\"깍두기\",\"오이스틱/초고추장5.6.13.\"]','[\"흰밥(쌀밥)\",\"왕만두떡국\",\"깐쇼새우\",\"석박지\",\"배추속겉절이\",\"피크닉복숭아맛200\"]','[\"차조밥\",\"아욱된장국\",\"돈갈비매운조림\",\"배추겉절이\",\"열무나물무침\",\"옥수수콘버터구이\"]','[\"5\",\"9\",\"13\",\"6\",\"10\",\"2\"]','[\"1\",\"5\",\"6\",\"10\",\"13\",\"9\",\"12\"]','[\"5\",\"6\",\"9\",\"13\",\"10\",\"12\",\"1\",\"2\"]'),('2016-03-29','[\"표고소고기영양죽\",\"배추김치\",\"파인애플컵젤리75\",\"초코찰링도너츠40\",\"흰우유\",\"꽃맛살샐러드\"]','[\"잡곡밥\",\"부추팽이달걀탕\",\"상추쑥갓무침\",\"수제파인애플탕수육\",\"허니버터양념감자\",\"배추김치\"]','[\"잡곡밥\",\"소고기해장국\",\"감식초꼬들장아찌무침\",\"두부코코넛치즈돈까스/소스\",\"깍두기\",\"타코야끼\"]','[\"13\",\"9\",\"2\",\"5\",\"1\",\"6\"]','[\"5\",\"1\",\"6\",\"9\",\"13\",\"2\",\"10\",\"12\"]','[\"5\",\"6\",\"9\",\"13\",\"1\",\"2\",\"10\",\"12\"]'),('2016-03-30','[\"검정콩밥5.\",\"콩나물국\",\"배추겉절이\",\"치즈떡소고기간장볶음\",\"크림소스알감자만두조림\",\"야쿠르트\"]','[\"흰밥(쌀밥)\",\"돼지국밥\",\"파래돌김자반\",\"풋마늘대초무침\",\"수제소세지케찹소스볶음\",\"깍두기\"]','[\"검정콩밥5.\",\"달래된장국\",\"닭봉바베큐소스조림\",\"숙주미나리무침\",\"당면군만두/케찹\",\"배추김치\"]','[\"5\",\"6\",\"9\",\"13\",\"1\",\"2\",\"10\"]','[\"9\",\"10\",\"13\",\"5\",\"6\",\"1\",\"2\",\"12\"]','[\"5\",\"6\",\"13\",\"12\",\"1\",\"2\",\"10\",\"9\"]'),('2016-03-31','[\"열대파파야칩핫도그50/케찹\",\"파프리카샐러드\",\"우리밀초코머핀80\",\"흰우유\",\"시리얼(아몬드후레이크)\"]','[\"수수밥\",\"대구매운탕\",\"문어다리땅콩볶음\",\"김치볶음\",\"청경채돈육두반장볶음\",\"생깻잎지\"]','[\"참치비빔밥/초고추장\",\"맑은장국\",\"닭꼬치70\",\"깍두기\",\"델몬트드링크190\"]','[\"1\",\"2\",\"5\",\"6\",\"10\",\"12\",\"13\"]','[\"5\",\"6\",\"9\",\"13\",\"4\",\"10\",\"12\"]','[\"5\",\"6\",\"13\",\"9\"]'),('2017-01-01','[]','[]','[]','[]','[]','[]'),('2017-01-02','[\"흰밥(쌀밥)\",\"소고기미역국\",\"호두멸치볶음\",\"떡갈비두부강정\",\"깍두기\",\"요구르트\"]','[\"흰밥(쌀밥)\",\"감자탕\",\"수제미트볼조림\",\"무생채9.13.\",\"열무나물무침\",\"사과\"]','[\"흰밥(쌀밥)\",\"어묵김치국\",\"수제무쌈\",\"오리로스구이\",\"생깻잎지\",\"사과당근플리또130\"]','[\"5\",\"6\",\"9\",\"13\",\"16\",\"14\",\"2\",\"10\",\"12\"]','[\"5\",\"6\",\"9\",\"10\",\"13\",\"1\",\"2\",\"12\"]','[\"1\",\"5\",\"6\",\"9\",\"13\"]'),('2017-01-03','[\"우리밀레드벨벳케익32\",\"망고야채샐러드/흑임자드레싱\",\"포도(레드글로브)\",\"흰우유\",\"시리얼(코코볼)\"]','[\"기장밥\",\"돈육김치찌개\",\"고등어무조림\",\"배추겉절이\",\"수제돈까스/소스\"]','[\"기장밥\",\"유부우동국\",\"등갈비김치찜\",\"부추장떡\",\"석박지\",\"블루베리맛엔요\"]','[\"1\",\"2\",\"5\",\"6\",\"13\"]','[\"5\",\"6\",\"9\",\"10\",\"13\",\"7\",\"1\",\"2\",\"12\"]','[\"5\",\"6\",\"13\",\"9\",\"10\",\"1\",\"2\"]'),('2017-01-04','[\"흑미밥\",\"김치국\",\"돼지갈비떡조림\",\"햄감자채볶음\",\"깍두기\",\"귤\"]','[\"흑미밥\",\"부추팽이달걀탕\",\"닭도리탕\",\"시금치나물\",\"군김/양념장\",\"총각김치9.13.\"]','[\"삼겹살김치볶음밥\",\"맑은장국\",\"새우또띠아\",\"깍두기\",\"망고쥬스\",\"함초녹차김(3)\"]','[\"9\",\"13\",\"5\",\"6\",\"10\",\"2\"]','[\"1\",\"5\",\"6\",\"9\",\"13\",\"15\"]','[\"5\",\"6\",\"9\",\"10\",\"13\",\"1\",\"2\",\"12\"]'),('2017-01-05','[\"보리밥\",\"소고기무국\",\"돈등뼈김치찜\",\"배추겉절이\",\"야채스크램블에그\"]','[\"보리밥\",\"순대국밥\",\"돼지고기산적\",\"알감자버터구이2.5.13.\",\"석박지\"]','[\"보리밥\",\"굴새우젓국찌개\",\"양배추쌈/쌈장5.6.13.\",\"무초절이\",\"돼지훈제구이\",\"배추김치\"]','[\"5\",\"6\",\"9\",\"13\",\"16\",\"10\",\"1\",\"2\"]','[\"6\",\"9\",\"10\",\"13\",\"5\"]','[\"5\",\"6\",\"9\",\"13\",\"10\"]'),('2017-01-06','[\"현미밥\",\"감자수제비국\",\"파래돌김자반\",\"실채도라지초무침\",\"팽이버섯돈육볶음\",\"배추김치\"]','[\"현미밥\",\"단배추된장국\",\"닭정육간장조림\",\"어묵김치볶음\",\"깍두기\",\"파인애플컵젤리75\"]','[]','[\"5\",\"6\",\"13\",\"10\",\"9\"]','[\"5\",\"6\",\"9\",\"13\",\"15\",\"1\",\"2\"]','[]'),('2017-01-07','[\"꼬마밤식빵35\",\"달걀후라이와베이컨구이\",\"바나나\",\"흰우유\",\"시리얼(첵스초코)\"]','[\"잡곡밥\",\"들깨가루무국\",\"깐쇼새우\",\"깍두기\",\"화인쿨파인애플맛180\",\"치킨피자\"]','[\"잡곡밥\",\"시래기된장국\",\"무생채9.13.\",\"삼겹살구이\",\"딸기\",\"상추쌈/쌈장5.6.13.\"]','[\"1\",\"2\",\"5\",\"6\",\"13\",\"10\"]','[\"5\",\"9\",\"13\",\"1\",\"6\",\"12\",\"2\",\"10\"]','[\"5\",\"6\",\"9\",\"13\",\"10\"]'),('2017-01-08','[\"우리밀초코치즈케익32\",\"크런치통살치킨너겟/머스타드소1.2.5.6.13.15.\",\"멜론\",\"흰우유\",\"시리얼(코코팝스)5.6.13.\"]','[\"잡곡밥\",\"청경채된장국\",\"소고기낙지볶음\",\"애호박전/양념장1.2.5.6.13.\",\"배추김치\",\"귤\"]','[\"잡곡밥\",\"설렁탕&amp;소면1.5.6.13.16.\",\"치즈스틱25/케찹\",\"석박지\",\"한라봉\",\"애플턴오버파이40\"]','[\"1\",\"2\",\"5\",\"13\"]','[\"5\",\"6\",\"9\",\"13\",\"10\",\"16\"]','[\"5\",\"2\",\"12\",\"13\",\"9\",\"1\",\"6\"]'),('2017-01-09','[\"차조밥\",\"감자호박된장국\",\"비엔나푸실리볶음\",\"진미채버터구이\",\"깍두기\",\"사과당근맛엔요\"]','[\"차조밥\",\"떡국1.9.13.\",\"닭봉바베큐소스조림\",\"오이새콤무침\",\"배추김치\"]','[\"차조밥\",\"콩나물국\",\"목살부추무침\",\"수제무쌈\",\"생깻잎지\",\"포도한모금쥬스100\"]','[\"5\",\"6\",\"9\",\"13\",\"1\",\"2\",\"10\",\"12\",\"17\"]','[\"5\",\"6\",\"12\",\"13\",\"15\",\"9\"]','[\"5\",\"6\",\"9\",\"13\",\"10\"]'),('2017-01-10','[\"갈릭식빵러스크\",\"맛감자구이/케찹\",\"토마토야채샐러드/크랜베리드레2.5.12.13.\",\"사과\",\"흰우유\",\"시리얼(아몬드후레이크)\"]','[\"율무밥\",\"참치찌개\",\"비름나물무침\",\"돈육주물럭\",\"고구마허니버터칩\",\"총각김치9.13.\"]','[\"율무밥\",\"소고기배추국\",\"배추겉절이\",\"우렁야채무침5.6.13.\",\"수제돈육강정\",\"딸기퓨레쥬스\"]','[\"1\",\"2\",\"5\",\"6\",\"13\",\"12\"]','[\"5\",\"6\",\"9\",\"13\",\"10\",\"2\"]','[\"5\",\"6\",\"9\",\"13\",\"16\",\"1\",\"2\",\"10\",\"12\"]'),('2017-01-11','[\"녹차밥\",\"순두부달걀탕\",\"삼겹살김치볶음\",\"온두부/양념장\",\"깍두기\",\"함초녹차김(3)\"]','[\"녹차밥\",\"낙지연포탕\",\"봄동겉절이\",\"떡볶이\",\"배추김치\",\"장각로스트치킨오븐구이\"]','[\"짜장밥\",\"상추겉절이\",\"깍두기\",\"수제베이컨피자\",\"오렌지쥬스185\"]','[\"1\",\"5\",\"6\",\"9\",\"13\",\"10\"]','[\"5\",\"6\",\"9\",\"13\",\"16\",\"1\",\"15\"]','[\"5\",\"6\",\"10\",\"13\",\"9\",\"1\",\"2\",\"12\"]'),('2017-01-12','[\"어린잎채소샐러드\",\"햄콘치즈토스트\",\"바나나\",\"흰우유\",\"시리얼(콘푸레이크)\"]','[\"검정콩밥5.\",\"오리들깨탕\",\"등갈비데리아끼오븐구이\",\"배추겉절이\",\"오징어도라지초무침5.6.13.17.\"]','[\"검정콩밥5.\",\"다시마감자국\",\"쫄면야채무침\",\"간장파닭\",\"백김치\",\"피크닉복숭아맛200\"]','[\"1\",\"5\",\"12\",\"2\",\"6\",\"10\",\"13\"]','[\"5\",\"6\",\"9\",\"13\",\"10\"]','[\"5\",\"6\",\"13\",\"1\",\"2\",\"15\",\"9\"]'),('2017-01-13','[\"수수밥\",\"소고기당면국\",\"후랑크야채케찹볶음\",\"스크램블에그\",\"총각김치9.13.\"]','[\"우동\",\"배추김치\",\"메이플피칸파이\",\"바나나우유\",\"파인애플\"]','[]','[\"5\",\"6\",\"8\",\"9\",\"13\",\"16\",\"2\",\"10\",\"12\",\"1\"]','[\"1\",\"5\",\"6\",\"9\",\"13\",\"2\"]','[]'),('2017-01-14','[]','[]','[]','[]','[]','[]'),('2017-01-15','[]','[]','[]','[]','[]','[]'),('2017-01-16','[]','[]','[]','[]','[]','[]'),('2017-01-17','[]','[]','[]','[]','[]','[]'),('2017-01-18','[]','[]','[]','[]','[]','[]'),('2017-01-19','[]','[]','[]','[]','[]','[]'),('2017-01-20','[]','[]','[]','[]','[]','[]'),('2017-01-21','[]','[]','[]','[]','[]','[]'),('2017-01-22','[]','[]','[]','[]','[]','[]'),('2017-01-23','[]','[]','[]','[]','[]','[]'),('2017-01-24','[]','[]','[]','[]','[]','[]'),('2017-01-25','[]','[]','[]','[]','[]','[]'),('2017-01-26','[]','[]','[]','[]','[]','[]'),('2017-01-27','[]','[]','[]','[]','[]','[]'),('2017-01-28','[]','[]','[]','[]','[]','[]'),('2017-01-29','[]','[]','[]','[]','[]','[]'),('2017-01-30','[]','[]','[]','[]','[]','[]'),('2017-01-31','[]','[]','[]','[]','[]','[]'),('2017-02-01','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-02-02','[\"흰밥(쌀밥)\",\"바지락미역국\",\"떡사태찜\",\"브로콜리초회\",\"깍두기\",\"요구르트\"]','[\"흰밥(쌀밥)\",\"돼지갈비김치찌개\",\"당면계란말이\",\"갈치구이\",\"총각김치9.13.\"]','[\"간장달걀볶음밥\",\"맑은장국\",\"배추김치\",\"포도맛요플레\",\"우리밀붕어빵50\"]','[\"5\",\"6\",\"9\",\"13\",\"10\",\"2\"]','[\"5\",\"6\",\"9\",\"10\",\"13\",\"1\",\"8\",\"2\"]','[\"1\",\"5\",\"6\",\"9\",\"13\",\"2\"]'),('2017-02-03','[\"잡곡밥\",\"유부김치국\",\"비엔나푸실리볶음\",\"시금치나물\",\"배추겉절이\",\"함초녹차김(3)\"]','[\"흰밥(쌀밥)\",\"잔치국수\",\"배추겉절이\",\"상추쑥갓무침\",\"화인쿨파인애플맛180\",\"피타브레드주머니빵\"]','[\"잡곡밥\",\"등뼈김치찌개\",\"소시지프리타타\",\"깍두기\",\"타코야끼\"]','[\"5\",\"9\",\"13\",\"1\",\"2\",\"6\",\"10\",\"12\"]','[\"1\",\"5\",\"6\",\"9\",\"13\",\"2\",\"10\"]','[\"5\",\"6\",\"9\",\"10\",\"13\",\"1\",\"2\"]'),('2017-02-04','[\"우리밀고구마롤케익30\",\"닭불꼬치70\",\"키위\",\"흰우유\",\"시리얼(첵스초코)\"]','[\"흰밥(쌀밥)\",\"우렁된장찌개\",\"수제무쌈\",\"삼겹살구이\",\"자몽쥬스100\",\"깻잎쌈/쌈장5.6.13.\"]','[\"잡곡밥\",\"부대찌개&amp;\",\"순대/소금6.10.\",\"배추겉절이\",\"신당동떡볶이\",\"고구마야채튀김/초간장\"]','[\"1\",\"2\",\"5\",\"6\",\"13\",\"12\"]','[\"5\",\"6\",\"13\",\"10\"]','[\"5\",\"라면사리\",\"9\",\"13\",\"1\",\"2\",\"6\"]'),('2017-02-05','[\"우리쌀초코롤케익35\",\"달걀후라이와베이컨구이\",\"플레인요구르트85\",\"흰우유\",\"시리얼(아몬드후레이크)\"]','[\"해물볶음우동\",\"우리밀허니브레드50\",\"맑은우동국\",\"깍두기\",\"파인애플\",\"참다래쥬스100\"]','[\"잡곡밥\",\"통배추된장국\",\"수육\",\"무생채9.13.\",\"상큼한능금쥬스140\",\"상추쌈/쌈장5.6.13.\"]','[\"1\",\"2\",\"5\",\"6\",\"13\",\"10\"]','[\"5\",\"6\",\"9\",\"12\",\"13\",\"1\",\"2\"]','[\"5\",\"6\",\"9\",\"13\",\"10\"]'),('2017-02-06','[\"검정콩밥5.\",\"소고기무국\",\"닭봉데리야끼오븐구이\",\"도토리묵/양념장\",\"깍두기\",\"요구르트\"]','[\"검정콩밥5.\",\"중국식계란탕\",\"돼지갈비고추장조림\",\"오이부추무침\",\"배추김치\"]','[\"검정콩밥5.\",\"손수제비감자국\",\"골뱅이야채무침\",\"수제까르보나라미트볼\",\"석박지\",\"뽀로로요구르트\"]','[\"5\",\"6\",\"9\",\"13\",\"2\"]','[\"1\",\"8\",\"9\",\"13\",\"2\",\"5\",\"6\",\"10\"]','[\"5\",\"6\",\"9\",\"13\",\"2\",\"10\"]'),('2017-02-07','[\"갈릭식빵러스크\",\"어린잎채소샐러드\",\"사과\",\"흰우유\",\"시리얼(콘푸레이크)\"]','[\"현미밥\",\"순두부해물탕\",\"무생채9.13.\",\"열무나물무침\",\"깐풍기\"]','[\"김치볶음밥\",\"콩나물무채국\",\"배추겉절이\",\"달걀후라이\",\"웨지양념반달감자\",\"사과쥬스185\"]','[\"1\",\"2\",\"5\",\"6\",\"13\",\"12\"]','[\"1\",\"5\",\"6\",\"13\",\"2\",\"12\"]','[\"2\",\"5\",\"6\",\"9\",\"10\",\"13\",\"1\"]'),('2017-02-08','[\"보리밥\",\"떡국1.9.13.\",\"콩나물잡채\",\"참치스크램블에그\",\"배추김치\"]','[\"참치마요볶음밥\",\"콩나물김치국\",\"깍두기\",\"수제식혜\",\"수제베이컨피자\"]','[\"급식이 없습니다.\"]','[\"1\",\"5\",\"6\",\"8\",\"13\",\"9\"]','[\"1\",\"5\",\"6\",\"13\",\"9\",\"2\",\"10\",\"12\"]','[\"급식이 없습니다.\"]'),('2017-02-09','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-02-10','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-02-11','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-02-12','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-02-13','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-02-14','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-02-15','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-02-16','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-02-17','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-02-18','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-02-19','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-02-20','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-02-21','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-02-22','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-02-23','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-02-24','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-02-25','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-02-26','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-02-27','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-02-28','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-03-01','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-03-02','[\"소고기미역국\",\"비엔나브로콜리굴소스볶음\",\"야채계란말이\",\"깍두기\",\"감귤쥬스\",\"흰밥(쌀밥)\"]','[\"감자탕\",\"수제미트볼조림\",\"봄동겉절이\",\"배추김치\",\"파인애플\",\"흰밥(쌀밥)\"]','[\"콩나물국\",\"김치전1.2.5.6.9.13.\",\"총각김치9.13.\",\"장각로스트치킨오븐구이\",\"흰밥(쌀밥)\",\"딸기\"]','[\"5\",\"6\",\"9\",\"13\",\"16\",\"2\",\"10\",\"18\",\"1\"]','[\"5\",\"6\",\"9\",\"10\",\"13\",\"1\",\"2\",\"12\"]','[\"5\",\"6\",\"9\",\"13\",\"15\"]'),('2017-03-03','[\"달래된장국\",\"근대나물무침\",\"숙주오리훈제굴소스볶음\",\"올방개묵김가루무침\",\"배추김치\",\"흑미밥\"]','[\"돼지국밥\",\"메추리알떡조림\",\"돈육김치볶음\",\"총각김치9.13.\",\"블루베리맛엔요\",\"흑미밥\"]','[\"실파계란국\",\"돈갈비매운조림\",\"고구마튀김\",\"배추김치\",\"뽀로로요구르트\",\"흑미밥\"]','[\"5\",\"6\",\"13\",\"18\",\"9\"]','[\"9\",\"10\",\"13\",\"1\",\"5\",\"6\",\"2\"]','[\"1\",\"5\",\"6\",\"9\",\"13\",\"10\",\"12\",\"2\"]'),('2017-03-04','[\"우리쌀초코롤케익35\",\"가스토갈릭프라이/케찹\",\"흰우유\",\"시리얼(첵스초코)\",\"멜론\"]','[\"잡곡밥\",\"버섯들깨수제비국\",\"수제무쌈\",\"삼겹살구이\",\"얼려먹는야쿠르트110\",\"상추쌈/쌈장5.6.13.\"]','[\"잡곡밥\",\"육개장\",\"수제미트볼떡강정\",\"순대/소금6.10.\",\"깍두기\",\"배(생과)\"]','[\"1\",\"2\",\"5\",\"6\",\"13\",\"12\"]','[\"5\",\"6\",\"9\",\"13\",\"10\",\"2\"]','[\"5\",\"6\",\"9\",\"13\",\"2\",\"10\",\"12\"]'),('2017-03-05','[\"우리쌀카스테라30\",\"치킨텐더/머스타드소스\",\"흰우유\",\"시리얼(코코볼)\",\"꽃맛살샐러드\"]','[\"잡곡밥\",\"시금치된장국\",\"돼지갈비떡조림\",\"배추겉절이\",\"애호박전/양념장1.2.5.6.13.\",\"복숭아아이스티\"]','[\"잡곡밥\",\"다시마감자국\",\"닭봉데리야끼오븐구이\",\"우리밀라비올리30\",\"깍두기\",\"화인쿨자두맛180\"]','[\"1\",\"2\",\"5\",\"6\",\"13\",\"15\"]','[\"5\",\"6\",\"9\",\"13\",\"10\",\"11\"]','[\"5\",\"6\",\"13\",\"15\",\"1\",\"10\",\"12\",\"9\",\"2\"]'),('2017-03-06','[\"참치찌개\",\"오리고추장불고기\",\"햄감자채볶음\",\"깍두기\",\"요구르트\",\"현미밥\"]','[\"시래기된장국\",\"돈사태고추장조림\",\"오이부추무침\",\"배추속겉절이\",\"키위\",\"현미밥\"]','[\"순두부달걀탕\",\"탕평채\",\"뼈없는닭갈비\",\"배추김치\",\"현미밥\"]','[\"5\",\"6\",\"9\",\"13\",\"2\",\"10\"]','[\"5\",\"6\",\"9\",\"13\",\"10\"]','[\"1\",\"5\",\"6\",\"9\",\"13\",\"15\"]'),('2017-03-07','[\"맛감자구이/케찹\",\"핫도그샌드위치\",\"흰우유\",\"시리얼(콘푸레이크)\",\"사과\"]','[\"냉이동태찌개\",\"후라이드치킨\",\"배추김치\",\"아이러브요거트토핑100\",\"기장밥\",\"숙주미나리무침\"]','[\"통배추된장국\",\"돼지고기산적\",\"깍두기\",\"사과당근맛엔요\",\"아삭이고추양파쌈장무침\",\"기장밥\"]','[\"5\",\"12\",\"13\",\"1\",\"2\",\"6\",\"10\"]','[\"5\",\"6\",\"13\",\"1\",\"2\",\"15\",\"9\"]','[\"5\",\"6\",\"9\",\"13\",\"10\"]'),('2017-03-08','[\"김치국\",\"안동찜닭\",\"오징어도라지초무침5.6.13.17.\",\"배추김치\",\"수수밥\",\"멜론\"]','[\"우렁된장찌개\",\"콩나물잡채\",\"군김/양념장\",\"치즈달걀스파게티소스오븐구이\",\"총각김치9.13.\",\"수수밥\"]','[\"북어계란국\",\"돼지훈제감자바베큐오븐구이\",\"도토리묵/달래장\",\"배추김치\",\"수수밥\"]','[\"9\",\"13\",\"5\",\"6\",\"15\"]','[\"5\",\"6\",\"13\",\"1\",\"8\",\"2\",\"10\"]','[\"1\",\"5\",\"9\",\"13\",\"6\",\"10\",\"12\"]'),('2017-03-09','[\"소고기무국\",\"알감자조림\",\"떡갈비단호박간장조림\",\"배추겉절이\",\"참진미채고추장볶음\",\"녹차밥\"]','[\"오리들깨탕\",\"골뱅이야채무침\",\"수제소세지케찹소스볶음\",\"석박지\",\"복숭아맛마시는샐러드쥬스100\",\"녹차밥\"]','[\"짜장밥\",\"깍두기\",\"메이플피칸파이\",\"사과쥬스185\",\"바나나\"]','[\"5\",\"6\",\"9\",\"13\",\"16\",\"2\",\"10\",\"17\"]','[\"5\",\"6\",\"9\",\"13\",\"2\",\"10\",\"12\",\"11\"]','[\"5\",\"6\",\"10\",\"13\",\"9\",\"1\",\"2\"]'),('2017-03-10','[\"닭곰탕\",\"바베큐폭찹\",\"브로콜리초회\",\"배추김치\",\"요구르트\",\"흰밥(쌀밥)\"]','[\"잔치국수\",\"배추겉절이\",\"상추쑥갓무침\",\"단호박샌드위치\",\"오미자화채\",\"흰밥(쌀밥)\"]','[\"검정콩밥5.\",\"홍합무국\",\"양배추쌈/쌈장5.6.13.\",\"무초절이\",\"돈육주물럭\"]','[\"1\",\"9\",\"13\",\"15\",\"5\",\"6\",\"10\",\"12\",\"2\"]','[\"1\",\"5\",\"6\",\"9\",\"13\",\"2\",\"11\"]','[\"9\",\"13\",\"18\",\"5\",\"6\",\"10\"]'),('2017-03-11','[\"우리밀허니브레드50\",\"치킨너겟/소스\",\"흰우유\",\"시리얼(그래놀라)5.6.13.\",\"딸기\"]','[\"김치알밥\",\"유부맑은국\",\"레몬향생선까스/소스\",\"깍두기\",\"딸기맛요플레85\",\"포도(레드글로브)\"]','[\"잡곡밥\",\"부추팽이된장국\",\"신당동떡볶이\",\"수제돈육강정\",\"배추김치\",\"자몽쥬스100\"]','[\"1\",\"2\",\"5\",\"6\",\"13\",\"12\",\"15\"]','[\"5\",\"6\",\"9\",\"10\",\"13\",\"1\",\"2\"]','[\"5\",\"6\",\"9\",\"13\",\"1\",\"2\",\"10\",\"12\"]'),('2017-03-12','[\"돌돌고기떡말이100\",\"대추토마토\",\"치즈케익40\",\"흰우유\",\"시리얼(콘푸레이크)\"]','[\"잡곡밥\",\"두부김치국\",\"등갈비데리아끼오븐구이\",\"쫄면야채무침\",\"백김치\",\"망고감귤젤리\"]','[\"잡곡밥\",\"미역국\",\"고등어무조림\",\"닭정육카레떡볶음\",\"총각김치9.13.\",\"사과당근플리또130\"]','[\"10\",\"13\",\"12\",\"1\",\"2\",\"5\",\"6\"]','[\"5\",\"9\",\"13\",\"6\",\"10\",\"2\"]','[\"5\",\"6\",\"9\",\"13\",\"7\",\"2\",\"15\"]'),('2017-03-13','[\"들깨가루무국\",\"우엉어묵볶음\",\"삼겹살김치볶음\",\"배추김치\",\"요구르트\",\"보리밥\"]','[\"올갱이아욱국\",\"돈수육5.6.10.13.\",\"무생채9.13.\",\"상추쌈/쌈장5.6.13.\",\"보리밥\",\"오렌지1/2\"]','[\"애호박고추장찌개\",\"단무지무침\",\"명엽채볶음\",\"불폭탄소스돈육구이\",\"배추김치\",\"보리밥\"]','[\"9\",\"13\",\"1\",\"5\",\"6\",\"10\",\"2\"]','[\"5\",\"6\",\"13\"]','[\"1\",\"5\",\"6\",\"9\",\"13\",\"10\",\"12\"]'),('2017-03-14','[\"표고소고기영양죽\",\"깍두기\",\"망고야채샐러드/흑임자드레싱\",\"고구마파이31.5\",\"델몬트드링크오렌지맛190\"]','[\"오징어무국\",\"맛살오이겨자냉채\",\"골파김가루볶음\",\"수제꿔바로우탕수육\",\"깍두기\",\"율무밥\"]','[\"조랭이떡국\",\"김치볶음\",\"수제돈까스/소스\",\"갈치구이\",\"율무밥\"]','[\"9\",\"13\",\"16\",\"1\",\"5\",\"2\",\"6\"]','[\"5\",\"6\",\"9\",\"13\",\"17\",\"1\",\"10\"]','[\"1\",\"9\",\"13\",\"5\",\"6\",\"2\",\"10\",\"12\"]'),('2017-03-15','[\"콩나물매운국\",\"부추무침\",\"느타리버섯볶음\",\"목살구이\",\"석박지\",\"흰밥(쌀밥)\"]','[\"청경채된장국\",\"실채도라지초무침\",\"탄두리치킨\",\"배추김치\",\"흰밥(쌀밥)\",\"파인애플\"]','[\"우동\",\"배추김치\",\"피타브레드주머니빵\",\"흰밥(쌀밥)\",\"사과\",\"망고쥬스\"]','[\"5\",\"6\",\"9\",\"13\",\"10\"]','[\"5\",\"6\",\"9\",\"13\",\"2\",\"15\"]','[\"1\",\"5\",\"6\",\"9\",\"13\",\"2\",\"10\"]'),('2017-03-16','[\"두부된장국\",\"메추리알비엔나조림\",\"콩나물파채무침\",\"배추김치\",\"함초녹차김(3)\",\"차조밥\"]','[\"대구매운탕\",\"우렁야채무침5.6.13.\",\"철판순대볶음\",\"당면군만두/케찹\",\"열무김치\",\"차조밥\"]','[\"도토리묵국\",\"수제무쌈\",\"오리로스구이\",\"요구르트\",\"깻잎쌈/쌈장5.6.13.\",\"차조밥\"]','[\"5\",\"6\",\"9\",\"13\",\"1\",\"2\",\"10\"]','[\"5\",\"6\",\"9\",\"13\",\"10\",\"1\",\"12\"]','[\"5\",\"9\",\"13\",\"2\"]'),('2017-03-17','[\"아귀탕\",\"배추겉절이\",\"부추양배추겨자소스무침\",\"뽀로로요구르트\",\"오리훈제\",\"흰밥(쌀밥)\"]','[\"참치마요볶음밥\",\"미소국5.6.9.13.\",\"고구마치즈매콤닭불구이100\",\"배추김치\",\"수제식혜\",\"키위\"]','[\"급식이 없습니다.\"]','[\"5\",\"6\",\"9\",\"13\",\"2\"]','[\"1\",\"5\",\"6\",\"13\",\"2\",\"10\",\"15\",\"9\"]','[\"급식이 없습니다.\"]'),('2017-03-18','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-03-19','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-03-20','[\"어묵매운탕\",\"돈등뼈김치찜\",\"감자베이컨오븐구이\",\"깍두기\",\"흑미밥\"]','[\"북어콩나물해장국\",\"상추겉절이\",\"마파두부\",\"간장파닭\",\"배추김치\",\"흑미밥\"]','[\"들깨감자국\",\"떡사태찜\",\"배추겉절이\",\"수제오이피클\",\"청포도맛플리또130\",\"흑미밥\"]','[\"1\",\"5\",\"6\",\"9\",\"13\",\"10\"]','[\"5\",\"6\",\"9\",\"13\",\"10\",\"12\",\"1\",\"2\",\"15\"]','[\"9\",\"13\",\"5\",\"6\",\"10\",\"2\"]'),('2017-03-21','[\"갈릭식빵러스크\",\"흰우유\",\"과일푸실리샐러드\",\"시리얼(아몬드후레이크)\",\"오렌지1/2\"]','[\"부대찌개\",\"해물콩나물찜\",\"비름나물무침\",\"배추겉절이\",\"야채스크램블에그\",\"흰밥(쌀밥)\"]','[\"맑은무채국\",\"닭도리탕\",\"꽃빵&amp;고추잡채\",\"깍두기\",\"흰밥(쌀밥)\"]','[\"1\",\"2\",\"5\",\"6\",\"13\",\"12\"]','[\"1\",\"2\",\"5\",\"6\",\"9\",\"10\",\"13\",\"17\",\"18\"]','[\"9\",\"13\",\"5\",\"6\",\"15\",\"10\"]'),('2017-03-22','[\"유부김치국\",\"팽이버섯돈육볶음\",\"삼치구이\",\"깍두기\",\"차조밥\"]','[\"낙지연포탕\",\"무생채9.13.\",\"하루나무침\",\"돼지훈제데리야끼오븐구이\",\"바나나우유\",\"차조밥\"]','[\"순대국밥\",\"몽떡볶이\",\"배추김치\",\"대추토마토\",\"타코야끼\",\"차조밥\"]','[\"5\",\"9\",\"13\",\"6\",\"10\"]','[\"5\",\"6\",\"9\",\"13\",\"16\",\"10\",\"2\"]','[\"6\",\"9\",\"10\",\"13\",\"1\",\"5\",\"12\"]'),('2017-03-23','[\"감자옹심이만두국\",\"등갈비김치찜\",\"배추겉절이\",\"견과류멸치볶음\",\"요구르트\",\"현미밥\"]','[\"콩비지찌개\",\"닭정육간장조림\",\"양배추오이초무침\",\"열무나물무침\",\"총각김치9.13.\",\"현미밥\"]','[\"간장달걀볶음밥\",\"김치황태국\",\"새우또띠아\",\"석박지\",\"포도쥬스185\",\"바나나\"]','[\"1\",\"5\",\"6\",\"10\",\"13\",\"9\",\"4\",\"2\"]','[\"5\",\"9\",\"10\",\"13\",\"6\",\"15\"]','[\"1\",\"5\",\"6\",\"9\",\"13\",\"2\",\"10\",\"12\"]'),('2017-03-24','[\"단배추된장국\",\"파래돌김자반\",\"온두부/양념장\",\"소시지프리타타\",\"배추김치\",\"녹차밥\"]','[\"소고기콩나물밥\",\"맑은장국\",\"배추겉절이\",\"수제허니버터감자칩\",\"수제베이컨피자\",\"비피더스\"]','[\"순두부찌개\",\"새송이브로콜리볶음\",\"수제돈육강정\",\"깍두기\",\"뽀로로요구르트\",\"녹차밥\"]','[\"5\",\"6\",\"9\",\"13\",\"1\",\"2\",\"10\"]','[\"5\",\"6\",\"16\",\"9\",\"13\",\"2\",\"1\",\"10\",\"12\"]','[\"5\",\"9\",\"10\",\"13\",\"6\",\"1\",\"2\",\"12\"]'),('2017-03-25','[\"우리밀초코치즈케익32\",\"크런치통살치킨너겟/머스타드소1.2.5.6.13.15.\",\"흰우유\",\"시리얼(콘푸레이크)\",\"파인애플\"]','[\"잡곡밥\",\"참치고추장찌개\",\"애호박부추전/양념장\",\"떡갈비두부강정\",\"배추김치\",\"상큼한능금쥬스140\"]','[\"잡곡밥\",\"팽이미소국\",\"오리훈제간장조림\",\"웨지양념반달감자\",\"석박지\",\"포도맛요플레\"]','[\"1\",\"2\",\"5\",\"13\",\"6\"]','[\"5\",\"6\",\"9\",\"13\",\"1\",\"2\",\"10\",\"12\"]','[\"5\",\"6\",\"9\",\"13\",\"2\"]'),('2017-03-26','[\"모닝빵/딸기잼12\",\"어린잎채소샐러드\",\"달걀후라이와베이컨구이\",\"흰우유\",\"시리얼(첵스초코)\"]','[\"설렁탕&amp;소면1.5.6.13.16.\",\"크링클컷감자/케찹\",\"깐쇼새우\",\"석박지\",\"배(생과)\",\"흰밥(쌀밥)\"]','[\"카레라이스\",\"닭불꼬치70\",\"배추김치\",\"초코시럽생크림와플\",\"델몬트드링크파인애플190\"]','[\"1\",\"2\",\"5\",\"6\",\"13\",\"12\",\"10\"]','[\"1\",\"2\",\"5\",\"12\",\"13\",\"6\",\"9\"]','[\"2\",\"5\",\"6\",\"9\",\"10\",\"12\",\"13\",\"15\",\"1\"]'),('2017-03-27','[\"감자수제비국\",\"닭봉바베큐소스조림\",\"배추김치\",\"단호박부꾸미86\",\"함초녹차김(3)\",\"흰밥(쌀밥)\"]','[\"돈육김치찌개\",\"오이새콤무침\",\"팽이미역줄기볶음\",\"오리간장볶음\",\"총각김치9.13.\",\"흰밥(쌀밥)\"]','[\"돈등뼈우거지국\",\"후랑크야채케찹볶음\",\"궁중떡볶이\",\"배추김치\",\"바나나\",\"흰밥(쌀밥)\"]','[\"5\",\"6\",\"13\",\"12\",\"15\",\"9\",\"1\",\"2\"]','[\"5\",\"6\",\"9\",\"10\",\"13\"]','[\"5\",\"6\",\"9\",\"10\",\"13\",\"2\",\"12\"]'),('2017-03-28','[\"햄콘치즈토스트\",\"토마토야채샐러드/크랜베리드레2.5.12.13.\",\"흰우유\",\"시리얼(코코볼)\",\"사과\"]','[\"감자쑥된장국\",\"콩나물무침5.\",\"애호박새우젓볶음9.13.\",\"양파닭\",\"배추김치\",\"흑미밥\"]','[\"북어감자국\",\"등갈비바베큐오븐구이\",\"배추겉절이\",\"두부구이와달래양념장\",\"대추토마토\",\"흑미밥\"]','[\"1\",\"2\",\"5\",\"6\",\"10\",\"12\",\"13\"]','[\"5\",\"6\",\"9\",\"13\",\"1\",\"2\",\"15\"]','[\"5\",\"6\",\"9\",\"13\",\"10\",\"12\"]'),('2017-03-29','[\"유부우동국\",\"돼지갈비김치찜\",\"명태코다리조림\",\"참나물무침\",\"백김치\",\"보리밥\"]','[\"감자고추장찌개\",\"비빔야채+만두\",\"임연수구이5.6.\",\"아몬드또띠아칩\",\"석박지\",\"보리밥\"]','[\"브로콜리스프\",\"우리쌀초코롤케익35\",\"스파게티\",\"깍두기\",\"복숭아아이스티\",\"멜론\"]','[\"5\",\"6\",\"13\",\"9\",\"10\"]','[\"5\",\"6\",\"9\",\"13\",\"1\",\"4\",\"10\",\"2\"]','[\"2\",\"5\",\"6\",\"13\",\"1\",\"10\",\"12\",\"9\",\"11\"]'),('2017-03-30','[\"떡국1.9.13.\",\"미트볼모듬바베큐\",\"숙주김가루무침\",\"옥수수콘샐러드\",\"배추김치\",\"현미밥\"]','[\"해물볶음우동\",\"소고기무매운국\",\"취나물무침\",\"두부돈전오븐구이\",\"열무김치\",\"현미밥\"]','[\"닭개장\",\"낙지볶음과소면\",\"치즈스틱25/케찹\",\"배추김치\",\"단배추나물무침\",\"현미밥\"]','[\"1\",\"2\",\"5\",\"6\",\"10\",\"12\",\"13\",\"9\"]','[\"5\",\"6\",\"9\",\"12\",\"13\",\"17\",\"18\",\"16\",\"1\",\"2\",\"10\"]','[\"5\",\"6\",\"9\",\"13\",\"15\",\"2\",\"12\"]'),('2017-03-31','[\"콩나물김치국\",\"돈육치즈떡짜장볶음\",\"구멍어묵브로콜리볶음\",\"석박지\",\"요구르트\",\"차조밥\"]','[\"삼겹살김치볶음밥\",\"맑은장국\",\"달걀후라이\",\"크림치즈찰깨스틱30\",\"깍두기\",\"수제식혜\",\"포도(레드글로브)\"]','[\"건새우아욱국\",\"쑥갓두부깨장무침\",\"오삼불고기\",\"진미채버터구이\",\"배추김치\",\"차조밥\"]','[\"5\",\"6\",\"9\",\"13\",\"2\",\"10\",\"1\"]','[\"5\",\"6\",\"9\",\"10\",\"13\",\"1\",\"2\"]','[\"5\",\"6\",\"9\",\"13\",\"10\",\"12\",\"2\",\"17\"]'),('2017-04-01','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-04-02','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-04-03','[\"흰밥(쌀밥)\",\"소고기미역국\",\"삼겹살김치볶음\",\"갈치구이\",\"깍두기\",\"뽀로로요구르트\"]','[\"흰밥(쌀밥)\",\"시금치된장국\",\"오징어도라지초무침5.6.13.17.\",\"느타리버섯볶음\",\"간장파닭\",\"배추김치\"]','[\"흰밥(쌀밥)\",\"유부김치국\",\"무초절이\",\"돼지훈제구이\",\"깍두기\",\"상추쌈/쌈장5.6.13.\"]','[\"5\",\"6\",\"9\",\"13\",\"16\",\"10\",\"2\"]','[\"5\",\"6\",\"9\",\"13\",\"1\",\"2\",\"15\"]','[\"5\",\"9\",\"13\",\"10\"]'),('2017-04-04','[\"스마일감자/케찹\",\"메이플피칸파이\",\"사과\",\"밀감푸딩\",\"흰우유\",\"시리얼(혼합)\"]','[\"흑미밥\",\"돈등뼈시래기조림\",\"대구매운탕\",\"꽃빵&amp;고추잡채\",\"배추김치\",\"요구르트\"]','[\"흑미밥\",\"들깨감자국\",\"닭정육간장조림\",\"도토리묵/양념장\",\"석박지\",\"쥬시쿨자두맛180\"]','[\"5\",\"12\",\"13\",\"1\",\"2\",\"6\"]','[\"5\",\"6\",\"10\",\"13\",\"9\",\"2\"]','[\"9\",\"13\",\"5\",\"6\",\"15\",\"2\"]'),('2017-04-05','[\"잡곡밥\",\"콩나물국\",\"돈사태메추리알조림\",\"야채스크램블에그\",\"배추김치\",\"바나나\"]','[\"잡곡밥\",\"열무된장국\",\"돈갈비매운조림\",\"애호박부추전/양념장\",\"깍두기\",\"파인애플\"]','[\"야채김가루볶음밥\",\"김치국\",\"배추겉절이\",\"오징어핫바문어맛90\",\"사과\",\"델몬트드링크파인애플190\"]','[\"5\",\"6\",\"9\",\"13\",\"1\",\"10\",\"2\"]','[\"5\",\"6\",\"9\",\"13\",\"10\",\"12\",\"1\"]','[\"2\",\"5\",\"6\",\"10\",\"13\",\"9\",\"1\"]'),('2017-04-06','[\"현미밥\",\"감자수제비국\",\"미트볼모듬바베큐\",\"팽이버섯돈육볶음\",\"석박지\"]','[\"현미밥\",\"순두부찌개\",\"오이부추무침\",\"오리간장볶음\",\"총각김치9.13.\",\"포도(레드글로브)\"]','[\"현미밥\",\"건새우달걀탕\",\"산적매콤오븐구이\",\"숙주미나리무침\",\"수제무쌈\",\"배추김치\"]','[\"5\",\"6\",\"13\",\"1\",\"2\",\"10\",\"12\",\"9\"]','[\"5\",\"9\",\"10\",\"13\",\"6\"]','[\"1\",\"5\",\"6\",\"9\",\"13\",\"10\"]'),('2017-04-07','[\"잡곡밥\",\"참치찌개\",\"부추무침\",\"목살구이\",\"총각김치9.13.\",\"오렌지1/2\"]','[\"숙채비빔밥/양념고추장\",\"맑은장국\",\"달걀후라이\",\"깍두기\",\"대추토마토\",\"아이러브요거트토핑100\"]','[\"급식이 없습니다.\"]','[\"5\",\"6\",\"9\",\"13\",\"10\"]','[\"5\",\"6\",\"10\",\"13\",\"9\",\"1\",\"12\",\"2\"]','[\"급식이 없습니다.\"]'),('2017-04-08','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-04-09','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-04-10','[\"보리밥\",\"애호박고추장찌개\",\"닭봉바베큐소스조림\",\"감자베이컨오븐구이\",\"배추김치\"]','[\"보리밥\",\"들깨북어미역국\",\"무생채9.13.\",\"오리고추장불고기\",\"요구르트\",\"상추쌈/쌈장5.6.13.\"]','[\"보리밥\",\"두부김치국\",\"신당동떡볶이\",\"수제돈까스/소스\",\"깍두기\",\"사과\"]','[\"1\",\"5\",\"6\",\"9\",\"13\",\"12\",\"15\",\"10\"]','[\"5\",\"6\",\"9\",\"13\",\"2\"]','[\"5\",\"9\",\"13\",\"1\",\"2\",\"6\",\"10\",\"12\"]'),('2017-04-11','[\"표고소고기영양죽\",\"꼬마밤식빵35\",\"어린잎채소샐러드\",\"망고쥬스\",\"파인애플\"]','[\"잡곡밥\",\"돈육김치찌개\",\"수제미트볼조림\",\"배추겉절이\",\"콩나물햄겨자채1.2.5.6.10.13.\",\"오렌지1/2\"]','[\"잡곡밥\",\"통배추된장국\",\"닭오븐구이\",\"골뱅이야채무침\",\"배추김치\",\"딸기퓨레쥬스\"]','[\"9\",\"13\",\"16\",\"1\",\"2\",\"5\",\"6\",\"12\"]','[\"5\",\"6\",\"9\",\"10\",\"13\",\"1\",\"2\",\"12\"]','[\"5\",\"6\",\"9\",\"13\",\"15\"]'),('2017-04-12','[\"기장밥\",\"소고기무국\",\"삼치김치돌돌말이조림\",\"올방개묵김가루무침\",\"치킨너겟/소스\",\"깍두기\"]','[\"기장밥\",\"오리들깨탕\",\"근대나물무침\",\"수제허니버터감자칩\",\"돈육데리야끼오븐구이\",\"열무김치\"]','[\"간장달걀볶음밥\",\"맑은우동국\",\"단무지무침\",\"배추김치\",\"타코야끼\",\"복숭아아이스티\"]','[\"5\",\"6\",\"9\",\"13\",\"16\",\"1\",\"12\",\"15\"]','[\"5\",\"6\",\"9\",\"13\",\"2\",\"10\"]','[\"1\",\"5\",\"6\",\"9\",\"13\",\"11\"]'),('2017-04-13','[\"수수밥\",\"다시마감자국\",\"배추겉절이\",\"양배추오이초무침\",\"오리바베큐오븐구이\",\"사과\"]','[\"수수밥\",\"시래기된장국\",\"닭도리탕\",\"비름나물무침\",\"배추김치\",\"청포도\"]','[\"수수밥\",\"어묵매운탕\",\"떡사태찜\",\"브로콜리초회\",\"당면군만두/케찹\",\"배추김치\"]','[\"5\",\"6\",\"13\",\"9\"]','[\"5\",\"6\",\"9\",\"13\",\"15\"]','[\"1\",\"5\",\"6\",\"9\",\"13\",\"10\",\"12\"]'),('2017-04-14','[\"흑미밥\",\"미소국5.6.9.13.\",\"오삼불고기\",\"조기구이\",\"배추김치\",\"뽀로로요구르트\"]','[\"소고기콩나물밥/달래장\",\"맑은무채국\",\"파래돌김자반\",\"새우또띠아\",\"석박지\",\"수제식혜\"]','[\"흑미밥\",\"우렁된장찌개\",\"등갈비바베큐오븐구이\",\"무말랭이무침\",\"치즈스틱25/케찹\",\"배추김치\"]','[\"5\",\"6\",\"10\",\"12\",\"13\",\"9\",\"2\"]','[\"5\",\"6\",\"13\",\"16\",\"9\",\"1\",\"2\",\"10\",\"12\"]','[\"5\",\"6\",\"13\",\"10\",\"12\",\"2\",\"9\"]'),('2017-04-15','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-04-16','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-04-17','[\"녹차밥\",\"부추팽이달걀탕\",\"영양돼지갈비찜\",\"온두부/양념장\",\"바삭김말이\",\"배추김치\"]','[\"녹차밥\",\"부대찌개\",\"배추겉절이\",\"사각어묵볶음\",\"두부돈전오븐구이\",\"포도(레드글로브)\"]','[\"녹차밥\",\"콩나물김치국\",\"수육\",\"무초절이\",\"보쌈김치9.13.\",\"상추쌈/쌈장5.6.13.\"]','[\"1\",\"5\",\"6\",\"9\",\"13\",\"10\"]','[\"1\",\"2\",\"5\",\"6\",\"9\",\"10\",\"13\"]','[\"5\",\"6\",\"9\",\"13\",\"10\"]'),('2017-04-18','[\"햄콘치즈토스트\",\"가스토갈릭프라이/케찹\",\"흰우유\",\"황도샐러드\",\"시리얼(혼합)\"]','[\"흑미밥\",\"동태찌개\",\"취나물무침\",\"견과류멸치볶음\",\"수제사천식탕수육\",\"배추김치\"]','[\"흑미밥\",\"단배추된장국\",\"순대야채볶음5.6.10.13.\",\"깍두기\",\"장각로스트치킨오븐구이\"]','[\"1\",\"2\",\"5\",\"6\",\"10\",\"12\",\"13\",\"11\"]','[\"5\",\"6\",\"9\",\"13\",\"4\",\"1\",\"2\",\"10\",\"12\"]','[\"5\",\"6\",\"9\",\"13\",\"15\"]'),('2017-04-19','[\"차조밥\",\"김치국\",\"치즈달걀스파게티소스오븐구이\",\"돼지훈제데리야끼오븐구이\",\"깍두기\"]','[\"차조밥\",\"감자탕\",\"오리훈제간장조림\",\"맛살오이겨자냉채\",\"총각김치9.13.\",\"대추토마토\"]','[\"하이라이스\",\"깍두기\",\"사과\",\"비피더스\",\"초코시럽생크림와플\"]','[\"9\",\"13\",\"1\",\"2\",\"6\",\"10\",\"5\"]','[\"5\",\"6\",\"9\",\"10\",\"13\",\"1\",\"12\"]','[\"1\",\"2\",\"5\",\"6\",\"10\",\"12\",\"13\",\"9\"]'),('2017-04-20','[\"보리밥\",\"북어콩나물해장국\",\"배추겉절이\",\"오이새콤무침\",\"청경채돈육두반장볶음\",\"멜론\"]','[\"보리밥\",\"건새우아욱국\",\"뼈없는닭갈비\",\"알감자버터구이2.5.13.\",\"배추김치\",\"키위\"]','[\"보리밥\",\"돼지국밥\",\"메추리알떡매운조림\",\"층층이등심돈까스/소스\",\"배추김치\",\"오렌지1/2\"]','[\"5\",\"6\",\"9\",\"13\",\"10\",\"12\"]','[\"5\",\"6\",\"9\",\"13\",\"15\"]','[\"9\",\"10\",\"13\",\"1\",\"5\",\"6\",\"2\",\"12\"]'),('2017-04-21','[\"기장밥\",\"소고기무매운국\",\"비엔나푸실리볶음\",\"도토리묵상추무침\",\"진미채버터구이\",\"석박지\"]','[\"김치알밥\",\"유부우동국\",\"달걀후라이\",\"깍두기\",\"포도맛요플레\",\"피타브레드주머니빵\"]','[\"기장밥\",\"두부된장국\",\"무생채9.13.\",\"후라이드치킨\",\"옥수수콘버터구이\"]','[\"5\",\"6\",\"9\",\"13\",\"16\",\"1\",\"2\",\"10\",\"12\",\"17\"]','[\"5\",\"6\",\"9\",\"10\",\"13\",\"1\",\"2\"]','[\"5\",\"6\",\"9\",\"13\",\"1\",\"2\",\"15\"]'),('2017-04-22','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-04-23','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-04-24','[\"흰밥(쌀밥)\",\"달래된장국\",\"후랑크야채케찹볶음\",\"야채계란말이\",\"배추김치\",\"블루베리맛엔요\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"5\",\"6\",\"13\",\"2\",\"10\",\"12\",\"1\",\"9\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-04-25','[\"토마토야채샐러드/크랜베리드레2.5.12.13.\",\"계란토스트/딸기잼12\",\"포도(레드글로브)\",\"흰우유\",\"시리얼(혼합)\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"1\",\"2\",\"5\",\"6\",\"13\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-04-26','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-04-27','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-04-28','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-04-29','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]'),('2017-04-30','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]','[\"급식이 없습니다.\"]');
/*!40000 ALTER TABLE `meal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merit_apply`
--

DROP TABLE IF EXISTS `merit_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `merit_apply` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(20) NOT NULL,
  `target` varchar(45) DEFAULT NULL,
  `content` varchar(500) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merit_apply`
--

LOCK TABLES `merit_apply` WRITE;
/*!40000 ALTER TABLE `merit_apply` DISABLE KEYS */;
INSERT INTO `merit_apply` VALUES (5,'',NULL,'평소 행실이 바르다'),(6,'','조성빈','착하다'),(7,'test',NULL,'착해서'),(8,'test','조성빈','예의바르기 때문에'),(13,'씨ㅡ발',NULL,'PostMan 프레임워크 지지합니다');
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
  `title` varchar(100) NOT NULL,
  `content` varchar(4000) NOT NULL,
  `writer` varchar(30) DEFAULT '사감부',
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (1,'안녕하세요','ㅎㅎ','사감부'),(2,'안녕하세요2','ㅎㅎ2','사감부'),(3,'안녕','ㅋㅋ','사감부'),(4,'안녕','ㅋㅋㅋㅋ','사감부'),(5,'안녕?','ㅋㅋㅋㅋ','사감부'),(6,'안녕ㅇㅇ','ㅋㅋㅋㅋ','사감부'),(7,'치킨','먹고싶다','사감부'),(8,'피자','먹고싶다','사감부'),(9,'탕수육','먹고싶다','사감부');
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
  PRIMARY KEY (`number`)
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
INSERT INTO `plan` VALUES (2017,1,'{\"Month\":1,\"Plans\":[{\"Plan\":[\"신정\"],\"Day\":1},{\"Plan\":[],\"Day\":2},{\"Plan\":[],\"Day\":3},{\"Plan\":[],\"Day\":4},{\"Plan\":[],\"Day\":5},{\"Plan\":[\"토요휴업일\"],\"Day\":6},{\"Plan\":[],\"Day\":7},{\"Plan\":[],\"Day\":8},{\"Plan\":[],\"Day\":9},{\"Plan\":[],\"Day\":10},{\"Plan\":[],\"Day\":11},{\"Plan\":[],\"Day\":12},{\"Plan\":[\"토요휴업일\"],\"Day\":13},{\"Plan\":[],\"Day\":14},{\"Plan\":[],\"Day\":15},{\"Plan\":[],\"Day\":16},{\"Plan\":[],\"Day\":17},{\"Plan\":[],\"Day\":18},{\"Plan\":[],\"Day\":19},{\"Plan\":[\"토요휴업일\"],\"Day\":20},{\"Plan\":[],\"Day\":21},{\"Plan\":[],\"Day\":22},{\"Plan\":[],\"Day\":23},{\"Plan\":[],\"Day\":24},{\"Plan\":[],\"Day\":25},{\"Plan\":[],\"Day\":26},{\"Plan\":[\"토요휴업일\"],\"Day\":27},{\"Plan\":[],\"Day\":28},{\"Plan\":[],\"Day\":29},{\"Plan\":[],\"Day\":30},{\"Plan\":[],\"Day\":31}],\"Year\":2017}'),(2017,2,'{\"Month\":2,\"Plans\":[{\"Plan\":[],\"Day\":1},{\"Plan\":[],\"Day\":2},{\"Plan\":[\"토요휴업일\"],\"Day\":3},{\"Plan\":[],\"Day\":4},{\"Plan\":[],\"Day\":5},{\"Plan\":[],\"Day\":6},{\"Plan\":[],\"Day\":7},{\"Plan\":[],\"Day\":8},{\"Plan\":[],\"Day\":9},{\"Plan\":[\"토요휴업일\"],\"Day\":10},{\"Plan\":[],\"Day\":11},{\"Plan\":[],\"Day\":12},{\"Plan\":[],\"Day\":13},{\"Plan\":[],\"Day\":14},{\"Plan\":[],\"Day\":15},{\"Plan\":[],\"Day\":16},{\"Plan\":[\"토요휴업일\"],\"Day\":17},{\"Plan\":[],\"Day\":18},{\"Plan\":[],\"Day\":19},{\"Plan\":[],\"Day\":20},{\"Plan\":[],\"Day\":21},{\"Plan\":[],\"Day\":22},{\"Plan\":[],\"Day\":23},{\"Plan\":[\"토요휴업일\"],\"Day\":24},{\"Plan\":[],\"Day\":25},{\"Plan\":[],\"Day\":26},{\"Plan\":[],\"Day\":27},{\"Plan\":[],\"Day\":28}],\"Year\":2017}'),(2017,3,'{\"Month\":3,\"Plans\":[{\"Plan\":[\"3・1절\"],\"Day\":1},{\"Plan\":[\"입학식/1\",\"개학식/2/3\"],\"Day\":2},{\"Plan\":[],\"Day\":3},{\"Plan\":[\"토요휴업일\"],\"Day\":4},{\"Plan\":[],\"Day\":5},{\"Plan\":[],\"Day\":6},{\"Plan\":[],\"Day\":7},{\"Plan\":[],\"Day\":8},{\"Plan\":[],\"Day\":9},{\"Plan\":[],\"Day\":10},{\"Plan\":[\"토요휴업일\"],\"Day\":11},{\"Plan\":[],\"Day\":12},{\"Plan\":[],\"Day\":13},{\"Plan\":[],\"Day\":14},{\"Plan\":[],\"Day\":15},{\"Plan\":[],\"Day\":16},{\"Plan\":[],\"Day\":17},{\"Plan\":[\"토요휴업일\"],\"Day\":18},{\"Plan\":[],\"Day\":19},{\"Plan\":[],\"Day\":20},{\"Plan\":[],\"Day\":21},{\"Plan\":[],\"Day\":22},{\"Plan\":[],\"Day\":23},{\"Plan\":[],\"Day\":24},{\"Plan\":[\"토요휴업일\"],\"Day\":25},{\"Plan\":[],\"Day\":26},{\"Plan\":[],\"Day\":27},{\"Plan\":[],\"Day\":28},{\"Plan\":[],\"Day\":29},{\"Plan\":[],\"Day\":30},{\"Plan\":[],\"Day\":31}],\"Year\":2017}'),(2017,4,'{\"Month\":4,\"Plans\":[{\"Plan\":[\"토요휴업일\"],\"Day\":1},{\"Plan\":[],\"Day\":2},{\"Plan\":[],\"Day\":3},{\"Plan\":[],\"Day\":4},{\"Plan\":[],\"Day\":5},{\"Plan\":[],\"Day\":6},{\"Plan\":[],\"Day\":7},{\"Plan\":[\"토요휴업일\"],\"Day\":8},{\"Plan\":[],\"Day\":9},{\"Plan\":[],\"Day\":10},{\"Plan\":[],\"Day\":11},{\"Plan\":[],\"Day\":12},{\"Plan\":[],\"Day\":13},{\"Plan\":[],\"Day\":14},{\"Plan\":[\"토요휴업일\"],\"Day\":15},{\"Plan\":[],\"Day\":16},{\"Plan\":[],\"Day\":17},{\"Plan\":[],\"Day\":18},{\"Plan\":[],\"Day\":19},{\"Plan\":[],\"Day\":20},{\"Plan\":[],\"Day\":21},{\"Plan\":[\"토요휴업일\"],\"Day\":22},{\"Plan\":[],\"Day\":23},{\"Plan\":[],\"Day\":24},{\"Plan\":[],\"Day\":25},{\"Plan\":[],\"Day\":26},{\"Plan\":[],\"Day\":27},{\"Plan\":[],\"Day\":28},{\"Plan\":[\"토요휴업일\"],\"Day\":29},{\"Plan\":[],\"Day\":30}],\"Year\":2017}'),(2017,5,'{\"Month\":5,\"Plans\":[{\"Plan\":[],\"Day\":1},{\"Plan\":[],\"Day\":2},{\"Plan\":[],\"Day\":3},{\"Plan\":[],\"Day\":4},{\"Plan\":[\"어린이날\"],\"Day\":5},{\"Plan\":[\"토요휴업일\"],\"Day\":6},{\"Plan\":[],\"Day\":7},{\"Plan\":[],\"Day\":8},{\"Plan\":[],\"Day\":9},{\"Plan\":[],\"Day\":10},{\"Plan\":[],\"Day\":11},{\"Plan\":[],\"Day\":12},{\"Plan\":[\"토요휴업일\"],\"Day\":13},{\"Plan\":[],\"Day\":14},{\"Plan\":[],\"Day\":15},{\"Plan\":[],\"Day\":16},{\"Plan\":[],\"Day\":17},{\"Plan\":[],\"Day\":18},{\"Plan\":[],\"Day\":19},{\"Plan\":[\"토요휴업일\"],\"Day\":20},{\"Plan\":[],\"Day\":21},{\"Plan\":[],\"Day\":22},{\"Plan\":[],\"Day\":23},{\"Plan\":[],\"Day\":24},{\"Plan\":[],\"Day\":25},{\"Plan\":[],\"Day\":26},{\"Plan\":[\"토요휴업일\"],\"Day\":27},{\"Plan\":[],\"Day\":28},{\"Plan\":[],\"Day\":29},{\"Plan\":[],\"Day\":30},{\"Plan\":[],\"Day\":31}],\"Year\":2017}'),(2017,6,'{\"Month\":6,\"Plans\":[{\"Plan\":[],\"Day\":1},{\"Plan\":[],\"Day\":2},{\"Plan\":[\"토요휴업일\"],\"Day\":3},{\"Plan\":[],\"Day\":4},{\"Plan\":[],\"Day\":5},{\"Plan\":[\"현충일\"],\"Day\":6},{\"Plan\":[],\"Day\":7},{\"Plan\":[],\"Day\":8},{\"Plan\":[],\"Day\":9},{\"Plan\":[\"토요휴업일\"],\"Day\":10},{\"Plan\":[],\"Day\":11},{\"Plan\":[],\"Day\":12},{\"Plan\":[],\"Day\":13},{\"Plan\":[],\"Day\":14},{\"Plan\":[],\"Day\":15},{\"Plan\":[],\"Day\":16},{\"Plan\":[\"토요휴업일\"],\"Day\":17},{\"Plan\":[],\"Day\":18},{\"Plan\":[],\"Day\":19},{\"Plan\":[],\"Day\":20},{\"Plan\":[],\"Day\":21},{\"Plan\":[],\"Day\":22},{\"Plan\":[],\"Day\":23},{\"Plan\":[\"토요휴업일\"],\"Day\":24},{\"Plan\":[],\"Day\":25},{\"Plan\":[],\"Day\":26},{\"Plan\":[],\"Day\":27},{\"Plan\":[],\"Day\":28},{\"Plan\":[],\"Day\":29},{\"Plan\":[],\"Day\":30}],\"Year\":2017}'),(2017,7,'{\"Month\":7,\"Plans\":[{\"Plan\":[\"토요휴업일\"],\"Day\":1},{\"Plan\":[],\"Day\":2},{\"Plan\":[],\"Day\":3},{\"Plan\":[],\"Day\":4},{\"Plan\":[],\"Day\":5},{\"Plan\":[],\"Day\":6},{\"Plan\":[],\"Day\":7},{\"Plan\":[\"토요휴업일\"],\"Day\":8},{\"Plan\":[],\"Day\":9},{\"Plan\":[],\"Day\":10},{\"Plan\":[],\"Day\":11},{\"Plan\":[],\"Day\":12},{\"Plan\":[],\"Day\":13},{\"Plan\":[],\"Day\":14},{\"Plan\":[\"토요휴업일\"],\"Day\":15},{\"Plan\":[],\"Day\":16},{\"Plan\":[],\"Day\":17},{\"Plan\":[],\"Day\":18},{\"Plan\":[],\"Day\":19},{\"Plan\":[],\"Day\":20},{\"Plan\":[],\"Day\":21},{\"Plan\":[\"토요휴업일\"],\"Day\":22},{\"Plan\":[],\"Day\":23},{\"Plan\":[],\"Day\":24},{\"Plan\":[],\"Day\":25},{\"Plan\":[],\"Day\":26},{\"Plan\":[],\"Day\":27},{\"Plan\":[],\"Day\":28},{\"Plan\":[\"토요휴업일\"],\"Day\":29},{\"Plan\":[],\"Day\":30},{\"Plan\":[],\"Day\":31}],\"Year\":2017}'),(2017,8,'{\"Month\":8,\"Plans\":[{\"Plan\":[],\"Day\":1},{\"Plan\":[],\"Day\":2},{\"Plan\":[],\"Day\":3},{\"Plan\":[],\"Day\":4},{\"Plan\":[\"토요휴업일\"],\"Day\":5},{\"Plan\":[],\"Day\":6},{\"Plan\":[],\"Day\":7},{\"Plan\":[],\"Day\":8},{\"Plan\":[],\"Day\":9},{\"Plan\":[],\"Day\":10},{\"Plan\":[],\"Day\":11},{\"Plan\":[\"토요휴업일\"],\"Day\":12},{\"Plan\":[],\"Day\":13},{\"Plan\":[],\"Day\":14},{\"Plan\":[\"광복절\"],\"Day\":15},{\"Plan\":[],\"Day\":16},{\"Plan\":[],\"Day\":17},{\"Plan\":[],\"Day\":18},{\"Plan\":[\"토요휴업일\"],\"Day\":19},{\"Plan\":[],\"Day\":20},{\"Plan\":[],\"Day\":21},{\"Plan\":[],\"Day\":22},{\"Plan\":[],\"Day\":23},{\"Plan\":[],\"Day\":24},{\"Plan\":[],\"Day\":25},{\"Plan\":[\"토요휴업일\"],\"Day\":26},{\"Plan\":[],\"Day\":27},{\"Plan\":[],\"Day\":28},{\"Plan\":[],\"Day\":29},{\"Plan\":[],\"Day\":30},{\"Plan\":[],\"Day\":31}],\"Year\":2017}'),(2017,9,'{\"Month\":9,\"Plans\":[{\"Plan\":[],\"Day\":1},{\"Plan\":[\"토요휴업일\"],\"Day\":2},{\"Plan\":[],\"Day\":3},{\"Plan\":[],\"Day\":4},{\"Plan\":[],\"Day\":5},{\"Plan\":[],\"Day\":6},{\"Plan\":[],\"Day\":7},{\"Plan\":[],\"Day\":8},{\"Plan\":[\"토요휴업일\"],\"Day\":9},{\"Plan\":[],\"Day\":10},{\"Plan\":[],\"Day\":11},{\"Plan\":[],\"Day\":12},{\"Plan\":[],\"Day\":13},{\"Plan\":[],\"Day\":14},{\"Plan\":[],\"Day\":15},{\"Plan\":[\"토요휴업일\"],\"Day\":16},{\"Plan\":[],\"Day\":17},{\"Plan\":[],\"Day\":18},{\"Plan\":[],\"Day\":19},{\"Plan\":[],\"Day\":20},{\"Plan\":[],\"Day\":21},{\"Plan\":[],\"Day\":22},{\"Plan\":[\"토요휴업일\"],\"Day\":23},{\"Plan\":[],\"Day\":24},{\"Plan\":[],\"Day\":25},{\"Plan\":[],\"Day\":26},{\"Plan\":[],\"Day\":27},{\"Plan\":[],\"Day\":28},{\"Plan\":[],\"Day\":29},{\"Plan\":[\"토요휴업일\"],\"Day\":30}],\"Year\":2017}'),(2017,10,'{\"Month\":10,\"Plans\":[{\"Plan\":[],\"Day\":2},{\"Plan\":[\"개천절\"],\"Day\":3},{\"Plan\":[],\"Day\":4},{\"Plan\":[],\"Day\":5},{\"Plan\":[],\"Day\":6},{\"Plan\":[\"토요휴업일\"],\"Day\":7},{\"Plan\":[],\"Day\":8},{\"Plan\":[\"한글날\"],\"Day\":9},{\"Plan\":[],\"Day\":10},{\"Plan\":[],\"Day\":11},{\"Plan\":[],\"Day\":12},{\"Plan\":[],\"Day\":13},{\"Plan\":[\"토요휴업일\"],\"Day\":14},{\"Plan\":[],\"Day\":15},{\"Plan\":[],\"Day\":16},{\"Plan\":[],\"Day\":17},{\"Plan\":[],\"Day\":18},{\"Plan\":[],\"Day\":19},{\"Plan\":[],\"Day\":20},{\"Plan\":[\"토요휴업일\"],\"Day\":21},{\"Plan\":[],\"Day\":22},{\"Plan\":[],\"Day\":23},{\"Plan\":[],\"Day\":24},{\"Plan\":[],\"Day\":25},{\"Plan\":[],\"Day\":26},{\"Plan\":[],\"Day\":27},{\"Plan\":[\"토요휴업일\"],\"Day\":28},{\"Plan\":[],\"Day\":29},{\"Plan\":[],\"Day\":30},{\"Plan\":[],\"Day\":31}],\"Year\":2017}'),(2017,11,'{\"Month\":11,\"Plans\":[{\"Plan\":[],\"Day\":1},{\"Plan\":[],\"Day\":2},{\"Plan\":[],\"Day\":3},{\"Plan\":[\"토요휴업일\"],\"Day\":4},{\"Plan\":[],\"Day\":5},{\"Plan\":[],\"Day\":6},{\"Plan\":[],\"Day\":7},{\"Plan\":[],\"Day\":8},{\"Plan\":[],\"Day\":9},{\"Plan\":[],\"Day\":10},{\"Plan\":[\"토요휴업일\"],\"Day\":11},{\"Plan\":[],\"Day\":12},{\"Plan\":[],\"Day\":13},{\"Plan\":[],\"Day\":14},{\"Plan\":[],\"Day\":15},{\"Plan\":[],\"Day\":16},{\"Plan\":[],\"Day\":17},{\"Plan\":[\"토요휴업일\"],\"Day\":18},{\"Plan\":[],\"Day\":19},{\"Plan\":[],\"Day\":20},{\"Plan\":[],\"Day\":21},{\"Plan\":[],\"Day\":22},{\"Plan\":[],\"Day\":23},{\"Plan\":[],\"Day\":24},{\"Plan\":[\"토요휴업일\"],\"Day\":25},{\"Plan\":[],\"Day\":26},{\"Plan\":[],\"Day\":27},{\"Plan\":[],\"Day\":28},{\"Plan\":[],\"Day\":29},{\"Plan\":[],\"Day\":30}],\"Year\":2017}'),(2017,12,'{\"Month\":12,\"Plans\":[{\"Plan\":[],\"Day\":1},{\"Plan\":[\"토요휴업일\"],\"Day\":2},{\"Plan\":[],\"Day\":3},{\"Plan\":[],\"Day\":4},{\"Plan\":[],\"Day\":5},{\"Plan\":[],\"Day\":6},{\"Plan\":[],\"Day\":7},{\"Plan\":[],\"Day\":8},{\"Plan\":[\"토요휴업일\"],\"Day\":9},{\"Plan\":[],\"Day\":10},{\"Plan\":[],\"Day\":11},{\"Plan\":[],\"Day\":12},{\"Plan\":[],\"Day\":13},{\"Plan\":[],\"Day\":14},{\"Plan\":[],\"Day\":15},{\"Plan\":[\"토요휴업일\"],\"Day\":16},{\"Plan\":[],\"Day\":17},{\"Plan\":[],\"Day\":18},{\"Plan\":[],\"Day\":19},{\"Plan\":[],\"Day\":20},{\"Plan\":[],\"Day\":21},{\"Plan\":[],\"Day\":22},{\"Plan\":[\"토요휴업일\"],\"Day\":23},{\"Plan\":[],\"Day\":24},{\"Plan\":[\"성탄절\"],\"Day\":25},{\"Plan\":[],\"Day\":26},{\"Plan\":[],\"Day\":27},{\"Plan\":[],\"Day\":28},{\"Plan\":[],\"Day\":29},{\"Plan\":[\"토요휴업일\"],\"Day\":30},{\"Plan\":[],\"Day\":31}],\"Year\":2017}');
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
  `writer` varchar(20) DEFAULT NULL,
  `answer_content` varchar(5000) DEFAULT NULL,
  `answer_date` datetime DEFAULT NULL,
  `privacy` tinyint(1) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  KEY `qna_comment_ibfk_1` (`no`),
  CONSTRAINT `qna_comment_ibfk_1` FOREIGN KEY (`no`) REFERENCES `qna` (`no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qna_comment`
--

LOCK TABLES `qna_comment` WRITE;
/*!40000 ALTER TABLE `qna_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `qna_comment` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rule`
--

LOCK TABLES `rule` WRITE;
/*!40000 ALTER TABLE `rule` DISABLE KEYS */;
INSERT INTO `rule` VALUES (1,'치킨 먹는법','그런거없다'),(2,'피자 먹는법','그런거없다'),(3,'탕수육 먹는법','그런거없다'),(4,'null','null'),(5,'null','null'),(6,'null','null'),(7,'null','null');
/*!40000 ALTER TABLE `rule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stay_apply`
--

DROP TABLE IF EXISTS `stay_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stay_apply` (
  `id` varchar(20) NOT NULL,
  `value` int(1) NOT NULL,
  `week` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stay_apply`
--

LOCK TABLES `stay_apply` WRITE;
/*!40000 ALTER TABLE `stay_apply` DISABLE KEYS */;
INSERT INTO `stay_apply` VALUES ('test',1,'2017-02-04'),('mingyu',1,'2017-02-24'),('test',2,'2017-03-02'),('test',3,'2017-03-03'),('test',2,'2017-03-01'),('test',2,'2017-04-01'),('test',2,'2017-04-05'),('asf',2,'2017-03-03'),('test',3,'2017-03-04'),('asdf',1,'2017-03-04');
/*!40000 ALTER TABLE `stay_apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stay_apply_default`
--

DROP TABLE IF EXISTS `stay_apply_default`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stay_apply_default` (
  `id` varchar(100) NOT NULL,
  `value` int(1) DEFAULT '4',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stay_apply_default`
--

LOCK TABLES `stay_apply_default` WRITE;
/*!40000 ALTER TABLE `stay_apply_default` DISABLE KEYS */;
INSERT INTO `stay_apply_default` VALUES ('city7310',4),('test',4);
/*!40000 ALTER TABLE `stay_apply_default` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_data`
--

DROP TABLE IF EXISTS `student_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_data` (
  `id` varchar(100) NOT NULL,
  `number` int(11) NOT NULL,
  `status` int(1) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_data`
--

LOCK TABLES `student_data` WRITE;
/*!40000 ALTER TABLE `student_data` DISABLE KEYS */;
INSERT INTO `student_data` VALUES ('test',20120,1,'조민규민규');
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
  `password` varchar(500) NOT NULL,
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

-- Dump completed on 2017-03-12 17:41:19
