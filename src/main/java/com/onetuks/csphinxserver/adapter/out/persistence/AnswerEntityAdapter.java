package com.onetuks.csphinxserver.adapter.out.persistence;

import com.onetuks.csphinxserver.adapter.out.persistence.converter.AnswerConverter;
import com.onetuks.csphinxserver.adapter.out.persistence.repository.AnswerEntityJpaRepository;
import com.onetuks.csphinxserver.application.port.out.AnswerPort;
import com.onetuks.csphinxserver.domain.answer.Answer;
import com.onetuks.csphinxserver.global.exception.NoSuchEntityException;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerEntityAdapter implements AnswerPort {

  private final AnswerEntityJpaRepository answerRepository;
  private final AnswerConverter answerConverter;

  public AnswerEntityAdapter(
      AnswerEntityJpaRepository answerRepository, AnswerConverter answerConverter) {
    this.answerConverter = answerConverter;
    this.answerRepository = answerRepository;
  }

  @Override
  public Answer create(Answer answer) {
    return answerConverter.toDomain(answerRepository.save(answerConverter.toEntity(answer)));
  }

  @Override
  public Answer read(long problemId) {
    return answerConverter.toDomain(
        answerRepository
            .findByProblemEntityProblemId(problemId)
            .orElseThrow(NoSuchEntityException::new));
  }

  @Override
  public void update(Answer answer) {
    answerRepository.save(answerConverter.toEntity(answer));
  }

  @Override
  public void delete(long answerId) {
    answerRepository.deleteById(answerId);
  }
}
