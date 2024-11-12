package com.onetuks.csphinxserver.domain.answer;

import java.time.LocalDateTime;

public record ChoiceAnswer(
    String answerId,
    String questionId,
    String answerNumber,
    LocalDateTime updatedAt
) implements Answer {}
