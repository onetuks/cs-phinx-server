package com.onetuks.csphinxserver.adapter.out.persistence.converter;

import com.onetuks.csphinxserver.adapter.out.persistence.entity.AnswerEntity;
import com.onetuks.csphinxserver.domain.answer.Answer;
import org.springframework.stereotype.Component;

@Component
public class AnswerConverter {

  public AnswerEntity toEntity(Answer domain) {
    return new AnswerEntity(
        domain.answerId(),
        domain.questionId(),
        domain.answerType(),
        domain.answerValues(),
        domain.updatedAt());
  }

  public Answer toDomain(AnswerEntity entity) {
    return new Answer(
        entity.getId(),
        entity.getQuestionId(),
        entity.getAnswerType(),
        entity.getAnswerValues(),
        entity.getUpdatedAt());
  }
}
