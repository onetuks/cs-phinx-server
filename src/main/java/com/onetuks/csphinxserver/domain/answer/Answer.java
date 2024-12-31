package com.onetuks.csphinxserver.domain.answer;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
public abstract class Answer {

  private final String answerId;
  private final String questionId;
  private final AnswerType answerType;
  private final LocalDateTime updatedAt;

  public String answerId() {
    return this.answerId;
  }

  public String questionId() {
    return this.questionId;
  }

  public AnswerType answerType() {
    return this.answerType;
  }

  public LocalDateTime updatedAt() {
    return this.updatedAt;
  }

  public abstract Object value();
}
