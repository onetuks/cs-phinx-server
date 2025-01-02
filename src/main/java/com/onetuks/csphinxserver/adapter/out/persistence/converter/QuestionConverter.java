package com.onetuks.csphinxserver.adapter.out.persistence.converter;

import com.onetuks.csphinxserver.adapter.out.persistence.entity.QuestionEntity;
import com.onetuks.csphinxserver.domain.question.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionConverter {

  public QuestionEntity toEntity(Question domain) {
    return new QuestionEntity(
        domain.title(),
        domain.description(),
        domain.difficulty(),
        domain.answerType(),
        domain.topic(),
        domain.tags(),
        domain.updatedAt(),
        domain.likeCount(),
        domain.attemptCount(),
        domain.solvedCount());
  }

  public QuestionEntity toEntityWithId(Question domain) {
    return new QuestionEntity(
        domain.questionId(),
        domain.title(),
        domain.description(),
        domain.difficulty(),
        domain.answerType(),
        domain.topic(),
        domain.tags(),
        domain.updatedAt(),
        domain.likeCount(),
        domain.attemptCount(),
        domain.solvedCount());
  }

  public Question toDomain(QuestionEntity entity) {
    return new Question(
        entity.getId(),
        entity.getTitle(),
        entity.getDescription(),
        entity.getDifficulty(),
        entity.getAnswerType(),
        entity.getTopic(),
        entity.getTags(),
        entity.getUpdatedAt(),
        entity.getLikeCount(),
        entity.getAttemptCount(),
        entity.getSolvedCount());
  }
}
