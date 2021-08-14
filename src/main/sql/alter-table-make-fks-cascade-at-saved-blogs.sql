USE blogs;

ALTER TABLE saved_blogs
DROP CONSTRAINT FK_sv_user_id;

ALTER TABLE saved_blogs
ADD CONSTRAINT FK_sv_user_id
FOREIGN KEY (user_id)
REFERENCES users(id)
ON DELETE CASCADE;


ALTER TABLE saved_blogs
DROP CONSTRAINT FK_sv_blog_id;

ALTER TABLE saved_blogs
ADD CONSTRAINT FK_sv_blog_id
FOREIGN KEY (blog_id)
REFERENCES blogs(id)
ON DELETE CASCADE;