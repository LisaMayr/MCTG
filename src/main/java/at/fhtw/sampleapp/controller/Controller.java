package at.fhtw.sampleapp.controller;

import at.fhtw.sampleapp.service.card.CardDAL;
import at.fhtw.sampleapp.service.card.CardDummyDAL;
import at.fhtw.sampleapp.service.session.SessionDummyDAL;
import at.fhtw.sampleapp.service.user.UserDummyDAL;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

public class Controller {
    @Getter
    @Setter
    private UserDummyDAL userDAL;
    @Setter
    @Getter
    private SessionDummyDAL sessionDAL;
    @Setter
    @Getter
    private CardDAL cardDAL;

    @Getter
    private ObjectMapper objectMapper;

    public Controller() {
        this.userDAL = new UserDummyDAL();
        this.objectMapper = new ObjectMapper();
        this.cardDAL = new CardDummyDAL();
        this.sessionDAL = new SessionDummyDAL();
    }
}
