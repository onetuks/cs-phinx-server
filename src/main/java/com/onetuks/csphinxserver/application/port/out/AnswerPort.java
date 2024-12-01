package com.onetuks.csphinxserver.application.port.out;

import com.onetuks.csphinxserver.domain.answer.Answer;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerPort {

  Answer create(Answer answer);

  List<Answer> read(String questionId);

  void update(Answer answer);

  void delete(String answerId);
}
