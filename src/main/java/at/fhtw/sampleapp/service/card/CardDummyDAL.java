package at.fhtw.sampleapp.service.card;

import at.fhtw.sampleapp.dal.Card;
import java.util.LinkedList;
import java.util.List;


public class CardDummyDAL implements CardDAL {
    private List<List<Card>> packages;

    public CardDummyDAL() {
        packages = new LinkedList<>();
    }

    // GET /package/:
    public List<Card> getCardPackage() {
        try{
            List<Card> cardPackage = packages.getFirst();
            packages.remove(cardPackage);
            return cardPackage;
        }catch(Exception e){
            throw new Error("No package found");
        }
    }

    // POST /package
    public void addCardPackage(List<Card> cardPackage) {
        this.packages.add(cardPackage);

    }
}
