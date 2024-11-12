package com.onetuks.csphinxserver.application.port.in;

import com.onetuks.csphinxserver.application.command.question.QuestionAddCommand;

public interface QuestionUseCases {

  String addQuestion(QuestionAddCommand command);
}
