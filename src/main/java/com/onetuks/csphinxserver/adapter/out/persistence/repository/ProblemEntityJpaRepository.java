package com.onetuks.csphinxserver.adapter.out.persistence.repository;

import com.onetuks.csphinxserver.adapter.out.persistence.entity.ProblemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemEntityJpaRepository extends JpaRepository<ProblemEntity, Long> {}
