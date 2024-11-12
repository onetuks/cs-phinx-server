package com.onetuks.csphinxserver.adapter.out.persistence;

import com.onetuks.csphinxserver.adapter.out.persistence.converter.QuestionConverter;
import com.onetuks.csphinxserver.adapter.out.persistence.repository.QuestionEntityMongoRepository;
import com.onetuks.csphinxserver.application.port.out.QuestionPort;
import com.onetuks.csphinxserver.domain.question.Question;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class QuestionEntityMongoAdapter implements QuestionPort {

  private final QuestionEntityMongoRepository questionRepository;
  private final QuestionConverter questionConverter;

  public QuestionEntityMongoAdapter(
      QuestionEntityMongoRepository questionRepository,
      QuestionConverter questionConverter) {
    this.questionRepository = questionRepository;
    this.questionConverter = questionConverter;
  }

  @Override
  @Transactional
  public Question create(Question question) {
    return questionConverter.toDomain(questionRepository.save(questionConverter.toEntity(question)));
  }

  @Override
  public Question read(String questionId) {
    return null;
  }

  @Override
  public void update(Question question) {

  }

  @Override
  public void delete(String questionId) {

  }
}
