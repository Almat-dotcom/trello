package kz.bitlab.trello.enums;

import lombok.Getter;

@Getter
public enum LocaleKey {
  EN("en"),
  RU("ru"),
  KK("kk");

  private final String value;

  LocaleKey(String value) {
    this.value = value;
  }
}
