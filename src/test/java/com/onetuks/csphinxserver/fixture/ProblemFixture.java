package com.onetuks.csphinxserver.fixture;

import com.onetuks.csphinxserver.application.command.ProblemCommand;
import com.onetuks.csphinxserver.domain.problem.Difficulty;
import com.onetuks.csphinxserver.domain.problem.Topic;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProblemFixture {

  private static final Random random = new Random();
  private static final List<String> TITLES =
      List.of("JVM에 대한 문제", "데이터베이스 설계 순서", "NoSQL과 RDBMS의 차이", "OSI 7계층", "IPv4와 IPv6의 차이");
  private static final List<String> TAGS =
      List.of("Java", "JVM", "Database", "NoSQL", "RDBMS", "Network", "OSI", "IPv4", "IPv6");

  public static ProblemCommand createProblemCommand() {
    return new ProblemCommand(
        createTitle(),
        createDescription(),
        createDifficulty(),
        createTopic(),
        createTags(),
        createIsActive());
  }

  private static String createTitle() {
    return TITLES.get(random.nextInt(TITLES.size()));
  }

  private static String createDescription() {
    return "문제지문" + UUID.randomUUID();
  }

  private static Difficulty createDifficulty() {
    return Difficulty.values()[random.nextInt(Difficulty.values().length)];
  }

  private static Topic createTopic() {
    return Topic.values()[random.nextInt(Topic.values().length)];
  }

  private static Set<String> createTags() {
    return IntStream.range(1, random.nextInt(TAGS.size()))
        .mapToObj(i -> TAGS.get(random.nextInt(TAGS.size())))
        .collect(Collectors.toSet());
  }

  private static boolean createIsActive() {
    return random.nextBoolean();
  }
}
