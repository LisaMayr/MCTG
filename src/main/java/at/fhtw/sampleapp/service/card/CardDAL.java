package at.fhtw.sampleapp.service.card;

import at.fhtw.sampleapp.dal.Card;
import java.util.List;

public interface CardDAL {
    List<Card> getCardPackage();
    void addCardPackage(List<Card> cardPackage);
}
