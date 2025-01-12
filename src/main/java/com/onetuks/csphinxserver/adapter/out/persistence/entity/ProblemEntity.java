package com.onetuks.csphinxserver.adapter.out.persistence.entity;

import com.onetuks.csphinxserver.domain.problem.Difficulty;
import com.onetuks.csphinxserver.domain.problem.Topic;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "problems")
public class ProblemEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "problem_id", nullable = false)
  private Long problemId;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description", nullable = false)
  private String description;

  @Enumerated(value = EnumType.STRING)
  @Column(name = "difficulty", nullable = false)
  private Difficulty difficulty;

  @Enumerated(value = EnumType.STRING)
  @Column(name = "topic", nullable = false)
  private Topic topic;

  @Type(JsonType.class)
  @Column(name = "tags", nullable = false)
  private Set<String> tags;

  @Column(name = "is_active")
  private Boolean isActive;
}
