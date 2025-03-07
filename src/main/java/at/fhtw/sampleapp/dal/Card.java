package at.fhtw.sampleapp.dal;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

public class Card {
    @Getter
    @Setter
    @JsonAlias({"Id"})
    private String id;
    @Getter
    @Setter
    @JsonAlias({"Name"})
    private String name;
    @Getter
    @Setter
    @JsonAlias({"Damage"})
    private Double damage;

    public Card() {
    }

    public Card(String id, String name, Double damage) {
        this.id = id;
        this.name = name;
        this.damage = damage;
    }

    @Override
    public String toString() {
        return "Card{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", damage=" + damage +
               '}';
    }
}