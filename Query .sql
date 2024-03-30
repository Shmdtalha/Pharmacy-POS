-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.1.0 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table pharmacypos.cartproducts
CREATE TABLE IF NOT EXISTS cartproducts (
  cartId varchar(20) NOT NULL,
  productCode varchar(255) NOT NULL,
  quantity int NOT NULL,
  productPrice double NOT NULL,
  KEY cartId (cartId),
  KEY productCode (productCode),
  CONSTRAINT cartproducts_ibfk_1 FOREIGN KEY (cartId) REFERENCES customercarts (cartId),
  CONSTRAINT cartproducts_ibfk_2 FOREIGN KEY (productCode) REFERENCES products (productCode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table pharmacypos.cartproducts: ~15 rows (approximately)
REPLACE INTO cartproducts (cartId, productCode, quantity, productPrice) VALUES
	('2311270310521152', 'AMOX500', 1, 0.3),
	('231127031705115', 'AMOX500', 11, 0.3),
	('231127031705115', 'AMOX500', 120, 0.3),
	('231127031705115', 'PRT', 1, 192.3),
	('2311270317451145', 'AMOX500', 11, 0.3),
	('2311270317451145', 'AMOX500', 120, 0.3),
	('2311270317451145', 'PRT', 1, 192.3),
	('2311270317451145', 'PRT', 1, 12.3),
	('231127031801111', 'AMOX500', 11, 0.3),
	('231127031801111', 'AMOX500', 120, 0.3),
	('231127031801111', 'PRT', 1, 192.3),
	('231127031801111', 'PRT', 1, 12.3),
	('231127031801111', 'PRT', 1, 12.3),
	('231127031801111', 'AMOX500', 1, 0.3),
	('2311270320301130', 'PRT', 1, 12.3),
	('2311270320401140', 'AMOX500', 1, 0.3),
	('2311290944531153', 'AMOX500', 1, 0.3),
	('2311290209521152', 'AMOX500', 1, 0.3),
	('231129021502112', 'AMOX500', 1, 0.3),
	('2311290215411141', 'VITC1000', 1, 0.2),
	('2311290216391139', 'VITC1000', 91, 0.2);

-- Dumping structure for table pharmacypos.categories
CREATE TABLE IF NOT EXISTS categories (
  categoryCode varchar(255) NOT NULL,
  categoryName varchar(255) NOT NULL,
  description text,
  parentCategoryCode varchar(255) DEFAULT NULL,
  PRIMARY KEY (categoryCode),
  UNIQUE KEY categoryCode (categoryCode),
  KEY categories_ibfk_1 (parentCategoryCode),
  CONSTRAINT categories_ibfk_1 FOREIGN KEY (parentCategoryCode) REFERENCES categories (categoryCode) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table pharmacypos.categories: ~6 rows (approximately)
REPLACE INTO categories (categoryCode, categoryName, description, parentCategoryCode) VALUES
	('KPK', 'Knee Pain Killers', 'Kills Knee Pain', 'PAINK'),
	('NUTR', 'Nutrients', 'Healthy Nutrients', NULL),
	('PAINK', 'Pain Killers', 'Medications for pain relief', NULL),
	('SKINC', 'Skin LovelyDoer', 'Fresh fresh skin very', NULL),
	('STM', 'Stomach', 'For Stomach Pain', 'PAINK'),
	('VITMN', 'Vitamins', 'Supplements and vitamins for various health needs', NULL);

-- Dumping structure for table pharmacypos.customercarts
CREATE TABLE IF NOT EXISTS customercarts (
  cartId varchar(20) NOT NULL,
  customerName varchar(255) DEFAULT NULL,
  totalAmount double NOT NULL,
  PRIMARY KEY (cartId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table pharmacypos.customercarts: ~7 rows (approximately)
REPLACE INTO customercarts (cartId, customerName, totalAmount) VALUES
	('2311270310521152', NULL, 0),
	('231127031705115', NULL, 231.60000000000002),
	('2311270317451145', NULL, 12.3),
	('231127031801111', NULL, 12.600000000000001),
	('2311270320301130', NULL, 12.3),
	('2311270320401140', NULL, 0.3),
	('231129020100110', NULL, 2.6999999999999997),
	('2311290201411141', NULL, 0.3),
	('2311290209521152', NULL, 0.3),
	('231129021502112', NULL, 0.3),
	('2311290215411141', NULL, 0.2),
	('2311290216391139', NULL, 18.2),
	('2311290944531153', NULL, 0.3);

-- Dumping structure for table pharmacypos.expirytable
CREATE TABLE IF NOT EXISTS expirytable (
  productCode varchar(255) NOT NULL,
  batchNumber int NOT NULL,
  expiryDate date DEFAULT NULL,
  location varchar(255) DEFAULT NULL,
  PRIMARY KEY (productCode,batchNumber),
  CONSTRAINT expirytable_ibfk_1 FOREIGN KEY (productCode) REFERENCES products (productCode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table pharmacypos.expirytable: ~0 rows (approximately)
REPLACE INTO expirytable (productCode, batchNumber, expiryDate, location) VALUES
	('AMOX500', 2, '2024-12-15', 'Warehouse 6, Box 10'),
	('AMOX500', 15, '2023-12-22', 'TOp shelf, LHS'),
	('AMOX500', 18, '2025-11-21', 'TOp shelf, LHS'),
	('AMOX500', 88, '2013-08-19', 'Under counter'),
	('CBH', 12, '2023-12-19', 'Shelf 14, Row 3'),
	('TPK', 1, '2023-12-15', 'Warehouse 5, Box 10');

-- Dumping structure for table pharmacypos.productcategories
CREATE TABLE IF NOT EXISTS productcategories (
  productCode varchar(255) NOT NULL,
  categoryCode varchar(255) NOT NULL,
  PRIMARY KEY (productCode,categoryCode),
  KEY productCode (productCode),
  KEY productcategories_ibfk_2 (categoryCode),
  CONSTRAINT productcategories_ibfk_1 FOREIGN KEY (productCode) REFERENCES products (productCode) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT productcategories_ibfk_2 FOREIGN KEY (categoryCode) REFERENCES categories (categoryCode) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table pharmacypos.productcategories: ~8 rows (approximately)
REPLACE INTO productcategories (productCode, categoryCode) VALUES
	('AMOX500', 'PAINK'),
	('CBH', 'NUTR'),
	('HPKR', 'PAINK'),
	('KPE', 'KPK'),
	('KPE', 'PAINK'),
	('PRT', 'NUTR'),
	('SNYD', 'VITMN'),
	('TCRM500', 'SKINC'),
	('TD', 'PAINK'),
	('TPK', 'PAINK'),
	('VITC1000', 'VITMN'),
	('VSL', 'SKINC');

-- Dumping structure for table pharmacypos.products
CREATE TABLE IF NOT EXISTS products (
  productCode varchar(255) NOT NULL,
  productName varchar(255) NOT NULL,
  description text,
  stockQuantity int NOT NULL,
  price decimal(10,2) NOT NULL,
  PRIMARY KEY (productCode),
  UNIQUE KEY productCode (productCode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table pharmacypos.products: ~9 rows (approximately)
REPLACE INTO products (productCode, productName, description, stockQuantity, price) VALUES
	('AMOX500', 'Amoxicillin 500mg', 'Used to treat a wide variety of bacterial infections', 38, 0.30),
	('CBH', 'Carbohydrates 75g', 'This is a new product', 144, 12.99),
	('COLDMED1', 'Cold Medicine', 'Medication for cold and flu symptoms', 150, 4.99),
	('GRLS', 'Gas Releaser 500ml', 'This is a new product', 11, 29.30),
	('HPKR', 'Heart PainKiller', 'This is a new product', 54, 123.10),
	('KFXR', 'Knee Fixer', 'This is a new product', 23, 1.34),
	('KPE', 'Knee Pain Ender 600g', 'This is a new product', 158, 23.56),
	('MVTMN', 'Multi-Vitamin Pills 200g', 'This is a new product', 23, 12.40),
	('PRT', 'Protein 500g', 'This is a new product', 534, 12.30),
	('SFXR', 'Stomach Fixer 55mg', 'This is a new product', 423, 11.00),
	('SNYD', 'SunnyD 500g', 'This is a new product', 12, 1.50),
	('TCRM500', 'Topical Antibiotic Cream', 'Cream with antibiotic properties for skin care', 80, 7.50),
	('TD', 'T-Day 6g', 'This is a new product', 20, 2.00),
	('TPK', 'Toe Pain Killer', 'This is a new product', 12, 9.31),
	('VITC1000', 'Vitamin C 1000mg', 'Vitamin C supplement for immunity boosting', 58, 0.20),
	('VSL', 'Vaseline 200ml', 'This is a new product', 50, 10.50);

-- Dumping structure for table pharmacypos.user
CREATE TABLE IF NOT EXISTS user (
  id varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  role varchar(255) NOT NULL,
  username varchar(255) NOT NULL,
  PASSWORD varchar(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table pharmacypos.user: ~0 rows (approximately)
REPLACE INTO user (id, name, role, username, PASSWORD) VALUES
	('1', 'Dummy1', 'MANAGER_ROLE', 'dum1', 'dum1');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
\

INSERT INTO User VALUES ('2', 'Dummy2', 'SALESASSISTANT_ROLE', 'dum2', 'dum2');
INSERT INTO USER VALUES("2", "Dummy2", "SALESASSISTANT_ROLE", "dum2", "dum2");