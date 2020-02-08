-- MySQL dump 10.13  Distrib 8.0.18, for Linux (x86_64)
--
-- Host: localhost    Database: SearchFood
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `BusinessHours`
--

DROP TABLE IF EXISTS `BusinessHours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BusinessHours` (
  `storeId` int(11) NOT NULL,
  `mon` char(11) DEFAULT NULL,
  `tue` char(11) DEFAULT NULL,
  `wed` char(11) DEFAULT NULL,
  `thu` char(11) DEFAULT NULL,
  `fri` char(11) DEFAULT NULL,
  `sat` char(11) DEFAULT NULL,
  `sun` char(11) DEFAULT NULL,
  PRIMARY KEY (`storeId`),
  CONSTRAINT `BusinessHours_ibfk_1` FOREIGN KEY (`storeId`) REFERENCES `StoreInfo` (`storeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BusinessHours`
--

LOCK TABLES `BusinessHours` WRITE;
/*!40000 ALTER TABLE `BusinessHours` DISABLE KEYS */;
INSERT INTO `BusinessHours` VALUES (2,'09:00~18:00','09:00~18:00','09:00~18:00','09:00~18:00','09:00~18:00','09:00~18:00','off'),(3,'09:00~18:00','09:00~18:00','09:00~18:00','09:00~18:00','09:00~18:00','09:00~18:00','off'),(4,'09:00~18:00','09:00~18:00','09:00~18:00','09:00~18:00','09:00~18:00','09:00~18:00','off'),(5,'09:00~18:00','09:00~18:00','09:00~18:00','09:00~18:00','09:00~18:00','09:00~18:00','off'),(7,'09:00~18:00','09:00~18:00','09:00~18:00','09:00~18:00','09:00~18:00','09:00~18:00','off');
/*!40000 ALTER TABLE `BusinessHours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ClickRecord`
--

DROP TABLE IF EXISTS `ClickRecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ClickRecord` (
  `username` varchar(20) NOT NULL,
  `storeId` int(11) NOT NULL,
  UNIQUE KEY `records` (`username`,`storeId`),
  KEY `storeId` (`storeId`),
  CONSTRAINT `ClickRecord_ibfk_1` FOREIGN KEY (`username`) REFERENCES `Users` (`username`),
  CONSTRAINT `ClickRecord_ibfk_2` FOREIGN KEY (`storeId`) REFERENCES `StoreInfo` (`storeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ClickRecord`
--

LOCK TABLES `ClickRecord` WRITE;
/*!40000 ALTER TABLE `ClickRecord` DISABLE KEYS */;
/*!40000 ALTER TABLE `ClickRecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ClickRecords`
--

DROP TABLE IF EXISTS `ClickRecords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ClickRecords` (
  `username` varchar(20) NOT NULL,
  `storeId` int(11) NOT NULL,
  UNIQUE KEY `records` (`username`,`storeId`),
  KEY `storeId` (`storeId`),
  CONSTRAINT `ClickRecords_ibfk_1` FOREIGN KEY (`username`) REFERENCES `Users` (`username`),
  CONSTRAINT `ClickRecords_ibfk_2` FOREIGN KEY (`storeId`) REFERENCES `StoreInfo` (`storeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ClickRecords`
--

LOCK TABLES `ClickRecords` WRITE;
/*!40000 ALTER TABLE `ClickRecords` DISABLE KEYS */;
/*!40000 ALTER TABLE `ClickRecords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ReferedTable`
--

DROP TABLE IF EXISTS `ReferedTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ReferedTable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `types` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `class` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ReferedTable`
--

LOCK TABLES `ReferedTable` WRITE;
/*!40000 ALTER TABLE `ReferedTable` DISABLE KEYS */;
INSERT INTO `ReferedTable` VALUES (1,'三寶飯','飯','FoodTypes'),(2,'滷肉飯','飯','FoodTypes'),(3,'豬排飯','飯','FoodTypes'),(4,'炒飯','飯','FoodTypes'),(5,'燴飯','飯','FoodTypes'),(6,'油飯','飯','FoodTypes'),(7,'陽春麵','麵食','FoodTypes'),(8,'涼麵','麵食','FoodTypes'),(9,'油麵','麵食','FoodTypes'),(10,'義大利麵','麵食','FoodTypes'),(11,'拉麵','麵食','FoodTypes'),(12,'麥當勞','速食','FoodTypes'),(13,'肯德基','速食','FoodTypes'),(14,'胖老爹','速食','FoodTypes'),(15,'頂呱呱','速食','FoodTypes');
/*!40000 ALTER TABLE `ReferedTable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StoreComment`
--

DROP TABLE IF EXISTS `StoreComment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `StoreComment` (
  `storeId` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `star` tinyint(4) NOT NULL DEFAULT '0',
  `price` varchar(20) NOT NULL,
  `commentAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `comments` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `picture` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`username`,`storeId`),
  KEY `storeId` (`storeId`),
  CONSTRAINT `StoreComment_ibfk_1` FOREIGN KEY (`username`) REFERENCES `Users` (`username`),
  CONSTRAINT `StoreComment_ibfk_2` FOREIGN KEY (`storeId`) REFERENCES `StoreInfo` (`storeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StoreComment`
--

LOCK TABLES `StoreComment` WRITE;
/*!40000 ALTER TABLE `StoreComment` DISABLE KEYS */;
INSERT INTO `StoreComment` VALUES (2,'Beta',4,'100','2020-01-01 05:40:03',NULL,NULL),(2,'Eason',4,'100','2020-01-01 05:39:54',NULL,NULL),(2,'Gamma',3,'100','2020-01-01 05:40:13',NULL,NULL),(2,'Joho12',2,'100','2020-01-01 05:39:40',NULL,NULL),(2,'Jumbo',3,'100','2020-01-01 05:39:18',NULL,NULL),(2,'Jumbo11',3,'100','2020-01-01 05:40:49',NULL,NULL),(2,'Jumbo12',4,'100','2020-01-01 05:40:44',NULL,NULL),(2,'Jumbo8',3,'100','2020-01-01 05:44:32',NULL,NULL),(2,'MRT22',5,'100','2020-01-01 05:40:36',NULL,NULL);
/*!40000 ALTER TABLE `StoreComment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StoreImages`
--

DROP TABLE IF EXISTS `StoreImages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `StoreImages` (
  `storeId` int(11) NOT NULL,
  `picUrl` varchar(150) DEFAULT NULL,
  KEY `storeId` (`storeId`),
  CONSTRAINT `StoreImages_ibfk_1` FOREIGN KEY (`storeId`) REFERENCES `StoreInfo` (`storeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StoreImages`
--

LOCK TABLES `StoreImages` WRITE;
/*!40000 ALTER TABLE `StoreImages` DISABLE KEYS */;
/*!40000 ALTER TABLE `StoreImages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StoreInfo`
--

DROP TABLE IF EXISTS `StoreInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `StoreInfo` (
  `storeid` int(11) NOT NULL AUTO_INCREMENT,
  `storename` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `city` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `district` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `address` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `tel` char(10) NOT NULL,
  `createdAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(20) NOT NULL,
  `click_week` int(3) DEFAULT '0',
  `click_cum` int(4) DEFAULT '0',
  `lat_long` json NOT NULL,
  `rating` float DEFAULT '0',
  `logo` varchar(150) DEFAULT NULL,
  `images` varchar(150) DEFAULT NULL,
  `slogan` varchar(50) DEFAULT NULL,
  `tags` varchar(150) NOT NULL,
  PRIMARY KEY (`storeid`,`storename`,`city`,`district`,`address`),
  UNIQUE KEY `city` (`city`,`district`,`address`),
  KEY `creator` (`creator`),
  CONSTRAINT `StoreInfo_ibfk_1` FOREIGN KEY (`creator`) REFERENCES `Users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StoreInfo`
--

LOCK TABLES `StoreInfo` WRITE;
/*!40000 ALTER TABLE `StoreInfo` DISABLE KEYS */;
INSERT INTO `StoreInfo` VALUES (2,'JP2','台北市','中和區','中壢路2211號','0912332123','2020-01-01 08:24:23','Johonny',0,5,'{\"lat\": 123, \"long\": 2332}',3.44444,NULL,NULL,NULL,'速食, 麵食, 飯'),(3,'JP2','新北市','中和區','中壢路2211號','0912332123','2020-01-01 05:19:21','Johonny',0,1,'{\"lat\": 123, \"long\": 2332}',0,NULL,NULL,NULL,'速食, 麵食, 飯'),(4,'JP2','新北市','中和區','中壢路11號','0912332123','2019-12-28 12:35:35','Johonny',0,0,'{\"lat\": 123, \"long\": 2332}',0,NULL,NULL,NULL,'速食, 麵食, 飯'),(5,'JP2','新北市','中和區','中正路11號','0912332123','2020-01-01 08:24:20','Johonny',0,2,'{\"lat\": 123, \"long\": 2332}',0,NULL,NULL,NULL,'速食, 麵食, 飯'),(7,'Test','新北市','中和區','中正路1021號','0912332123','2020-01-02 09:59:58','Jumbo18',0,0,'{\"lat\": 123, \"long\": 2332}',0,NULL,NULL,NULL,'速食, 麵食, 飯');
/*!40000 ALTER TABLE `StoreInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StoresMenu`
--

DROP TABLE IF EXISTS `StoresMenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `StoresMenu` (
  `storeId` int(11) NOT NULL,
  `foodId` int(11) NOT NULL,
  PRIMARY KEY (`storeId`,`foodId`),
  KEY `foodId` (`foodId`),
  CONSTRAINT `StoresMenu_ibfk_1` FOREIGN KEY (`storeId`) REFERENCES `StoreInfo` (`storeid`),
  CONSTRAINT `StoresMenu_ibfk_2` FOREIGN KEY (`foodId`) REFERENCES `ReferedTable` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StoresMenu`
--

LOCK TABLES `StoresMenu` WRITE;
/*!40000 ALTER TABLE `StoresMenu` DISABLE KEYS */;
INSERT INTO `StoresMenu` VALUES (2,1),(3,1),(4,1),(5,1),(7,1),(2,2),(3,2),(4,2),(5,2),(7,2),(2,3),(3,3),(4,3),(5,3),(7,3),(2,4),(3,4),(4,4),(5,4),(7,4),(2,5),(3,5),(4,5),(5,5),(7,5),(2,7),(3,7),(4,7),(5,7),(7,7),(2,8),(3,8),(4,8),(5,8),(7,8),(2,9),(3,9),(4,9),(5,9),(7,9),(2,10),(3,10),(4,10),(5,10),(7,10),(2,12),(3,12),(4,12),(5,12),(7,12),(2,13),(3,13),(4,13),(5,13),(7,13),(2,14),(3,14),(4,14),(5,14),(7,14),(2,15),(3,15),(4,15),(5,15),(7,15);
/*!40000 ALTER TABLE `StoresMenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Token`
--

DROP TABLE IF EXISTS `Token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Token` (
  `mail` varchar(25) NOT NULL,
  `username` varchar(20) NOT NULL,
  `token` varchar(80) NOT NULL,
  `login_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `navigator_type` varchar(20) NOT NULL,
  PRIMARY KEY (`mail`,`navigator_type`),
  KEY `username` (`username`),
  CONSTRAINT `Token_ibfk_1` FOREIGN KEY (`mail`) REFERENCES `Users` (`mail`),
  CONSTRAINT `Token_ibfk_2` FOREIGN KEY (`username`) REFERENCES `Users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Token`
--

LOCK TABLES `Token` WRITE;
/*!40000 ALTER TABLE `Token` DISABLE KEYS */;
INSERT INTO `Token` VALUES ('admin@test.com','admin','853ab0c1-505c-4321-9649-2fbd8682c9ca','2019-12-28 13:42:28','Nan'),('ts68@t2s1.om','Jumbo15','4b860648-63f1-4bcd-9625-644dff770a50','2019-12-31 04:07:26','Nan'),('ts68s@23s1.om','Jumbo18','9685770a-3ce0-4a88-b458-ed7fa6c937fc','2020-01-02 09:57:05','Nan'),('ts68s@t2s1.om','Jumbo16','91e5f808-1cf1-4103-9aab-0a6feed24055','2019-12-31 04:17:02','Nan'),('tvjs68@t1.2om','Jumbo7','3728e85c-3d46-4a3b-bef3-5a54bddb162c','2019-12-31 03:30:06','Nan'),('tvjs68@t1.3om','Jumbo8','1928db1e-c2b5-42d3-b3fa-befc01caea98','2019-12-31 03:31:55','Nan'),('tvjs68@t1.c112om','Jumbo6','8b8fc6a5-c25d-4c9b-b64d-1e6dc1c4dc6d','2019-12-31 03:27:43','Nan'),('tvjs68@t1.om','Jumbo12','1b247765-97e8-49f8-b643-f26eca393ca4','2019-12-31 03:43:46','Nan'),('tvjs68@t21.om','Jumbo13','1f6b8713-353f-4995-8ce9-ad572f71de93','2019-12-31 03:56:04','Nan'),('tvjs68@t211.3om','Jumbo9','38f9f40b-62be-43e8-84cd-5b044898d158','2019-12-31 03:37:38','Nan'),('tvjs68@t231.3om','Jumbo10','abb193da-1273-4c1d-80f2-dabc9e498900','2019-12-31 03:41:08','Nan'),('tvjs68@t231.om','Jumbo11','e7200063-56e2-47cb-8ef3-b49f49d4e8d7','2019-12-31 03:42:09','Nan'),('tvjs68@t2s1.om','Jumbo14','ead7eb2a-15c8-4da1-ba76-9e991b2ba0bb','2019-12-31 03:57:07','Nan'),('tvjs68@test1.c112om','Jumbo4','a1ec43ab-f2ad-4b54-82b6-07b1ccd8c6a6','2019-12-31 03:25:57','Nan'),('tvjs68@test1.c12om','Jumbo3','48358f0b-53c7-4b4b-adc8-8026f3b204de','2019-12-31 03:15:53','Nan'),('tvjs68@test1.c1om','Jumbo2','20d51499-1d8f-499a-abd4-e3ce0c55f151','2019-12-31 03:15:04','Nan'),('tvjs68@test123.com','Jumbo','9fd9144b-bcd8-4298-a994-b39b01690171','2020-01-01 08:09:02','IE'),('tvjs68@test123.com','Jumbo','2acfb269-3da0-4515-b1fd-6a837d152015','2019-12-31 03:14:35','Nan'),('tvjs68@tt1.c112om','Jumbo5','a54eb040-338e-49f3-b2cd-50380b289781','2019-12-31 03:27:24','Nan');
/*!40000 ALTER TABLE `Token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `mail` varchar(25) NOT NULL,
  `passwd` char(10) NOT NULL,
  `sexual` int(11) NOT NULL,
  `birthyear` year(4) NOT NULL,
  `age` tinyint(4) DEFAULT ((year(curdate()) - `birthyear`)),
  PRIMARY KEY (`userId`),
  UNIQUE KEY `mail` (`mail`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (77,'Johonny','ab123c@twest1.com','f345c93d',0,1998,21),(78,'Joho12','ab22c@twest2.com','1232134567',0,2001,18),(79,'Lesie','a3c@twest1.com','1232134567',0,1989,30),(80,'Eason','ab1t@test1.com','1232134567',0,2000,19),(81,'Alpha','3211throwsc@test1.com','1232134567',1,2000,19),(82,'Beta','abc4@test3.com','1232134567',1,1992,27),(83,'Gamma','a2bc@test4.com','12321rd567',1,1993,26),(84,'Eposoln','afc@test2.com','1232134567',1,1992,27),(85,'Elephant','a223d@test1.com','1232134567',0,1989,30),(86,'Eggs','aew112@test1.com','1232134567',1,1993,26),(87,'33Eas','tre2d@test1.com','1232134567',0,2001,18),(88,'Apple','App3le@test1.com','12tfr34567',1,1998,21),(89,'P11lsw','Pl2s@test1.com','123dcc4567',1,2000,19),(90,'MRT22','m1rt@test1.com','1245r34567',0,2001,18),(91,'admin','admin@test.com','123456',0,1928,91),(92,'Jumbo','tvjs68@test123.com','1234#$%@',1,1999,20),(94,'Jumbo2','tvjs68@test1.c1om','1234#$%@',1,1999,20),(95,'Jumbo3','tvjs68@test1.c12om','1234#$%@',1,1999,20),(96,'Jumbo4','tvjs68@test1.c112om','1234#$%@',1,1999,20),(97,'Jumbo5','tvjs68@tt1.c112om','1234#$%@',1,1999,20),(98,'Jumbo6','tvjs68@t1.c112om','1234#$%@',1,1999,20),(99,'Jumbo7','tvjs68@t1.2om','1234#$%@',1,1999,20),(100,'Jumbo8','tvjs68@t1.3om','1234#$%@',1,1999,20),(101,'Jumbo9','tvjs68@t211.3om','1234#$%@',1,1999,20),(102,'Jumbo10','tvjs68@t231.3om','1234#$%@',1,1999,20),(103,'Jumbo11','tvjs68@t231.om','1234#$%@',1,1999,20),(104,'Jumbo12','tvjs68@t1.om','1234#$%@',1,1999,20),(105,'Jumbo13','tvjs68@t21.om','1234#$%@',1,1999,20),(106,'Jumbo14','tvjs68@t2s1.om','1234#$%@',1,1999,20),(107,'Jumbo15','ts68@t2s1.om','1234#$%@',1,1999,20),(108,'Jumbo16','ts68s@t2s1.om','1234#$%@',1,1999,20),(110,'Jumbo18','ts68s@23s1.om','1234#$%@',1,1999,21);
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-08 16:33:32
