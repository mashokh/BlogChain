package model;

public class CategoryDao {
    private int id;
    private String name;
    private boolean isApproved;

    public CategoryDao(int id, String name, boolean isApproved) {
        this.id = id;
        this.name = name;
        this.isApproved = isApproved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}