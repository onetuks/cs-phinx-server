package com.onetuks.csphinxserver.domain.answer;

import java.time.LocalDateTime;
import java.util.Set;

public record ShortAnswer(
    String answerId,
    String questionId,
    Set<String> answerWords,
    LocalDateTime updatedAt
) implements Answer {}
