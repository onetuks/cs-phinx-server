package com.onetuks.csphinxserver.adapter.out.persistence.entity;

import com.onetuks.csphinxserver.domain.answer.AnswerType;
import java.time.LocalDateTime;
import java.util.List;
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
public class AnswerEntity {

  @Id private String id;

  @Field(name = "question_id")
  private String questionId;

  @Field(name = "answer_type")
  private AnswerType answerType;

  @Field(name = "answer_values")
  private List<String> answerValues;

  @Filter(name = "updated_at")
  private LocalDateTime updatedAt;

  public AnswerEntity(
      String questionId,
      AnswerType answerType,
      List<String> answerValues,
      LocalDateTime updatedAt) {
    this.questionId = questionId;
    this.answerType = answerType;
    this.answerValues = answerValues;
    this.updatedAt = updatedAt;
  }
}
