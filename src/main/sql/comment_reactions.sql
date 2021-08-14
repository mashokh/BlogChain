use blogs;

CREATE TABLE comment_reactions (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          comment_id INT,
                          user_id INT,
                          reaction TEXT,
                          CONSTRAINT FK_ur_user_id FOREIGN KEY (user_id)
                              REFERENCES users(id),
                          CONSTRAINT FK_ur_comment_id FOREIGN KEY (comment_id)
                              REFERENCES comments(id)
);