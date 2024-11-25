package com.onetuks.csphinxserver.application.port.in;

import com.onetuks.csphinxserver.application.command.answer.ChoiceAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.DescriptiveAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.ShortAnswerAddCommand;
import com.onetuks.csphinxserver.domain.answer.Answer;

public interface AnswerUseCases {

  String addChoiceAnswer(ChoiceAnswerAddCommand command);

  String addShortAnswer(ShortAnswerAddCommand command);

  String addDescriptiveAnswer(DescriptiveAnswerAddCommand command);

  Answer searchChoiceAnswer(String answerId);

  Answer searchShortAnswer(String answerId);

  Answer searchDescriptiveAnswer(String answerId);
}
