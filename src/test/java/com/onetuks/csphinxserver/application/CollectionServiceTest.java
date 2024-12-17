package com.onetuks.csphinxserver.application;

import static com.onetuks.csphinxserver.fixture.CollectionFixture.createCollectionAddCommand;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import com.onetuks.csphinxserver.CsPhinxServerApplicationTests;
import com.onetuks.csphinxserver.application.command.question.CollectionAddCommand;
import com.onetuks.csphinxserver.domain.question.Collection;
import com.onetuks.csphinxserver.domain.question.CollectionType;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
