package com.onetuks.csphinxserver.application.command.answer;

import java.util.List;

public record DescriptiveAnswerAddCommand(String questionId, List<Double> embeddedVector) {}
