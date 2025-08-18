package com.onetuks.csphinxserver.adapter.out.web.dto;

import java.util.List;

public record GradeRequest(String userAnswer, List<String> desirableAnswers) {}
