package com.onetuks.csphinxserver.application.port.out;

import com.onetuks.csphinxserver.domain.answer.Answer;
import com.onetuks.csphinxserver.domain.answer.AnswerType;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerPort {

  Answer create(Answer answer);

  Answer readByProblemId(long problemId);

  Answer read(long answerId);

  List<Answer> readAll(AnswerType answerType);

  void update(Answer answer);

  void delete(long answerId);
}
