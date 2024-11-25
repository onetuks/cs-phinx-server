package com.onetuks.csphinxserver.application;

import com.onetuks.csphinxserver.application.command.answer.ChoiceAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.DescriptiveAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.ShortAnswerAddCommand;
import com.onetuks.csphinxserver.application.port.in.AnswerUseCases;
import com.onetuks.csphinxserver.application.port.out.AnswerPort;
import com.onetuks.csphinxserver.domain.answer.Answer;
import com.onetuks.csphinxserver.domain.answer.ChoiceAnswer;
import com.onetuks.csphinxserver.domain.answer.DescriptiveAnswer;
import com.onetuks.csphinxserver.domain.answer.ShortAnswer;
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
  public String addChoiceAnswer(ChoiceAnswerAddCommand command) {
    return answerPort.create(
            new ChoiceAnswer(
                null, command.questionId(), command.answerNumber(), LocalDateTime.now()))
        .answerId();
  }

  @Override
  @Transactional
  public String addShortAnswer(ShortAnswerAddCommand command) {
    return answerPort.create(
            new ShortAnswer(
                null, command.questionId(), command.answerWords(), LocalDateTime.now()))
        .answerId();
  }

  @Override
  @Transactional
  public String addDescriptiveAnswer(DescriptiveAnswerAddCommand command) {
    return answerPort.create(
            new DescriptiveAnswer(
                null, command.questionId(), command.embeddedVector(), LocalDateTime.now()))
        .answerId();
  }

  @Override
  @Transactional(readOnly = true)
  public Answer searchChoiceAnswer(String answerId) {
    return answerPort.readChoice(answerId);
  }

  @Override
  @Transactional(readOnly = true)
  public Answer searchShortAnswer(String answerId) {
    return answerPort.readShort(answerId);
  }

  @Override
  @Transactional(readOnly = true)
  public Answer searchDescriptiveAnswer(String answerId) {
    return answerPort.readDescriptive(answerId);
  }
}
