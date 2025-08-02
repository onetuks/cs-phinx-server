package com.onetuks.csphinxserver.application;

import com.onetuks.csphinxserver.application.port.in.GradeUseCases;
import com.onetuks.csphinxserver.application.port.out.AnswerPort;
import com.onetuks.csphinxserver.application.port.out.GradePort;
import com.onetuks.csphinxserver.domain.grader.Grade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GradeService implements GradeUseCases {

  private final AnswerPort answerPort;
  private final GradePort gradePort;

  public GradeService(AnswerPort answerPort, GradePort gradePort) {
    this.answerPort = answerPort;
    this.gradePort = gradePort;
  }

  @Transactional
  public Grade gradeUserAnswer(Long answerId, String userAnswer) {
    return gradePort.readGrade(userAnswer, answerPort.read(answerId).answerValues());
  }
}
