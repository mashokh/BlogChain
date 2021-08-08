use blogs;

CREATE TABLE categories (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(255),
                       is_approved BOOLEAN,
                       UNIQUE (name)
);
