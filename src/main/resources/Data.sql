
-- Dumping database structure for testsql
CREATE DATABASE IF NOT EXISTS `testsql` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `testsql`;

-- Dumping structure for table testsql.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table testsql.hibernate_sequence: 1 rows
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(5);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Dumping structure for table testsql.TESTCASE_TESTSTEP_MAPPING
CREATE TABLE IF NOT EXISTS `TESTCASE_TESTSTEP_MAPPING` (
  `TESTCASE_ID` bigint NOT NULL,
  `TESTSTEP_ID` bigint NOT NULL,
  PRIMARY KEY (`TESTCASE_ID`,`TESTSTEP_ID`),
  KEY `FKbqax3jgvyn65mic4r5o2xq2dp` (`TESTSTEP_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table testsql.TESTCASE_TESTSTEP_MAPPING: 0 rows
/*!40000 ALTER TABLE `TESTCASE_TESTSTEP_MAPPING` DISABLE KEYS */;
INSERT INTO `TESTCASE_TESTSTEP_MAPPING` (`TESTCASE_ID`, `TESTSTEP_ID`) VALUES
	(4, 3);
/*!40000 ALTER TABLE `TESTCASE_TESTSTEP_MAPPING` ENABLE KEYS */;

-- Dumping structure for table testsql.TESTSUITE_TESTCASE_MAPPING
CREATE TABLE IF NOT EXISTS `TESTSUITE_TESTCASE_MAPPING` (
  `TESTSUITE_ID` bigint NOT NULL,
  `TESTCASE_ID` bigint NOT NULL,
  PRIMARY KEY (`TESTSUITE_ID`,`TESTCASE_ID`),
  KEY `FKhc0xc5rdcyy3hb0xqrlhfjol5` (`TESTCASE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table testsql.TESTSUITE_TESTCASE_MAPPING: 0 rows
/*!40000 ALTER TABLE `TESTSUITE_TESTCASE_MAPPING` DISABLE KEYS */;
INSERT INTO `TESTSUITE_TESTCASE_MAPPING` (`TESTSUITE_ID`, `TESTCASE_ID`) VALUES
	(3, 1),
	(3, 2);
/*!40000 ALTER TABLE `TESTSUITE_TESTCASE_MAPPING` ENABLE KEYS */;

-- Dumping structure for table testsql.TEST_CASE
CREATE TABLE IF NOT EXISTS `TEST_CASE` (
  `ID` bigint NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table testsql.TEST_CASE: 0 rows
/*!40000 ALTER TABLE `TEST_CASE` DISABLE KEYS */;
INSERT INTO `TEST_CASE` (`ID`, `DESCRIPTION`, `NAME`) VALUES
	(3, 'Login', 'Login');
/*!40000 ALTER TABLE `TEST_CASE` ENABLE KEYS */;

-- Dumping structure for table testsql.TEST_STEP
CREATE TABLE IF NOT EXISTS `TEST_STEP` (
  `ID` bigint NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table testsql.TEST_STEP: 0 rows
/*!40000 ALTER TABLE `TEST_STEP` DISABLE KEYS */;
INSERT INTO `TEST_STEP` (`ID`, `DESCRIPTION`, `NAME`) VALUES
	(4, 'Visit URL/Website.', 'Visit URL/Website.');
/*!40000 ALTER TABLE `TEST_STEP` ENABLE KEYS */;

-- Dumping structure for table testsql.TEST_SUITE
CREATE TABLE IF NOT EXISTS `TEST_SUITE` (
  `ID` bigint NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table testsql.TEST_SUITE: 0 rows
/*!40000 ALTER TABLE `TEST_SUITE` DISABLE KEYS */;
INSERT INTO `TEST_SUITE` (`ID`, `DESCRIPTION`, `NAME`) VALUES
	(1, 'Account', 'Account'),
	(2, 'Beneficiary', 'Beneficiary');