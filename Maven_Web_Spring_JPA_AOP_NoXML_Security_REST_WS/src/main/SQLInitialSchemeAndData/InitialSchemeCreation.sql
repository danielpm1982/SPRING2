DROP DATABASE  IF EXISTS `scheme1`;

CREATE DATABASE  IF NOT EXISTS `scheme1`;
USE `scheme1`;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `user` (username,password,first_name,last_name,email)
VALUES 
('user1','$2a$12$lrYhYSLu0DAezMDW/l7xc.bNoctKPkn6zUE7hepP25xLNko4iS3j2','UserOneFirstName','UserOneLastName','user1@user.com'),
('user2','$2a$12$lrYhYSLu0DAezMDW/l7xc.bNoctKPkn6zUE7hepP25xLNko4iS3j2','UserTwoFirstName','UserTwoLastName','user2@user.com'),
('user3','$2a$12$lrYhYSLu0DAezMDW/l7xc.bNoctKPkn6zUE7hepP25xLNko4iS3j2','UserThreeFirstName','UserThreeLastName','user3@user.com'),
('user4','$2a$12$lrYhYSLu0DAezMDW/l7xc.bNoctKPkn6zUE7hepP25xLNko4iS3j2','UserFourFirstName','UserFourLastName','user4@user.com');

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `role` (name)
VALUES 
('ROLE_USER'),('ROLE_ADMIN'),('ROLE_MANAGER'),('ROLE_DIRECTOR'),('ROLE_CEO');

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`role_id`),
  
  KEY `FK_ROLE_idx` (`role_id`),
  
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `users_roles` (user_id,role_id)
VALUES 
(1, 1),
(2, 1),
(2, 2),
(2, 3),
(3, 1),
(3, 2),
(3, 3),
(3, 4),
(4, 1),
(4, 2),
(4, 3),
(4, 4),
(4, 5);

DROP TABLE IF EXISTS `entity_model`;
CREATE TABLE `entity_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_time_epoch` int(11) NOT NULL,
  `date_time_stringified` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

