-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: local
-- ------------------------------------------------------
-- Server version	5.7.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `travels`
--

DROP TABLE IF EXISTS `travels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `travels` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) NOT NULL,
  `DriverID` int(11) NOT NULL,
  `sourceIDF` varchar(100) NOT NULL,
  `sourceAD` varchar(100) NOT NULL,
  `destinyIDF` varchar(100) NOT NULL,
  `destinyAD` varchar(100) NOT NULL,
  `value` mediumtext NOT NULL,
  `payForm` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_User` (`UserID`),
  KEY `fk_Driver` (`DriverID`),
  CONSTRAINT `fk_Driver` FOREIGN KEY (`DriverID`) REFERENCES `drivers` (`id`),
  CONSTRAINT `fk_User` FOREIGN KEY (`UserID`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travels`
--

LOCK TABLES `travels` WRITE;
/*!40000 ALTER TABLE `travels` DISABLE KEYS */;
INSERT INTO `travels` VALUES (1,10,1,'Viagem','Olinda','Viagem','Recife','50.0','cartão'),(2,10,1,'Viagem','Olinda','Viagem','Recife','50.0','dinheiro'),(3,10,1,'Viagem','Olinda','Viagem','Recife','50.0','dinheiro'),(4,10,1,'Viagem','Olinda','Viagem','Recife','50.0','dinheiro'),(5,10,1,'Viagem','Olinda','Viagem','Recife','50.0','cartão'),(6,10,7,'Viagem','Paulista','Viagem','Olinda','50.0','dinheiro'),(7,14,6,'Viagem','Paratibe','Viagem','Caruaru','50.0','cartão'),(8,14,2,'Viagem','Marco Zero','Viagem','Derby','50.0','grana');
/*!40000 ALTER TABLE `travels` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-06  0:12:38
