/*уронить базу*/
DROP DATABASE `summarytask4`;

/*создать базу */
CREATE SCHEMA `summarytask4` ;

/*создать таблицу Юзеров*/
CREATE TABLE `summarytask4`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `role` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC));

/*Создать таблицу ролей*/
CREATE TABLE `summarytask4`.`role` (
  `id` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nae_UNIQUE` (`title` ASC));

/*Связать таблицу ббзеров с ролями*/
ALTER TABLE `summarytask4`.`user` 
ADD INDEX `fk_roleName_idx` (`role` ASC);
ALTER TABLE `summarytask4`.`user` 
ADD CONSTRAINT `fk_roleName`
  FOREIGN KEY (`role`)
  REFERENCES `summarytask4`.`role` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
/*Созадть таблицу Для машин*/
CREATE TABLE `summarytask4`.`car` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `namber` VARCHAR(45) NOT NULL,
  `type` INT NOT NULL,
  `carryingCar` DOUBLE NOT NULL,
  `amountCar` DOUBLE NOT NULL,
  `enginePower` DOUBLE NULL,
  `statusCar` INT NOT NULL,
  `comments` MEDIUMTEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `namber_UNIQUE` (`namber` ASC));

/*Созадание таблицы типов машин*/
CREATE TABLE `summarytask4`.`type_car` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `type_UNIQUE` (`type` ASC));
  
/*внешний ключ в машине*/
ALTER TABLE `summarytask4`.`car` 
ADD CONSTRAINT `fk_status_car`
  FOREIGN KEY (`statusCar`)
  REFERENCES `summarytask4`.`status_car` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
  /*внешний клюк в рейсе*/
  ALTER TABLE `summarytask4`.`flight` 
DROP FOREIGN KEY `fk_car_car`;
ALTER TABLE `summarytask4`.`flight` 
ADD CONSTRAINT `fk_car_car`
  FOREIGN KEY (`car`)
  REFERENCES `summarytask4`.`car` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;


  
  /*задание связей машина -тип*/
  ALTER TABLE `summarytask4`.`car` 
ADD INDEX `fk_type_idx` (`type` ASC);
ALTER TABLE `summarytask4`.`car` 
ADD CONSTRAINT `fk_type`
  FOREIGN KEY (`type`)
  REFERENCES `summarytask4`.`type_car` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
    /*создание таблицы статуса машины*/
  
  CREATE TABLE `summarytask4`.`status_car` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `sstatus_UNIQUE` (`sstatus` ASC));


/*Создание таблицы для  рейса*/
CREATE TABLE `summarytask4`.`flight` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` TIMESTAMP NOT NULL,
  `status` INT NOT NULL,
  `driver` INT NOT NULL,
  `car` INT NOT NULL,
  `dispatcher` INT NOT NULL,
  `note` MEDIUMTEXT NULL,
  PRIMARY KEY (`id`));

/*Создание таблицы для заявок*/
CREATE TABLE `summarytask4`.`request` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ownerRequest` INT NOT NULL,
  `dataRequest` TIMESTAMP NOT NULL,
  `dataDeparture` TIMESTAMP NOT NULL,
  `car_type` INT NULL,
  `carrying_car` DOUBLE NULL,
  `amount_car` DOUBLE NULL,
  `enginePower` DOUBLE NULL,
  `status` INT NULL,
  `note` MEDIUMTEXT NULL,
  PRIMARY KEY (`id`));

/*Создание таблицы для статусов*/
CREATE TABLE `summarytask4`.`status` (
  `id` INT NOT NULL,
  `status_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `status_name_UNIQUE` (`status_name` ASC));
  
/*связь рейцса с статусом, водителем, диспечером */
ALTER TABLE `summarytask4`.`flight` 
ADD INDEX `fk_status_idx` (`status` ASC),
ADD INDEX `fk_driver_idx` (`driver` ASC),
ADD INDEX `fk_dispatcher_idx` (`dispatcher` ASC);
ALTER TABLE `summarytask4`.`flight` 
ADD CONSTRAINT `fk_status`
  FOREIGN KEY (`status`)
  REFERENCES `summarytask4`.`status` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `fk_driver`
  FOREIGN KEY (`driver`)
  REFERENCES `summarytask4`.`user` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `fk_dispatcher`
  FOREIGN KEY (`dispatcher`)
  REFERENCES `summarytask4`.`user` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
  /*связь рейса с машиной*/
  ALTER TABLE `summarytask4`.`flight` 
ADD INDEX `fk_car_car_idx` (`car` ASC);
ALTER TABLE `summarytask4`.`flight` 
ADD CONSTRAINT `fk_car_car`
  FOREIGN KEY (`car`)
  REFERENCES `summarytask4`.`car` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

/*Связь заявки с статусами и водителем*/
ALTER TABLE `summarytask4`.`request` 
ADD INDEX `fk_user_driver_idx` (`ownerRequest` ASC);
ALTER TABLE `summarytask4`.`request` 
ADD CONSTRAINT `fk_user_driver`
  FOREIGN KEY (`ownerRequest`)
  REFERENCES `summarytask4`.`user` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
ALTER TABLE `summarytask4`.`request` 
ADD CONSTRAINT `fk_requst_status`
  FOREIGN KEY (`status`)
  REFERENCES `summarytask4`.`status` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;


/*Вставить роли*/
INSERT INTO `summarytask4`.`role` (`id`, `title`) VALUES ('1', 'Admin');
INSERT INTO `summarytask4`.`role` (`id`, `title`) VALUES ('2', 'Dispatcher');
INSERT INTO `summarytask4`.`role` (`id`, `title`) VALUES ('3', 'Driver');

/*Добавить админа*/
INSERT INTO `summarytask4`.`user` (`login`, `password`, `name`, `email`, `role`) VALUES ('admin', 'root', 'admin', '', '1');
INSERT INTO `summarytask4`.`user` (`login`, `password`, `name`, `email`, `role`) VALUES ('dispatcher', '123', 'name', '@mail.ru', '2');
INSERT INTO `summarytask4`.`user` (`login`, `password`, `name`, `role`) VALUES ('driver', 'пароль', 'вывф', '3');

  
  /*Задать Стандартные типы машин*/
INSERT INTO `summarytask4`.`type_car` (`id`, `type`) VALUES ('1', 'PLATFORM');
INSERT INTO `summarytask4`.`type_car` (`id`, `type`) VALUES ('2', 'VAN');
INSERT INTO `summarytask4`.`type_car` (`id`, `type`) VALUES ('3', 'TANK');
INSERT INTO `summarytask4`.`type_car` (`id`, `type`) VALUES ('4', 'SUGGESTION');
INSERT INTO `summarytask4`.`type_car` (`id`, `type`) VALUES ('5', 'SORTEMENT');
INSERT INTO `summarytask4`.`type_car` (`id`, `type`) VALUES ('6', 'BOARD');
INSERT INTO `summarytask4`.`type_car` (`id`, `type`) VALUES ('7', 'CONTAINER');
INSERT INTO `summarytask4`.`type_car` (`id`, `type`) VALUES ('8', 'GASOLINE');
INSERT INTO `summarytask4`.`type_car` (`id`, `type`) VALUES ('9', 'TANKER');
INSERT INTO `summarytask4`.`type_car` (`id`, `type`) VALUES ('10', 'TENT');
INSERT INTO `summarytask4`.`type_car` (`id`, `type`) VALUES ('11', 'AVTOVOSCH');
INSERT INTO `summarytask4`.`type_car` (`id`, `type`) VALUES ('12', 'REFRIGERATOR');
INSERT INTO `summarytask4`.`type_car` (`id`, `type`) VALUES ('13', 'OTHERS');

/*Запись статусов машины */
INSERT INTO `summarytask4`.`status_car` (`id`, `sstatus`) VALUES ('1', 'FREE');
INSERT INTO `summarytask4`.`status_car` (`id`, `sstatus`) VALUES ('2', 'USED');
INSERT INTO `summarytask4`.`status_car` (`id`, `sstatus`) VALUES ('3', 'BROKEN');
  
/*задание типов статусо заявок и рейсов*/
INSERT INTO `summarytask4`.`status` (`id`, `status_name`) VALUES ('1', 'OPEN');
INSERT INTO `summarytask4`.`status` (`id`, `status_name`) VALUES ('2', 'REJEJECTED');
INSERT INTO `summarytask4`.`status` (`id`, `status_name`) VALUES ('3', 'CANCELED');
INSERT INTO `summarytask4`.`status` (`id`, `status_name`) VALUES ('4', 'INPROGRESS');
INSERT INTO `summarytask4`.`status` (`id`, `status_name`) VALUES ('5', 'CLOSED');
INSERT INTO `summarytask4`.`status` (`id`, `status_name`) VALUES ('6', 'PROCESSED');


/* запись машин */
INSERT INTO `summarytask4`.`car` (`namber`, `type`, `carryingCar`, `amountCar`, `enginePower`, `statusCar`) VALUES ('AD2156AD', '1', '13.5', '15.5', '1', '1');
INSERT INTO `summarytask4`.`car` (`namber`, `type`, `carryingCar`, `amountCar`, `enginePower`, `statusCar`) VALUES ('XS2564AD', '2', '76', '364', '113', '1');
INSERT INTO `summarytask4`.`car` (`namber`, `type`, `carryingCar`, `amountCar`, `enginePower`, `statusCar`) VALUES ('DS2564DE', '4', '15', '15.5', '18', '1');


