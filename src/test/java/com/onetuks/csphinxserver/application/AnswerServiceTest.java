package com.onetuks.csphinxserver.application;

import static com.onetuks.csphinxserver.fixture.AnswerFixture.createChoiceAddCommand;
import static com.onetuks.csphinxserver.fixture.AnswerFixture.createChoiceAnswerEditCommand;
import static com.onetuks.csphinxserver.fixture.AnswerFixture.createDescriptiveAddCommand;
import static com.onetuks.csphinxserver.fixture.AnswerFixture.createDescriptiveAnswerEditCommand;
import static com.onetuks.csphinxserver.fixture.AnswerFixture.createShortAddCommand;
import static com.onetuks.csphinxserver.fixture.AnswerFixture.createShortAnswerEditCommand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswerServiceTest extends CsPhinxServerApplicationTests {

  @Test
  @DisplayName("객관식 답안을 추가한다")
  void addChoiceAnswerTest() {
    // Given
    String questionId = UUID.randomUUID().toString();
    ChoiceAnswerAddCommand command = createChoiceAddCommand(questionId);

    // When
    String result = answerService.addChoiceAnswer(command);

    // Then
    assertThat(result).isNotNull();
  }

  @Test
  @DisplayName("단답형 답안을 추가한다.")
  void addShortAnswerTest() {
    // Given
    String questionId = UUID.randomUUID().toString();
    ShortAnswerAddCommand command = createShortAddCommand(questionId);

    // When
    String result = answerService.addShortAnswer(command);

    // Then
    assertThat(result).isNotNull();
  }

  @Test
  @DisplayName("서술형 답안을 추가한다.")
  void addDescriptiveAnswerTest() {
    // Given
    String questionId = UUID.randomUUID().toString();
    DescriptiveAnswerAddCommand command = createDescriptiveAddCommand(questionId);

    // When
    String result = answerService.addDescriptiveAnswer(command);

    // Then
    assertThat(result).isNotNull();
  }

  @Test
  @DisplayName("객관식 답안을 조회한다.")
  void searchAnswersTest() {
    // Given
    String questionId = UUID.randomUUID().toString();
    answerService.addChoiceAnswer(createChoiceAddCommand(questionId));

    // When
    List<Answer> answer = answerService.searchAnswers(questionId);

    // Then
    assertThat(answer).isNotNull().hasSize(1);

    ChoiceAnswer result = (ChoiceAnswer) answer.getFirst();

    assertAll(
        () -> assertThat(result.answerId()).isNotNull(),
        () -> assertThat(result.questionId()).isEqualTo(questionId),
        () -> assertThat(Integer.parseInt(result.value())).isLessThanOrEqualTo(4),
        () -> assertThat(result.updatedAt()).isBeforeOrEqualTo(LocalDateTime.now()));
  }

  @Test
  @DisplayName("단답형 답안을 조회한다.")
  void searchShortAnswerTest() {
    // Given
    String questionId = UUID.randomUUID().toString();
    answerService.addShortAnswer(createShortAddCommand(questionId));
    answerService.addShortAnswer(createShortAddCommand(questionId));

    // When
    List<Answer> answer = answerService.searchAnswers(questionId);

    // Then
    List<ShortAnswer> results = answer.stream().map(a -> (ShortAnswer) a).toList();

    assertThat(results).isNotNull()
        .hasSize(2)
        .allSatisfy(result -> {
          assertThat(result.answerId()).isNotNull();
          assertThat(result.questionId()).isEqualTo(questionId);
          assertThat(result.value()).isNotEmpty();
          assertThat(result.updatedAt()).isBeforeOrEqualTo(LocalDateTime.now());
        });
  }

  @Test
  @DisplayName("서술형 답안을 조회한다.")
  void searchDescriptiveAnswerTest() {
    // Given
    String questionId = UUID.randomUUID().toString();
    answerService.addDescriptiveAnswer(createDescriptiveAddCommand(questionId));
    answerService.addDescriptiveAnswer(createDescriptiveAddCommand(questionId));

    // When
    List<Answer> answer = answerService.searchAnswers(questionId);

    // Then
    List<DescriptiveAnswer> results = answer.stream().map(a -> (DescriptiveAnswer) a).toList();

    assertThat(results).isNotNull()
        .hasSize(2)
        .allSatisfy(result -> {
          assertThat(result.answerId()).isNotNull();
          assertThat(result.questionId()).isEqualTo(questionId);
          assertThat(result.value()).isNotNull();
          assertThat(result.value().originContext()).isNotBlank();
          assertThat(result.value().embeddingVector()).isNotEmpty();
          assertThat(result.updatedAt()).isBeforeOrEqualTo(LocalDateTime.now());
        });
  }

  @Test
  @DisplayName("객관식 답안을 수정한다.")
  void editChoiceAnswerTest() {
    // Given
    String questionId = UUID.randomUUID().toString();
    String answerId = answerService.addChoiceAnswer(createChoiceAddCommand(questionId));
    Answer answer = answerService.searchAnswers(questionId).getFirst();

    ChoiceAnswerEditCommand command = createChoiceAnswerEditCommand(answerId, questionId);

    // When
    answerService.editChoiceAnswer(answerId, command);

    // Then
    ChoiceAnswer result = (ChoiceAnswer) answerService.searchAnswers(questionId).getFirst();

    assertAll(
        () -> assertThat(result.answerId()).isEqualTo(answerId),
        () -> assertThat(result.questionId()).isEqualTo(questionId),
        () -> assertThat(result.value()).isEqualTo(command.answerNumber()),
        () -> assertThat(result.updatedAt()).isAfterOrEqualTo(answer.updatedAt()));
  }

  @Test
  @DisplayName("단답형 답안을 수정한다.")
  void editShortAnswerTest() {
    // Given
    String questionId = UUID.randomUUID().toString();
    String answerId = answerService.addShortAnswer(createShortAddCommand(questionId));

    ShortAnswerEditCommand command = createShortAnswerEditCommand(answerId, questionId);

    // When
    answerService.editShortAnswer(answerId, command);

    // Then
    List<ShortAnswer> results = answerService.searchAnswers(questionId).stream()
        .map(a -> (ShortAnswer) a).toList();

    assertThat(results).isNotNull()
        .hasSize(1)
        .allSatisfy(result -> {
          assertThat(result.answerId()).isEqualTo(answerId);
          assertThat(result.questionId()).isEqualTo(questionId);
          assertThat(result.value()).isEqualTo(command.answerWord());
          assertThat(result.updatedAt()).isBeforeOrEqualTo(LocalDateTime.now());
        });
  }

  @Test
  @DisplayName("서술형 답안을 수정한다.")
  void editDescriptiveAnswerTest() {
    // Given
    String questionId = UUID.randomUUID().toString();
    String answerId = answerService.addDescriptiveAnswer(createDescriptiveAddCommand(questionId));

    DescriptiveAnswerEditCommand command = createDescriptiveAnswerEditCommand(answerId, questionId);

    // When
    answerService.editDescriptiveAnswer(answerId, command);

    // Then
    List<DescriptiveAnswer> results = answerService.searchAnswers(questionId).stream()
        .map(a -> (DescriptiveAnswer) a).toList();

    assertThat(results).isNotNull()
        .hasSize(1)
        .allSatisfy(result -> {
          assertThat(result.answerId()).isEqualTo(answerId);
          assertThat(result.questionId()).isEqualTo(questionId);
          assertThat(result.value()).isNotNull();
          assertThat(result.value().originContext()).isNotBlank();
          assertThat(result.value().embeddingVector()).isNotEmpty();
          assertThat(result.updatedAt()).isBeforeOrEqualTo(LocalDateTime.now());
        });
  }

  @Test
  @DisplayName("답안을 제거한다.")
  void removeAnswerTest() {
    // Given
    String questionId = UUID.randomUUID().toString();
    String answerId = answerService.addChoiceAnswer(createChoiceAddCommand(questionId));

    // When
    answerService.removeAnswer(answerId);

    // Then
    List<Answer> results = answerService.searchAnswers(questionId);

    assertThat(results).isNotNull().hasSize(0);
  }
}