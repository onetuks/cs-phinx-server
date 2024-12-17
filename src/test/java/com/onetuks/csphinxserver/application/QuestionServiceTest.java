package com.onetuks.csphinxserver.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.onetuks.csphinxserver.CsPhinxServerApplicationTests;
import com.onetuks.csphinxserver.application.command.question.QuestionEditCommand;
import com.onetuks.csphinxserver.application.command.question.QuestionAddCommand;
import com.onetuks.csphinxserver.domain.question.Question;
import com.onetuks.csphinxserver.fixture.QuestionFixture;
import com.onetuks.csphinxserver.global.exception.NoSuchEntityException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

class QuestionServiceTest extends CsPhinxServerApplicationTests {

  @Test
  @DisplayName("문제를 추가한다")
  void addQuestion() {
    // Given
    QuestionAddCommand command = QuestionFixture.createQuestionAddCommand();

    // When
    String result = questionService.addQuestion(command);

    // Then
    assertThat(result).isNotNull();
  }

  @Test
  @DisplayName("문제를 조회한다.")
  void searchQuestion() {
    // Given
    QuestionAddCommand command = QuestionFixture.createQuestionAddCommand();
    String questionId = questionService.addQuestion(command);

    // When
    Question result = questionService.searchQuestion(questionId);

    // Then
    assertAll(
        () -> assertThat(result.questionId()).isEqualTo(questionId),
        () -> assertThat(result.title()).isEqualTo(command.title()),
        () -> assertThat(result.description()).isEqualTo(command.description()),
        () -> assertThat(result.difficulty()).isEqualTo(command.difficulty()),
        () -> assertThat(result.timeLimit().seconds()).isEqualTo(command.timeLimit()),
        () -> assertThat(result.category()).isEqualTo(command.category()),
        () -> assertThat(result.topic()).isEqualTo(command.topic()),
        () -> assertThat(result.tags()).isEqualTo(command.tags()),
        () -> assertThat(result.updatedAt()).isBeforeOrEqualTo(LocalDateTime.now()),
        () -> assertThat(result.likeCount()).isZero(),
        () -> assertThat(result.attemptCount()).isZero(),
        () -> assertThat(result.solvedCount()).isZero());
  }

  @Test
  @DisplayName("존재하지 않는 아이디로 문제 조회 시 예외를 던진다.")
  void searchQuestionException() {
    // Given
    String notExistQuestionId = "not-exist-question-id";

    // When & Then
    assertThatThrownBy(() -> questionService.searchQuestion(notExistQuestionId))
        .isInstanceOf(NoSuchEntityException.class);
  }

  @Test
  @DisplayName("전체 문제를 조회한다.")
  void searchQuestions() {
    // Given
    Pageable pageable = PageRequest.of(0, 10);
    List<String> questionIds = IntStream.range(0, 5)
        .mapToObj(i -> questionService.addQuestion(QuestionFixture.createQuestionAddCommand()))
        .toList();

    // When
    Page<Question> questions = questionService.searchQuestions(pageable);

    // Then
    List<Question> results = questions.getContent().stream()
        .filter(question -> questionIds.contains(question.questionId()))
        .toList();

    assertThat(results).hasSize(questionIds.size());
  }
  
  @Test
  @DisplayName("문제를 수정한다.")
  void editQuestion() {
    // Given
    QuestionAddCommand postCommand = QuestionFixture.createQuestionAddCommand();
    String questionId = questionService.addQuestion(postCommand);
    QuestionEditCommand command = QuestionFixture.createQuestionEditCommand();

    // When
    questionService.editQuestion(questionId, command);
    
    // Then
    Question result = questionService.searchQuestion(questionId);

    assertAll(
        () -> assertThat(result.questionId()).isEqualTo(questionId),
        () -> assertThat(result.title()).isEqualTo(command.title()),
        () -> assertThat(result.description()).isEqualTo(command.description()),
        () -> assertThat(result.difficulty()).isEqualTo(command.difficulty()),
        () -> assertThat(result.timeLimit().seconds()).isEqualTo(command.timeLimit()),
        () -> assertThat(result.category()).isEqualTo(command.category()),
        () -> assertThat(result.topic()).isEqualTo(command.topic()),
        () -> assertThat(result.tags()).isEqualTo(command.tags()),
        () -> assertThat(result.updatedAt()).isBeforeOrEqualTo(LocalDateTime.now()),
        () -> assertThat(result.likeCount()).isZero(),
        () -> assertThat(result.attemptCount()).isZero(),
        () -> assertThat(result.solvedCount()).isZero());
  }

  @Test
  @DisplayName("문제를 삭제한다.")
  void removeQuestion() {
    // Given
    QuestionAddCommand postCommand = QuestionFixture.createQuestionAddCommand();
    String questionId = questionService.addQuestion(postCommand);

    // When
    questionService.removeQuestion(questionId);

    // Then
    assertThatThrownBy(() -> questionService.searchQuestion(questionId))
        .isInstanceOf(NoSuchEntityException.class);
  }
}