package at.fhtw.sampleapp.service.session;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.sampleapp.controller.Controller;
import at.fhtw.sampleapp.dal.Session;
import com.fasterxml.jackson.core.JsonProcessingException;

public class SessionController extends Controller {

    public SessionController() {

        // Nur noch für die Dummy-JUnit-Tests notwendig. Stattdessen ein RepositoryPattern verwenden.
    }

    // POST /session
    public Response addSession(Request request) {
        try {

            // request.getBody() => "{ \"id\": 4, \"city\": \"Graz\", ... }
            Session session = this.getObjectMapper().readValue(request.getBody(), Session.class);
            this.getSessionDAL().addSession(this.getUserDAL(), session);

            return new Response(
                HttpStatus.CREATED,
                ContentType.JSON,
                "{ \"message\": \"Success -Token: "+session.getUsername()+"-"+session.getToken()+"\" }"
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
