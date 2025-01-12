package com.onetuks.csphinxserver.adapter.out.persistence.converter;

import com.onetuks.csphinxserver.adapter.out.persistence.entity.AnswerEntity;
import com.onetuks.csphinxserver.domain.answer.Answer;
import org.springframework.stereotype.Component;

@Component
public class AnswerConverter {

  private final ProblemConverter problemConverter;

  public AnswerConverter(ProblemConverter problemConverter) {
    this.problemConverter = problemConverter;
  }

  public AnswerEntity toEntity(Answer domain) {
    return new AnswerEntity(
        domain.answerId(),
        problemConverter.toEntity(domain.problem()),
        domain.answerType(),
        domain.answerValues());
  }

  public Answer toDomain(AnswerEntity entity) {
    return new Answer(
        entity.getAnswerId(),
        problemConverter.toDomain(entity.getProblemEntity()),
        entity.getAnswerType(),
        entity.getAnswerValues(),
        entity.getUpdatedAt());
  }
}
