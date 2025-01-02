package com.onetuks.csphinxserver.adapter.out.persistence;

import com.onetuks.csphinxserver.adapter.out.persistence.converter.AnswerConverter;
import com.onetuks.csphinxserver.adapter.out.persistence.repository.AnswerEntityMongoRepository;
import com.onetuks.csphinxserver.application.port.out.AnswerPort;
import com.onetuks.csphinxserver.domain.answer.Answer;
import com.onetuks.csphinxserver.global.exception.NoSuchEntityException;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerEntityAdapter implements AnswerPort {

  private final AnswerEntityMongoRepository answerRepository;
  private final AnswerConverter answerConverter;

  public AnswerEntityAdapter(
      AnswerEntityMongoRepository answerRepository, AnswerConverter answerConverter) {
    this.answerConverter = answerConverter;
    this.answerRepository = answerRepository;
  }

  @Override
  public Answer create(Answer answer) {
    return answerConverter.toDomain(answerRepository.save(answerConverter.toEntity(answer)));
  }

  @Override
  public Answer read(String questionId) {
    return answerConverter.toDomain(
        answerRepository.findByQuestionId(questionId).orElseThrow(NoSuchEntityException::new));
  }

  @Override
  public void update(Answer answer) {
    answerRepository.save(answerConverter.toEntity(answer));
  }

  @Override
  public void delete(String answerId) {
    answerRepository.deleteById(answerId);
  }
}
