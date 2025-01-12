package com.onetuks.csphinxserver.application.port.in;

import com.onetuks.csphinxserver.application.command.AnswerCommand;
import com.onetuks.csphinxserver.domain.answer.Answer;

public interface AnswerUseCases {

  Answer addAnswer(AnswerCommand command);

  Answer searchAnswer(long problemId);

  void editAnswer(long answerId, AnswerCommand command);

  void removeAnswer(long answerId);
}
