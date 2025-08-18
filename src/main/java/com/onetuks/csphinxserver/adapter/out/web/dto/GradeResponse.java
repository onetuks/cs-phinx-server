package com.onetuks.csphinxserver.adapter.out.web.dto;

import com.onetuks.csphinxserver.domain.grader.Grade;
import java.util.List;

public record GradeResponse(
    String userAnswer, List<String> desirableAnswers, Integer bestScore, String bestAnswer) {

  public static Grade from(GradeResponse response) {
    return new Grade(
        response.userAnswer, response.desirableAnswers, response.bestScore, response.bestAnswer);
  }
}
