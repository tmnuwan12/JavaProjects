delimiter $$

CREATE TABLE `tokenlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(200) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Salt` varchar(10) NOT NULL,
  `Time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1$$

