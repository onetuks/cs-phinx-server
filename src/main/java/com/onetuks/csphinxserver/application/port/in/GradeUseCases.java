package com.onetuks.csphinxserver.application.port.in;

import com.onetuks.csphinxserver.application.command.GradeCommand;
import com.onetuks.csphinxserver.domain.grader.Grade;

public interface GradeUseCases {

  Grade gradeUserAnswer(Long answerId, GradeCommand command);
}
