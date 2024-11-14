package com.onetuks.csphinxserver.fixture;

import com.onetuks.csphinxserver.application.command.question.QuestionPatchCommand;
import com.onetuks.csphinxserver.application.command.question.QuestionPostCommand;
import com.onetuks.csphinxserver.domain.question.Category;
import com.onetuks.csphinxserver.domain.question.Difficulty;
import com.onetuks.csphinxserver.domain.question.Question;
import com.onetuks.csphinxserver.domain.question.TimeLimit;
import com.onetuks.csphinxserver.domain.question.Topic;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QuestionFixture {

  private static final Random random = new Random();
  private static final List<String> TITLES = List.of(
      "JVM에 대한 문제", "데이터베이스 설계 순서", "NoSQL과 RDBMS의 차이", "OSI 7계층", "IPv4와 IPv6의 차이");
  private static final List<String> TAGS = List.of(
      "Java", "JVM", "Database", "NoSQL", "RDBMS", "Network", "OSI", "IPv4", "IPv6");

  public static Question create() {
    return new Question(
        null, createTitle(), createDescription(),
        createDifficulty(), createTimeLimit(), createCategory(), createTopic(), createTags(),
        createUpdatedAt(), createLikeCount(), createAttemptCount(), createSolvedCount());
  }

  public static QuestionPostCommand createPostCommand() {
    Question question = create();
    return new QuestionPostCommand(
        question.title(), question.description(), question.difficulty(),
        question.timeLimit().seconds(), question.category(), question.topic(), question.tags());
  }

  public static QuestionPatchCommand createPatchCommand() {
    Question question = create();
    return new QuestionPatchCommand(
        question.title(), question.description(), question.difficulty(),
        question.timeLimit().seconds(), question.category(), question.topic(), question.tags());
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

  private static TimeLimit createTimeLimit() {
    return new TimeLimit(random.nextInt(100) + 1);
  }

  private static Category createCategory() {
    return Category.values()[random.nextInt(Category.values().length)];
  }

  private static Topic createTopic() {
    return Topic.values()[random.nextInt(Topic.values().length)];
  }

  private static Set<String> createTags() {
    return IntStream.range(1, random.nextInt(TAGS.size()))
        .mapToObj(i -> TAGS.get(random.nextInt(TAGS.size())))
        .collect(Collectors.toSet());
  }

  private static LocalDateTime createUpdatedAt() {
    return LocalDateTime.now();
  }

  private static int createLikeCount() {
    return random.nextInt(100);
  }

  private static int createAttemptCount() {
    return random.nextInt(100);
  }

  private static int createSolvedCount() {
    return random.nextInt(100);
  }
}
