package com.onetuks.csphinxserver.adapter.out.persistence.entity.answer;

import com.onetuks.csphinxserver.domain.answer.AnswerType;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@TypeAlias(value = "choiceAnswerEntity")
public class ChoiceAnswerEntity extends AnswerEntity {

  private String value;

  public ChoiceAnswerEntity(
      String id, String questionId, AnswerType answerType, Object value, LocalDateTime updatedAt) {
    super(id, questionId, answerType, updatedAt);
    this.value = convertValueType(value);
  }

  private String convertValueType(Object value) {
    if (!(value instanceof String)) {
      throw new IllegalArgumentException("Choice Value is must be String");
    }

    return (String) value;
  }
}
