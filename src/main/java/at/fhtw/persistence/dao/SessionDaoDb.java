package at.fhtw.persistence.dao;

import at.fhtw.persistence.DbConnection;
import at.fhtw.sampleapp.dal.Session;
import at.fhtw.sampleapp.dal.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class SessionDaoDb implements Dao<Session> {

    @Override
    public Optional<Session> get(int userid) {
        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                SELECT id, userid, token 
                FROM session 
                WHERE userid=?
                """)
        ) {
            statement.setInt(1, userid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                //int userid = resultSet.getInt("userid");
                String token = resultSet.getString("token");
                return Optional.of(new Session(userid, token)); // Return session if found
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    // READ: Get all sessions
    @Override
    public Collection<Session> getAll() {
        ArrayList<Session> result = new ArrayList<>();
        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                SELECT id, userid, token 
                FROM session 
                """)
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(new Session(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3)
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    // CREATE: Save new session
    @Override
    public void save(Session session) {

    }

    // DELETE: Delete a session
    @Override
    public void delete(Session session) {

    }

    @Override
    public void update(Session session) {}

}
