DROP USER IF EXISTS 'fog'@'localhost';
DROP DATABASE IF EXISTS fog;

CREATE DATABASE IF NOT EXISTS fog;
CREATE USER IF NOT EXISTS 'fog'@'localhost';
grant all privileges on fog.* TO 'fog'@'localhost';


