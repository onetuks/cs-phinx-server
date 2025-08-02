package com.onetuks.csphinxserver.domain.grader;

import java.util.List;

public record Grade(
    String userAnswer, List<String> desirableAnswers, Integer userScore, String gradeComment) {

  public static Grade of(String userAnswer, List<String> desirableAnswers) {
    return new Grade(userAnswer, desirableAnswers, null, null);
  }
}
