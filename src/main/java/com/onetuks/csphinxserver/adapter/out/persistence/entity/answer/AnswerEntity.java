package com.onetuks.csphinxserver.adapter.out.persistence.entity.answer;

import com.onetuks.csphinxserver.domain.answer.AnswerType;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@TypeAlias(value = "answerEntity")
@Document(collection = "answers")
public abstract class AnswerEntity {

  @Id private String id;

  @Field(name = "question_id")
  private String questionId;

  @Field(name = "answer_type")
  private AnswerType answerType;

  @Filter(name = "updated_at")
  private LocalDateTime updatedAt;

  public AnswerEntity(String questionId, AnswerType answerType, LocalDateTime updatedAt) {
    this.questionId = questionId;
    this.answerType = answerType;
    this.updatedAt = updatedAt;
  }

  public abstract Object getValue();
}
