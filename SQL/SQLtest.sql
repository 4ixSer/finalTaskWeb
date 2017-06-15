/*уронить базу*/
DROP DATABASE IF EXISTS `summarytask4test`;

/*создать базу */
CREATE SCHEMA `summarytask4test` ;

/*создать таблицу Юзеров*/
CREATE TABLE `summarytask4test`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(70) NOT NULL,
  `name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `role` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC));

/*Создать таблицу ролей*/
CREATE TABLE `summarytask4test`.`role` (
  `id` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nae_UNIQUE` (`title` ASC));



/*Созадть таблицу Для машин*/
CREATE TABLE `summarytask4test`.`car` (
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
CREATE TABLE `summarytask4test`.`type_car` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `type_UNIQUE` (`type` ASC));


    /*создание таблицы статуса машины*/

  CREATE TABLE `summarytask4test`.`status_car` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `status_UNIQUE` (`status` ASC));


/*Создание таблицы для  рейса*/
CREATE TABLE `summarytask4test`.`flight` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` TIMESTAMP NOT NULL,
  `status` INT NOT NULL,
  `driver` INT NOT NULL,
  `car` INT NOT NULL,
  `dispatcher` INT NOT NULL,
  `note` MEDIUMTEXT NULL,
  PRIMARY KEY (`id`));

/*Создание таблицы для заявок*/
CREATE TABLE `summarytask4test`.`request` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ownerRequest` INT NOT NULL,
  `dateRequest` TIMESTAMP NOT NULL,
  `dateDeparture` TIMESTAMP NOT NULL,
  `car_type` INT NULL,
  `carrying_car` DOUBLE NULL,
  `amount_car` DOUBLE NULL,
  `enginePower` DOUBLE NULL,
  `status` INT NULL,
  `note` MEDIUMTEXT NULL,
  PRIMARY KEY (`id`));

/*Создание таблицы для статусов*/
CREATE TABLE `summarytask4test`.`status` (
  `id` INT NOT NULL,
  `status_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `status_name_UNIQUE` (`status_name` ASC));

/*связь рейцса с статусом, водителем, диспечером */
ALTER TABLE `summarytask4test`.`flight`
ADD INDEX `fk_status_idx` (`status` ASC),
ADD INDEX `fk_driver_idx` (`driver` ASC),
ADD INDEX `fk_dispatcher_idx` (`dispatcher` ASC);
ALTER TABLE `summarytask4test`.`flight`
ADD CONSTRAINT `fk_status`
  FOREIGN KEY (`status`)
  REFERENCES `summarytask4test`.`status` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `fk_driver`
  FOREIGN KEY (`driver`)
  REFERENCES `summarytask4test`.`user` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `fk_dispatcher`
  FOREIGN KEY (`dispatcher`)
  REFERENCES `summarytask4test`.`user` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

  /*связь рейса с машиной*/
  ALTER TABLE `summarytask4test`.`flight`
ADD INDEX `fk_car_car_idx` (`car` ASC);
ALTER TABLE `summarytask4test`.`flight`
ADD CONSTRAINT `fk_car_car`
  FOREIGN KEY (`car`)
  REFERENCES `summarytask4test`.`car` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

/*Связь заявки с статусами и водителем*/
ALTER TABLE `summarytask4test`.`request`
ADD INDEX `fk_user_driver_idx` (`ownerRequest` ASC);
ALTER TABLE `summarytask4test`.`request`
ADD CONSTRAINT `fk_user_driver`
  FOREIGN KEY (`ownerRequest`)
  REFERENCES `summarytask4test`.`user` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `summarytask4test`.`request`
ADD CONSTRAINT `fk_requst_status`
  FOREIGN KEY (`status`)
  REFERENCES `summarytask4test`.`status` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;


/*внешний ключ в машине*/
ALTER TABLE `summarytask4test`.`car`
ADD CONSTRAINT `fk_status_car`
  FOREIGN KEY (`statusCar`)
  REFERENCES `summarytask4test`.`status_car` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

  /*внешний клюк в рейсе*/
  ALTER TABLE `summarytask4test`.`flight`
DROP FOREIGN KEY `fk_car_car`;
ALTER TABLE `summarytask4test`.`flight`
ADD CONSTRAINT `fk_car_car`
  FOREIGN KEY (`car`)
  REFERENCES `summarytask4test`.`car` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

/*Связать таблицу ббзеров с ролями*/
ALTER TABLE `summarytask4test`.`user`
ADD INDEX `fk_roleName_idx` (`role` ASC);
ALTER TABLE `summarytask4test`.`user`
ADD CONSTRAINT `fk_roleName`
  FOREIGN KEY (`role`)
  REFERENCES `summarytask4test`.`role` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;


  /*задание связей машина -тип*/
  ALTER TABLE `summarytask4test`.`car`
ADD INDEX `fk_type_idx` (`type` ASC);
ALTER TABLE `summarytask4test`.`car`
ADD CONSTRAINT `fk_type`
  FOREIGN KEY (`type`)
  REFERENCES `summarytask4test`.`type_car` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;



/*Вставить роли*/
INSERT INTO `summarytask4test`.`role` (`id`, `title`) VALUES ('1', 'Admin');
INSERT INTO `summarytask4test`.`role` (`id`, `title`) VALUES ('2', 'Dispatcher');
INSERT INTO `summarytask4test`.`role` (`id`, `title`) VALUES ('3', 'Driver');

/*Добавить админа*/
INSERT INTO `summarytask4test`.`user` (`login`, `password`, `name`, `email`, `role`) VALUES ('admin', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'admin', '', '1');
INSERT INTO `summarytask4test`.`user` (`login`, `password`, `name`, `email`, `role`) VALUES ('dispatcher', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'name', '@mail.ru', '2');
INSERT INTO `summarytask4test`.`user` (`login`, `password`, `name`,`email`,`role`) VALUES ('driver', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '����', 'qsa@mail.ru','3');


  /*Задать Стандартные типы машин*/
INSERT INTO `summarytask4test`.`type_car` (`id`, `type`) VALUES ('1', 'PLATFORM');
INSERT INTO `summarytask4test`.`type_car` (`id`, `type`) VALUES ('2', 'VAN');
INSERT INTO `summarytask4test`.`type_car` (`id`, `type`) VALUES ('3', 'TANK');
INSERT INTO `summarytask4test`.`type_car` (`id`, `type`) VALUES ('4', 'TIPPER');
INSERT INTO `summarytask4test`.`type_car` (`id`, `type`) VALUES ('5', 'SORTEMENT');
INSERT INTO `summarytask4test`.`type_car` (`id`, `type`) VALUES ('7', 'BOARD');
INSERT INTO `summarytask4test`.`type_car` (`id`, `type`) VALUES ('6', 'CONTAINER');
INSERT INTO `summarytask4test`.`type_car` (`id`, `type`) VALUES ('8', 'GASOLINE');
INSERT INTO `summarytask4test`.`type_car` (`id`, `type`) VALUES ('9', 'TENT');
INSERT INTO `summarytask4test`.`type_car` (`id`, `type`) VALUES ('10', 'TRUCK');
INSERT INTO `summarytask4test`.`type_car` (`id`, `type`) VALUES ('11', 'REFRIGERATOR');
INSERT INTO `summarytask4test`.`type_car` (`id`, `type`) VALUES ('12', 'OTHERS');

/*Запись статусов машины */
INSERT INTO `summarytask4test`.`status_car` (`id`, `status`) VALUES ('1', 'FREE');
INSERT INTO `summarytask4test`.`status_car` (`id`, `status`) VALUES ('2', 'USED');
INSERT INTO `summarytask4test`.`status_car` (`id`, `status`) VALUES ('3', 'BROKEN');

/*задание типов статусо заявок и рейсов*/
INSERT INTO `summarytask4test`.`status` (`id`, `status_name`) VALUES ('1', 'OPEN');
INSERT INTO `summarytask4test`.`status` (`id`, `status_name`) VALUES ('2', 'REJECTED');
INSERT INTO `summarytask4test`.`status` (`id`, `status_name`) VALUES ('3', 'CANCELED');
INSERT INTO `summarytask4test`.`status` (`id`, `status_name`) VALUES ('4', 'INPROGRESS');
INSERT INTO `summarytask4test`.`status` (`id`, `status_name`) VALUES ('5', 'CLOSED');
INSERT INTO `summarytask4test`.`status` (`id`, `status_name`) VALUES ('6', 'PROCESSED');
INSERT INTO `summarytask4test`.`status` (`id`, `status_name`) VALUES ('7', 'SUBMITTED');



