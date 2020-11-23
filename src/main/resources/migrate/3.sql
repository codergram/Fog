DROP TABLE IF EXISTS styklistetræ;
DROP TABLE IF EXISTS styklistebeslagskruer;
DROP TABLE IF EXISTS beslagskruer;
DROP TABLE IF EXISTS træ;


CREATE TABLE IF NOT EXISTS træ (
træ_id INT NOT NULL AUTO_INCREMENT,
træ_navn VARCHAR(45) NOT NULL,
træ_mål INT NOT NULL DEFAULT 0,
træ_pris DECIMAL(8,2) NOT NULL DEFAULT 0,
PRIMARY KEY (træ_id)
);

CREATE TABLE IF NOT EXISTS beslagskruer (
bes_id INT NOT NULL AUTO_INCREMENT,
bes_navn VARCHAR(45) NOT NULL,
bes_pris DECIMAL(8,2) NOT NULL DEFAULT 0,
PRIMARY KEY (bes_id)
);

CREATE TABLE IF NOT EXISTS styklistetræ (
st_id INT NOT NULL AUTO_INCREMENT,
fk_træ_id INT NOT NULL,
st_bes VARCHAR(255) NOT NULL,
st_antal DECIMAL(8,2) NOT NULL DEFAULT 0,
st_pris DECIMAL(8,2) NOT NULL DEFAULT 0,
st_subtotal DECIMAL(8,2) NOT NULL DEFAULT 0,
fk_bestilling_id INT NOT NULL,
PRIMARY KEY (st_id),
FOREIGN KEY (fk_træ_id) REFERENCES træ(træ_id),
FOREIGN KEY (fk_bestilling_id) REFERENCES bestilling(bes_id)
);

CREATE TABLE IF NOT EXISTS styklistebeslagskruer (
sb_id INT NOT NULL AUTO_INCREMENT,
fk_bes_id INT NOT NULL,
sb_bes VARCHAR(255) NOT NULL,
sb_antal DECIMAL(8,2) NOT NULL DEFAULT 0,
sb_pris DECIMAL(8,2) NOT NULL DEFAULT 0,
sb_subtotal DECIMAL(8,2) NOT NULL DEFAULT 0,
fk_bestilling_id INT NOT NULL,
PRIMARY KEY (sb_id),
FOREIGN KEY (fk_bes_id) REFERENCES beslagskruer(bes_id),
FOREIGN KEY (fk_bestilling_id) REFERENCES bestilling(bes_id)
);

INSERT INTO træ
    (træ_navn, træ_mål, træ_pris)
VALUES
    ('25x150 mm. trykimp. Bræt', 480, 0),
    ('25x150 mm. trykimp. Bræt', 600, 0),
    ('25x150 mm. trykimp. Bræt', 540, 0),
    ('fædigskåret (byg-selv spær)', 360, 0),
    ('97x97 mm. trykimp. Stolpe', 300, 0),
    ('45x195 spærtræ ubh.', 480, 0),
    ('45x95 Reglar ubh.', 240, 0),
    ('45x95 Reglar ubh.', 360, 0),
    ('19x100 mm. trykimp.', 480, 0),
    ('19x100 mm. trykimp.', 240, 0),
    ('19x100 mm. trykimp.', 210, 0),
    ('25x50 mm. trykimp. Bræt', 540, 0),
    ('38x73 mm. taglægte T1', 540, 0),
    ('38x73 mm. taglægte T1', 420, 0),
    ('25x200 mm. trykimp. Brædt', 360, 0),
    ('25x200 mm. trykimp. Brædt', 540, 0),
    ('25x125mm. trykimp. Brædt', 360, 0),
    ('25x125mm. trykimp. Brædt', 540, 0),
    ('38x73 mm. Lægte ubh.', 420, 0),
    ('45x95 Reglar ubh.', 270, 0),
    ('45x195 spærtræ ubh.', 600, 0),
    ('19x100 mm. trykimp.', 360, 0),
    ('19x100 mm. trykimp.', 540, 0),
    ('Plastmo Ecolite blåtonet', 360, 0),
    ('Plastmo Ecolite blåtonet', 600, 0);


INSERT INTO beslagskruer
(bes_navn, bes_pris)
VALUES
('B & C Dobbelt -s sort', 0),
('B & C Rygsten sort', 0),
('B & C Toplægte holder', 0),
('B & C rygstensbeslag', 0),
('B & C tagstens bindere & nakkekroge', 0),
('universal 190 mm højre', 0),
('universal 190 mm venstre', 0),
('Stalddørsgreb 50x75', 0),
('T-hængsel 390 mm.', 0),
('vinkelbeslag', 0),
('4,5 x 60 mm. Skruer 200 stk.', 0),
('5,0 x 40 mm. beslagskruer 250 stk.', 0),
('5,0 x 100 mm. skruer 100 stk.', 0),
('bræddebolt 10 x 120 mm.', 0),
('firkantskiver 40x40x11mm', 0),
('4,5 x 70 mm. Skruer 200 stk.', 0),
('4,5 x 50 mm. Skruer 350 stk.', 0),
('plastmo bundskruer 200 stk.', 0),
('hulbånd 1x20 mm. 10 mtr.', 0),
('4,0 x 50 mm. beslagskruer 250 stk.', 0);

-- Husk at update jeres database version.
UPDATE properties
SET value = '3'
WHERE name = 'version';
