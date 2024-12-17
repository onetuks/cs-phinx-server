package com.onetuks.csphinxserver.domain.answer;

import java.time.LocalDateTime;

public class ShortAnswer extends Answer {

  private final String value;

  public ShortAnswer(
      String answerId,
      String questionId,
      AnswerType answerType,
      String value,
      LocalDateTime updatedAt) {
    super(answerId, questionId, answerType, updatedAt);
    this.value = value;
  }

  public ShortAnswer(
      String answerId,
      String questionId,
      AnswerType answerType,
      Object value,
      LocalDateTime updatedAt) {
    super(answerId, questionId, answerType, updatedAt);
    this.value = convertValueType(value);
  }

  public String value() {
    return value;
  }

  private String convertValueType(Object value) {
    if (!(value instanceof String)) {
      throw new IllegalArgumentException("Value must be a set of strings");
    }

    return (String) value;
  }
}
