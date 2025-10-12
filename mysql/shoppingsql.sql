CREATE DATABASE  IF NOT EXISTS `shopping` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `shopping`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)v2
--
-- Host: localhost    Database: shopping
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `使用者`
--

DROP TABLE IF EXISTS `使用者`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `使用者` (
  `使用者編號` int NOT NULL AUTO_INCREMENT,
  `姓名` varchar(45) NOT NULL,
  `性別` enum('男生','女生','其他') DEFAULT '其他',
  `年齡` varchar(45) NOT NULL,
  `地址` varchar(100) NOT NULL,
  `帳號` varchar(50) NOT NULL,
  `密碼` varchar(255) NOT NULL,
  `電子郵件` varchar(100) DEFAULT NULL,
  `建立時間` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`使用者編號`),
  UNIQUE KEY `帳號` (`帳號`),
  UNIQUE KEY `UKddej2yntgnndtgrbobfckxtf7` (`帳號`),
  UNIQUE KEY `電子郵件` (`電子郵件`),
  UNIQUE KEY `UKh5wiu093xptxs4dxwoq1ip5h4` (`電子郵件`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `使用者`
--

LOCK TABLES `使用者` WRITE;
/*!40000 ALTER TABLE `使用者` DISABLE KEYS */;
INSERT INTO `使用者` VALUES (1,'','男生','','','user1','123456','user1@example.com','2025-10-04 14:57:57'),(2,'','男生','','','user2','123456','user2@example.com','2025-10-04 14:57:57'),(3,'','男生','','','user3','123456','user3@example.com','2025-10-04 14:57:57'),(4,'','男生','','','user4','123456','user4@example.com','2025-10-04 14:57:57');
/*!40000 ALTER TABLE `使用者` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `商品`
--

DROP TABLE IF EXISTS `商品`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `商品` (
  `商品編號` int NOT NULL AUTO_INCREMENT,
  `上架者編號` int NOT NULL,
  `商品名稱` varchar(100) NOT NULL,
  `商品圖片` varchar(500) DEFAULT NULL,
  `商品描述` text,
  `顏色總類` varchar(100) NOT NULL,
  `尺寸總類` varchar(45) NOT NULL,
  `價格` decimal(10,0) NOT NULL,
  `庫存數量` int DEFAULT '0',
  `上架時間` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`商品編號`),
  KEY `FKobs4lmqyxy0ixb4wske23vfkr` (`上架者編號`),
  CONSTRAINT `FKobs4lmqyxy0ixb4wske23vfkr` FOREIGN KEY (`上架者編號`) REFERENCES `使用者` (`使用者編號`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `商品`
--

LOCK TABLES `商品` WRITE;
/*!40000 ALTER TABLE `商品` DISABLE KEYS */;
INSERT INTO `商品` VALUES (16,1,'素色襯衫（白）','shirt_white.jpg','經典白襯衫，百搭設計，適合正式與休閒場合。','白','S,M,L,XL',890,50,'2025-10-04 14:58:00'),(17,2,'修身西裝褲（深灰）','trousers_grey.jpg','西裝布料製成，彈性佳不易皺。','深灰','S,M,L,XL',1290,40,'2025-10-04 14:58:00'),(18,3,'細針織毛衣（米色）','knit_beige.jpg','柔軟觸感，適合秋冬層次穿搭。','米色,深藍','M,L,XL',990,35,'2025-10-04 14:58:00'),(19,1,'西裝外套（海軍藍）','blazer_navy.jpg','正式場合與休閒穿搭皆可的西裝外套。','海軍藍','M,L,XL',2390,20,'2025-10-04 14:58:00'),(20,4,'棉質POLO衫（黑）','polo_black.jpg','吸濕排汗材質，通勤與休閒皆適合。','黑,白,灰','S,M,L,XL',790,60,'2025-10-04 14:58:00'),(21,2,'修身西裝褲（深灰）','trousers_grey.jpg','西裝布料製成，彈性佳不易皺。','深灰','S,M,L,XL',1290,40,'2025-10-04 14:58:00');
/*!40000 ALTER TABLE `商品` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `訂單`
--

DROP TABLE IF EXISTS `訂單`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `訂單` (
  `訂單編號` int NOT NULL AUTO_INCREMENT,
  `使用者編號` int NOT NULL,
  `總金額` decimal(10,0) NOT NULL,
  `狀態` varchar(3) DEFAULT NULL,
  `建立時間` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`訂單編號`),
  KEY `使用者編號` (`使用者編號`),
  CONSTRAINT `訂單_ibfk_1` FOREIGN KEY (`使用者編號`) REFERENCES `使用者` (`使用者編號`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `訂單`
--

LOCK TABLES `訂單` WRITE;
/*!40000 ALTER TABLE `訂單` DISABLE KEYS */;
INSERT INTO `訂單` VALUES (1,1,3000,'完成','2025-10-04 16:00:00'),(2,2,2500,'完成','2025-10-04 16:10:00'),(3,3,1500,'進行中','2025-10-04 16:20:00'),(4,4,4000,'取消','2025-10-04 16:30:00');
/*!40000 ALTER TABLE `訂單` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `訂單明細`
--

DROP TABLE IF EXISTS `訂單明細`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `訂單明細` (
  `明細編號` int NOT NULL AUTO_INCREMENT,
  `訂單編號` int NOT NULL,
  `商品編號` int NOT NULL,
  `數量` int NOT NULL,
  `單價` decimal(10,0) NOT NULL,
  PRIMARY KEY (`明細編號`),
  KEY `訂單編號` (`訂單編號`),
  KEY `商品編號` (`商品編號`),
  CONSTRAINT `訂單明細_ibfk_1` FOREIGN KEY (`訂單編號`) REFERENCES `訂單` (`訂單編號`),
  CONSTRAINT `訂單明細_ibfk_2` FOREIGN KEY (`商品編號`) REFERENCES `商品` (`商品編號`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `訂單明細`
--

LOCK TABLES `訂單明細` WRITE;
/*!40000 ALTER TABLE `訂單明細` DISABLE KEYS */;
INSERT INTO `訂單明細` VALUES (1,1,16,2,890),(2,1,19,1,2390),(3,2,17,1,1290),(4,2,20,1,790),(5,3,18,1,990),(6,4,16,2,890);
/*!40000 ALTER TABLE `訂單明細` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `評論`
--

DROP TABLE IF EXISTS `評論`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `評論` (
  `評論編號` int NOT NULL AUTO_INCREMENT,
  `商品編號` int NOT NULL,
  `使用者編號` int NOT NULL,
  `評分` int DEFAULT NULL,
  `評論內容` text,
  `建立時間` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`評論編號`),
  KEY `商品編號` (`商品編號`),
  KEY `使用者編號` (`使用者編號`),
  CONSTRAINT `評論_ibfk_1` FOREIGN KEY (`商品編號`) REFERENCES `商品` (`商品編號`),
  CONSTRAINT `評論_ibfk_2` FOREIGN KEY (`使用者編號`) REFERENCES `使用者` (`使用者編號`),
  CONSTRAINT `評論_chk_1` CHECK (((`評分` >= 1) and (`評分` <= 5)))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `評論`
--

LOCK TABLES `評論` WRITE;
/*!40000 ALTER TABLE `評論` DISABLE KEYS */;
INSERT INTO `評論` VALUES (1,16,1,5,'質感很好，穿起來舒適','2025-10-04 17:00:00'),(2,17,2,4,'西裝褲合身，彈性佳','2025-10-04 17:05:00'),(3,18,3,3,'毛衣還可以，有點薄','2025-10-04 17:10:00'),(4,19,1,5,'外套質感一流','2025-10-04 17:15:00'),(5,20,4,2,'POLO衫顏色不太對','2025-10-04 17:20:00');
/*!40000 ALTER TABLE `評論` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `購物車`
--

DROP TABLE IF EXISTS `購物車`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `購物車` (
  `購物車編號` int NOT NULL AUTO_INCREMENT,
  `使用者編號` int NOT NULL,
  `商品編號` int NOT NULL,
  `商品大小` varchar(255) NOT NULL,
  `商品顏色` varchar(255) NOT NULL,
  `數量` int NOT NULL,
  `加入時間` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`購物車編號`),
  KEY `使用者編號` (`使用者編號`),
  KEY `商品編號` (`商品編號`),
  CONSTRAINT `購物車_ibfk_1` FOREIGN KEY (`使用者編號`) REFERENCES `使用者` (`使用者編號`),
  CONSTRAINT `購物車_ibfk_2` FOREIGN KEY (`商品編號`) REFERENCES `商品` (`商品編號`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `購物車`
--

LOCK TABLES `購物車` WRITE;
/*!40000 ALTER TABLE `購物車` DISABLE KEYS */;
INSERT INTO `購物車` VALUES (2,1,16,'XL','red',2,'2025-10-04 15:00:00'),(3,1,19,'XL','red',1,'2025-10-04 15:01:00'),(4,2,17,'XL','red',3,'2025-10-04 15:02:00'),(5,2,20,'XL','red',1,'2025-10-04 15:03:00'),(6,3,18,'XL','red',2,'2025-10-04 15:04:00'),(7,3,17,'XL','red',1,'2025-10-04 15:05:00'),(8,4,20,'XLL','blue',100,'2025-10-06 16:22:16'),(9,4,16,'XL','red',1,'2025-10-04 15:07:00'),(10,1,18,'XL','red',1,'2025-10-04 15:08:00'),(11,2,19,'XL','red',2,'2025-10-04 15:09:00'),(12,1,16,'XL','red',2,'2025-10-04 15:00:00'),(13,1,19,'XL','red',1,'2025-10-04 15:01:00'),(14,2,17,'XL','red',3,'2025-10-04 15:02:00'),(15,2,20,'XL','red',1,'2025-10-04 15:03:00'),(16,3,18,'XL','red',2,'2025-10-04 15:04:00'),(17,3,17,'XL','red',1,'2025-10-04 15:05:00'),(18,4,20,'XL','red',4,'2025-10-04 15:06:00'),(19,4,16,'XL','red',1,'2025-10-04 15:07:00'),(20,1,18,'XL','red',1,'2025-10-04 15:08:00'),(21,2,19,'XL','red',2,'2025-10-04 15:09:00');
/*!40000 ALTER TABLE `購物車` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-08 14:41:04
