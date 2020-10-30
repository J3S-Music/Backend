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
  `PictureName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`AvatarID`)
);


-- -----------------------------------------------------
-- Table `j3s`.`Connection`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `j3s`.`Connection` (
  `ConnectionID` BIGINT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NULL,
  PRIMARY KEY (`ConnectionID`)
);

-- -----------------------------------------------------
-- Table `j3s`.`Room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `j3s`.`Room` (
`RoomID` BIGINT NOT NULL AUTO_INCREMENT,
`Principle` VARCHAR(45) NOT NULL,
`RoomCode` VARCHAR(45) NOT NULL,
`RoomName` VARCHAR(45) NOT NULL,
`ConnectionID` BIGINT NOT NULL,
PRIMARY KEY (`RoomID`),
FOREIGN KEY (`ConnectionID`) REFERENCES `Connection` (`ConnectionID`)
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
-- Table `User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `j3s`.`User` (
  `UserID` BIGINT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `AvatarID` INT NOT NULL DEFAULT 1,
  `RoleID` BIGINT NOT NULL DEFAULT 1,
  `RoomID` BIGINT,
  `Email` VARCHAR(45) NOT NULL UNIQUE,
  `Password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`UserID`),
  FOREIGN KEY (`AvatarID`) REFERENCES `Avatar` (`AvatarID`),
  FOREIGN KEY (`RoleID`) REFERENCES `Role` (`RoleID`),
  FOREIGN KEY (`RoomID`) REFERENCES `Room` (`RoomID`)
);

-- -----------------------------------------------------
-- Table `User_to_Connection`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `j3s`.`User_to_Connection` (
`UserConnectionID` BIGINT NOT NULL AUTO_INCREMENT,
`Key` VARCHAR(45) NOT NULL,
`Default` BOOLEAN NOT NULL,
`Active` BOOLEAN NOT NULL DEFAULT FALSE,
`UserID` BIGINT NOT NULL,
`ConnectionID` BIGINT NOT NULL,
PRIMARY KEY (`UserConnectionID`),
FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`),
FOREIGN KEY (`ConnectionID`) REFERENCES `Connection` (`ConnectionID`)
);

-- -----------------------------------------------------
-- Insert INTO `Avatar`
-- -----------------------------------------------------
INSERT INTO `Avatar` (AvatarID, pictureName)VALUES (1, 'img_avatar1.png');
INSERT INTO `Avatar` (AvatarID, pictureName)VALUES (2, 'img_avatar2.png');
INSERT INTO `Avatar` (AvatarID, pictureName)VALUES (3, 'img_avatar3.png');
INSERT INTO `Avatar` (AvatarID, pictureName)VALUES (4, 'img_avatar4.png');
INSERT INTO `Avatar` (AvatarID, pictureName)VALUES (5, 'img_avatar5.png');
INSERT INTO `Avatar` (AvatarID, pictureName)VALUES (6, 'img_avatar6.png');

-- -----------------------------------------------------
-- Insert INTO `Role`
-- -----------------------------------------------------
INSERT INTO `Role` (RoleID, Name)VALUES (1, 'User');
INSERT INTO `Role` (RoleID, Name)VALUES (2, 'Moderator');
INSERT INTO `Role` (RoleID, Name)VALUES (3, 'Owner');

-- -----------------------------------------------------
-- Insert INTO `Connection`
-- -----------------------------------------------------
INSERT INTO `Connection` (ConnectionID, Name) VALUES (1, 'Spotify');
INSERT INTO `Connection` (ConnectionID, Name) VALUES (2, 'Apple Music');
INSERT INTO `Connection` (ConnectionID, Name) VALUES (3, 'Soundcloud');
INSERT INTO `Connection` (ConnectionID, Name) VALUES (4, 'Youtube');

-- -----------------------------------------------------
-- Insert INTO `Room`
-- -----------------------------------------------------
INSERT INTO `Room` (RoomID, Principle, RoomCode, RoomName, ConnectionID) VALUES (1,'Voting', '1234', 'RoomOne', 1);
INSERT INTO `Room` (RoomID, Principle, RoomCode, RoomName, ConnectionID) VALUES (2,'FCFS', '4567', 'TWO', 1);
INSERT INTO `Room` (RoomID, Principle, RoomCode, RoomName, ConnectionID) VALUES (3,'Random','3245', 'asdasd', 1);

-- ------------------------------------------------------
-- Insert INTO `User`
-- ------------------------------------------------------
INSERT INTO `User` (Name, AvatarID, Email, Password, RoleID, RoomID) VALUES ('Test',1,'test','test',3,1);
INSERT INTO `User` (Name, AvatarID, Email, Password, RoleID, RoomID) VALUES ('Test1',2,'test@m4ail','nichtPasswort',3,2);
INSERT INTO `User` (Name, AvatarID, Email, Password, RoleID, RoomID) VALUES ('Test2',3,'test@mai2l','coolesPasswort',2,2);
INSERT INTO `User` (Name, AvatarID, Email, Password, RoleID, RoomID) VALUES ('Tes3t2',6,'test@ma3il','hi',1,1);

-- -- -- -----------------------------------------------------
-- -- -- Insert INTO `User_to_Connection`
-- -- -- -----------------------------------------------------
INSERT INTO `User_to_Connection` (UserConnectionID, UserID, ConnectionID, `Key`, `Default`, Active) VALUES (1,1, 1, 'spotifytestkey1234', true, true);
INSERT INTO `User_to_Connection` (UserConnectionID, UserID, ConnectionID, `Key`, `Default`, Active) VALUES (2,1, 4, 'youtubetestkey1234', false, false);
INSERT INTO `User_to_Connection` (UserConnectionID, UserID, ConnectionID, `Key`, `Default`, Active) VALUES (3,1, 3, 'sctestkey1234', false, true);
INSERT INTO `User_to_Connection` (UserConnectionID, UserID, ConnectionID, `Key`, `Default`, Active) VALUES (4,3, 1, 'spotifytestkeyr542', false, true);
INSERT INTO `User_to_Connection` (UserConnectionID, UserID, ConnectionID, `Key`, `Default`, Active) VALUES (5,2, 4, 'youtubetestkey15434', false, false);
