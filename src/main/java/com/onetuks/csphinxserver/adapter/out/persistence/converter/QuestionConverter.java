package com.onetuks.csphinxserver.adapter.out.persistence.converter;

import com.onetuks.csphinxserver.adapter.out.persistence.entity.question.QuestionEntity;
import com.onetuks.csphinxserver.domain.question.Question;
import com.onetuks.csphinxserver.domain.question.TimeLimit;
import org.springframework.stereotype.Component;

@Component
public class QuestionConverter {

  public QuestionEntity toEntity(Question domain) {
    return new QuestionEntity(
        domain.title(),
        domain.description(),
        domain.difficulty(),
        domain.timeLimit().seconds(),
        domain.category(),
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
        domain.timeLimit().seconds(),
        domain.category(),
        domain.topic(),
        domain.tags(),
        domain.updatedAt(),
        domain.likeCount(),
        domain.attemptCount(),
        domain.solvedCount()
    );
  }

  public Question toDomain(QuestionEntity entity) {
    return new Question(
        entity.getId(),
        entity.getTitle(),
        entity.getDescription(),
        entity.getDifficulty(),
        new TimeLimit(entity.getTimeLimit()),
        entity.getCategory(),
        entity.getTopic(),
        entity.getTags(),
        entity.getUpdatedAt(),
        entity.getLikeCount(),
        entity.getAttemptCount(),
        entity.getSolvedCount());
  }
}
