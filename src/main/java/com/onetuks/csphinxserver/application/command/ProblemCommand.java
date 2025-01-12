package com.onetuks.csphinxserver.application.command;

import com.onetuks.csphinxserver.domain.problem.Difficulty;
import com.onetuks.csphinxserver.domain.problem.Topic;
import java.util.Set;

public record ProblemCommand(
    String title,
    String description,
    Difficulty difficulty,
    Topic topic,
    Set<String> tags,
    Boolean isActive) {}
