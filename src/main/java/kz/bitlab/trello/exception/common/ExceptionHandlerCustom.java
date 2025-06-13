package kz.bitlab.trello.exception.common;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static kz.bitlab.trello.utility.LocalizationProcessor.getLocalizedErrorMessagesForCode;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerCustom {

    @ExceptionHandler(TrelloException.class)
    public ResponseEntity<ErrorResponse> handleAppException(TrelloException ex, HttpServletRequest request) {
        ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);
        HttpStatus status =
                (responseStatus != null) ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
        String message = responseStatus != null & !responseStatus.reason().isEmpty() ? responseStatus.reason() : "error.internal_exception";
        log.error(ex.getMessage(), ex);
        return buildErrorResponse(status, message, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception e, HttpServletRequest request) {
        log.error("GenericException: {}", e.getMessage(), e);
        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR, "error.internal_exception", e.getMessage());
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            HttpStatus status, String message, String details) {
        ErrorResponse errorResponse =
                ErrorResponse.builder()
                        .id(UUID.randomUUID().toString())
                        .timestamp(getCurrentTimestamp())
                        .status(status)
                        .message(getLocalizedErrorMessagesForCode(message))
                        .details(details)
                        .build();
        return ResponseEntity.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    private String getCurrentTimestamp() {
        return Instant.now()
                .atZone(ZoneId.systemDefault())
                .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}
