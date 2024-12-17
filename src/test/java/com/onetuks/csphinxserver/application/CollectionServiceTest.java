package com.onetuks.csphinxserver.application;

import static com.onetuks.csphinxserver.fixture.CollectionFixture.createCollectionAddCommand;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.onetuks.csphinxserver.CsPhinxServerApplicationTests;
import com.onetuks.csphinxserver.application.command.question.CollectionAddCommand;
import com.onetuks.csphinxserver.domain.question.Collection;
import com.onetuks.csphinxserver.domain.question.CollectionType;
import com.onetuks.csphinxserver.fixture.CollectionFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CollectionServiceTest extends CsPhinxServerApplicationTests {

  @Test
  @DisplayName("모음집을 추가한다.")
  void addCollectionTest() {
    // Given
    List<String> questionIds = List.of("1", "2", "3");
    CollectionAddCommand command = createCollectionAddCommand(questionIds.getFirst(), questionIds.get(1), questionIds.getLast());

    // When
    Collection result = collectionService.addCollection(command);

    // Then
    assertAll(
        () -> assertThat(result.collectionId()).isNotNull(),
        () -> assertThat(result.collectionName()).isNotBlank(),
        () -> assertThat(result.collectionType()).isInstanceOf(CollectionType.class),
        () -> assertThat(result.includedQuestionIds()).hasSize(questionIds.size()),
        () -> assertThat(result.includedQuestionIds()).containsAll(questionIds));
  }
}