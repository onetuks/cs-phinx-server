package com.onetuks.csphinxserver.domain.answer;

import java.time.LocalDateTime;
import java.util.List;

public record DescriptiveAnswer(
    String answerId,
    String questionId,
    List<Double> embeddedVector,
    LocalDateTime updatedAt
) implements Answer {}
