package com.onetuks.csphinxserver.application.port.in;

import com.onetuks.csphinxserver.application.command.ProblemCommand;
import com.onetuks.csphinxserver.domain.problem.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProblemUseCases {

  Problem addProblem(ProblemCommand command);

  Problem searchProblem(long problemId);

  Page<Problem> searchProblems(Pageable pageable);

  void editProblem(long problemId, ProblemCommand command);

  void removeProblem(long problemId);
}
