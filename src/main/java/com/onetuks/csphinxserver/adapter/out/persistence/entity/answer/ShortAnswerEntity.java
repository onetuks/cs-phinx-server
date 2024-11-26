package com.onetuks.csphinxserver.adapter.out.persistence.entity.answer;

import com.onetuks.csphinxserver.domain.answer.AnswerType;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@TypeAlias(value = "shortAnswerEntity")
public class ShortAnswerEntity extends AnswerEntity {

  private Set<String> value;

  public ShortAnswerEntity(
      String id, String questionId, AnswerType answerType, Object value, LocalDateTime updatedAt) {
    super(id, questionId, answerType, updatedAt);
    this.value = convertValueType(value);
  }

  private Set<String> convertValueType(Object value) {
    if (!(value instanceof Set<?>)) {
      throw new IllegalArgumentException("Value is not a set of values");
    }

    return (Set<String>) value;
  }
}
