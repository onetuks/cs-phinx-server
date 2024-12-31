package com.onetuks.csphinxserver.adapter.out.persistence.entity.answer;

import com.onetuks.csphinxserver.domain.answer.AnswerType;
import com.onetuks.csphinxserver.domain.answer.EmbeddingValue;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@TypeAlias(value = "descriptive_answer_entity")
public class DescriptiveAnswerEntity extends AnswerEntity {

  private EmbeddingValue value;

  public DescriptiveAnswerEntity(
      String id, String questionId, AnswerType answerType, Object value, LocalDateTime updatedAt) {
    super(id, questionId, answerType, updatedAt);
    this.value = convertValueType(value);
  }

  private EmbeddingValue convertValueType(Object value) {
    if (!(value instanceof EmbeddingValue)) {
      throw new IllegalArgumentException("Value is not a embedding value");
    }

    return (EmbeddingValue) value;
  }
}
