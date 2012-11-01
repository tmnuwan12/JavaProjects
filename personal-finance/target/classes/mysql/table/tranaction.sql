CREATE TABLE `transaction` (
  `Transaction_ID` int(11) NOT NULL AUTO_INCREMENT,
  `User_ID` int(11) NOT NULL,
  `TimeStamp` datetime NOT NULL,
  `Description` varchar(200) NOT NULL,
  `Amount` float(7,2) DEFAULT NULL,
  `FurtherInformation` varchar(8000) DEFAULT NULL,
  PRIMARY KEY (`Transaction_ID`),
  UNIQUE KEY `Transaction_ID_UNIQUE` (`Transaction_ID`),
  KEY `FK_Transaction.User_ID_User.User_ID_idx` (`User_ID`),
  CONSTRAINT `FK_Transaction.User_ID_User.User_ID` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1$$



