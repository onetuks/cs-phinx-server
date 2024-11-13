package com.onetuks.csphinxserver.adapter.out.persistence;

import com.onetuks.csphinxserver.adapter.out.persistence.converter.QuestionConverter;
import com.onetuks.csphinxserver.adapter.out.persistence.repository.QuestionEntityMongoRepository;
import com.onetuks.csphinxserver.application.port.out.QuestionPort;
import com.onetuks.csphinxserver.domain.question.Question;
import com.onetuks.csphinxserver.global.exception.NoSuchEntityException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class QuestionEntityAdapter implements QuestionPort {

  private final QuestionEntityMongoRepository questionRepository;
  private final QuestionConverter questionConverter;

  public QuestionEntityAdapter(
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
  @Transactional
  public Question read(String questionId) {
    return questionConverter.toDomain(
        questionRepository.findById(questionId).orElseThrow(NoSuchEntityException::new));
  }

  @Override
  @Transactional
  public Page<Question> readAll(Pageable pageable) {
    return questionRepository.findAll(pageable).map(questionConverter::toDomain);
  }

  @Override
  @Transactional
  public void update(Question question) {

  }

  @Override
  @Transactional
  public void delete(String questionId) {

  }
}