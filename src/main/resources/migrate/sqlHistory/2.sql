/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

/*
 Navicat Premium Data Transfer

 Source Server         : Localhost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : fog

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 05/12/2020 20:05:26
*/

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of customers
-- ----------------------------
BEGIN;
INSERT INTO `customers` VALUES (1, 'test navn', 'test addresse', 1234, 'by', 12345678, 'test@mail.dk');
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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of materiale
-- ----------------------------
BEGIN;
INSERT INTO `materiale` VALUES (1, '19x100 mm. trykimp. Brædt', 10.95);
INSERT INTO `materiale` VALUES (2, '25x150 mm. trykimp. Brædt', 44.95);
INSERT INTO `materiale` VALUES (3, '25x200 mm. trykimp. Brædt', 54.95);
INSERT INTO `materiale` VALUES (4, '25x50 mm. trykimp. Bræt', 8.95);
INSERT INTO `materiale` VALUES (5, '38x73 mm. Lægte ubh.', 1.00);
INSERT INTO `materiale` VALUES (6, '38x73 mm. taglægte T1', 1.00);
INSERT INTO `materiale` VALUES (7, '4.0 x 50 mm. beslagskruer 250 stk.', 425.00);
INSERT INTO `materiale` VALUES (8, '4.5 x 50 mm. Skruer 300 stk.', 510.00);
INSERT INTO `materiale` VALUES (9, '4.5 x 50 mm. Skruer 350 stk.', 595.00);
INSERT INTO `materiale` VALUES (10, '4.5 x 60 mm. Skruer 200 stk.', 150.00);
INSERT INTO `materiale` VALUES (11, '4.5 x 70 mm. Skruer 200 stk.', 200.00);
INSERT INTO `materiale` VALUES (12, '4.5 x 70 mm. Skruer 400 stk.', 400.00);
INSERT INTO `materiale` VALUES (13, '45x195 mm. spærtræ ubh.', 60.00);
INSERT INTO `materiale` VALUES (14, '45x95 mm. Reglar ub.', 22.00);
INSERT INTO `materiale` VALUES (15, '5.0 x 100 mm. skruer 100 stk.', 200.00);
INSERT INTO `materiale` VALUES (16, '5.0 x 40 mm. beslagskruer 250 stk.', 174.00);
INSERT INTO `materiale` VALUES (17, '97x97 mm. trykimp. Stolpe', 50.00);
INSERT INTO `materiale` VALUES (18, 'B & C Dobbelt -s sort', 1.00);
INSERT INTO `materiale` VALUES (19, 'B & C Rygsten sort', 1.00);
INSERT INTO `materiale` VALUES (20, 'B & C rygstensbeslag', 1.00);
INSERT INTO `materiale` VALUES (21, 'B & C tagstens bindere & nakkekroge', 1.00);
INSERT INTO `materiale` VALUES (22, 'B & C Toplægte holder', 1.00);
INSERT INTO `materiale` VALUES (23, 'bræddebolt 10 x 120 mm.', 20.00);
INSERT INTO `materiale` VALUES (24, 'firkantskiver 40x40x11mm', 18.00);
INSERT INTO `materiale` VALUES (25, 'fædigskåret (byg-selv spær)', 1.00);
INSERT INTO `materiale` VALUES (26, 'hulbånd 20x1 mm. 10 mtr.', 209.00);
INSERT INTO `materiale` VALUES (27, 'plastmo bundskruer 200 stk.', 409.00);
INSERT INTO `materiale` VALUES (28, 'Plastmo Ecolite blåtonet', 41.00);
INSERT INTO `materiale` VALUES (29, 'Stalddørsgreb 50x75', 189.00);
INSERT INTO `materiale` VALUES (30, 'T-hængsel 390 mm.', 80.00);
INSERT INTO `materiale` VALUES (31, 'universal 190 mm højre', 1.00);
INSERT INTO `materiale` VALUES (32, 'universal 190 mm venstre', 1.00);
INSERT INTO `materiale` VALUES (33, 'vinkelbeslag', 1.00);
INSERT INTO `materiale` VALUES (34, 'vinkelbeslag 35', 1.00);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of orders
-- ----------------------------
BEGIN;
INSERT INTO `orders` VALUES (1, NULL, NULL, NULL, NULL, NULL, 'New', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for partlists
-- ----------------------------
DROP TABLE IF EXISTS `partlists`;
CREATE TABLE `partlists` (
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_partlists_parts_1` FOREIGN KEY (`id`) REFERENCES `parts` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

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
  `partlist_id` int DEFAULT NULL,
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
INSERT INTO `properties` VALUES (2, 25.00);
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of type
-- ----------------------------
BEGIN;
INSERT INTO `type` VALUES (2, 'Poles');
INSERT INTO `type` VALUES (3, 'Boards');
INSERT INTO `type` VALUES (4, 'Laths');
INSERT INTO `type` VALUES (5, 'Rules');
INSERT INTO `type` VALUES (6, 'Rafts');
INSERT INTO `type` VALUES (7, 'Roofing');
INSERT INTO `type` VALUES (8, 'BottomScrews');
INSERT INTO `type` VALUES (9, 'PerforatedTape');
INSERT INTO `type` VALUES (10, 'Universal');
INSERT INTO `type` VALUES (11, 'Screw');
INSERT INTO `type` VALUES (12, 'FittingScrews');
INSERT INTO `type` VALUES (13, 'Fitting');
INSERT INTO `type` VALUES (14, 'BoardBolt');
INSERT INTO `type` VALUES (15, 'SquareDiscs');
INSERT INTO `type` VALUES (16, 'BarnDoorHandle');
INSERT INTO `type` VALUES (17, 'Hinge');
INSERT INTO `type` VALUES (18, 'AngleBracket');
INSERT INTO `type` VALUES (19, 'Dobbelt');
INSERT INTO `type` VALUES (20, 'BackStone');
INSERT INTO `type` VALUES (21, 'TopLayer');
INSERT INTO `type` VALUES (22, 'BackstoneFittings');
INSERT INTO `type` VALUES (23, 'RoofBinder');
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
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of usage
-- ----------------------------
BEGIN;
INSERT INTO `usage` VALUES (1, 'UnderStarFronBack', 3, 3);
INSERT INTO `usage` VALUES (2, 'UnderStarSide', 3, 3);
INSERT INTO `usage` VALUES (3, 'OverStarFronBack', 2, 3);
INSERT INTO `usage` VALUES (4, 'OverStarSide', 2, 3);
INSERT INTO `usage` VALUES (5, 'Door', 5, 4);
INSERT INTO `usage` VALUES (6, 'ShedGables', 14, 5);
INSERT INTO `usage` VALUES (7, 'ShedSide', 14, 5);
INSERT INTO `usage` VALUES (8, 'Straps', 13, 6);
INSERT INTO `usage` VALUES (9, 'ShedStraps', 13, 6);
INSERT INTO `usage` VALUES (10, 'Roof', 13, 6);
INSERT INTO `usage` VALUES (11, 'Foundation', 17, 2);
INSERT INTO `usage` VALUES (12, 'ShedCladding', 1, 3);
INSERT INTO `usage` VALUES (13, 'WaterStarSide', 1, 3);
INSERT INTO `usage` VALUES (14, 'WaterStartEnd', 1, 3);
INSERT INTO `usage` VALUES (15, 'RoffingBig', 28, 7);
INSERT INTO `usage` VALUES (16, 'RoffingSmall', 28, 7);
INSERT INTO `usage` VALUES (17, 'Roof', 27, 8);
INSERT INTO `usage` VALUES (18, 'Windshield', 26, 9);
INSERT INTO `usage` VALUES (19, 'RaftersRight', 31, 10);
INSERT INTO `usage` VALUES (20, 'RaftersLeft', 32, 10);
INSERT INTO `usage` VALUES (21, 'StarWaterBoard', 10, 11);
INSERT INTO `usage` VALUES (22, 'UniversalFittings', 7, 12);
INSERT INTO `usage` VALUES (23, 'Pole', 23, 14);
INSERT INTO `usage` VALUES (24, 'Pole', 24, 15);
INSERT INTO `usage` VALUES (25, 'OuterCladding400', 12, 11);
INSERT INTO `usage` VALUES (26, 'InnerCladding300', 8, 11);
INSERT INTO `usage` VALUES (27, 'Shed', 29, 16);
INSERT INTO `usage` VALUES (28, 'Shed', 30, 17);
INSERT INTO `usage` VALUES (29, 'Shed', 34, 18);
INSERT INTO `usage` VALUES (30, 'BackStone', 20, 22);
INSERT INTO `usage` VALUES (31, 'CustomRoof', 25, 6);
INSERT INTO `usage` VALUES (32, 'InnerCladding350', 9, 11);
INSERT INTO `usage` VALUES (33, 'OuterCladding200', 11, 11);
INSERT INTO `usage` VALUES (34, 'Rafters', 22, 21);
INSERT INTO `usage` VALUES (35, 'Roof', 6, 4);
INSERT INTO `usage` VALUES (36, 'RoofBattens', 18, 19);
INSERT INTO `usage` VALUES (37, 'RoofBattens', 15, 11);
INSERT INTO `usage` VALUES (38, 'RoofBattens', 4, 3);
INSERT INTO `usage` VALUES (39, 'RoofTiles', 21, 23);
INSERT INTO `usage` VALUES (40, 'StarSide', 2, 3);
INSERT INTO `usage` VALUES (41, 'TopLath', 19, 20);
INSERT INTO `usage` VALUES (42, 'TopLath', 6, 4);
INSERT INTO `usage` VALUES (43, 'WaterStartEnd', 2, 3);
INSERT INTO `usage` VALUES (44, 'Windshield', 2, 3);
INSERT INTO `usage` VALUES (45, 'WindshieldsBoard', 1, 3);
INSERT INTO `usage` VALUES (46, 'WindshieldsGavel', 1, 3);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES (1, 'Admin user', 'admin@admin.dk', 'Admin', 0xA94D392CA48DB1C0E32AD7A760CDEBF1, 0x3024A3A2A0DE7366E575919B9BD4A28FA50EA2907957DAFF6E905840A9981E33, 1);
INSERT INTO `users` VALUES (4, 'test', 'test@test.dk', 'Employee', 0x7FF8F8F4D436BDFE960B25D2FC0AE70C, 0x2198AAB853A75F9EAA8018A32A024E5E529FF484F932AF2DB30C1540E2F5A52F, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
