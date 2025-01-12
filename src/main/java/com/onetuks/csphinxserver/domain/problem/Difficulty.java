package com.onetuks.csphinxserver.domain.problem;

import lombok.Getter;

@Getter
public enum Difficulty {
  EASY(10), // 쉬움
  MEDIUM(20), // 보통
  HARD(40) // 어려움
;

  private final int score;

  Difficulty(int score) {
    this.score = score;
  }
}
