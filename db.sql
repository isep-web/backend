CREATE DATABASE IF NOT EXISTS web /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE web;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: web
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @old_character_set_client = @@character_set_client */;
/*!40101 SET @old_character_set_results = @@character_set_results */;
/*!40101 SET @old_collation_connection = @@collation_connection */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @old_time_zone = @@time_zone */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @old_unique_checks = @@unique_checks, UNIQUE_CHECKS = 0 */;
/*!40014 SET @old_foreign_key_checks = @@foreign_key_checks, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @old_sql_mode = @@sql_mode, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @old_sql_notes = @@sql_notes, SQL_NOTES = 0 */;

--
-- Table structure for table `t_amenity`
--

DROP TABLE IF EXISTS t_amenity;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE t_amenity
(
    f_id                bigint                                                        NOT NULL AUTO_INCREMENT,
    f_name              varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '',
    f_detail            varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
    f_created_time      datetime                                                               DEFAULT NULL,
    f_last_updated_time datetime                                                               DEFAULT NULL,
    PRIMARY KEY (f_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 9
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_amenity`
--

LOCK TABLES t_amenity WRITE;
/*!40000 ALTER TABLE t_amenity
    DISABLE KEYS */;
INSERT INTO t_amenity
VALUES (1, 'TV', 'Have TV', '2021-05-30 23:31:42', '2021-05-30 23:31:42'),
       (2, 'WiFi', 'Have WiFi', '2021-05-30 23:31:45', '2021-05-30 23:31:54'),
       (3, 'A/C', 'Have A/C', '2021-05-30 23:32:01', '2021-05-30 23:32:01'),
       (4, 'Cook', 'Have Cook', '2021-05-30 23:31:42', '2021-05-30 23:31:42'),
       (5, 'Bathtub', 'Have Bathtub', '2021-05-30 23:31:42', '2021-05-30 23:31:42'),
       (6, 'Heating system', 'Have Heating system', '2021-05-30 23:31:42', '2021-05-30 23:31:42'),
       (7, 'Swimming pool', 'Have Swimming pool', '2021-05-30 23:31:42', '2021-05-30 23:31:42'),
       (8, 'Elevator', 'Have Elevator', '2021-05-30 23:31:42', '2021-05-30 23:31:42');
/*!40000 ALTER TABLE t_amenity
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_application`
--

DROP TABLE IF EXISTS t_application;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE t_application
(
    f_id                bigint NOT NULL AUTO_INCREMENT,
    f_source_user_id    bigint NOT NULL,
    f_target_user_id    bigint NOT NULL,
    f_house_id          bigint NOT NULL,
    f_is_accepted       int    NOT NULL DEFAULT '0' COMMENT '0=no/1=yes',
    f_start_date        date            DEFAULT NULL,
    f_end_date          date            DEFAULT NULL,
    f_guest_number      int    NOT NULL DEFAULT '0',
    f_created_time      datetime        DEFAULT NULL,
    f_last_updated_time datetime        DEFAULT NULL,
    PRIMARY KEY (f_id),
    KEY fk_app_user_source_idx (f_source_user_id),
    KEY fk_app_user_target_idx (f_target_user_id),
    KEY fk_app_house_idx (f_house_id),
    CONSTRAINT fk_app_house FOREIGN KEY (f_house_id) REFERENCES t_house (f_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_app_user_source FOREIGN KEY (f_source_user_id) REFERENCES t_user (f_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_app_user_target FOREIGN KEY (f_target_user_id) REFERENCES t_user (f_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_application`
--

LOCK TABLES t_application WRITE;
/*!40000 ALTER TABLE t_application
    DISABLE KEYS */;
INSERT INTO t_application
VALUES (1, 1, 2, 3, 0, '2021-05-30', '2021-06-30', 5, '2021-05-31 00:08:37', '2021-05-31 00:08:37'),
       (2, 2, 1, 1, 1, '2021-05-31', '2021-06-10', 1, '2021-05-31 00:08:37', '2021-05-31 00:08:37');
/*!40000 ALTER TABLE t_application
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_constraint`
--

DROP TABLE IF EXISTS t_constraint;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE t_constraint
(
    f_id                bigint                                                        NOT NULL,
    f_name              varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '',
    f_detail            varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
    f_created_time      datetime                                                               DEFAULT NULL,
    f_last_updated_time datetime                                                               DEFAULT NULL,
    PRIMARY KEY (f_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_constraint`
--

LOCK TABLES t_constraint WRITE;
/*!40000 ALTER TABLE t_constraint
    DISABLE KEYS */;
INSERT INTO t_constraint
VALUES (1, 'Smoke', 'No smoking', '2021-05-30 23:31:42', '2021-05-30 23:31:42'),
       (2, 'Children', 'No children allowed', '2021-05-30 23:31:45', '2021-05-30 23:31:54'),
       (3, 'Pet', 'No pets allowed', '2021-05-30 23:32:01', '2021-05-30 23:32:01');
/*!40000 ALTER TABLE t_constraint
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_house`
--

DROP TABLE IF EXISTS t_house;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE t_house
(
    f_id                bigint                                                        NOT NULL AUTO_INCREMENT,
    f_user_id           bigint                                                        NOT NULL,
    f_title             varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
    f_area              int                                                           NOT NULL DEFAULT '0',
    f_location          varchar(255) COLLATE utf8mb4_general_ci                                DEFAULT NULL,
    f_description       varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
    is_published        int                                                           NOT NULL DEFAULT '1' COMMENT '0=no/1=yes',
    f_guest_number      int                                                           NOT NULL DEFAULT '0',
    f_created_time      datetime                                                               DEFAULT NULL,
    f_last_updated_time datetime                                                               DEFAULT NULL,
    PRIMARY KEY (f_id),
    KEY fk_house_user_idx (f_user_id),
    CONSTRAINT fk_house_user FOREIGN KEY (f_user_id) REFERENCES t_user (f_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_house`
--

LOCK TABLES t_house WRITE;
/*!40000 ALTER TABLE t_house
    DISABLE KEYS */;
INSERT INTO t_house
VALUES (1, 1, 'title1', 100, NULL, 'description1', 1, 5, '2021-05-30 23:26:34',
        '2021-06-01 23:22:20'),
       (2, 2, 'title2', 150, NULL, 'description2', 0, 10, '2021-05-30 23:26:58',
        '2021-05-30 23:28:26'),
       (3, 2, 'title3', 50, NULL, 'description3', 1, 2, '2021-05-30 23:28:05',
        '2021-05-30 23:28:05');
/*!40000 ALTER TABLE t_house
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_house__amenity`
--

DROP TABLE IF EXISTS t_house__amenity;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE t_house__amenity
(
    f_house_id     bigint NOT NULL,
    f_amenity_id   bigint NOT NULL,
    f_created_time datetime DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (f_house_id, f_amenity_id),
    KEY fk_house__amenity_amenity_idx (f_amenity_id),
    KEY fk_house__amenity_house_idx (f_house_id),
    CONSTRAINT fk_house__amenity_amenity FOREIGN KEY (f_amenity_id) REFERENCES t_amenity (f_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_house__amenity_house FOREIGN KEY (f_house_id) REFERENCES t_house (f_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_house__amenity`
--

LOCK TABLES t_house__amenity WRITE;
/*!40000 ALTER TABLE t_house__amenity
    DISABLE KEYS */;
INSERT INTO t_house__amenity
VALUES (1, 1, '2021-06-01 23:22:20'),
       (1, 2, '2021-06-01 23:22:20'),
       (1, 3, '2021-06-01 23:22:20'),
       (2, 1, '2021-06-01 23:22:20'),
       (3, 2, '2021-06-01 23:22:20'),
       (3, 3, '2021-06-01 23:22:20');
/*!40000 ALTER TABLE t_house__amenity
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_house__constraint`
--

DROP TABLE IF EXISTS t_house__constraint;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE t_house__constraint
(
    f_house_id      bigint NOT NULL,
    f_constraint_id bigint NOT NULL,
    f_created_time  datetime DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (f_house_id, f_constraint_id),
    KEY fk_house__constraint_constraint_idx (f_constraint_id),
    CONSTRAINT fk_house__constraint_constraint FOREIGN KEY (f_constraint_id) REFERENCES t_constraint (f_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_house__constraint_house FOREIGN KEY (f_house_id) REFERENCES t_house (f_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_house__constraint`
--

LOCK TABLES t_house__constraint WRITE;
/*!40000 ALTER TABLE t_house__constraint
    DISABLE KEYS */;
INSERT INTO t_house__constraint
VALUES (1, 1, NULL),
       (1, 2, NULL),
       (2, 1, NULL),
       (3, 2, NULL),
       (3, 3, NULL);
/*!40000 ALTER TABLE t_house__constraint
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_house__service`
--

DROP TABLE IF EXISTS t_house__service;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE t_house__service
(
    f_house_id     bigint NOT NULL,
    f_service_id   bigint NOT NULL,
    f_created_time datetime DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (f_house_id, f_service_id),
    KEY fk_house__service_service_idx (f_service_id),
    CONSTRAINT fk_house__service_house FOREIGN KEY (f_house_id) REFERENCES t_house (f_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_house__service_service FOREIGN KEY (f_service_id) REFERENCES t_service (f_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_house__service`
--

LOCK TABLES t_house__service WRITE;
/*!40000 ALTER TABLE t_house__service
    DISABLE KEYS */;
INSERT INTO t_house__service
VALUES (1, 1, NULL),
       (1, 2, NULL),
       (1, 3, NULL),
       (1, 4, NULL),
       (2, 1, NULL),
       (2, 2, NULL),
       (2, 3, NULL),
       (3, 2, NULL),
       (3, 3, NULL),
       (3, 4, NULL);
/*!40000 ALTER TABLE t_house__service
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_message`
--

DROP TABLE IF EXISTS t_message;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE t_message
(
    f_id                bigint                                                        NOT NULL AUTO_INCREMENT,
    f_source_user_id    bigint                                                        NOT NULL,
    f_target_user_id    bigint                                                        NOT NULL,
    f_is_read           int                                                           NOT NULL DEFAULT '0' COMMENT '0=no/1=yes',
    f_content           varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
    f_created_time      datetime                                                               DEFAULT NULL,
    f_last_updated_time datetime                                                               DEFAULT NULL,
    PRIMARY KEY (f_id),
    KEY fk_message_user_source_idx (f_source_user_id),
    KEY fk_message_user_target_idx (f_target_user_id),
    CONSTRAINT fk_message_user_source FOREIGN KEY (f_source_user_id) REFERENCES t_user (f_id) ON UPDATE CASCADE,
    CONSTRAINT fk_message_user_target FOREIGN KEY (f_target_user_id) REFERENCES t_user (f_id) ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_message`
--

LOCK TABLES t_message WRITE;
/*!40000 ALTER TABLE t_message
    DISABLE KEYS */;
/*!40000 ALTER TABLE t_message
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_permission`
--

DROP TABLE IF EXISTS t_permission;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE t_permission
(
    f_id                bigint                                 NOT NULL AUTO_INCREMENT,
    f_name              varchar(60) COLLATE utf8mb4_general_ci NOT NULL,
    f_url               varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL,
    f_method            varchar(20) COLLATE utf8mb4_general_ci  DEFAULT NULL,
    f_created_time      datetime                                DEFAULT NULL,
    f_last_updated_time datetime                                DEFAULT NULL,
    PRIMARY KEY (f_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_permission`
--

LOCK TABLES t_permission WRITE;
/*!40000 ALTER TABLE t_permission
    DISABLE KEYS */;
INSERT INTO t_permission
VALUES (1, 'amenities r', '/amenities', 'R', '2021-06-07 22:07:07', '2021-06-07 22:07:07'),
       (2, 'applications cr', '/applications', 'CR', '2021-06-07 22:07:07', '2021-06-07 22:07:07'),
       (3, 'applications/id crud', '/applications/*/**', 'CURD', '2021-06-07 22:07:07',
        '2021-06-07 22:07:07'),
       (4, 'constraints r', '/constraints', 'R', '2021-06-07 22:07:07', '2021-06-07 22:07:07'),
       (5, 'houses cr', '/houses', 'CR', '2021-06-07 22:07:07', '2021-06-07 22:07:07'),
       (6, 'houses/id crud', '/houses/*/**', 'CURD', '2021-06-07 22:07:07', '2021-06-07 22:07:07'),
       (7, 'picture cr', '/pictures', 'CR', '2021-06-07 22:07:07', '2021-06-07 22:07:07'),
       (8, 'picture/id crud', '/pictures/*/**', 'CURD', '2021-06-07 22:07:07',
        '2021-06-07 22:07:07'),
       (9, 'services r', '/services', 'R', '2021-06-07 22:07:07', '2021-06-07 22:07:07'),
       (10, 'users cr', '/users', 'CR', '2021-06-07 22:07:07', '2021-06-07 22:07:07'),
       (11, 'users/id crud', '/users/*/**', 'CURD', '2021-06-07 22:07:07', '2021-06-07 22:07:07');
/*!40000 ALTER TABLE t_permission
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_picture`
--

DROP TABLE IF EXISTS t_picture;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE t_picture
(
    f_id                bigint                                 NOT NULL AUTO_INCREMENT,
    f_content_id        varchar(100) COLLATE utf8mb4_general_ci         DEFAULT NULL,
    f_content_length    bigint                                          DEFAULT NULL,
    f_mime_type         varchar(45) COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'text/plain',
    f_type              int                                             DEFAULT '1' COMMENT '0=avatar/1=house_photo',
    f_house_id          bigint                                          DEFAULT NULL,
    f_user_id           bigint                                          DEFAULT NULL,
    f_created_time      datetime                                        DEFAULT NULL,
    f_last_updated_time datetime                                        DEFAULT NULL,
    PRIMARY KEY (f_id),
    KEY fk_picture_house_idx (f_house_id),
    KEY fk_picture_user_idx (f_user_id),
    CONSTRAINT fk_picture_house FOREIGN KEY (f_house_id) REFERENCES t_house (f_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_picture_user FOREIGN KEY (f_user_id) REFERENCES t_user (f_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_picture`
--

LOCK TABLES t_picture WRITE;
/*!40000 ALTER TABLE t_picture
    DISABLE KEYS */;
/*!40000 ALTER TABLE t_picture
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS t_role;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE t_role
(
    f_id                bigint                                 NOT NULL AUTO_INCREMENT,
    f_name              varchar(60) COLLATE utf8mb4_general_ci NOT NULL,
    f_description       varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
    f_created_time      datetime                                DEFAULT NULL,
    f_last_updated_time datetime                                DEFAULT NULL,
    PRIMARY KEY (f_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES t_role WRITE;
/*!40000 ALTER TABLE t_role
    DISABLE KEYS */;
INSERT INTO t_role
VALUES (1, 'admin', 'administrator', '2021-06-07 22:05:31', '2021-06-07 22:05:31'),
       (2, 'user', 'normal user', '2021-06-07 22:05:31', '2021-06-07 22:05:31');
/*!40000 ALTER TABLE t_role
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role__permission`
--

DROP TABLE IF EXISTS t_role__permission;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE t_role__permission
(
    f_role_id       bigint NOT NULL,
    f_permission_id bigint NOT NULL,
    f_created_time  datetime DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (f_role_id, f_permission_id),
    KEY fk_role__permission_permission_id_idx (f_permission_id),
    CONSTRAINT fk_role__permission_permission_id FOREIGN KEY (f_permission_id) REFERENCES t_permission (f_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_role__permission_role_id FOREIGN KEY (f_role_id) REFERENCES t_role (f_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role__permission`
--

LOCK TABLES t_role__permission WRITE;
/*!40000 ALTER TABLE t_role__permission
    DISABLE KEYS */;
INSERT INTO t_role__permission
VALUES (1, 1, '2021-06-07 22:07:12'),
       (1, 2, '2021-06-07 22:07:12'),
       (1, 3, '2021-06-07 22:07:12'),
       (1, 4, '2021-06-07 22:07:12'),
       (1, 5, '2021-06-07 22:07:12'),
       (1, 6, '2021-06-07 22:07:12'),
       (1, 7, '2021-06-07 22:07:12'),
       (1, 8, '2021-06-07 22:07:12'),
       (1, 9, '2021-06-07 22:07:12'),
       (1, 10, '2021-06-07 22:07:12'),
       (1, 11, '2021-06-07 22:07:12'),
       (2, 1, '2021-06-07 22:07:12'),
       (2, 2, '2021-06-07 22:07:12'),
       (2, 3, '2021-06-07 22:07:12'),
       (2, 4, '2021-06-07 22:07:12'),
       (2, 5, '2021-06-07 22:07:12'),
       (2, 6, '2021-06-07 22:07:12'),
       (2, 7, '2021-06-07 22:07:12'),
       (2, 8, '2021-06-07 22:07:12'),
       (2, 9, '2021-06-07 22:07:12'),
       (2, 11, '2021-06-07 22:07:12');
/*!40000 ALTER TABLE t_role__permission
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_service`
--

DROP TABLE IF EXISTS t_service;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE t_service
(
    f_id                bigint                                                        NOT NULL AUTO_INCREMENT,
    f_name              varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '',
    f_detail            varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
    f_created_time      datetime                                                               DEFAULT NULL,
    f_last_updated_time datetime                                                               DEFAULT NULL,
    PRIMARY KEY (f_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_service`
--

LOCK TABLES t_service WRITE;
/*!40000 ALTER TABLE t_service
    DISABLE KEYS */;
INSERT INTO t_service
VALUES (1, 'Cleaning', 'Keep the room clean', '2021-05-30 23:30:46', '2021-05-30 23:30:46'),
       (2, 'Feed pet', 'Feed the pet on time', '2021-05-30 23:31:01', '2021-05-30 23:31:01'),
       (3, 'Water plant', 'Watering plants regularly', '2021-05-30 23:31:06',
        '2021-05-30 23:31:06');
/*!40000 ALTER TABLE t_service
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS t_user;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE t_user
(
    f_id                bigint                                                        NOT NULL AUTO_INCREMENT,
    f_username          varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    f_password          varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    f_status            int                                                           NOT NULL DEFAULT '1' COMMENT '0=banned/1=normal',
    f_display_name      varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '',
    f_email             varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '',
    f_phone             varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '',
    f_sex               int                                                           NOT NULL DEFAULT '0' COMMENT '0=unset/1=male/2=female',
    f_language          varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
    f_description       varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
    f_location          varchar(255) COLLATE utf8mb4_general_ci                                DEFAULT NULL,
    f_created_time      datetime                                                               DEFAULT NULL,
    f_last_updated_time datetime                                                               DEFAULT NULL,
    PRIMARY KEY (f_id),
    UNIQUE KEY username_unique (f_username),
    UNIQUE KEY f_email_unique (f_email),
    UNIQUE KEY f_phone_unique (f_phone)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES t_user WRITE;
/*!40000 ALTER TABLE t_user
    DISABLE KEYS */;
INSERT INTO t_user
VALUES (1, 'admin', '$2a$10$adUzNRkdHzAFVGeQRWv3SeOXOIYJ0./h1J2.kDVasUatzLNOUspC2', 1,
        'admin_display', 'admin@mail.com', '12312312312', 1, 'english', 'description1', NULL,
        '2021-05-30 23:20:43', '2021-05-30 23:20:43'),
       (2, 'user', '$2a$10$Gc.qjF3ryza7hlcByiSK5uW9DeIrcGBd.bkINwCFMQfmuKo6QVreq', 1,
        'user_display', 'user@mail.com', '32132132132', 2, 'french', 'description2', NULL,
        '2021-05-30 23:21:19', '2021-05-30 23:21:19');
/*!40000 ALTER TABLE t_user
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user__role`
--

DROP TABLE IF EXISTS t_user__role;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE t_user__role
(
    f_user_id      bigint NOT NULL,
    f_role_id      bigint NOT NULL,
    f_created_time datetime DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (f_user_id, f_role_id),
    KEY fk_user__role_role_id_idx (f_role_id),
    CONSTRAINT fk_user__role_role_id FOREIGN KEY (f_role_id) REFERENCES t_role (f_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_user__role_user_id FOREIGN KEY (f_user_id) REFERENCES t_user (f_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user__role`
--

LOCK TABLES t_user__role WRITE;
/*!40000 ALTER TABLE t_user__role
    DISABLE KEYS */;
INSERT INTO t_user__role
VALUES (1, 1, '2021-06-07 22:10:51'),
       (2, 2, '2021-06-07 22:10:51');
/*!40000 ALTER TABLE t_user__role
    ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @old_time_zone */;

/*!40101 SET SQL_MODE = @old_sql_mode */;
/*!40014 SET FOREIGN_KEY_CHECKS = @old_foreign_key_checks */;
/*!40014 SET UNIQUE_CHECKS = @old_unique_checks */;
/*!40101 SET CHARACTER_SET_CLIENT = @old_character_set_client */;
/*!40101 SET CHARACTER_SET_RESULTS = @old_character_set_results */;
/*!40101 SET COLLATION_CONNECTION = @old_collation_connection */;
/*!40111 SET SQL_NOTES = @old_sql_notes */;

-- Dump completed on 2021-06-08 15:08:05
