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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,'anonymousUser','2024-04-08 10:04:56.568000','anonymousUser','2024-04-08 15:43:39.415000','https://wetalk.ibme.edu.vn/upload/hust-app//pvq.jpg','phamquynhltbn12@gmail.com','Phạm Văn Quỳnh ltbn'),(2,'anonymousUser','2024-04-08 10:16:07.370000','anonymousUser','2024-04-08 10:16:07.370000',NULL,'quynh12712053@gmail.com','Phạm Văn Quỳnh 127'),(3,'anonymousUser','2024-04-08 10:17:30.919000','anonymousUser','2024-04-08 10:17:30.919000',NULL,'quynh171217@gmail.com','Pham Van Quynh 17'),(4,'anonymousUser','2024-04-08 12:21:15.962000','anonymousUser','2024-04-11 16:34:25.882000','https://wetalk.ibme.edu.vn/upload/hust-app//image.png','truong8dt@gmail.com','Nguyễn Văn Trường'),(5,'anonymousUser','2024-04-08 12:22:35.137000','anonymousUser','2024-04-08 12:22:35.137000',NULL,'truongnguyenduc935@gmail.com','Nguyễn Văn Minh'),(6,'anonymousUser','2024-04-08 14:25:46.532000','anonymousUser','2024-04-09 08:28:15.830000','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','doanhtv2759@gmail.com','Doanh'),(7,'anonymousUser','2024-04-08 15:55:43.505000','anonymousUser','2024-04-08 15:55:43.505000',NULL,'hoangquanghuy@gmail.com','Hoàng Huy'),(8,'anonymousUser','2024-04-11 04:48:45.050000','anonymousUser','2024-04-11 04:49:18.776000','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','vdoanh301@gmail.com','DoanhPro'),(10,'anonymousUser','2024-04-13 02:14:21.899000','anonymousUser','2024-04-13 02:14:21.899000',NULL,'chuyenp32@gmail.com','chuyen'),(11,'anonymousUser','2024-04-13 02:30:55.332000','anonymousUser','2024-04-13 02:30:55.332000',NULL,'phamdoan060801@gmail.com','chuyendz');
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation`
--

LOCK TABLES `conversation` WRITE;
/*!40000 ALTER TABLE `conversation` DISABLE KEYS */;
INSERT INTO `conversation` VALUES (1,'phamquynhltbn12@gmail.com','2024-04-08 10:18:31.698000','phamquynhltbn12@gmail.com','2024-04-08 10:18:31.698000','phamquynhltbn12@gmail.com_phamquynhltbn12@gmail.com','SINGLE'),(2,'truong8dt@gmail.com','2024-04-08 12:22:56.898000','truong8dt@gmail.com','2024-04-08 12:22:56.898000','truong8dt@gmail.com_truong8dt@gmail.com','SINGLE'),(3,'doanhtv2759@gmail.com','2024-04-08 15:27:59.658000','doanhtv2759@gmail.com','2024-04-08 15:27:59.658000','doanhtv2759@gmail.com_doanhtv2759@gmail.com','SINGLE'),(4,'hoangquanghuy@gmail.com','2024-04-10 01:56:46.426000','hoangquanghuy@gmail.com','2024-04-10 01:56:46.426000','hoangquanghuy@gmail.com_hoangquanghuy@gmail.com','SINGLE'),(5,'hoangquanghuy@gmail.com','2024-04-10 01:57:08.324000','hoangquanghuy@gmail.com','2024-04-10 01:57:08.324000','hoangquanghuy@gmail.com_hoangquanghuy@gmail.com','SINGLE'),(6,'vdoanh301@gmail.com','2024-04-11 04:49:42.036000','vdoanh301@gmail.com','2024-04-11 04:49:42.036000','vdoanh301@gmail.com_vdoanh301@gmail.com','SINGLE'),(7,'doanhtv2759@gmail.com','2024-04-14 13:48:39.993000','doanhtv2759@gmail.com','2024-04-14 13:48:39.993000','doanhtv2759@gmail.com_doanhtv2759@gmail.com','SINGLE'),(8,'doanhtv2759@gmail.com','2024-04-14 13:48:46.079000','doanhtv2759@gmail.com','2024-04-14 13:48:46.079000','doanhtv2759@gmail.com_doanhtv2759@gmail.com','SINGLE'),(9,'doanhtv2759@gmail.com','2024-04-14 13:49:23.657000','doanhtv2759@gmail.com','2024-04-14 13:49:23.657000','doanhtv2759@gmail.com_doanhtv2759@gmail.com','SINGLE'),(10,'doanhtv2759@gmail.com','2024-04-14 13:49:29.167000','doanhtv2759@gmail.com','2024-04-14 13:49:29.167000','doanhtv2759@gmail.com_doanhtv2759@gmail.com','SINGLE'),(11,'doanhtv2759@gmail.com','2024-04-14 13:49:37.321000','doanhtv2759@gmail.com','2024-04-14 13:49:37.321000','doanhtv2759@gmail.com_doanhtv2759@gmail.com','SINGLE');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_collection`
--

LOCK TABLES `data_collection` WRITE;
/*!40000 ALTER TABLE `data_collection` DISABLE KEYS */;
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
INSERT INTO `friend_ship` VALUES ('quynh171217@gmail.com','2024-04-08 10:17:58.613000','10:18, 08/04/2024','2024-04-08 10:18:26.492000',200,3,1),('doanhtv2759@gmail.com','2024-04-08 14:29:26.896000','15:20, 08/04/2024','2024-04-08 15:20:00.682000',200,6,1),('hoangquanghuy@gmail.com','2024-04-10 01:56:31.500000','01:57, 10/04/2024','2024-04-10 01:57:32.503000',200,7,1),('quynh171217@gmail.com','2024-04-08 10:17:50.353000','01:57, 10/04/2024','2024-04-10 01:57:57.417000',200,3,2),('doanhtv2759@gmail.com','2024-04-08 15:17:40.961000','01:57, 10/04/2024','2024-04-10 01:57:52.244000',200,6,2),('hoangquanghuy@gmail.com','2024-04-10 01:56:36.779000','01:57, 10/04/2024','2024-04-10 01:57:54.676000',200,7,2),('doanhtv2759@gmail.com','2024-04-08 15:17:41.518000','4/8/24, 3:17 PM','2024-04-08 15:17:41.518000',100,6,3),('hoangquanghuy@gmail.com','2024-04-10 01:56:41.690000','01:56, 10/04/2024','2024-04-10 01:56:41.690000',100,7,3),('truongnguyenduc935@gmail.com','2024-04-08 12:22:44.824000','12:22, 08/04/2024','2024-04-08 12:22:50.765000',200,5,4),('vdoanh301@gmail.com','2024-04-11 04:49:28.261000','4/11/24, 4:49 AM','2024-04-11 04:49:32.834000',200,8,6),('phamdoan060801@gmail.com','2024-04-13 02:32:39.615000','4/13/24, 3:25 AM','2024-04-13 03:25:32.902000',200,11,10);
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_member`
--

LOCK TABLES `group_member` WRITE;
/*!40000 ALTER TABLE `group_member` DISABLE KEYS */;
INSERT INTO `group_member` VALUES (1,'phamquynhltbn12@gmail.com','2024-04-08 10:18:31.706000','phamquynhltbn12@gmail.com','2024-04-08 10:18:31.706000',_binary '\0',NULL,1,1),(2,'phamquynhltbn12@gmail.com','2024-04-08 10:18:31.710000','phamquynhltbn12@gmail.com','2024-04-08 10:18:31.710000',_binary '\0',NULL,3,1),(3,'truong8dt@gmail.com','2024-04-08 12:22:56.900000','doanhtv2759@gmail.com','2024-04-14 14:00:39.035000',_binary '\0','2024-04-14 14:00:39.034797',4,2),(4,'truong8dt@gmail.com','2024-04-08 12:22:56.902000','doanhtv2759@gmail.com','2024-04-14 14:00:39.031000',_binary '\0','2024-04-14 14:00:39.031586',5,2),(5,'doanhtv2759@gmail.com','2024-04-08 15:27:59.668000','phamquynhltbn12@gmail.com','2024-04-10 14:50:20.938000',_binary '\0','2024-04-10 14:50:20.937975',6,3),(6,'doanhtv2759@gmail.com','2024-04-08 15:27:59.671000','doanhtv2759@gmail.com','2024-04-08 15:27:59.671000',_binary '\0',NULL,1,3),(7,'hoangquanghuy@gmail.com','2024-04-10 01:56:46.442000','hoangquanghuy@gmail.com','2024-04-10 01:56:46.442000',_binary '\0',NULL,7,4),(8,'hoangquanghuy@gmail.com','2024-04-10 01:56:46.444000','hoangquanghuy@gmail.com','2024-04-10 01:56:46.444000',_binary '\0',NULL,1,4),(9,'hoangquanghuy@gmail.com','2024-04-10 01:57:08.326000','hoangquanghuy@gmail.com','2024-04-10 01:57:08.326000',_binary '\0',NULL,7,5),(10,'hoangquanghuy@gmail.com','2024-04-10 01:57:08.328000','hoangquanghuy@gmail.com','2024-04-10 01:57:08.328000',_binary '\0',NULL,2,5),(11,'vdoanh301@gmail.com','2024-04-11 04:49:42.041000','vdoanh301@gmail.com','2024-04-11 04:49:42.041000',_binary '\0',NULL,8,6),(12,'vdoanh301@gmail.com','2024-04-11 04:49:42.042000','vdoanh301@gmail.com','2024-04-11 04:49:42.042000',_binary '\0',NULL,6,6),(13,'doanhtv2759@gmail.com','2024-04-14 13:48:40.014000','doanhtv2759@gmail.com','2024-04-14 13:48:40.014000',_binary '\0',NULL,6,7),(14,'doanhtv2759@gmail.com','2024-04-14 13:48:40.016000','doanhtv2759@gmail.com','2024-04-14 13:48:40.016000',_binary '\0',NULL,2,7),(15,'doanhtv2759@gmail.com','2024-04-14 13:48:46.081000','doanhtv2759@gmail.com','2024-04-14 13:48:46.081000',_binary '\0',NULL,6,8),(16,'doanhtv2759@gmail.com','2024-04-14 13:48:46.082000','doanhtv2759@gmail.com','2024-04-14 13:48:46.082000',_binary '\0',NULL,3,8),(17,'doanhtv2759@gmail.com','2024-04-14 13:49:23.658000','doanhtv2759@gmail.com','2024-04-14 13:49:23.658000',_binary '\0',NULL,6,9),(18,'doanhtv2759@gmail.com','2024-04-14 13:49:23.660000','doanhtv2759@gmail.com','2024-04-14 13:49:23.660000',_binary '\0',NULL,4,9),(19,'doanhtv2759@gmail.com','2024-04-14 13:49:29.169000','doanhtv2759@gmail.com','2024-04-14 13:49:29.169000',_binary '\0',NULL,6,10),(20,'doanhtv2759@gmail.com','2024-04-14 13:49:29.170000','doanhtv2759@gmail.com','2024-04-14 13:49:29.170000',_binary '\0',NULL,5,10),(21,'doanhtv2759@gmail.com','2024-04-14 13:49:37.322000','doanhtv2759@gmail.com','2024-04-14 13:49:37.322000',_binary '\0',NULL,6,11),(22,'doanhtv2759@gmail.com','2024-04-14 13:49:37.323000','doanhtv2759@gmail.com','2024-04-14 13:49:37.323000',_binary '\0',NULL,7,11);
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'phamquynhltbn12@gmail.com','2024-04-09 07:39:17.543000','phamquynhltbn12@gmail.com','2024-04-09 07:39:17.543000','dg',NULL,'TEXT',100,5),(19,'truong8dt@gmail.com','2024-04-14 13:34:13.000000','truong8dt@gmail.com',NULL,'hi bạn','https://wetalk.ibme.edu.vn/upload/hust-app//image.png','TEXT',100,3),(20,'truongnguyenduc935@gmail.com','2024-04-14 13:34:27.000000','truongnguyenduc935@gmail.com',NULL,'có gì không',NULL,'TEXT',100,4),(21,'truong8dt@gmail.com','2024-04-14 13:34:36.000000','truong8dt@gmail.com',NULL,'không có gì đâu nhé','https://wetalk.ibme.edu.vn/upload/hust-app//image.png','TEXT',100,3),(22,'truongnguyenduc935@gmail.com','2024-04-14 13:34:43.000000','truongnguyenduc935@gmail.com',NULL,'Thật không',NULL,'TEXT',100,4),(23,'truong8dt@gmail.com','2024-04-14 13:37:11.000000','truong8dt@gmail.com',NULL,'đâu rồi sao t thấy sai','https://wetalk.ibme.edu.vn/upload/hust-app//image.png','TEXT',100,3),(24,'truongnguyenduc935@gmail.com','2024-04-14 13:37:16.000000','truongnguyenduc935@gmail.com',NULL,'sao ở đâu',NULL,'TEXT',100,4),(25,'truongnguyenduc935@gmail.com','2024-04-14 13:37:18.000000','truongnguyenduc935@gmail.com',NULL,'1',NULL,'TEXT',100,4),(26,'truong8dt@gmail.com','2024-04-14 13:37:19.000000','truong8dt@gmail.com',NULL,'2','https://wetalk.ibme.edu.vn/upload/hust-app//image.png','TEXT',100,3),(27,'truongnguyenduc935@gmail.com','2024-04-14 13:37:35.000000','truongnguyenduc935@gmail.com',NULL,'3',NULL,'TEXT',100,4),(28,'truong8dt@gmail.com','2024-04-14 13:42:50.000000','truong8dt@gmail.com',NULL,'đừng tỏ ra hay ho','https://wetalk.ibme.edu.vn/upload/hust-app//image.png','TEXT',100,3),(29,'truongnguyenduc935@gmail.com','2024-04-14 13:43:00.000000','truongnguyenduc935@gmail.com',NULL,'th thích',NULL,'TEXT',100,4),(30,'vdoanh301@gmail.com','2024-04-14 14:02:16.000000','vdoanh301@gmail.com',NULL,'HelLo','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','TEXT',100,11);
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'anonymousUser','2024-04-08 10:04:56.677000','phamquynhltbn12@gmail.com','2024-04-08 15:43:39.423000','Bắc Ninh','https://wetalk.ibme.edu.vn/upload/hust-app//pvq.jpg','2001-07-12 00:00:00.000000','phamquynhltbn12@gmail.com','MALE',_binary '\0','Phạm Văn Quỳnh ltbn','hust','1235689','ADMIN'),(2,'anonymousUser','2024-04-08 10:16:07.382000','anonymousUser','2024-04-08 10:16:07.382000',NULL,NULL,NULL,'quynh12712053@gmail.com',NULL,_binary '\0','Phạm Văn Quỳnh 127','hust',NULL,'ADMIN'),(3,'anonymousUser','2024-04-08 10:17:30.930000','anonymousUser','2024-04-08 10:17:30.930000',NULL,NULL,NULL,'quynh171217@gmail.com',NULL,_binary '\0','Pham Van Quynh 17','hust',NULL,'ADMIN'),(4,'anonymousUser','2024-04-08 12:21:15.974000','truong8dt@gmail.com','2024-04-11 16:34:25.930000','Bắc Ninh','https://wetalk.ibme.edu.vn/upload/hust-app//image.png','2001-01-10 00:00:00.000000','truong8dt@gmail.com','MALE',_binary '\0','Nguyễn Văn Trường','123456aA@','0978803231','ADMIN'),(5,'anonymousUser','2024-04-08 12:22:35.147000','anonymousUser','2024-04-08 12:22:35.147000',NULL,NULL,NULL,'truongnguyenduc935@gmail.com',NULL,_binary '\0','Nguyễn Văn Minh','123456aA@',NULL,'ADMIN'),(6,'anonymousUser','2024-04-08 14:25:46.558000','doanhtv2759@gmail.com','2024-04-09 09:08:08.495000','Namd','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','2024-04-08 00:00:00.000000','doanhtv2759@gmail.com','MALE',_binary '\0','Doanh','s','0372232323','USER'),(7,'anonymousUser','2024-04-08 15:55:43.514000','anonymousUser','2024-04-08 15:55:43.514000',NULL,NULL,NULL,'hoangquanghuy@gmail.com',NULL,_binary '\0','Hoàng Huy','123456',NULL,'ADMIN'),(8,'anonymousUser','2024-04-11 04:48:45.079000','vdoanh301@gmail.com','2024-04-11 04:49:18.784000','Hanoi','https://wetalk.ibme.edu.vn/upload/media-general//IMG_20240410_033405.jpg','2024-04-11 00:00:00.000000','vdoanh301@gmail.com','MALE',_binary '\0','DoanhPro','s','032323232','USER'),(10,'anonymousUser','2024-04-13 02:14:22.016000','anonymousUser','2024-04-13 02:14:22.016000',NULL,NULL,NULL,'chuyenp32@gmail.com',NULL,_binary '\0','chuyen','123456789',NULL,'ADMIN'),(11,'anonymousUser','2024-04-13 02:30:55.342000','anonymousUser','2024-04-13 02:30:55.342000',NULL,NULL,NULL,'phamdoan060801@gmail.com',NULL,_binary '\0','chuyendz','123456789',NULL,'ADMIN');
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vocabulary`
--

LOCK TABLES `vocabulary` WRITE;
/*!40000 ALTER TABLE `vocabulary` DISABLE KEYS */;
INSERT INTO `vocabulary` VALUES (2,'phamquynhltbn12@gmail.com','2024-04-13 09:34:27.070000','phamquynhltbn12@gmail.com','2024-04-13 09:34:27.070000','con cá',2),(9,'phamquynhltbn12@gmail.com','2024-04-13 11:19:19.352000','phamquynhltbn12@gmail.com','2024-04-13 11:19:19.352000','con chim',2),(10,'truong8dt@gmail.com','2024-04-14 06:40:23.450000','truong8dt@gmail.com','2024-04-14 06:40:23.450000','con chuột',2);
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vocabulary_image`
--

LOCK TABLES `vocabulary_image` WRITE;
/*!40000 ALTER TABLE `vocabulary_image` DISABLE KEYS */;
INSERT INTO `vocabulary_image` VALUES (2,'phamquynhltbn12@gmail.com','2024-04-13 09:34:27.072000','truong8dt@gmail.com','2024-04-14 06:26:07.306000','https://wetalk.ibme.edu.vn/upload/hust-app//0838_con%20ca.jpg',_binary '',2),(8,'phamquynhltbn12@gmail.com','2024-04-13 11:19:19.360000','phamquynhltbn12@gmail.com','2024-04-13 11:19:19.360000','https://wetalk.ibme.edu.vn/upload/vocabularies//0121_con%20chim.gif',_binary '',9),(10,'truong8dt@gmail.com','2024-04-14 02:29:10.608000','truong8dt@gmail.com','2024-04-14 06:14:26.809000','https://wetalk.ibme.edu.vn/upload/hust-app//0119_con%20c%C3%A1.jpg',_binary '\0',2),(11,'truong8dt@gmail.com','2024-04-14 06:40:23.455000','truong8dt@gmail.com','2024-04-14 06:40:23.455000','https://wetalk.ibme.edu.vn/upload/vocabularies//0122_con%20chu%E1%BB%99t.png',_binary '',10);
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vocabulary_video`
--

LOCK TABLES `vocabulary_video` WRITE;
/*!40000 ALTER TABLE `vocabulary_video` DISABLE KEYS */;
INSERT INTO `vocabulary_video` VALUES (2,'phamquynhltbn12@gmail.com','2024-04-13 09:34:27.075000','truong8dt@gmail.com','2024-04-14 06:26:20.950000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0119_con%20c%C3%A1.mp4',2),(8,'phamquynhltbn12@gmail.com','2024-04-13 11:19:19.365000','phamquynhltbn12@gmail.com','2024-04-13 11:19:19.365000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0121_con%20chim.mp4',9),(10,'truong8dt@gmail.com','2024-04-14 02:29:10.653000','truong8dt@gmail.com','2024-04-14 06:26:20.950000',_binary '\0','https://wetalk.ibme.edu.vn/upload/hust-app//0837_con%20ca.mp4',2),(11,'truong8dt@gmail.com','2024-04-14 06:40:23.459000','truong8dt@gmail.com','2024-04-14 06:40:23.459000',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0122_con%20chu%E1%BB%99t.mp4',10);
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

-- Dump completed on 2024-04-15  8:56:01
