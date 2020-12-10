DROP DATABASE IF EXISTS fogtest;
CREATE DATABASE fogtest;

USE fogtest;

# CREATE TABLE properties
# (
#     name  VARCHAR(255) PRIMARY KEY,
#     value VARCHAR(255) NOT NULL
# );
#
# INSERT INTO properties (name, value)
# VALUES ('version', '0');

DROP TABLE IF EXISTS `properties`;
CREATE TABLE `properties` (
                              `name` VARCHAR(255) PRIMARY KEY,
                              `value` VARCHAR(255) NOT NULL,
                              `vat` decimal(4,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_danish_ci;

-- ----------------------------
-- Records of properties
-- ----------------------------
BEGIN;
INSERT INTO `properties` VALUES ('version', '0', 25.00);
COMMIT;