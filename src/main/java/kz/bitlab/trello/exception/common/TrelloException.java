package kz.bitlab.trello.exception.common;

import lombok.Getter;

@Getter
public class TrelloException extends RuntimeException {
    protected TrelloException(String message) {
        super(message);
    }
}
