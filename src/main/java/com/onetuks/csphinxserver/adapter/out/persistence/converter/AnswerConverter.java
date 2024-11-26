package com.onetuks.csphinxserver.adapter.out.persistence.converter;

import com.onetuks.csphinxserver.adapter.out.persistence.entity.answer.AnswerEntity;
import com.onetuks.csphinxserver.adapter.out.persistence.entity.answer.ChoiceAnswerEntity;
import com.onetuks.csphinxserver.adapter.out.persistence.entity.answer.DescriptiveAnswerEntity;
import com.onetuks.csphinxserver.adapter.out.persistence.entity.answer.ShortAnswerEntity;
import com.onetuks.csphinxserver.domain.answer.Answer;
import com.onetuks.csphinxserver.domain.answer.ChoiceAnswer;
import com.onetuks.csphinxserver.domain.answer.DescriptiveAnswer;
import com.onetuks.csphinxserver.domain.answer.ShortAnswer;
import org.springframework.stereotype.Component;

@Component
public class AnswerConverter {

  public AnswerEntity toEntity(Answer domain) {
    return switch (domain.answerType()) {
      case CHOICE -> new ChoiceAnswerEntity(
          domain.answerId(), domain.questionId(),
          domain.answerType(), domain.value(), domain.updatedAt());
      case SHORT -> new ShortAnswerEntity(
          domain.answerId(), domain.questionId(),
          domain.answerType(), domain.value(), domain.updatedAt());
      case DESCRIPTION -> new DescriptiveAnswerEntity(
          domain.answerId(), domain.questionId(),
          domain.answerType(), domain.value(), domain.updatedAt());
      default -> throw new IllegalArgumentException("Unsupported answer type: " + domain.answerType());
    };
  }

  public Answer toDomain(AnswerEntity entity) {
    return switch (entity.getAnswerType()) {
      case CHOICE -> new ChoiceAnswer(
          entity.getId(), entity.getQuestionId(),
          entity.getAnswerType(), entity.getValue(), entity.getUpdatedAt());
      case SHORT -> new ShortAnswer(
          entity.getId(), entity.getQuestionId(),
          entity.getAnswerType(), entity.getValue(), entity.getUpdatedAt());
      case DESCRIPTION -> new DescriptiveAnswer(
          entity.getId(), entity.getQuestionId(),
          entity.getAnswerType(), entity.getValue(), entity.getUpdatedAt());
      default -> throw new IllegalArgumentException("Unsupported answer type: " + entity.getAnswerType());
    };
  }
}
