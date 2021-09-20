-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.3.13-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping data for table capstone.buy_order: ~0 rows (approximately)
/*!40000 ALTER TABLE `buy_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `buy_order` ENABLE KEYS */;

-- Dumping data for table capstone.client: ~0 rows (approximately)
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` (`clientid`, `clientname`, `remainingtransactionlimit`, `transactionlimit`, `custodianid`) VALUES
	('DBS001', 'MACQUARIE BANK LIMITED', 14000000, 14000000, 'CS001'),
	('DBS002', 'LLOYDS BANK CORPORATE MARKETS PLC', 14000000, 14000000, 'CS001'),
	('DBS003', 'KEYBANK NATIONAL ASSOCIATION', 18000000, 18000000, 'CS001'),
	('DBS004', 'JP MORGAN SECURITIES LLC', 5000000, 5000000, 'CS001'),
	('DBS005', 'JEFFERIES FINANCIAL SERVICES, INC.', 21000006, 21000006, 'CS002'),
	('DBS006', 'JB DRAX HONORE UK LTD', 21000006, 21000006, 'CS002'),
	('DBS007', 'J ARON & COMPANY SINGAPORE PTE', 18000006, 18000006, 'CS002'),
	('DBS008', 'HSBC BANK PLC', 17500015, 17500015, 'CS003'),
	('DBS009', 'GOLDMAN SACHS PARIS INC ET CIE', 28000024, 28000024, 'CS003');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;

-- Dumping data for table capstone.client_stocks: ~0 rows (approximately)
/*!40000 ALTER TABLE `client_stocks` DISABLE KEYS */;
INSERT INTO `client_stocks` (`quantity`, `instrumentid`, `clientid`) VALUES
	(50, 'I001', 'DBS001'),
	(500, 'I002', 'DBS001'),
	(100, 'I002', 'DBS002'),
	(45, 'I002', 'DBS003');
/*!40000 ALTER TABLE `client_stocks` ENABLE KEYS */;

-- Dumping data for table capstone.custodian: ~0 rows (approximately)
/*!40000 ALTER TABLE `custodian` DISABLE KEYS */;
INSERT INTO `custodian` (`custodianid`, `custodianname`, `password`) VALUES
	('CS001', 'BNP Paribas Securities ServicesFrance', 'bnp'),
	('CS002', 'The Bank of New York Mellon CorporationU.S.', 'bny'),
	('CS003', 'EuroclearBelgium', 'ebg');
/*!40000 ALTER TABLE `custodian` ENABLE KEYS */;

-- Dumping data for table capstone.instrument: ~0 rows (approximately)
/*!40000 ALTER TABLE `instrument` DISABLE KEYS */;
INSERT INTO `instrument` (`instrumentid`, `expirydate`, `facevalue`, `instrumentname`, `minquantity`) VALUES
	('I001', '2021-09-20', 100, 'US treasury Bills', 25),
	('I002', '2021-09-20', 50, 'RBI-2026 -6.5%', 25),
	('I003', '2021-09-20', 1000, 'RBI-2045 -8%', 25),
	('I004', '2020-09-20', 200, 'USDINR', 25),
	('I005', '2021-09-20', 500, 'EUR USD-FUT', 25);
/*!40000 ALTER TABLE `instrument` ENABLE KEYS */;

-- Dumping data for table capstone.sell_order: ~0 rows (approximately)
/*!40000 ALTER TABLE `sell_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `sell_order` ENABLE KEYS */;

-- Dumping data for table capstone.trade_history: ~0 rows (approximately)
/*!40000 ALTER TABLE `trade_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `trade_history` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
