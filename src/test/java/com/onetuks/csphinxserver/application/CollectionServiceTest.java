package com.onetuks.csphinxserver.application;

import static com.onetuks.csphinxserver.fixture.CollectionFixture.createCollectionAddCommand;
import static com.onetuks.csphinxserver.fixture.CollectionFixture.createCollectionEditCommand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.onetuks.csphinxserver.CsPhinxServerApplicationTests;
import com.onetuks.csphinxserver.application.command.question.CollectionAddCommand;
import com.onetuks.csphinxserver.application.command.question.CollectionEditCommand;
import com.onetuks.csphinxserver.domain.question.Collection;
import com.onetuks.csphinxserver.domain.question.CollectionType;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

class CollectionServiceTest extends CsPhinxServerApplicationTests {

  private final List<String> INCLUDED_QUESTION_IDS = List.of("1", "2", "3");

  private Collection collection;

  @BeforeEach
  void setUp() {
    collection =
        collectionService.addCollection(
            createCollectionAddCommand(
                INCLUDED_QUESTION_IDS.getFirst(),
                INCLUDED_QUESTION_IDS.get(1),
                INCLUDED_QUESTION_IDS.getLast()));
  }

  @Test
  @DisplayName("모음집을 추가한다.")
  void addCollectionTest() {
    // Given
    CollectionAddCommand command =
        createCollectionAddCommand(
            INCLUDED_QUESTION_IDS.getFirst(),
            INCLUDED_QUESTION_IDS.get(1),
            INCLUDED_QUESTION_IDS.getLast());

    // When
    Collection result = collectionService.addCollection(command);

    // Then
    assertAll(
        () -> assertThat(result.collectionId()).isNotNull(),
        () -> assertThat(result.collectionName()).isNotBlank(),
        () -> assertThat(result.collectionType()).isInstanceOf(CollectionType.class),
        () -> assertThat(result.includedQuestionIds()).hasSize(INCLUDED_QUESTION_IDS.size()),
        () -> assertThat(result.includedQuestionIds()).containsAll(INCLUDED_QUESTION_IDS));
  }

  @Test
  @DisplayName("모음집을 상세 조회한다.")
  void searchCollectionTest() {
    // Given
    String collectionId = collection.collectionId();

    // When
    Collection result = collectionService.searchCollection(collectionId);

    // Then
    assertAll(
        () -> assertThat(result.collectionId()).isNotNull(),
        () -> assertThat(result.collectionName()).isNotBlank(),
        () -> assertThat(result.collectionType()).isInstanceOf(CollectionType.class),
        () -> assertThat(result.includedQuestionIds()).hasSize(INCLUDED_QUESTION_IDS.size()),
        () -> assertThat(result.includedQuestionIds()).containsAll(INCLUDED_QUESTION_IDS));
  }

  @Test
  @DisplayName("모음집을 전체 조회한다.")
  void searchAllCollectionsTest() {
    // Given
    Pageable pageable = PageRequest.of(0, 10);
    List<Collection> collections =
        IntStream.range(0, 3)
            .mapToObj(
                i ->
                    collectionService.addCollection(
                        createCollectionAddCommand(
                            INCLUDED_QUESTION_IDS.getFirst(),
                            INCLUDED_QUESTION_IDS.get(1),
                            INCLUDED_QUESTION_IDS.getLast())))
            .toList();

    // When
    Page<Collection> results = collectionService.searchAllCollections(pageable);

    // Then
    assertThat(results)
        .hasSizeGreaterThanOrEqualTo(collections.size())
        .allSatisfy(
            result -> {
              assertThat(result.collectionId()).isNotNull();
              assertThat(result.collectionName()).isNotBlank();
              assertThat(result.collectionType()).isInstanceOf(CollectionType.class);
            });
  }

  @Test
  @DisplayName("모음집을 수정한다.")
  void editCollectionTest() {
    // Given
    CollectionEditCommand command =
        createCollectionEditCommand(
            INCLUDED_QUESTION_IDS.getFirst(), INCLUDED_QUESTION_IDS.getLast());

    // When
    collectionService.editCollection(collection.collectionId(), command);

    // Then
    Collection result = collectionService.searchCollection(collection.collectionId());

    assertAll(
        () -> assertThat(result.collectionId()).isEqualTo(collection.collectionId()),
        () -> assertThat(result.collectionName()).isEqualTo(command.collectionName()),
        () -> assertThat(result.collectionType()).isEqualTo(command.collectionType()),
        () ->
            assertThat(result.includedQuestionIds())
                .containsExactlyElementsOf(command.includedQuestionIds()));
  }
}
