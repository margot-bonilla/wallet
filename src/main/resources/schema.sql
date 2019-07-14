DROP TABLE IF EXISTS `user_role`;
DROP TABLE IF EXISTS `card`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `role`;


CREATE TABLE `role`
(
  `id`   bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
INSERT INTO `role`
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER');
UNLOCK TABLES;

--
-- Table structure for table `user`
--

CREATE TABLE `user`
(
  `id`       bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role`
(
  `user_id` bigint(11) NOT NULL,
  `role_id` bigint(11) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  CONSTRAINT `fk_user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `card`
(
  `id`       bigint(11) NOT NULL AUTO_INCREMENT,
  `owner_id` bigint(11) NOT NULL,
  `number`   bigint(11) NOT NULL,
  `month`    int        NOT NULL,
  `year`     int        NOT NULL,
  `secret`   int(3)     NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_user_card_owner_id` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  UNIQUE (`number`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
