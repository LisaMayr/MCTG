package at.fhtw.sampleapp.dal;

public class SpellCard extends Card {
    private String elementType;
    public SpellCard(int cardId, String name, double damage, String elementType ) {
        super(cardId, name, damage);
        this.elementType = elementType;

    }

    @Override
    public String getCardType() {
        return "SpellCard";
    }

}
