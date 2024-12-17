package com.onetuks.csphinxserver.fixture;

import com.onetuks.csphinxserver.application.command.question.CollectionAddCommand;
import com.onetuks.csphinxserver.domain.question.Collection;
import com.onetuks.csphinxserver.domain.question.CollectionType;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CollectionFixture {

  private static final Random random = new Random();

  private static final List<String> COLLECTION_NAMES = List.of(
      "OS 모음집", "자료구조 모음집", "우리카드 면접직전", "금융결제원 면접 직전", "오늘의문제");

  public static Collection create(String collectionId, String... questionIds) {
    return new Collection(
        collectionId,
        createCollectionName(),
        createCollectionType(),
        Set.of(questionIds));
  }

  public static CollectionAddCommand createCollectionAddCommand(String... questionIds) {
    return new CollectionAddCommand(createCollectionName(), createCollectionType(), Set.of(questionIds));
  }

  private static String createCollectionName() {
    return COLLECTION_NAMES.get(random.nextInt(COLLECTION_NAMES.size()));
  }

  private static CollectionType createCollectionType() {
    return CollectionType.values()[random.nextInt(CollectionType.values().length)];
  }
}
