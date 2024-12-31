package com.onetuks.csphinxserver.application.port.in;

import com.onetuks.csphinxserver.application.command.question.QuestionAddCommand;
import com.onetuks.csphinxserver.application.command.question.QuestionEditCommand;
import com.onetuks.csphinxserver.domain.question.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionUseCases {

  String addQuestion(QuestionAddCommand command);

  Question searchQuestion(String questionId);

  Page<Question> searchQuestions(Pageable pageable);

  void editQuestion(String questionId, QuestionEditCommand command);

  void removeQuestion(String questionId);
}
