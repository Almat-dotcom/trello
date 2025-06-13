package kz.bitlab.trello.exception;

import kz.bitlab.trello.exception.common.TrelloException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "error.user.not-exist")
public class UserNotExistException extends TrelloException {
    public UserNotExistException(String message) {
        super(message);
    }
}
