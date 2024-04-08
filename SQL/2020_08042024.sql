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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,'anonymousUser','2024-04-08 10:04:56.568000','anonymousUser','2024-04-08 12:39:39.969000','https://wetalk.ibme.edu.vn/upload/hust-app//0120_con%20ch%C3%B3.jpg','phamquynhltbn12@gmail.com','Phạm Văn Quỳnh ltbn'),(2,'anonymousUser','2024-04-08 10:16:07.370000','anonymousUser','2024-04-08 10:16:07.370000',NULL,'quynh12712053@gmail.com','Phạm Văn Quỳnh 127'),(3,'anonymousUser','2024-04-08 10:17:30.919000','anonymousUser','2024-04-08 10:17:30.919000',NULL,'quynh171217@gmail.com','Pham Van Quynh 17'),(4,'anonymousUser','2024-04-08 12:21:15.962000','anonymousUser','2024-04-08 12:21:15.962000',NULL,'truong8dt@gmail.com','Nguyễn Văn Trường'),(5,'anonymousUser','2024-04-08 12:22:35.137000','anonymousUser','2024-04-08 12:22:35.137000',NULL,'truongnguyenduc935@gmail.com','Nguyễn Văn Minh');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation`
--

LOCK TABLES `conversation` WRITE;
/*!40000 ALTER TABLE `conversation` DISABLE KEYS */;
INSERT INTO `conversation` VALUES (1,'phamquynhltbn12@gmail.com','2024-04-08 10:18:31.698000','phamquynhltbn12@gmail.com','2024-04-08 10:18:31.698000','phamquynhltbn12@gmail.com_phamquynhltbn12@gmail.com','SINGLE'),(2,'truong8dt@gmail.com','2024-04-08 12:22:56.898000','truong8dt@gmail.com','2024-04-08 12:22:56.898000','truong8dt@gmail.com_truong8dt@gmail.com','SINGLE');
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
INSERT INTO `friend_ship` VALUES ('quynh171217@gmail.com','2024-04-08 10:17:58.613000','10:18, 08/04/2024','2024-04-08 10:18:26.492000',200,3,1),('quynh171217@gmail.com','2024-04-08 10:17:50.353000','10:17, 08/04/2024','2024-04-08 10:17:50.353000',100,3,2),('truongnguyenduc935@gmail.com','2024-04-08 12:22:44.824000','12:22, 08/04/2024','2024-04-08 12:22:50.765000',200,5,4);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_member`
--

LOCK TABLES `group_member` WRITE;
/*!40000 ALTER TABLE `group_member` DISABLE KEYS */;
INSERT INTO `group_member` VALUES (1,'phamquynhltbn12@gmail.com','2024-04-08 10:18:31.706000','phamquynhltbn12@gmail.com','2024-04-08 10:18:31.706000',_binary '\0',NULL,1,1),(2,'phamquynhltbn12@gmail.com','2024-04-08 10:18:31.710000','phamquynhltbn12@gmail.com','2024-04-08 10:18:31.710000',_binary '\0',NULL,3,1),(3,'truong8dt@gmail.com','2024-04-08 12:22:56.900000','truong8dt@gmail.com','2024-04-08 12:22:56.900000',_binary '\0',NULL,4,2),(4,'truong8dt@gmail.com','2024-04-08 12:22:56.902000','truong8dt@gmail.com','2024-04-08 12:22:56.902000',_binary '\0',NULL,5,2);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
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
INSERT INTO `topic` VALUES (1,'phamquynhltbn12@gmail.com','2024-04-08 10:07:06.775000','phamquynhltbn12@gmail.com','2024-04-08 10:07:06.775000','Tất cả',NULL,NULL),(2,'phamquynhltbn12@gmail.com','2024-04-08 10:21:27.689000','phamquynhltbn12@gmail.com','2024-04-08 10:21:27.689000','Động vật',NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'anonymousUser','2024-04-08 10:04:56.677000','phamquynhltbn12@gmail.com','2024-04-08 12:39:40.146000','Bắc Ninh','https://wetalk.ibme.edu.vn/upload/hust-app//0120_con%20ch%C3%B3.jpg','2001-07-12 00:00:00.000000','phamquynhltbn12@gmail.com','MALE',_binary '\0','Phạm Văn Quỳnh ltbn','hust','1235689','ADMIN'),(2,'anonymousUser','2024-04-08 10:16:07.382000','anonymousUser','2024-04-08 10:16:07.382000',NULL,NULL,NULL,'quynh12712053@gmail.com',NULL,_binary '\0','Phạm Văn Quỳnh 127','hust',NULL,'ADMIN'),(3,'anonymousUser','2024-04-08 10:17:30.930000','anonymousUser','2024-04-08 10:17:30.930000',NULL,NULL,NULL,'quynh171217@gmail.com',NULL,_binary '\0','Pham Van Quynh 17','hust',NULL,'ADMIN'),(4,'anonymousUser','2024-04-08 12:21:15.974000','anonymousUser','2024-04-08 12:21:15.974000',NULL,NULL,NULL,'truong8dt@gmail.com',NULL,_binary '\0','Nguyễn Văn Trường','123456aA@',NULL,'ADMIN'),(5,'anonymousUser','2024-04-08 12:22:35.147000','anonymousUser','2024-04-08 12:22:35.147000',NULL,NULL,NULL,'truongnguyenduc935@gmail.com',NULL,_binary '\0','Nguyễn Văn Minh','123456aA@',NULL,'ADMIN');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vocabulary`
--

LOCK TABLES `vocabulary` WRITE;
/*!40000 ALTER TABLE `vocabulary` DISABLE KEYS */;
INSERT INTO `vocabulary` VALUES (1,'phamquynhltbn12@gmail.com','2024-04-08 10:20:11.578000','phamquynhltbn12@gmail.com','2024-04-08 10:20:11.591000','Con cá',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vocabulary_medium`
--

LOCK TABLES `vocabulary_medium` WRITE;
/*!40000 ALTER TABLE `vocabulary_medium` DISABLE KEYS */;
INSERT INTO `vocabulary_medium` VALUES (1,'phamquynhltbn12@gmail.com','2024-04-08 10:20:11.585000','phamquynhltbn12@gmail.com','2024-04-08 10:20:11.585000','https://wetalk.ibme.edu.vn/upload/vocabularies//0119_con%20c%C3%A1.jpg',_binary '','https://wetalk.ibme.edu.vn/upload/vocabularies//0119_con%20c%C3%A1.mp4',1);
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

-- Dump completed on 2024-04-08 20:20:43
