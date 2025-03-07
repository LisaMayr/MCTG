package at.fhtw.sampleapp.controller;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.sampleapp.dal.Card;
import at.fhtw.persistence.dao.CardDaoDb;
import at.fhtw.sampleapp.dal.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CardController extends Controller {
    private final CardDaoDb cardDaoDb;

    public CardController() {
        this.cardDaoDb = new CardDaoDb();
        // Nur noch für die Dummy-JUnit-Tests notwendig. Stattdessen ein RepositoryPattern verwenden.

    }

    // GET /card(:name
    public Response getCard(String username)
    {
        try {
            Optional<Card> card = this.getCardDaoDb().getByCardName(username);
            String userDataJSON = this.getObjectMapper().writeValueAsString(card.get());

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

    // GET /cards
    public Response getCards()
    {
        try {
            List<Card> cardData = this.cardDaoDb.getAll();
            String cardDataJSON = this.getObjectMapper().writeValueAsString(cardData);

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

    // POST /card
    public Response addCard(Request request) {
        try {
            List<Card> cardData = this.cardDaoDb.getAll();

            Card card = this.getObjectMapper().readValue(request.getBody(), Card.class);

            if (cardData.contains(card)) {
                throw new IllegalArgumentException("Card already exists");
            }

            this.cardDaoDb.save(card);


            return new Response(
                    HttpStatus.CREATED,
                    ContentType.JSON,
                    "{ \"message\": \"Success\" }"
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            return new Response(
                    HttpStatus.CONFLICT,
                    ContentType.JSON,
                    "{ \"message\": \"" + e.getMessage() + "\" }"
            );
        }

        return new Response(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ContentType.JSON,
                "{ \"message\" : \"Internal Server Error\" }"
        );
    }
}