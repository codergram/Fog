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

 Date: 09/12/2020 13:49:57
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of carports
-- ----------------------------
BEGIN;
INSERT INTO `carports` VALUES (12, 780, 600, 'Flat', 11, 27769.26, 12);
INSERT INTO `carports` VALUES (13, 580, 300, 'Flat', NULL, 9857.44, 13);
INSERT INTO `carports` VALUES (14, 640, 370, 'Flat', 12, 17464.12, 14);
INSERT INTO `carports` VALUES (15, 630, 350, 'Flat', 13, 16923.90, 15);
INSERT INTO `carports` VALUES (16, 640, 360, 'Peak', 14, 13123.93, 16);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of customers
-- ----------------------------
BEGIN;
INSERT INTO `customers` VALUES (3, 'Emil test', 'Vejnavn 123', 1234, 'Bynavn', 12345678, 'emil@test.dk');
INSERT INTO `customers` VALUES (4, 'Kurt Verner', 'Ingenvej 666', 1234, 'VarmBy', 12345678, 'cph-en93@cphbusiness.dk');
INSERT INTO `customers` VALUES (5, 'John smith', 'Kedeligvej 12', 2200, 'Nørrebronx', 12345678, 'john@smith.com');
INSERT INTO `customers` VALUES (6, 'Arnold', 'vejnavn 1234', 2100, 'Speltbro', 12345678, 'arnold@hej.dk');
INSERT INTO `customers` VALUES (7, 'test navn', 'test addresse', 1234, 'by', 12121212, 'test@mail.dk');
INSERT INTO `customers` VALUES (8, 'arik', 'vejnavn 234', 1234, 'bynavn', 12345678, 'arik@arik.dk');
INSERT INTO `customers` VALUES (9, 'Emil', 'Testvej 1234', 9999, 'Bynavn', 60146057, 'emil@evsn.dk');
INSERT INTO `customers` VALUES (10, 'Johnny madsen', 'Rygevej 69', 1234, 'Fanø', 12345678, 'emil@vta.dk');
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
INSERT INTO `materiale` VALUES (5, '38x73 mm. Lægte ubh.', 1.00, 'Stk');
INSERT INTO `materiale` VALUES (6, '38x73 mm. taglægte T1', 1.00, 'Stk');
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
INSERT INTO `materiale` VALUES (22, 'B & C Toplægte holder', 1.00, 'Stk');
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
INSERT INTO `materiale` VALUES (34, 'vinkelbeslag 35', 1.00, 'Stk');
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
) ENGINE=InnoDB AUTO_INCREMENT=202005 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of orders
-- ----------------------------
BEGIN;
INSERT INTO `orders` VALUES (202001, 600, 780, '2020-12-07 20:23:19', 1, 4, 'Completed', 12, 15.24, '10f96d5b-5e3a-4f85-a0cd-7ad224071769');
INSERT INTO `orders` VALUES (202002, 300, 580, '2020-12-08 13:25:09', 1, 4, 'New', 13, 30.00, '09e14312-29b1-4d5b-8295-68a315c5224d');
INSERT INTO `orders` VALUES (202003, 350, 630, '2020-12-09 13:30:30', NULL, 9, 'New', 15, 30.00, '87f27eb6-6b8c-4c11-a1f9-c9fd293bf783');
INSERT INTO `orders` VALUES (202004, 360, 640, '2020-12-09 13:39:34', 1, 10, 'New', 16, 30.00, '1cc7b4e1-2c46-43e3-bc24-5ce6eb8f65c5');
COMMIT;

-- ----------------------------
-- Table structure for partlists
-- ----------------------------
DROP TABLE IF EXISTS `partlists`;
CREATE TABLE `partlists` (
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of partlists
-- ----------------------------
BEGIN;
INSERT INTO `partlists` VALUES (12);
INSERT INTO `partlists` VALUES (13);
INSERT INTO `partlists` VALUES (14);
INSERT INTO `partlists` VALUES (15);
INSERT INTO `partlists` VALUES (16);
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
) ENGINE=InnoDB AUTO_INCREMENT=664 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of parts
-- ----------------------------
BEGIN;
INSERT INTO `parts` VALUES (525, 'understernbrædder til for & bag ende', 1, 4, 600, 12);
INSERT INTO `parts` VALUES (526, 'understernbrædder til siderne', 2, 4, 780, 12);
INSERT INTO `parts` VALUES (527, 'oversternbrædder til forenden', 3, 2, 600, 12);
INSERT INTO `parts` VALUES (528, 'oversternbrædder til siderne', 4, 4, 780, 12);
INSERT INTO `parts` VALUES (529, 'Remme i sider, sadles ned i stolper', 8, 2, 600, 12);
INSERT INTO `parts` VALUES (530, 'Spær, monteres på rem', 10, 14, 600, 12);
INSERT INTO `parts` VALUES (531, 'Stolper nedgraves 90 cm. i jord', 11, 11, 300, 12);
INSERT INTO `parts` VALUES (532, 'vandbrædt på stern i sider', 13, 4, 540, 12);
INSERT INTO `parts` VALUES (533, 'vandbrædt på stern i forende', 14, 2, 360, 12);
INSERT INTO `parts` VALUES (534, 'tagplader monteres på spær', 15, 6, 600, 12);
INSERT INTO `parts` VALUES (535, 'tagplader monteres på spær', 16, 6, 360, 12);
INSERT INTO `parts` VALUES (536, 'Skruer til tagplader', 37, 4, 0, 12);
INSERT INTO `parts` VALUES (537, 'Til vindkryds på spær', 18, 3, 0, 12);
INSERT INTO `parts` VALUES (538, 'Til montering af spær på rem', 19, 15, 0, 12);
INSERT INTO `parts` VALUES (539, 'Til montering af spær på rem', 20, 15, 0, 12);
INSERT INTO `parts` VALUES (540, 'Til montering af stern&vandbrædt', 21, 1, 0, 12);
INSERT INTO `parts` VALUES (541, 'Til montering af universalbeslag + hulbånd', 22, 4, 0, 12);
INSERT INTO `parts` VALUES (542, 'Til montering af rem på stolper', 23, 19, 0, 12);
INSERT INTO `parts` VALUES (543, 'Til montering af rem på stolper', 24, 12, 0, 12);
INSERT INTO `parts` VALUES (544, 'til z på bagside af dør', 5, 1, 420, 12);
INSERT INTO `parts` VALUES (545, 'løsholter til skur gavle', 6, 12, 270, 12);
INSERT INTO `parts` VALUES (546, 'løsholter til skur sider', 7, 4, 240, 12);
INSERT INTO `parts` VALUES (547, 'Remme i sider, sadles ned i stolper ( skur del, deles)', 9, 1, 420, 12);
INSERT INTO `parts` VALUES (548, 'til beklædning af skur 1 på 2', 12, 197, 210, 12);
INSERT INTO `parts` VALUES (549, 'til montering af yderste beklædning', 25, 3, 0, 12);
INSERT INTO `parts` VALUES (550, 'til montering af inderste beklædning', 26, 3, 0, 12);
INSERT INTO `parts` VALUES (551, 'Til lås på dør i skur', 27, 1, 0, 12);
INSERT INTO `parts` VALUES (552, 'Til skurdør', 28, 2, 0, 12);
INSERT INTO `parts` VALUES (553, 'Til montering af løsholter i skur', 29, 15, 0, 12);
INSERT INTO `parts` VALUES (554, 'understernbrædder til for & bag ende', 1, 4, 300, 13);
INSERT INTO `parts` VALUES (555, 'understernbrædder til siderne', 2, 4, 580, 13);
INSERT INTO `parts` VALUES (556, 'oversternbrædder til forenden', 3, 2, 300, 13);
INSERT INTO `parts` VALUES (557, 'oversternbrædder til siderne', 4, 4, 580, 13);
INSERT INTO `parts` VALUES (558, 'Remme i sider, sadles ned i stolper', 8, 2, 300, 13);
INSERT INTO `parts` VALUES (559, 'Spær, monteres på rem', 10, 10, 300, 13);
INSERT INTO `parts` VALUES (560, 'Stolper nedgraves 90 cm. i jord', 11, 7, 300, 13);
INSERT INTO `parts` VALUES (561, 'vandbrædt på stern i sider', 13, 4, 240, 13);
INSERT INTO `parts` VALUES (562, 'vandbrædt på stern i forende', 14, 2, 260, 13);
INSERT INTO `parts` VALUES (563, 'tagplader monteres på spær', 15, 6, 300, 13);
INSERT INTO `parts` VALUES (564, 'tagplader monteres på spær', 16, 6, 260, 13);
INSERT INTO `parts` VALUES (565, 'Skruer til tagplader', 37, 2, 0, 13);
INSERT INTO `parts` VALUES (566, 'Til vindkryds på spær', 18, 1, 0, 13);
INSERT INTO `parts` VALUES (567, 'Til montering af spær på rem', 19, 11, 0, 13);
INSERT INTO `parts` VALUES (568, 'Til montering af spær på rem', 20, 11, 0, 13);
INSERT INTO `parts` VALUES (569, 'Til montering af stern&vandbrædt', 21, 1, 0, 13);
INSERT INTO `parts` VALUES (570, 'Til montering af universalbeslag + hulbånd', 22, 2, 0, 13);
INSERT INTO `parts` VALUES (571, 'Til montering af rem på stolper', 23, 7, 0, 13);
INSERT INTO `parts` VALUES (572, 'Til montering af rem på stolper', 24, 5, 0, 13);
INSERT INTO `parts` VALUES (573, 'understernbrædder til for & bag ende', 1, 4, 370, 14);
INSERT INTO `parts` VALUES (574, 'understernbrædder til siderne', 2, 4, 640, 14);
INSERT INTO `parts` VALUES (575, 'oversternbrædder til forenden', 3, 2, 370, 14);
INSERT INTO `parts` VALUES (576, 'oversternbrædder til siderne', 4, 4, 640, 14);
INSERT INTO `parts` VALUES (577, 'Remme i sider, sadles ned i stolper', 8, 2, 370, 14);
INSERT INTO `parts` VALUES (578, 'Spær, monteres på rem', 10, 11, 370, 14);
INSERT INTO `parts` VALUES (579, 'Stolper nedgraves 90 cm. i jord', 11, 11, 300, 14);
INSERT INTO `parts` VALUES (580, 'vandbrædt på stern i sider', 13, 4, 310, 14);
INSERT INTO `parts` VALUES (581, 'vandbrædt på stern i forende', 14, 2, 290, 14);
INSERT INTO `parts` VALUES (582, 'tagplader monteres på spær', 15, 6, 370, 14);
INSERT INTO `parts` VALUES (583, 'tagplader monteres på spær', 16, 6, 290, 14);
INSERT INTO `parts` VALUES (584, 'Skruer til tagplader', 37, 2, 0, 14);
INSERT INTO `parts` VALUES (585, 'Til vindkryds på spær', 18, 2, 0, 14);
INSERT INTO `parts` VALUES (586, 'Til montering af spær på rem', 19, 12, 0, 14);
INSERT INTO `parts` VALUES (587, 'Til montering af spær på rem', 20, 12, 0, 14);
INSERT INTO `parts` VALUES (588, 'Til montering af stern&vandbrædt', 21, 1, 0, 14);
INSERT INTO `parts` VALUES (589, 'Til montering af universalbeslag + hulbånd', 22, 2, 0, 14);
INSERT INTO `parts` VALUES (590, 'Til montering af rem på stolper', 23, 10, 0, 14);
INSERT INTO `parts` VALUES (591, 'Til montering af rem på stolper', 24, 7, 0, 14);
INSERT INTO `parts` VALUES (592, 'til z på bagside af dør', 5, 1, 420, 14);
INSERT INTO `parts` VALUES (593, 'løsholter til skur gavle', 6, 9, 270, 14);
INSERT INTO `parts` VALUES (594, 'løsholter til skur sider', 7, 2, 240, 14);
INSERT INTO `parts` VALUES (595, 'Remme i sider, sadles ned i stolper ( skur del, deles)', 9, 1, 190, 14);
INSERT INTO `parts` VALUES (596, 'til beklædning af skur 1 på 2', 12, 100, 210, 14);
INSERT INTO `parts` VALUES (597, 'til montering af yderste beklædning', 25, 2, 0, 14);
INSERT INTO `parts` VALUES (598, 'til montering af inderste beklædning', 26, 2, 0, 14);
INSERT INTO `parts` VALUES (599, 'Til lås på dør i skur', 27, 1, 0, 14);
INSERT INTO `parts` VALUES (600, 'Til skurdør', 28, 2, 0, 14);
INSERT INTO `parts` VALUES (601, 'Til montering af løsholter i skur', 29, 7, 0, 14);
INSERT INTO `parts` VALUES (602, 'understernbrædder til for & bag ende', 1, 4, 350, 15);
INSERT INTO `parts` VALUES (603, 'understernbrædder til siderne', 2, 4, 630, 15);
INSERT INTO `parts` VALUES (604, 'oversternbrædder til forenden', 3, 2, 350, 15);
INSERT INTO `parts` VALUES (605, 'oversternbrædder til siderne', 4, 4, 630, 15);
INSERT INTO `parts` VALUES (606, 'Remme i sider, sadles ned i stolper', 8, 2, 350, 15);
INSERT INTO `parts` VALUES (607, 'Spær, monteres på rem', 10, 11, 350, 15);
INSERT INTO `parts` VALUES (608, 'Stolper nedgraves 90 cm. i jord', 11, 11, 300, 15);
INSERT INTO `parts` VALUES (609, 'vandbrædt på stern i sider', 13, 4, 290, 15);
INSERT INTO `parts` VALUES (610, 'vandbrædt på stern i forende', 14, 2, 285, 15);
INSERT INTO `parts` VALUES (611, 'tagplader monteres på spær', 15, 6, 350, 15);
INSERT INTO `parts` VALUES (612, 'tagplader monteres på spær', 16, 6, 285, 15);
INSERT INTO `parts` VALUES (613, 'Skruer til tagplader', 37, 2, 0, 15);
INSERT INTO `parts` VALUES (614, 'Til vindkryds på spær', 18, 2, 0, 15);
INSERT INTO `parts` VALUES (615, 'Til montering af spær på rem', 19, 12, 0, 15);
INSERT INTO `parts` VALUES (616, 'Til montering af spær på rem', 20, 12, 0, 15);
INSERT INTO `parts` VALUES (617, 'Til montering af stern&vandbrædt', 21, 1, 0, 15);
INSERT INTO `parts` VALUES (618, 'Til montering af universalbeslag + hulbånd', 22, 2, 0, 15);
INSERT INTO `parts` VALUES (619, 'Til montering af rem på stolper', 23, 9, 0, 15);
INSERT INTO `parts` VALUES (620, 'Til montering af rem på stolper', 24, 6, 0, 15);
INSERT INTO `parts` VALUES (621, 'til z på bagside af dør', 5, 1, 420, 15);
INSERT INTO `parts` VALUES (622, 'løsholter til skur gavle', 6, 9, 270, 15);
INSERT INTO `parts` VALUES (623, 'løsholter til skur sider', 7, 2, 240, 15);
INSERT INTO `parts` VALUES (624, 'Remme i sider, sadles ned i stolper ( skur del, deles)', 9, 1, 170, 15);
INSERT INTO `parts` VALUES (625, 'til beklædning af skur 1 på 2', 12, 93, 210, 15);
INSERT INTO `parts` VALUES (626, 'til montering af yderste beklædning', 25, 2, 0, 15);
INSERT INTO `parts` VALUES (627, 'til montering af inderste beklædning', 26, 2, 0, 15);
INSERT INTO `parts` VALUES (628, 'Til lås på dør i skur', 27, 1, 0, 15);
INSERT INTO `parts` VALUES (629, 'Til skurdør', 28, 2, 0, 15);
INSERT INTO `parts` VALUES (630, 'Til montering af løsholter i skur', 29, 7, 0, 15);
INSERT INTO `parts` VALUES (631, 'Vindskeder på rejsning', 44, 2, 480, 16);
INSERT INTO `parts` VALUES (632, 'Sternbrædder til siderne Carport del', 40, 2, 510, 16);
INSERT INTO `parts` VALUES (633, 'byg-selv spær (skal samles) 7 stk.', 31, 7, 360, 16);
INSERT INTO `parts` VALUES (634, 'Stolper nedgraves 90 cm. i jord', 11, 11, 300, 16);
INSERT INTO `parts` VALUES (635, 'Remme i sider, sadles ned i stolper Carport del', 8, 2, 480, 16);
INSERT INTO `parts` VALUES (636, 'Vand bræt på vindskeder', 45, 2, 490, 16);
INSERT INTO `parts` VALUES (637, 'beklædning af gavle 1 på 2', 46, 33, 240, 16);
INSERT INTO `parts` VALUES (638, 'til montering oven på tagfodslægte', 38, 3, 720, 16);
INSERT INTO `parts` VALUES (639, 'til montering på spær, 7 rækker lægter på hver skiftevis 1 hel & 1 halv lægte', 35, 18, 490, 16);
INSERT INTO `parts` VALUES (640, 'toplægte til montering af rygsten lægges i toplægte holder', 42, 2, 460, 16);
INSERT INTO `parts` VALUES (641, 'monteres på taglægter 6 rækker af 24 sten på hver side af taget', 36, 300, 0, 16);
INSERT INTO `parts` VALUES (642, 'monteres på toplægte med medfølgende beslag se tagstens vejledning', 41, 19, 0, 16);
INSERT INTO `parts` VALUES (643, 'monteres på toppen af spæret (til toplægte)', 34, 8, 0, 16);
INSERT INTO `parts` VALUES (644, 'Til montering af rygsten', 30, 19, 0, 16);
INSERT INTO `parts` VALUES (645, 'til montering af tagsten, alle ydersten + hver anden fastgøres', 39, 2, 0, 16);
INSERT INTO `parts` VALUES (646, 'Til montering af spær på rem', 19, 8, 0, 16);
INSERT INTO `parts` VALUES (647, 'Til montering af spær på rem', 20, 8, 0, 16);
INSERT INTO `parts` VALUES (648, 'Til montering af Stern, vindskeder, vindkryds & vand bræt', 21, 1, 0, 16);
INSERT INTO `parts` VALUES (649, 'Til montering af universalbeslag + toplægte', 22, 1, 0, 16);
INSERT INTO `parts` VALUES (650, 'til taglægter', 37, 2, 0, 16);
INSERT INTO `parts` VALUES (651, 'Til montering af rem på stolper', 23, 21, 0, 16);
INSERT INTO `parts` VALUES (652, 'Til montering af rem på stolper', 24, 21, 0, 16);
INSERT INTO `parts` VALUES (653, 'Sternbrædder til siderne Skur del ( deles )', 14, 1, 450, 16);
INSERT INTO `parts` VALUES (654, 'Remme i sider, sadles ned i stolper ( skur del, deles)', 9, 1, 480, 16);
INSERT INTO `parts` VALUES (655, 'Løsholter i siderne af skur', 7, 4, 240, 16);
INSERT INTO `parts` VALUES (656, 'Løsholter i gavle af skur', 6, 6, 360, 16);
INSERT INTO `parts` VALUES (657, 'til beklædning af skur 1 på 2', 12, 130, 225, 16);
INSERT INTO `parts` VALUES (658, 'til z på bagside af dør', 5, 1, 490, 16);
INSERT INTO `parts` VALUES (659, 'Til lås på dør i skur', 27, 1, 0, 16);
INSERT INTO `parts` VALUES (660, 'Til skurdør', 28, 2, 0, 16);
INSERT INTO `parts` VALUES (661, 'Til montering af løsholter i skur', 29, 10, 0, 16);
INSERT INTO `parts` VALUES (662, 'til montering af yderste beklædning', 33, 4, 0, 16);
INSERT INTO `parts` VALUES (663, 'til montering af inderste beklædning', 32, 2, 0, 16);
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of sheds
-- ----------------------------
BEGIN;
INSERT INTO `sheds` VALUES (11, 210, 225);
INSERT INTO `sheds` VALUES (12, 210, 110);
INSERT INTO `sheds` VALUES (13, 210, 100);
INSERT INTO `sheds` VALUES (14, 225, 140);
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

/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES (1, 'Admin user', 'admin@admin.dk', 'Admin', 0xA94D392CA48DB1C0E32AD7A760CDEBF1, 0x3024A3A2A0DE7366E575919B9BD4A28FA50EA2907957DAFF6E905840A9981E33, 1);
INSERT INTO `users` VALUES (4, 'test', 'test@test.dk', 'Employee', 0x7FF8F8F4D436BDFE960B25D2FC0AE70C, 0x2198AAB853A75F9EAA8018A32A024E5E529FF484F932AF2DB30C1540E2F5A52F, 1);
INSERT INTO `users` VALUES (5, 'arik', 'arik@saelger.dk', 'Employee', 0xDD261A088173ABB151343FF6BC24FCBC, 0xE31E987D225BBE0FEF1570BA3572CD62026AEE9D287DCD826646A24825935151, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
