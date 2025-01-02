package com.onetuks.csphinxserver.application;

import static com.onetuks.csphinxserver.fixture.AnswerFixture.CHOICE_OPTIONS;
import static com.onetuks.csphinxserver.fixture.AnswerFixture.DESCRIPTIVE_OPTIONS;
import static com.onetuks.csphinxserver.fixture.AnswerFixture.SHORT_OPTIONS;
import static com.onetuks.csphinxserver.fixture.AnswerFixture.createAnswerAddCommand;
import static com.onetuks.csphinxserver.fixture.AnswerFixture.createAnswerEditCommand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.onetuks.csphinxserver.CsPhinxServerApplicationTests;
import com.onetuks.csphinxserver.application.command.answer.AnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.AnswerEditCommand;
import com.onetuks.csphinxserver.domain.answer.Answer;
import com.onetuks.csphinxserver.domain.answer.AnswerType;
import com.onetuks.csphinxserver.global.exception.NoSuchEntityException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswerServiceTest extends CsPhinxServerApplicationTests {

  @Test
  @DisplayName("답안을 추가한다")
  void addAnswerTest() {
    // Given
    String questionId = UUID.randomUUID().toString();
    AnswerAddCommand command = createAnswerAddCommand(questionId, AnswerType.SHORT);

    // When
    String result = answerService.addAnswer(command);

    // Then
    assertThat(result).isNotNull();
  }

  @Test
  @DisplayName("객관식 답안을 조회한다.")
  void searchAnswerTest() {
    // Given
    String questionId = UUID.randomUUID().toString();
    answerService.addAnswer(createAnswerAddCommand(questionId, AnswerType.CHOICE));

    // When
    Answer result = answerService.searchAnswer(questionId);

    // Then
    assertAll(
        () -> assertThat(result.answerId()).isNotNull(),
        () -> assertThat(result.questionId()).isEqualTo(questionId),
        () -> assertThat(result.answerType()).isEqualTo(AnswerType.CHOICE),
        () -> assertThat(result.answerValues()).hasSize(1),
        () ->
            assertThat(Objects.requireNonNull(result.answerValues()).getFirst())
                .isIn(CHOICE_OPTIONS),
        () -> assertThat(result.updatedAt()).isBeforeOrEqualTo(LocalDateTime.now()));
  }

  @Test
  @DisplayName("단답형 답안을 조회한다.")
  void searchShortAnswerTest() {
    // Given
    String questionId = UUID.randomUUID().toString();
    answerService.addAnswer(createAnswerAddCommand(questionId, AnswerType.SHORT));

    // When
    Answer result = answerService.searchAnswer(questionId);

    // Then
    assertAll(
        () -> assertThat(result.answerId()).isNotNull(),
        () -> assertThat(result.questionId()).isEqualTo(questionId),
        () -> assertThat(result.answerType()).isEqualTo(AnswerType.SHORT),
        () -> assertThat(result.answerValues()).isNotEmpty(),
        () ->
            assertThat(Objects.requireNonNull(result.answerValues()).getFirst())
                .isIn(SHORT_OPTIONS),
        () -> assertThat(result.updatedAt()).isBeforeOrEqualTo(LocalDateTime.now()));
  }

  @Test
  @DisplayName("서술형 답안을 조회한다.")
  void searchDescriptiveAnswerTest() {
    // Given
    String questionId = UUID.randomUUID().toString();
    answerService.addAnswer(createAnswerAddCommand(questionId, AnswerType.DESCRIPTION));

    // When
    Answer result = answerService.searchAnswer(questionId);

    // Then
    assertAll(
        () -> assertThat(result.answerId()).isNotNull(),
        () -> assertThat(result.questionId()).isEqualTo(questionId),
        () -> assertThat(result.answerType()).isEqualTo(AnswerType.DESCRIPTION),
        () -> assertThat(result.answerValues()).isNotEmpty(),
        () ->
            assertThat(Objects.requireNonNull(result.answerValues()).getFirst())
                .isIn(DESCRIPTIVE_OPTIONS),
        () -> assertThat(result.updatedAt()).isBeforeOrEqualTo(LocalDateTime.now()));
  }

  @Test
  @DisplayName("답안을 수정한다.")
  void editChoiceAnswerTest() {
    // Given
    String questionId = UUID.randomUUID().toString();
    String answerId =
        answerService.addAnswer(createAnswerAddCommand(questionId, AnswerType.CHOICE));

    AnswerEditCommand command =
        createAnswerEditCommand(answerId, questionId, AnswerType.DESCRIPTION);

    // When
    answerService.editAnswer(answerId, command);

    // Then
    Answer result = answerService.searchAnswer(questionId);

    assertAll(
        () -> assertThat(result.answerId()).isEqualTo(answerId),
        () -> assertThat(result.questionId()).isEqualTo(questionId),
        () -> assertThat(result.answerType()).isEqualTo(command.answerType()),
        () -> assertThat(result.answerValues()).isNotNull(),
        () -> assertThat(result.answerValues()).isNotEmpty(),
        () ->
            assertThat(Objects.requireNonNull(result.answerValues()).getFirst())
                .isIn(DESCRIPTIVE_OPTIONS));
  }

  @Test
  @DisplayName("답안을 제거한다.")
  void removeAnswerTest() {
    // Given
    String questionId = UUID.randomUUID().toString();
    String answerId =
        answerService.addAnswer(createAnswerAddCommand(questionId, AnswerType.CHOICE));

    // When
    answerService.removeAnswer(answerId);

    // Then
    assertThatThrownBy(() -> answerService.searchAnswer(questionId))
        .isInstanceOf(NoSuchEntityException.class);
  }
}
