DROP DATABASE IF EXISTS fogtest;
CREATE DATABASE fogtest;

USE fogtest;

/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

# CREATE TABLE properties
# (
#     name  VARCHAR(255) PRIMARY KEY,
#     value VARCHAR(255) NOT NULL
# );
#
# INSERT INTO properties (name, value)
# VALUES ('version', '0');

DROP TABLE IF EXISTS `properties`;
CREATE TABLE `properties`
(
    `name`  VARCHAR(255) PRIMARY KEY,
    `value` VARCHAR(255) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_danish_ci;

-- ----------------------------
-- Records of properties
-- ----------------------------
BEGIN;
INSERT INTO `properties`
VALUES ('version', '0');
COMMIT;