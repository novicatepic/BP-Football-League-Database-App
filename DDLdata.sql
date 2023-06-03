CREATE DATABASE  IF NOT EXISTS `fudbalskaliga` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fudbalskaliga`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: fudbalskaliga
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `dogadjaj`
--

DROP TABLE IF EXISTS `dogadjaj`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dogadjaj` (
  `DogadjajId` int NOT NULL AUTO_INCREMENT,
  `POMOCNI_SUDIJA_SUDIJA_SudijaId` int NOT NULL,
  `UTAKMICA_UtakmicaId` int NOT NULL,
  `OpisDogadjaja` varchar(1000) NOT NULL,
  PRIMARY KEY (`DogadjajId`),
  KEY `fk_POMOCNI_SUDIJA_has_UTAKMICA_IZMEDJU_TIMOVA_POMOCNI_SUDIJ_idx` (`POMOCNI_SUDIJA_SUDIJA_SudijaId`),
  KEY `fk_DOGADJAJ_UTAKMICA1_idx` (`UTAKMICA_UtakmicaId`),
  CONSTRAINT `fk_DOGADJAJ_UTAKMICA1` FOREIGN KEY (`UTAKMICA_UtakmicaId`) REFERENCES `utakmica` (`UtakmicaId`),
  CONSTRAINT `fk_POMOCNI_SUDIJA_has_UTAKMICA_IZMEDJU_TIMOVA_POMOCNI_SUDIJA1` FOREIGN KEY (`POMOCNI_SUDIJA_SUDIJA_SudijaId`) REFERENCES `pomocni_sudija` (`SUDIJA_SudijaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dogadjaj`
--

LOCK TABLES `dogadjaj` WRITE;
/*!40000 ALTER TABLE `dogadjaj` DISABLE KEYS */;
/*!40000 ALTER TABLE `dogadjaj` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dogadjaj_igraca`
--

DROP TABLE IF EXISTS `dogadjaj_igraca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dogadjaj_igraca` (
  `DogadjajId` int NOT NULL AUTO_INCREMENT,
  `IGRAC_NA_UTAKMICI_IGRAC_ZAPOSLENIK_ZapolseniId` int NOT NULL,
  `OpisDogadjaja` varchar(1000) NOT NULL,
  PRIMARY KEY (`DogadjajId`),
  KEY `fk_KLUB_NA_UTAKMICI_has_IGRAC_NA_UTAKMICI_IGRAC_NA_UTAKMICI_idx` (`IGRAC_NA_UTAKMICI_IGRAC_ZAPOSLENIK_ZapolseniId`),
  CONSTRAINT `fk_KLUB_NA_UTAKMICI_has_IGRAC_NA_UTAKMICI_IGRAC_NA_UTAKMICI1` FOREIGN KEY (`IGRAC_NA_UTAKMICI_IGRAC_ZAPOSLENIK_ZapolseniId`) REFERENCES `igrac_na_utakmici` (`IGRAC_ZAPOSLENIK_ZapolseniId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dogadjaj_igraca`
--

LOCK TABLES `dogadjaj_igraca` WRITE;
/*!40000 ALTER TABLE `dogadjaj_igraca` DISABLE KEYS */;
/*!40000 ALTER TABLE `dogadjaj_igraca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fudbalska_liga`
--

DROP TABLE IF EXISTS `fudbalska_liga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fudbalska_liga` (
  `Naziv` varchar(100) NOT NULL,
  `DatumOsnivanja` date NOT NULL,
  PRIMARY KEY (`Naziv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fudbalska_liga`
--

LOCK TABLES `fudbalska_liga` WRITE;
/*!40000 ALTER TABLE `fudbalska_liga` DISABLE KEYS */;
/*!40000 ALTER TABLE `fudbalska_liga` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fudbalski_klub`
--

DROP TABLE IF EXISTS `fudbalski_klub`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fudbalski_klub` (
  `IdKluba` int NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(100) NOT NULL,
  `DatumOsnivanja` date NOT NULL,
  `BrojOsvojenihTrofeja` int NOT NULL,
  `STADION_StadioId` int NOT NULL,
  PRIMARY KEY (`IdKluba`),
  KEY `fk_FUDBALSKI_KLUB_STADION1_idx` (`STADION_StadioId`),
  CONSTRAINT `fk_FUDBALSKI_KLUB_STADION1` FOREIGN KEY (`STADION_StadioId`) REFERENCES `stadion` (`StadionId`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fudbalski_klub`
--

LOCK TABLES `fudbalski_klub` WRITE;
/*!40000 ALTER TABLE `fudbalski_klub` DISABLE KEYS */;
INSERT INTO `fudbalski_klub` VALUES (1,'Manchester United','1945-10-10',24,2),(2,'Man City','2010-09-21',13,1),(5,'Arsenal','1986-09-23',13,6),(6,'Aston Villa','1909-05-05',7,16),(7,'Bournemouth','1909-05-05',0,17),(8,'Brighton','1909-05-05',0,19),(9,'Chelsea','1909-05-05',6,7),(10,'Crystal Palace','1909-05-05',0,13),(11,'Everton','1909-05-05',9,11),(12,'Leicester','1909-05-05',1,15),(13,'Liverpool','1909-05-05',19,9),(14,'Newcastle','1909-05-05',4,12),(15,'Southampton','1909-05-05',0,14),(16,'Tottenham','1909-05-05',0,8),(17,'West Ham','1909-05-05',0,10),(18,'Wolves','1909-05-05',11,18),(19,'Brentford','1986-09-23',0,20),(20,'Fulham','1909-05-05',0,21),(21,'Nottingham Forest','1909-05-05',1,22),(22,'Leeds','1909-05-06',1,23);
/*!40000 ALTER TABLE `fudbalski_klub` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `glavni_sudija`
--

DROP TABLE IF EXISTS `glavni_sudija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `glavni_sudija` (
  `SUDIJA_SudijaId` int NOT NULL,
  PRIMARY KEY (`SUDIJA_SudijaId`),
  CONSTRAINT `fk_GLAVNI_SUDIJA_SUDIJA1` FOREIGN KEY (`SUDIJA_SudijaId`) REFERENCES `sudija` (`SudijaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `glavni_sudija`
--

LOCK TABLES `glavni_sudija` WRITE;
/*!40000 ALTER TABLE `glavni_sudija` DISABLE KEYS */;
INSERT INTO `glavni_sudija` VALUES (1),(2),(3);
/*!40000 ALTER TABLE `glavni_sudija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `igrac`
--

DROP TABLE IF EXISTS `igrac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `igrac` (
  `ZAPOSLENIK_ZapolseniId` int NOT NULL,
  `BrojOpreme` int NOT NULL,
  `BrojGolova` int NOT NULL,
  `BrojAsistencija` int NOT NULL,
  `BrojZutihKartona` int NOT NULL,
  `BrojCrvenihKartona` int NOT NULL,
  PRIMARY KEY (`ZAPOSLENIK_ZapolseniId`),
  CONSTRAINT `fk_IGRAC_ZAPOSLENIK1` FOREIGN KEY (`ZAPOSLENIK_ZapolseniId`) REFERENCES `zaposlenik` (`ZapolseniId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `igrac`
--

LOCK TABLES `igrac` WRITE;
/*!40000 ALTER TABLE `igrac` DISABLE KEYS */;
INSERT INTO `igrac` VALUES (1,10,2,1,1,0),(2,23,0,0,1,0),(3,9,0,0,0,0),(4,17,0,0,0,0),(5,9,1,0,2,0),(6,20,0,0,0,0),(7,11,0,0,0,0),(8,19,0,0,0,0),(9,9,0,0,0,0),(10,11,0,0,0,0),(11,12,0,0,0,0),(12,13,0,0,0,0),(13,14,0,0,0,0);
/*!40000 ALTER TABLE `igrac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `igrac_na_utakmici`
--

DROP TABLE IF EXISTS `igrac_na_utakmici`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `igrac_na_utakmici` (
  `IGRAC_ZAPOSLENIK_ZapolseniId` int NOT NULL,
  `BrojGolovaNaUtakmici` int NOT NULL,
  `BrojAsistencijaNaUtakmici` int NOT NULL,
  `DobioZuti` tinyint NOT NULL,
  `DobioCrveni` tinyint NOT NULL,
  `KLUB_NA_UTAKMICI_FUDBALSKI_KLUB_IdKluba` int NOT NULL,
  `KLUB_NA_UTAKMICI_UTAKMICA_UtakmicaId` int NOT NULL,
  `PoceoUtakmicu` tinyint NOT NULL,
  `OdigraoMinuta` int NOT NULL,
  PRIMARY KEY (`IGRAC_ZAPOSLENIK_ZapolseniId`,`KLUB_NA_UTAKMICI_FUDBALSKI_KLUB_IdKluba`,`KLUB_NA_UTAKMICI_UTAKMICA_UtakmicaId`),
  KEY `fk_UTAKMICA_IZMEDJU_TIMOVA_has_IGRAC_IGRAC1_idx` (`IGRAC_ZAPOSLENIK_ZapolseniId`),
  KEY `fk_IGRAC_NA_UTAKMICI_KLUB_NA_UTAKMICI1_idx` (`KLUB_NA_UTAKMICI_FUDBALSKI_KLUB_IdKluba`,`KLUB_NA_UTAKMICI_UTAKMICA_UtakmicaId`),
  CONSTRAINT `fk_IGRAC_NA_UTAKMICI_KLUB_NA_UTAKMICI1` FOREIGN KEY (`KLUB_NA_UTAKMICI_FUDBALSKI_KLUB_IdKluba`, `KLUB_NA_UTAKMICI_UTAKMICA_UtakmicaId`) REFERENCES `klub_na_utakmici` (`FUDBALSKI_KLUB_IdKluba`, `UTAKMICA_UtakmicaId`),
  CONSTRAINT `fk_UTAKMICA_IZMEDJU_TIMOVA_has_IGRAC_IGRAC1` FOREIGN KEY (`IGRAC_ZAPOSLENIK_ZapolseniId`) REFERENCES `igrac` (`ZAPOSLENIK_ZapolseniId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `igrac_na_utakmici`
--

LOCK TABLES `igrac_na_utakmici` WRITE;
/*!40000 ALTER TABLE `igrac_na_utakmici` DISABLE KEYS */;
INSERT INTO `igrac_na_utakmici` VALUES (1,1,0,0,0,1,69,1,89),(2,0,0,1,0,1,52,1,89),(5,1,0,1,0,1,52,1,56),(5,0,0,1,0,1,69,0,54);
/*!40000 ALTER TABLE `igrac_na_utakmici` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `check_stats_before_insert` BEFORE INSERT ON `igrac_na_utakmici` FOR EACH ROW begin
	declare vBrojPostignutihGolova int default 0;
    declare vBrojPrimljenihGolova int default 0;
    declare vBrojPostignutihGolovaDoSad int default 0;
    declare vBrojAsistencijaDoSad int default 0;
	if new.OdigraoMinuta<0 or new.OdigraoMinuta>90 then
		signal sqlstate '45000' set message_text='Nevalidan unos minuta!';
    end if;
    
    select knu.BrojPostignutihGolova into vBrojPostignutihGolova
    from klub_na_utakmici knu
    where new.KLUB_NA_UTAKMICI_FUDBALSKI_KLUB_IdKluba=knu.FUDBALSKI_KLUB_IdKluba 
    and new.KLUB_NA_UTAKMICI_UTAKMICA_UtakmicaId=knu.UTAKMICA_UtakmicaId;

    select knu.BrojPrimljenihGolova into vBrojPrimljenihGolova
    from klub_na_utakmici knu
    where new.KLUB_NA_UTAKMICI_FUDBALSKI_KLUB_IdKluba=knu.FUDBALSKI_KLUB_IdKluba 
    and new.KLUB_NA_UTAKMICI_UTAKMICA_UtakmicaId=knu.UTAKMICA_UtakmicaId;
	
    select ifnull(sum(inu.BrojGolovaNaUtakmici), 0) into vBrojPostignutihGolovaDoSad
    from igrac_na_utakmici inu 
    where inu.KLUB_NA_UTAKMICI_UTAKMICA_UtakmicaId=new.KLUB_NA_UTAKMICI_UTAKMICA_UtakmicaId
    and new.KLUB_NA_UTAKMICI_FUDBALSKI_KLUB_IdKluba=inu.KLUB_NA_UTAKMICI_FUDBALSKI_KLUB_IdKluba;
    
    select ifnull(sum(inu.BrojAsistencijaNaUtakmici), 0) into vBrojAsistencijaDoSad
    from igrac_na_utakmici inu 
    where inu.KLUB_NA_UTAKMICI_UTAKMICA_UtakmicaId=new.KLUB_NA_UTAKMICI_UTAKMICA_UtakmicaId
    and new.KLUB_NA_UTAKMICI_FUDBALSKI_KLUB_IdKluba=inu.KLUB_NA_UTAKMICI_FUDBALSKI_KLUB_IdKluba;
    
    if vBrojPostignutihGolovaDoSad+vBrojAsistencijaDoSad+new.BrojGolovaNaUtakmici+new.BrojAsistencijaNaUtakmici>vBrojPostignutihGolova then
		signal sqlstate '45000' set message_text='Previse golova!';
    end if;
    /*if vBrojPostignutihGolovaDoSad+vBrojAsistencijaDoSad+new.BrojGolovaNaUtakmici+new.BrojAsistencijaNaUtakmici>vBrojPostignutihGolova then
		signal sqlstate '45000' set message_text='Previse golova!';
    end if;*/
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `update_player_stats` AFTER INSERT ON `igrac_na_utakmici` FOR EACH ROW begin
	update igrac i
    set i.BrojGolova=i.BrojGolova+new.BrojGolovaNaUtakmici,
    i.BrojAsistencija=i.BrojAsistencija+new.BrojAsistencijaNaUtakmici,
    i.BrojZutihKartona=i.BrojZutihKartona+new.DobioZuti,
    i.BrojCrvenihKartona=i.BrojCrvenihKartona+new.DobioCrveni
    where i.ZAPOSLENIK_ZapolseniId=new.IGRAC_ZAPOSLENIK_ZapolseniId;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `delete_player_stats` AFTER DELETE ON `igrac_na_utakmici` FOR EACH ROW begin
	update igrac i
    set i.BrojGolova=i.BrojGolova-old.BrojGolovaNaUtakmici,
    i.BrojAsistencija=i.BrojAsistencija-old.BrojAsistencijaNaUtakmici,
    i.BrojZutihKartona=i.BrojZutihKartona-old.DobioZuti,
    i.BrojCrvenihKartona=i.BrojCrvenihKartona-old.DobioCrveni
    where i.ZAPOSLENIK_ZapolseniId=old.IGRAC_ZAPOSLENIK_ZapolseniId;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `klub_na_utakmici`
--

DROP TABLE IF EXISTS `klub_na_utakmici`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `klub_na_utakmici` (
  `FUDBALSKI_KLUB_IdKluba` int NOT NULL,
  `UTAKMICA_UtakmicaId` int NOT NULL,
  `BrojPostignutihGolova` int NOT NULL,
  `IsDomacin` tinyint NOT NULL,
  `BrojPrimljenihGolova` int NOT NULL,
  PRIMARY KEY (`FUDBALSKI_KLUB_IdKluba`,`UTAKMICA_UtakmicaId`),
  KEY `fk_FUDBALSKI_KLUB_has_UTAKMICA_UTAKMICA1_idx` (`UTAKMICA_UtakmicaId`),
  KEY `fk_FUDBALSKI_KLUB_has_UTAKMICA_FUDBALSKI_KLUB1_idx` (`FUDBALSKI_KLUB_IdKluba`),
  CONSTRAINT `fk_FUDBALSKI_KLUB_has_UTAKMICA_FUDBALSKI_KLUB1` FOREIGN KEY (`FUDBALSKI_KLUB_IdKluba`) REFERENCES `fudbalski_klub` (`IdKluba`),
  CONSTRAINT `fk_FUDBALSKI_KLUB_has_UTAKMICA_UTAKMICA1` FOREIGN KEY (`UTAKMICA_UtakmicaId`) REFERENCES `utakmica` (`UtakmicaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `klub_na_utakmici`
--

LOCK TABLES `klub_na_utakmici` WRITE;
/*!40000 ALTER TABLE `klub_na_utakmici` DISABLE KEYS */;
INSERT INTO `klub_na_utakmici` VALUES (1,52,1,1,2),(1,69,2,1,1),(1,81,3,1,3),(2,52,2,0,1),(2,70,2,1,3),(5,55,1,1,1),(5,71,2,1,2),(6,55,1,0,1),(6,76,1,1,1),(7,56,1,1,1),(7,74,1,0,1),(8,56,1,0,1),(8,77,2,1,1),(9,57,2,1,0),(9,69,1,0,2),(10,57,0,0,2),(10,70,3,0,2),(10,81,3,0,3),(11,58,1,1,2),(11,78,0,1,0),(12,59,2,1,2),(12,74,1,1,1),(13,60,3,1,1),(13,71,2,0,2),(14,60,1,0,3),(14,75,1,0,1),(15,59,2,0,2),(15,78,0,0,0),(16,58,2,0,1),(16,73,1,0,1),(17,61,1,1,2),(17,76,1,0,1),(18,61,2,0,1),(18,75,1,1,1),(19,62,1,1,1),(19,72,1,1,1),(20,62,1,0,1),(20,73,1,1,1),(21,72,1,0,1),(21,85,2,0,1),(22,77,1,0,2),(22,85,1,1,2);
/*!40000 ALTER TABLE `klub_na_utakmici` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `did_team_play` BEFORE INSERT ON `klub_na_utakmici` FOR EACH ROW begin
	call find_fixture_based_on_game(new.UTAKMICA_UtakmicaId, @fixture);
    call check_club_in_game(@fixture, new.FUDBALSKI_KLUB_IdKluba, @pstatus);
    if @pstatus=1 then
		signal sqlstate '45000' set message_text='Vec postoji unos!';
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `update_team_stats` AFTER INSERT ON `klub_na_utakmici` FOR EACH ROW begin
	declare points int default 0;
    if new.BrojPostignutihGolova>new.BrojPrimljenihGolova then
		set points=3;
    elseif new.BrojPostignutihGolova=new.BrojPrimljenihGolova then
		set points=1;
    end if;
	update statistika_u_sezoni sz
    set sz.BrojPostignutihGolova=sz.BrojPostignutihGolova+new.BrojPostignutihGolova,
    sz.BrojPrimljenihGolova=sz.BrojPrimljenihGolova+new.BrojPrimljenihGolova,
    sz.BrojPobjeda=sz.BrojPobjeda+(new.BrojPostignutihGolova>new.BrojPrimljenihGolova),
    sz.BrojNerijesenih=sz.BrojNerijesenih+(new.BrojPostignutihGolova=new.BrojPrimljenihGolova),
    sz.BrojPoraza=sz.BrojPoraza+(new.BrojPostignutihGolova<new.BrojPrimljenihGolova),
    sz.BrojOdigranihUtakmica=sz.BrojOdigranihUtakmica+1,
    sz.BrojBodova=sz.BrojBodova+points
    where sz.FUDBALSKI_KLUB_IdKluba=new.FUDBALSKI_KLUB_IdKluba;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `change_team_stats` AFTER UPDATE ON `klub_na_utakmici` FOR EACH ROW begin
	update statistika_u_sezoni sz
    set sz.BrojPostignutihGolova=sz.BrojPostignutihGolova-old.BrojPostignutihGolova+new.BrojPostignutihGolova,
    sz.BrojPrimljenihGolova=sz.BrojPrimljenihGolova-old.BrojPrimljenihGolova+new.BrojPrimljenihGolova
    where sz.FUDBALSKI_KLUB_IdKluba=old.FUDBALSKI_KLUB_IdKluba;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `delete_team_stats` AFTER DELETE ON `klub_na_utakmici` FOR EACH ROW begin
	declare points int default -3;
    if old.BrojPostignutihGolova>old.BrojPrimljenihGolova then
		set points=3;
    elseif old.BrojPostignutihGolova=old.BrojPrimljenihGolova then
		set points=1;
    end if;
	update statistika_u_sezoni sz
    set sz.BrojPostignutihGolova=sz.BrojPostignutihGolova-old.BrojPostignutihGolova,
    sz.BrojPrimljenihGolova=sz.BrojPrimljenihGolova-old.BrojPrimljenihGolova,
    sz.BrojPobjeda=sz.BrojPobjeda-(old.BrojPostignutihGolova>old.BrojPrimljenihGolova),
    sz.BrojNerijesenih=sz.BrojNerijesenih-(old.BrojPostignutihGolova=old.BrojPrimljenihGolova),
    sz.BrojPoraza=sz.BrojPoraza-(old.BrojPostignutihGolova<old.BrojPrimljenihGolova),
    sz.BrojOdigranihUtakmica=sz.BrojOdigranihUtakmica-1,
    sz.BrojBodova=sz.BrojBodova-points
    where sz.FUDBALSKI_KLUB_IdKluba=old.FUDBALSKI_KLUB_IdKluba;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `kolo`
--

DROP TABLE IF EXISTS `kolo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kolo` (
  `BrojKola` int NOT NULL,
  PRIMARY KEY (`BrojKola`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kolo`
--

LOCK TABLES `kolo` WRITE;
/*!40000 ALTER TABLE `kolo` DISABLE KEYS */;
INSERT INTO `kolo` VALUES (1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11),(12),(13),(14),(15),(16),(17),(18),(19),(20),(21),(22),(23),(24),(25),(26),(27),(28),(29),(30),(31),(32),(33),(34),(35),(36),(37),(38);
/*!40000 ALTER TABLE `kolo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `linijski_sudija`
--

DROP TABLE IF EXISTS `linijski_sudija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `linijski_sudija` (
  `SUDIJA_SudijaId` int NOT NULL,
  PRIMARY KEY (`SUDIJA_SudijaId`),
  CONSTRAINT `fk_LINIJSKI_SUDIJA_SUDIJA1` FOREIGN KEY (`SUDIJA_SudijaId`) REFERENCES `sudija` (`SudijaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `linijski_sudija`
--

LOCK TABLES `linijski_sudija` WRITE;
/*!40000 ALTER TABLE `linijski_sudija` DISABLE KEYS */;
/*!40000 ALTER TABLE `linijski_sudija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osvajac`
--

DROP TABLE IF EXISTS `osvajac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `osvajac` (
  `Sezona` varchar(8) NOT NULL,
  `NazivKluba` varchar(100) NOT NULL,
  PRIMARY KEY (`Sezona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osvajac`
--

LOCK TABLES `osvajac` WRITE;
/*!40000 ALTER TABLE `osvajac` DISABLE KEYS */;
/*!40000 ALTER TABLE `osvajac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pomocni_sudija`
--

DROP TABLE IF EXISTS `pomocni_sudija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pomocni_sudija` (
  `SUDIJA_SudijaId` int NOT NULL,
  PRIMARY KEY (`SUDIJA_SudijaId`),
  CONSTRAINT `fk_POMOCNI_SUDIJA_SUDIJA1` FOREIGN KEY (`SUDIJA_SudijaId`) REFERENCES `sudija` (`SudijaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pomocni_sudija`
--

LOCK TABLES `pomocni_sudija` WRITE;
/*!40000 ALTER TABLE `pomocni_sudija` DISABLE KEYS */;
/*!40000 ALTER TABLE `pomocni_sudija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stadion`
--

DROP TABLE IF EXISTS `stadion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stadion` (
  `StadionId` int NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(100) NOT NULL,
  `Kapacitet` int NOT NULL,
  `Grad` varchar(100) NOT NULL,
  PRIMARY KEY (`StadionId`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stadion`
--

LOCK TABLES `stadion` WRITE;
/*!40000 ALTER TABLE `stadion` DISABLE KEYS */;
INSERT INTO `stadion` VALUES (1,'Etihad',23000,'Manchester'),(2,'Old Trafford',45000,'Manchester'),(6,'Emirates Stadium',60704,'London'),(7,'Stamford Bridge',40341,'London'),(8,'White Hart Lane',62850,'London'),(9,'Anfield',54000,'Liverpool'),(10,'London Stadium',90000,'London'),(11,'Goodison Park',39572,'Liverpool'),(12,'St. James\' Park',52000,'Newcastle'),(13,'Selhurst Park',25486,'London'),(14,'St Mary\'s Stadium',32384,'Southampton'),(15,'King Power Stadium',32261,'Leicester'),(16,'Villa Park',42640,'Birmingham'),(17,'Vitality Stadium',11379,'Kings Park'),(18,'Molineux Stadium',32050,'Wolverhampton'),(19,'Falmer Stadium',31800,'Falmer'),(20,'Gtech Stadium',17250,'Brentford'),(21,'Craven Cottage',25700,'Fulham'),(22,'City Ground',62850,'West Bridgford'),(23,'Elland Road',37895,'Leeds');
/*!40000 ALTER TABLE `stadion` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `check_stadium_capacity` BEFORE INSERT ON `stadion` FOR EACH ROW begin
	if new.Kapacitet<0 then
		signal sqlstate '45000' set message_text='Nevalidan kapacitet stadiona!';
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `statistika_u_sezoni`
--

DROP TABLE IF EXISTS `statistika_u_sezoni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `statistika_u_sezoni` (
  `BrojOdigranihUtakmica` int NOT NULL,
  `BrojPobjeda` int NOT NULL,
  `BrojNerijesenih` int NOT NULL,
  `BrojPoraza` int NOT NULL,
  `BrojPostignutihGolova` int NOT NULL,
  `BrojPrimljenihGolova` int NOT NULL,
  `FUDBALSKI_KLUB_IdKluba` int NOT NULL,
  `BrojBodova` int NOT NULL,
  PRIMARY KEY (`FUDBALSKI_KLUB_IdKluba`),
  CONSTRAINT `fk_STATISTIKA_U_SEZONI_FUDBALSKI_KLUB1` FOREIGN KEY (`FUDBALSKI_KLUB_IdKluba`) REFERENCES `fudbalski_klub` (`IdKluba`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statistika_u_sezoni`
--

LOCK TABLES `statistika_u_sezoni` WRITE;
/*!40000 ALTER TABLE `statistika_u_sezoni` DISABLE KEYS */;
INSERT INTO `statistika_u_sezoni` VALUES (14,1,11,2,51,74,1,21),(0,0,0,0,0,0,2,0),(0,0,0,0,0,0,5,0),(0,0,0,0,0,0,6,0),(0,0,0,0,0,0,7,6),(0,0,0,0,0,0,8,0),(0,0,0,0,0,0,9,0),(1,0,1,0,3,3,10,1),(0,0,0,0,0,0,11,0),(0,0,0,0,0,0,12,0),(0,0,0,0,0,0,13,0),(0,0,0,0,0,0,14,0),(0,0,0,0,0,0,15,0),(0,0,0,0,0,0,16,0),(0,0,0,0,0,0,17,0),(0,0,0,0,0,0,18,3),(0,0,0,0,0,0,19,0),(0,0,0,0,0,0,20,0),(0,1,0,-1,1,-1,21,6),(0,-1,0,1,-1,1,22,-3);
/*!40000 ALTER TABLE `statistika_u_sezoni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sudi_sa_strane`
--

DROP TABLE IF EXISTS `sudi_sa_strane`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sudi_sa_strane` (
  `UTAKMICA_UtakmicaId` int NOT NULL,
  `LINIJSKI_SUDIJA_SUDIJA_SudijaId` int NOT NULL,
  PRIMARY KEY (`UTAKMICA_UtakmicaId`,`LINIJSKI_SUDIJA_SUDIJA_SudijaId`),
  KEY `fk_UTAKMICA_has_LINIJSKI_SUDIJA_LINIJSKI_SUDIJA1_idx` (`LINIJSKI_SUDIJA_SUDIJA_SudijaId`),
  KEY `fk_UTAKMICA_has_LINIJSKI_SUDIJA_UTAKMICA1_idx` (`UTAKMICA_UtakmicaId`),
  CONSTRAINT `fk_UTAKMICA_has_LINIJSKI_SUDIJA_LINIJSKI_SUDIJA1` FOREIGN KEY (`LINIJSKI_SUDIJA_SUDIJA_SudijaId`) REFERENCES `linijski_sudija` (`SUDIJA_SudijaId`),
  CONSTRAINT `fk_UTAKMICA_has_LINIJSKI_SUDIJA_UTAKMICA1` FOREIGN KEY (`UTAKMICA_UtakmicaId`) REFERENCES `utakmica` (`UtakmicaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sudi_sa_strane`
--

LOCK TABLES `sudi_sa_strane` WRITE;
/*!40000 ALTER TABLE `sudi_sa_strane` DISABLE KEYS */;
/*!40000 ALTER TABLE `sudi_sa_strane` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sudija`
--

DROP TABLE IF EXISTS `sudija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sudija` (
  `SudijaId` int NOT NULL AUTO_INCREMENT,
  `Ime` varchar(45) NOT NULL,
  `Prezime` varchar(45) NOT NULL,
  `GodineRada` int NOT NULL,
  `BrojUtakmica` int NOT NULL,
  PRIMARY KEY (`SudijaId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sudija`
--

LOCK TABLES `sudija` WRITE;
/*!40000 ALTER TABLE `sudija` DISABLE KEYS */;
INSERT INTO `sudija` VALUES (1,'Milorad','Mazic',15,121),(2,'Michael','Oliver',6,111),(3,'Mike','Dean',22,211);
/*!40000 ALTER TABLE `sudija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trener`
--

DROP TABLE IF EXISTS `trener`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trener` (
  `ZAPOSLENIK_ZapolseniId` int NOT NULL,
  PRIMARY KEY (`ZAPOSLENIK_ZapolseniId`),
  CONSTRAINT `fk_TRENER_ZAPOSLENIK1` FOREIGN KEY (`ZAPOSLENIK_ZapolseniId`) REFERENCES `zaposlenik` (`ZapolseniId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trener`
--

LOCK TABLES `trener` WRITE;
/*!40000 ALTER TABLE `trener` DISABLE KEYS */;
/*!40000 ALTER TABLE `trener` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utakmica`
--

DROP TABLE IF EXISTS `utakmica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utakmica` (
  `UtakmicaId` int NOT NULL AUTO_INCREMENT,
  `KOLO_IdKola` int NOT NULL,
  `GLAVNI_SUDIJA_SUDIJA_SudijaId` int NOT NULL,
  `DatumUtakmice` date NOT NULL,
  PRIMARY KEY (`UtakmicaId`),
  KEY `fk_UTAKMICA_KOLO1_idx` (`KOLO_IdKola`),
  KEY `fk_UTAKMICA_GLAVNI_SUDIJA1_idx` (`GLAVNI_SUDIJA_SUDIJA_SudijaId`),
  CONSTRAINT `fk_UTAKMICA_GLAVNI_SUDIJA1` FOREIGN KEY (`GLAVNI_SUDIJA_SUDIJA_SudijaId`) REFERENCES `glavni_sudija` (`SUDIJA_SudijaId`),
  CONSTRAINT `fk_UTAKMICA_KOLO1` FOREIGN KEY (`KOLO_IdKola`) REFERENCES `kolo` (`BrojKola`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utakmica`
--

LOCK TABLES `utakmica` WRITE;
/*!40000 ALTER TABLE `utakmica` DISABLE KEYS */;
INSERT INTO `utakmica` VALUES (52,1,2,'2010-01-01'),(55,1,2,'2023-05-25'),(56,1,3,'2023-05-25'),(57,1,3,'2023-05-24'),(58,1,3,'2023-05-24'),(59,1,1,'2023-05-23'),(60,1,3,'2023-06-25'),(61,1,1,'2023-05-26'),(62,1,1,'2023-05-26'),(69,2,1,'2023-05-26'),(70,2,1,'2023-05-27'),(71,2,1,'2023-05-25'),(72,2,3,'2023-05-23'),(73,2,1,'2023-05-26'),(74,2,2,'2023-05-26'),(75,2,3,'2023-05-25'),(76,2,1,'2023-05-24'),(77,2,2,'2023-05-23'),(78,2,2,'2023-05-26'),(81,4,1,'2023-05-05'),(85,1,1,'2023-05-05');
/*!40000 ALTER TABLE `utakmica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zaposlenik`
--

DROP TABLE IF EXISTS `zaposlenik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zaposlenik` (
  `ZapolseniId` int NOT NULL AUTO_INCREMENT,
  `Ime` varchar(45) NOT NULL,
  `Prezime` varchar(45) NOT NULL,
  `DatumZaposlenja` date NOT NULL,
  `FUDBALSKI_KLUB_IdKluba` int DEFAULT NULL,
  PRIMARY KEY (`ZapolseniId`),
  KEY `fk_ZAPOSLENIK_FUDBALSKI_KLUB1_idx` (`FUDBALSKI_KLUB_IdKluba`),
  CONSTRAINT `fk_ZAPOSLENIK_FUDBALSKI_KLUB1` FOREIGN KEY (`FUDBALSKI_KLUB_IdKluba`) REFERENCES `fudbalski_klub` (`IdKluba`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zaposlenik`
--

LOCK TABLES `zaposlenik` WRITE;
/*!40000 ALTER TABLE `zaposlenik` DISABLE KEYS */;
INSERT INTO `zaposlenik` VALUES (1,'Marcus','Rashford','2011-05-05',1),(2,'Luke','Shaw','2015-10-10',1),(3,'Erling','Haaland','2022-02-02',2),(4,'Kevin','De Bruyne','2018-05-23',2),(5,'Antony','Martial','2018-10-10',1),(6,'Bernardo ','Silva','2020-10-15',2),(7,'Mohamed','Salah','2019-10-10',13),(8,'Cody','Gakpo','2023-01-01',13),(9,'Harry','Kane','2015-10-23',16),(10,'Heung-Min','Son','2019-01-23',16),(11,'Bukayo','Saka','2022-07-23',5),(12,'Gabriel','Martinelli','2021-08-22',5),(13,'Gabriel','Jesus','2022-06-23',5);
/*!40000 ALTER TABLE `zaposlenik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zaposlenik_u_kompleksu`
--

DROP TABLE IF EXISTS `zaposlenik_u_kompleksu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zaposlenik_u_kompleksu` (
  `ZAPOSLENIK_ZapolseniId` int NOT NULL,
  PRIMARY KEY (`ZAPOSLENIK_ZapolseniId`),
  CONSTRAINT `fk_ZAPOSLENIK_U_KOMPLEKSU_ZAPOSLENIK1` FOREIGN KEY (`ZAPOSLENIK_ZapolseniId`) REFERENCES `zaposlenik` (`ZapolseniId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zaposlenik_u_kompleksu`
--

LOCK TABLES `zaposlenik_u_kompleksu` WRITE;
/*!40000 ALTER TABLE `zaposlenik_u_kompleksu` DISABLE KEYS */;
/*!40000 ALTER TABLE `zaposlenik_u_kompleksu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'fudbalskaliga'
--

--
-- Dumping routines for database 'fudbalskaliga'
--
/*!50003 DROP PROCEDURE IF EXISTS `check_club_in_game` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `check_club_in_game`(in pKolo int,in pKlubId int, out pStatus bool)
begin
    select count(*)>0 into pStatus
    from klub_na_utakmici knu 
    inner join utakmica u on knu.UTAKMICA_UtakmicaId=u.UtakmicaId
    where u.KOLO_IdKola=pKolo and knu.FUDBALSKI_KLUB_IdKluba=pKlubId;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `find_fixture_based_on_game` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `find_fixture_based_on_game`(in pGame int, out fixture int)
begin
	set fixture=-1;
    select u.KOLO_IdKola into fixture
    from utakmica u 
    where u.UtakmicaId=pGame;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `find_teams_in_game` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `find_teams_in_game`(in pUtakmicaId int)
begin
	select * from klub_na_utakmici ku
    where pUtakmicaId=ku.UTAKMICA_UtakmicaId;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-03 13:00:18
