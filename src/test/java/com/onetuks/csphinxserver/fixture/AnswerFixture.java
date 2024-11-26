package com.onetuks.csphinxserver.fixture;

import com.onetuks.csphinxserver.application.command.answer.ChoiceAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.ChoiceAnswerEditCommand;
import com.onetuks.csphinxserver.application.command.answer.DescriptiveAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.DescriptiveAnswerEditCommand;
import com.onetuks.csphinxserver.application.command.answer.ShortAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.ShortAnswerEditCommand;
import com.onetuks.csphinxserver.domain.answer.AnswerType;
import com.onetuks.csphinxserver.domain.answer.ChoiceAnswer;
import com.onetuks.csphinxserver.domain.answer.DescriptiveAnswer;
import com.onetuks.csphinxserver.domain.answer.ShortAnswer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AnswerFixture {

  private static final Random random = new Random();

  public static ChoiceAnswer createChoiceAnswer(String answerId, String questionId) {
    return new ChoiceAnswer(answerId, questionId, AnswerType.CHOICE, createAnswerNumber(), LocalDateTime.now());
  }

  public static ShortAnswer createShortAnswer(String answerId, String questionId) {
    return new ShortAnswer(answerId, questionId, AnswerType.SHORT, createAnswerWords(), LocalDateTime.now());
  }

  public static DescriptiveAnswer createDescriptiveAnswer(String answerId, String questionId) {
    return new DescriptiveAnswer(answerId, questionId, AnswerType.DESCRIPTION, createEmbeddedVector(), LocalDateTime.now());
  }

  public static ChoiceAnswerAddCommand createChoiceAddCommand(String questionId) {
    return new ChoiceAnswerAddCommand(
        questionId, createChoiceAnswer(null, questionId).value());
  }

  public static ShortAnswerAddCommand createShortAddCommand(String questionId) {
    return new ShortAnswerAddCommand(
        questionId, createShortAnswer(null, questionId).value());
  }

  public static DescriptiveAnswerAddCommand createDescriptiveAddCommand(String questionId) {
    return new DescriptiveAnswerAddCommand(
        questionId, createDescriptiveAnswer(null, questionId).value());
  }

  public static ChoiceAnswerEditCommand createChoiceAnswerEditCommand(
      String answerId, String questionId) {
    return new ChoiceAnswerEditCommand(
        questionId, createChoiceAnswer(answerId, questionId).value());
  }

  public static ShortAnswerEditCommand createShortAnswerEditCommand(
      String answerId, String questionId) {
    return new ShortAnswerEditCommand(questionId, createShortAnswer(answerId, questionId).value());
  }

  public static DescriptiveAnswerEditCommand createDescriptiveAnswerEditCommand(
      String answerId, String questionId) {
    return new DescriptiveAnswerEditCommand(
        questionId, createDescriptiveAnswer(answerId, questionId).value());
  }

  private static String createAnswerNumber() {
    return String.valueOf(random.nextInt(4) + 1);
  }

  private static Set<String> createAnswerWords() {
    return IntStream.range(1, random.nextInt(10))
        .mapToObj(i -> "word" + i)
        .collect(Collectors.toSet());
  }

  private static List<Double> createEmbeddedVector() {
    return IntStream.range(0, random.nextInt(4) + 1)
        .mapToObj(i -> random.nextDouble())
        .collect(Collectors.toList());
  }
}
