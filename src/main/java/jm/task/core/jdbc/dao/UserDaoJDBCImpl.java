package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String INSERT = "INSERT INTO users(name, lastname, age) VALUES(?, ?, ?)";
    private static final String CREATE = "CREATE TABLE IF NOT EXISTS users " +
            "(id bigint primary key not null auto_increment, " +
            "name varchar(50), " +
            "lastname varchar(50), " +
            "age tinyint)";
    private static final String DROP = "DROP TABLE IF EXISTS users";
    private static final String REMOVE = "DELETE FROM users WHERE id = ?";
    private static final String GET = "SELECT * FROM users";
    private static final String CLEAN = "TRUNCATE TABLE users";

    Connection connection = (Connection) Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(PreparedStatement preparedStatement = connection.prepareStatement(CREATE))
        {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: при создании таблицы");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(PreparedStatement preparedStatement = connection.prepareStatement(DROP))
        {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: при удалении таблицы");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT))
        {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: при добавлении пользователя с именем:" + name
            + ", фамилией: " + lastName + ", возрастом: " + age);
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE))
        {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: при удалении пользователя с id = " + id);
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List <User> users = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET))
        {
            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {
                User user = new User();
                user.setId(res.getLong("id"));
                user.setName(res.getString("name"));
                user.setLastName(res.getString("lastname"));
                user.setAge(res.getByte("age"));

                users.add(user);
            }
        } catch (Exception e) {
            System.out.println("Error: при получении списка пользователей");
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try(PreparedStatement preparedStatement = connection.prepareStatement(CLEAN))
        {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: при очистке таблицы");
            e.printStackTrace();
        }
    }
}
