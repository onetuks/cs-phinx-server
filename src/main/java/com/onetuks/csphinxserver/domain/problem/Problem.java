package com.onetuks.csphinxserver.domain.problem;

import com.onetuks.csphinxserver.global.util.Generated;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public record Problem(
    Long problemId,
    String title,
    String description,
    Difficulty difficulty,
    Topic topic,
    Set<String> tags,
    boolean isActive,
    LocalDateTime updatedAt) {

  @Generated
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Problem problem = (Problem) o;
    return isActive == problem.isActive
        && topic == problem.topic
        && Objects.equals(title, problem.title)
        && Objects.equals(problemId, problem.problemId)
        && Objects.equals(tags, problem.tags)
        && Objects.equals(description, problem.description)
        && difficulty == problem.difficulty;
  }

  @Generated
  @Override
  public int hashCode() {
    int result = Objects.hashCode(problemId);
    result = 31 * result + Objects.hashCode(title);
    result = 31 * result + Objects.hashCode(description);
    result = 31 * result + Objects.hashCode(difficulty);
    result = 31 * result + Objects.hashCode(topic);
    result = 31 * result + Objects.hashCode(tags);
    result = 31 * result + Boolean.hashCode(isActive);
    return result;
  }
}
