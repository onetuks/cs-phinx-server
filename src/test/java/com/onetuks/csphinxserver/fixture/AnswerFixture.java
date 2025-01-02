package com.onetuks.csphinxserver.fixture;

import com.onetuks.csphinxserver.application.command.answer.AnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.AnswerEditCommand;
import com.onetuks.csphinxserver.domain.answer.Answer;
import com.onetuks.csphinxserver.domain.answer.AnswerType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class AnswerFixture {

  public static final List<String> CHOICE_OPTIONS = List.of("A", "B", "C", "D");
  public static final List<String> SHORT_OPTIONS = List.of("B+트리", "어셈블리어", "삽입정렬", "LinkedList");
  public static final List<String> DESCRIPTIVE_OPTIONS =
      List.of(
          "DB 인덱스는 삽입/갱신/삭제 성능을 포기하고, 읽기 성능에 주력한 기술입니다.",
          "파이썬은 C, java와는 달리 컴파일러를 사용하지 않는 인터프리터 언어입니다.",
          """
      삽입정렬은 현재 인덱스에 해당하는 값이 적절하게 정렬될 수 있는 인덱스에 삽입하고,
      원래 있던 값은 현재 인덱스(맨끝)으로 이동하는 정렬입니다. O(n^2) 시간복잡도를 갖습니다.""",
          """
      LinkedList는 Node와 Pointer로 구성되어 있으며, 포인터로 다음 노드를 참조합니다.
      따라서 삽입 및 삭제의 경우 이점이 있으나, 순차적으로 탐색하는 경우 연속 메모리 기반의 ArrayList보다 성능이 좋지 않습니다.
      """);

  private static final Random random = new Random();

  public static Answer createAnswer(String answerId, String questionId, AnswerType answerType) {
    return new Answer(
        answerId, questionId, answerType, createAnswerValues(answerType), LocalDateTime.now());
  }

  public static AnswerAddCommand createAnswerAddCommand(String questionId, AnswerType answerType) {
    Answer answer = createAnswer(null, questionId, answerType);
    return new AnswerAddCommand(questionId, answer.answerType(), answer.answerValues());
  }

  public static AnswerEditCommand createAnswerEditCommand(
      String answerId, String questionId, AnswerType answerType) {
    Answer answer = createAnswer(answerId, questionId, answerType);
    return new AnswerEditCommand(questionId, answer.answerType(), answer.answerValues());
  }

  private static List<String> createAnswerValues(AnswerType answerType) {
    return switch (answerType) {
      case CHOICE -> List.of(CHOICE_OPTIONS.get(random.nextInt(CHOICE_OPTIONS.size())));
      case SHORT ->
          List.of(
              SHORT_OPTIONS.get(random.nextInt(SHORT_OPTIONS.size())),
              SHORT_OPTIONS.get(random.nextInt(SHORT_OPTIONS.size())));
      case DESCRIPTION ->
          List.of(
              DESCRIPTIVE_OPTIONS.get(random.nextInt(DESCRIPTIVE_OPTIONS.size())),
              DESCRIPTIVE_OPTIONS.get(random.nextInt(DESCRIPTIVE_OPTIONS.size())));
    };
  }
}
