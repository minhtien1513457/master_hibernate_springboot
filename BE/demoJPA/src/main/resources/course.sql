CREATE DATABASE IF NOT EXISTS jpa_demo;

USE jpa_demo;

CREATE TABLE IF NOT EXISTS course (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS student (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
    `passport_id` INT UNSIGNED NOT NULL,
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS passport (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
	`number` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS review (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
	`rating` VARCHAR(255),
	`description` VARCHAR(255),
    `course_id` INT UNSIGNED NOT NULL,
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS student_course (
	`course_id` INT UNSIGNED NOT NULL,
    `student_id` INT UNSIGNED NOT NULL,
    PRIMARY KEY (`course_id`, `student_id`)
)ENGINE=InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE student 
ADD constraint st_fk_passport_id 
FOREIGN KEY  (`passport_id`) 
REFERENCES `passport` (`id`); 

ALTER TABLE review 
ADD CONSTRAINT rv_fk_course_id 
FOREIGN KEY (`course_id`) 
REFERENCES `course` (`id`);

ALTER TABLE student_course
ADD CONSTRAINT ct_fk_course_id 
FOREIGN KEY (`course_id`) 
REFERENCES `course` (`id`);

ALTER TABLE student_course 
ADD CONSTRAINT ct_fk_student_id 
FOREIGN KEY (`student_id`) 
REFERENCES `student` (`id`);