CREATE TABLE IF NOT EXISTS længde (
længde_id INT NOT NULL AUTO_INCREMENT,
længde_mål VARCHAR(45) NOT NULL,
PRIMARY KEY (længde_id)
);

CREATE TABLE IF NOT EXISTS bredde (
bredde_id INT NOT NULL AUTO_INCREMENT,
bredde_mål VARCHAR(45) NOT NULL,
PRIMARY KEY (bredde_id)
);

-- Husk at update jeres database version.
UPDATE properties
SET value = '4'
WHERE name = 'version';
