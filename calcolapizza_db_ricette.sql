CREATE DATABASE  IF NOT EXISTS `calcolapizza_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `calcolapizza_db`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: calcolapizza_db
-- ------------------------------------------------------
-- Server version	5.6.21

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
-- Table structure for table `ricette`
--

DROP TABLE IF EXISTS `ricette`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ricette` (
  `nomeRicetta` varchar(255) NOT NULL,
  `npizze` int(11) NOT NULL,
  `pesoPizza` int(11) DEFAULT NULL,
  `percAcqua` int(11) NOT NULL,
  `grSale` int(11) NOT NULL,
  `grOlio` int(11) DEFAULT NULL,
  `OreLievitazione` int(11) NOT NULL,
  `tAmbiente` int(11) NOT NULL,
  `Farina` double DEFAULT NULL,
  `Acqua` double NOT NULL,
  `Sale` varchar(45) NOT NULL,
  `Olio` double DEFAULT NULL,
  `Lievito` double NOT NULL,
  PRIMARY KEY (`nomeRicetta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ricette`
--

LOCK TABLES `ricette` WRITE;
/*!40000 ALTER TABLE `ricette` DISABLE KEYS */;
INSERT INTO `ricette` VALUES ('Napoletana Classica',10,260,65,20,0,8,20,1500,975,'30.0',0,3.31),('Napoletana Contemporanea',10,280,75,25,0,24,18,1500,1125,'30.0',0,1.06),('Pane Casereccio',2,1000,70,10,0,12,25,1100,770,'11.0',0,1.5),('Pizza in teglia Romana',3,800,80,20,50,24,18,1200,960,'24.0',60,1.16),('Pizza Tonda Romana',15,200,50,15,30,6,23,1900,950,'19.0',57,5.66);
/*!40000 ALTER TABLE `ricette` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-13 11:50:50
