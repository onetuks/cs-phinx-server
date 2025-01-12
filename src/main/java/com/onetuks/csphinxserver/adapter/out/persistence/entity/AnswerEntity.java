package com.onetuks.csphinxserver.adapter.out.persistence.entity;

import com.onetuks.csphinxserver.domain.answer.AnswerType;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "answers")
public class AnswerEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "answer_id", nullable = false)
  private Long answerId;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "problem_id", nullable = false)
  private ProblemEntity problemEntity;

  @Enumerated(value = EnumType.STRING)
  @Column(name = "answer_type", nullable = false)
  private AnswerType answerType;

  @Type(JsonType.class)
  @Column(name = "answer_values", nullable = false)
  private List<String> answerValues;
}
