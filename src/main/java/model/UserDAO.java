package model;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {

    public UserDAO() {}

    public static User getUserById(int id) {
        Connection connection = DataBase.getConnection();

        try {

            PreparedStatement statement = connection.prepareStatement("select * from users where id = ?;");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) return new User(rs.getString("username"), rs.getString("password"),
                                        rs.getString("avatar"), rs.getBoolean("is_admin"));

        } catch (SQLException throwables) { throwables.printStackTrace(); }

        return null;
    }

    public static String getPasswordByUsername(String username) {
        Connection connection = DataBase.getConnection();

        try {

            PreparedStatement statement = connection.prepareStatement("select password from users where username = ?;");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) return rs.getString("password");

        } catch (SQLException throwables) { throwables.printStackTrace(); }

        return "";
    }


    public static boolean successLogin(String username, String password) {
       return getPasswordByUsername(username).equals(password);

    }

    public static void addUser(User user) {
        Connection connection = DataBase.getConnection();
        String query = "INSERT INTO users (username, password, avatar, is_admin) VALUES(?, ?, ?, ?);";

        try {

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getAvatar());
            statement.setBoolean(4, user.getAdmin());
            statement.execute();

        } catch (SQLException throwables) { throwables.printStackTrace(); }

    }

    public static boolean usernameExists(String username) {
        Connection connection = DataBase.getConnection();
        String query = "SELECT COUNT(*) AS count FROM users WHERE username = ?;";

        try {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) return rs.getInt("count") > 0;

        } catch (SQLException throwables) { throwables.printStackTrace(); }
        return false;
    }

    public static int getIdByUsername(String username) {
        Connection connection = DataBase.getConnection();
        String query = "SELECT id FROM users WHERE username = ?;";

        try {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) return rs.getInt("id");

        } catch (SQLException throwables) { throwables.printStackTrace(); }

        return 0;
    }

    /**
     * @param isAdmin true if need admins users
     *
     * @return array of admin users or non-admin users */
    public static ArrayList<User> getUserByAdmin(boolean isAdmin) throws SQLException {
        Connection connection = DataBase.getConnection();

        String query = "SELECT * FROM users WHERE is_admin = ?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setBoolean(1, isAdmin);

        return loadUsers(statement.executeQuery());
    }

    private static ArrayList<User> loadUsers(ResultSet rs) {
        ArrayList<User> users = new ArrayList<>();

        try {
            while (rs.next())
                users.add(new User(rs.getString("username"), rs.getString("password"),
                        rs.getString("avatar"), rs.getBoolean("is_admin")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }


    /**
     * @param username relevant username
     * @return true if the user is admin, otherwise - false
     */
    public static boolean userIsAdmin(String username) {
        Connection connection = DataBase.getConnection();
        String query = "SELECT is_admin FROM users WHERE username = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) return rs.getBoolean("is_admin");

        } catch (SQLException throwables) {throwables.printStackTrace();}
        
        return false;
    }

    public static void updateUserStatus(String username, Boolean isAdmin) {
        Connection connection = DataBase.getConnection();

        String query = "UPDATE users SET is_admin = ? WHERE username =?;";
        try {

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setBoolean(1, isAdmin);
            statement.setString(2, username);
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}

