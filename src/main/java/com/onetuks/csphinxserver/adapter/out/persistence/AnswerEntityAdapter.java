package com.onetuks.csphinxserver.adapter.out.persistence;

import com.onetuks.csphinxserver.adapter.out.persistence.converter.AnswerConverter;
import com.onetuks.csphinxserver.adapter.out.persistence.repository.AnswerEntityMongoRepository;
import com.onetuks.csphinxserver.application.port.out.AnswerPort;
import com.onetuks.csphinxserver.domain.answer.Answer;
import com.onetuks.csphinxserver.global.exception.NoSuchEntityException;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerEntityAdapter implements AnswerPort {

  private final AnswerConverter answerConverter;
  private final AnswerEntityMongoRepository answerRepository;

  public AnswerEntityAdapter(
      AnswerConverter answerConverter, AnswerEntityMongoRepository answerRepository) {
    this.answerConverter = answerConverter;
    this.answerRepository = answerRepository;
  }

  @Override
  public Answer create(Answer answer) {
    return answerConverter.toDomain(answerRepository.save(answerConverter.toEntity(answer)));
  }

  @Override
  public List<Answer> read(String questionId) {
    return answerRepository.findAllByQuestionId(questionId).stream()
        .map(answerConverter::toDomain)
        .toList();
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
