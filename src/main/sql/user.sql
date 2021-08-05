CREATE DATABASE blogs;

use blogs;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255),
    password VARCHAR(255),
    avatar VARCHAR(255),
    is_admin BOOLEAN,
    UNIQUE (username)
);



