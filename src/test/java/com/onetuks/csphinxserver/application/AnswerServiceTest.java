package com.onetuks.csphinxserver.application;

import static com.onetuks.csphinxserver.fixture.AnswerFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.onetuks.csphinxserver.CsPhinxServerApplicationTests;
import com.onetuks.csphinxserver.application.command.answer.ChoiceAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.DescriptiveAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.ShortAnswerAddCommand;
import com.onetuks.csphinxserver.domain.answer.Answer;
import com.onetuks.csphinxserver.domain.answer.ChoiceAnswer;
import com.onetuks.csphinxserver.domain.answer.DescriptiveAnswer;
import com.onetuks.csphinxserver.domain.answer.ShortAnswer;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswerServiceTest extends CsPhinxServerApplicationTests {

  private static final String QUESTION_ID = "questionId";

  @Test
  @DisplayName("객관식 답안을 추가한다")
  void addChoiceAnswerTest() {
    // Given
    ChoiceAnswer choiceAnswer = createChoiceAnswer(QUESTION_ID);
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
    ShortAnswer shortAnswer = createShortAnswer(QUESTION_ID);
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
    DescriptiveAnswer descriptiveAnswer = createDescriptiveAnswer(QUESTION_ID);
    DescriptiveAnswerAddCommand command = new DescriptiveAnswerAddCommand(
        descriptiveAnswer.questionId(), descriptiveAnswer.embeddedVector());

    // When
    String result = answerService.addDescriptiveAnswer(command);

    // Then
    assertThat(result).isNotNull();
  }

  @Test
  @DisplayName("객관식 답안을 조회한다.")
  void searchChoiceAnswerTest() {
    // Given
    String answerId = answerService.addChoiceAnswer(createChoiceCommand(QUESTION_ID));

    // When
    Answer answer = answerService.searchChoiceAnswer(answerId);

    // Then
    ChoiceAnswer result = (ChoiceAnswer) answer;

    assertAll(
        () -> assertThat(result.answerId()).isNotNull(),
        () -> assertThat(result.questionId()).isEqualTo(QUESTION_ID),
        () -> assertThat(Integer.parseInt(result.answerNumber())).isLessThanOrEqualTo(4),
        () -> assertThat(result.updatedAt()).isBeforeOrEqualTo(LocalDateTime.now()));
  }

  @Test
  @DisplayName("단답형 답안을 조회한다.")
  void searchShortAnswerTest() {
    // Given
    String answerId = answerService.addShortAnswer(createShortCommand(QUESTION_ID));

    // When
    Answer answer = answerService.searchShortAnswer(answerId);

    // Then
    ShortAnswer result = (ShortAnswer) answer;

    assertAll(
        () -> assertThat(result.answerId()).isNotNull(),
        () -> assertThat(result.questionId()).isEqualTo(QUESTION_ID),
        () -> assertThat(result.answerWords()).isNotEmpty(),
        () -> assertThat(result.updatedAt()).isBeforeOrEqualTo(LocalDateTime.now()));
  }

  @Test
  @DisplayName("서술형 답안을 조회한다.")
  void searchDescriptiveAnswerTest() {
    // Given
    String answerId = answerService.addDescriptiveAnswer(createDescriptiveCommand(QUESTION_ID));

    // When
    Answer answer = answerService.searchDescriptiveAnswer(answerId);

    // Then
    DescriptiveAnswer result = (DescriptiveAnswer) answer;

    assertAll(
        () -> assertThat(result.answerId()).isNotNull(),
        () -> assertThat(result.questionId()).isEqualTo(QUESTION_ID),
        () -> assertThat(result.embeddedVector()).isNotEmpty());

    assertThat(result.embeddedVector())
        .allSatisfy(vector -> assertThat(vector).isBetween(-1.0, 1.0));
  }
}