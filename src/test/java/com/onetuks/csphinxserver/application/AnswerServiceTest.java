package com.onetuks.csphinxserver.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.onetuks.csphinxserver.CsPhinxServerApplicationTests;
import com.onetuks.csphinxserver.application.command.answer.ChoiceAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.DescriptiveAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.ShortAnswerAddCommand;
import com.onetuks.csphinxserver.domain.answer.ChoiceAnswer;
import com.onetuks.csphinxserver.domain.answer.DescriptiveAnswer;
import com.onetuks.csphinxserver.domain.answer.ShortAnswer;
import com.onetuks.csphinxserver.fixture.AnswerFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswerServiceTest extends CsPhinxServerApplicationTests {

  private static final String QUESTION_ID = "questionId";

  @Test
  @DisplayName("객관식 답안을 추가한다")
  void addChoiceAnswerTest() {
    // Given
    ChoiceAnswer choiceAnswer = AnswerFixture.createChoiceAnswer(QUESTION_ID);
    ChoiceAnswerAddCommand command =
        new ChoiceAnswerAddCommand(choiceAnswer.questionId(), choiceAnswer.answerNumber());

    // When
    String result = answerService.addChoiceAnswer(command);

    // Then
    assertThat(result).isNotNull();
  }

  @Test
  @DisplayName("단답형 답안을 추가한다.")
  void addShortAnswerTest() {
    // Given
    ShortAnswer shortAnswer = AnswerFixture.createShortAnswer(QUESTION_ID);
    ShortAnswerAddCommand command = new ShortAnswerAddCommand(shortAnswer.questionId(),
        shortAnswer.answerWords());

    // When
    String result = answerService.addShortAnswer(command);

    // Then
    assertThat(result).isNotNull();
  }

  @Test
  @DisplayName("서술형 답안을 추가한다.")
  void addDescriptiveAnswerTest() {
    // Given
    DescriptiveAnswer descriptiveAnswer = AnswerFixture.createDescriptiveAnswer(QUESTION_ID);
    DescriptiveAnswerAddCommand command = new DescriptiveAnswerAddCommand(
        descriptiveAnswer.questionId(), descriptiveAnswer.embeddedVector());

    // When
    String result = answerService.addDescriptiveAnswer(command);

    // Then
    assertThat(result).isNotNull();
  }
}