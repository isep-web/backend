CREATE DATABASE  IF NOT EXISTS `web` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `web`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: web
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_amenity`
--

DROP TABLE IF EXISTS `t_amenity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_amenity` (
  `f_id` bigint NOT NULL AUTO_INCREMENT,
  `f_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `f_detail` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `f_created_time` datetime DEFAULT NULL,
  `f_last_updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_amenity`
--

LOCK TABLES `t_amenity` WRITE;
/*!40000 ALTER TABLE `t_amenity` DISABLE KEYS */;
INSERT INTO `t_amenity` VALUES (1,'amenity1','detail1','2021-05-30 23:31:42','2021-05-30 23:31:42'),(2,'amenity2','detail2','2021-05-30 23:31:45','2021-05-30 23:31:54'),(3,'amenity3','detail3','2021-05-30 23:32:01','2021-05-30 23:32:01');
/*!40000 ALTER TABLE `t_amenity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_application`
--

DROP TABLE IF EXISTS `t_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_application` (
  `f_id` bigint NOT NULL AUTO_INCREMENT,
  `f_source_user_id` bigint NOT NULL,
  `f_target_user_id` bigint NOT NULL,
  `f_house_id` bigint NOT NULL,
  `f_is_accepted` tinyint NOT NULL DEFAULT '0' COMMENT '0=no/1=yes',
  `f_start_date` date DEFAULT NULL,
  `f_end_date` date DEFAULT NULL,
  `f_guest_number` tinyint NOT NULL DEFAULT '0',
  `f_created_time` datetime DEFAULT NULL,
  `f_last_updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`f_id`),
  KEY `fk_app_user_source_idx` (`f_source_user_id`),
  KEY `fk_app_user_target_idx` (`f_target_user_id`),
  KEY `fk_app_house_idx` (`f_house_id`),
  CONSTRAINT `fk_app_house` FOREIGN KEY (`f_house_id`) REFERENCES `t_house` (`f_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_app_user_source` FOREIGN KEY (`f_source_user_id`) REFERENCES `t_user` (`f_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_app_user_target` FOREIGN KEY (`f_target_user_id`) REFERENCES `t_user` (`f_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_application`
--

LOCK TABLES `t_application` WRITE;
/*!40000 ALTER TABLE `t_application` DISABLE KEYS */;
INSERT INTO `t_application` VALUES (1,1,2,3,0,'2021-05-30','2021-06-30',5,'2021-05-31 00:08:37','2021-05-31 00:08:37'),(2,2,1,1,1,'2021-05-31','2021-06-10',1,'2021-05-31 00:08:37','2021-05-31 00:08:37');
/*!40000 ALTER TABLE `t_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_constraint`
--

DROP TABLE IF EXISTS `t_constraint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_constraint` (
  `f_id` bigint NOT NULL,
  `f_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `f_detail` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `f_created_time` datetime DEFAULT NULL,
  `f_last_updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_constraint`
--

LOCK TABLES `t_constraint` WRITE;
/*!40000 ALTER TABLE `t_constraint` DISABLE KEYS */;
INSERT INTO `t_constraint` VALUES (1,'constraint1','detail1','2021-05-30 23:31:42','2021-05-30 23:31:42'),(2,'constraint2','detail2','2021-05-30 23:31:45','2021-05-30 23:31:54'),(3,'constraint3','detail3','2021-05-30 23:32:01','2021-05-30 23:32:01');
/*!40000 ALTER TABLE `t_constraint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_house`
--

DROP TABLE IF EXISTS `t_house`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_house` (
  `f_id` bigint NOT NULL AUTO_INCREMENT,
  `f_user_id` bigint NOT NULL,
  `f_title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `f_area` int NOT NULL DEFAULT '0',
  `f_location` json DEFAULT NULL,
  `f_description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `is_published` tinyint NOT NULL DEFAULT '0' COMMENT '0=no/1=yes',
  `f_guest_number` tinyint NOT NULL DEFAULT '0',
  `f_picture` json DEFAULT NULL,
  `f_created_time` datetime DEFAULT NULL,
  `f_last_updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`f_id`),
  KEY `fk_house_user_idx` (`f_user_id`),
  CONSTRAINT `fk_house_user` FOREIGN KEY (`f_user_id`) REFERENCES `t_user` (`f_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_house`
--

LOCK TABLES `t_house` WRITE;
/*!40000 ALTER TABLE `t_house` DISABLE KEYS */;
INSERT INTO `t_house` VALUES (1,1,'title1',100,NULL,'description1',1,5,NULL,'2021-05-30 23:26:34','2021-05-30 23:26:34'),(2,2,'title2',150,NULL,'description2',0,10,NULL,'2021-05-30 23:26:58','2021-05-30 23:28:26'),(3,2,'title3',50,NULL,'description3',1,2,NULL,'2021-05-30 23:28:05','2021-05-30 23:28:05');
/*!40000 ALTER TABLE `t_house` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_house__amenity`
--

DROP TABLE IF EXISTS `t_house__amenity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_house__amenity` (
  `f_house_id` bigint NOT NULL,
  `f_amenity_id` bigint NOT NULL,
  `f_created_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`f_house_id`,`f_amenity_id`),
  KEY `fk_house__amenity_amenity_idx` (`f_amenity_id`),
  KEY `fk_house__amenity_house_idx` (`f_house_id`),
  CONSTRAINT `fk_house__amenity_amenity` FOREIGN KEY (`f_amenity_id`) REFERENCES `t_amenity` (`f_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_house__amenity_house` FOREIGN KEY (`f_house_id`) REFERENCES `t_house` (`f_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_house__amenity`
--

LOCK TABLES `t_house__amenity` WRITE;
/*!40000 ALTER TABLE `t_house__amenity` DISABLE KEYS */;
INSERT INTO `t_house__amenity` VALUES (1,1,NULL),(1,2,NULL),(2,1,NULL),(3,2,NULL),(3,3,NULL);
/*!40000 ALTER TABLE `t_house__amenity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_house__constraint`
--

DROP TABLE IF EXISTS `t_house__constraint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_house__constraint` (
  `f_house_id` bigint NOT NULL,
  `f_constraint_id` bigint NOT NULL,
  `f_created_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`f_house_id`,`f_constraint_id`),
  KEY `fk_house__constraint_constraint_idx` (`f_constraint_id`),
  CONSTRAINT `fk_house__constraint_constraint` FOREIGN KEY (`f_constraint_id`) REFERENCES `t_constraint` (`f_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_house__constraint_house` FOREIGN KEY (`f_house_id`) REFERENCES `t_house` (`f_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_house__constraint`
--

LOCK TABLES `t_house__constraint` WRITE;
/*!40000 ALTER TABLE `t_house__constraint` DISABLE KEYS */;
INSERT INTO `t_house__constraint` VALUES (1,1,NULL),(1,2,NULL),(2,1,NULL),(3,2,NULL),(3,3,NULL);
/*!40000 ALTER TABLE `t_house__constraint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_house__service`
--

DROP TABLE IF EXISTS `t_house__service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_house__service` (
  `f_house_id` bigint NOT NULL,
  `f_service_id` bigint NOT NULL,
  `f_created_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`f_house_id`,`f_service_id`),
  KEY `fk_house__service_service_idx` (`f_service_id`),
  CONSTRAINT `fk_house__service_house` FOREIGN KEY (`f_house_id`) REFERENCES `t_house` (`f_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_house__service_service` FOREIGN KEY (`f_service_id`) REFERENCES `t_service` (`f_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_house__service`
--

LOCK TABLES `t_house__service` WRITE;
/*!40000 ALTER TABLE `t_house__service` DISABLE KEYS */;
INSERT INTO `t_house__service` VALUES (1,1,NULL),(1,2,NULL),(2,1,NULL),(3,2,NULL),(3,3,NULL);
/*!40000 ALTER TABLE `t_house__service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_message`
--

DROP TABLE IF EXISTS `t_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_message` (
  `f_id` bigint NOT NULL AUTO_INCREMENT,
  `f_source_user_id` bigint NOT NULL,
  `f_target_user_id` bigint NOT NULL,
  `f_is_read` tinyint NOT NULL DEFAULT '0' COMMENT '0=no/1=yes',
  `f_content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `f_created_time` datetime DEFAULT NULL,
  `f_last_updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`f_id`),
  KEY `fk_message_user_source_idx` (`f_source_user_id`),
  KEY `fk_message_user_target_idx` (`f_target_user_id`),
  CONSTRAINT `fk_message_user_source` FOREIGN KEY (`f_source_user_id`) REFERENCES `t_user` (`f_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_message_user_target` FOREIGN KEY (`f_target_user_id`) REFERENCES `t_user` (`f_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_message`
--

LOCK TABLES `t_message` WRITE;
/*!40000 ALTER TABLE `t_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_service`
--

DROP TABLE IF EXISTS `t_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_service` (
  `f_id` bigint NOT NULL AUTO_INCREMENT,
  `f_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `f_detail` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `f_created_time` datetime DEFAULT NULL,
  `f_last_updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_service`
--

LOCK TABLES `t_service` WRITE;
/*!40000 ALTER TABLE `t_service` DISABLE KEYS */;
INSERT INTO `t_service` VALUES (1,'service1','detail1','2021-05-30 23:30:46','2021-05-30 23:30:46'),(2,'service2','detail2','2021-05-30 23:31:01','2021-05-30 23:31:01'),(3,'service3','detail3','2021-05-30 23:31:06','2021-05-30 23:31:06');
/*!40000 ALTER TABLE `t_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user` (
  `f_id` bigint NOT NULL AUTO_INCREMENT,
  `f_user_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_role` int NOT NULL DEFAULT '0' COMMENT '0=unset/1=user/2=admin',
  `f_display_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `f_email` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `f_phone` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `f_gender` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `f_language` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `f_description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `f_location` json DEFAULT NULL,
  `f_icon` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `f_created_time` datetime DEFAULT NULL,
  `f_last_updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`f_id`),
  UNIQUE KEY `username_UNIQUE` (`f_user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'user1','password1',0,'user_display1','user1@gmail.com','12312312312','male','english','description1',NULL,'','2021-05-30 23:20:43','2021-05-30 23:20:43'),(2,'user2','password2',1,'user_display2','user2@gmail.com','32132132132','female','french','description2',NULL,'','2021-05-30 23:21:19','2021-05-30 23:21:19');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-31  0:09:37
