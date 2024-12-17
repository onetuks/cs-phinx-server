package com.onetuks.csphinxserver.domain.answer;

import java.time.LocalDateTime;

public class ChoiceAnswer extends Answer {

  private final String value;

  public ChoiceAnswer(
      String answerId,
      String questionId,
      AnswerType answerType,
      String value,
      LocalDateTime updatedAt) {
    super(answerId, questionId, answerType, updatedAt);
    this.value = value;
  }

  public ChoiceAnswer(
      String answerId,
      String questionId,
      AnswerType answerType,
      Object value,
      LocalDateTime updatedAt) {
    super(answerId, questionId, answerType, updatedAt);
    this.value = convertValueType(value);
  }

  public String value() {
    return this.value;
  }

  private String convertValueType(Object value) {
    if (!(value instanceof String)) {
      throw new IllegalArgumentException("Answer value is not a string");
    }

    return ((String) value).trim();
  }
}
