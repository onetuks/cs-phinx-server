package com.onetuks.csphinxserver.application;

import com.onetuks.csphinxserver.application.command.answer.AnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.AnswerEditCommand;
import com.onetuks.csphinxserver.application.port.in.AnswerUseCases;
import com.onetuks.csphinxserver.application.port.out.AnswerPort;
import com.onetuks.csphinxserver.domain.answer.Answer;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnswerService implements AnswerUseCases {

  private final AnswerPort answerPort;

  public AnswerService(AnswerPort answerPort) {
    this.answerPort = answerPort;
  }

  @Override
  @Transactional
  public String addAnswer(AnswerAddCommand command) {
    return answerPort
        .create(
            new Answer(
                null,
                command.questionId(),
                command.answerType(),
                command.answerValues(),
                LocalDateTime.now()))
        .answerId();
  }

  @Override
  @Transactional(readOnly = true)
  public Answer searchAnswer(String questionId) {
    return answerPort.read(questionId);
  }

  @Override
  @Transactional
  public void editAnswer(String answerId, AnswerEditCommand command) {
    answerPort.update(
        new Answer(
            answerId,
            command.questionId(),
            command.answerType(),
            command.answerValues(),
            LocalDateTime.now()));
  }

  @Override
  public void removeAnswer(String answerId) {
    answerPort.delete(answerId);
  }
}
