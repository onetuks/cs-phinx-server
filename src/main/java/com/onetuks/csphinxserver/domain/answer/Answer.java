package com.onetuks.csphinxserver.domain.answer;

import java.time.LocalDateTime;
import java.util.List;

public record Answer(
    String answerId,
    String questionId,
    AnswerType answerType,
    List<String> answerValues,
    LocalDateTime updatedAt) {}
