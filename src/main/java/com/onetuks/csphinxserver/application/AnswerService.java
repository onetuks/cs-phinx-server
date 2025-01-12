package com.onetuks.csphinxserver.application;

import com.onetuks.csphinxserver.application.command.AnswerCommand;
import com.onetuks.csphinxserver.application.port.in.AnswerUseCases;
import com.onetuks.csphinxserver.application.port.out.AnswerPort;
import com.onetuks.csphinxserver.application.port.out.ProblemPort;
import com.onetuks.csphinxserver.domain.answer.Answer;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnswerService implements AnswerUseCases {

  private final AnswerPort answerPort;
  private final ProblemPort problemPort;

  public AnswerService(AnswerPort answerPort, ProblemPort problemPort) {
    this.answerPort = answerPort;
    this.problemPort = problemPort;
  }

  @Override
  @Transactional
  public Answer addAnswer(AnswerCommand command) {
    return answerPort.create(
        new Answer(
            null,
            problemPort.read(command.problemId()),
            command.answerType(),
            command.answerValues(),
            LocalDateTime.now()));
  }

  @Override
  @Transactional(readOnly = true)
  public Answer searchAnswer(long problemId) {
    return answerPort.read(problemId);
  }

  @Override
  @Transactional
  public void editAnswer(long answerId, AnswerCommand command) {
    answerPort.update(
        new Answer(
            answerId,
            problemPort.read(command.problemId()),
            command.answerType(),
            command.answerValues(),
            LocalDateTime.now()));
  }

  @Override
  public void removeAnswer(long answerId) {
    answerPort.delete(answerId);
  }
}
