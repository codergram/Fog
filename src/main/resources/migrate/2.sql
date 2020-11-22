CREATE TABLE IF NOT EXISTS kunde (
kunde_id INT NOT NULL AUTO_INCREMENT,
kunde_navn VARCHAR(45) NOT NULL,
kunde_adresse VARCHAR(45) NOT NULL,
kunde_post INT NOT NULL,
kunde_by VARCHAR(45) NOT NULL,
kunde_tlf INT NOT NULL,
kunde_email VARCHAR(45) NOT NULL,
PRIMARY KEY (kunde_id)
);

CREATE TABLE IF NOT EXISTS carport (
carport_id INT NOT NULL AUTO_INCREMENT,
carport_type VARCHAR(45) NOT NULL,
carport_længde INT NOT NULL,
carport_højde INT NOT NULL,
PRIMARY KEY (carport_id)
);

CREATE TABLE IF NOT EXISTS skur (
skur_id INT NOT NULL AUTO_INCREMENT,
skur_længde INT NOT NULL,
skur_højde INT NOT NULL,
PRIMARY KEY (skur_id)
);

CREATE TABLE IF NOT EXISTS bestilling (
bes_id INT NOT NULL AUTO_INCREMENT,
bes_tid VARCHAR(45) NOT NULL,
bes_status VARCHAR(45) NOT NULL DEFAULT 'afventer',
bes_aktiv TINYINT NOT NULL DEFAULT 1,
bes_subtotal DECIMAL(8,2) NOT NULL DEFAULT 0,
bes_moms DECIMAL(8,2) NOT NULL DEFAULT 25,
bes_total DECIMAL(8,2) NOT NULL DEFAULT 0,
fk_kunde_id INT NOT NULL,
fk_carport_id INT NOT NULL,
fk_skur_id INT NOT NULL,
PRIMARY KEY (bes_id),
FOREIGN KEY (fk_kunde_id) REFERENCES kunde(kunde_id),
FOREIGN KEY (fk_carport_id) REFERENCES carport(carport_id),
FOREIGN KEY (fk_skur_id) REFERENCES skur(skur_id)
);

CREATE TABLE IF NOT EXISTS materiel (
mat_id INT NOT NULL AUTO_INCREMENT,
mat_navn VARCHAR(45) NOT NULL,
mat_pris DECIMAL(8,2) NOT NULL DEFAULT 0,
PRIMARY KEY (mat_id)
);

CREATE TABLE IF NOT EXISTS stykliste (
styk_id INT NOT NULL AUTO_INCREMENT,
fk_mat_id INT NOT NULL,
styk_antal DECIMAL(8,2) NOT NULL DEFAULT 0,
styk_pris DECIMAL(8,2) NOT NULL DEFAULT 0,
styk_subtotal DECIMAL(8,2) NOT NULL DEFAULT 0,
fk_bes_id INT NOT NULL,
PRIMARY KEY (styk_id),
FOREIGN KEY (fk_mat_id) REFERENCES materiel(mat_id),
FOREIGN KEY (fk_bes_id) REFERENCES bestilling(bes_id)
);

-- Husk at update jeres database version.
UPDATE properties
SET value = '2'
WHERE name = 'version';
