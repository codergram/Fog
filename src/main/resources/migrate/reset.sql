DROP USER IF EXISTS 'fog'@'localhost';
DROP DATABASE IF EXISTS fog;

CREATE DATABASE IF NOT EXISTS fog;
CREATE USER IF NOT EXISTS 'fog'@'localhost' IDENTIFIED BY 'codergram';
grant all privileges on fog.* TO 'fog'@'localhost';


