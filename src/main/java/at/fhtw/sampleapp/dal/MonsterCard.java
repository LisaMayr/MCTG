package at.fhtw.sampleapp.dal;

public class MonsterCard extends Card {

    public MonsterCard(int cardId, String name, double damage) {
        super(cardId, name, damage);
    }
    @Override
    public String getCardType () {
        return "Monster";
    }
}
