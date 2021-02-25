DROP DATABASE IF EXISTS musicbeanz;
CREATE DATABASE musicbeanz;
USE musicbeanz;

DROP TABLE IF EXISTS `countries`;
CREATE TABLE `countries`(
	`countryID` int(11) NOT NULL DEFAULT '0',
    `countryName` VARCHAR(50) NOT NULL DEFAULT '',
    PRIMARY KEY(`countryID`)
);

INSERT INTO `countries` VALUES (0, 'Australia'), (1, 'United States of America'), (2, 'Germany'), (3, 'Croatia'), (4, 'Canada'), (5, 'Jamaica'), (6, 'United Kingdom'), (7, 'France'), (8, 'Russia'), (9, 'Japan'), (10, 'Serbia');

DROP TABLE IF EXISTS `cities`;
CREATE TABLE `cities`(
	`cityID` int(11) NOT NULL DEFAULT '0',
    `cityName` VARCHAR(50) NOT NULL DEFAULT '',
    `countryID` int(11) NOT NULL DEFAULT '0',
    PRIMARY KEY(`cityID`),
    FOREIGN KEY (`countryID`) REFERENCES `countries`(`countryID`)
);

INSERT INTO `cities` VALUES (00, 'Perth', 0), (01, 'Sydney', 0), (20, 'Berlin', 2), (30, 'Split', 3), (31, 'Zagreb', 3), (10, 'New York City', 1), (11, 'Seattle', 1), (40, 'Toronto', 4), (50, 'Kingston', 5), (60, 'London', 6), (61, 'Isle of Wight', 6), (70, 'Paris', 7), (80, 'Moscow', 8), (90, 'Kyoto', 9), (100, 'Novi Sad', 10);

DROP TABLE IF EXISTS `criminalCharges`;
CREATE TABLE `criminalCharges`(
	`criminalID` int(11) NOT NULL DEFAULT '0',
    `criminalChargeName` VARCHAR(50) NOT NULL DEFAULT '',
    PRIMARY KEY (`criminalID`)
);

INSERT INTO `criminalCharges` VALUES (0, 'Aggravated Assault'), (1, 'Arson'), (2, 'Bribery'), (3, 'Conspiracy'), (4, 'Money Laundering'), (5, 'Prostitution'), (6, 'Robbery'), (7, 'Public Intoxication'), (8, 'Drug Possession'), (9, 'Homicide'), (10, 'Theft');

DROP DATABASE IF EXISTS `artists`;
CREATE TABLE `artists`(
	`artistID` int(11) NOT NULL DEFAULT '0',
    `artistName` VARCHAR (50) NOT NULL DEFAULT '',
    PRIMARY KEY (`artistID`)
);

INSERT INTO `artists` VALUES (0, 'Paul Kalkbrenner'), (1, 'The Strokes'), (2, 'Djecaci'), (3, 'Tame Impala'), (4, 'dropthegunfatboy');
INSERT INTO `artists` VALUES (5, 'TBF'), (6, 'Mall Grab'), (7, 'Toots & The Maytals'), (8, 'ASAP Rockey'), (9, 'Jimi Hendrix');

DROP TABLE IF EXISTS `genres`;
CREATE TABLE `genres`(
	`genreID` int(11) NOT NULL DEFAULT '0',
    `genreName` VARCHAR (50) NOT NULL DEFAULT '',
    PRIMARY KEY (`genreID`)
);

INSERT INTO `genres` VALUES (0, 'Rap'), (1, 'Rock'), (2, 'Techno'), (3, 'Reggae'), (4, 'Alternative');

DROP TABLE IF EXISTS `albums`;
CREATE TABLE `albums`(
	`albumID` int(11) NOT NULL DEFAULT '0',
    `albumName` VARCHAR(50) NOT NULL DEFAULT '',
    `artistID` int(11) NOT NULL DEFAULT '0',
    `genreID` int(11) NOT NULL DEFAULT '0',
    PRIMARY KEY (`albumID`),
    FOREIGN KEY (`artistID`) REFERENCES `artists`(`artistID`),
    FOREIGN KEY (`genreID`) REFERENCES `genres`(`genreID`)
);

INSERT INTO `albums` VALUES (0000, 'Self', 0, 2), (0100, 'Is This It', 1, 1), (0200, 'Istina', 2, 0), (0300, 'The Slow Rush', 3, 4), (0400, 'dropthegunfatboy', 4, 2), (0500, 'Maxon Universal', 5, 0), (0600, 'Don\'t Keep The Fires Burning', 6, 2), (0700, 'Funky Kingston', 7, 3), (0800, 'TESTING', 8, 0), (0900, 'Electric Ladyland', 9, 1);


DROP TABLE IF EXISTS `artistInfo`;
CREATE TABLE `artistInfo`(
	`artistID` int(11) NOT NULL DEFAULT '0',
    `cityID` int(11) NOT NULL DEFAULT '0',
    `criminalID` int(11) NOT NULL DEFAULT '0',
    FOREIGN KEY (`artistID`) REFERENCES `artists` (`artistID`),
    FOREIGN KEY (`cityID`) REFERENCES `cities` (`cityID`),
    FOREIGN KEY (`criminalID`) REFERENCES `criminalCharges` (`criminalID`)
);

INSERT INTO `artistInfo` VALUES (0, 20, 8), (1, 10, 3), (2, 30, 5), (3, 00, 6), (4, 40, 1), (5, 30, 3), (6, 01, 8), (7, 50, 6), (8, 10, 6), (9, 11, 10);
SELECT * FROM `artists`;
DROP TABLE IF EXISTS `upcomingEvents`;
CREATE TABLE `upcomingEvents`(
	`upcomingEventID` int(11) NOT NULL DEFAULT '0',
    `upcomingEventName` VARCHAR(50) NOT NULL DEFAULT '',
	`artistID` int(11) NOT NULL DEFAULT '0',
    `cityID` int(11) NOT NULL DEFAULT '0',
    PRIMARY KEY (`upcomingEventID`),
    FOREIGN KEY(`artistID`) REFERENCES `artists`(`artistID`),
    FOREIGN KEY(`cityID`) REFERENCES `cities`(`cityID`)
);
SET FOREIGN_KEY_CHECKS = 0;
INSERT INTO `upcomingEvents` VALUES (0, 'Isle of Wight Festival', 3, 61), (1, 'EXIT Festival', 6, 100), (2, 'The Strokes @ Madison Square Garden', 1, 10), (3, 'Djecaci @ Tvornica Kultura', 2, 31);
SET FOREIGN_KEY_CHECKS = 1;
SELECT * FROM `countries`;
SELECT * FROM `cities`;
SELECT `albumName` FROM `albums`;

DROP TABLE IF EXISTS `songs`;
CREATE TABLE `songs`(
	`songID` int(11) NOT NULL DEFAULT '0',
    `songName` VARCHAR (50) NOT NULL,
    `artistID` int(11) NOT NULL DEFAULT '0',
    `genreID` int(11) NOT NULL DEFAULT '0',
    `albumID` int(11) NOT NULL DEFAULT '0',
    `runTime` TIME NOT NULL DEFAULT '0:00',
    Primary KEY (`songID`),
    FOREIGN KEY (`artistID`) REFERENCES `artists` (`artistID`),
    FOREIGN KEY (`genreID`) REFERENCES `genres` (`genreID`),
    FOREIGN KEY (`albumID`) REFERENCES `albums`(`albumID`)
);

INSERT INTO `songs` VALUES (0001, 'Page One', 0, 2, 0000, '0:0:26'), (0002, 'Press On', 0, 2, 0000, '0:5:52'), (0003, 'Castanets', 0, 2, 0000, '0:5:30'), (0004, 'Queer Fellow', 0, 2, 0000, '0:4:17'), (0005, 'Since 77', 0, 2, 0000, '0:5:06'), (0006, 'Page Two', 0, 2, 0000, '0:0:25'), (0007, 'Marbles', 0, 2, 0000, '0:4:17'), (0008, 'Dockyard', 0, 2, 0000, '0:5:40'), (0009, 'The Grouch', 0, 2, 0000, '0:6:08'), (0010, 'The Palisades', 0, 2, 0000, '0:3:46');
INSERT INTO `songs` VALUES (0101, 'Is This It', 1, 1, 0100, '0:2:31'), (0102, 'The Modern Age', 1, 1, 0100, '0:3:28'), (0103, 'Soma', 1, 1, 0100, '0:2:34'), (0104, 'Barely Legal', 1, 1, 0100, '0:3:55'), (0105, 'Someday', 1, 1, 0100, '0:3:04'), (0106, 'Alone, Together', 1, 1, 0100, '0:3:09'), (0107, 'Last Nite', 1, 1, 0100, '0:3:14'), (0108, 'Hard To Explain', 1, 1, 0100, '0:3:44'), (0109, 'When It Started', 1, 1, 0100, '0:2:55'), (0110, 'Trying Your Luck', 1, 1, 0100, '0:3:23'), (0111, 'Take It or Leave It', 1, 1, 0100, '0:3:16');
INSERT INTO `songs` VALUES (0201, 'Istina', 2, 0, 0200, '0:3:45'), (0202, 'Maci', 2, 0, 0200, '0:5:04'), (0203, 'Cigan', 2, 0, 0200, '4:23'), (0204, 'Lovrinac', 2, 0, 0200, '3:44'), (0205, 'Dalmacija', 2, 0, 0200, '0:5:40'), (0206, 'Med', 2, 0, 0200, '0:3:11'), (0207, 'Evala', 2, 0, 0200, '0:3:11'), (0208, 'Sto Eura', 2, 0, 0200, '0:1:10'), (0209, 'Miris', 2, 0, 0200, '0:4:48'), (0210, 'Janeway', 2, 0, 0200, '0:4:15'), (0211, 'Siromasan', 2, 0, 0200, '0:3:24'), (0212, 'Letece Sranje', 2, 0, 0200, '0:2:30'), (0213, 'Zgb', 2, 0, 0200, '0:3:29'), (0214, 'Za Beby', 2, 0, 0200, '0:3:24'), (0215, 'Aladin', 2, 0, 0200, '0:2:08'), (0216, 'Deblo', 2, 0, 0200, '0:3:58'), (0217, 'After U Zatvoru', 2, 0, 0200, '0:3:27');
INSERT INTO `songs` VALUES (0301, 'One More Year', 3, 4, 0300, '0:5:22'), (0302, 'Instant Destiny', 3, 4, 0300, '0:3:14'), (0303, 'Borderline', 3, 4, 0300, '0:3:58'), (0304, 'Posthumous', 3, 4, 0300, '0:6:06'), (0305, 'Breathe Deeper', 3, 4, 0300, '0:6:13'), (0306, 'Tomorrow\'s Dust', 3, 4, 0300, '0:5:27'), (0307, 'On Track', 3, 4, 0300, '0:5:02'), (0308, 'Lost in Yesterday', 3, 4, 0300, '0:4:10');


SELECT * FROM `songs`;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`(
	`userName` VARCHAR(30) NOT NULL DEFAULT '0',
    `firstName` VARCHAR(30) NOT NULL DEFAULT '',
    `lastName` VARCHAR(30) NOT NULL DEFAULT '',
    `password` VARCHAR(30) NOT NULL DEFAULT '',
    `Role` VARCHAR(30) NOT NULL DEFAULT 'General',
    Primary KEY (`userName`)
);

INSERT INTO `users` VALUES ('kado', 'Luka', 'Kontrec', 'luk@h@s@p@$$w0rd', 'Admin'), ('cico', 'Marko', 'Palinec', 'm@rk0h@s@p@$$w0rd', 'Admin'), ('gogi', 'Petar', 'Gojun', 'test', 'Admin'), ('misa', 'Mykhalo', 'Ivchenko', 'mykh@s@p@$$w0rd', 'Admin');
INSERT INTO `users` VALUES ('brankasorous', 'Branko', 'Mihaljevic', 'pr0f3$$0rh@$@p@$$w0rd', 'General');


DROP TABLE IF EXISTS `customPlaylist`;
CREATE TABLE `customPlaylist`(
	`playlistID` int(11) NOT NULL AUTO_INCREMENT,
    `songName` VARCHAR(30) NOT NULL DEFAULT '',
    PRIMARY KEY (`playlistID`)
);
select * from customPlaylist;
use musicbeanz;
select * from users;
select * from upcomingEvents where upcomingEventName = "EXIT Festival";
select * from cities where cityID = "100";
select * from songs where genreId = "0";