package at.fhtw.sampleapp.service;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.http.Method;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.server.Service;
import at.fhtw.sampleapp.controller.CardController;

public class CardService implements Service {
    private final CardController cardController;

    public CardService() {
        this.cardController = new CardController();
    }

    @Override
    public Response handleRequest(Request request) {
        if (request.getMethod() == Method.GET &&
            request.getPathParts().size() > 1) {
            return this.cardController.getCard(request.getPathParts().get(1));
        } else if (request.getMethod() == Method.GET) {
            return this.cardController.getCards();
        } else if (request.getMethod() == Method.POST) {
            return this.cardController.addCard(request);
        }

        return new Response(
                HttpStatus.BAD_REQUEST,
                ContentType.JSON,
                "[]"
        );
    }
}
