package kz.bitlab.trello.exception;

import kz.bitlab.trello.exception.common.TrelloException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "error.task.not-exist")
public class TaskNotExistException extends TrelloException {
    public TaskNotExistException(String message) {
        super(message);
    }
}
