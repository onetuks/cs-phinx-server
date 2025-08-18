package com.onetuks.csphinxserver.domain.grader;

import java.util.List;

public record Grade(
    String userAnswer, List<String> desirableAnswers, Integer bestScore, String bestAnswer) {}
