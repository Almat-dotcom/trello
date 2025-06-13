package kz.bitlab.trello.exception;

import kz.bitlab.trello.exception.common.TrelloException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PasswordNotMatchException extends TrelloException {
    public PasswordNotMatchException(String message) {
        super(message);
    }
}
