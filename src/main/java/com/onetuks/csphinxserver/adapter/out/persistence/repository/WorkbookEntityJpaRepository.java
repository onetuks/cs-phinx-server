package com.onetuks.csphinxserver.adapter.out.persistence.repository;

import com.onetuks.csphinxserver.adapter.out.persistence.entity.WorkbookEntity;
import com.onetuks.csphinxserver.domain.workbook.CollectionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkbookEntityJpaRepository extends JpaRepository<WorkbookEntity, Long> {

  Page<WorkbookEntity> findAllByCollectionType(CollectionType collectionType, Pageable pageable);

  Page<WorkbookEntity> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);

  Page<WorkbookEntity> findAllByTitleContainingIgnoreCaseAndCollectionType(
      String title, CollectionType collectionType, Pageable pageable);
}
