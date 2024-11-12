package com.onetuks.csphinxserver.application.port.out;

import com.onetuks.csphinxserver.domain.question.Question;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionPort {

  Question create(Question question);

  Question read(String questionId);

  void update(Question question);

  void delete(String questionId);
}
