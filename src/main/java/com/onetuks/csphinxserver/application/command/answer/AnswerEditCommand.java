package com.onetuks.csphinxserver.application.command.answer;

import com.onetuks.csphinxserver.domain.answer.AnswerType;
import java.util.List;

public record AnswerEditCommand(
    String questionId, AnswerType answerType, List<String> answerValues) {}
