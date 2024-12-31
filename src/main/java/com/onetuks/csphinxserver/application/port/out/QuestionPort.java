package com.onetuks.csphinxserver.application.port.out;

import com.onetuks.csphinxserver.domain.question.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionPort {

  Question create(Question question);

  Question read(String questionId);

  Page<Question> readAll(Pageable pageable);

  void update(Question question);

  void delete(String questionId);
}
