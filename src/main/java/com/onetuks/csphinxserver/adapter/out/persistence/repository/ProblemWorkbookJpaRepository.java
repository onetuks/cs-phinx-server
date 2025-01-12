package com.onetuks.csphinxserver.adapter.out.persistence.repository;

import com.onetuks.csphinxserver.adapter.out.persistence.entity.ProblemWorkbookEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemWorkbookJpaRepository extends JpaRepository<ProblemWorkbookEntity, Long> {

  List<ProblemWorkbookEntity> findByWorkbookEntityWorkbookId(Long problemId);
}
