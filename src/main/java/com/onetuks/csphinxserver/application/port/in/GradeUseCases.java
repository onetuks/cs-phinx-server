package com.onetuks.csphinxserver.application.port.in;

import com.onetuks.csphinxserver.domain.grader.Grade;

public interface GradeUseCases {

  Grade gradeUserAnswer(Long answerId, String userAnswer);
}
