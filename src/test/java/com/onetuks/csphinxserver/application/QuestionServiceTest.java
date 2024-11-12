package com.onetuks.csphinxserver.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.onetuks.csphinxserver.CsPhinxServerApplicationTests;
import com.onetuks.csphinxserver.application.command.question.QuestionAddCommand;
import com.onetuks.csphinxserver.domain.question.Question;
import com.onetuks.csphinxserver.fixture.QuestionFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QuestionServiceTest extends CsPhinxServerApplicationTests {

  @Test
  @DisplayName("문제를 추가한다")
  void addQuestion() {
    // Given
    Question question = QuestionFixture.create();
    QuestionAddCommand command = new QuestionAddCommand(
        question.title(), question.description(), question.difficulty(),
        question.timeLimit().seconds(), question.category(), question.topic(), question.tags());

    // When
    String result = questionService.addQuestion(command);

    // Then
    assertThat(result).isNotNull();
  }
}