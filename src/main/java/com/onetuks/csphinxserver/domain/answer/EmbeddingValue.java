package com.onetuks.csphinxserver.domain.answer;

import java.util.List;

public record EmbeddingValue(
    String originContext,
    List<Double> embeddingVector
) {}
