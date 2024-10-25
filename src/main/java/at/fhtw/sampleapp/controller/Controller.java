package at.fhtw.sampleapp.controller;

import at.fhtw.sampleapp.service.weather.SessionDummyDAL;
import at.fhtw.sampleapp.service.weather.UserDummyDAL;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Controller {
    private UserDummyDAL userDAL;
    private SessionDummyDAL sessionDAL;

    private ObjectMapper objectMapper;

    public Controller() {
        this.objectMapper = new ObjectMapper();
        this.userDAL = new UserDummyDAL();
        this.sessionDAL = new SessionDummyDAL();

    }

    public void setUserDAL(UserDummyDAL userDAL) {
        this.userDAL = userDAL;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public UserDummyDAL getUserDAL() {
        return userDAL;
    }

    public SessionDummyDAL getSessionDAL() {
        return sessionDAL;
    }
}
