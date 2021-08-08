package model;

public class Comments {
    private String blog_id;
    private String user_id;
    private String text;
    private String created_at;

    public Comments(String blog_id, String user_id, String text, String created_at) {
        this.blog_id = blog_id;
        this.user_id = user_id;
        this.text = text;
        this.created_at = created_at;
    }

    public String getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(String blog_id) {
        this.blog_id = blog_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
