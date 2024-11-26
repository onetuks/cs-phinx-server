package com.onetuks.csphinxserver.domain.answer;

import java.time.LocalDateTime;
import java.util.List;

public class DescriptiveAnswer extends Answer {

  private final List<Double> value;

  public DescriptiveAnswer(
      String answerId, String questionId,
      AnswerType answerType, List<Double> value, LocalDateTime updatedAt) {
    super(answerId, questionId, answerType, updatedAt);
    this.value = value;
  }

  public DescriptiveAnswer(
      String answerId, String questionId,
      AnswerType answerType, Object value, LocalDateTime updatedAt) {
    super(answerId, questionId, answerType, updatedAt);
    this.value = convertValueType(value);
  }

  public List<Double> value() {
    return value;
  }

  private List<Double> convertValueType(Object value) {
    if (!(value instanceof List<?>)) {
      throw new IllegalArgumentException("Value is not a list");
    }

    return (List<Double>) value;
  }
}
