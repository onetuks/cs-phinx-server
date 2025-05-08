package com.onetuks.csphinxserver.application;

import com.onetuks.csphinxserver.application.command.ProblemCommand;
import com.onetuks.csphinxserver.application.port.in.ProblemUseCases;
import com.onetuks.csphinxserver.application.port.out.AnswerPort;
import com.onetuks.csphinxserver.application.port.out.ProblemPort;
import com.onetuks.csphinxserver.domain.answer.AnswerType;
import com.onetuks.csphinxserver.domain.problem.Problem;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProblemService implements ProblemUseCases {

  private final ProblemPort problemPort;
  private final AnswerPort answerPort;

  public ProblemService(ProblemPort problemPort, AnswerPort answerPort) {
    this.problemPort = problemPort;
    this.answerPort = answerPort;
  }

  @Override
  @Transactional
  public Problem addProblem(ProblemCommand command) {
    return problemPort.create(
        new Problem(
            null,
            command.title(),
            command.description(),
            command.difficulty(),
            command.topic(),
            command.tags(),
            command.isActive(),
            LocalDateTime.now()));
  }

  @Override
  @Transactional(readOnly = true)
  public Problem searchProblem(long problemId) {
    return problemPort.read(problemId);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Problem> searchProblems(AnswerType answerType, Pageable pageable) {
    return new PageImpl<>(
        answerType == null
            ? problemPort.readAll(pageable).toList()
            : answerPort.readAll(answerType).stream()
                .map(answer -> searchProblem(answer.problem().problemId()))
                .toList());
  }

  @Override
  @Transactional
  public void editProblem(long problemId, ProblemCommand command) {
    problemPort.update(
        new Problem(
            problemId,
            command.title(),
            command.description(),
            command.difficulty(),
            command.topic(),
            command.tags(),
            command.isActive(),
            LocalDateTime.now()));
  }

  @Override
  @Transactional
  public void removeProblem(long problemId) {
    problemPort.delete(problemId);
  }
}
