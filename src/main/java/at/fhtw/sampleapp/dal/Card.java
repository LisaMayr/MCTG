package at.fhtw.sampleapp.dal;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;

enum CardType {
    SPELLCARD,
    MONSTERCARD
}

public class Card {
    @JsonAlias({"cardId"})
    private int cardId;
    @JsonAlias({"name"})
    private String name;
    @JsonAlias({"damage"})
    private double damage;
    @Getter
    @JsonAlias({"cardType"})
    private CardType cardType;

    public Card() {
    }

    public Card(int cardId, String name, double damage, CardType cardType) {
        this.cardId = cardId;
        this.name = name;
        this.damage = damage;
        this.cardType = cardType;
    }
}