package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public UserDAO() {}

    public User getUserById(int id) throws SQLException {
        Connection connection = DataBase.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from users where id = ?;");
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();
        if (!rs.next()) return null;

        return new User(rs.getString("username"), rs.getString("password"), rs.getString("avatar"), rs.getBoolean("is_admin"));
    }

    public static String getPasswordByUsername(String username) throws SQLException {
        Connection connection = DataBase.getConnection();
        PreparedStatement statement = connection.prepareStatement("select password from users where username = ?;");
        statement.setString(1, username);

        ResultSet rs = statement.executeQuery();
        if (!rs.next()) return "";

        return rs.getString("password");
    }

    public static boolean successLogin(String username, String password) throws SQLException {
       return getPasswordByUsername(username).equals(password);
    }

    public static void addUser(User user) throws SQLException {
        Connection connection = DataBase.getConnection();
        String query = "INSERT INTO users (username, password, avatar, is_admin) VALUES(?, ?, ?, ?);";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getAvatar());
        statement.setBoolean(4, user.getAdmin());

        statement.execute();
    }

    public static boolean usernameExists(String username) throws SQLException {
        Connection connection = DataBase.getConnection();
        String query = "SELECT COUNT(*) AS count FROM users WHERE username = ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);

        ResultSet rs = statement.executeQuery();
        if (!rs.next()) return false;

        return rs.getInt("count") > 0;
    }

}
