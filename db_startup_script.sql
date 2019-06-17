CREATE TABLE `login` (
                         `username` varchar(45) NOT NULL,
                         `password` varchar(45) DEFAULT NULL,
                         PRIMARY KEY (`username`)
);

INSERT INTO `homework4`.`login` (`username`, `password`) VALUES ('admin', '123456');