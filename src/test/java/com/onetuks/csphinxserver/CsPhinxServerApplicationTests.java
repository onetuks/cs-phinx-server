package com.onetuks.csphinxserver;

import com.onetuks.csphinxserver.adapter.out.persistence.repository.AnswerEntityJpaRepository;
import com.onetuks.csphinxserver.adapter.out.persistence.repository.ProblemEntityJpaRepository;
import com.onetuks.csphinxserver.application.AnswerService;
import com.onetuks.csphinxserver.application.ProblemService;
import com.onetuks.csphinxserver.application.WorkbookService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
public class CsPhinxServerApplicationTests {

  @Autowired protected ProblemService problemService;
  @Autowired protected AnswerService answerService;
  @Autowired protected WorkbookService workbookService;

  @PersistenceContext protected EntityManager entityManager;
  @Autowired protected ProblemEntityJpaRepository problemRepository;
  @Autowired protected AnswerEntityJpaRepository answerRepository;
}
