package at.fhtw.sampleapp.service.card;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.sampleapp.controller.Controller;
import at.fhtw.sampleapp.dal.Card;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class CardController extends Controller {

    public CardController() {

        // Nur noch für die Dummy-JUnit-Tests notwendig. Stattdessen ein RepositoryPattern verwenden.
    }

    // GET /cardPackage
    public Response getCardPackage()
    {
        try {
            List<Card> cardPackage = this.getCardDAL().getCardPackage();
            // "[ { \"id\": 1, \"city\": \"Vienna\", \"temperature\": 9.0 }, { ... }, { ... } ]"
            String cardDataJSON = this.getObjectMapper().writeValueAsString(cardPackage);

            return new Response(
                    HttpStatus.OK,
                    ContentType.JSON,
                    cardDataJSON
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

    // POST /cardPackage
    public Response addCardPackage(Request request) {
        try {
            // request.getBody() => "{ \"id\": 4, \"city\": \"Graz\", ... }
            List <Card> cardPackage = this.getObjectMapper().readValue(request.getBody(), new TypeReference<>(){});
            CardDAL cardDAL = this.getCardDAL();
            cardDAL.addCardPackage(cardPackage);

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
