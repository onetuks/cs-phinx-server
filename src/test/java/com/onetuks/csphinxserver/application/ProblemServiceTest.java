package com.onetuks.csphinxserver.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.onetuks.csphinxserver.CsPhinxServerApplicationTests;
import com.onetuks.csphinxserver.application.command.ProblemCommand;
import com.onetuks.csphinxserver.domain.problem.Problem;
import com.onetuks.csphinxserver.fixture.ProblemFixture;
import com.onetuks.csphinxserver.global.exception.NoSuchEntityException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

class ProblemServiceTest extends CsPhinxServerApplicationTests {

  @Test
  @DisplayName("문제를 추가한다")
  void addProblem() {
    // Given
    ProblemCommand command = ProblemFixture.createProblemCommand();

    // When
    Problem result = problemService.addProblem(command);

    // Then
    assertThat(result).isNotNull();
  }

  @Test
  @DisplayName("문제를 조회한다.")
  void searchProblem() {
    // Given
    ProblemCommand command = ProblemFixture.createProblemCommand();
    Problem problem = problemService.addProblem(command);

    // When
    Problem result = problemService.searchProblem(problem.problemId());

    // Then
    assertAll(
        () -> assertThat(result.problemId()).isEqualTo(problem.problemId()),
        () -> assertThat(result.title()).isEqualTo(command.title()),
        () -> assertThat(result.description()).isEqualTo(command.description()),
        () -> assertThat(result.difficulty()).isEqualTo(command.difficulty()),
        () -> assertThat(result.topic()).isEqualTo(command.topic()),
        () -> assertThat(result.tags()).isEqualTo(command.tags()),
        () -> assertThat(result.isActive()).isEqualTo(command.isActive()),
        () -> assertThat(result.updatedAt()).isBeforeOrEqualTo(LocalDateTime.now().plusMinutes(1)));
  }

  @Test
  @DisplayName("존재하지 않는 아이디로 문제 조회 시 예외를 던진다.")
  void searchProblemException() {
    // Given
    long notExistProblemId = 12_123_432L;

    // When & Then
    assertThatThrownBy(() -> problemService.searchProblem(notExistProblemId))
        .isInstanceOf(NoSuchEntityException.class);
  }

  @Test
  @DisplayName("전체 문제를 조회한다.")
  void searchProblems() {
    // Given
    Pageable pageable = PageRequest.of(0, 100);
    List<Problem> problems =
        IntStream.range(0, 5)
            .mapToObj(i -> problemService.addProblem(ProblemFixture.createProblemCommand()))
            .toList();
    List<Long> problemIds = problems.stream().map(Problem::problemId).toList();

    // When
    long results =
        problemService.searchProblems(pageable).getContent().stream()
            .filter(problem -> problemIds.contains(problem.problemId()))
            .count();

    // Then
    assertThat(results).isEqualTo(problems.size());
  }

  @Test
  @DisplayName("문제를 수정한다.")
  void editProblem() {
    // Given
    ProblemCommand postCommand = ProblemFixture.createProblemCommand();
    Problem problem = problemService.addProblem(postCommand);
    ProblemCommand patchCommand = ProblemFixture.createProblemCommand();

    // When
    problemService.editProblem(problem.problemId(), patchCommand);

    // Then
    Problem result = problemService.searchProblem(problem.problemId());

    assertAll(
        () -> assertThat(result.problemId()).isEqualTo(problem.problemId()),
        () -> assertThat(result.title()).isEqualTo(patchCommand.title()),
        () -> assertThat(result.description()).isEqualTo(patchCommand.description()),
        () -> assertThat(result.difficulty()).isEqualTo(patchCommand.difficulty()),
        () -> assertThat(result.topic()).isEqualTo(patchCommand.topic()),
        () -> assertThat(result.tags()).isEqualTo(patchCommand.tags()),
        () -> assertThat(result.isActive()).isEqualTo(patchCommand.isActive()),
        () -> assertThat(result.updatedAt()).isBeforeOrEqualTo(LocalDateTime.now().plusMinutes(1)));
  }

  @Test
  @DisplayName("문제를 삭제한다.")
  void removeProblem() {
    // Given
    ProblemCommand postCommand = ProblemFixture.createProblemCommand();
    Problem problem = problemService.addProblem(postCommand);

    // When
    problemService.removeProblem(problem.problemId());

    // Then
    assertThatThrownBy(() -> problemService.searchProblem(problem.problemId()))
        .isInstanceOf(NoSuchEntityException.class);
  }
}
