package kz.bitlab.trello.exception;

import kz.bitlab.trello.exception.common.TrelloException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameAlreadyExistException extends TrelloException {
    public UsernameAlreadyExistException(String message) {
        super(message);
    }
}
