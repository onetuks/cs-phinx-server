package com.onetuks.csphinxserver.application.port.in;

import com.onetuks.csphinxserver.application.command.answer.AnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.AnswerEditCommand;
import com.onetuks.csphinxserver.domain.answer.Answer;

public interface AnswerUseCases {

  String addAnswer(AnswerAddCommand command);

  Answer searchAnswer(String questionId);

  void editAnswer(String answerId, AnswerEditCommand command);

  void removeAnswer(String answerId);
}
