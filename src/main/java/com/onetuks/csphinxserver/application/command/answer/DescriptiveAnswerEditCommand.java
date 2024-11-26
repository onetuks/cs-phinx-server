package com.onetuks.csphinxserver.application.command.answer;

import java.util.List;

public record DescriptiveAnswerEditCommand(String questionId, List<Double> embeddedVector) {}
