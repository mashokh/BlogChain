use blogs;

CREATE TABLE blogs (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            title VARCHAR(255),
                            text TEXT,
                            created_at DATE,
                            created_by INT,
                            category_id INT ,
                            CONSTRAINT FK_created_by FOREIGN KEY (created_by)
                            REFERENCES users(id),
                            CONSTRAINT  FK_category_id FOREIGN KEY (category_id)
                            REFERENCES categories(id)
                        );
