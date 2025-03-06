package at.fhtw.persistence.dao;

import at.fhtw.sampleapp.dal.User;
import at.fhtw.persistence.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public class UserDaoDb implements Dao<User> {

    @Override
    public Optional<User> get(int id) {
        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                SELECT id, username, password, token 
                FROM "user" 
                WHERE id=?
                """)
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<User> getByName(String username) {
        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                SELECT id, username, password, token 
                FROM "user" 
                WHERE username=?
                """)
        ) {
            statement.setString(1, username);
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Collection<User> getAll() {
        ArrayList<User> result = new ArrayList<>();
        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                SELECT id, username, password, token 
                FROM "user" 
                """)
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public void save(User user) {
        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                INSERT INTO "user"
                (username, password, token) 
                VALUES (?, ?, ?);
                """)
        ) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getToken());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        // ToDo: update more fields
        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                UPDATE "user" 
                SET username = COALESCE(?, username),   
                    password = COALESCE(?, password)
                WHERE id = ?;
                """)
        ) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getId());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                DELETE FROM "user" 
                WHERE id = ?;
                """)
        ) {
            statement.setInt(1, user.getId());
            int rowsAffected = statement.executeUpdate(); // Use executeUpdate() for DELETE

            if (rowsAffected == 0) {
                System.err.println("No user found with ID: " + user.getId());
            } else {
                System.out.println("User with ID " + user.getId() + " deleted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}