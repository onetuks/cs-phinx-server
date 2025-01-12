package com.onetuks.csphinxserver.application.command;

import com.onetuks.csphinxserver.domain.answer.AnswerType;
import java.util.List;

public record AnswerCommand(Long problemId, AnswerType answerType, List<String> answerValues) {}
