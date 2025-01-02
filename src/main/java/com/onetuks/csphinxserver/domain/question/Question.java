package com.onetuks.csphinxserver.domain.question;

import com.onetuks.csphinxserver.domain.answer.AnswerType;
import java.time.LocalDateTime;
import java.util.Set;

public record Question(
    String questionId,
    String title,
    String description,
    Difficulty difficulty,
    AnswerType answerType,
    Topic topic,
    Set<String> tags,
    LocalDateTime updatedAt,
    int likeCount,
    int attemptCount,
    int solvedCount) {}
