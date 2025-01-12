package com.onetuks.csphinxserver.fixture;

import com.onetuks.csphinxserver.application.command.WorkbookCommand;
import com.onetuks.csphinxserver.domain.workbook.CollectionType;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class WorkbookFixture {

  private static final Random random = new Random();

  private static final List<String> COLLECTION_NAMES =
      List.of("OS 모음집", "자료구조 모음집", "우리카드 면접직전", "금융결제원 면접 직전", "오늘의문제");

  public static WorkbookCommand createWorkbookCommand(Long... problemIds) {
    return new WorkbookCommand(
        createTitle(),
        createDescription(),
        createCollectionType(),
        List.of(problemIds),
        createIsActive());
  }

  private static String createTitle() {
    return COLLECTION_NAMES.get(random.nextInt(COLLECTION_NAMES.size()));
  }

  private static String createDescription() {
    return UUID.randomUUID().toString();
  }

  private static CollectionType createCollectionType() {
    return CollectionType.values()[random.nextInt(CollectionType.values().length)];
  }

  private static boolean createIsActive() {
    return random.nextBoolean();
  }
}
