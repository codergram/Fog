/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

DROP USER IF EXISTS 'fog'@'localhost';
DROP DATABASE IF EXISTS fog;

CREATE DATABASE IF NOT EXISTS fog;
CREATE USER IF NOT EXISTS 'fog'@'localhost' IDENTIFIED BY 'codergram';
grant all privileges on fog.* TO 'fog'@'localhost';


