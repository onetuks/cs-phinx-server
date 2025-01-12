package com.onetuks.csphinxserver.domain.answer;

import com.onetuks.csphinxserver.domain.problem.Problem;
import java.time.LocalDateTime;
import java.util.List;

public record Answer(
    Long answerId,
    Problem problem,
    AnswerType answerType,
    List<String> answerValues,
    LocalDateTime updatedAt) {}
