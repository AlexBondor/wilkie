package model.dao;

import model.User;
import utils.MySQLConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Alex, 1/9/2016.
 */
public class UserDAO {

    private Connection connection;
    private Statement statement;

    public UserDAO() {

    }

    public ArrayList<User> getAllUsers() throws SQLException {
        ResultSet resultSet;
        User user;
        ArrayList<User> usersList = new ArrayList<User>();
        try {
            connection = MySQLConnector.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user");
            while (resultSet.next()) {
                user = new User(
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("name")
                );
                usersList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public User getUserById(int id) {
        ResultSet resultSet;
        User user = null;
        try {
            connection = MySQLConnector.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user WHERE id='" + String.valueOf(id) + "'");
            if (resultSet.first()) {
                user = new User(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public User addUser(User user) {
        String email;
        try {
            email = user.getEmail();
            final String password = user.getPassword();
            final String name = user.getName();
            connection = MySQLConnector.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO user(email, password, name) VALUES ('" + email + "', '"
                    + password + "', '" + name + "')");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return getUserByEmail(email);
    }

    public User getUserByEmail(String email) {
        ResultSet resultSet;
        User user = null;
        try {
            connection = MySQLConnector.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user WHERE email='" + email + "'");
            if (resultSet.first()) {
                user = new User(
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("name")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
