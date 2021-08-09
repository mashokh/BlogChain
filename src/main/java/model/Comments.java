package model;

public class Comments {
    private int blog_id;
    private int user_id;
    private String text;
    private String created_at;

    public Comments(int blog_id, int user_id, String text, String created_at) {
        this.blog_id = blog_id;
        this.user_id = user_id;
        this.text = text;
        this.created_at = created_at;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
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
