USE blogs;

create table saved_blogs (
    id int primary key auto_increment,
    user_id int,
    blog_id int,
    CONSTRAINT FK_sv_user_id FOREIGN KEY (user_id)
    REFERENCES users(id),
    CONSTRAINT FK_sv_blog_id FOREIGN KEY (blog_id)
    REFERENCES blogs(id)
);