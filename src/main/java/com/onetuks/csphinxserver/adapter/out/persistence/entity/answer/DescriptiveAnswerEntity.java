package com.onetuks.csphinxserver.adapter.out.persistence.entity.answer;

import com.onetuks.csphinxserver.domain.answer.AnswerType;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@TypeAlias(value = "descriptiveAnswerEntity")
public class DescriptiveAnswerEntity extends AnswerEntity {

  private List<Double> value;

  public DescriptiveAnswerEntity(
      String id, String questionId, AnswerType answerType, Object value, LocalDateTime updatedAt) {
    super(id, questionId, answerType, updatedAt);
    this.value = convertValueType(value);
  }

  private List<Double> convertValueType(Object value) {
    if (!(value instanceof List<?>)) {
      throw new IllegalArgumentException("Value is not a list");
    }

    return (List<Double>) value;
  }
}
