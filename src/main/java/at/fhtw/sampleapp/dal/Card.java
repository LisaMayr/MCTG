package at.fhtw.sampleapp.dal;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.lang.annotation.ElementType;

public abstract class Card {
    @JsonAlias({"cardId"})
    private int cardId;
    @JsonAlias({"name"})
    private String name;
    @JsonAlias({"damage"})
    private double damage;

    public Card() {
    }

    public Card(int cardId, String name, double damage) {
        this.cardId = cardId;
        this.name = name;
        this.damage = damage;
    }

    public abstract String getCardType();
}

