package com.onetuks.csphinxserver.adapter.out.persistence.entity.answer;

import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "short_answers")
public class ShortAnswerEntity {

  @Id
  private String id;
  private String questionId;
  private Set<String> answerWords;
  private LocalDateTime updatedAt;

  public ShortAnswerEntity(String questionId, Set<String> answerWords, LocalDateTime updatedAt) {
    this.questionId = questionId;
    this.answerWords = answerWords;
    this.updatedAt = updatedAt;
  }
}
