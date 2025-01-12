package com.onetuks.csphinxserver.adapter.out.persistence.repository;

import com.onetuks.csphinxserver.adapter.out.persistence.entity.AnswerEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerEntityJpaRepository extends JpaRepository<AnswerEntity, Long> {

  Optional<AnswerEntity> findByProblemEntityProblemId(Long problemId);
}
