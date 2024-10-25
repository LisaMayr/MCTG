package at.fhtw.sampleapp.service.weather;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.sampleapp.controller.Controller;
import at.fhtw.sampleapp.dal.User;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class UserController extends Controller {
    private UserDummyDAL userDAL;

    public UserController() {

        // Nur noch für die Dummy-JUnit-Tests notwendig. Stattdessen ein RepositoryPattern verwenden.
        this.userDAL = new UserDummyDAL();
    }

    // GET /user(:username
    public Response getUser(String username)
    {
        try {
            User user = this.userDAL.getUser(username);
            // "[ { \"id\": 1, \"city\": \"Vienna\", \"temperature\": 9.0 }, { ... }, { ... } ]"
            String userDataJSON = this.getObjectMapper().writeValueAsString(user);

            return new Response(
                    HttpStatus.OK,
                    ContentType.JSON,
                    userDataJSON
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new Response(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ContentType.JSON,
                    "{ \"message\" : \"Internal Server Error\" }"
            );
        }
    }
    // GET /users
    public Response getUsers() {
        try {
            List userData = this.userDAL.getUsers();
            // "[ { \"id\": 1, \"city\": \"Vienna\", \"temperature\": 9.0 }, { ... }, { ... } ]"
            String userDataJSON = this.getObjectMapper().writeValueAsString(userData);

            return new Response(
                HttpStatus.OK,
                ContentType.JSON,
                userDataJSON
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new Response(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ContentType.JSON,
                "{ \"message\" : \"Internal Server Error\" }"
            );
        }
    }

    // POST /user
    public Response addUser(Request request) {
        try {

            // request.getBody() => "{ \"id\": 4, \"city\": \"Graz\", ... }
            User user = this.getObjectMapper().readValue(request.getBody(), User.class);
            this.userDAL.addUser(user);

            return new Response(
                HttpStatus.CREATED,
                ContentType.JSON,
                "{ \"message\": \"Success\" }"
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new Response(
                    HttpStatus.CONFLICT,
                    ContentType.JSON,
                    "{ \"message\" : \""+e.getMessage()+"\" }"
            );
        }

        return new Response(
            HttpStatus.INTERNAL_SERVER_ERROR,
            ContentType.JSON,
            "{ \"message\" : \"Internal Server Error\" }"
        );

    }
}
