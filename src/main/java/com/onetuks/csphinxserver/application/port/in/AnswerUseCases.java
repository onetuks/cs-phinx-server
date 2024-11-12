package com.onetuks.csphinxserver.application.port.in;

import com.onetuks.csphinxserver.application.command.answer.ChoiceAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.DescriptiveAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.ShortAnswerAddCommand;

public interface AnswerUseCases {

  String addChoiceAnswer(ChoiceAnswerAddCommand command);

  String addShortAnswer(ShortAnswerAddCommand command);

  String addDescriptiveAnswer(DescriptiveAnswerAddCommand command);
}
