use blogs;

CREATE TABLE comments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    blog_id INT,
    user_id INT,
    text TEXT,
    created_at DATE,
    num_likes INT,
    CONSTRAINT FK_user_id FOREIGN KEY (user_id)
    REFERENCES users(id),
    CONSTRAINT FK_blog_id FOREIGN KEY (blog_id)
    REFERENCES blogs(id)
);