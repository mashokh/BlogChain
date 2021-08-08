package model;


import java.util.Date;

public class Blogs {
    private String title;
    private String text;
    private String created_by;
    private String created_at;
    private String category_id;

    public Blogs(String title, String text, String created_by, String created_at, String category_id) {
        this.title = title;
        this.text = text;
        this.created_by = created_by;
        this.created_at = created_at;
        this.category_id = category_id;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }
}
