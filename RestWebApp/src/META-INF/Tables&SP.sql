CREATE PROCEDURE `getKey`(in in_token varchar(250), out out_key varchar(250))
BEGIN
	select `key` into out_key from Usertoken where token = in_token;
END


CREATE PROCEDURE `authenticateUser`(in in_username varchar(45), in in_password varchar(45), out out_valid boolean)
BEGIN
	if (select exists (select 1 from userpass where username = in_username and `password` = in_password) = 1) then 
		select true into out_valid;
	else	
		select false into out_valid;
	end if;
END

--
-- Table structure for table `userpass`
--

DROP TABLE IF EXISTS `userpass`;
CREATE TABLE `userpass` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `userpass`
--

LOCK TABLES `userpass` WRITE;
INSERT INTO `userpass` VALUES (1,'kevin','password');
UNLOCK TABLES;

--
-- Table structure for table `usertoken`
--

DROP TABLE IF EXISTS `usertoken`;
CREATE TABLE `usertoken` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `token` varchar(250) DEFAULT NULL,
  `key` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usertoken`
--

LOCK TABLES `usertoken` WRITE;
INSERT INTO `usertoken` VALUES (1,'kevin','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrZXZpbiJ9.uQbKM5PKU1LSOVhNn17rBdHaOFarqfXGgXglZdPG-cPa8EFyNX7kAwe172gkzS6UDUjaUw7irfUWa_MdyZhjpQ','8qrkeSZu6UPCIHdl8BkcV9uOkgTnQYQWH8mxfkOl2rl2C9vAyGL4TfZEDV93OLB2e23ApBh1LQsKNVgstXOW5w==');
UNLOCK TABLES;