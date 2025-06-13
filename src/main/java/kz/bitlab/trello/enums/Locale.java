package kz.bitlab.trello.enums;

import lombok.Getter;

import static kz.bitlab.trello.enums.LocaleKey.*;

@Getter
public enum Locale {
  ENGLISH(new java.util.Locale(EN.getValue())),
  RUSSIAN(new java.util.Locale(RU.getValue())),
  KAZAKH(new java.util.Locale(KK.getValue()));

  private final java.util.Locale locale;

  Locale(java.util.Locale locale) {
    this.locale = locale;
  }
}
