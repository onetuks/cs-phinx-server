package com.onetuks.csphinxserver.application.command.question;

import com.onetuks.csphinxserver.domain.question.Category;
import com.onetuks.csphinxserver.domain.question.Difficulty;
import com.onetuks.csphinxserver.domain.question.Topic;
import java.util.Set;

public record QuestionPostCommand(
   String title,
   String description,
   Difficulty difficulty,
   Integer timeLimit,
   Category category,
   Topic topic,
   Set<String> tags
) {}
