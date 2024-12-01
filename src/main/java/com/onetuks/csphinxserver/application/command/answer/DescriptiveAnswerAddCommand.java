package com.onetuks.csphinxserver.application.command.answer;

import com.onetuks.csphinxserver.domain.answer.EmbeddingValue;

public record DescriptiveAnswerAddCommand(String questionId, EmbeddingValue embeddingValue) {}
