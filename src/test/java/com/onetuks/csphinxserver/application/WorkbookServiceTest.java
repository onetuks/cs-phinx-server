package com.onetuks.csphinxserver.application;

import static com.onetuks.csphinxserver.fixture.ProblemFixture.createProblemCommand;
import static com.onetuks.csphinxserver.fixture.WorkbookFixture.createWorkbookCommand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.onetuks.csphinxserver.CsPhinxServerApplicationTests;
import com.onetuks.csphinxserver.application.command.WorkbookCommand;
import com.onetuks.csphinxserver.domain.problem.Problem;
import com.onetuks.csphinxserver.domain.workbook.CollectionType;
import com.onetuks.csphinxserver.domain.workbook.Workbook;
import com.onetuks.csphinxserver.global.exception.NoSuchEntityException;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

class WorkbookServiceTest extends CsPhinxServerApplicationTests {

  private Workbook workbook;
  private List<Problem> problems;

  @BeforeEach
  void setUp() {
    problems =
        IntStream.range(0, 4)
            .mapToObj(i -> problemService.addProblem(createProblemCommand()))
            .toList();

    workbook =
        workbookService.addWorkbook(
            createWorkbookCommand(problems.getFirst().problemId(), problems.getLast().problemId()));
  }

  @Test
  @DisplayName("모음집을 추가한다.")
  void addCollectionTest() {
    // Given
    WorkbookCommand command =
        createWorkbookCommand(problems.get(1).problemId(), problems.get(2).problemId());

    // When
    Workbook result = workbookService.addWorkbook(command);

    // Then
    assertAll(
        () -> assertThat(result.workbookId()).isNotNull(),
        () -> assertThat(result.title()).isNotBlank(),
        () -> assertThat(result.collectionType()).isInstanceOf(CollectionType.class),
        () -> assertThat(result.includedProblems()).hasSize(2),
        () ->
            assertThat(result.includedProblems())
                .containsAll(List.of(problems.get(1), problems.get(2))));
  }

  @Test
  @DisplayName("모음집을 상세 조회한다.")
  void searchCollectionTest() {
    // Given
    Long workbookId = workbook.workbookId();

    // When
    Workbook result = workbookService.searchWorkbook(workbookId);

    // Then
    assertAll(
        () -> assertThat(result.workbookId()).isNotNull(),
        () -> assertThat(result.title()).isNotBlank(),
        () -> assertThat(result.collectionType()).isInstanceOf(CollectionType.class),
        () -> assertThat(result.includedProblems()).hasSize(2),
        () ->
            assertThat(result.includedProblems())
                .containsAll(List.of(problems.getFirst(), problems.getLast())));
  }

  @Test
  @DisplayName("모음집을 전체 조회한다.")
  void searchAllCollectionsTest() {
    // Given
    Pageable pageable = PageRequest.of(0, 10);
    List<Workbook> workbooks =
        IntStream.range(0, 3)
            .mapToObj(
                i ->
                    workbookService.addWorkbook(
                        createWorkbookCommand(
                            problems.getFirst().problemId(),
                            problems.get(1).problemId(),
                            problems.getLast().problemId())))
            .toList();

    // When
    Page<Workbook> results = workbookService.searchAllWorkbooks(pageable);

    // Then
    assertThat(results)
        .hasSizeGreaterThanOrEqualTo(workbooks.size())
        .allSatisfy(
            result -> {
              assertThat(result.workbookId()).isNotNull();
              assertThat(result.title()).isNotBlank();
              assertThat(result.collectionType()).isInstanceOf(CollectionType.class);
            });
  }

  @Test
  @DisplayName("모음집을 수정한다.")
  void editCollectionTest() {
    // Given
    WorkbookCommand command =
        createWorkbookCommand(problems.getFirst().problemId(), problems.getLast().problemId());

    // When
    workbookService.editWorkbook(workbook.workbookId(), command);

    // Then
    Workbook result = workbookService.searchWorkbook(workbook.workbookId());

    assertAll(
        () -> assertThat(result.workbookId()).isEqualTo(workbook.workbookId()),
        () -> assertThat(result.title()).isEqualTo(command.title()),
        () -> assertThat(result.collectionType()).isEqualTo(command.collectionType()),
        () ->
            assertThat(result.includedProblems())
                .containsAll(List.of(problems.getFirst(), problems.getLast())));
  }

  @Test
  @DisplayName("모음집을 삭제한다.")
  void removeCollectionTest() {
    // Given
    Long workbookId = workbook.workbookId();

    // When
    workbookService.removeWorkbook(workbookId);

    // Then
    assertThatThrownBy(() -> workbookService.searchWorkbook(workbookId))
        .isInstanceOf(NoSuchEntityException.class);
  }
}
