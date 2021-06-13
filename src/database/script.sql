CREATE TABLE IF NOT EXISTS `SportTitle`
(
    `Id`   int AUTO_INCREMENT NOT NULL,
    `Name` varchar(255)       NOT NULL,
    PRIMARY KEY (`Id`)
);

CREATE TABLE IF NOT EXISTS `Sport`
(
    `Id`   int AUTO_INCREMENT NOT NULL,
    `Name` varchar(255)       NOT NULL,
    PRIMARY KEY (`Id`)
);

CREATE TABLE IF NOT EXISTS `Sportsman`
(
    `Id`           int AUTO_INCREMENT NOT NULL,
    `FullName`     varchar(255)       NOT NULL,
    `SportId`      int                NOT NULL,
    `PhoneNumber`  varchar(15)        NOT NULL,
    `SportTitleId` int                NOT NULL,
    `CoachName`    varchar(255)       NOT NULL,
    PRIMARY KEY (`Id`),
    CONSTRAINT `Sportsman_FK_SportId` FOREIGN KEY (`SportId`) REFERENCES `Sport` (`Id`) ON DELETE CASCADE,
    CONSTRAINT `Sportsman_FK_SportTitleId` FOREIGN KEY (`SportTitleId`) REFERENCES `SportTitle` (`Id`) ON DELETE CASCADE
);