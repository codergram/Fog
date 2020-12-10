DROP USER IF EXISTS fogtest@localhost;
CREATE USER fogtest@localhost IDENTIFIED BY 'codergram';
GRANT ALL PRIVILEGES ON fogtest.* TO fogtest@localhost;