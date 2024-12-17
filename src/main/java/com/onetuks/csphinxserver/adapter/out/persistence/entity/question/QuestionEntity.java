package com.onetuks.csphinxserver.adapter.out.persistence.entity.question;

import com.onetuks.csphinxserver.domain.question.Category;
import com.onetuks.csphinxserver.domain.question.Difficulty;
import com.onetuks.csphinxserver.domain.question.Topic;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Document(collection = "questions")
public class QuestionEntity {

  @Id private String id;
  private String title;
  private String description;
  private Difficulty difficulty;
  private Integer timeLimit;
  private Category category;
  private Topic topic;
  private Set<String> tags;
  private LocalDateTime updatedAt;
  private Integer likeCount;
  private Integer attemptCount;
  private Integer solvedCount;

  public QuestionEntity(
      String title,
      String description,
      Difficulty difficulty,
      Integer timeLimit,
      Category category,
      Topic topic,
      Set<String> tags,
      LocalDateTime updatedAt,
      Integer likeCount,
      Integer attemptCount,
      Integer solvedCount) {
    this.title = title;
    this.description = description;
    this.difficulty = difficulty;
    this.timeLimit = timeLimit;
    this.category = category;
    this.topic = topic;
    this.tags = tags;
    this.updatedAt = updatedAt;
    this.likeCount = likeCount;
    this.attemptCount = attemptCount;
    this.solvedCount = solvedCount;
  }
}
