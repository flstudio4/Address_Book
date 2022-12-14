-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: contacts
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `contact_entries`
--

DROP TABLE IF EXISTS `contact_entries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact_entries` (
  `id` int NOT NULL AUTO_INCREMENT,
  `l_name` varchar(45) DEFAULT NULL,
  `f_name` varchar(45) DEFAULT NULL,
  `work_number` varchar(45) DEFAULT NULL,
  `cellphone_number` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zip` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=533 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_entries`
--

LOCK TABLES `contact_entries` WRITE;
/*!40000 ALTER TABLE `contact_entries` DISABLE KEYS */;
INSERT INTO `contact_entries` VALUES (1,'Sumenko','Dmitrii','7734589876','7735678798','d@gmail.com','123 W Eddy Str','Chicago','IL','60612'),(2,'Woods','Anthony','7734567890','7734567876','w@gmail.com','12 S Western ave','Chicago','IL','60622'),(3,'Blackburn','Landon','7734590987','7734567654','b@gmail.com','34 E Chicago ave','Chicago','IL','60645'),(4,'Kwakye','Lillian','7734578765','7734563423','k@gmail.com','2343 W North ave','Chicago','IL','60612'),(5,'May','Andy','7734895876','7734561234','m@gmail.com','32 E Chicago Ave','Chicago','IL','60623'),(6,'Tate','Allison','7734587657','7732356576','t@gmail.com','10 Fullton ave','Chicago','IL','60613'),(7,'Wu','Jacky','7734590987','7735434567','w@gmail.com','234 W Division Str','Chicago','IL','60624'),(8,'Morrison','Julia','7734578765','7734576545','m@gmail.com','234 Wacker Drive','Chicago','IL','60634'),(9,'Dvornik','Thomas','7734568765','7734567657','dd@gmail.com','23 W 23Rd Str','New York','NY','60643'),(10,'Hlebnikova','Marina','3467876545','3454321234','mh@gmail.com','23 W chi ave','Chicago','IL','12345');
/*!40000 ALTER TABLE `contact_entries` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-14  0:40:18
