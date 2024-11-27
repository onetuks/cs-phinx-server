package com.onetuks.csphinxserver.application;

import com.onetuks.csphinxserver.application.command.answer.ChoiceAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.ChoiceAnswerEditCommand;
import com.onetuks.csphinxserver.application.command.answer.DescriptiveAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.DescriptiveAnswerEditCommand;
import com.onetuks.csphinxserver.application.command.answer.ShortAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.ShortAnswerEditCommand;
import com.onetuks.csphinxserver.application.port.in.AnswerUseCases;
import com.onetuks.csphinxserver.application.port.out.AnswerPort;
import com.onetuks.csphinxserver.domain.answer.Answer;
import com.onetuks.csphinxserver.domain.answer.AnswerType;
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
                null, command.questionId(),
                AnswerType.CHOICE, command.answerNumber(), LocalDateTime.now()))
        .answerId();
  }

  @Override
  @Transactional
  public String addShortAnswer(ShortAnswerAddCommand command) {
    return answerPort.create(
            new ShortAnswer(
                null, command.questionId(),
                AnswerType.SHORT, command.answerWords(), LocalDateTime.now()))
        .answerId();
  }

  @Override
  @Transactional
  public String addDescriptiveAnswer(DescriptiveAnswerAddCommand command) {
    return answerPort.create(
            new DescriptiveAnswer(
                null, command.questionId(),
                AnswerType.DESCRIPTION, command.embeddedVector(), LocalDateTime.now()))
        .answerId();
  }

  @Override
  @Transactional(readOnly = true)
  public Answer searchAnswers(String questionId) {
    return answerPort.read(questionId);
  }

  @Override
  @Transactional
  public void editChoiceAnswer(String answerId, ChoiceAnswerEditCommand command) {
    answerPort.update(
        new ChoiceAnswer(
            answerId, command.questionId(),
            AnswerType.CHOICE, command.answerNumber(), LocalDateTime.now()));
  }

  @Override
  @Transactional
  public void editShortAnswer(String answerId, ShortAnswerEditCommand command) {
    answerPort.update(
        new ShortAnswer(
            answerId, command.questionId(),
            AnswerType.SHORT, command.answerWords(), LocalDateTime.now()));
  }

  @Override
  @Transactional
  public void editDescriptiveAnswer(String answerId, DescriptiveAnswerEditCommand command) {
    answerPort.update(
        new DescriptiveAnswer(
            answerId, command.questionId(),
            AnswerType.DESCRIPTION, command.embeddedVector(), LocalDateTime.now()));
  }
}
