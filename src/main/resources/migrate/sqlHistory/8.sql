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

 Date: 10/12/2020 11:43:02
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
  `roof` enum('Flat','Peak') CHARACTER SET utf8 COLLATE utf8_danish_ci NOT NULL,
  `shed_id` int DEFAULT NULL,
  `price` decimal(8,2) NOT NULL,
  `partlist_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_carports_sheds_1` (`shed_id`),
  KEY `fk_carports_partlists_1` (`partlist_id`),
  CONSTRAINT `fk_carports_partlists_1` FOREIGN KEY (`partlist_id`) REFERENCES `partlists` (`id`),
  CONSTRAINT `fk_carports_sheds_1` FOREIGN KEY (`shed_id`) REFERENCES `sheds` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of carports
-- ----------------------------
BEGIN;
INSERT INTO `carports` VALUES (1, 610, 390, 'Flat', 1, 17604.96, 1);
INSERT INTO `carports` VALUES (2, 780, 600, 'Peak', 2, 20757.05, 2);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of customers
-- ----------------------------
BEGIN;
INSERT INTO `customers` VALUES (1, 'Test Bruger 1', 'Testvej 1', 1111, 'By1', 11111111, 'test1@mail.dk');
INSERT INTO `customers` VALUES (2, 'Test Bruger 2', 'Testvej 2', 2222, 'By2', 22222222, 'test2@mail.dk');
COMMIT;

-- ----------------------------
-- Table structure for materiale
-- ----------------------------
DROP TABLE IF EXISTS `materiale`;
CREATE TABLE `materiale` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_danish_ci DEFAULT NULL,
  `price` decimal(8,2) DEFAULT NULL,
  `unit` enum('Stk','Pk','Rulle','Sæt') CHARACTER SET utf8 COLLATE utf8_danish_ci DEFAULT 'Stk',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of materiale
-- ----------------------------
BEGIN;
INSERT INTO `materiale` VALUES (1, '19x100 mm. trykimp. Brædt', 10.95, 'Stk');
INSERT INTO `materiale` VALUES (2, '25x150 mm. trykimp. Brædt', 44.95, 'Stk');
INSERT INTO `materiale` VALUES (3, '25x200 mm. trykimp. Brædt', 54.95, 'Stk');
INSERT INTO `materiale` VALUES (4, '25x50 mm. trykimp. Bræt', 8.95, 'Stk');
INSERT INTO `materiale` VALUES (5, '38x73 mm. Lægte ubh.', 10.95, 'Stk');
INSERT INTO `materiale` VALUES (6, '38x73 mm. taglægte T1', 10.95, 'Stk');
INSERT INTO `materiale` VALUES (7, '4.0 x 50 mm. beslagskruer 250 stk.', 425.00, 'Pk');
INSERT INTO `materiale` VALUES (8, '4.5 x 50 mm. Skruer 300 stk.', 510.00, 'Pk');
INSERT INTO `materiale` VALUES (9, '4.5 x 50 mm. Skruer 350 stk.', 595.00, 'Pk');
INSERT INTO `materiale` VALUES (10, '4.5 x 60 mm. Skruer 200 stk.', 150.00, 'Pk');
INSERT INTO `materiale` VALUES (11, '4.5 x 70 mm. Skruer 200 stk.', 200.00, 'Pk');
INSERT INTO `materiale` VALUES (12, '4.5 x 70 mm. Skruer 400 stk.', 400.00, 'Pk');
INSERT INTO `materiale` VALUES (13, '45x195 mm. spærtræ ubh.', 60.00, 'Stk');
INSERT INTO `materiale` VALUES (14, '45x95 mm. Reglar ub.', 22.00, 'Stk');
INSERT INTO `materiale` VALUES (15, '5.0 x 100 mm. skruer 100 stk.', 200.00, 'Pk');
INSERT INTO `materiale` VALUES (16, '5.0 x 40 mm. beslagskruer 250 stk.', 174.00, 'Pk');
INSERT INTO `materiale` VALUES (17, '97x97 mm. trykimp. Stolpe', 50.00, 'Stk');
INSERT INTO `materiale` VALUES (18, 'B & C Dobbelt -s sort', 1.00, 'Stk');
INSERT INTO `materiale` VALUES (19, 'B & C Rygsten sort', 1.00, 'Stk');
INSERT INTO `materiale` VALUES (20, 'B & C rygstensbeslag', 1.00, 'Stk');
INSERT INTO `materiale` VALUES (21, 'B & C tagstens bindere & nakkekroge', 1.00, 'Pk');
INSERT INTO `materiale` VALUES (22, 'B & C Toplægte holder', 59.95, 'Stk');
INSERT INTO `materiale` VALUES (23, 'bræddebolt 10 x 120 mm.', 20.00, 'Stk');
INSERT INTO `materiale` VALUES (24, 'firkantskiver 40x40x11mm', 18.00, 'Stk');
INSERT INTO `materiale` VALUES (25, 'fædigskåret (byg-selv spær)', 1.00, 'Sæt');
INSERT INTO `materiale` VALUES (26, 'hulbånd 20x1 mm. 10 mtr.', 209.00, 'Rulle');
INSERT INTO `materiale` VALUES (27, 'plastmo bundskruer 200 stk.', 409.00, 'Pk');
INSERT INTO `materiale` VALUES (28, 'Plastmo Ecolite blåtonet', 41.00, 'Stk');
INSERT INTO `materiale` VALUES (29, 'Stalddørsgreb 50x75', 189.00, 'Sæt');
INSERT INTO `materiale` VALUES (30, 'T-hængsel 390 mm.', 80.00, 'Stk');
INSERT INTO `materiale` VALUES (31, 'universal 190 mm højre', 1.00, 'Stk');
INSERT INTO `materiale` VALUES (32, 'universal 190 mm venstre', 1.00, 'Stk');
INSERT INTO `materiale` VALUES (33, 'vinkelbeslag', 1.00, 'Stk');
INSERT INTO `materiale` VALUES (34, 'vinkelbeslag 35', 24.95, 'Stk');
COMMIT;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `width` double DEFAULT NULL,
  `length` double DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `employee_id` int DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  `status` enum('New','Awaiting','Accepted','Paid','Completed','Cancelled') COLLATE utf8_danish_ci DEFAULT 'New',
  `carport_id` int DEFAULT NULL,
  `margin` decimal(4,2) DEFAULT NULL,
  `uuid` varchar(36) COLLATE utf8_danish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_orders_customers_1` (`customer_id`),
  KEY `fk_orders_carports_1` (`carport_id`),
  KEY `fk_orders_users_1` (`employee_id`),
  CONSTRAINT `fk_orders_carports_1` FOREIGN KEY (`carport_id`) REFERENCES `carports` (`id`),
  CONSTRAINT `fk_orders_customers_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  CONSTRAINT `fk_orders_users_1` FOREIGN KEY (`employee_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=202003 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of orders
-- ----------------------------
BEGIN;
INSERT INTO `orders` VALUES (202001, 390, 610, '2020-12-10 11:35:18', NULL, 1, 'New', 1, 30.00, 'af1ee13d-8f81-4aba-a04c-80cc1875b64e');
INSERT INTO `orders` VALUES (202002, 600, 780, '2020-12-10 11:36:09', 2, 2, 'Completed', 2, 30.00, '5503c670-c6dc-4655-8d05-2dcafa34fc05');
COMMIT;

-- ----------------------------
-- Table structure for partlists
-- ----------------------------
DROP TABLE IF EXISTS `partlists`;
CREATE TABLE `partlists` (
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of partlists
-- ----------------------------
BEGIN;
INSERT INTO `partlists` VALUES (1);
INSERT INTO `partlists` VALUES (2);
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
  KEY `fk_part_partlist` (`partlist_id`),
  CONSTRAINT `fk_part_partlist` FOREIGN KEY (`partlist_id`) REFERENCES `partlists` (`id`),
  CONSTRAINT `fk_part_usage_1` FOREIGN KEY (`usage_id`) REFERENCES `usage` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of parts
-- ----------------------------
BEGIN;
INSERT INTO `parts` VALUES (1, 'understernbrædder til for & bag ende', 1, 4, 390, 1);
INSERT INTO `parts` VALUES (2, 'understernbrædder til siderne', 2, 4, 610, 1);
INSERT INTO `parts` VALUES (3, 'oversternbrædder til forenden', 3, 2, 390, 1);
INSERT INTO `parts` VALUES (4, 'oversternbrædder til siderne', 4, 4, 610, 1);
INSERT INTO `parts` VALUES (5, 'Remme i sider, sadles ned i stolper', 8, 2, 390, 1);
INSERT INTO `parts` VALUES (6, 'Spær, monteres på rem', 10, 11, 390, 1);
INSERT INTO `parts` VALUES (7, 'Stolper nedgraves 90 cm. i jord', 11, 11, 300, 1);
INSERT INTO `parts` VALUES (8, 'vandbrædt på stern i sider', 13, 4, 330, 1);
INSERT INTO `parts` VALUES (9, 'vandbrædt på stern i forende', 14, 2, 275, 1);
INSERT INTO `parts` VALUES (10, 'tagplader monteres på spær', 15, 6, 390, 1);
INSERT INTO `parts` VALUES (11, 'tagplader monteres på spær', 16, 6, 275, 1);
INSERT INTO `parts` VALUES (12, 'Skruer til tagplader', 37, 2, 0, 1);
INSERT INTO `parts` VALUES (13, 'Til vindkryds på spær', 18, 2, 0, 1);
INSERT INTO `parts` VALUES (14, 'Til montering af spær på rem', 19, 12, 0, 1);
INSERT INTO `parts` VALUES (15, 'Til montering af spær på rem', 20, 12, 0, 1);
INSERT INTO `parts` VALUES (16, 'Til montering af stern&vandbrædt', 21, 1, 0, 1);
INSERT INTO `parts` VALUES (17, 'Til montering af universalbeslag + hulbånd', 22, 2, 0, 1);
INSERT INTO `parts` VALUES (18, 'Til montering af rem på stolper', 23, 10, 0, 1);
INSERT INTO `parts` VALUES (19, 'Til montering af rem på stolper', 24, 7, 0, 1);
INSERT INTO `parts` VALUES (20, 'til z på bagside af dør', 5, 1, 420, 1);
INSERT INTO `parts` VALUES (21, 'løsholter til skur gavle', 6, 9, 270, 1);
INSERT INTO `parts` VALUES (22, 'løsholter til skur sider', 7, 2, 240, 1);
INSERT INTO `parts` VALUES (23, 'Remme i sider, sadles ned i stolper ( skur del, deles)', 9, 1, 210, 1);
INSERT INTO `parts` VALUES (24, 'til beklædning af skur 1 på 2', 12, 100, 210, 1);
INSERT INTO `parts` VALUES (25, 'til montering af yderste beklædning', 25, 2, 0, 1);
INSERT INTO `parts` VALUES (26, 'til montering af inderste beklædning', 26, 2, 0, 1);
INSERT INTO `parts` VALUES (27, 'Til lås på dør i skur', 27, 1, 0, 1);
INSERT INTO `parts` VALUES (28, 'Til skurdør', 28, 2, 0, 1);
INSERT INTO `parts` VALUES (29, 'Til montering af løsholter i skur', 29, 20, 0, 1);
INSERT INTO `parts` VALUES (30, 'Vindskeder på rejsning', 44, 2, 720, 2);
INSERT INTO `parts` VALUES (31, 'Sternbrædder til siderne Carport del', 40, 2, 650, 2);
INSERT INTO `parts` VALUES (32, 'byg-selv spær (skal samles) 8 stk.', 31, 8, 600, 2);
INSERT INTO `parts` VALUES (33, 'Stolper nedgraves 90 cm. i jord', 11, 11, 300, 2);
INSERT INTO `parts` VALUES (34, 'Remme i sider, sadles ned i stolper Carport del', 8, 2, 720, 2);
INSERT INTO `parts` VALUES (35, 'Vand bræt på vindskeder', 45, 2, 630, 2);
INSERT INTO `parts` VALUES (36, 'beklædning af gavle 1 på 2', 46, 41, 240, 2);
INSERT INTO `parts` VALUES (37, 'til montering oven på tagfodslægte', 38, 3, 1200, 2);
INSERT INTO `parts` VALUES (38, 'til montering på spær, 7 rækker lægter på hver skiftevis 1 hel & 1 halv lægte', 35, 38, 630, 2);
INSERT INTO `parts` VALUES (39, 'toplægte til montering af rygsten lægges i toplægte holder', 42, 2, 700, 2);
INSERT INTO `parts` VALUES (40, 'monteres på taglægter 6 rækker af 24 sten på hver side af taget', 36, 540, 0, 2);
INSERT INTO `parts` VALUES (41, 'monteres på toplægte med medfølgende beslag se tagstens vejledning', 41, 39, 0, 2);
INSERT INTO `parts` VALUES (42, 'monteres på toppen af spæret (til toplægte)', 34, 9, 0, 2);
INSERT INTO `parts` VALUES (43, 'Til montering af rygsten', 30, 39, 0, 2);
INSERT INTO `parts` VALUES (44, 'til montering af tagsten, alle ydersten + hver anden fastgøres', 39, 2, 0, 2);
INSERT INTO `parts` VALUES (45, 'Til montering af spær på rem', 19, 9, 0, 2);
INSERT INTO `parts` VALUES (46, 'Til montering af spær på rem', 20, 9, 0, 2);
INSERT INTO `parts` VALUES (47, 'Til montering af Stern, vindskeder, vindkryds & vand bræt', 21, 1, 0, 2);
INSERT INTO `parts` VALUES (48, 'Til montering af universalbeslag + toplægte', 22, 1, 0, 2);
INSERT INTO `parts` VALUES (49, 'til taglægter', 37, 4, 0, 2);
INSERT INTO `parts` VALUES (50, 'Til montering af rem på stolper', 23, 24, 0, 2);
INSERT INTO `parts` VALUES (51, 'Til montering af rem på stolper', 24, 24, 0, 2);
INSERT INTO `parts` VALUES (52, 'Sternbrædder til siderne Skur del ( deles )', 14, 1, 590, 2);
INSERT INTO `parts` VALUES (53, 'Remme i sider, sadles ned i stolper ( skur del, deles)', 9, 1, 720, 2);
INSERT INTO `parts` VALUES (54, 'Løsholter i siderne af skur', 7, 6, 240, 2);
INSERT INTO `parts` VALUES (55, 'Løsholter i gavle af skur', 6, 6, 600, 2);
INSERT INTO `parts` VALUES (56, 'til beklædning af skur 1 på 2', 12, 263, 225, 2);
INSERT INTO `parts` VALUES (57, 'til z på bagside af dør', 5, 1, 630, 2);
INSERT INTO `parts` VALUES (58, 'Til lås på dør i skur', 27, 1, 0, 2);
INSERT INTO `parts` VALUES (59, 'Til skurdør', 28, 2, 0, 2);
INSERT INTO `parts` VALUES (60, 'Til montering af løsholter i skur', 29, 18, 0, 2);
INSERT INTO `parts` VALUES (61, 'til montering af yderste beklædning', 33, 7, 0, 2);
INSERT INTO `parts` VALUES (62, 'til montering af inderste beklædning', 32, 4, 0, 2);
COMMIT;

-- ----------------------------
-- Table structure for properties
-- ----------------------------
DROP TABLE IF EXISTS `properties`;
CREATE TABLE `properties` (
  `name` varchar(255) COLLATE utf8_danish_ci NOT NULL,
  `value` varchar(255) COLLATE utf8_danish_ci NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of properties
-- ----------------------------
BEGIN;
INSERT INTO `properties` VALUES ('version', '0');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of sheds
-- ----------------------------
BEGIN;
INSERT INTO `sheds` VALUES (1, 210, 315);
INSERT INTO `sheds` VALUES (2, 225, 260);
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES (1, 'Admin user', 'admin@admin.dk', 'Admin', 0xA94D392CA48DB1C0E32AD7A760CDEBF1, 0x3024A3A2A0DE7366E575919B9BD4A28FA50EA2907957DAFF6E905840A9981E33, 1);
INSERT INTO `users` VALUES (2, 'Sælger', 'sales@sales.dk', 'Employee', 0xDAA13E614966F63AA4FDBFAD6FA45FCF, 0xE2CB3108EC80A8182F790D1D259B4CC8C2FA0B38C8A57E69E7DF4C57FC480932, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
