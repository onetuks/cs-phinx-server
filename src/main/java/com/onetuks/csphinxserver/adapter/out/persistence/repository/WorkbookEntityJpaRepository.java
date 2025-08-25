package com.onetuks.csphinxserver.adapter.out.persistence.repository;

import com.onetuks.csphinxserver.adapter.out.persistence.entity.WorkbookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkbookEntityJpaRepository extends JpaRepository<WorkbookEntity, Long> {

  Page<WorkbookEntity> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
