package at.fhtw.sampleapp.controller;

import at.fhtw.persistence.dao.UserDaoDb;
import at.fhtw.sampleapp.service.card.CardDAL;
import at.fhtw.sampleapp.service.card.CardDummyDAL;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

public class Controller {
    @Getter
    @Setter
    private UserDaoDb userDaoDb;
    @Setter
    @Getter
    private CardDAL cardDAL;

    @Getter
    private ObjectMapper objectMapper;

    public Controller() {
        this.userDaoDb = new UserDaoDb();
        this.objectMapper = new ObjectMapper();
        this.cardDAL = new CardDummyDAL();
       // this.sessionDAL = new SessionDummyDAL();
    }
}
