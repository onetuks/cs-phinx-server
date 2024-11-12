package com.onetuks.csphinxserver.domain.question;

import java.time.LocalDateTime;
import java.util.Set;

public record Question(
    String questionId,
    String title,
    String description,
    Difficulty difficulty,
    TimeLimit timeLimit,
    Category category,
    Topic topic,
    Set<String> tags,
    LocalDateTime updatedAt,
    int likeCount,
    int attemptCount,
    int solvedCount
) {}
