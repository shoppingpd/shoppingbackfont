CREATE DATABASE  IF NOT EXISTS `shopping` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `shopping`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
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
  `姓名` varchar(50) NOT NULL,
  `性別` varchar(50) NOT NULL,
  `年齡` varchar(50) NOT NULL,
  `地址` varchar(50) NOT NULL,
  `帳號` varchar(50) NOT NULL,
  `密碼` varchar(255) NOT NULL,
  `電子郵件` varchar(100) DEFAULT NULL,
  `建立時間` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`使用者編號`),
  UNIQUE KEY `帳號` (`帳號`),
  UNIQUE KEY `UKddej2yntgnndtgrbobfckxtf7` (`帳號`),
  UNIQUE KEY `電子郵件` (`電子郵件`),
  UNIQUE KEY `UKh5wiu093xptxs4dxwoq1ip5h4` (`電子郵件`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `使用者`
--

LOCK TABLES `使用者` WRITE;
/*!40000 ALTER TABLE `使用者` DISABLE KEYS */;
INSERT INTO `使用者` VALUES (1,'小明1','男生','25','123','user1','123456','user1@example.com','2025-10-04 14:57:57'),(2,'小明100','男生','25','234','user2','123456','user2@example.com','2025-10-04 14:57:57'),(3,'小明3','男生','25','23','user3','123456','user3@example.com','2025-10-04 14:57:57'),(4,'小明3','男生','25','334','user4','123456','user4@example.com','2025-10-04 14:57:57'),(25,'小明4','男生','25','台北市','mingX91','123456','mingX91@mail.com','2025-10-06 17:13:12'),(26,'小華','男生','30','新北市','huaY72','123456','huaY72@mail.com','2025-10-06 17:13:12'),(27,'小美','女生','22','台中市','meiZ84','123456','meiZ84@mail.com','2025-10-06 17:13:12'),(28,'小強','男生','28','高雄市','qiangA35','123456','qiangA35@mail.com','2025-10-06 17:13:12'),(29,'小美玲','女生','26','台南市','meilingB63','123456','meilingB63@mail.com','2025-10-06 17:13:12'),(30,'小傑','男生','35','桃園市','jieC19','123456','jieC19@mail.com','2025-10-06 17:13:12'),(31,'小芳','女生','24','新竹市','fangD48','123456','fangD48@mail.com','2025-10-06 17:13:12'),(32,'小宇','男生','29','台北市','yuE57','123456','yuE57@mail.com','2025-10-06 17:13:12'),(33,'小靜','女生','31','台中市','jingF86','123456','jingF86@mail.com','2025-10-06 17:13:12'),(34,'小龍','男生','27','高雄市','longG24','123456','longG24@mail.com','2025-10-06 17:13:12'),(35,'小明122200','男生','25','234','user2123','123456','1sfegf@example.com','2025-10-06 18:06:28');
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
) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `商品`
--

LOCK TABLES `商品` WRITE;
/*!40000 ALTER TABLE `商品` DISABLE KEYS */;
INSERT INTO `商品` VALUES (16,1,'女裝- BEAVER 短袖上衣','女裝BEAVER 短袖上衣.jpg','短袖上衣以撞色結合經典LOGO印花，搭配反摺袖與不規則下襬巧思，展現輕盈俏皮感，是夏季不可或缺的元氣單品。','黑#000000,白#FFFFFF,粉紫#d27497,淺綠#90926F','XS,S,M,L',1584,50,'2025-10-04 14:58:00'),(17,2,'男裝-TAIWAN DAY','男裝TAIWAN DAY.jpg','此款短袖T恤正面低調點綴品牌LOGO，背面滿版插畫印花傳遞濃濃台灣風情，是日常出遊與文化穿搭的必備單品。','黑#000000,白#FFFFFF,灰#808080,藍#3030FF','S,M,L,XL',1824,40,'2025-10-04 14:58:00'),(18,3,'女裝- COOPER PLAID 短袖上衣','女裝COOPER PLAID短袖上衣.jpg','經典 T 恤LOGO換上格紋新裝！Cooper Plaid 短袖上衣以溫暖格紋設計煥新登場，純棉材質舒適百搭，是秋冬休閒造型的全新首選。','紅#FF0000,灰#808080,白#FFFFFF','XS,S,M,L',1680,35,'2025-10-04 14:58:00'),(19,1,'女裝- ORIGINAL 連帽上衣','女裝ORIGINAL連帽上衣.jpg','連帽上衣是衣櫥必備的百搭單品！採用有機棉及環保材質，胸前加入經典海狸LOGO，休閒舒適輕鬆應對日常各種搭配。','淺紫#c5bef9,粉紫#d27497,淺綠#90926F','XS,S,M,L',1540,20,'2025-10-04 14:58:00'),(20,4,'女裝- SIGNATURE CORD 夾克','女裝SIGNATURE CORD夾克.jpg','經典單品注入現代新意！Roots Signature 燈芯絨夾克以刺繡細節搭配微寬鬆、微短版版型，展現煥然一新的時尚風格，勢必成為你衣櫃中的全新百搭首選。','胡桃木#8A4D36,黑#000000,白#FFFFFF','S,M,L,XL',5580,60,'2025-10-04 14:58:00'),(843,1,'男裝-TAIWAN DAY ANIMAL 短袖上衣','男裝TAIWAN DAY ANIMAL短袖上衣.jpg','此款短袖T恤以柔軟親膚棉料打造舒適體感，結合台灣人氣動物與撞色印花，讓你在日常中展現文化識別感。','黑#000000,白#FFFFFF,灰#808080','S,M,L,XL',1664,28,'2025-10-13 12:03:30'),(844,2,'女裝- MODERN CLASSIC 連帽外套','女裝MODERN CLASSIC連帽外套.jpg','連帽外套以直條壓印發泡印刷打造層次感LOGO，前胸LOGO與背部楓葉標章設計相呼應，細節別緻，實穿不失風格。','淡灰藍#DADEDF,粉紫#d27497,黑#000000','XS,S,M,L',3264,32,'2025-10-13 12:03:30'),(845,3,'女裝- MODERN CLASSIC SLIM 棉褲','女裝MODERN CLASSIC SLIM棉褲.jpg','棉褲採鬆緊腰頭與窄管剪裁，舒適好穿，結合品牌LOGO與楓葉標章印花，俐落有型，輕鬆打造日常穿搭質感。','深藍#00008B,淺藍#ADD8E6,黑#000000','XS,S,M,L',2704,30,'2025-10-13 12:03:30'),(846,4,'中性-GF ROOTS MODERN CLASSIC 短袖上衣','中性GF MODERN CLASSIC短袖上衣.jpg','正面天鵝絨貼花細膩吸睛，背面結合印花與刺繡設計，層次豐富展現個性，讓你穿出休閒與態度兼具的獨特風格。','粉紫#d27497,白#FFFFFF,黑#000000','S,M,L,XL',3424,31,'2025-10-13 12:03:30'),(847,1,'中性-GF MODERN CLASSIC 連帽外套','中性GF MODERN CLASSIC連帽外套.jpg','連帽外套以天鵝絨貼花結合印花與刺繡詮釋設計巧思，搭配中性剪裁與柔軟質地。','紫色#8B79B1,白#FFFFFF,深藍#00008B','S,M,L,XL',5580,26,'2025-10-13 12:03:30');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `訂單明細`
--

LOCK TABLES `訂單明細` WRITE;
/*!40000 ALTER TABLE `訂單明細` DISABLE KEYS */;
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

-- Dump completed on 2025-10-14 12:16:34
