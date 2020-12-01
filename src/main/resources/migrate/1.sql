SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for carports
-- ----------------------------
DROP TABLE IF EXISTS `carports`;
CREATE TABLE `carports` (
  `id` int NOT NULL AUTO_INCREMENT,
  `length` double NOT NULL,
  `width` double NOT NULL,
  `roof` enum('Flat','Sloping') COLLATE utf8_danish_ci NOT NULL,
  `shed_id` int DEFAULT NULL,
  `price` decimal(8,2) NOT NULL,
  `partlist_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_carports_sheds_1` (`shed_id`),
  KEY `fk_carports_partlists_1` (`partlist_id`),
  CONSTRAINT `fk_carports_partlists_1` FOREIGN KEY (`partlist_id`) REFERENCES `partlists` (`id`),
  CONSTRAINT `fk_carports_sheds_1` FOREIGN KEY (`shed_id`) REFERENCES `sheds` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of carports
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_danish_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_danish_ci NOT NULL,
  `postal` int NOT NULL,
  `city` varchar(255) COLLATE utf8_danish_ci NOT NULL,
  `phone` int NOT NULL,
  `email` varchar(255) COLLATE utf8_danish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of customers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for materiale
-- ----------------------------
DROP TABLE IF EXISTS `materiale`;
CREATE TABLE `materiale` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_danish_ci DEFAULT NULL,
  `price` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of materiale
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `width` double DEFAULT NULL,
  `length` double DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  `employee_id` int DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  `status` enum('New','Awaiting','Accepted','Paid','Completed','Cancelled') COLLATE utf8_danish_ci DEFAULT 'New',
  `carport_id` int DEFAULT NULL,
  `margin` decimal(4,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_orders_customers_1` (`customer_id`),
  KEY `fk_orders_users_1` (`employee_id`),
  KEY `fk_orders_carports_1` (`carport_id`),
  CONSTRAINT `fk_orders_carports_1` FOREIGN KEY (`carport_id`) REFERENCES `carports` (`id`),
  CONSTRAINT `fk_orders_customers_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  CONSTRAINT `fk_orders_users_1` FOREIGN KEY (`employee_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of orders
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for partlists
-- ----------------------------
DROP TABLE IF EXISTS `partlists`;
CREATE TABLE `partlists` (
  `id` int NOT NULL AUTO_INCREMENT,
  `part_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_partlists_parts_1` (`part_id`),
  CONSTRAINT `fk_partlists_parts_1` FOREIGN KEY (`part_id`) REFERENCES `parts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of partlists
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for parts
-- ----------------------------
DROP TABLE IF EXISTS `parts`;
CREATE TABLE `parts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8_danish_ci DEFAULT NULL,
  `usage_id` int DEFAULT NULL,
  `amount` int DEFAULT NULL,
  `length` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_part_usage_1` (`usage_id`),
  CONSTRAINT `fk_part_usage_1` FOREIGN KEY (`usage_id`) REFERENCES `usage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of parts
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for properties
-- ----------------------------
DROP TABLE IF EXISTS `properties`;
CREATE TABLE `properties` (
  `version` int NOT NULL,
  `vat` decimal(4,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of properties
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sheds
-- ----------------------------
DROP TABLE IF EXISTS `sheds`;
CREATE TABLE `sheds` (
  `id` int NOT NULL AUTO_INCREMENT,
  `length` double DEFAULT NULL,
  `width` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of sheds
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_danish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of type
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for usage
-- ----------------------------
DROP TABLE IF EXISTS `usage`;
CREATE TABLE `usage` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_danish_ci DEFAULT NULL,
  `material_id` int DEFAULT NULL,
  `type_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_usage_materiale_1` (`material_id`),
  KEY `fk_usage_type_1` (`type_id`),
  CONSTRAINT `fk_usage_materiale_1` FOREIGN KEY (`material_id`) REFERENCES `materiale` (`id`),
  CONSTRAINT `fk_usage_type_1` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of usage
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_danish_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_danish_ci NOT NULL,
  `role` enum('Employee','Admin') COLLATE utf8_danish_ci NOT NULL DEFAULT 'Employee',
  `salt` blob NOT NULL,
  `secret` blob NOT NULL,
  `active` tinyint DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES (1, 'Admin user', 'admin@admin.dk', 'Admin', 0xA94D392CA48DB1C0E32AD7A760CDEBF1, 0x3024A3A2A0DE7366E575919B9BD4A28FA50EA2907957DAFF6E905840A9981E33, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
