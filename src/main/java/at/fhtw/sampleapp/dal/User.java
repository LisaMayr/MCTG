package at.fhtw.sampleapp.dal;

import javax.smartcardio.Card;
import java.util.List;

public class User {
    private String username;
    private String lastname;
    private String firstname;
    private String email;
    private String password;
    private Integer elo;
    private Integer coins;
    private List<Card> cards;


    public User(String username, String lastname, String firstname, String email, String password, Integer elo, Integer coins, List<Card> cards) {
        this.username = username;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.elo = elo;
        this.coins = coins;
        this.cards = cards;
    }

    public User() {
    }
}
