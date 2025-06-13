package kz.bitlab.trello.utility;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static kz.bitlab.trello.enums.Locale.*;

@Component
public class LocalizationProcessor {
  private static MessageSource messageSource;
  private static final List<Locale> availableLocales =
          List.of(ENGLISH.getLocale(), RUSSIAN.getLocale(), KAZAKH.getLocale());

  public LocalizationProcessor(MessageSource messageSource) {
    LocalizationProcessor.messageSource = messageSource;
  }

  public static Map<Locale, String> getLocalizedErrorMessagesForCode(String errorCode) {
    var localizedMessages = new HashMap<Locale, String>();

    for (java.util.Locale locale : availableLocales) {
      String errorMessage = messageSource.getMessage(errorCode, null, locale);
      localizedMessages.put(locale, errorMessage);
    }

    return localizedMessages;
  }
}
