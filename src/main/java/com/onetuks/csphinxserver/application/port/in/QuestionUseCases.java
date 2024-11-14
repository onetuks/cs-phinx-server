package com.onetuks.csphinxserver.application.port.in;

import com.onetuks.csphinxserver.application.command.question.QuestionPatchCommand;
import com.onetuks.csphinxserver.application.command.question.QuestionPostCommand;
import com.onetuks.csphinxserver.domain.question.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionUseCases {

  String addQuestion(QuestionPostCommand command);

  Question searchQuestion(String questionId);

  Page<Question> searchQuestions(Pageable pageable);

  void editQuestion(String questionId, QuestionPatchCommand command);

  void removeQuestion(String questionId);
}
