CREATE DATABASE `db_tdct` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `tuser` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) NOT NULL,
  `nickName` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `createDate` datetime NOT NULL,
  `lastLoginDate` datetime DEFAULT NULL,
  `ip` varchar(45) DEFAULT NULL,
  `photo` varchar(45) DEFAULT NULL,
  `qq` varchar(45) DEFAULT NULL,
  `locked` tinyint(4) DEFAULT '0',
  `token` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


