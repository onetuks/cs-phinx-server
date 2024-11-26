package com.onetuks.csphinxserver.application;

import static com.onetuks.csphinxserver.fixture.AnswerFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.onetuks.csphinxserver.CsPhinxServerApplicationTests;
import com.onetuks.csphinxserver.application.command.answer.ChoiceAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.ChoiceAnswerEditCommand;
import com.onetuks.csphinxserver.application.command.answer.DescriptiveAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.DescriptiveAnswerEditCommand;
import com.onetuks.csphinxserver.application.command.answer.ShortAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.ShortAnswerEditCommand;
import com.onetuks.csphinxserver.domain.answer.Answer;
import com.onetuks.csphinxserver.domain.answer.ChoiceAnswer;
import com.onetuks.csphinxserver.domain.answer.DescriptiveAnswer;
import com.onetuks.csphinxserver.domain.answer.ShortAnswer;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

class AnswerServiceTest extends CsPhinxServerApplicationTests {

  private static final String QUESTION_ID = "questionId";

  @Test
  @DisplayName("객관식 답안을 추가한다")
  void addChoiceAnswerTest() {
    // Given
    ChoiceAnswerAddCommand command = createChoiceAddCommand(QUESTION_ID);

    // When
    String result = answerService.addChoiceAnswer(command);

    // Then
    assertThat(result).isNotNull();
  }

  @Test
  @DisplayName("단답형 답안을 추가한다.")
  void addShortAnswerTest() {
    // Given
    ShortAnswerAddCommand command = createShortAddCommand(QUESTION_ID);

    // When
    String result = answerService.addShortAnswer(command);

    // Then
    assertThat(result).isNotNull();
  }

  @Test
  @DisplayName("서술형 답안을 추가한다.")
  void addDescriptiveAnswerTest() {
    // Given
    DescriptiveAnswerAddCommand command = createDescriptiveAddCommand(QUESTION_ID);

    // When
    String result = answerService.addDescriptiveAnswer(command);

    // Then
    assertThat(result).isNotNull();
  }

  @Test
  @DisplayName("객관식 답안을 조회한다.")
  void searchChoiceAnswerTest() {
    // Given
    String answerId = answerService.addChoiceAnswer(createChoiceAddCommand(QUESTION_ID));

    // When
    Answer answer = answerService.searchChoiceAnswer(answerId);

    // Then
    ChoiceAnswer result = (ChoiceAnswer) answer;

    assertAll(
        () -> assertThat(result.answerId()).isNotNull(),
        () -> assertThat(result.questionId()).isEqualTo(QUESTION_ID),
        () -> assertThat(Integer.parseInt(result.value())).isLessThanOrEqualTo(4),
        () -> assertThat(result.updatedAt()).isBeforeOrEqualTo(LocalDateTime.now()));
  }

  @Test
  @DisplayName("단답형 답안을 조회한다.")
  void searchShortAnswerTest() {
    // Given
    String answerId = answerService.addShortAnswer(createShortAddCommand(QUESTION_ID));

    // When
    Answer answer = answerService.searchShortAnswer(answerId);

    // Then
    ShortAnswer result = (ShortAnswer) answer;

    assertAll(
        () -> assertThat(result.answerId()).isNotNull(),
        () -> assertThat(result.questionId()).isEqualTo(QUESTION_ID),
        () -> assertThat(result.value()).isNotEmpty(),
        () -> assertThat(result.updatedAt()).isBeforeOrEqualTo(LocalDateTime.now()));
  }

  @Test
  @DisplayName("서술형 답안을 조회한다.")
  void searchDescriptiveAnswerTest() {
    // Given
    String answerId = answerService.addDescriptiveAnswer(createDescriptiveAddCommand(QUESTION_ID));

    // When
    Answer answer = answerService.searchDescriptiveAnswer(answerId);

    // Then
    DescriptiveAnswer result = (DescriptiveAnswer) answer;

    assertAll(
        () -> assertThat(result.answerId()).isNotNull(),
        () -> assertThat(result.questionId()).isEqualTo(QUESTION_ID),
        () -> assertThat(result.value()).isNotEmpty(),
        () -> assertThat(result.updatedAt()).isBeforeOrEqualTo(LocalDateTime.now()));

    assertThat(result.value())
        .allSatisfy(vector -> assertThat(vector).isBetween(-1.0, 1.0));
  }

  @Test
  @DisplayName("객관식 답안을 수정한다.")
  void editChoiceAnswerTest() {
    // Given
    String answerId = answerService.addChoiceAnswer(createChoiceAddCommand(QUESTION_ID));
    Answer answer = answerService.searchChoiceAnswer(answerId);

    ChoiceAnswerEditCommand command = createChoiceAnswerEditCommand(answerId, QUESTION_ID);

    // When
    answerService.editChoiceAnswer(answerId, command);

    // Then
    ChoiceAnswer result = (ChoiceAnswer) answerService.searchChoiceAnswer(answerId);

    assertAll(
        () -> assertThat(result.answerId()).isEqualTo(answerId),
        () -> assertThat(result.questionId()).isEqualTo(QUESTION_ID),
        () -> assertThat(result.value()).isEqualTo(command.answerNumber()),
        () -> assertThat(result.updatedAt()).isAfterOrEqualTo(answer.updatedAt()));
  }

  @Test
  @DisplayName("단답형 답안을 수정한다.")
  void editShortAnswerTest() {
    // Given
    String answerId = answerService.addShortAnswer(createShortAddCommand(QUESTION_ID));
    Answer answer = answerService.searchShortAnswer(answerId);

    ShortAnswerEditCommand command = createShortAnswerEditCommand(answerId, QUESTION_ID);

    // When
    answerService.editShortAnswer(answerId, command);

    // Then
    ShortAnswer result = (ShortAnswer) answerService.searchShortAnswer(answerId);

    assertAll(
        () -> assertThat(result.answerId()).isEqualTo(answerId),
        () -> assertThat(result.questionId()).isEqualTo(QUESTION_ID),
        () -> assertThat(result.value()).hasSize(command.answerWords().size()),
        () -> assertThat(result.value()).containsAll(command.answerWords()),
        () -> assertThat(result.updatedAt()).isAfterOrEqualTo(answer.updatedAt()));
  }

  @Test
  @DisplayName("서술형 답안을 수정한다.")
  void editDescriptiveAnswerTest() {
    // Given
    String answerId = answerService.addDescriptiveAnswer(createDescriptiveAddCommand(QUESTION_ID));
    Answer answer = answerService.searchDescriptiveAnswer(answerId);

    DescriptiveAnswerEditCommand command = createDescriptiveAnswerEditCommand(answerId, QUESTION_ID);

    // When
    answerService.editDescriptiveAnswer(answerId, command);

    // Then
    DescriptiveAnswer result = (DescriptiveAnswer) answerService.searchDescriptiveAnswer(answerId);

    assertAll(
        () -> assertThat(result.answerId()).isEqualTo(answerId),
        () -> assertThat(result.questionId()).isEqualTo(QUESTION_ID),
        () -> assertThat(result.value()).hasSize(command.embeddedVector().size()),
        () -> assertThat(result.value()).containsAll(command.embeddedVector()),
        () -> assertThat(result.updatedAt()).isAfterOrEqualTo(answer.updatedAt()));
  }
}