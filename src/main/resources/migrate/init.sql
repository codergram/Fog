CREATE TABLE `carports`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `length` double NOT NULL,
  `width` double NOT NULL,
  `roof` enum('Flat','Sloping') NOT NULL,
  `shed_id` int(10) NULL,
  `price` decimal(8, 2) NOT NULL,
  `partlist_id` int(10) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `customers`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `postal` int(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `phone` int(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `materiale`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NULL,
  `price` decimal(8, 2) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `orders`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `width` double NULL,
  `length` double NULL,
  `timestamp` timestamp NULL,
  `employee_id` int NULL,
  `customer_id` int NULL,
  `status` enum('New','Awaiting','Accepted','Paid','Completed','Cancelled') NULL DEFAULT 'New',
  `carport_id` int NULL,
  `margin` decimal(4, 2) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `parts`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NULL,
  `usage_id` int(0) NULL,
  `amount` int(255) NULL,
  `length` double NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `partlists`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `part_id` int(0) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `properties`  (
  `version` int(255) NOT NULL,
  `vat` decimal(4, 2) NULL
);

CREATE TABLE `sheds`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `length` double NULL,
  `width` double NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `type`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `usage`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NULL,
  `material_id` int(0) NULL,
  `type_id` int(0) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `users`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NULL,
  `email` varchar(255) NOT NULL,
  `role` enum('Employee','Admin') NOT NULL DEFAULT 'Employee',
  `salt` blob NOT NULL,
  `secret` blob NOT NULL,
  `active` tinyint(255) NULL DEFAULT 1,
  PRIMARY KEY (`id`)
);

ALTER TABLE `carports` ADD CONSTRAINT `fk_carports_sheds_1` FOREIGN KEY (`shed_id`) REFERENCES `sheds` (`id`);
ALTER TABLE `carports` ADD CONSTRAINT `fk_carports_partlists_1` FOREIGN KEY (`partlist_id`) REFERENCES `partlists` (`id`);
ALTER TABLE `orders` ADD CONSTRAINT `fk_orders_customers_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`);
ALTER TABLE `orders` ADD CONSTRAINT `fk_orders_users_1` FOREIGN KEY (`employee_id`) REFERENCES `users` (`id`);
ALTER TABLE `orders` ADD CONSTRAINT `fk_orders_carports_1` FOREIGN KEY (`carport_id`) REFERENCES `carports` (`id`);
ALTER TABLE `parts` ADD CONSTRAINT `fk_part_usage_1` FOREIGN KEY (`usage_id`) REFERENCES `usage` (`id`);
ALTER TABLE `partlists` ADD CONSTRAINT `fk_partlists_parts_1` FOREIGN KEY (`part_id`) REFERENCES `parts` (`id`);
ALTER TABLE `usage` ADD CONSTRAINT `fk_usage_materiale_1` FOREIGN KEY (`material_id`) REFERENCES `materiale` (`id`);
ALTER TABLE `usage` ADD CONSTRAINT `fk_usage_type_1` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`);

/* Password: admin */
INSERT INTO users(id, name, email, role, salt, secret, active) VALUES (1, 'Admin user', 'admin@test.dk', 'Admin', 0xB94329C90E158E72C1EB57A3C38C6AD4, 0xD26F573ADCBF63F730EA22F26A2C19E2743FC734FF4D8AD533C2B93D5A32FD04, 1);


