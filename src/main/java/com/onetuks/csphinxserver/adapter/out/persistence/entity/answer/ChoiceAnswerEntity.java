package com.onetuks.csphinxserver.adapter.out.persistence.entity.answer;

import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "choice_answers")
public class ChoiceAnswerEntity {

  @Id
  private String id;
  private String questionId;
  private String answerNumber;
  private LocalDateTime updatedAt;

  public ChoiceAnswerEntity(String questionId, String answerNumber, LocalDateTime updatedAt) {
    this.questionId = questionId;
    this.answerNumber = answerNumber;
    this.updatedAt = updatedAt;
  }
}
