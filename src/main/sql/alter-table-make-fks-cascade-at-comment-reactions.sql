USE blogs;

ALTER TABLE comment_reactions
    DROP CONSTRAINT FK_ur_user_id;

ALTER TABLE comment_reactions
    ADD CONSTRAINT FK_ur_user_id
    FOREIGN KEY (user_id)
    REFERENCES users(id)
    ON DELETE CASCADE;


ALTER TABLE comment_reactions
    DROP CONSTRAINT FK_ur_comment_id;

ALTER TABLE comment_reactions
    ADD CONSTRAINT FK_ur_comment_id
    FOREIGN KEY (comment_id)
    REFERENCES comments(id)
    ON DELETE CASCADE;