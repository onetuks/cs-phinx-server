package com.onetuks.csphinxserver.adapter.out.persistence.entity.answer;

import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "descriptive_answers")
public class DescriptiveAnswerEntity {

  @Id
  private String id;
  private String questionId;
  private List<Double> embeddedVector;
  private LocalDateTime updatedAt;

  public DescriptiveAnswerEntity(String questionId, List<Double> embeddedVector,
      LocalDateTime updatedAt) {
    this.questionId = questionId;
    this.embeddedVector = embeddedVector;
    this.updatedAt = updatedAt;
  }
}
