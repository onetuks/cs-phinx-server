package com.onetuks.csphinxserver;

import com.onetuks.csphinxserver.application.AnswerService;
import com.onetuks.csphinxserver.application.ProblemService;
import com.onetuks.csphinxserver.application.WorkbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
public class CsPhinxServerApplicationTests {

  @Autowired protected ProblemService problemService;
  @Autowired protected AnswerService answerService;
  @Autowired protected WorkbookService workbookService;
}
