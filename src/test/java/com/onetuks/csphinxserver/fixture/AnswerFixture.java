package com.onetuks.csphinxserver.fixture;

import com.onetuks.csphinxserver.application.command.answer.ChoiceAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.DescriptiveAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.ShortAnswerAddCommand;
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

  public static ChoiceAnswer createChoiceAnswer(String questionId) {
    return new ChoiceAnswer(null, questionId, createAnswerNumber(), LocalDateTime.now());
  }

  public static ShortAnswer createShortAnswer(String questionId) {
    return new ShortAnswer(null, questionId, createAnswerWords(), LocalDateTime.now());
  }

  public static DescriptiveAnswer createDescriptiveAnswer(String questionId) {
    return new DescriptiveAnswer(null, questionId, createEmbeddedVector(), LocalDateTime.now());
  }

  public static ChoiceAnswerAddCommand createChoiceCommand(String questionId) {
    return new ChoiceAnswerAddCommand(questionId, createChoiceAnswer(questionId).answerNumber());
  }

  public static ShortAnswerAddCommand createShortCommand(String questionId) {
    return new ShortAnswerAddCommand(questionId, createShortAnswer(questionId).answerWords());
  }

  public static DescriptiveAnswerAddCommand createDescriptiveCommand(String questionId) {
    return new DescriptiveAnswerAddCommand(
        questionId, createDescriptiveAnswer(questionId).embeddedVector());
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
    return IntStream.range(1, random.nextInt(4))
        .mapToObj(i -> random.nextDouble())
        .collect(Collectors.toList());
  }
}
