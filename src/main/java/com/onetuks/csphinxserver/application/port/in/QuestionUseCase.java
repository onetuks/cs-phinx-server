package com.onetuks.csphinxserver.application.port.in;

import com.onetuks.csphinxserver.application.command.QuestionAddCommand;

public interface QuestionUseCase {

  String addQuestion(QuestionAddCommand command);
}
