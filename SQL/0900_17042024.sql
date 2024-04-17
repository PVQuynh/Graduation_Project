-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 202.191.56.11    Database: hustappuser
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answer` (
  `answer_id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `image_location` varchar(255) DEFAULT NULL,
  `is_correct` bit(1) NOT NULL,
  `video_location` varchar(255) DEFAULT NULL,
  `question_id` bigint DEFAULT NULL,
  PRIMARY KEY (`answer_id`),
  KEY `FK8frr4bcabmmeyyu60qt7iiblo` (`question_id`),
  CONSTRAINT `FK8frr4bcabmmeyyu60qt7iiblo` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (1,'phamquynhltbn12@gmail.com','2024-04-08 10:24:14.873000','phamquynhltbn12@gmail.com','2024-04-08 10:24:14.873000','Con cá','https://wetalk.ibme.edu.vn/upload/vocabularies//0119_con%20c%C3%A1.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0119_con%20c%C3%A1.mp4',1),(2,'phamquynhltbn12@gmail.com','2024-04-08 10:24:14.876000','phamquynhltbn12@gmail.com','2024-04-08 10:24:14.876000','Con chim','https://wetalk.ibme.edu.vn/upload/vocabularies//0121_con%20chim.gif',_binary '\0','https://wetalk.ibme.edu.vn/upload/vocabularies//0121_con%20chim.mp4',1);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact` (
  `contact_id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `avatar_location` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,'anonymousUser','2024-04-08 10:04:56.568000','anonymousUser','2024-04-08 15:43:39.415000','https://wetalk.ibme.edu.vn/upload/hust-app//pvq.jpg','phamquynhltbn12@gmail.com','Phạm Văn Quỳnh ltbn'),(2,'anonymousUser','2024-04-08 10:16:07.370000','anonymousUser','2024-04-08 10:16:07.370000',NULL,'quynh12712053@gmail.com','Phạm Văn Quỳnh 127'),(3,'anonymousUser','2024-04-08 10:17:30.919000','anonymousUser','2024-04-08 10:17:30.919000',NULL,'quynh171217@gmail.com','Pham Van Quynh 17'),(4,'anonymousUser','2024-04-08 12:21:15.962000','anonymousUser','2024-04-11 16:34:25.882000','https://wetalk.ibme.edu.vn/upload/hust-app//image.png','truong8dt@gmail.com','Nguyễn Văn Trường'),(5,'anonymousUser','2024-04-08 12:22:35.137000','anonymousUser','2024-04-08 12:22:35.137000',NULL,'truongnguyenduc935@gmail.com','Nguyễn Văn Minh'),(6,'anonymousUser','2024-04-08 14:25:46.532000','anonymousUser','2024-04-09 08:28:15.830000','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','doanhtv2759@gmail.com','Doanh'),(7,'anonymousUser','2024-04-08 15:55:43.505000','anonymousUser','2024-04-08 15:55:43.505000',NULL,'hoangquanghuy@gmail.com','Hoàng Huy'),(8,'anonymousUser','2024-04-11 04:48:45.050000','anonymousUser','2024-04-16 15:08:04.664000','https://wetalk.ibme.edu.vn/upload/media-general//9d9e23b9802028befafb052f7c2af883.jpg','vdoanh301@gmail.com','DoanhPro'),(10,'anonymousUser','2024-04-13 02:14:21.899000','anonymousUser','2024-04-13 02:14:21.899000',NULL,'chuyenp32@gmail.com','chuyen'),(11,'anonymousUser','2024-04-13 02:30:55.332000','anonymousUser','2024-04-13 02:30:55.332000',NULL,'phamdoan060801@gmail.com','chuyendz'),(12,'anonymousUser','2024-04-15 11:31:34.015000','anonymousUser','2024-04-15 11:31:34.015000',NULL,'hqhuy.bme@gmail.com','Hoàng Huy BME');
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversation`
--

DROP TABLE IF EXISTS `conversation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conversation` (
  `conversation_id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `conversation_name` varchar(255) DEFAULT NULL,
  `conversation_type` enum('GROUP','SINGLE') DEFAULT NULL,
  PRIMARY KEY (`conversation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation`
--

LOCK TABLES `conversation` WRITE;
/*!40000 ALTER TABLE `conversation` DISABLE KEYS */;
INSERT INTO `conversation` VALUES (1,'phamquynhltbn12@gmail.com','2024-04-08 10:18:31.698000','phamquynhltbn12@gmail.com','2024-04-08 10:18:31.698000','phamquynhltbn12@gmail.com_phamquynhltbn12@gmail.com','SINGLE'),(2,'truong8dt@gmail.com','2024-04-08 12:22:56.898000','truong8dt@gmail.com','2024-04-08 12:22:56.898000','truong8dt@gmail.com_truong8dt@gmail.com','SINGLE'),(3,'doanhtv2759@gmail.com','2024-04-08 15:27:59.658000','doanhtv2759@gmail.com','2024-04-08 15:27:59.658000','doanhtv2759@gmail.com_doanhtv2759@gmail.com','SINGLE'),(4,'hoangquanghuy@gmail.com','2024-04-10 01:56:46.426000','hoangquanghuy@gmail.com','2024-04-10 01:56:46.426000','hoangquanghuy@gmail.com_hoangquanghuy@gmail.com','SINGLE'),(5,'hoangquanghuy@gmail.com','2024-04-10 01:57:08.324000','hoangquanghuy@gmail.com','2024-04-10 01:57:08.324000','hoangquanghuy@gmail.com_hoangquanghuy@gmail.com','SINGLE'),(6,'vdoanh301@gmail.com','2024-04-11 04:49:42.036000','vdoanh301@gmail.com','2024-04-11 04:49:42.036000','vdoanh301@gmail.com_vdoanh301@gmail.com','SINGLE'),(7,'doanhtv2759@gmail.com','2024-04-14 13:48:39.993000','doanhtv2759@gmail.com','2024-04-14 13:48:39.993000','doanhtv2759@gmail.com_doanhtv2759@gmail.com','SINGLE'),(8,'doanhtv2759@gmail.com','2024-04-14 13:48:46.079000','doanhtv2759@gmail.com','2024-04-14 13:48:46.079000','doanhtv2759@gmail.com_doanhtv2759@gmail.com','SINGLE'),(9,'doanhtv2759@gmail.com','2024-04-14 13:49:23.657000','doanhtv2759@gmail.com','2024-04-14 13:49:23.657000','doanhtv2759@gmail.com_doanhtv2759@gmail.com','SINGLE'),(10,'doanhtv2759@gmail.com','2024-04-14 13:49:29.167000','doanhtv2759@gmail.com','2024-04-14 13:49:29.167000','doanhtv2759@gmail.com_doanhtv2759@gmail.com','SINGLE'),(11,'doanhtv2759@gmail.com','2024-04-14 13:49:37.321000','doanhtv2759@gmail.com','2024-04-14 13:49:37.321000','doanhtv2759@gmail.com_doanhtv2759@gmail.com','SINGLE'),(12,'phamquynhltbn12@gmail.com','2024-04-15 03:29:58.986000','phamquynhltbn12@gmail.com','2024-04-15 03:29:58.986000','phamquynhltbn12@gmail.com_phamquynhltbn12@gmail.com','SINGLE'),(13,'phamquynhltbn12@gmail.com','2024-04-15 03:30:05.170000','phamquynhltbn12@gmail.com','2024-04-15 03:30:05.170000','phamquynhltbn12@gmail.com_phamquynhltbn12@gmail.com','SINGLE');
/*!40000 ALTER TABLE `conversation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_collection`
--

DROP TABLE IF EXISTS `data_collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `data_collection` (
  `data_collection_id` bigint NOT NULL AUTO_INCREMENT,
  `volunteer_email` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `admin_email` varchar(255) DEFAULT NULL,
  `data_location` varchar(255) DEFAULT NULL,
  `feed_back` varchar(255) DEFAULT NULL,
  `score` float NOT NULL,
  `status` int NOT NULL,
  `vocabulary_id` bigint DEFAULT NULL,
  PRIMARY KEY (`data_collection_id`),
  KEY `FKlp6aat9pduh93prfhiykbv1r5` (`vocabulary_id`),
  CONSTRAINT `FKlp6aat9pduh93prfhiykbv1r5` FOREIGN KEY (`vocabulary_id`) REFERENCES `vocabulary` (`vocabulary_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_collection`
--

LOCK TABLES `data_collection` WRITE;
/*!40000 ALTER TABLE `data_collection` DISABLE KEYS */;
INSERT INTO `data_collection` VALUES (1,'anonymousUser','2024-04-15 11:48:00.955000','truong8dt@gmail.com','2024-04-16 15:56:26.681000','hoangquanghuy@gmail.com','https://wetalk.ibme.edu.vn/upload/data-collection//IMG_20240406_155500_1.jpg','',6.5,300,NULL),(2,'hqhuy.bme@gmail.com','2024-04-15 12:33:41.523000','truong8dt@gmail.com','2024-04-16 15:56:26.682000','hoangquanghuy@gmail.com','https://wetalk.ibme.edu.vn/upload/data-collection//Screenshot_20240415-184831.png','',6.5,300,NULL),(3,'hqhuy.bme@gmail.com','2024-04-15 12:33:41.533000','truong8dt@gmail.com','2024-04-16 15:56:26.682000','hoangquanghuy@gmail.com','https://wetalk.ibme.edu.vn/upload/data-collection//Screenshot_20240415-184831.png','',1,300,NULL),(4,'truong8dt@gmail.com','2024-04-16 15:40:27.803000','truong8dt@gmail.com','2024-04-16 16:14:48.331000','truong8dt@gmail.com','https://wetalk.ibme.edu.vn/upload/hust-app//captured_image_1713282027058.jpg','Lỗi',5,300,9),(5,'truong8dt@gmail.com','2024-04-16 16:17:03.644000','truong8dt@gmail.com','2024-04-16 16:17:22.387000','truong8dt@gmail.com','https://wetalk.ibme.edu.vn/upload/hust-app//volunteer_con%20b%C3%B2_1713284217403.mp4','Sai tay',6,300,72);
/*!40000 ALTER TABLE `data_collection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend_ship`
--

DROP TABLE IF EXISTS `friend_ship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friend_ship` (
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `status` int NOT NULL,
  `send_friend_id` bigint NOT NULL,
  `accept_friend_id` bigint NOT NULL,
  PRIMARY KEY (`accept_friend_id`,`send_friend_id`),
  KEY `FK1aao5ce4qv11rxt2ae94ix7r0` (`send_friend_id`),
  CONSTRAINT `FK1aao5ce4qv11rxt2ae94ix7r0` FOREIGN KEY (`send_friend_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKg0yu0k5m1wq1yg4usf0xmyuxu` FOREIGN KEY (`accept_friend_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend_ship`
--

LOCK TABLES `friend_ship` WRITE;
/*!40000 ALTER TABLE `friend_ship` DISABLE KEYS */;
INSERT INTO `friend_ship` VALUES ('quynh171217@gmail.com','2024-04-08 10:17:58.613000','10:18, 08/04/2024','2024-04-08 10:18:26.492000',200,3,1),('doanhtv2759@gmail.com','2024-04-08 14:29:26.896000','15:20, 08/04/2024','2024-04-08 15:20:00.682000',200,6,1),('hoangquanghuy@gmail.com','2024-04-10 01:56:31.500000','01:57, 10/04/2024','2024-04-10 01:57:32.503000',200,7,1),('hqhuy.bme@gmail.com','2024-04-15 12:34:41.522000','4/15/24, 12:34 PM','2024-04-15 12:34:41.522000',100,12,1),('quynh171217@gmail.com','2024-04-08 10:17:50.353000','01:57, 10/04/2024','2024-04-10 01:57:57.417000',200,3,2),('doanhtv2759@gmail.com','2024-04-08 15:17:40.961000','01:57, 10/04/2024','2024-04-10 01:57:52.244000',200,6,2),('hoangquanghuy@gmail.com','2024-04-10 01:56:36.779000','01:57, 10/04/2024','2024-04-10 01:57:54.676000',200,7,2),('hqhuy.bme@gmail.com','2024-04-15 12:34:42.367000','4/15/24, 12:34 PM','2024-04-15 12:34:42.367000',100,12,2),('doanhtv2759@gmail.com','2024-04-08 15:17:41.518000','4/8/24, 3:17 PM','2024-04-08 15:17:41.518000',100,6,3),('hoangquanghuy@gmail.com','2024-04-10 01:56:41.690000','01:56, 10/04/2024','2024-04-10 01:56:41.690000',100,7,3),('hqhuy.bme@gmail.com','2024-04-15 12:34:43.185000','4/15/24, 12:34 PM','2024-04-15 12:34:43.185000',100,12,3),('truongnguyenduc935@gmail.com','2024-04-08 12:22:44.824000','12:22, 08/04/2024','2024-04-08 12:22:50.765000',200,5,4),('hqhuy.bme@gmail.com','2024-04-15 12:34:57.954000','4/15/24, 12:34 PM','2024-04-15 12:34:57.954000',100,12,5),('vdoanh301@gmail.com','2024-04-16 08:47:20.745000','4/16/24, 8:47 AM','2024-04-16 08:47:20.745000',100,8,6),('hqhuy.bme@gmail.com','2024-04-15 12:34:45.150000','4/16/24, 7:49 AM','2024-04-16 07:49:52.269000',200,12,6),('hqhuy.bme@gmail.com','2024-04-15 12:34:34.893000','4/15/24, 12:34 PM','2024-04-15 12:34:34.893000',100,12,7),('hqhuy.bme@gmail.com','2024-04-15 12:34:47.786000','4/16/24, 7:55 AM','2024-04-16 07:55:26.213000',200,12,8),('phamquynhltbn12@gmail.com','2024-04-15 03:29:19.647000','03:29, 15/04/2024','2024-04-15 03:29:19.647000',100,1,10),('phamdoan060801@gmail.com','2024-04-13 02:32:39.615000','4/13/24, 3:25 AM','2024-04-13 03:25:32.902000',200,11,10),('hqhuy.bme@gmail.com','2024-04-15 12:34:48.556000','4/15/24, 12:34 PM','2024-04-15 12:34:48.556000',100,12,10),('phamquynhltbn12@gmail.com','2024-04-15 03:29:41.368000','03:29, 15/04/2024','2024-04-15 03:29:41.368000',100,1,11),('hqhuy.bme@gmail.com','2024-04-15 12:34:53.641000','4/15/24, 12:34 PM','2024-04-15 12:34:53.641000',100,12,11);
/*!40000 ALTER TABLE `friend_ship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_member`
--

DROP TABLE IF EXISTS `group_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_member` (
  `group_member_id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `last_activity` datetime(6) DEFAULT NULL,
  `contact_id` bigint DEFAULT NULL,
  `conversation_id` bigint DEFAULT NULL,
  PRIMARY KEY (`group_member_id`),
  KEY `FKqbnnx666uxh38dff4uanvgw88` (`contact_id`),
  KEY `FKh9ojh95jsbqa9k94mk9sjs64k` (`conversation_id`),
  CONSTRAINT `FKh9ojh95jsbqa9k94mk9sjs64k` FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`conversation_id`),
  CONSTRAINT `FKqbnnx666uxh38dff4uanvgw88` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`contact_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_member`
--

LOCK TABLES `group_member` WRITE;
/*!40000 ALTER TABLE `group_member` DISABLE KEYS */;
INSERT INTO `group_member` VALUES (1,'phamquynhltbn12@gmail.com','2024-04-08 10:18:31.706000','phamquynhltbn12@gmail.com','2024-04-16 16:05:12.000000',_binary '\0','2024-04-16 16:05:11.999866',1,1),(2,'phamquynhltbn12@gmail.com','2024-04-08 10:18:31.710000','phamquynhltbn12@gmail.com','2024-04-16 16:05:11.997000',_binary '\0','2024-04-16 16:05:11.996761',3,1),(3,'truong8dt@gmail.com','2024-04-08 12:22:56.900000','truong8dt@gmail.com','2024-04-15 04:01:59.709000',_binary '\0','2024-04-15 04:01:59.708519',4,2),(4,'truong8dt@gmail.com','2024-04-08 12:22:56.902000','truong8dt@gmail.com','2024-04-15 04:01:59.704000',_binary '\0','2024-04-15 04:01:59.703865',5,2),(5,'doanhtv2759@gmail.com','2024-04-08 15:27:59.668000','phamquynhltbn12@gmail.com','2024-04-16 16:05:13.639000',_binary '\0','2024-04-16 16:05:13.639731',6,3),(6,'doanhtv2759@gmail.com','2024-04-08 15:27:59.671000','phamquynhltbn12@gmail.com','2024-04-16 16:05:13.636000',_binary '\0','2024-04-16 16:05:13.636508',1,3),(7,'hoangquanghuy@gmail.com','2024-04-10 01:56:46.442000','hoangquanghuy@gmail.com','2024-04-10 01:56:46.442000',_binary '\0',NULL,7,4),(8,'hoangquanghuy@gmail.com','2024-04-10 01:56:46.444000','phamquynhltbn12@gmail.com','2024-04-15 12:59:37.846000',_binary '\0','2024-04-15 12:59:37.846297',1,4),(9,'hoangquanghuy@gmail.com','2024-04-10 01:57:08.326000','hoangquanghuy@gmail.com','2024-04-10 01:57:08.326000',_binary '\0',NULL,7,5),(10,'hoangquanghuy@gmail.com','2024-04-10 01:57:08.328000','hoangquanghuy@gmail.com','2024-04-10 01:57:08.328000',_binary '\0',NULL,2,5),(11,'vdoanh301@gmail.com','2024-04-11 04:49:42.041000','vdoanh301@gmail.com','2024-04-16 15:07:29.151000',_binary '\0','2024-04-16 15:07:29.151383',8,6),(12,'vdoanh301@gmail.com','2024-04-11 04:49:42.042000','vdoanh301@gmail.com','2024-04-16 15:07:29.141000',_binary '\0','2024-04-16 15:07:29.141220',6,6),(13,'doanhtv2759@gmail.com','2024-04-14 13:48:40.014000','doanhtv2759@gmail.com','2024-04-14 13:48:40.014000',_binary '\0',NULL,6,7),(14,'doanhtv2759@gmail.com','2024-04-14 13:48:40.016000','doanhtv2759@gmail.com','2024-04-14 13:48:40.016000',_binary '\0',NULL,2,7),(15,'doanhtv2759@gmail.com','2024-04-14 13:48:46.081000','doanhtv2759@gmail.com','2024-04-14 13:48:46.081000',_binary '\0',NULL,6,8),(16,'doanhtv2759@gmail.com','2024-04-14 13:48:46.082000','doanhtv2759@gmail.com','2024-04-14 13:48:46.082000',_binary '\0',NULL,3,8),(17,'doanhtv2759@gmail.com','2024-04-14 13:49:23.658000','doanhtv2759@gmail.com','2024-04-14 13:49:23.658000',_binary '\0',NULL,6,9),(18,'doanhtv2759@gmail.com','2024-04-14 13:49:23.660000','doanhtv2759@gmail.com','2024-04-14 13:49:23.660000',_binary '\0',NULL,4,9),(19,'doanhtv2759@gmail.com','2024-04-14 13:49:29.169000','doanhtv2759@gmail.com','2024-04-14 13:49:29.169000',_binary '\0',NULL,6,10),(20,'doanhtv2759@gmail.com','2024-04-14 13:49:29.170000','doanhtv2759@gmail.com','2024-04-14 13:49:29.170000',_binary '\0',NULL,5,10),(21,'doanhtv2759@gmail.com','2024-04-14 13:49:37.322000','doanhtv2759@gmail.com','2024-04-14 13:49:37.322000',_binary '\0',NULL,6,11),(22,'doanhtv2759@gmail.com','2024-04-14 13:49:37.323000','doanhtv2759@gmail.com','2024-04-14 13:49:37.323000',_binary '\0',NULL,7,11),(23,'phamquynhltbn12@gmail.com','2024-04-15 03:29:59.021000','phamquynhltbn12@gmail.com','2024-04-15 12:59:35.371000',_binary '\0','2024-04-15 12:59:35.370918',1,12),(24,'phamquynhltbn12@gmail.com','2024-04-15 03:29:59.024000','phamquynhltbn12@gmail.com','2024-04-15 03:29:59.024000',_binary '\0',NULL,10,12),(25,'phamquynhltbn12@gmail.com','2024-04-15 03:30:05.172000','phamquynhltbn12@gmail.com','2024-04-15 12:59:31.545000',_binary '\0','2024-04-15 12:59:31.545452',1,13),(26,'phamquynhltbn12@gmail.com','2024-04-15 03:30:05.174000','phamquynhltbn12@gmail.com','2024-04-15 03:30:05.174000',_binary '\0',NULL,11,13);
/*!40000 ALTER TABLE `group_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `message_id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `media_location` varchar(255) DEFAULT NULL,
  `message_type` enum('TEXT','VIDEO') DEFAULT NULL,
  `status` int NOT NULL,
  `group_member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`message_id`),
  KEY `FKggk1gfstbl8c40323lcq45iij` (`group_member_id`),
  CONSTRAINT `FKggk1gfstbl8c40323lcq45iij` FOREIGN KEY (`group_member_id`) REFERENCES `group_member` (`group_member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'phamquynhltbn12@gmail.com','2024-04-09 07:39:17.543000','phamquynhltbn12@gmail.com','2024-04-09 07:39:17.543000','dg',NULL,'TEXT',100,5),(19,'truong8dt@gmail.com','2024-04-14 13:34:13.000000','truong8dt@gmail.com',NULL,'hi bạn','https://wetalk.ibme.edu.vn/upload/hust-app//image.png','TEXT',100,3),(20,'truongnguyenduc935@gmail.com','2024-04-14 13:34:27.000000','truongnguyenduc935@gmail.com',NULL,'có gì không',NULL,'TEXT',100,4),(21,'truong8dt@gmail.com','2024-04-14 13:34:36.000000','truong8dt@gmail.com',NULL,'không có gì đâu nhé','https://wetalk.ibme.edu.vn/upload/hust-app//image.png','TEXT',100,3),(22,'truongnguyenduc935@gmail.com','2024-04-14 13:34:43.000000','truongnguyenduc935@gmail.com',NULL,'Thật không',NULL,'TEXT',100,4),(23,'truong8dt@gmail.com','2024-04-14 13:37:11.000000','truong8dt@gmail.com',NULL,'đâu rồi sao t thấy sai','https://wetalk.ibme.edu.vn/upload/hust-app//image.png','TEXT',100,3),(24,'truongnguyenduc935@gmail.com','2024-04-14 13:37:16.000000','truongnguyenduc935@gmail.com',NULL,'sao ở đâu',NULL,'TEXT',100,4),(25,'truongnguyenduc935@gmail.com','2024-04-14 13:37:18.000000','truongnguyenduc935@gmail.com',NULL,'1',NULL,'TEXT',100,4),(26,'truong8dt@gmail.com','2024-04-14 13:37:19.000000','truong8dt@gmail.com',NULL,'2','https://wetalk.ibme.edu.vn/upload/hust-app//image.png','TEXT',100,3),(27,'truongnguyenduc935@gmail.com','2024-04-14 13:37:35.000000','truongnguyenduc935@gmail.com',NULL,'3',NULL,'TEXT',100,4),(28,'truong8dt@gmail.com','2024-04-14 13:42:50.000000','truong8dt@gmail.com',NULL,'đừng tỏ ra hay ho','https://wetalk.ibme.edu.vn/upload/hust-app//image.png','TEXT',100,3),(29,'truongnguyenduc935@gmail.com','2024-04-14 13:43:00.000000','truongnguyenduc935@gmail.com',NULL,'th thích',NULL,'TEXT',100,4),(30,'vdoanh301@gmail.com','2024-04-14 14:02:16.000000','vdoanh301@gmail.com',NULL,'HelLo','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11),(31,'phamquynhltbn12@gmail.com','2024-04-15 03:30:23.000000','phamquynhltbn12@gmail.com',NULL,'hello','https://wetalk.ibme.edu.vn/upload/hust-app//pvq.jpg','TEXT',100,1),(32,'quynh171217@gmail.com','2024-04-15 03:30:45.000000','quynh171217@gmail.com',NULL,'chao bạn',NULL,'TEXT',100,2),(33,'phamquynhltbn12@gmail.com','2024-04-15 03:31:33.000000','phamquynhltbn12@gmail.com',NULL,'hello e zai','https://wetalk.ibme.edu.vn/upload/hust-app//pvq.jpg','TEXT',100,25),(34,'phamquynhltbn12@gmail.com','2024-04-15 03:31:40.000000','phamquynhltbn12@gmail.com',NULL,'hi e zai','https://wetalk.ibme.edu.vn/upload/hust-app//pvq.jpg','TEXT',100,23),(35,'phamquynhltbn12@gmail.com','2024-04-15 03:31:47.000000','phamquynhltbn12@gmail.com',NULL,'em chào thầy','https://wetalk.ibme.edu.vn/upload/hust-app//pvq.jpg','TEXT',100,8),(36,'phamquynhltbn12@gmail.com','2024-04-15 03:31:51.000000','phamquynhltbn12@gmail.com',NULL,'lô','https://wetalk.ibme.edu.vn/upload/hust-app//pvq.jpg','TEXT',100,6),(37,'phamquynhltbn12@gmail.com','2024-04-15 03:34:00.000000','phamquynhltbn12@gmail.com',NULL,'hi','https://wetalk.ibme.edu.vn/upload/hust-app//pvq.jpg','TEXT',100,1),(38,'vdoanh301@gmail.com','2024-04-15 04:02:33.000000','vdoanh301@gmail.com',NULL,'Name','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11),(39,'vdoanh301@gmail.com','2024-04-15 04:03:22.000000','vdoanh301@gmail.com',NULL,'Hêllo','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11),(40,'doanhtv2759@gmail.com','2024-04-15 04:03:47.000000','doanhtv2759@gmail.com',NULL,'Name','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(41,'doanhtv2759@gmail.com','2024-04-15 04:21:24.000000','doanhtv2759@gmail.com',NULL,'dssds','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,5),(42,'doanhtv2759@gmail.com','2024-04-15 05:12:22.000000','doanhtv2759@gmail.com',NULL,'dssds','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(43,'doanhtv2759@gmail.com','2024-04-15 05:23:25.000000','doanhtv2759@gmail.com',NULL,'dssds','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(44,'doanhtv2759@gmail.com','2024-04-15 05:23:34.000000','doanhtv2759@gmail.com',NULL,'dssds','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(45,'doanhtv2759@gmail.com','2024-04-15 05:41:10.000000','doanhtv2759@gmail.com',NULL,'dssds','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(46,'doanhtv2759@gmail.com','2024-04-15 06:10:35.000000','doanhtv2759@gmail.com',NULL,'dssds','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(47,'doanhtv2759@gmail.com','2024-04-15 06:11:27.000000','doanhtv2759@gmail.com',NULL,'dssds','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(48,'doanhtv2759@gmail.com','2024-04-15 06:47:27.000000','doanhtv2759@gmail.com',NULL,'ty','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(49,'doanhtv2759@gmail.com','2024-04-15 06:49:13.000000','doanhtv2759@gmail.com',NULL,'hekko','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(50,'vdoanh301@gmail.com','2024-04-15 06:49:20.000000','vdoanh301@gmail.com',NULL,'helo','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11),(51,'doanhtv2759@gmail.com','2024-04-15 06:49:28.000000','doanhtv2759@gmail.com',NULL,'no','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(52,'doanhtv2759@gmail.com','2024-04-15 06:51:31.000000','doanhtv2759@gmail.com',NULL,'jio','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(53,'vdoanh301@gmail.com','2024-04-15 06:51:37.000000','vdoanh301@gmail.com',NULL,'hr','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11),(54,'quynh171217@gmail.com','2024-04-15 13:05:05.000000','quynh171217@gmail.com',NULL,'lô',NULL,'TEXT',100,2),(55,'quynh171217@gmail.com','2024-04-16 01:42:41.000000','quynh171217@gmail.com',NULL,'ơi',NULL,'TEXT',100,2),(56,'doanhtv2759@gmail.com','2024-04-16 04:42:16.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(57,'doanhtv2759@gmail.com','2024-04-16 06:26:34.000000','doanhtv2759@gmail.com',NULL,'hello','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(58,'vdoanh301@gmail.com','2024-04-16 06:27:02.000000','vdoanh301@gmail.com',NULL,'no','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11),(59,'doanhtv2759@gmail.com','2024-04-16 06:35:03.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(60,'doanhtv2759@gmail.com','2024-04-16 06:35:16.000000','doanhtv2759@gmail.com',NULL,'get','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(61,'doanhtv2759@gmail.com','2024-04-16 06:35:21.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(62,'doanhtv2759@gmail.com','2024-04-16 06:38:46.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(63,'doanhtv2759@gmail.com','2024-04-16 06:39:05.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(64,'doanhtv2759@gmail.com','2024-04-16 06:42:13.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(65,'doanhtv2759@gmail.com','2024-04-16 06:42:36.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(66,'doanhtv2759@gmail.com','2024-04-16 06:44:13.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(67,'doanhtv2759@gmail.com','2024-04-16 06:44:42.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(68,'doanhtv2759@gmail.com','2024-04-16 06:45:27.000000','doanhtv2759@gmail.com',NULL,'gt','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(69,'doanhtv2759@gmail.com','2024-04-16 06:45:33.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(70,'doanhtv2759@gmail.com','2024-04-16 06:46:21.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(71,'doanhtv2759@gmail.com','2024-04-16 06:47:36.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(72,'doanhtv2759@gmail.com','2024-04-16 06:48:41.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(73,'doanhtv2759@gmail.com','2024-04-16 06:49:17.000000','doanhtv2759@gmail.com',NULL,'ger','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(74,'doanhtv2759@gmail.com','2024-04-16 06:49:20.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(75,'doanhtv2759@gmail.com','2024-04-16 06:53:50.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(76,'doanhtv2759@gmail.com','2024-04-16 06:55:22.000000','doanhtv2759@gmail.com',NULL,'frtt','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(77,'doanhtv2759@gmail.com','2024-04-16 06:55:22.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(78,'doanhtv2759@gmail.com','2024-04-16 06:55:32.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(79,'doanhtv2759@gmail.com','2024-04-16 06:55:32.000000','doanhtv2759@gmail.com',NULL,'blurd','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(80,'doanhtv2759@gmail.com','2024-04-16 06:55:33.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(81,'doanhtv2759@gmail.com','2024-04-16 07:14:38.000000','doanhtv2759@gmail.com',NULL,'yu','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(82,'doanhtv2759@gmail.com','2024-04-16 07:14:59.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(83,'doanhtv2759@gmail.com','2024-04-16 07:16:19.000000','doanhtv2759@gmail.com',NULL,'gello','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(84,'doanhtv2759@gmail.com','2024-04-16 07:16:40.000000','doanhtv2759@gmail.com',NULL,'rt','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(85,'doanhtv2759@gmail.com','2024-04-16 07:16:44.000000','doanhtv2759@gmail.com',NULL,'yu','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(86,'doanhtv2759@gmail.com','2024-04-16 07:16:53.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(87,'doanhtv2759@gmail.com','2024-04-16 07:16:54.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(88,'doanhtv2759@gmail.com','2024-04-16 07:17:14.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(89,'doanhtv2759@gmail.com','2024-04-16 07:17:14.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(90,'doanhtv2759@gmail.com','2024-04-16 07:17:15.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(91,'doanhtv2759@gmail.com','2024-04-16 07:17:29.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(92,'doanhtv2759@gmail.com','2024-04-16 07:17:45.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(93,'doanhtv2759@gmail.com','2024-04-16 07:17:45.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(94,'doanhtv2759@gmail.com','2024-04-16 07:18:09.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(95,'vdoanh301@gmail.com','2024-04-16 07:18:40.000000','vdoanh301@gmail.com',NULL,'helo','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11),(96,'vdoanh301@gmail.com','2024-04-16 07:18:54.000000','vdoanh301@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11),(97,'doanhtv2759@gmail.com','2024-04-16 07:20:32.000000','doanhtv2759@gmail.com',NULL,'ty','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(98,'vdoanh301@gmail.com','2024-04-16 07:20:44.000000','vdoanh301@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11),(99,'vdoanh301@gmail.com','2024-04-16 07:23:27.000000','vdoanh301@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11),(100,'vdoanh301@gmail.com','2024-04-16 07:23:34.000000','vdoanh301@gmail.com',NULL,'helo','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11),(101,'vdoanh301@gmail.com','2024-04-16 07:23:46.000000','vdoanh301@gmail.com',NULL,'pro','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11),(102,'vdoanh301@gmail.com','2024-04-16 07:24:24.000000','vdoanh301@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11),(103,'vdoanh301@gmail.com','2024-04-16 07:25:03.000000','vdoanh301@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11),(104,'vdoanh301@gmail.com','2024-04-16 07:25:03.000000','vdoanh301@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11),(105,'doanhtv2759@gmail.com','2024-04-16 07:26:32.000000','doanhtv2759@gmail.com',NULL,'hebo','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(106,'doanhtv2759@gmail.com','2024-04-16 07:26:43.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(107,'vdoanh301@gmail.com','2024-04-16 07:27:23.000000','vdoanh301@gmail.com',NULL,'ui','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11),(108,'vdoanh301@gmail.com','2024-04-16 07:27:31.000000','vdoanh301@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11),(109,'vdoanh301@gmail.com','2024-04-16 07:27:47.000000','vdoanh301@gmail.com',NULL,'cd','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11),(110,'vdoanh301@gmail.com','2024-04-16 07:27:47.000000','vdoanh301@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11),(111,'doanhtv2759@gmail.com','2024-04-16 07:29:47.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(112,'doanhtv2759@gmail.com','2024-04-16 08:53:31.000000','doanhtv2759@gmail.com',NULL,'gh','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(113,'vdoanh301@gmail.com','2024-04-16 08:53:32.000000','vdoanh301@gmail.com',NULL,'helo','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11),(114,'vdoanh301@gmail.com','2024-04-16 08:53:32.000000','vdoanh301@gmail.com',NULL,'he','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11),(115,'doanhtv2759@gmail.com','2024-04-16 09:03:19.000000','doanhtv2759@gmail.com',NULL,'ty','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(116,'vdoanh301@gmail.com','2024-04-16 09:03:26.000000','vdoanh301@gmail.com',NULL,'no','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11),(117,'vdoanh301@gmail.com','2024-04-16 09:03:45.000000','vdoanh301@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11),(118,'doanhtv2759@gmail.com','2024-04-16 09:03:58.000000','doanhtv2759@gmail.com',NULL,'ty','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(119,'doanhtv2759@gmail.com','2024-04-16 09:04:19.000000','doanhtv2759@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(120,'doanhtv2759@gmail.com','2024-04-16 09:04:33.000000','doanhtv2759@gmail.com',NULL,'ty','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','TEXT',100,12),(121,'vdoanh301@gmail.com','2024-04-16 10:49:34.000000','vdoanh301@gmail.com',NULL,'','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11),(122,'vdoanh301@gmail.com','2024-04-16 10:49:34.000000','vdoanh301@gmail.com',NULL,'helo','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `question_id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `explanation` varchar(2000) DEFAULT NULL,
  `image_location` varchar(255) DEFAULT NULL,
  `video_location` varchar(255) DEFAULT NULL,
  `topic_id` bigint DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `FK9h1t7swdq9eej6qf9yxtc8g9w` (`topic_id`),
  CONSTRAINT `FK9h1t7swdq9eej6qf9yxtc8g9w` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'phamquynhltbn12@gmail.com','2024-04-08 10:24:14.867000','phamquynhltbn12@gmail.com','2024-04-08 10:24:14.878000','Đây là con gì','Đây là con cá','https://wetalk.ibme.edu.vn/upload/vocabularies//0119_con%20c%C3%A1.jpg','https://wetalk.ibme.edu.vn/upload/vocabularies//0119_con%20c%C3%A1.mp4',2);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `code` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('ADMIN','ADMIN','ADMIN'),('USER','USER','USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topic` (
  `topic_id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `image_location` varchar(255) DEFAULT NULL,
  `video_location` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES (1,'phamquynhltbn12@gmail.com','2024-04-08 10:07:06.775000','phamquynhltbn12@gmail.com','2024-04-08 10:07:06.775000','*Chủ Đề Chưa Xác Định*',NULL,NULL),(2,'phamquynhltbn12@gmail.com','2024-04-08 10:21:27.689000','phamquynhltbn12@gmail.com','2024-04-08 10:21:27.689000','Động vật',NULL,NULL);
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `avatar_location` varchar(255) DEFAULT NULL,
  `birth_day` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `gender` enum('FEMALE','MALE') DEFAULT NULL,
  `is_oauth2` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  KEY `FKn3vfurnv0p6bkguxp0os8t1t7` (`code`),
  CONSTRAINT `FKn3vfurnv0p6bkguxp0os8t1t7` FOREIGN KEY (`code`) REFERENCES `role` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'anonymousUser','2024-04-08 10:04:56.677000','phamquynhltbn12@gmail.com','2024-04-08 15:43:39.423000','Bắc Ninh','https://wetalk.ibme.edu.vn/upload/hust-app//pvq.jpg','2001-07-12 00:00:00.000000','phamquynhltbn12@gmail.com','MALE',_binary '\0','Phạm Văn Quỳnh ltbn','hust','1235689','ADMIN'),(2,'anonymousUser','2024-04-08 10:16:07.382000','anonymousUser','2024-04-08 10:16:07.382000',NULL,NULL,NULL,'quynh12712053@gmail.com',NULL,_binary '\0','Phạm Văn Quỳnh 127','hust',NULL,'ADMIN'),(3,'anonymousUser','2024-04-08 10:17:30.930000','anonymousUser','2024-04-08 10:17:30.930000',NULL,NULL,NULL,'quynh171217@gmail.com',NULL,_binary '\0','Pham Van Quynh 17','hust',NULL,'ADMIN'),(4,'anonymousUser','2024-04-08 12:21:15.974000','truong8dt@gmail.com','2024-04-11 16:34:25.930000','Bắc Ninh','https://wetalk.ibme.edu.vn/upload/hust-app//image.png','2001-01-10 00:00:00.000000','truong8dt@gmail.com','MALE',_binary '\0','Nguyễn Văn Trường','123456aA@','0978803231','ADMIN'),(5,'anonymousUser','2024-04-08 12:22:35.147000','anonymousUser','2024-04-08 12:22:35.147000',NULL,NULL,NULL,'truongnguyenduc935@gmail.com',NULL,_binary '\0','Nguyễn Văn Minh','123456aA@',NULL,'ADMIN'),(6,'anonymousUser','2024-04-08 14:25:46.558000','doanhtv2759@gmail.com','2024-04-09 09:08:08.495000','Namd','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','2024-04-08 00:00:00.000000','doanhtv2759@gmail.com','MALE',_binary '\0','Doanh','s','0372232323','USER'),(7,'anonymousUser','2024-04-08 15:55:43.514000','anonymousUser','2024-04-08 15:55:43.514000',NULL,NULL,NULL,'hoangquanghuy@gmail.com',NULL,_binary '\0','Hoàng Huy','123456',NULL,'ADMIN'),(8,'anonymousUser','2024-04-11 04:48:45.079000','vdoanh301@gmail.com','2024-04-16 15:08:04.686000','Hanoi','https://wetalk.ibme.edu.vn/upload/media-general//9d9e23b9802028befafb052f7c2af883.jpg','2024-04-11 00:00:00.000000','vdoanh301@gmail.com','MALE',_binary '\0','DoanhPro','s','032323232','USER'),(10,'anonymousUser','2024-04-13 02:14:22.016000','anonymousUser','2024-04-13 02:14:22.016000',NULL,NULL,NULL,'chuyenp32@gmail.com',NULL,_binary '\0','chuyen','123456789',NULL,'ADMIN'),(11,'anonymousUser','2024-04-13 02:30:55.342000','anonymousUser','2024-04-13 02:30:55.342000',NULL,NULL,NULL,'phamdoan060801@gmail.com',NULL,_binary '\0','chuyendz','123456789',NULL,'ADMIN'),(12,'anonymousUser','2024-04-15 11:31:34.039000','anonymousUser','2024-04-15 11:31:34.039000',NULL,NULL,NULL,'hqhuy.bme@gmail.com',NULL,_binary '\0','Hoàng Huy BME','123456',NULL,'USER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vocabulary`
--

DROP TABLE IF EXISTS `vocabulary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vocabulary` (
  `vocabulary_id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `topic_id` bigint DEFAULT NULL,
  PRIMARY KEY (`vocabulary_id`),
  KEY `FKdtg3eitwnfxdem5ick6ocp3kn` (`topic_id`),
  CONSTRAINT `FKdtg3eitwnfxdem5ick6ocp3kn` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vocabulary`
--

LOCK TABLES `vocabulary` WRITE;
/*!40000 ALTER TABLE `vocabulary` DISABLE KEYS */;
INSERT INTO `vocabulary` VALUES (9,'phamquynhltbn12@gmail.com','2024-04-13 11:19:19.352000','phamquynhltbn12@gmail.com','2024-04-13 11:19:19.352000','con chim',2),(10,'truong8dt@gmail.com','2024-04-14 06:40:23.450000','truong8dt@gmail.com','2024-04-14 06:40:23.450000','con chuột',2),(13,'truong8dt@gmail.com','2024-04-16 15:51:34.622000','truong8dt@gmail.com','2024-04-16 15:51:34.622000','con cá',1),(15,'truong8dt@gmail.com','2024-04-16 15:51:34.630000','truong8dt@gmail.com','2024-04-16 15:51:34.630000','con sò',1),(16,'truong8dt@gmail.com','2024-04-16 15:51:34.635000','truong8dt@gmail.com','2024-04-16 15:51:34.635000','con chuột',1),(17,'truong8dt@gmail.com','2024-04-16 15:51:34.639000','truong8dt@gmail.com','2024-04-16 15:51:34.639000','con mèo',1),(18,'truong8dt@gmail.com','2024-04-16 15:51:34.643000','truong8dt@gmail.com','2024-04-16 15:51:34.643000','con dế',1),(19,'truong8dt@gmail.com','2024-04-16 15:51:34.646000','truong8dt@gmail.com','2024-04-16 15:51:34.646000','con dâu',1),(20,'truong8dt@gmail.com','2024-04-16 15:51:34.650000','truong8dt@gmail.com','2024-04-16 15:51:34.650000','con gà',1),(21,'truong8dt@gmail.com','2024-04-16 15:51:34.653000','truong8dt@gmail.com','2024-04-16 15:51:34.653000','cu_toi',1),(22,'truong8dt@gmail.com','2024-04-16 15:51:34.657000','truong8dt@gmail.com','2024-04-16 15:51:34.657000','con người',1),(26,'truong8dt@gmail.com','2024-04-16 15:51:34.672000','truong8dt@gmail.com','2024-04-16 15:51:34.672000','con bê',1),(27,'truong8dt@gmail.com','2024-04-16 15:51:34.676000','truong8dt@gmail.com','2024-04-16 15:51:34.676000','con rối',1),(28,'truong8dt@gmail.com','2024-04-16 15:51:34.679000','truong8dt@gmail.com','2024-04-16 15:51:34.679000','con cho',1),(29,'truong8dt@gmail.com','2024-04-16 15:51:34.682000','truong8dt@gmail.com','2024-04-16 15:51:34.682000','con gái',1),(30,'truong8dt@gmail.com','2024-04-16 15:51:34.686000','truong8dt@gmail.com','2024-04-16 15:51:34.686000','con số',1),(31,'truong8dt@gmail.com','2024-04-16 15:51:34.689000','truong8dt@gmail.com','2024-04-16 15:51:34.689000','con voi',1),(32,'truong8dt@gmail.com','2024-04-16 15:51:34.693000','truong8dt@gmail.com','2024-04-16 15:51:34.693000','con mực_1',1),(33,'truong8dt@gmail.com','2024-04-16 15:51:34.696000','truong8dt@gmail.com','2024-04-16 15:51:34.696000','con vẹt',1),(34,'truong8dt@gmail.com','2024-04-16 15:51:34.700000','truong8dt@gmail.com','2024-04-16 15:51:34.700000','con chim',1),(35,'truong8dt@gmail.com','2024-04-16 15:51:34.703000','truong8dt@gmail.com','2024-04-16 15:51:34.703000','con thỏ',1),(36,'truong8dt@gmail.com','2024-04-16 15:51:34.706000','truong8dt@gmail.com','2024-04-16 15:51:34.706000','con mực',1),(37,'truong8dt@gmail.com','2024-04-16 15:51:34.709000','truong8dt@gmail.com','2024-04-16 15:51:34.709000','trẻ con_con nít',1),(38,'truong8dt@gmail.com','2024-04-16 15:51:34.712000','truong8dt@gmail.com','2024-04-16 15:51:34.712000','con gà_1',1),(39,'truong8dt@gmail.com','2024-04-16 15:51:34.715000','truong8dt@gmail.com','2024-04-16 15:51:34.715000','con cóc',1),(40,'truong8dt@gmail.com','2024-04-16 15:51:34.718000','truong8dt@gmail.com','2024-04-16 15:51:34.718000','con ếch',1),(41,'truong8dt@gmail.com','2024-04-16 15:51:34.721000','truong8dt@gmail.com','2024-04-16 15:51:34.721000','con bê_1',1),(42,'truong8dt@gmail.com','2024-04-16 15:51:34.725000','truong8dt@gmail.com','2024-04-16 15:51:34.725000','côn trùng',1),(43,'truong8dt@gmail.com','2024-04-16 15:51:34.728000','truong8dt@gmail.com','2024-04-16 15:51:34.728000','con ghẹ',1),(44,'truong8dt@gmail.com','2024-04-16 15:51:34.731000','truong8dt@gmail.com','2024-04-16 15:51:34.731000','con rùa',1),(45,'truong8dt@gmail.com','2024-04-16 15:51:34.735000','truong8dt@gmail.com','2024-04-16 15:51:34.735000','con hàu',1),(46,'truong8dt@gmail.com','2024-04-16 15:51:34.738000','truong8dt@gmail.com','2024-04-16 15:51:34.738000','con quạ',1),(47,'truong8dt@gmail.com','2024-04-16 15:51:34.741000','truong8dt@gmail.com','2024-04-16 15:51:34.741000','con ốc',1),(48,'truong8dt@gmail.com','2024-04-16 15:51:34.744000','truong8dt@gmail.com','2024-04-16 15:51:34.744000','con dê',1),(49,'truong8dt@gmail.com','2024-04-16 15:51:34.747000','truong8dt@gmail.com','2024-04-16 15:51:34.747000','con ong',1),(50,'truong8dt@gmail.com','2024-04-16 15:51:34.750000','truong8dt@gmail.com','2024-04-16 15:51:34.750000','con cọp',1),(51,'truong8dt@gmail.com','2024-04-16 15:51:34.753000','truong8dt@gmail.com','2024-04-16 15:51:34.753000','con tôm',1),(52,'truong8dt@gmail.com','2024-04-16 15:51:34.756000','truong8dt@gmail.com','2024-04-16 15:51:34.756000','con_chim',1),(53,'truong8dt@gmail.com','2024-04-16 15:51:34.760000','truong8dt@gmail.com','2024-04-16 15:51:34.760000','con trỏ',1),(54,'truong8dt@gmail.com','2024-04-16 15:51:34.764000','truong8dt@gmail.com','2024-04-16 15:51:34.764000','con lợn (con heo)',1),(55,'truong8dt@gmail.com','2024-04-16 15:51:34.767000','truong8dt@gmail.com','2024-04-16 15:51:34.767000','con rắn',1),(56,'truong8dt@gmail.com','2024-04-16 15:51:34.770000','truong8dt@gmail.com','2024-04-16 15:51:34.770000','con nai',1),(57,'truong8dt@gmail.com','2024-04-16 15:51:34.773000','truong8dt@gmail.com','2024-04-16 15:51:34.773000','con gấu',1),(58,'truong8dt@gmail.com','2024-04-16 15:51:34.776000','truong8dt@gmail.com','2024-04-16 15:51:34.776000','con muỗi',1),(59,'truong8dt@gmail.com','2024-04-16 15:51:34.779000','truong8dt@gmail.com','2024-04-16 15:51:34.779000','con tem',1),(60,'truong8dt@gmail.com','2024-04-16 15:51:34.782000','truong8dt@gmail.com','2024-04-16 15:51:34.782000','con cò',1),(61,'truong8dt@gmail.com','2024-04-16 15:51:34.785000','truong8dt@gmail.com','2024-04-16 15:51:34.785000','con sóc',1),(62,'truong8dt@gmail.com','2024-04-16 15:51:34.788000','truong8dt@gmail.com','2024-04-16 15:51:34.788000','con vịt',1),(63,'truong8dt@gmail.com','2024-04-16 15:51:34.791000','truong8dt@gmail.com','2024-04-16 15:51:34.791000','con nai_1',1),(64,'truong8dt@gmail.com','2024-04-16 15:51:34.793000','truong8dt@gmail.com','2024-04-16 15:51:34.793000','con sâu',1),(65,'truong8dt@gmail.com','2024-04-16 15:51:34.796000','truong8dt@gmail.com','2024-04-16 15:51:34.796000','con sói',1),(66,'truong8dt@gmail.com','2024-04-16 15:51:34.799000','truong8dt@gmail.com','2024-04-16 15:51:34.799000','con cừu',1),(67,'truong8dt@gmail.com','2024-04-16 15:51:34.802000','truong8dt@gmail.com','2024-04-16 15:51:34.802000','dê con',1),(68,'truong8dt@gmail.com','2024-04-16 15:51:34.805000','truong8dt@gmail.com','2024-04-16 15:51:34.805000','con khỉ',1),(69,'truong8dt@gmail.com','2024-04-16 15:52:39.492000','truong8dt@gmail.com','2024-04-16 15:52:39.492000','con cua',2),(70,'truong8dt@gmail.com','2024-04-16 15:52:39.535000','truong8dt@gmail.com','2024-04-16 15:52:39.535000','con chó',2),(71,'truong8dt@gmail.com','2024-04-16 15:53:18.964000','truong8dt@gmail.com','2024-04-16 15:53:18.964000','con_tho',2),(72,'truong8dt@gmail.com','2024-04-16 15:53:18.985000','truong8dt@gmail.com','2024-04-16 15:53:18.985000','con bò',2),(73,'truong8dt@gmail.com','2024-04-16 15:53:19.005000','truong8dt@gmail.com','2024-04-16 15:53:19.005000','con đẻ',2),(74,'truong8dt@gmail.com','2024-04-16 15:56:36.931000','truong8dt@gmail.com','2024-04-16 15:56:36.931000','con ca',2);
/*!40000 ALTER TABLE `vocabulary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vocabulary_image`
--

DROP TABLE IF EXISTS `vocabulary_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vocabulary_image` (
  `vocabulary_image_id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `image_location` varchar(255) DEFAULT NULL,
  `is_primary` bit(1) NOT NULL,
  `vocabulary_id` bigint DEFAULT NULL,
  PRIMARY KEY (`vocabulary_image_id`),
  KEY `FKlnpmkqih9sri9d63gj3foc5oo` (`vocabulary_id`),
  CONSTRAINT `FKlnpmkqih9sri9d63gj3foc5oo` FOREIGN KEY (`vocabulary_id`) REFERENCES `vocabulary` (`vocabulary_id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vocabulary_image`
--

LOCK TABLES `vocabulary_image` WRITE;
/*!40000 ALTER TABLE `vocabulary_image` DISABLE KEYS */;
INSERT INTO `vocabulary_image` VALUES (8,'phamquynhltbn12@gmail.com','2024-04-13 11:19:19.360000','phamquynhltbn12@gmail.com','2024-04-13 11:19:19.360000','https://wetalk.ibme.edu.vn/upload/vocabularies//0121_con%20chim.gif',_binary '',9),(11,'truong8dt@gmail.com','2024-04-14 06:40:23.455000','truong8dt@gmail.com','2024-04-14 06:40:23.455000','https://wetalk.ibme.edu.vn/upload/vocabularies//0122_con%20chu%E1%BB%99t.png',_binary '',10),(14,'truong8dt@gmail.com','2024-04-16 15:51:34.623000','truong8dt@gmail.com','2024-04-16 16:06:30.335000','https://wetalk.ibme.edu.vn/upload/vocabularies//0119_con%20c%C3%A1.jpg',_binary '\0',13),(16,'truong8dt@gmail.com','2024-04-16 15:51:34.632000','truong8dt@gmail.com','2024-04-16 15:51:34.632000','https://wetalk.ibme.edu.vn/upload/vocabularies//0501_con%20s%C3%B2.jpg',_binary '',15),(17,'truong8dt@gmail.com','2024-04-16 15:51:34.636000','truong8dt@gmail.com','2024-04-16 15:51:34.636000','https://wetalk.ibme.edu.vn/upload/vocabularies//0122_con%20chu%E1%BB%99t.png',_binary '',16),(18,'truong8dt@gmail.com','2024-04-16 15:51:34.640000','truong8dt@gmail.com','2024-04-16 15:51:34.640000',NULL,_binary '',17),(19,'truong8dt@gmail.com','2024-04-16 15:51:34.644000','truong8dt@gmail.com','2024-04-16 15:51:34.644000','https://wetalk.ibme.edu.vn/upload/vocabularies//0497_con%20d%E1%BA%BF.jpg',_binary '',18),(20,'truong8dt@gmail.com','2024-04-16 15:51:34.648000','truong8dt@gmail.com','2024-04-16 15:51:34.648000',NULL,_binary '',19),(21,'truong8dt@gmail.com','2024-04-16 15:51:34.651000','truong8dt@gmail.com','2024-04-16 15:51:34.651000','https://wetalk.ibme.edu.vn/upload/vocabularies//0499_con%20g%C3%A0.jpg',_binary '',20),(22,'truong8dt@gmail.com','2024-04-16 15:51:34.655000','truong8dt@gmail.com','2024-04-16 15:51:34.655000','https://wetalk.ibme.edu.vn/upload/vocabularies//139_cu_toi.jpg',_binary '',21),(23,'truong8dt@gmail.com','2024-04-16 15:51:34.659000','truong8dt@gmail.com','2024-04-16 15:51:34.659000',NULL,_binary '',22),(27,'truong8dt@gmail.com','2024-04-16 15:51:34.673000','truong8dt@gmail.com','2024-04-16 15:51:34.673000','https://wetalk.ibme.edu.vn/upload/vocabularies//0493_con%20b%C3%AA.jpg',_binary '',26),(28,'truong8dt@gmail.com','2024-04-16 15:51:34.677000','truong8dt@gmail.com','2024-04-16 15:51:34.677000',NULL,_binary '',27),(29,'truong8dt@gmail.com','2024-04-16 15:51:34.680000','truong8dt@gmail.com','2024-04-16 15:51:34.680000','https://wetalk.ibme.edu.vn/upload/vocabularies//0837_con%20cho.jpg',_binary '',28),(30,'truong8dt@gmail.com','2024-04-16 15:51:34.684000','truong8dt@gmail.com','2024-04-16 15:51:34.684000',NULL,_binary '',29),(31,'truong8dt@gmail.com','2024-04-16 15:51:34.687000','truong8dt@gmail.com','2024-04-16 15:51:34.687000',NULL,_binary '',30),(32,'truong8dt@gmail.com','2024-04-16 15:51:34.691000','truong8dt@gmail.com','2024-04-16 15:51:34.691000','https://wetalk.ibme.edu.vn/upload/vocabularies//0835_con%20voi.jpg',_binary '',31),(33,'truong8dt@gmail.com','2024-04-16 15:51:34.694000','truong8dt@gmail.com','2024-04-16 15:51:34.694000','https://wetalk.ibme.edu.vn/upload/vocabularies//0819_con%20m%E1%BB%B1c_1.jpg',_binary '',32),(34,'truong8dt@gmail.com','2024-04-16 15:51:34.697000','truong8dt@gmail.com','2024-04-16 15:51:34.697000','https://wetalk.ibme.edu.vn/upload/vocabularies//0833_con%20v%E1%BA%B9t.jpg',_binary '',33),(35,'truong8dt@gmail.com','2024-04-16 15:51:34.701000','truong8dt@gmail.com','2024-04-16 15:51:34.701000',NULL,_binary '',34),(36,'truong8dt@gmail.com','2024-04-16 15:51:34.704000','truong8dt@gmail.com','2024-04-16 15:51:34.704000','https://wetalk.ibme.edu.vn/upload/vocabularies//0830_con%20th%E1%BB%8F.jpg',_binary '',35),(37,'truong8dt@gmail.com','2024-04-16 15:51:34.707000','truong8dt@gmail.com','2024-04-16 15:51:34.707000','https://wetalk.ibme.edu.vn/upload/vocabularies//0819_con%20m%E1%BB%B1c.jpg',_binary '',36),(38,'truong8dt@gmail.com','2024-04-16 15:51:34.710000','truong8dt@gmail.com','2024-04-16 15:51:34.710000',NULL,_binary '',37),(39,'truong8dt@gmail.com','2024-04-16 15:51:34.713000','truong8dt@gmail.com','2024-04-16 15:51:34.713000','https://wetalk.ibme.edu.vn/upload/vocabularies//0499_con%20g%C3%A0_1.jpg',_binary '',38),(40,'truong8dt@gmail.com','2024-04-16 15:51:34.716000','truong8dt@gmail.com','2024-04-16 15:51:34.716000','https://wetalk.ibme.edu.vn/upload/vocabularies//0809_con%20c%C3%B3c.png',_binary '',39),(41,'truong8dt@gmail.com','2024-04-16 15:51:34.719000','truong8dt@gmail.com','2024-04-16 15:51:34.719000','https://wetalk.ibme.edu.vn/upload/vocabularies//0814_con%20%E1%BA%BFch.jpg',_binary '',40),(42,'truong8dt@gmail.com','2024-04-16 15:51:34.722000','truong8dt@gmail.com','2024-04-16 15:51:34.722000','https://wetalk.ibme.edu.vn/upload/vocabularies//0493_con%20b%C3%AA_1.jpg',_binary '',41),(43,'truong8dt@gmail.com','2024-04-16 15:51:34.726000','truong8dt@gmail.com','2024-04-16 15:51:34.726000',NULL,_binary '',42),(44,'truong8dt@gmail.com','2024-04-16 15:51:34.729000','truong8dt@gmail.com','2024-04-16 15:51:34.729000','https://wetalk.ibme.edu.vn/upload/vocabularies//0817_con%20gh%E1%BA%B9.jpg',_binary '',43),(45,'truong8dt@gmail.com','2024-04-16 15:51:34.732000','truong8dt@gmail.com','2024-04-16 15:51:34.732000',NULL,_binary '',44),(46,'truong8dt@gmail.com','2024-04-16 15:51:34.736000','truong8dt@gmail.com','2024-04-16 15:51:34.736000','https://wetalk.ibme.edu.vn/upload/vocabularies//0659_con%20h%C3%A0u.jpg',_binary '',45),(47,'truong8dt@gmail.com','2024-04-16 15:51:34.739000','truong8dt@gmail.com','2024-04-16 15:51:34.739000','https://wetalk.ibme.edu.vn/upload/vocabularies//0822_con%20qu%E1%BA%A1.jpg',_binary '',46),(48,'truong8dt@gmail.com','2024-04-16 15:51:34.742000','truong8dt@gmail.com','2024-04-16 15:51:34.742000','https://wetalk.ibme.edu.vn/upload/vocabularies//0500_con%20%E1%BB%91c.jpg',_binary '',47),(49,'truong8dt@gmail.com','2024-04-16 15:51:34.745000','truong8dt@gmail.com','2024-04-16 15:51:34.745000','https://wetalk.ibme.edu.vn/upload/vocabularies//0496_con%20d%C3%AA.jpg',_binary '',48),(50,'truong8dt@gmail.com','2024-04-16 15:51:34.748000','truong8dt@gmail.com','2024-04-16 15:51:34.748000','https://wetalk.ibme.edu.vn/upload/vocabularies//0821_con%20ong.png',_binary '',49),(51,'truong8dt@gmail.com','2024-04-16 15:51:34.751000','truong8dt@gmail.com','2024-04-16 15:51:34.751000','https://wetalk.ibme.edu.vn/upload/vocabularies//0810_con%20c%E1%BB%8Dp.jpg',_binary '',50),(52,'truong8dt@gmail.com','2024-04-16 15:51:34.754000','truong8dt@gmail.com','2024-04-16 15:51:34.754000','https://wetalk.ibme.edu.vn/upload/vocabularies//0831_con%20t%C3%B4m.jpg',_binary '',51),(53,'truong8dt@gmail.com','2024-04-16 15:51:34.758000','truong8dt@gmail.com','2024-04-16 15:51:34.758000','https://wetalk.ibme.edu.vn/upload/vocabularies//0121_con_chim.gif',_binary '',52),(54,'truong8dt@gmail.com','2024-04-16 15:51:34.762000','truong8dt@gmail.com','2024-04-16 15:51:34.762000',NULL,_binary '',53),(55,'truong8dt@gmail.com','2024-04-16 15:51:34.765000','truong8dt@gmail.com','2024-04-16 15:51:34.765000','https://wetalk.ibme.edu.vn/upload/vocabularies//0123_con%20l%E1%BB%A3n%20%28con%20heo%29.png',_binary '',54),(56,'truong8dt@gmail.com','2024-04-16 15:51:34.768000','truong8dt@gmail.com','2024-04-16 15:51:34.768000','https://wetalk.ibme.edu.vn/upload/vocabularies//0823_con%20r%E1%BA%AFn.jpg',_binary '',55),(57,'truong8dt@gmail.com','2024-04-16 15:51:34.771000','truong8dt@gmail.com','2024-04-16 15:51:34.771000','https://wetalk.ibme.edu.vn/upload/vocabularies//0820_con%20nai.jpg',_binary '',56),(58,'truong8dt@gmail.com','2024-04-16 15:51:34.774000','truong8dt@gmail.com','2024-04-16 15:51:34.774000','https://wetalk.ibme.edu.vn/upload/vocabularies//0816_con%20g%E1%BA%A5u.jpg',_binary '',57),(59,'truong8dt@gmail.com','2024-04-16 15:51:34.777000','truong8dt@gmail.com','2024-04-16 15:51:34.777000','https://wetalk.ibme.edu.vn/upload/vocabularies//0124_con%20mu%E1%BB%97i.webp',_binary '',58),(60,'truong8dt@gmail.com','2024-04-16 15:51:34.780000','truong8dt@gmail.com','2024-04-16 15:51:34.780000',NULL,_binary '',59),(61,'truong8dt@gmail.com','2024-04-16 15:51:34.783000','truong8dt@gmail.com','2024-04-16 15:51:34.783000','https://wetalk.ibme.edu.vn/upload/vocabularies//0495_con%20c%C3%B2.jpg',_binary '',60),(62,'truong8dt@gmail.com','2024-04-16 15:51:34.786000','truong8dt@gmail.com','2024-04-16 15:51:34.786000','https://wetalk.ibme.edu.vn/upload/vocabularies//0827_con%20s%C3%B3c.jpg',_binary '',61),(63,'truong8dt@gmail.com','2024-04-16 15:51:34.789000','truong8dt@gmail.com','2024-04-16 15:51:34.789000','https://wetalk.ibme.edu.vn/upload/vocabularies//0834_con%20v%E1%BB%8Bt.jpg',_binary '',62),(64,'truong8dt@gmail.com','2024-04-16 15:51:34.791000','truong8dt@gmail.com','2024-04-16 15:51:34.791000','https://wetalk.ibme.edu.vn/upload/vocabularies//0820_con%20nai_1.jpg',_binary '',63),(65,'truong8dt@gmail.com','2024-04-16 15:51:34.794000','truong8dt@gmail.com','2024-04-16 15:51:34.794000','https://wetalk.ibme.edu.vn/upload/vocabularies//0826_con%20s%C3%A2u.jpg',_binary '',64),(66,'truong8dt@gmail.com','2024-04-16 15:51:34.797000','truong8dt@gmail.com','2024-04-16 15:51:34.797000','https://wetalk.ibme.edu.vn/upload/vocabularies//0828_con%20s%C3%B3i.jpg',_binary '',65),(67,'truong8dt@gmail.com','2024-04-16 15:51:34.800000','truong8dt@gmail.com','2024-04-16 15:51:34.800000','https://wetalk.ibme.edu.vn/upload/vocabularies//0812_con%20c%E1%BB%ABu.jpg',_binary '',66),(68,'truong8dt@gmail.com','2024-04-16 15:51:34.803000','truong8dt@gmail.com','2024-04-16 15:51:34.803000','https://wetalk.ibme.edu.vn/upload/vocabularies//0530_d%C3%AA%20con.jpg',_binary '',67),(69,'truong8dt@gmail.com','2024-04-16 15:51:34.805000','truong8dt@gmail.com','2024-04-16 15:51:34.805000','https://wetalk.ibme.edu.vn/upload/vocabularies//0818_con%20kh%E1%BB%89.jpg',_binary '',68),(70,'truong8dt@gmail.com','2024-04-16 15:52:39.498000','truong8dt@gmail.com','2024-04-16 15:52:39.498000','https://wetalk.ibme.edu.vn/upload/vocabularies//0811_con%20cua.jpg',_binary '',69),(71,'truong8dt@gmail.com','2024-04-16 15:52:39.539000','truong8dt@gmail.com','2024-04-16 15:52:39.539000','https://wetalk.ibme.edu.vn/upload/vocabularies//0120_con%20ch%C3%B3.jpg',_binary '',70),(72,'truong8dt@gmail.com','2024-04-16 15:53:18.968000','truong8dt@gmail.com','2024-04-16 15:53:18.968000','https://wetalk.ibme.edu.vn/upload/vocabularies//0830_con_tho.jpg',_binary '',71),(73,'truong8dt@gmail.com','2024-04-16 15:53:18.989000','truong8dt@gmail.com','2024-04-16 15:53:18.989000','https://wetalk.ibme.edu.vn/upload/vocabularies//0494_con%20b%C3%B2.png',_binary '',72),(74,'truong8dt@gmail.com','2024-04-16 15:53:19.009000','truong8dt@gmail.com','2024-04-16 15:53:19.009000',NULL,_binary '',73),(75,'truong8dt@gmail.com','2024-04-16 15:56:36.937000','truong8dt@gmail.com','2024-04-16 15:56:36.937000','https://wetalk.ibme.edu.vn/upload/vocabularies//0838_con%20ca.jpg',_binary '',74),(76,'truong8dt@gmail.com','2024-04-16 15:57:35.968000','truong8dt@gmail.com','2024-04-16 16:26:20.101000','https://wetalk.ibme.edu.vn/upload/hust-app//0836_con%20ca.jpg',_binary '\0',13),(77,'truong8dt@gmail.com','2024-04-16 15:57:35.973000','truong8dt@gmail.com','2024-04-16 16:07:29.532000','https://wetalk.ibme.edu.vn/upload/vocabularies//0836_con%20ca.jpg',_binary '\0',13),(78,'truong8dt@gmail.com','2024-04-16 16:26:20.099000','truong8dt@gmail.com','2024-04-16 16:26:20.099000','https://wetalk.ibme.edu.vn/upload/hust-app//0838_con%20ca.jpg',_binary '',13);
/*!40000 ALTER TABLE `vocabulary_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vocabulary_video`
--

DROP TABLE IF EXISTS `vocabulary_video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vocabulary_video` (
  `vocabulary_video_id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `is_primary` bit(1) NOT NULL,
  `video_location` varchar(255) DEFAULT NULL,
  `vocabulary_id` bigint DEFAULT NULL,
  PRIMARY KEY (`vocabulary_video_id`),
  KEY `FKrg36qrk5reo4fidttev18jmuf` (`vocabulary_id`),
  CONSTRAINT `FKrg36qrk5reo4fidttev18jmuf` FOREIGN KEY (`vocabulary_id`) REFERENCES `vocabulary` (`vocabulary_id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vocabulary_video`
--

LOCK TABLES `vocabulary_video` WRITE;
/*!40000 ALTER TABLE `vocabulary_video` DISABLE KEYS */;
INSERT INTO `vocabulary_video` VALUES (8,'phamquynhltbn12@gmail.com','2024-04-13 11:19:19.365000','phamquynhltbn12@gmail.com','2024-04-13 11:19:19.365000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0121_con%20chim.mp4',9),(11,'truong8dt@gmail.com','2024-04-14 06:40:23.459000','truong8dt@gmail.com','2024-04-14 06:40:23.459000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0122_con%20chu%E1%BB%99t.mp4',10),(14,'truong8dt@gmail.com','2024-04-16 15:51:34.625000','truong8dt@gmail.com','2024-04-16 16:26:20.128000',_binary '\0','https://wetalk.ibme.edu.vn/upload/vocabularies//0119_con%20c%C3%A1.mp4',13),(16,'truong8dt@gmail.com','2024-04-16 15:51:34.633000','truong8dt@gmail.com','2024-04-16 15:51:34.633000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0501_con%20s%C3%B2.mp4',15),(17,'truong8dt@gmail.com','2024-04-16 15:51:34.638000','truong8dt@gmail.com','2024-04-16 15:51:34.638000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0122_con%20chu%E1%BB%99t.mp4',16),(18,'truong8dt@gmail.com','2024-04-16 15:51:34.641000','truong8dt@gmail.com','2024-04-16 15:51:34.641000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0125_con%20m%C3%A8o.mp4',17),(19,'truong8dt@gmail.com','2024-04-16 15:51:34.645000','truong8dt@gmail.com','2024-04-16 15:51:34.645000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0497_con%20d%E1%BA%BF.mp4',18),(20,'truong8dt@gmail.com','2024-04-16 15:51:34.649000','truong8dt@gmail.com','2024-04-16 15:51:34.649000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0813_con%20d%C3%A2u.mp4',19),(21,'truong8dt@gmail.com','2024-04-16 15:51:34.652000','truong8dt@gmail.com','2024-04-16 15:51:34.652000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0499_con%20g%C3%A0.mp4',20),(22,'truong8dt@gmail.com','2024-04-16 15:51:34.656000','truong8dt@gmail.com','2024-04-16 15:51:34.656000',_binary '',NULL,21),(23,'truong8dt@gmail.com','2024-04-16 15:51:34.660000','truong8dt@gmail.com','2024-04-16 15:51:34.660000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0126_con%20ng%C6%B0%E1%BB%9Di.mp4',22),(27,'truong8dt@gmail.com','2024-04-16 15:51:34.674000','truong8dt@gmail.com','2024-04-16 15:51:34.674000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0493_con%20b%C3%AA.mp4',26),(28,'truong8dt@gmail.com','2024-04-16 15:51:34.678000','truong8dt@gmail.com','2024-04-16 15:51:34.678000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0824_con%20r%E1%BB%91i.mp4',27),(29,'truong8dt@gmail.com','2024-04-16 15:51:34.681000','truong8dt@gmail.com','2024-04-16 15:51:34.681000',_binary '',NULL,28),(30,'truong8dt@gmail.com','2024-04-16 15:51:34.685000','truong8dt@gmail.com','2024-04-16 15:51:34.685000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0815_con%20g%C3%A1i.mp4',29),(31,'truong8dt@gmail.com','2024-04-16 15:51:34.688000','truong8dt@gmail.com','2024-04-16 15:51:34.688000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0502_con%20s%E1%BB%91.mp4',30),(32,'truong8dt@gmail.com','2024-04-16 15:51:34.692000','truong8dt@gmail.com','2024-04-16 15:51:34.692000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0835_con%20voi.mp4',31),(33,'truong8dt@gmail.com','2024-04-16 15:51:34.695000','truong8dt@gmail.com','2024-04-16 15:51:34.695000',_binary '',NULL,32),(34,'truong8dt@gmail.com','2024-04-16 15:51:34.698000','truong8dt@gmail.com','2024-04-16 15:51:34.698000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0833_con%20v%E1%BA%B9t.mp4',33),(35,'truong8dt@gmail.com','2024-04-16 15:51:34.702000','truong8dt@gmail.com','2024-04-16 15:51:34.702000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0121_con%20chim.mp4',34),(36,'truong8dt@gmail.com','2024-04-16 15:51:34.705000','truong8dt@gmail.com','2024-04-16 15:51:34.705000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0830_con%20th%E1%BB%8F.mp4',35),(37,'truong8dt@gmail.com','2024-04-16 15:51:34.708000','truong8dt@gmail.com','2024-04-16 15:51:34.708000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0819_con%20m%E1%BB%B1c.mp4',36),(38,'truong8dt@gmail.com','2024-04-16 15:51:34.711000','truong8dt@gmail.com','2024-04-16 15:51:34.711000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0213_tr%E1%BA%BB%20con_con%20n%C3%ADt.mp4',37),(39,'truong8dt@gmail.com','2024-04-16 15:51:34.714000','truong8dt@gmail.com','2024-04-16 15:51:34.714000',_binary '',NULL,38),(40,'truong8dt@gmail.com','2024-04-16 15:51:34.717000','truong8dt@gmail.com','2024-04-16 15:51:34.717000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0809_con%20c%C3%B3c.mp4',39),(41,'truong8dt@gmail.com','2024-04-16 15:51:34.720000','truong8dt@gmail.com','2024-04-16 15:51:34.720000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0814_con%20%E1%BA%BFch.mp4',40),(42,'truong8dt@gmail.com','2024-04-16 15:51:34.724000','truong8dt@gmail.com','2024-04-16 15:51:34.724000',_binary '',NULL,41),(43,'truong8dt@gmail.com','2024-04-16 15:51:34.727000','truong8dt@gmail.com','2024-04-16 15:51:34.727000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0128_c%C3%B4n%20tr%C3%B9ng.mp4',42),(44,'truong8dt@gmail.com','2024-04-16 15:51:34.730000','truong8dt@gmail.com','2024-04-16 15:51:34.730000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0817_con%20gh%E1%BA%B9.mp4',43),(45,'truong8dt@gmail.com','2024-04-16 15:51:34.733000','truong8dt@gmail.com','2024-04-16 15:51:34.733000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0825_con%20r%C3%B9a.mp4',44),(46,'truong8dt@gmail.com','2024-04-16 15:51:34.737000','truong8dt@gmail.com','2024-04-16 15:51:34.737000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0659_con%20h%C3%A0u.mp4',45),(47,'truong8dt@gmail.com','2024-04-16 15:51:34.740000','truong8dt@gmail.com','2024-04-16 15:51:34.740000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0822_con%20qu%E1%BA%A1.mp4',46),(48,'truong8dt@gmail.com','2024-04-16 15:51:34.743000','truong8dt@gmail.com','2024-04-16 15:51:34.743000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0500_con%20%E1%BB%91c.mp4',47),(49,'truong8dt@gmail.com','2024-04-16 15:51:34.746000','truong8dt@gmail.com','2024-04-16 15:51:34.746000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0496_con%20d%C3%AA.mp4',48),(50,'truong8dt@gmail.com','2024-04-16 15:51:34.749000','truong8dt@gmail.com','2024-04-16 15:51:34.749000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0821_con%20ong.mp4',49),(51,'truong8dt@gmail.com','2024-04-16 15:51:34.752000','truong8dt@gmail.com','2024-04-16 15:51:34.752000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0810_con%20c%E1%BB%8Dp.mp4',50),(52,'truong8dt@gmail.com','2024-04-16 15:51:34.755000','truong8dt@gmail.com','2024-04-16 15:51:34.755000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0831_con%20t%C3%B4m.mp4',51),(53,'truong8dt@gmail.com','2024-04-16 15:51:34.759000','truong8dt@gmail.com','2024-04-16 15:51:34.759000',_binary '',NULL,52),(54,'truong8dt@gmail.com','2024-04-16 15:51:34.763000','truong8dt@gmail.com','2024-04-16 15:51:34.763000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0832_con%20tr%E1%BB%8F.mp4',53),(55,'truong8dt@gmail.com','2024-04-16 15:51:34.766000','truong8dt@gmail.com','2024-04-16 15:51:34.766000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0123_con%20l%E1%BB%A3n%20%28con%20heo%29.mp4',54),(56,'truong8dt@gmail.com','2024-04-16 15:51:34.769000','truong8dt@gmail.com','2024-04-16 15:51:34.769000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0823_con%20r%E1%BA%AFn.mp4',55),(57,'truong8dt@gmail.com','2024-04-16 15:51:34.772000','truong8dt@gmail.com','2024-04-16 15:51:34.772000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0820_con%20nai.mp4',56),(58,'truong8dt@gmail.com','2024-04-16 15:51:34.775000','truong8dt@gmail.com','2024-04-16 15:51:34.775000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0816_con%20g%E1%BA%A5u.mp4',57),(59,'truong8dt@gmail.com','2024-04-16 15:51:34.778000','truong8dt@gmail.com','2024-04-16 15:51:34.778000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0124_con%20mu%E1%BB%97i.mp4',58),(60,'truong8dt@gmail.com','2024-04-16 15:51:34.781000','truong8dt@gmail.com','2024-04-16 15:51:34.781000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0829_con%20tem.mp4',59),(61,'truong8dt@gmail.com','2024-04-16 15:51:34.784000','truong8dt@gmail.com','2024-04-16 15:51:34.784000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0495_con%20c%C3%B2.mp4',60),(62,'truong8dt@gmail.com','2024-04-16 15:51:34.787000','truong8dt@gmail.com','2024-04-16 15:51:34.787000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0827_con%20s%C3%B3c.mp4',61),(63,'truong8dt@gmail.com','2024-04-16 15:51:34.790000','truong8dt@gmail.com','2024-04-16 15:51:34.790000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0834_con%20v%E1%BB%8Bt.mp4',62),(64,'truong8dt@gmail.com','2024-04-16 15:51:34.792000','truong8dt@gmail.com','2024-04-16 15:51:34.792000',_binary '',NULL,63),(65,'truong8dt@gmail.com','2024-04-16 15:51:34.795000','truong8dt@gmail.com','2024-04-16 15:51:34.795000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0826_con%20s%C3%A2u.mp4',64),(66,'truong8dt@gmail.com','2024-04-16 15:51:34.798000','truong8dt@gmail.com','2024-04-16 15:51:34.798000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0828_con%20s%C3%B3i.mp4',65),(67,'truong8dt@gmail.com','2024-04-16 15:51:34.801000','truong8dt@gmail.com','2024-04-16 15:51:34.801000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0812_con%20c%E1%BB%ABu.mp4',66),(68,'truong8dt@gmail.com','2024-04-16 15:51:34.804000','truong8dt@gmail.com','2024-04-16 15:51:34.804000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0530_d%C3%AA%20con.mp4',67),(69,'truong8dt@gmail.com','2024-04-16 15:51:34.806000','truong8dt@gmail.com','2024-04-16 15:51:34.806000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0818_con%20kh%E1%BB%89.mp4',68),(70,'truong8dt@gmail.com','2024-04-16 15:52:39.501000','truong8dt@gmail.com','2024-04-16 15:52:39.501000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0811_con%20cua.mp4',69),(71,'truong8dt@gmail.com','2024-04-16 15:52:39.542000','truong8dt@gmail.com','2024-04-16 15:52:39.542000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0120_con%20ch%C3%B3.mp4',70),(72,'truong8dt@gmail.com','2024-04-16 15:53:18.971000','truong8dt@gmail.com','2024-04-16 15:53:18.971000',_binary '',NULL,71),(73,'truong8dt@gmail.com','2024-04-16 15:53:18.991000','truong8dt@gmail.com','2024-04-16 15:53:18.991000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0494_con%20b%C3%B2.mp4',72),(74,'truong8dt@gmail.com','2024-04-16 15:53:19.012000','truong8dt@gmail.com','2024-04-16 15:53:19.012000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0498_con%20%C4%91%E1%BA%BB.mp4',73),(75,'truong8dt@gmail.com','2024-04-16 15:56:36.941000','truong8dt@gmail.com','2024-04-16 15:56:36.941000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0837_con%20ca.mp4',74),(77,'truong8dt@gmail.com','2024-04-16 16:26:20.127000','truong8dt@gmail.com','2024-04-16 16:26:20.127000',_binary '','https://wetalk.ibme.edu.vn/upload/hust-app//0837_con%20ca.mp4',13);
/*!40000 ALTER TABLE `vocabulary_video` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-17  9:00:56
