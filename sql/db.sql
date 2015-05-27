CREATE DATABASE `db_tdct` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `tuser` (
  `userId` int NOT NULL AUTO_INCREMENT, ##用户ID
  `userName` varchar(45) NOT NULL, ##用户名(email)
  `nickName` varchar(45) NOT NULL, ##昵称
  `password` varchar(45) NOT NULL, ##密码
  `createDate` datetime NOT NULL, ##创建日期
  `lastLoginDate` datetime DEFAULT NULL, ##登陆日期
  `ip` varchar(45) DEFAULT NULL, ##IP
  `photo` varchar(45) DEFAULT NULL, ##头像
  `qq` varchar(45) DEFAULT NULL, ##QQ
  `locked` tinyint(4) DEFAULT '0', ##锁定 1锁定 0未锁定
  `token` varchar(45) DEFAULT NULL, ##token
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `db_tdct`.`game` (
  `gameId` INT NOT NULL AUTO_INCREMENT, ##ID
  `gameName` VARCHAR(100) NOT NULL, ##名称
  `enName` VARCHAR(45) NULL, ##英文名称
  `jpName` VARCHAR(45) NULL, ##日文名称
  `typeId` VARCHAR(2) NOT NULL, ##游戏类型
  `maker` VARCHAR(45) NULL, ##开发商
  `publisher` VARCHAR(45) NULL ##发行商,
  `platformId` VARCHAR(2) NOT NULL, ##平台
  `regionId` VARCHAR(2) NULL, ##区域
  PRIMARY KEY (`gameId`));

CREATE TABLE `db_tdct`.`dictionary` (
  `dictId` INT NOT NULL AUTO_INCREMENT, ##字典ID
  `category` VARCHAR(45) NOT NULL, ##类别
  `name` VARCHAR(45) NOT NULL, ##名字
  `code` VARCHAR(45) NOT NULL, ##代码
  `enable` INT NOT NULL DEFAULT 1, ##可用 0不可用 1可用
  `desc` VARCHAR(100) NULL, ##描述
  PRIMARY KEY (`dictId`));



