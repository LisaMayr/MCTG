package at.fhtw.sampleapp.controller;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.persistence.dao.UserDaoDb;
import at.fhtw.sampleapp.dal.User;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Collections;
import java.util.List;
//only is accessed by curlscript
public class UserController extends Controller {

    public UserController() {

    }

    // GET /user(:username
    public Response getUser(String username)
    {
        try {//ToDo: create method in UserDaoDb to get user by username insert here
            //User user = this.getUserDaoDb().getUser(username);
            User user = new User();
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
        try {//ToDo: insert method from UserDaoDb to get Alluseres
            List userData = Collections.singletonList(this.getUserDaoDb().getAll());
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

            // request.getBody() => "{ \"id\": 4, ... }
            User user = this.getObjectMapper().readValue(request.getBody(), User.class);
            UserDaoDb userDaoDb = this.getUserDaoDb();
            userDaoDb.saveUser(user);

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
