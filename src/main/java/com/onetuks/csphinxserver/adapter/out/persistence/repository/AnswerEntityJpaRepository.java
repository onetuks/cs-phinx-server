package com.onetuks.csphinxserver.adapter.out.persistence.repository;

import com.onetuks.csphinxserver.adapter.out.persistence.entity.AnswerEntity;
import com.onetuks.csphinxserver.domain.answer.AnswerType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerEntityJpaRepository extends JpaRepository<AnswerEntity, Long> {

  Optional<AnswerEntity> findByProblemEntityProblemId(Long problemId);

  List<AnswerEntity> findAllByAnswerType(AnswerType answerType);
}
