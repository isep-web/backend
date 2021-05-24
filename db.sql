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
  `f_name` varchar(40) NOT NULL DEFAULT '',
  `f_detail` varchar(200) NOT NULL DEFAULT '',
  `f_created_time` datetime DEFAULT NULL,
  `f_last_updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_constraint`
--

DROP TABLE IF EXISTS `t_constraint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_constraint` (
  `f_id` bigint NOT NULL,
  `f_name` varchar(40) NOT NULL DEFAULT '',
  `f_detail` varchar(200) NOT NULL DEFAULT '',
  `f_created_time` datetime DEFAULT NULL,
  `f_last_updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_house`
--

DROP TABLE IF EXISTS `t_house`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_house` (
  `f_id` bigint NOT NULL AUTO_INCREMENT,
  `f_user_id` bigint NOT NULL,
  `f_title` varchar(100) NOT NULL DEFAULT '',
  `f_area` int NOT NULL DEFAULT '0',
  `f_location` json DEFAULT NULL,
  `f_description` varchar(500) NOT NULL DEFAULT '',
  `is_published` tinyint NOT NULL DEFAULT '0' COMMENT '0=no/1=yes',
  `f_guest_number` tinyint NOT NULL DEFAULT '0',
  `f_picture` json DEFAULT NULL,
  `f_created_time` datetime DEFAULT NULL,
  `f_last_updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`f_id`),
  KEY `fk_house_user_idx` (`f_user_id`),
  CONSTRAINT `fk_house_user` FOREIGN KEY (`f_user_id`) REFERENCES `t_user` (`f_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_house__amenity`
--

DROP TABLE IF EXISTS `t_house__amenity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_house__amenity` (
  `f_house_id` bigint NOT NULL,
  `f_amenity_id` bigint NOT NULL,
  `f_created_time` datetime DEFAULT NULL,
  PRIMARY KEY (`f_house_id`,`f_amenity_id`),
  KEY `fk_house__amenity_amenity_idx` (`f_amenity_id`),
  KEY `fk_house__amenity_house_idx` (`f_house_id`),
  CONSTRAINT `fk_house__amenity_amenity` FOREIGN KEY (`f_amenity_id`) REFERENCES `t_amenity` (`f_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_house__amenity_house` FOREIGN KEY (`f_house_id`) REFERENCES `t_house` (`f_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_house__constraint`
--

DROP TABLE IF EXISTS `t_house__constraint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_house__constraint` (
  `f_house_id` bigint NOT NULL,
  `f_constraint_id` bigint NOT NULL,
  `f_created_time` datetime DEFAULT NULL,
  PRIMARY KEY (`f_house_id`,`f_constraint_id`),
  KEY `fk_house__constraint_constraint_idx` (`f_constraint_id`),
  CONSTRAINT `fk_house__constraint_constraint` FOREIGN KEY (`f_constraint_id`) REFERENCES `t_constraint` (`f_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_house__constraint_house` FOREIGN KEY (`f_house_id`) REFERENCES `t_house` (`f_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_house__service`
--

DROP TABLE IF EXISTS `t_house__service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_house__service` (
  `f_house_id` bigint NOT NULL,
  `f_service_id` bigint NOT NULL,
  `f_created_time` datetime DEFAULT NULL,
  PRIMARY KEY (`f_house_id`,`f_service_id`),
  KEY `fk_house__service_service_idx` (`f_service_id`),
  CONSTRAINT `fk_house__service_house` FOREIGN KEY (`f_house_id`) REFERENCES `t_house` (`f_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_house__service_service` FOREIGN KEY (`f_service_id`) REFERENCES `t_service` (`f_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `f_content` varchar(500) NOT NULL DEFAULT '',
  `f_created_time` datetime DEFAULT NULL,
  `f_last_updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`f_id`),
  KEY `fk_message_user_source_idx` (`f_source_user_id`),
  KEY `fk_message_user_target_idx` (`f_target_user_id`),
  CONSTRAINT `fk_message_user_source` FOREIGN KEY (`f_source_user_id`) REFERENCES `t_user` (`f_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_message_user_target` FOREIGN KEY (`f_target_user_id`) REFERENCES `t_user` (`f_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_service`
--

DROP TABLE IF EXISTS `t_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_service` (
  `f_id` bigint NOT NULL AUTO_INCREMENT,
  `f_name` varchar(40) NOT NULL DEFAULT '',
  `f_detail` varchar(200) NOT NULL DEFAULT '',
  `f_created_time` datetime DEFAULT NULL,
  `f_last_updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user` (
  `f_id` bigint NOT NULL AUTO_INCREMENT,
  `f_user_name` varchar(60) NOT NULL,
  `f_password` varchar(60) NOT NULL,
  `f_role` int NOT NULL DEFAULT '0' COMMENT '0=unset/1=user/2=admin',
  `f_created_time` datetime DEFAULT NULL,
  `f_last_updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`f_id`),
  UNIQUE KEY `username_UNIQUE` (`f_user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_userdtl`
--

DROP TABLE IF EXISTS `t_userdtl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_userdtl` (
  `f_id` bigint NOT NULL AUTO_INCREMENT,
  `f_user_id` bigint NOT NULL,
  `f_display_name` varchar(60) NOT NULL DEFAULT '',
  `f_email` varchar(60) NOT NULL DEFAULT '',
  `f_phone` varchar(45) NOT NULL DEFAULT '',
  `f_birth_date` date DEFAULT NULL,
  `f_gender` varchar(45) NOT NULL DEFAULT '',
  `f_language` varchar(100) NOT NULL DEFAULT '',
  `f_description` varchar(500) NOT NULL DEFAULT '',
  `f_location` json DEFAULT NULL,
  `f_picture` json DEFAULT NULL,
  `f_created_time` datetime DEFAULT NULL,
  `f_last_updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`f_id`),
  KEY `fk_userdtl_user_idx` (`f_user_id`),
  CONSTRAINT `fk_userdtl_user` FOREIGN KEY (`f_user_id`) REFERENCES `t_user` (`f_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-24 21:45:40
