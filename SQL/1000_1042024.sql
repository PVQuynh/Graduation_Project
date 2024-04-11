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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,'anonymousUser','2024-04-08 10:04:56.568000','anonymousUser','2024-04-08 15:43:39.415000','https://wetalk.ibme.edu.vn/upload/hust-app//pvq.jpg','phamquynhltbn12@gmail.com','Phạm Văn Quỳnh ltbn'),(2,'anonymousUser','2024-04-08 10:16:07.370000','anonymousUser','2024-04-08 10:16:07.370000',NULL,'quynh12712053@gmail.com','Phạm Văn Quỳnh 127'),(3,'anonymousUser','2024-04-08 10:17:30.919000','anonymousUser','2024-04-08 10:17:30.919000',NULL,'quynh171217@gmail.com','Pham Van Quynh 17'),(4,'anonymousUser','2024-04-08 12:21:15.962000','anonymousUser','2024-04-08 12:21:15.962000',NULL,'truong8dt@gmail.com','Nguyễn Văn Trường'),(5,'anonymousUser','2024-04-08 12:22:35.137000','anonymousUser','2024-04-08 12:22:35.137000',NULL,'truongnguyenduc935@gmail.com','Nguyễn Văn Minh'),(6,'anonymousUser','2024-04-08 14:25:46.532000','anonymousUser','2024-04-09 08:28:15.830000','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','doanhtv2759@gmail.com','Doanh'),(7,'anonymousUser','2024-04-08 15:55:43.505000','anonymousUser','2024-04-08 15:55:43.505000',NULL,'hoangquanghuy@gmail.com','Hoàng Huy');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation`
--

LOCK TABLES `conversation` WRITE;
/*!40000 ALTER TABLE `conversation` DISABLE KEYS */;
INSERT INTO `conversation` VALUES (1,'phamquynhltbn12@gmail.com','2024-04-08 10:18:31.698000','phamquynhltbn12@gmail.com','2024-04-08 10:18:31.698000','phamquynhltbn12@gmail.com_phamquynhltbn12@gmail.com','SINGLE'),(2,'truong8dt@gmail.com','2024-04-08 12:22:56.898000','truong8dt@gmail.com','2024-04-08 12:22:56.898000','truong8dt@gmail.com_truong8dt@gmail.com','SINGLE'),(3,'doanhtv2759@gmail.com','2024-04-08 15:27:59.658000','doanhtv2759@gmail.com','2024-04-08 15:27:59.658000','doanhtv2759@gmail.com_doanhtv2759@gmail.com','SINGLE'),(4,'hoangquanghuy@gmail.com','2024-04-10 01:56:46.426000','hoangquanghuy@gmail.com','2024-04-10 01:56:46.426000','hoangquanghuy@gmail.com_hoangquanghuy@gmail.com','SINGLE'),(5,'hoangquanghuy@gmail.com','2024-04-10 01:57:08.324000','hoangquanghuy@gmail.com','2024-04-10 01:57:08.324000','hoangquanghuy@gmail.com_hoangquanghuy@gmail.com','SINGLE');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_collection`
--

LOCK TABLES `data_collection` WRITE;
/*!40000 ALTER TABLE `data_collection` DISABLE KEYS */;
INSERT INTO `data_collection` VALUES (6,'phamquynhltbn12@gmail.com','2024-04-10 02:39:52.157000','phamquynhltbn12@gmail.com','2024-04-10 02:39:52.157000',NULL,'https://wetalk.ibme.edu.vn/upload/hust-app//captured_image_1712716790633.jpg',NULL,0,100,3);
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
INSERT INTO `friend_ship` VALUES ('quynh171217@gmail.com','2024-04-08 10:17:58.613000','10:18, 08/04/2024','2024-04-08 10:18:26.492000',200,3,1),('doanhtv2759@gmail.com','2024-04-08 14:29:26.896000','15:20, 08/04/2024','2024-04-08 15:20:00.682000',200,6,1),('hoangquanghuy@gmail.com','2024-04-10 01:56:31.500000','01:57, 10/04/2024','2024-04-10 01:57:32.503000',200,7,1),('quynh171217@gmail.com','2024-04-08 10:17:50.353000','01:57, 10/04/2024','2024-04-10 01:57:57.417000',200,3,2),('doanhtv2759@gmail.com','2024-04-08 15:17:40.961000','01:57, 10/04/2024','2024-04-10 01:57:52.244000',200,6,2),('hoangquanghuy@gmail.com','2024-04-10 01:56:36.779000','01:57, 10/04/2024','2024-04-10 01:57:54.676000',200,7,2),('doanhtv2759@gmail.com','2024-04-08 15:17:41.518000','4/8/24, 3:17 PM','2024-04-08 15:17:41.518000',100,6,3),('hoangquanghuy@gmail.com','2024-04-10 01:56:41.690000','01:56, 10/04/2024','2024-04-10 01:56:41.690000',100,7,3),('truongnguyenduc935@gmail.com','2024-04-08 12:22:44.824000','12:22, 08/04/2024','2024-04-08 12:22:50.765000',200,5,4);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_member`
--

LOCK TABLES `group_member` WRITE;
/*!40000 ALTER TABLE `group_member` DISABLE KEYS */;
INSERT INTO `group_member` VALUES (1,'phamquynhltbn12@gmail.com','2024-04-08 10:18:31.706000','phamquynhltbn12@gmail.com','2024-04-08 10:18:31.706000',_binary '\0',NULL,1,1),(2,'phamquynhltbn12@gmail.com','2024-04-08 10:18:31.710000','phamquynhltbn12@gmail.com','2024-04-08 10:18:31.710000',_binary '\0',NULL,3,1),(3,'truong8dt@gmail.com','2024-04-08 12:22:56.900000','truong8dt@gmail.com','2024-04-08 12:22:56.900000',_binary '\0',NULL,4,2),(4,'truong8dt@gmail.com','2024-04-08 12:22:56.902000','truong8dt@gmail.com','2024-04-08 12:22:56.902000',_binary '\0',NULL,5,2),(5,'doanhtv2759@gmail.com','2024-04-08 15:27:59.668000','phamquynhltbn12@gmail.com','2024-04-10 14:50:20.938000',_binary '\0','2024-04-10 14:50:20.937975',6,3),(6,'doanhtv2759@gmail.com','2024-04-08 15:27:59.671000','doanhtv2759@gmail.com','2024-04-08 15:27:59.671000',_binary '\0',NULL,1,3),(7,'hoangquanghuy@gmail.com','2024-04-10 01:56:46.442000','hoangquanghuy@gmail.com','2024-04-10 01:56:46.442000',_binary '\0',NULL,7,4),(8,'hoangquanghuy@gmail.com','2024-04-10 01:56:46.444000','hoangquanghuy@gmail.com','2024-04-10 01:56:46.444000',_binary '\0',NULL,1,4),(9,'hoangquanghuy@gmail.com','2024-04-10 01:57:08.326000','hoangquanghuy@gmail.com','2024-04-10 01:57:08.326000',_binary '\0',NULL,7,5),(10,'hoangquanghuy@gmail.com','2024-04-10 01:57:08.328000','hoangquanghuy@gmail.com','2024-04-10 01:57:08.328000',_binary '\0',NULL,2,5);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'phamquynhltbn12@gmail.com','2024-04-09 07:39:17.543000','phamquynhltbn12@gmail.com','2024-04-09 07:39:17.543000','dg',NULL,'TEXT',100,5);
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'anonymousUser','2024-04-08 10:04:56.677000','phamquynhltbn12@gmail.com','2024-04-08 15:43:39.423000','Bắc Ninh','https://wetalk.ibme.edu.vn/upload/hust-app//pvq.jpg','2001-07-12 00:00:00.000000','phamquynhltbn12@gmail.com','MALE',_binary '\0','Phạm Văn Quỳnh ltbn','hust','1235689','ADMIN'),(2,'anonymousUser','2024-04-08 10:16:07.382000','anonymousUser','2024-04-08 10:16:07.382000',NULL,NULL,NULL,'quynh12712053@gmail.com',NULL,_binary '\0','Phạm Văn Quỳnh 127','hust',NULL,'ADMIN'),(3,'anonymousUser','2024-04-08 10:17:30.930000','anonymousUser','2024-04-08 10:17:30.930000',NULL,NULL,NULL,'quynh171217@gmail.com',NULL,_binary '\0','Pham Van Quynh 17','hust',NULL,'ADMIN'),(4,'anonymousUser','2024-04-08 12:21:15.974000','anonymousUser','2024-04-08 12:21:15.974000',NULL,NULL,NULL,'truong8dt@gmail.com',NULL,_binary '\0','Nguyễn Văn Trường','123456aA@',NULL,'ADMIN'),(5,'anonymousUser','2024-04-08 12:22:35.147000','anonymousUser','2024-04-08 12:22:35.147000',NULL,NULL,NULL,'truongnguyenduc935@gmail.com',NULL,_binary '\0','Nguyễn Văn Minh','123456aA@',NULL,'ADMIN'),(6,'anonymousUser','2024-04-08 14:25:46.558000','doanhtv2759@gmail.com','2024-04-09 09:08:08.495000','Namd','https://wetalk.ibme.edu.vn/upload/media-general//1000000037.jpg','2024-04-08 00:00:00.000000','doanhtv2759@gmail.com','MALE',_binary '\0','Doanh','s','0372232323','USER'),(7,'anonymousUser','2024-04-08 15:55:43.514000','anonymousUser','2024-04-08 15:55:43.514000',NULL,NULL,NULL,'hoangquanghuy@gmail.com',NULL,_binary '\0','Hoàng Huy','123456',NULL,'ADMIN');
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
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vocabulary`
--

LOCK TABLES `vocabulary` WRITE;
/*!40000 ALTER TABLE `vocabulary` DISABLE KEYS */;
INSERT INTO `vocabulary` VALUES (3,'phamquynhltbn12@gmail.com','2024-04-09 14:55:25.243000','phamquynhltbn12@gmail.com','2024-04-09 14:55:25.248000','con cá',2),(4,'phamquynhltbn12@gmail.com','2024-04-09 14:56:23.818000','phamquynhltbn12@gmail.com','2024-04-09 14:56:23.822000','con chó',2),(5,'phamquynhltbn12@gmail.com','2024-04-09 14:57:01.686000','phamquynhltbn12@gmail.com','2024-04-09 14:57:01.690000','con chim',2),(6,'phamquynhltbn12@gmail.com','2024-04-09 14:57:45.017000','phamquynhltbn12@gmail.com','2024-04-09 14:57:45.022000','con chuột',2),(7,'phamquynhltbn12@gmail.com','2024-04-09 15:00:40.360000','phamquynhltbn12@gmail.com','2024-04-09 15:00:40.364000','con lợn (con heo)',2),(8,'phamquynhltbn12@gmail.com','2024-04-09 15:09:53.268000','phamquynhltbn12@gmail.com','2024-04-09 15:09:53.307000','con muỗi',2),(9,'truong8dt@gmail.com','2024-04-09 16:17:49.755000','truong8dt@gmail.com','2024-04-09 16:17:49.898000','con chó',1),(10,'truong8dt@gmail.com','2024-04-09 16:17:49.759000','truong8dt@gmail.com','2024-04-09 16:17:49.898000','con cua',1),(11,'truong8dt@gmail.com','2024-04-09 16:17:49.762000','truong8dt@gmail.com','2024-04-10 15:38:55.391000','Con Cá',1),(12,'truong8dt@gmail.com','2024-04-09 16:17:49.765000','truong8dt@gmail.com','2024-04-09 16:17:49.899000','con sò',1),(13,'truong8dt@gmail.com','2024-04-09 16:17:49.768000','truong8dt@gmail.com','2024-04-09 16:17:49.899000','con chuột',1),(14,'truong8dt@gmail.com','2024-04-09 16:17:49.772000','truong8dt@gmail.com','2024-04-09 16:17:49.899000','con mèo',1),(15,'truong8dt@gmail.com','2024-04-09 16:17:49.775000','truong8dt@gmail.com','2024-04-09 16:17:49.899000','con dế',1),(16,'truong8dt@gmail.com','2024-04-09 16:17:49.777000','truong8dt@gmail.com','2024-04-09 16:17:49.899000','con dâu',1),(17,'truong8dt@gmail.com','2024-04-09 16:17:49.779000','truong8dt@gmail.com','2024-04-09 16:17:49.899000','con gà',1),(18,'truong8dt@gmail.com','2024-04-09 16:17:49.782000','truong8dt@gmail.com','2024-04-09 16:17:49.900000','cu_toi',1),(19,'truong8dt@gmail.com','2024-04-09 16:17:49.784000','truong8dt@gmail.com','2024-04-09 16:17:49.900000','con người',1),(20,'truong8dt@gmail.com','2024-04-09 16:17:49.787000','truong8dt@gmail.com','2024-04-09 16:17:49.900000','con bò',1),(21,'truong8dt@gmail.com','2024-04-09 16:17:49.789000','truong8dt@gmail.com','2024-04-09 16:17:49.901000','con_tho',1),(22,'truong8dt@gmail.com','2024-04-09 16:17:49.793000','truong8dt@gmail.com','2024-04-09 16:17:49.901000','con đẻ',1),(23,'truong8dt@gmail.com','2024-04-09 16:17:49.795000','truong8dt@gmail.com','2024-04-09 16:17:49.901000','con bê',1),(24,'truong8dt@gmail.com','2024-04-09 16:17:49.797000','truong8dt@gmail.com','2024-04-09 16:17:49.901000','con rối',1),(25,'truong8dt@gmail.com','2024-04-09 16:17:49.800000','truong8dt@gmail.com','2024-04-09 16:17:49.901000','con gái',1),(26,'truong8dt@gmail.com','2024-04-09 16:17:49.802000','truong8dt@gmail.com','2024-04-09 16:17:49.902000','con số',1),(27,'truong8dt@gmail.com','2024-04-09 16:17:49.804000','truong8dt@gmail.com','2024-04-09 16:17:49.902000','con voi',1),(28,'truong8dt@gmail.com','2024-04-09 16:17:49.807000','truong8dt@gmail.com','2024-04-09 16:17:49.902000','con mực_1',1),(29,'truong8dt@gmail.com','2024-04-09 16:17:49.809000','truong8dt@gmail.com','2024-04-09 16:17:49.903000','con vẹt',1),(30,'truong8dt@gmail.com','2024-04-09 16:17:49.811000','truong8dt@gmail.com','2024-04-09 16:17:49.903000','con chim',1),(31,'truong8dt@gmail.com','2024-04-09 16:17:49.814000','truong8dt@gmail.com','2024-04-09 16:17:49.903000','con thỏ',1),(32,'truong8dt@gmail.com','2024-04-09 16:17:49.816000','truong8dt@gmail.com','2024-04-09 16:17:49.903000','con mực',1),(33,'truong8dt@gmail.com','2024-04-09 16:17:49.818000','truong8dt@gmail.com','2024-04-09 16:17:49.903000','trẻ con_con nít',1),(34,'truong8dt@gmail.com','2024-04-09 16:17:49.821000','truong8dt@gmail.com','2024-04-09 16:17:49.903000','con gà_1',1),(35,'truong8dt@gmail.com','2024-04-09 16:17:49.823000','truong8dt@gmail.com','2024-04-09 16:17:49.904000','con chuột - Copy',1),(36,'truong8dt@gmail.com','2024-04-09 16:17:49.826000','truong8dt@gmail.com','2024-04-09 16:17:49.904000','con cóc',1),(37,'truong8dt@gmail.com','2024-04-09 16:17:49.828000','truong8dt@gmail.com','2024-04-09 16:17:49.904000','con ếch',1),(38,'truong8dt@gmail.com','2024-04-09 16:17:49.830000','truong8dt@gmail.com','2024-04-09 16:17:49.904000','con bê_1',1),(39,'truong8dt@gmail.com','2024-04-09 16:17:49.832000','truong8dt@gmail.com','2024-04-09 16:17:49.904000','côn trùng',1),(40,'truong8dt@gmail.com','2024-04-09 16:17:49.835000','truong8dt@gmail.com','2024-04-09 16:17:49.904000','con ghẹ',1),(41,'truong8dt@gmail.com','2024-04-09 16:17:49.837000','truong8dt@gmail.com','2024-04-09 16:17:49.904000','con rùa',1),(42,'truong8dt@gmail.com','2024-04-09 16:17:49.839000','truong8dt@gmail.com','2024-04-09 16:17:49.905000','con hàu',1),(43,'truong8dt@gmail.com','2024-04-09 16:17:49.842000','truong8dt@gmail.com','2024-04-09 16:17:49.905000','con quạ',1),(44,'truong8dt@gmail.com','2024-04-09 16:17:49.844000','truong8dt@gmail.com','2024-04-09 16:17:49.905000','con ốc',1),(45,'truong8dt@gmail.com','2024-04-09 16:17:49.847000','truong8dt@gmail.com','2024-04-09 16:17:49.905000','con dê',1),(46,'truong8dt@gmail.com','2024-04-09 16:17:49.850000','truong8dt@gmail.com','2024-04-09 16:17:49.905000','con ong',1),(47,'truong8dt@gmail.com','2024-04-09 16:17:49.853000','truong8dt@gmail.com','2024-04-09 16:17:49.905000','con cọp',1),(48,'truong8dt@gmail.com','2024-04-09 16:17:49.855000','truong8dt@gmail.com','2024-04-09 16:17:49.906000','con tôm',1),(49,'truong8dt@gmail.com','2024-04-09 16:17:49.858000','truong8dt@gmail.com','2024-04-09 16:17:49.906000','con_chim',1),(50,'truong8dt@gmail.com','2024-04-09 16:17:49.860000','truong8dt@gmail.com','2024-04-09 16:17:49.906000','con trỏ',1),(51,'truong8dt@gmail.com','2024-04-09 16:17:49.863000','truong8dt@gmail.com','2024-04-09 16:17:49.906000','con lợn (con heo)',1),(52,'truong8dt@gmail.com','2024-04-09 16:17:49.865000','truong8dt@gmail.com','2024-04-09 16:17:49.906000','con rắn',1),(53,'truong8dt@gmail.com','2024-04-09 16:17:49.867000','truong8dt@gmail.com','2024-04-09 16:17:49.906000','con nai',1),(54,'truong8dt@gmail.com','2024-04-09 16:17:49.870000','truong8dt@gmail.com','2024-04-09 16:17:49.906000','con gấu',1),(55,'truong8dt@gmail.com','2024-04-09 16:17:49.872000','truong8dt@gmail.com','2024-04-09 16:17:49.906000','con muỗi',1),(56,'truong8dt@gmail.com','2024-04-09 16:17:49.874000','truong8dt@gmail.com','2024-04-09 16:17:49.907000','con tem',1),(57,'truong8dt@gmail.com','2024-04-09 16:17:49.877000','truong8dt@gmail.com','2024-04-09 16:17:49.907000','con cò',1),(58,'truong8dt@gmail.com','2024-04-09 16:17:49.879000','truong8dt@gmail.com','2024-04-09 16:17:49.907000','con sóc',1),(59,'truong8dt@gmail.com','2024-04-09 16:17:49.881000','truong8dt@gmail.com','2024-04-09 16:17:49.907000','con vịt',1),(60,'truong8dt@gmail.com','2024-04-09 16:17:49.883000','truong8dt@gmail.com','2024-04-09 16:17:49.907000','con nai_1',1),(61,'truong8dt@gmail.com','2024-04-09 16:17:49.886000','truong8dt@gmail.com','2024-04-09 16:17:49.907000','con sâu',1),(62,'truong8dt@gmail.com','2024-04-09 16:17:49.888000','truong8dt@gmail.com','2024-04-09 16:17:49.907000','con sói',1),(63,'truong8dt@gmail.com','2024-04-09 16:17:49.890000','truong8dt@gmail.com','2024-04-09 16:17:49.907000','con cừu',1),(64,'truong8dt@gmail.com','2024-04-09 16:17:49.892000','truong8dt@gmail.com','2024-04-09 16:17:49.907000','dê con',1),(65,'truong8dt@gmail.com','2024-04-09 16:17:49.894000','truong8dt@gmail.com','2024-04-09 16:17:49.907000','con khỉ',1);
/*!40000 ALTER TABLE `vocabulary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vocabulary_medium`
--

DROP TABLE IF EXISTS `vocabulary_medium`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vocabulary_medium` (
  `vocabulary_medium_id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `image_location` varchar(255) DEFAULT NULL,
  `is_primary` bit(1) NOT NULL,
  `video_location` varchar(255) DEFAULT NULL,
  `vocabulary_id` bigint DEFAULT NULL,
  PRIMARY KEY (`vocabulary_medium_id`),
  KEY `FKtmg1c67q5yiho5eatffmiv1m7` (`vocabulary_id`),
  CONSTRAINT `FKtmg1c67q5yiho5eatffmiv1m7` FOREIGN KEY (`vocabulary_id`) REFERENCES `vocabulary` (`vocabulary_id`)
) ENGINE=InnoDB AUTO_INCREMENT=183 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vocabulary_medium`
--

LOCK TABLES `vocabulary_medium` WRITE;
/*!40000 ALTER TABLE `vocabulary_medium` DISABLE KEYS */;
INSERT INTO `vocabulary_medium` VALUES (5,'phamquynhltbn12@gmail.com','2024-04-09 14:55:25.245000','phamquynhltbn12@gmail.com','2024-04-09 14:55:25.245000','https://wetalk.ibme.edu.vn/upload/vocabularies//0119_con%20c%C3%A1.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0119_con%20c%C3%A1.mp4',3),(6,'phamquynhltbn12@gmail.com','2024-04-09 14:56:23.820000','phamquynhltbn12@gmail.com','2024-04-09 14:56:23.820000','https://wetalk.ibme.edu.vn/upload/vocabularies//0120_con%20ch%C3%B3.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0120_con%20ch%C3%B3.mp4',4),(7,'phamquynhltbn12@gmail.com','2024-04-09 14:57:01.688000','phamquynhltbn12@gmail.com','2024-04-09 14:58:59.038000','https://wetalk.ibme.edu.vn/upload/vocabularies//0121_con%20chim.gif',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0121_con%20chim.mp4',5),(8,'phamquynhltbn12@gmail.com','2024-04-09 14:57:45.019000','phamquynhltbn12@gmail.com','2024-04-09 14:57:45.019000','https://wetalk.ibme.edu.vn/upload/vocabularies//0122_con%20chu%E1%BB%99t.png',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0122_con%20chu%E1%BB%99t.mp4',6),(9,'phamquynhltbn12@gmail.com','2024-04-09 14:57:45.019000','truong8dt@gmail.com','2024-04-10 17:03:38.761000','https://wetalk.ibme.edu.vn/upload/hust-app//0837_con%20cho.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0119_con%20c%C3%A1.mp4',9),(10,'phamquynhltbn12@gmail.com','2024-04-09 15:09:53.298000','phamquynhltbn12@gmail.com','2024-04-09 15:09:53.298000','https://wetalk.ibme.edu.vn/upload/vocabularies//0124_con%20mu%E1%BB%97i.webp',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0124_con%20mu%E1%BB%97i.mp4',8),(125,'truong8dt@gmail.com','2024-04-09 16:17:49.757000','truong8dt@gmail.com','2024-04-09 16:17:49.757000','https://wetalk.ibme.edu.vn/upload/vocabularies//0120_con%20ch%C3%B3.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0120_con%20ch%C3%B3.mp4',9),(126,'truong8dt@gmail.com','2024-04-09 16:17:49.760000','truong8dt@gmail.com','2024-04-09 16:17:49.760000','https://wetalk.ibme.edu.vn/upload/vocabularies//0811_con%20cua.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0811_con%20cua.mp4',10),(127,'truong8dt@gmail.com','2024-04-09 16:17:49.763000','truong8dt@gmail.com','2024-04-09 16:17:49.763000','https://wetalk.ibme.edu.vn/upload/vocabularies//0119_con%20c%C3%A1.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0119_con%20c%C3%A1.mp4',11),(128,'truong8dt@gmail.com','2024-04-09 16:17:49.766000','truong8dt@gmail.com','2024-04-09 16:17:49.766000','https://wetalk.ibme.edu.vn/upload/vocabularies//0501_con%20s%C3%B2.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0501_con%20s%C3%B2.mp4',12),(129,'truong8dt@gmail.com','2024-04-09 16:17:49.770000','truong8dt@gmail.com','2024-04-09 16:17:49.770000','https://wetalk.ibme.edu.vn/upload/vocabularies//0122_con%20chu%E1%BB%99t.png',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0122_con%20chu%E1%BB%99t.mp4',13),(130,'truong8dt@gmail.com','2024-04-09 16:17:49.773000','truong8dt@gmail.com','2024-04-09 16:17:49.773000',NULL,_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0125_con%20m%C3%A8o.mp4',14),(131,'truong8dt@gmail.com','2024-04-09 16:17:49.776000','truong8dt@gmail.com','2024-04-09 16:17:49.776000','https://wetalk.ibme.edu.vn/upload/vocabularies//0497_con%20d%E1%BA%BF.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0497_con%20d%E1%BA%BF.mp4',15),(132,'truong8dt@gmail.com','2024-04-09 16:17:49.778000','truong8dt@gmail.com','2024-04-09 16:17:49.778000',NULL,_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0813_con%20d%C3%A2u.mp4',16),(133,'truong8dt@gmail.com','2024-04-09 16:17:49.780000','truong8dt@gmail.com','2024-04-09 16:17:49.780000','https://wetalk.ibme.edu.vn/upload/vocabularies//0499_con%20g%C3%A0.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0499_con%20g%C3%A0.mp4',17),(134,'truong8dt@gmail.com','2024-04-09 16:17:49.783000','truong8dt@gmail.com','2024-04-09 16:17:49.783000','https://wetalk.ibme.edu.vn/upload/vocabularies//139_cu_toi.jpg',_binary '',NULL,18),(135,'truong8dt@gmail.com','2024-04-09 16:17:49.785000','truong8dt@gmail.com','2024-04-09 16:17:49.785000',NULL,_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0126_con%20ng%C6%B0%E1%BB%9Di.mp4',19),(136,'truong8dt@gmail.com','2024-04-09 16:17:49.788000','truong8dt@gmail.com','2024-04-09 16:17:49.788000','https://wetalk.ibme.edu.vn/upload/vocabularies//0494_con%20b%C3%B2.png',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0494_con%20b%C3%B2.mp4',20),(137,'truong8dt@gmail.com','2024-04-09 16:17:49.791000','truong8dt@gmail.com','2024-04-09 16:17:49.791000','https://wetalk.ibme.edu.vn/upload/vocabularies//0830_con_tho.jpg',_binary '',NULL,21),(138,'truong8dt@gmail.com','2024-04-09 16:17:49.794000','truong8dt@gmail.com','2024-04-09 16:17:49.794000',NULL,_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0498_con%20%C4%91%E1%BA%BB.mp4',22),(139,'truong8dt@gmail.com','2024-04-09 16:17:49.796000','truong8dt@gmail.com','2024-04-09 16:17:49.796000','https://wetalk.ibme.edu.vn/upload/vocabularies//0493_con%20b%C3%AA.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0493_con%20b%C3%AA.mp4',23),(140,'truong8dt@gmail.com','2024-04-09 16:17:49.798000','truong8dt@gmail.com','2024-04-09 16:17:49.798000',NULL,_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0824_con%20r%E1%BB%91i.mp4',24),(141,'truong8dt@gmail.com','2024-04-09 16:17:49.801000','truong8dt@gmail.com','2024-04-09 16:17:49.801000',NULL,_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0815_con%20g%C3%A1i.mp4',25),(142,'truong8dt@gmail.com','2024-04-09 16:17:49.803000','truong8dt@gmail.com','2024-04-09 16:17:49.803000',NULL,_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0502_con%20s%E1%BB%91.mp4',26),(143,'truong8dt@gmail.com','2024-04-09 16:17:49.805000','truong8dt@gmail.com','2024-04-09 16:17:49.805000','https://wetalk.ibme.edu.vn/upload/vocabularies//0835_con%20voi.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0835_con%20voi.mp4',27),(144,'truong8dt@gmail.com','2024-04-09 16:17:49.808000','truong8dt@gmail.com','2024-04-09 16:17:49.808000','https://wetalk.ibme.edu.vn/upload/vocabularies//0819_con%20m%E1%BB%B1c_1.jpg',_binary '',NULL,28),(145,'truong8dt@gmail.com','2024-04-09 16:17:49.810000','truong8dt@gmail.com','2024-04-09 16:17:49.810000','https://wetalk.ibme.edu.vn/upload/vocabularies//0833_con%20v%E1%BA%B9t.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0833_con%20v%E1%BA%B9t.mp4',29),(146,'truong8dt@gmail.com','2024-04-09 16:17:49.812000','truong8dt@gmail.com','2024-04-09 16:17:49.812000',NULL,_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0121_con%20chim.mp4',30),(147,'truong8dt@gmail.com','2024-04-09 16:17:49.815000','truong8dt@gmail.com','2024-04-09 16:17:49.815000','https://wetalk.ibme.edu.vn/upload/vocabularies//0830_con%20th%E1%BB%8F.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0830_con%20th%E1%BB%8F.mp4',31),(148,'truong8dt@gmail.com','2024-04-09 16:17:49.817000','truong8dt@gmail.com','2024-04-09 16:17:49.817000','https://wetalk.ibme.edu.vn/upload/vocabularies//0819_con%20m%E1%BB%B1c.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0819_con%20m%E1%BB%B1c.mp4',32),(149,'truong8dt@gmail.com','2024-04-09 16:17:49.819000','truong8dt@gmail.com','2024-04-09 16:17:49.819000',NULL,_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0213_tr%E1%BA%BB%20con_con%20n%C3%ADt.mp4',33),(150,'truong8dt@gmail.com','2024-04-09 16:17:49.822000','truong8dt@gmail.com','2024-04-09 16:17:49.822000','https://wetalk.ibme.edu.vn/upload/vocabularies//0499_con%20g%C3%A0_1.jpg',_binary '',NULL,34),(151,'truong8dt@gmail.com','2024-04-09 16:17:49.824000','truong8dt@gmail.com','2024-04-09 16:17:49.824000',NULL,_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0122_con%20chu%E1%BB%99t%20-%20Copy.mp4',35),(152,'truong8dt@gmail.com','2024-04-09 16:17:49.826000','truong8dt@gmail.com','2024-04-09 16:17:49.826000','https://wetalk.ibme.edu.vn/upload/vocabularies//0809_con%20c%C3%B3c.png',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0809_con%20c%C3%B3c.mp4',36),(153,'truong8dt@gmail.com','2024-04-09 16:17:49.829000','truong8dt@gmail.com','2024-04-09 16:17:49.829000','https://wetalk.ibme.edu.vn/upload/vocabularies//0814_con%20%E1%BA%BFch.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0814_con%20%E1%BA%BFch.mp4',37),(154,'truong8dt@gmail.com','2024-04-09 16:17:49.831000','truong8dt@gmail.com','2024-04-09 16:17:49.831000','https://wetalk.ibme.edu.vn/upload/vocabularies//0493_con%20b%C3%AA_1.jpg',_binary '',NULL,38),(155,'truong8dt@gmail.com','2024-04-09 16:17:49.833000','truong8dt@gmail.com','2024-04-09 16:17:49.833000',NULL,_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0128_c%C3%B4n%20tr%C3%B9ng.mp4',39),(156,'truong8dt@gmail.com','2024-04-09 16:17:49.836000','truong8dt@gmail.com','2024-04-09 16:17:49.836000','https://wetalk.ibme.edu.vn/upload/vocabularies//0817_con%20gh%E1%BA%B9.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0817_con%20gh%E1%BA%B9.mp4',40),(157,'truong8dt@gmail.com','2024-04-09 16:17:49.838000','truong8dt@gmail.com','2024-04-09 16:17:49.838000',NULL,_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0825_con%20r%C3%B9a.mp4',41),(158,'truong8dt@gmail.com','2024-04-09 16:17:49.840000','truong8dt@gmail.com','2024-04-09 16:17:49.840000','https://wetalk.ibme.edu.vn/upload/vocabularies//0659_con%20h%C3%A0u.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0659_con%20h%C3%A0u.mp4',42),(159,'truong8dt@gmail.com','2024-04-09 16:17:49.843000','truong8dt@gmail.com','2024-04-09 16:17:49.843000','https://wetalk.ibme.edu.vn/upload/vocabularies//0822_con%20qu%E1%BA%A1.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0822_con%20qu%E1%BA%A1.mp4',43),(160,'truong8dt@gmail.com','2024-04-09 16:17:49.845000','truong8dt@gmail.com','2024-04-09 16:17:49.845000','https://wetalk.ibme.edu.vn/upload/vocabularies//0500_con%20%E1%BB%91c.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0500_con%20%E1%BB%91c.mp4',44),(161,'truong8dt@gmail.com','2024-04-09 16:17:49.848000','truong8dt@gmail.com','2024-04-09 16:17:49.848000','https://wetalk.ibme.edu.vn/upload/vocabularies//0496_con%20d%C3%AA.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0496_con%20d%C3%AA.mp4',45),(162,'truong8dt@gmail.com','2024-04-09 16:17:49.852000','truong8dt@gmail.com','2024-04-09 16:17:49.852000','https://wetalk.ibme.edu.vn/upload/vocabularies//0821_con%20ong.png',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0821_con%20ong.mp4',46),(163,'truong8dt@gmail.com','2024-04-09 16:17:49.854000','truong8dt@gmail.com','2024-04-09 16:17:49.854000','https://wetalk.ibme.edu.vn/upload/vocabularies//0810_con%20c%E1%BB%8Dp.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0810_con%20c%E1%BB%8Dp.mp4',47),(164,'truong8dt@gmail.com','2024-04-09 16:17:49.856000','truong8dt@gmail.com','2024-04-09 16:17:49.856000','https://wetalk.ibme.edu.vn/upload/vocabularies//0831_con%20t%C3%B4m.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0831_con%20t%C3%B4m.mp4',48),(165,'truong8dt@gmail.com','2024-04-09 16:17:49.859000','truong8dt@gmail.com','2024-04-09 16:17:49.859000','https://wetalk.ibme.edu.vn/upload/vocabularies//0121_con_chim.gif',_binary '',NULL,49),(166,'truong8dt@gmail.com','2024-04-09 16:17:49.861000','truong8dt@gmail.com','2024-04-09 16:17:49.861000',NULL,_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0832_con%20tr%E1%BB%8F.mp4',50),(167,'truong8dt@gmail.com','2024-04-09 16:17:49.864000','truong8dt@gmail.com','2024-04-09 16:17:49.864000','https://wetalk.ibme.edu.vn/upload/vocabularies//0123_con%20l%E1%BB%A3n%20%28con%20heo%29.png',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0123_con%20l%E1%BB%A3n%20%28con%20heo%29.mp4',51),(168,'truong8dt@gmail.com','2024-04-09 16:17:49.866000','truong8dt@gmail.com','2024-04-09 16:17:49.866000','https://wetalk.ibme.edu.vn/upload/vocabularies//0823_con%20r%E1%BA%AFn.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0823_con%20r%E1%BA%AFn.mp4',52),(169,'truong8dt@gmail.com','2024-04-09 16:17:49.868000','truong8dt@gmail.com','2024-04-09 16:17:49.868000','https://wetalk.ibme.edu.vn/upload/vocabularies//0820_con%20nai.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0820_con%20nai.mp4',53),(170,'truong8dt@gmail.com','2024-04-09 16:17:49.871000','truong8dt@gmail.com','2024-04-09 16:17:49.871000','https://wetalk.ibme.edu.vn/upload/vocabularies//0816_con%20g%E1%BA%A5u.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0816_con%20g%E1%BA%A5u.mp4',54),(171,'truong8dt@gmail.com','2024-04-09 16:17:49.873000','truong8dt@gmail.com','2024-04-09 16:17:49.873000','https://wetalk.ibme.edu.vn/upload/vocabularies//0124_con%20mu%E1%BB%97i.webp',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0124_con%20mu%E1%BB%97i.mp4',55),(172,'truong8dt@gmail.com','2024-04-09 16:17:49.875000','truong8dt@gmail.com','2024-04-09 16:17:49.875000',NULL,_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0829_con%20tem.mp4',56),(173,'truong8dt@gmail.com','2024-04-09 16:17:49.877000','truong8dt@gmail.com','2024-04-09 16:17:49.877000','https://wetalk.ibme.edu.vn/upload/vocabularies//0495_con%20c%C3%B2.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0495_con%20c%C3%B2.mp4',57),(174,'truong8dt@gmail.com','2024-04-09 16:17:49.880000','truong8dt@gmail.com','2024-04-09 16:17:49.880000','https://wetalk.ibme.edu.vn/upload/vocabularies//0827_con%20s%C3%B3c.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0827_con%20s%C3%B3c.mp4',58),(175,'truong8dt@gmail.com','2024-04-09 16:17:49.882000','truong8dt@gmail.com','2024-04-09 16:17:49.882000','https://wetalk.ibme.edu.vn/upload/vocabularies//0834_con%20v%E1%BB%8Bt.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0834_con%20v%E1%BB%8Bt.mp4',59),(176,'truong8dt@gmail.com','2024-04-09 16:17:49.884000','truong8dt@gmail.com','2024-04-09 16:17:49.884000','https://wetalk.ibme.edu.vn/upload/vocabularies//0820_con%20nai_1.jpg',_binary '',NULL,60),(177,'truong8dt@gmail.com','2024-04-09 16:17:49.886000','truong8dt@gmail.com','2024-04-09 16:17:49.886000','https://wetalk.ibme.edu.vn/upload/vocabularies//0826_con%20s%C3%A2u.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0826_con%20s%C3%A2u.mp4',61),(178,'truong8dt@gmail.com','2024-04-09 16:17:49.889000','truong8dt@gmail.com','2024-04-09 16:17:49.889000','https://wetalk.ibme.edu.vn/upload/vocabularies//0828_con%20s%C3%B3i.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0828_con%20s%C3%B3i.mp4',62),(179,'truong8dt@gmail.com','2024-04-09 16:17:49.891000','truong8dt@gmail.com','2024-04-09 16:17:49.891000','https://wetalk.ibme.edu.vn/upload/vocabularies//0812_con%20c%E1%BB%ABu.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0812_con%20c%E1%BB%ABu.mp4',63),(180,'truong8dt@gmail.com','2024-04-09 16:17:49.893000','truong8dt@gmail.com','2024-04-09 16:17:49.893000','https://wetalk.ibme.edu.vn/upload/vocabularies//0530_d%C3%AA%20con.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0530_d%C3%AA%20con.mp4',64),(181,'truong8dt@gmail.com','2024-04-09 16:17:49.895000','truong8dt@gmail.com','2024-04-09 16:17:49.895000','https://wetalk.ibme.edu.vn/upload/vocabularies//0818_con%20kh%E1%BB%89.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0818_con%20kh%E1%BB%89.mp4',65),(182,'truong8dt@gmail.com','2024-04-10 16:40:02.899000','truong8dt@gmail.com','2024-04-10 16:40:02.899000','https://wetalk.ibme.edu.vn/upload/hust-app//0836_con%20ca.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/hust-app//0119_con%20c%C3%A1.mp4',3);
/*!40000 ALTER TABLE `vocabulary_medium` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-11  9:48:10
