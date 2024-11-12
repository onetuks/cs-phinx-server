package com.onetuks.csphinxserver.application;

import com.onetuks.csphinxserver.application.command.QuestionAddCommand;
import com.onetuks.csphinxserver.application.port.in.QuestionUseCase;
import com.onetuks.csphinxserver.application.port.out.QuestionPort;
import com.onetuks.csphinxserver.domain.question.Question;
import com.onetuks.csphinxserver.domain.question.TimeLimit;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class QuestionService implements QuestionUseCase {

  private final QuestionPort questionPort;

  public QuestionService(QuestionPort questionPort) {
    this.questionPort = questionPort;
  }

  @Override
  public String addQuestion(QuestionAddCommand command) {
    return questionPort.create(
        new Question(
            null,
            command.title(), command.description(),
            command.difficulty(), new TimeLimit(command.timeLimit()),
            command.category(), command.topic(), command.tags(),
            LocalDateTime.now(), 0, 0, 0))
        .questionId();
  }
}
