package com.onetuks.csphinxserver.adapter.out.persistence;

import com.onetuks.csphinxserver.adapter.out.persistence.converter.AnswerConverter;
import com.onetuks.csphinxserver.adapter.out.persistence.repository.ChoiceAnswerEntityMongoRepository;
import com.onetuks.csphinxserver.adapter.out.persistence.repository.DescriptiveAnswerEntityMongoRepository;
import com.onetuks.csphinxserver.adapter.out.persistence.repository.ShortAnswerEntityMongoRepository;
import com.onetuks.csphinxserver.application.port.out.AnswerPort;
import com.onetuks.csphinxserver.domain.answer.Answer;
import com.onetuks.csphinxserver.domain.answer.ChoiceAnswer;
import com.onetuks.csphinxserver.domain.answer.DescriptiveAnswer;
import com.onetuks.csphinxserver.domain.answer.ShortAnswer;
import com.onetuks.csphinxserver.global.exception.NoSuchEntityException;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerEntityAdapter implements AnswerPort {

  private final AnswerConverter answerConverter;
  private final ChoiceAnswerEntityMongoRepository choiceAnswerRepository;
  private final ShortAnswerEntityMongoRepository shortAnswerRepository;
  private final DescriptiveAnswerEntityMongoRepository descriptiveAnswerRepository;

  public AnswerEntityAdapter(
      AnswerConverter answerConverter,
      ChoiceAnswerEntityMongoRepository choiceAnswerRepository,
      ShortAnswerEntityMongoRepository shortAnswerRepository,
      DescriptiveAnswerEntityMongoRepository descriptiveAnswerRepository) {
    this.answerConverter = answerConverter;
    this.choiceAnswerRepository = choiceAnswerRepository;
    this.shortAnswerRepository = shortAnswerRepository;
    this.descriptiveAnswerRepository = descriptiveAnswerRepository;
  }

  @Override
  public Answer create(Answer answer) {
    if (answer instanceof ChoiceAnswer) {
      return answerConverter.toDomain(
          choiceAnswerRepository.save(answerConverter.toEntity((ChoiceAnswer) answer)));
    } else if (answer instanceof ShortAnswer) {
      return answerConverter.toDomain(
          shortAnswerRepository.save(answerConverter.toEntity((ShortAnswer) answer)));
    } else if (answer instanceof DescriptiveAnswer) {
      return answerConverter.toDomain(
          descriptiveAnswerRepository.save(answerConverter.toEntity((DescriptiveAnswer) answer)));
    }
    return null;
  }

  @Override
  public Answer readChoice(String answerId) {
    return choiceAnswerRepository.findById(answerId)
        .map(answerConverter::toDomain)
        .orElseThrow(NoSuchEntityException::new);
  }

  @Override
  public Answer readShort(String answerId) {
    return shortAnswerRepository.findById(answerId)
        .map(answerConverter::toDomain)
        .orElseThrow(NoSuchEntityException::new);
  }

  @Override
  public Answer readDescriptive(String answerId) {
    return descriptiveAnswerRepository.findById(answerId)
        .map(answerConverter::toDomain)
        .orElseThrow(NoSuchEntityException::new);
  }

  @Override
  public void update(Answer answer) {

  }

  @Override
  public void delete(String answerId) {

  }
}
