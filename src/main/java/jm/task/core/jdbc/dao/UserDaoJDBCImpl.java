package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String INSERT = "INSERT INTO pz114.users(name, lastname, age) VALUES(?, ?, ?)";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement())
        {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS pz114.users" +
                    "(id bigint primary key not null auto_increment," +
                    "name varchar(50)," +
                    "lastname varchar(50)," +
                    "age tinyint)");
            System.out.println("Table created!");
        } catch (SQLException e) {
            System.out.println("Error: при создании таблицы");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement())
        {
            statement.executeUpdate("DROP TABLE IF EXISTS pz114.users");
            System.out.println("Table droped");
        } catch (SQLException e) {
            System.out.println("Error: при удалении таблицы");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT))
        {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println(name + " " + lastName + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Error: при добавлении пользователя с именем:" + name
            + ", фамилией: " + lastName + ", возрастом: " + age);
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement())
        {
            statement.executeUpdate("DELETE FROM pz114.users WHERE id = " + id);
            System.out.println("Пользователь с id(" + id + ") удалён!");
        } catch (SQLException e) {
            System.out.println("Error: при удалении пользователя с id = " + id);
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List <User> users = new ArrayList<>();

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement())
        {
            ResultSet res = statement.executeQuery("SELECT * FROM pz114.users");

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
        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement())
        {
            statement.executeUpdate("TRUNCATE TABLE users");
            System.out.println("Table clean");
        } catch (SQLException e) {
            System.out.println("Error: при очистке таблицы");
            e.printStackTrace();
        }
    }
}
