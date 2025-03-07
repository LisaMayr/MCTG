package at.fhtw.persistence.dao;

import at.fhtw.persistence.DbConnection;
import at.fhtw.sampleapp.dal.Card;
import at.fhtw.sampleapp.dal.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CardDaoDb implements Dao<Card> {

    @Override
    public Optional<Card> get(int id) {
        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                SELECT id, name, damage 
                FROM card 
                WHERE id=?
                """)
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Card(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3)
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Card> getByCardName(String name) {
        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                SELECT id, name, damage 
                FROM card 
                WHERE name=?
                """)
        ) {
            statement.setString(1, name);
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Card(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3)
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Card> getAll() {
        ArrayList<Card> result = new ArrayList<>();
        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                SELECT id, name, damage 
                FROM card 
                """)
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(new Card(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3)
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public void save(Card card) {
        try (PreparedStatement statement = DbConnection.getInstance().prepareStatement("""
                INSERT INTO card
                (id, name, damage) 
                VALUES (?, ?, ?);
                """)
        ) {
            statement.setString(1, card.getId());
            statement.setString(2, card.getName());
            statement.setDouble(3, card.getDamage());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Card card) {
        // ToDo: is this needed???
    }
    @Override
    public void delete(Card card) {
        // ToDo: is this needed???
    }
}
