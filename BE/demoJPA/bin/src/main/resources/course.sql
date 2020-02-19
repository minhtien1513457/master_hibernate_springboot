CREATE TABLE IF NOT EXISTS course (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255),
    PRIMARY KEY (`id`)
)ENGINE=InnoDB;

insert into course(id,name) values(1000, 'spring boot');