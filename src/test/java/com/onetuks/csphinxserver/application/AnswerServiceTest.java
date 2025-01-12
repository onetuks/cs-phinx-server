package com.onetuks.csphinxserver.application;

import static com.onetuks.csphinxserver.fixture.AnswerFixture.CHOICE_OPTIONS;
import static com.onetuks.csphinxserver.fixture.AnswerFixture.DESCRIPTIVE_OPTIONS;
import static com.onetuks.csphinxserver.fixture.AnswerFixture.SHORT_OPTIONS;
import static com.onetuks.csphinxserver.fixture.AnswerFixture.createAnswerAddCommand;
import static com.onetuks.csphinxserver.fixture.ProblemFixture.createProblemCommand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.onetuks.csphinxserver.CsPhinxServerApplicationTests;
import com.onetuks.csphinxserver.application.command.AnswerCommand;
import com.onetuks.csphinxserver.domain.answer.Answer;
import com.onetuks.csphinxserver.domain.answer.AnswerType;
import com.onetuks.csphinxserver.domain.problem.Problem;
import com.onetuks.csphinxserver.global.exception.NoSuchEntityException;
import java.time.LocalDateTime;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswerServiceTest extends CsPhinxServerApplicationTests {

  private Problem problem;

  @BeforeEach
  void setUp() {
    problem = problemService.addProblem(createProblemCommand());
  }

  @Test
  @DisplayName("답안을 추가한다")
  void addAnswerTest() {
    // Given
    AnswerCommand command = createAnswerAddCommand(problem.problemId(), AnswerType.SHORT);

    // When
    Answer result = answerService.addAnswer(command);

    // Then
    assertThat(result).isNotNull();
  }

  @Test
  @DisplayName("객관식 답안을 조회한다.")
  void searchAnswerTest() {
    // Given
    answerService.addAnswer(createAnswerAddCommand(problem.problemId(), AnswerType.CHOICE));

    // When
    Answer result = answerService.searchAnswer(problem.problemId());

    // Then
    assertAll(
        () -> assertThat(result.answerId()).isNotNull(),
        () -> assertThat(result.problem().problemId()).isEqualTo(problem.problemId()),
        () -> assertThat(result.answerType()).isEqualTo(AnswerType.CHOICE),
        () -> assertThat(result.answerValues()).hasSize(1),
        () ->
            assertThat(Objects.requireNonNull(result.answerValues()).getFirst())
                .isIn(CHOICE_OPTIONS),
        () -> assertThat(result.updatedAt()).isBeforeOrEqualTo(LocalDateTime.now().plusMinutes(1)));
  }

  @Test
  @DisplayName("단답형 답안을 조회한다.")
  void searchShortAnswerTest() {
    // Given
    answerService.addAnswer(createAnswerAddCommand(problem.problemId(), AnswerType.SHORT));

    // When
    Answer result = answerService.searchAnswer(problem.problemId());

    // Then
    assertAll(
        () -> assertThat(result.answerId()).isNotNull(),
        () -> assertThat(result.problem().problemId()).isEqualTo(problem.problemId()),
        () -> assertThat(result.answerType()).isEqualTo(AnswerType.SHORT),
        () -> assertThat(result.answerValues()).isNotEmpty(),
        () ->
            assertThat(Objects.requireNonNull(result.answerValues()).getFirst())
                .isIn(SHORT_OPTIONS),
        () -> assertThat(result.updatedAt()).isBeforeOrEqualTo(LocalDateTime.now().plusMinutes(1)));
  }

  @Test
  @DisplayName("서술형 답안을 조회한다.")
  void searchDescriptiveAnswerTest() {
    // Given
    answerService.addAnswer(createAnswerAddCommand(problem.problemId(), AnswerType.DESCRIPTION));

    // When
    Answer result = answerService.searchAnswer(problem.problemId());

    // Then
    assertAll(
        () -> assertThat(result.answerId()).isNotNull(),
        () -> assertThat(result.problem().problemId()).isEqualTo(problem.problemId()),
        () -> assertThat(result.answerType()).isEqualTo(AnswerType.DESCRIPTION),
        () -> assertThat(result.answerValues()).isNotEmpty(),
        () ->
            assertThat(Objects.requireNonNull(result.answerValues()).getFirst())
                .isIn(DESCRIPTIVE_OPTIONS),
        () -> assertThat(result.updatedAt()).isBeforeOrEqualTo(LocalDateTime.now().plusMinutes(1)));
  }

  @Test
  @DisplayName("답안을 수정한다.")
  void editChoiceAnswerTest() {
    // Given
    Answer answer =
        answerService.addAnswer(createAnswerAddCommand(problem.problemId(), AnswerType.CHOICE));

    AnswerCommand command = createAnswerAddCommand(problem.problemId(), AnswerType.DESCRIPTION);

    // When
    answerService.editAnswer(answer.answerId(), command);

    // Then
    Answer result = answerService.searchAnswer(problem.problemId());

    assertAll(
        () -> assertThat(result.answerId()).isEqualTo(answer.answerId()),
        () -> assertThat(result.problem().problemId()).isEqualTo(problem.problemId()),
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
    Answer answer =
        answerService.addAnswer(createAnswerAddCommand(problem.problemId(), AnswerType.CHOICE));

    // When
    answerService.removeAnswer(answer.answerId());

    // Then
    assertThatThrownBy(() -> answerService.searchAnswer(problem.problemId()))
        .isInstanceOf(NoSuchEntityException.class);
  }
}
