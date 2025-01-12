package com.onetuks.csphinxserver.adapter.out.persistence;

import com.onetuks.csphinxserver.adapter.out.persistence.converter.ProblemConverter;
import com.onetuks.csphinxserver.adapter.out.persistence.repository.ProblemEntityJpaRepository;
import com.onetuks.csphinxserver.application.port.out.ProblemPort;
import com.onetuks.csphinxserver.domain.problem.Problem;
import com.onetuks.csphinxserver.global.exception.NoSuchEntityException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ProblemEntityAdapter implements ProblemPort {

  private final ProblemEntityJpaRepository problemRepository;
  private final ProblemConverter problemConverter;

  public ProblemEntityAdapter(
      ProblemEntityJpaRepository problemRepository, ProblemConverter problemConverter) {
    this.problemRepository = problemRepository;
    this.problemConverter = problemConverter;
  }

  @Override
  public Problem create(Problem problem) {
    return problemConverter.toDomain(problemRepository.save(problemConverter.toEntity(problem)));
  }

  @Override
  public Problem read(long problemId) {
    return problemConverter.toDomain(
        problemRepository.findById(problemId).orElseThrow(NoSuchEntityException::new));
  }

  @Override
  public Page<Problem> readAll(Pageable pageable) {
    return problemRepository.findAll(pageable).map(problemConverter::toDomain);
  }

  @Override
  public void update(Problem problem) {
    problemRepository.save(problemConverter.toEntity(problem));
  }

  @Override
  public void delete(long problemId) {
    problemRepository.deleteById(problemId);
  }
}
