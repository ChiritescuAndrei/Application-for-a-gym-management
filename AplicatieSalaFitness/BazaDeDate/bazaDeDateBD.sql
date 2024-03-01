-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: salafitness
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `abonamente`
--

DROP TABLE IF EXISTS `abonamente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `abonamente` (
  `AbonamentID` int NOT NULL AUTO_INCREMENT,
  `ClientID` int DEFAULT NULL,
  `Tip` varchar(10) DEFAULT NULL,
  `Pret` decimal(5,2) NOT NULL,
  `Data_incepere` date NOT NULL,
  `Data_expirare` date NOT NULL,
  `Status` enum('Activ','Inactiv') NOT NULL DEFAULT 'Inactiv',
  PRIMARY KEY (`AbonamentID`),
  KEY `ClientID` (`ClientID`),
  CONSTRAINT `abonamente_ibfk_1` FOREIGN KEY (`ClientID`) REFERENCES `clienti` (`ClientID`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abonamente`
--

LOCK TABLES `abonamente` WRITE;
/*!40000 ALTER TABLE `abonamente` DISABLE KEYS */;
INSERT INTO `abonamente` VALUES (2,8,'SILVER',238.00,'2023-01-01','2023-01-31','Activ'),(3,5,'BRONZE',180.00,'2023-01-23','2023-01-24','Activ'),(4,15,'Platinum',180.00,'2023-01-01','2023-04-30','Activ'),(5,14,'SILVER',238.00,'2022-11-01','2022-12-31','Inactiv'),(6,10,'Platinum',300.00,'2023-09-01','2023-09-30','Inactiv'),(8,37,'PLATINUM',100.00,'2023-01-01','2023-12-31','Activ'),(9,36,'GOLD',180.00,'2024-01-18','2024-02-18','Activ'),(10,36,'GOLD',300.00,'2024-01-11','2024-02-11','Activ'),(11,40,'PLATINUM',180.00,'2024-01-09','2024-02-09','Activ'),(12,43,'PLATINUM',260.00,'2024-01-17','2024-02-17','Activ'),(13,46,'BRONZE',180.00,'2024-01-16','2024-02-16','Activ'),(14,44,'BRONZE',180.00,'2024-01-15','2024-02-15','Activ'),(15,45,'BRONZE',180.00,'2024-01-12','2024-02-12','Activ'),(16,48,'BRONZE',180.00,'2024-01-03','2024-02-03','Activ'),(17,49,'BRONZE',180.00,'2024-01-15','2024-02-15','Activ'),(18,50,'BRONZE',180.00,'2024-01-23','2024-02-23','Activ'),(19,51,'BRONZE',180.00,'2024-01-16','2024-02-16','Activ'),(20,52,'GOLD',180.00,'2024-01-15','2024-02-15','Activ'),(21,53,'PLATINUM',0.00,'2024-01-12','2024-02-12','Activ'),(22,57,'GOLD',300.00,'2024-01-16','2024-02-16','Activ'),(23,55,'PLATINUM',180.00,'2024-01-17','2024-02-17','Activ'),(24,58,'BRONZE',260.00,'2024-01-15','2024-02-15','Activ'),(25,59,'GOLD',260.00,'2024-01-20','2024-02-20','Activ'),(26,39,'BRONZE',180.00,'2024-01-10','2024-02-10','Activ'),(27,60,'BRONZE',180.00,'2024-01-10','2024-02-10','Activ'),(29,61,'GOLD',180.00,'2024-01-17','2024-02-17','Activ'),(30,62,'GOLD',180.00,'2024-01-16','2024-02-16','Activ'),(31,64,'SILVER',180.00,'2024-01-16','2024-02-16','Activ'),(32,65,'GOLD',238.00,'2024-01-23','2024-02-23','Activ'),(33,67,'BRONZE',180.00,'2024-01-10','2024-02-10','Activ'),(34,68,'BRONZE',260.00,'2024-01-22','2024-02-22','Activ'),(35,70,'PLATINUM',260.00,'2024-01-22','2024-02-22','Activ'),(36,71,'GOLD',238.00,'2024-01-15','2024-02-15','Activ'),(37,72,'BRONZE',180.00,'2024-01-15','2024-02-15','Activ'),(38,73,'BRONZE',180.00,'2024-01-17','2024-02-17','Activ'),(39,74,'BRONZE',180.00,'2024-01-16','2024-02-16','Activ');
/*!40000 ALTER TABLE `abonamente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `antrenori`
--

DROP TABLE IF EXISTS `antrenori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `antrenori` (
  `AntrenorID` int NOT NULL AUTO_INCREMENT,
  `Nume` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Prenume` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Strada` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Numar` char(4) NOT NULL,
  `Oras` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Judet` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `CNP` char(13) NOT NULL,
  `Data_Angajare` date DEFAULT NULL,
  `Salariu` decimal(8,2) NOT NULL,
  `Specializare` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`AntrenorID`),
  UNIQUE KEY `CNP` (`CNP`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `antrenori`
--

LOCK TABLES `antrenori` WRITE;
/*!40000 ALTER TABLE `antrenori` DISABLE KEYS */;
INSERT INTO `antrenori` VALUES (1,'Oprea','Adriana','Arcului','42','Bucuresti','Sector 6','8301121234567',NULL,4000.00,NULL),(2,'Caramizaru','Bogdan Catalin','Geniului','52','Bucuresti','Sector 3','9510252345678','2023-03-05',3500.00,'Aerobic'),(3,'Mitu','Alexandru','Fainari','5','Bucuresti','Sector 4','8812313456789','2019-10-10',3500.00,NULL),(4,'Ioan','George','Eminescu','63','Iasi','Iasi','9002104567890','2015-09-09',6000.00,'Inot'),(5,'Muscalu','Georgiana','Splaiul Unirii','7','Bucuresti','Sector 3','7407245678901','2022-06-06',5000.00,'Cardio Fitness');
/*!40000 ALTER TABLE `antrenori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clase`
--

DROP TABLE IF EXISTS `clase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clase` (
  `ClasaID` int NOT NULL AUTO_INCREMENT,
  `AntrenorID` int DEFAULT NULL,
  `Nume` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Descriere` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Ora_incepere` time NOT NULL,
  `Ora_terminare` time NOT NULL,
  `Pret` decimal(5,2) NOT NULL,
  `Ziua_saptamana` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Locuri_disponibile` int DEFAULT NULL,
  PRIMARY KEY (`ClasaID`),
  KEY `AntrenorID` (`AntrenorID`),
  CONSTRAINT `clase_ibfk_1` FOREIGN KEY (`AntrenorID`) REFERENCES `antrenori` (`AntrenorID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clase`
--

LOCK TABLES `clase` WRITE;
/*!40000 ALTER TABLE `clase` DISABLE KEYS */;
INSERT INTO `clase` VALUES (1,3,'BodyBalance','Cuprinde exercitii de : Yoga, Pilates, Thai Chi','19:30:00','21:00:00',200.00,'Luni',25),(2,1,'GLUTES',NULL,'17:00:00','18:30:00',150.00,'Marti',30),(3,5,'Zumba','Miscare fara incetare','16:00:00','17:30:00',100.00,NULL,20),(4,5,'Inot',NULL,'14:00:00','15:30:00',100.00,'Sambata',15),(5,2,'Functional','Miscari Complexe','09:00:00','11:00:00',120.00,'Duminica',NULL),(6,4,'Inot',NULL,'17:00:00','19:00:00',150.00,'Vineri',30),(7,2,'Karate','Pentru incepatori','14:00:00','16:00:00',250.00,'Miercuri',20),(8,2,'Pilates','Miscare pentru viata','17:00:00','18:00:00',150.00,'Luni',15),(9,1,'Box',NULL,'15:00:00','16:30:00',100.00,'Marti',15),(10,2,'Aerobic',NULL,'16:00:00','18:00:00',170.00,'Joi',25),(11,5,'Cardio',NULL,'18:00:00','20:00:00',150.00,'Miercuri',35),(12,3,'Calisthenics',NULL,'10:00:00','12:00:00',140.00,'Sambata',15),(13,3,'AutoAparare',NULL,'18:00:00','20:00:00',180.00,'Joi',20),(14,5,'Yoga',NULL,'15:00:00','17:00:00',180.00,'Sambata',50);
/*!40000 ALTER TABLE `clase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `claseincaperi`
--

DROP TABLE IF EXISTS `claseincaperi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `claseincaperi` (
  `IncapereID` int NOT NULL,
  `ClasaID` int NOT NULL,
  `Data_desfasurare` date DEFAULT NULL,
  PRIMARY KEY (`IncapereID`,`ClasaID`),
  KEY `ClasaID` (`ClasaID`),
  CONSTRAINT `claseincaperi_ibfk_1` FOREIGN KEY (`IncapereID`) REFERENCES `incaperi` (`IncapereID`),
  CONSTRAINT `claseincaperi_ibfk_2` FOREIGN KEY (`ClasaID`) REFERENCES `clase` (`ClasaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `claseincaperi`
--

LOCK TABLES `claseincaperi` WRITE;
/*!40000 ALTER TABLE `claseincaperi` DISABLE KEYS */;
INSERT INTO `claseincaperi` VALUES (1,2,'2023-11-30'),(1,3,'2023-11-29'),(1,5,'2023-11-29'),(1,7,'2024-01-21'),(1,9,'2024-01-21'),(2,4,'2023-11-26'),(2,6,'2024-01-22'),(3,5,'2023-11-30'),(3,10,'2024-01-19'),(3,11,'2024-01-24'),(4,1,'2023-12-01'),(4,3,'2023-11-30'),(4,8,'2024-01-20'),(4,12,'2024-01-23'),(4,13,'2024-01-21'),(4,14,'2024-01-21');
/*!40000 ALTER TABLE `claseincaperi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clienti`
--

DROP TABLE IF EXISTS `clienti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clienti` (
  `ClientID` int NOT NULL AUTO_INCREMENT,
  `Nume` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Prenume` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Strada` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Numar` char(10) NOT NULL,
  `Oras` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Judet` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Nr_telefon` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ClientID`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clienti`
--

LOCK TABLES `clienti` WRITE;
/*!40000 ALTER TABLE `clienti` DISABLE KEYS */;
INSERT INTO `clienti` VALUES (4,'Bourceanu','Alex','Vasile Alecsandri','65','Bacau','Bacau','Mail','0731029318'),(5,'Bourceanu','Alex','Vasile Alecsandri','65','Bacau','Bacau','Alex@gmail.com','0732314'),(8,'Anghel','Marian','Milcov','3','Sibiu','Sibiu','Niculae@gmail.com','0762938472'),(9,'Bucur','Radu','Jean Steriadi','7','Bucuresti','Sector 3','Bucur@outlook.com','0732829147'),(10,'Ursinschi','Violeta','Miron Costin','92','Iasi','Iasi','Violeta@yahoo.com','0732829382'),(11,'Stoica','Vasile','Fericirii','27','Buzau','Buzau','Vasile@yahoo.com',NULL),(12,'Traian','Decebal','Ferdinand','82','Timisoara','Timisoara','Decebalus@gmail.com','0753923853'),(13,'Zarnescu','Nicoleta','Miron Costin','1','Zimnicea','Teleorman','Nico@gmail.com',NULL),(14,'Radu','Ionut','Panselutelor','2','Brasov','Brasov','Fericitul123@yahoo.com','0753920293'),(15,'Mihai','Cosmin','Fainari','5','Bucuresti','Sector 6','Doctorita@yahoo.com',NULL),(32,'Florea','Alexandru','Narciselor','33','Bacau','Bacau','florea@alex.com',NULL),(36,'alex','alex','Cornisa','33','Bacau','Bacau','alex@alex.com',NULL),(37,'Andrei','Andrei','Alecu','33','Bacau','Bacau','andrei@andrei.com','0751090713'),(39,'ion','ion','Enescu','25','Calarasi','Calarasi','ion@ion.com',NULL),(40,'calin','calin','Jmek','23','Iasi','Iasi','calin@calin.com','074283919'),(42,'razvan','razvan','Bucegi','23','Bacau','Bacau','razvan@razvan.com','0751090713'),(43,'da','da','da','33','da','da','da@da.com',NULL),(44,'a','a','a','22','a','a','a@a.com',NULL),(45,'b','b','b','22','b','b','b@b.com',NULL),(46,'c','c','c','22','c','c','c@c.com',NULL),(47,'d','d','d','22','d','d','d@d.com',NULL),(48,'e','e','e','33','ee','e','e@e.com',NULL),(49,'f','f','f','22','f','f','f@f.com',NULL),(50,'g','g','g','23','g','g','g@g.com',NULL),(51,'h','h','h','23','h','h','h@h.com',NULL),(52,'sirsky','sirsky','Panselutelor','23','b','b','sirsky@sirsky.com',NULL),(53,'da','da','da','2','da','da','x@x.com',NULL),(54,'da','da','da','2','da','da','y@y.com',NULL),(55,'da','da','da','da','da','da','z@z.com',NULL),(56,'da','da','da','22','da','da','da@da.com',NULL),(57,'da','da','da','22','da','da','nu@nu.com',NULL),(58,'bd','bd','bd','23','bd','bd','bd@bd.com',NULL),(59,'da','da','da','22','da','da','smi@smi.com',NULL),(60,'ion','ion','ion','23','ion','ion','ion@ion.com',NULL),(61,'ana','ana','ana','53','bacau','bacau','ana@ana.com',NULL),(62,'t','t','t','23','t','t','t@t.com',NULL),(63,'ad','ad','ad','23','ad','ad','ad@ad.com',NULL),(64,'k','k','k','23','k','k','k@k.com',NULL),(65,'U','U','U','54','u','u','u@u.com',NULL),(66,'maria','maria','maria','23','maria','maria','maria@maria.com',NULL),(67,'r','r','r','23','r','r','p@p.com',NULL),(68,'i','i','i','2','i','i','i@i.com',NULL),(69,'k','k','k','23','k','k','k@k.com',NULL),(70,'diana','diana','diana','23','diana','diana','diana@diana.com',NULL),(71,'a','a','a','23','a','a','bolnav@bolnav.com',NULL),(72,'!','!','!','23','!','!','!@!.com',NULL),(73,'efi','efi','efi','23','efi','efi','efi@efi.com',NULL),(74,'trump','trump','trump','23','trump','trump','trump@trump.com',NULL);
/*!40000 ALTER TABLE `clienti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incaperi`
--

DROP TABLE IF EXISTS `incaperi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incaperi` (
  `IncapereID` int NOT NULL AUTO_INCREMENT,
  `Suprafata` decimal(4,2) NOT NULL,
  `Tip` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Stare` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`IncapereID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incaperi`
--

LOCK TABLES `incaperi` WRITE;
/*!40000 ALTER TABLE `incaperi` DISABLE KEYS */;
INSERT INTO `incaperi` VALUES (1,30.00,'Forta','Renovare'),(2,40.00,'Inot','Functionala'),(3,60.00,'Cardio',NULL),(4,45.00,'Gimnastica','Functionala'),(5,40.00,'Receptie','Functionala');
/*!40000 ALTER TABLE `incaperi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parolecont`
--

DROP TABLE IF EXISTS `parolecont`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parolecont` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ClientID` int DEFAULT NULL,
  `parola` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ClientID` (`ClientID`),
  CONSTRAINT `parolecont_ibfk_1` FOREIGN KEY (`ClientID`) REFERENCES `clienti` (`ClientID`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parolecont`
--

LOCK TABLES `parolecont` WRITE;
/*!40000 ALTER TABLE `parolecont` DISABLE KEYS */;
INSERT INTO `parolecont` VALUES (12,32,'123'),(16,36,'da'),(17,37,'da'),(19,39,'da'),(20,40,'da'),(22,42,'da'),(23,43,'da'),(24,44,'da'),(25,45,'da'),(26,46,'da'),(27,47,'da'),(28,48,'da'),(29,49,'da'),(30,50,'da'),(31,51,'da'),(32,52,'da'),(33,53,'da'),(34,54,'da'),(35,55,'da'),(36,56,'da'),(37,57,'da'),(38,58,'nu'),(39,59,'da'),(40,60,'da'),(41,61,'qwe'),(42,62,'da'),(43,63,'da'),(44,64,'x'),(45,65,'y'),(46,66,'maria'),(47,67,'p'),(48,68,'j'),(49,69,'da'),(50,70,'yes'),(51,71,'y'),(52,72,'!'),(53,73,'efi'),(54,74,'trump');
/*!40000 ALTER TABLE `parolecont` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rezervari`
--

DROP TABLE IF EXISTS `rezervari`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rezervari` (
  `RezervareID` int NOT NULL AUTO_INCREMENT,
  `ClientID` int DEFAULT NULL,
  `ClasaID` int DEFAULT NULL,
  `Status` enum('activ','inactiv') NOT NULL DEFAULT 'inactiv',
  `Data_rezervare` datetime NOT NULL,
  PRIMARY KEY (`RezervareID`),
  KEY `ClasaID` (`ClasaID`),
  KEY `ClientID` (`ClientID`),
  CONSTRAINT `rezervari_ibfk_2` FOREIGN KEY (`ClasaID`) REFERENCES `clase` (`ClasaID`),
  CONSTRAINT `rezervari_ibfk_3` FOREIGN KEY (`ClientID`) REFERENCES `clienti` (`ClientID`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rezervari`
--

LOCK TABLES `rezervari` WRITE;
/*!40000 ALTER TABLE `rezervari` DISABLE KEYS */;
INSERT INTO `rezervari` VALUES (1,4,2,'activ','2023-11-21 00:00:00'),(2,4,3,'activ','2023-11-21 00:00:00'),(3,4,4,'activ','2023-11-21 00:00:00'),(4,12,4,'activ','2023-11-21 00:00:00'),(5,10,4,'inactiv','2023-11-15 00:00:00'),(6,9,5,'inactiv','2022-09-05 00:00:00'),(7,8,3,'activ','2023-12-11 00:00:00'),(8,4,2,'inactiv','2023-12-15 00:00:00'),(9,4,4,'activ','2023-11-25 00:00:00'),(10,4,4,'activ','2023-11-25 00:00:00'),(11,55,8,'activ','2024-01-12 00:00:00'),(12,55,7,'activ','2024-01-12 00:00:00'),(13,39,9,'activ','2024-01-12 00:00:00'),(14,61,9,'activ','2024-01-12 00:00:00'),(15,61,7,'activ','2024-01-12 00:00:00'),(16,61,7,'activ','2024-01-12 00:00:00'),(17,61,4,'activ','2024-01-12 00:00:00'),(18,61,9,'activ','2024-01-12 00:00:00'),(19,62,7,'activ','2024-01-12 00:00:00'),(20,62,9,'activ','2024-01-12 00:00:00'),(21,55,9,'activ','2024-01-12 00:00:00'),(22,55,8,'activ','2024-01-12 00:00:00'),(23,63,8,'activ','2024-01-12 00:00:00'),(24,63,9,'activ','2024-01-12 00:00:00'),(25,64,9,'activ','2024-01-12 00:00:00'),(26,64,7,'activ','2024-01-12 00:00:00'),(27,65,9,'activ','2024-01-12 00:00:00'),(28,65,7,'activ','2024-01-12 00:00:00'),(29,66,4,'activ','2024-01-12 00:00:00'),(30,67,9,'activ','2024-01-12 00:00:00'),(31,67,7,'activ','2024-01-12 00:00:00'),(32,68,9,'activ','2024-01-12 00:00:00'),(33,68,7,'activ','2024-01-12 00:00:00'),(34,68,4,'activ','2024-01-12 00:00:00'),(35,70,9,'activ','2024-01-13 00:00:00'),(36,70,7,'activ','2024-01-13 00:00:00'),(37,55,9,'activ','2024-01-13 00:00:00'),(38,55,7,'activ','2024-01-13 00:00:00'),(39,55,4,'activ','2024-01-13 00:00:00'),(40,71,8,'activ','2024-01-13 00:00:00'),(41,71,9,'activ','2024-01-13 00:00:00'),(42,71,13,'activ','2024-01-13 00:00:00'),(43,71,11,'activ','2024-01-13 00:00:00'),(44,71,11,'activ','2024-01-13 00:00:00'),(45,55,7,'activ','2024-01-13 00:00:00'),(46,55,13,'activ','2024-01-13 15:35:47'),(47,55,7,'activ','2024-01-13 15:49:28'),(48,55,8,'activ','2024-01-13 15:50:13'),(49,55,7,'activ','2024-01-13 15:50:30'),(50,55,4,'activ','2024-01-13 15:51:09'),(51,55,14,'activ','2024-01-13 15:52:33'),(52,55,7,'activ','2024-01-13 15:53:30'),(53,55,4,'activ','2024-01-13 15:53:57'),(54,55,8,'activ','2024-01-13 15:54:11'),(55,55,7,'activ','2024-01-13 15:55:42'),(56,55,14,'activ','2024-01-13 15:55:51'),(57,55,4,'activ','2024-01-13 15:56:51'),(58,55,13,'activ','2024-01-13 16:00:04'),(59,55,14,'activ','2024-01-13 16:00:16'),(60,55,14,'activ','2024-01-13 16:07:42'),(61,55,12,'activ','2024-01-13 16:08:36'),(62,72,9,'activ','2024-01-13 16:15:00'),(63,73,7,'activ','2024-01-13 16:16:38'),(64,73,14,'activ','2024-01-13 16:17:03'),(65,73,4,'activ','2024-01-13 16:18:14'),(66,55,14,'activ','2024-01-13 16:21:59'),(67,55,9,'activ','2024-01-13 16:29:26'),(68,55,9,'activ','2024-01-13 16:30:39'),(69,55,4,'activ','2024-01-13 16:31:00'),(70,55,14,'activ','2024-01-13 16:31:11'),(71,55,8,'activ','2024-01-13 16:40:28'),(72,55,4,'activ','2024-01-13 16:42:30'),(73,55,7,'activ','2024-01-13 16:42:51'),(74,55,13,'activ','2024-01-13 16:43:08'),(75,55,13,'activ','2024-01-13 16:43:24'),(76,55,14,'activ','2024-01-13 16:43:31'),(77,55,12,'activ','2024-01-13 16:55:09'),(78,74,7,'activ','2024-01-13 16:57:17'),(79,74,13,'activ','2024-01-13 16:57:31'),(80,74,4,'activ','2024-01-13 17:05:09'),(81,55,4,'activ','2024-01-13 17:43:08');
/*!40000 ALTER TABLE `rezervari` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-13 18:38:02
