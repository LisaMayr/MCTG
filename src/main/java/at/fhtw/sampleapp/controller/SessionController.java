package at.fhtw.sampleapp.controller;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.sampleapp.dal.Session;
import at.fhtw.sampleapp.dal.User;
import at.fhtw.persistence.dao.SessionDaoDb;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.UUID;
import java.util.Optional;

public class SessionController extends Controller {

    private SessionDaoDb SessionDaoDb;

    public SessionController() {
        this.SessionDaoDb = new SessionDaoDb(); // Assuming DAL handles database operations
    }

    // POST /sessions - Create a Session
    public Response addSession(Request request) {
        try {
            // get JSON body and map it to a User object
            User loginData = this.getObjectMapper().readValue(request.getBody(), User.class);

            // Authenticate User
            User user = this.authenticateUser(loginData.getUsername(), loginData.getPassword());

            if (user != null) {
                String usernameSession = user.getUsername().toString();
                String token = usernameSession + "-mtcgtoken";

                // create a new Session
                Session session = new Session(user.getId(), token);
                SessionDaoDb.save(session);

                return new Response(
                        HttpStatus.CREATED,
                        ContentType.JSON,
                        "{ \"message\": \"Success - Token: " + session.getToken() + "\" }"
                );
            } else {
                return new Response(
                        HttpStatus.UNAUTHORIZED,
                        ContentType.JSON,
                        "{ \"message\": \"Login failed\" }"
                );
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new Response(
                    HttpStatus.CONFLICT,
                    ContentType.JSON,
                    "{ \"message\" : \"" + e.getMessage() + "\" }"
            );
        }

        return new Response(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ContentType.JSON,
                "{ \"message\" : \"Internal Server Error\" }"
        );
    }

    // Helper function to authenticate the user
    private User authenticateUser(String username, String password) {
        Optional<User> userOptional = getUserDaoDb().getByName(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;  // Authentication failed
    }

    // DELETE /sessions - delete session ---  not required :D
    public Response deleteSession(Request request) {
        return null;
    };
}