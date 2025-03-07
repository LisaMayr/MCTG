package at.fhtw.sampleapp.controller;

import at.fhtw.persistence.dao.CardDaoDb;
import at.fhtw.persistence.dao.UserDaoDb;
import at.fhtw.persistence.dao.SessionDaoDb;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

public class Controller {
    @Getter
    @Setter
    private UserDaoDb userDaoDb;
    @Setter
    @Getter
    private CardDaoDb cardDaoDb;
    @Setter
    @Getter
    private SessionDaoDb sessionDaoDb;
    /*@Setter
    @Getter
    private PackageDaoDb packageDaoDb;*/

    @Getter
    private ObjectMapper objectMapper;

    public Controller() {
        this.userDaoDb = new UserDaoDb();
        this.objectMapper = new ObjectMapper();
        this.sessionDaoDb = new SessionDaoDb();
        this.cardDaoDb = new CardDaoDb();
        //this.packageDaoDb = new PackageDaoDb();
    }
}
