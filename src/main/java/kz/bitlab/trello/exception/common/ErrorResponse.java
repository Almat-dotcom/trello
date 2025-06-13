package kz.bitlab.trello.exception.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Locale;
import java.util.Map;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
  @JsonProperty("id")
  private String id;

  @JsonProperty("timestamp")
  private String timestamp;

  @JsonProperty("code")
  private HttpStatus status;

  @JsonProperty("message")
  private Map<Locale, String> message;

  @JsonProperty("details")
  private String details;
}