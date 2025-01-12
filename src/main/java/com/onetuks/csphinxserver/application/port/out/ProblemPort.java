package com.onetuks.csphinxserver.application.port.out;

import com.onetuks.csphinxserver.domain.problem.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemPort {

  Problem create(Problem problem);

  Problem read(long problemId);

  Page<Problem> readAll(Pageable pageable);

  void update(Problem problem);

  void delete(long problemId);
}
