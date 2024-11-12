package com.onetuks.csphinxserver.domain.answer;

import java.time.LocalDateTime;

public interface Answer {

  String answerId();
  String questionId();
  LocalDateTime updatedAt();
}
