package com.onetuks.csphinxserver.application.command.question;

import com.onetuks.csphinxserver.domain.answer.AnswerType;
import com.onetuks.csphinxserver.domain.question.Difficulty;
import com.onetuks.csphinxserver.domain.question.Topic;
import java.util.Set;

public record QuestionEditCommand(
    String title,
    String description,
    Difficulty difficulty,
    AnswerType answerType,
    Topic topic,
    Set<String> tags) {}
