package com.onetuks.csphinxserver.application.port.in;

import com.onetuks.csphinxserver.application.command.answer.ChoiceAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.ChoiceAnswerEditCommand;
import com.onetuks.csphinxserver.application.command.answer.DescriptiveAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.DescriptiveAnswerEditCommand;
import com.onetuks.csphinxserver.application.command.answer.ShortAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.ShortAnswerEditCommand;
import com.onetuks.csphinxserver.domain.answer.Answer;
import java.util.List;

public interface AnswerUseCases {

  String addChoiceAnswer(ChoiceAnswerAddCommand command);

  String addShortAnswer(ShortAnswerAddCommand command);

  String addDescriptiveAnswer(DescriptiveAnswerAddCommand command);

  List<Answer> searchAnswers(String questionId);

  void editChoiceAnswer(String answerId, ChoiceAnswerEditCommand command);

  void editShortAnswer(String answerId, ShortAnswerEditCommand command);

  void editDescriptiveAnswer(String answerId, DescriptiveAnswerEditCommand command);
}
