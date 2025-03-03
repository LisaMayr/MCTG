package at.fhtw.sampleapp.dal;

public class SpellCard extends Card {

    private final ElementType elementType;

    public SpellCard(int cardId, String name, double damage, ElementType elementType ) {
        super(cardId, name, damage, CardType.SPELLCARD);
        this.elementType = elementType;
    }
}