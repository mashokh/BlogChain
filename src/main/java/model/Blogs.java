package model;


import java.util.Date;

public class Blogs {
    private int blog_id;
    private String title;
    private String text;
    private String created_by;
    private Date created_at;
    private int category_id;

    public Blogs(int blog_id, String title, String text, String created_by, Date created_at, int category_id) {
        this.blog_id = blog_id;
        this.title = title;
        this.text = text;
        this.created_by = created_by;
        this.created_at = created_at;
        this.category_id = category_id;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
