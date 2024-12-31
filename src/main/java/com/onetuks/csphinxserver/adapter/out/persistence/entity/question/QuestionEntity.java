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
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Document(collection = "questions")
public class QuestionEntity {

  @Id private String id;

  @Field(name = "title")
  private String title;

  @Field(name = "description")
  private String description;

  @Field(name = "difficulty")
  private Difficulty difficulty;

  @Field(name = "time_limit")
  private Integer timeLimit;

  @Field(name = "category")
  private Category category;

  @Field(name = "topic")
  private Topic topic;

  @Field(name = "tags")
  private Set<String> tags;

  @Field(name = "updated_at")
  private LocalDateTime updatedAt;

  @Field(name = "like_count")
  private Integer likeCount;

  @Field(name = "attempt_count")
  private Integer attemptCount;

  @Field(name = "solved_count")
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
