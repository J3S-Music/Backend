-- -----------------------------------------------------
-- Schema j3s
-- -----------------------------------------------------
DROP DATABASE  IF EXISTS  `j3s` ;
CREATE SCHEMA IF NOT EXISTS `j3s` DEFAULT CHARACTER SET utf8 ;
USE `j3s` ;

-- -----------------------------------------------------
-- Table `Avatar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `j3s`.`Avatar` (
  `AvatarID` INT NOT NULL,
  `Link` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`AvatarID`)
);

-- -----------------------------------------------------
-- Table `User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `j3s`.`User` (
  `UserID` BIGINT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `AvatarID` INT NOT NULL DEFAULT 1,
  `Email` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`UserID`),
  FOREIGN KEY (`AvatarID`) REFERENCES `Avatar` (`AvatarID`)
);

-- -----------------------------------------------------
-- Table `j3s`.`Connection`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `j3s`.`Connection` (
  `ConnectionID` BIGINT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NULL,
  PRIMARY KEY (`ConnectionID`));

-- -----------------------------------------------------
-- Table `User_to_Connection`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `j3s`.`User_to_Connection` (
  `UserID` BIGINT NOT NULL,
  `ConnectionID` BIGINT NOT NULL,
  `Key` VARCHAR(45) NULL,
  `Default` BOOLEAN NOT NULL,
  FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`),
  FOREIGN KEY (`ConnectionID`) REFERENCES `Connection` (`ConnectionID`)
);

-- -----------------------------------------------------
-- Table `j3s`.`Room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `j3s`.`Room` (
  `RoomID` BIGINT NOT NULL AUTO_INCREMENT,
  `Principle` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`RoomID`)
);

-- -----------------------------------------------------
-- Table `Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `j3s`.`Role` (
  `RoleID` BIGINT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`RoleID`)
  );

-- -----------------------------------------------------
-- Table `User_to_Room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `j3s`.`User_to_Room` (
  `UserID` BIGINT NOT NULL,
  `RoomID` BIGINT NOT NULL,
  `RoleID` BIGINT NOT NULL,
  FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`),
  FOREIGN KEY (`RoomID`) REFERENCES `room` (`RoomID`),
  FOREIGN KEY (`RoleID`) REFERENCES `role` (`RoleID`)
);

-- -----------------------------------------------------
-- Insert INTO `Avatar`
-- -----------------------------------------------------
INSERT INTO `Avatar` (AvatarID, Link) VALUES (1, '../assets/img_avatar1.png');
INSERT INTO `Avatar` (AvatarID, Link) VALUES (2, '../assets/img_avatar2.png');
INSERT INTO `Avatar` (AvatarID, Link) VALUES (3, '../assets/img_avatar3.png');
INSERT INTO `Avatar` (AvatarID, Link) VALUES (4, '../assets/img_avatar4.png');
INSERT INTO `Avatar` (AvatarID, Link) VALUES (5, '../assets/img_avatar5.png');
INSERT INTO `Avatar` (AvatarID, Link) VALUES (6, '../assets/img_avatar6.png');

-- -----------------------------------------------------
-- Insert INTO `User`
-- -----------------------------------------------------
INSERT INTO `User` (Name, AvatarID, Email, Password) VALUES ('Test',1,'test@mail','sicheresPasswort');
INSERT INTO `User` (Name, AvatarID, Email, Password) VALUES ('Test1',2,'test@m4ail','nichtPasswort');
INSERT INTO `User` (Name, AvatarID, Email, Password) VALUES ('Test2',3,'test@mai2l','coolesPasswort');
INSERT INTO `User` (Name, AvatarID, Email, Password) VALUES ('Tes3t2',6,'test@ma3il','hi');
