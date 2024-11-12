package com.onetuks.csphinxserver.adapter.out.persistence.converter;

import com.onetuks.csphinxserver.adapter.out.persistence.entity.answer.ChoiceAnswerEntity;
import com.onetuks.csphinxserver.adapter.out.persistence.entity.answer.DescriptiveAnswerEntity;
import com.onetuks.csphinxserver.adapter.out.persistence.entity.answer.ShortAnswerEntity;
import com.onetuks.csphinxserver.domain.answer.ChoiceAnswer;
import com.onetuks.csphinxserver.domain.answer.DescriptiveAnswer;
import com.onetuks.csphinxserver.domain.answer.ShortAnswer;
import org.springframework.stereotype.Component;

@Component
public class AnswerConverter {

  public ChoiceAnswerEntity toEntity(ChoiceAnswer domain) {
    return new ChoiceAnswerEntity(domain.questionId(), domain.answerNumber(), domain.updatedAt());
  }

  public ShortAnswerEntity toEntity(ShortAnswer domain) {
    return new ShortAnswerEntity(domain.questionId(), domain.answerWords(), domain.updatedAt());
  }

  public DescriptiveAnswerEntity toEntity(DescriptiveAnswer domain) {
    return new DescriptiveAnswerEntity(
        domain.questionId(), domain.embeddedVector(), domain.updatedAt());
  }

  public ChoiceAnswer toDomain(ChoiceAnswerEntity entity) {
    return new ChoiceAnswer(
        entity.getId(), entity.getQuestionId(), entity.getAnswerNumber(), entity.getUpdatedAt());
  }

  public ShortAnswer toDomain(ShortAnswerEntity entity) {
    return new ShortAnswer(
        entity.getId(), entity.getQuestionId(), entity.getAnswerWords(), entity.getUpdatedAt());
  }

  public DescriptiveAnswer toDomain(DescriptiveAnswerEntity entity) {
    return new DescriptiveAnswer(
        entity.getId(), entity.getQuestionId(), entity.getEmbeddedVector(), entity.getUpdatedAt());
  }
}
