package com.onetuks.csphinxserver.application.port.out;

import com.onetuks.csphinxserver.domain.answer.Answer;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerPort {

  Answer create(Answer answer);

  Answer read(long problemId);

  void update(Answer answer);

  void delete(long answerId);
}
