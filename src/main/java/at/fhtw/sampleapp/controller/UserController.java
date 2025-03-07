package at.fhtw.sampleapp.controller;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.persistence.dao.UserDaoDb;
import at.fhtw.sampleapp.dal.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.SneakyThrows;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

//only is accessed by curlscript
public class UserController extends Controller {

    public UserController() {

    }

    // GET /user(:username
    public Response getUser(String username)
    {
        try {//ToDo: create method in UserDaoDb to get user by username insert here
            Optional<User> user = this.getUserDaoDb().getByName(username);
            if (!user.isPresent()) {
                // ToDO:fehlermeldung
            }
            String userDataJSON = this.getObjectMapper().writeValueAsString(user.get());

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
            //Check if the username of requestbody is empty
            if (user.getUsername() == null || user.getUsername().isBlank()) {
                return new Response(
                        HttpStatus.BAD_REQUEST,
                        ContentType.JSON,
                        "{ \"message\" : \"Username cannot be empty\" }"
                );
            }
            //Check if the user already exists in the db
            if(this.getUserDaoDb().getByName(user.getUsername()).isPresent()){
                return new Response(
                        HttpStatus.CONFLICT,
                        ContentType.JSON,
                        "{ \"message\" : \"User already exists\" }"
                );
            }
            UserDaoDb userDaoDb = this.getUserDaoDb();
            userDaoDb.save(user);

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

    // PUT /user
    /*public Response update(Request request) {
        try {

            // request.getBody() => "{ \"id\": 4, ... }
            User user = this.getObjectMapper().readValue(request.getBody(), User.class);
            UserDaoDb userDaoDb = this.getUserDaoDb();
            System.out.println(user);
            userDaoDb.update(user);

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

    }*/

    public Response update(Request request) {
        try {
            User user = this.getObjectMapper().readValue(request.getBody(), User.class);

            if (user.getId() == null) {
                return new Response(
                        HttpStatus.BAD_REQUEST,
                        ContentType.JSON,
                        "{ \"message\": \"User ID is required for update\" }"
                );
            }

            Optional<User> existingUser = this.getUserDaoDb().get(user.getId());
            if (existingUser.isEmpty()) {
                return new Response(
                        HttpStatus.NOT_FOUND,
                        ContentType.JSON,
                        "{ \"message\": \"User not found\" }"
                );
            }

            // Merge existing user data with new data
            User updatedUser = existingUser.get();
            if (user.getUsername() != null) updatedUser.setUsername(user.getUsername());
            if (user.getPassword() != null) updatedUser.setPassword(user.getPassword());

            this.getUserDaoDb().update(updatedUser);

            return new Response(
                    HttpStatus.OK,
                    ContentType.JSON,
                    "{ \"message\": \"User updated successfully\" }"
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new Response(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ContentType.JSON,
                    "{ \"message\": \"Internal Server Error\" }"
            );
        }
    }
}
