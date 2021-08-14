package model;

public class User {

    private int id;
    private String username;
    private String password;
    private String avatar;
    private Boolean isAdmin;

    public User(int id, String username, String password, String avatar, Boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
