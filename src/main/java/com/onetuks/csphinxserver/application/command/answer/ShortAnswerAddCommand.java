package com.onetuks.csphinxserver.application.command.answer;

import java.util.Set;

public record ShortAnswerAddCommand(String questionId, Set<String> answerWords) {}
