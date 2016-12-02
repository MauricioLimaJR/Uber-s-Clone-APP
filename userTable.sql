create table users (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `cpf` VARCHAR(20) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `birthDay` DATE,
  `sex` varchar(20) NOT NULL,
  `number` varchar(20) NOT NULL,
  primary key (id)
);