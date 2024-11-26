package com.onetuks.csphinxserver.domain.answer;

import java.time.LocalDateTime;
import java.util.Set;

public class ShortAnswer extends Answer {

  private final Set<String> value;

  public ShortAnswer(
      String answerId, String questionId,
      AnswerType answerType, Set<String> value, LocalDateTime updatedAt) {
    super(answerId, questionId, answerType, updatedAt);
    this.value = value;
  }

  public ShortAnswer(
      String answerId, String questionId,
      AnswerType answerType, Object value, LocalDateTime updatedAt) {
    super(answerId, questionId, answerType, updatedAt);
    this.value = convertValueType(value);
  }

  public Set<String> value() {
    return value;
  }

  private Set<String> convertValueType(Object value) {
    if (!(value instanceof Set<?>)) {
      throw new IllegalArgumentException("Value must be a set of strings");
    }

    return (Set<String>) value;
  }
}
