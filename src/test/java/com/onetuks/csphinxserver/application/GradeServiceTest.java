package com.onetuks.csphinxserver.application;

import static com.onetuks.csphinxserver.fixture.ProblemFixture.createProblemCommand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.onetuks.csphinxserver.CsPhinxServerApplicationTests;
import com.onetuks.csphinxserver.domain.answer.Answer;
import com.onetuks.csphinxserver.domain.answer.AnswerType;
import com.onetuks.csphinxserver.domain.grader.Grade;
import com.onetuks.csphinxserver.domain.problem.Problem;
import com.onetuks.csphinxserver.fixture.AnswerFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GradeServiceTest extends CsPhinxServerApplicationTests {

  private Answer answer;

  @BeforeEach
  void setUp() {
    Problem problem = problemService.addProblem(createProblemCommand());
    answer =
        answerService.addAnswer(
            AnswerFixture.createAnswerAddCommand(problem.problemId(), AnswerType.SHORT));
  }

  @Test
  @DisplayName("올바른 유저의 응답을 채점한다")
  void gradeUserAnswer() {
    // Given
    String userAnswer = answer.answerValues().getFirst();
    Grade expected = new Grade(userAnswer, answer.answerValues(), 100, "정답입니다");

    given(gradeModuleAdapter.readGrade(userAnswer, answer.answerValues())).willReturn(expected);

    // When
    Grade result = gradeService.gradeUserAnswer(answer.answerId(), userAnswer);

    // Then
    assertThat(result).isNotNull().isEqualTo(expected);
  }
}
