package com.onetuks.csphinxserver.domain.answer;

import java.time.LocalDateTime;

public class DescriptiveAnswer extends Answer {

  private final EmbeddingValue value;

  public DescriptiveAnswer(
      String answerId,
      String questionId,
      AnswerType answerType,
      EmbeddingValue value,
      LocalDateTime updatedAt) {
    super(answerId, questionId, answerType, updatedAt);
    this.value = value;
  }

  public DescriptiveAnswer(
      String answerId,
      String questionId,
      AnswerType answerType,
      Object value,
      LocalDateTime updatedAt) {
    super(answerId, questionId, answerType, updatedAt);
    this.value = convertValueType(value);
  }

  public EmbeddingValue value() {
    return value;
  }

  private EmbeddingValue convertValueType(Object value) {
    if (!(value instanceof EmbeddingValue)) {
      throw new IllegalArgumentException("Value is not a EmbeddingValue");
    }

    return (EmbeddingValue) value;
  }
}
