package com.onetuks.csphinxserver.adapter.out.persistence.converter;

import com.onetuks.csphinxserver.adapter.out.persistence.entity.ProblemEntity;
import com.onetuks.csphinxserver.domain.problem.Problem;
import org.springframework.stereotype.Component;

@Component
public class ProblemConverter {

  public ProblemEntity toEntity(Problem domain) {
    return new ProblemEntity(
        domain.problemId(),
        domain.title(),
        domain.description(),
        domain.difficulty(),
        domain.topic(),
        domain.tags(),
        domain.isActive());
  }

  public Problem toDomain(ProblemEntity entity) {
    return new Problem(
        entity.getProblemId(),
        entity.getTitle(),
        entity.getDescription(),
        entity.getDifficulty(),
        entity.getTopic(),
        entity.getTags(),
        entity.getIsActive(),
        entity.getUpdatedAt());
  }
}
