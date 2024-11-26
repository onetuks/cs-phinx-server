package com.onetuks.csphinxserver.application.command.answer;

import java.util.Set;

public record ShortAnswerEditCommand(String questionId, Set<String> answerWords) {}
