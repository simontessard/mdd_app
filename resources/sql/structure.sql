CREATE DATABASE mdd2;

USE mdd2;

CREATE TABLE `users` (
                         `id` integer PRIMARY KEY AUTO_INCREMENT,
                         `email` varchar(255),
                         `last_name` VARCHAR(40),
                         `first_name` VARCHAR(40),
                         `password` varchar(255),
                         `created_at` timestamp default CURRENT_TIMESTAMP,
                         `updated_at` timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `topics` (
                          `id` integer PRIMARY KEY AUTO_INCREMENT,
                          `title` varchar(255),
                          `description` varchar(2000),
                          `created_at` timestamp default CURRENT_TIMESTAMP
);

CREATE TABLE `posts` (
                         `id` integer PRIMARY KEY AUTO_INCREMENT,
                         `author`   VARCHAR(255),
                         `topic_id` integer,
                         `title` varchar(300),
                         `content` varchar(5000),
                         `created_at` timestamp default CURRENT_TIMESTAMP,
                         FOREIGN KEY (`topic_id`) REFERENCES `topics` (`id`)
);

CREATE TABLE `subs` (
                        `id` integer PRIMARY KEY AUTO_INCREMENT,
                        `topic_id` integer,
                        `user_id` integer,
                        FOREIGN KEY (`topic_id`) REFERENCES `topics` (`id`),
                        FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

CREATE TABLE `comments` (
                            `id` integer PRIMARY KEY AUTO_INCREMENT,
                            `post_id` integer,
                            `user_id` integer,
                            `comment` varchar(2000),
                            `created_at` timestamp default CURRENT_TIMESTAMP,
                            FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`),
                            FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

CREATE UNIQUE INDEX `USERS_index` ON `users` (`email`);

CREATE USER 'mdduser'@'%' identified BY 'ThePassword';

GRANT ALL PRIVILEGES ON mdd2.* TO mdduser@'localhost';

FLUSH PRIVILEGES;
