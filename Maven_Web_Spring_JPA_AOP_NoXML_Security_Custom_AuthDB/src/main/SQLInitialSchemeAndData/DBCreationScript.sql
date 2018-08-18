DROP DATABASE  IF EXISTS `scheme1`;

CREATE DATABASE  IF NOT EXISTS `scheme1`;
USE `scheme1`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(68) NOT NULL, /*for bcrypt at least 68 character-length is needed - 60 for the password itself and 8 for the id and braces (curly brackets)*/
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--

INSERT INTO `users` 
VALUES 
('user1','{noop}123',1),
('user2','{noop}123',1),
('user3','{bcrypt}$2a$12$F3VGlfgkdjmmOtmj0wBFFO4j5ZcB0NY3KPrTurjkwM4PQ/Yms32Om',1),
('user4','{bcrypt}$2a$12$F3VGlfgkdjmmOtmj0wBFFO4j5ZcB0NY3KPrTurjkwM4PQ/Yms32Om',1);

/*For example purposes, two of the values is put as {noop} plain text, the other two as bcrypted values, which is the most advisable.*/

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('user1','ROLE_USER'),
('user2','ROLE_ADMIN'),
('user2','ROLE_MANAGER'),
('user2','ROLE_USER'),
('user3','ROLE_DIRECTOR'),
('user3','ROLE_ADMIN'),
('user3','ROLE_MANAGER'),
('user3','ROLE_USER'),
('user4','ROLE_CEO'),
('user4','ROLE_DIRECTOR'),
('user4','ROLE_ADMIN'),
('user4','ROLE_MANAGER'),
('user4','ROLE_USER');
