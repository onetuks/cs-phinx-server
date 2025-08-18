package com.onetuks.csphinxserver.application.port.out;

import com.onetuks.csphinxserver.domain.grader.Grade;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface GradePort {

  Grade readGrade(String userAnswer, List<String> desirableAnswers);
}
