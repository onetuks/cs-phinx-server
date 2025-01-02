package com.onetuks.csphinxserver.application;

import com.onetuks.csphinxserver.application.command.question.QuestionAddCommand;
import com.onetuks.csphinxserver.application.command.question.QuestionEditCommand;
import com.onetuks.csphinxserver.application.port.in.QuestionUseCases;
import com.onetuks.csphinxserver.application.port.out.QuestionPort;
import com.onetuks.csphinxserver.domain.question.Question;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionService implements QuestionUseCases {

  private final QuestionPort questionPort;

  public QuestionService(QuestionPort questionPort) {
    this.questionPort = questionPort;
  }

  @Override
  @Transactional
  public String addQuestion(QuestionAddCommand command) {
    return questionPort
        .create(
            new Question(
                null,
                command.title(),
                command.description(),
                command.difficulty(),
                command.answerType(),
                command.topic(),
                command.tags(),
                LocalDateTime.now(),
                0,
                0,
                0))
        .questionId();
  }

  @Override
  @Transactional(readOnly = true)
  public Question searchQuestion(String questionId) {
    return questionPort.read(questionId);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Question> searchQuestions(Pageable pageable) {
    return questionPort.readAll(pageable);
  }

  @Override
  @Transactional
  public void editQuestion(String questionId, QuestionEditCommand command) {
    questionPort.update(
        new Question(
            questionId,
            command.title(),
            command.description(),
            command.difficulty(),
            command.answerType(),
            command.topic(),
            command.tags(),
            LocalDateTime.now(),
            0,
            0,
            0));
  }

  @Override
  @Transactional
  public void removeQuestion(String questionId) {
    questionPort.delete(questionId);
  }
}
