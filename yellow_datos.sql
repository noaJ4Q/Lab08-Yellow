-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: yellow
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `catalogodeobjetos`
--

DROP TABLE IF EXISTS `catalogodeobjetos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `catalogodeobjetos` (
  `idObjeto` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `efecto_uso` varchar(200) NOT NULL,
  `peso` float NOT NULL,
  PRIMARY KEY (`idObjeto`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogodeobjetos`
--

LOCK TABLES `catalogodeobjetos` WRITE;
/*!40000 ALTER TABLE `catalogodeobjetos` DISABLE KEYS */;
INSERT INTO `catalogodeobjetos` VALUES (1,'Arma Artema','Espada que inflinge un 50% menos de daño a enemigos cercanos, mientras que a los lejanos aumenta en 30%',5.2),(2,'Aguja Sombría','El personaje recibe 5% de experiencia adicional por cada muerte dentro de un intervalo de 1 minuto',15.5),(3,'Apoyo Espectral','Pergamino que invoca una version espectral de tu pareja',7.8),(4,'Espada Dorada','Espada pemanentemente en llamas que aumenta el ataque del personaje en 10%',7.5),(5,'Sable Largo','Roba un objeto de curación de tu oponente y atúrdelo durante 10 segunos',30.6);
/*!40000 ALTER TABLE `catalogodeobjetos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clase`
--

DROP TABLE IF EXISTS `clase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clase` (
  `idClase` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  PRIMARY KEY (`idClase`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clase`
--

LOCK TABLES `clase` WRITE;
/*!40000 ALTER TABLE `clase` DISABLE KEYS */;
INSERT INTO `clase` VALUES (1,'Dragon'),(2,'Fantasma'),(3,'Demonio'),(4,'Pez'),(5,'Humano'),(6,'Bestia'),(7,'Ave'),(8,'Otros');
/*!40000 ALTER TABLE `clase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `danoporelemento`
--

DROP TABLE IF EXISTS `danoporelemento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `danoporelemento` (
  `idClase` int NOT NULL,
  `idElemento` int NOT NULL,
  `danoRecibido` float NOT NULL,
  PRIMARY KEY (`idClase`,`idElemento`),
  KEY `fk_Clase_has_Elemento_Elemento1_idx` (`idElemento`),
  KEY `fk_Clase_has_Elemento_Clase1_idx` (`idClase`),
  CONSTRAINT `fk_Clase_has_Elemento_Clase1` FOREIGN KEY (`idClase`) REFERENCES `clase` (`idClase`),
  CONSTRAINT `fk_Clase_has_Elemento_Elemento1` FOREIGN KEY (`idElemento`) REFERENCES `elemento` (`idElemento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `danoporelemento`
--

LOCK TABLES `danoporelemento` WRITE;
/*!40000 ALTER TABLE `danoporelemento` DISABLE KEYS */;
INSERT INTO `danoporelemento` VALUES (1,1,0.3),(1,2,0.5),(1,3,0.2),(1,4,0.1),(1,5,0.15),(2,1,1.2),(2,2,0.05),(2,3,2.5),(2,4,1.5),(2,5,0.2),(3,1,0.1),(3,2,0.6),(3,3,3),(3,4,0.3),(3,5,0.3),(4,1,2.8),(4,2,1.5),(4,3,0.05),(4,4,0.8),(4,5,0.4),(5,1,1.8),(5,2,0.8),(5,3,0.5),(5,4,0.3),(5,5,0.1),(6,1,0.7),(6,2,0.2),(6,3,0.4),(6,4,0.1),(6,5,0.3),(7,1,1.7),(7,2,0.8),(7,3,0.6),(7,4,0.05),(7,5,0.2),(8,1,1.3),(8,2,0.7),(8,3,0.5),(8,4,0.4),(8,5,0.3);
/*!40000 ALTER TABLE `danoporelemento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dropeo`
--

DROP TABLE IF EXISTS `dropeo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dropeo` (
  `idObjeto` int NOT NULL,
  `idEnemigo` int NOT NULL,
  `probabilidad` float NOT NULL,
  PRIMARY KEY (`idObjeto`,`idEnemigo`),
  KEY `fk_Objeto_has_Enemigo_Enemigo1_idx` (`idEnemigo`),
  KEY `fk_Objeto_has_Enemigo_Objeto1_idx` (`idObjeto`),
  CONSTRAINT `fk_Objeto_has_Enemigo_Enemigo1` FOREIGN KEY (`idEnemigo`) REFERENCES `enemigo` (`idEnemigo`),
  CONSTRAINT `fk_Objeto_has_Enemigo_Objeto1` FOREIGN KEY (`idObjeto`) REFERENCES `catalogodeobjetos` (`idObjeto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dropeo`
--

LOCK TABLES `dropeo` WRITE;
/*!40000 ALTER TABLE `dropeo` DISABLE KEYS */;
/*!40000 ALTER TABLE `dropeo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `elemento`
--

DROP TABLE IF EXISTS `elemento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `elemento` (
  `idElemento` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(15) NOT NULL,
  PRIMARY KEY (`idElemento`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elemento`
--

LOCK TABLES `elemento` WRITE;
/*!40000 ALTER TABLE `elemento` DISABLE KEYS */;
INSERT INTO `elemento` VALUES (1,'Fuego'),(2,'Tierra'),(3,'Agua'),(4,'Viento'),(5,'Void');
/*!40000 ALTER TABLE `elemento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enemigo`
--

DROP TABLE IF EXISTS `enemigo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enemigo` (
  `idEnemigo` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `idClase` int NOT NULL,
  `ataque` int NOT NULL,
  `experiencia` int NOT NULL,
  `idGenero` char(1) DEFAULT NULL,
  PRIMARY KEY (`idEnemigo`),
  KEY `fk_Enemigo_Clase1_idx` (`idClase`),
  KEY `fk_Enemigo_Genero1_idx` (`idGenero`),
  CONSTRAINT `fk_Enemigo_Clase1` FOREIGN KEY (`idClase`) REFERENCES `clase` (`idClase`),
  CONSTRAINT `fk_Enemigo_Genero1` FOREIGN KEY (`idGenero`) REFERENCES `genero` (`idGenero`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enemigo`
--

LOCK TABLES `enemigo` WRITE;
/*!40000 ALTER TABLE `enemigo` DISABLE KEYS */;
INSERT INTO `enemigo` VALUES (1,'DevilMan',3,300,700,'M'),(2,'HardRock',6,500,1000,'M'),(3,'BombGirl',5,250,500,'F'),(4,'DragonChaos',1,400,900,'M'),(5,'MeanBird',7,150,350,'-');
/*!40000 ALTER TABLE `enemigo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genero`
--

DROP TABLE IF EXISTS `genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genero` (
  `idGenero` char(1) NOT NULL,
  `nombre` char(10) NOT NULL,
  PRIMARY KEY (`idGenero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genero`
--

LOCK TABLES `genero` WRITE;
/*!40000 ALTER TABLE `genero` DISABLE KEYS */;
INSERT INTO `genero` VALUES ('-',''),('F','Femenino'),('M','Masculino'),('O','Otro');
/*!40000 ALTER TABLE `genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hechizo`
--

DROP TABLE IF EXISTS `hechizo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hechizo` (
  `idHechizo` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(15) NOT NULL,
  `idElemento` int NOT NULL,
  `potencia` float NOT NULL,
  `precision` float NOT NULL,
  `idHechizoBase` int DEFAULT NULL,
  `nivelDeAprendizaje` tinyint DEFAULT NULL,
  PRIMARY KEY (`idHechizo`),
  KEY `fk_Hechizo_Elemento1_idx` (`idElemento`),
  KEY `fk_Hechizo_Hechizo1_idx` (`idHechizoBase`),
  CONSTRAINT `fk_Hechizo_Elemento1` FOREIGN KEY (`idElemento`) REFERENCES `elemento` (`idElemento`),
  CONSTRAINT `fk_Hechizo_Hechizo1` FOREIGN KEY (`idHechizoBase`) REFERENCES `hechizo` (`idHechizo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hechizo`
--

LOCK TABLES `hechizo` WRITE;
/*!40000 ALTER TABLE `hechizo` DISABLE KEYS */;
/*!40000 ALTER TABLE `hechizo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `heroe`
--

DROP TABLE IF EXISTS `heroe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `heroe` (
  `idHeroe` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(10) NOT NULL,
  `edad` varchar(3) NOT NULL,
  `idGenero` char(1) NOT NULL,
  `clase` varchar(50) NOT NULL,
  `nivel` int NOT NULL,
  `ataque` int NOT NULL,
  `experiencia` float NOT NULL,
  `idPareja` int DEFAULT NULL,
  PRIMARY KEY (`idHeroe`),
  KEY `fk_Heroe_Genero_idx` (`idGenero`),
  KEY `fk_Heroe_Heroe1_idx` (`idPareja`),
  CONSTRAINT `fk_Heroe_Genero` FOREIGN KEY (`idGenero`) REFERENCES `genero` (`idGenero`),
  CONSTRAINT `fk_Heroe_Heroe1` FOREIGN KEY (`idPareja`) REFERENCES `heroe` (`idHeroe`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `heroe`
--

LOCK TABLES `heroe` WRITE;
/*!40000 ALTER TABLE `heroe` DISABLE KEYS */;
INSERT INTO `heroe` VALUES (1,'Urhan','30','M','Caballero',50,40,142500,NULL),(2,'Ataz','350','F','Curandero',12,20,979.2,3),(3,'Ophior','120','F','Bestia',20,230,5440,NULL),(4,'Atosh','560','M','Guardabosque',70,100,459620,1),(5,'Ilrolius','90','O','Mago',5,560,65,2);
/*!40000 ALTER TABLE `heroe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventario`
--

DROP TABLE IF EXISTS `inventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventario` (
  `idInventario` int NOT NULL AUTO_INCREMENT,
  `idHeroe` int NOT NULL,
  `idObjeto` int NOT NULL,
  PRIMARY KEY (`idInventario`),
  KEY `fk_Heroe_has_Objeto_Objeto1_idx` (`idObjeto`),
  KEY `fk_Heroe_has_Objeto_Heroe1_idx` (`idHeroe`),
  CONSTRAINT `fk_Heroe_has_Objeto_Heroe1` FOREIGN KEY (`idHeroe`) REFERENCES `heroe` (`idHeroe`),
  CONSTRAINT `fk_Heroe_has_Objeto_Objeto1` FOREIGN KEY (`idObjeto`) REFERENCES `catalogodeobjetos` (`idObjeto`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventario`
--

LOCK TABLES `inventario` WRITE;
/*!40000 ALTER TABLE `inventario` DISABLE KEYS */;
INSERT INTO `inventario` VALUES (1,1,3),(2,5,1),(3,1,1),(4,5,4),(5,2,2);
/*!40000 ALTER TABLE `inventario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-04 16:25:42
