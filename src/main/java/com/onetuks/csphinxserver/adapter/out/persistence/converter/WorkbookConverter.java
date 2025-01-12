package com.onetuks.csphinxserver.adapter.out.persistence.converter;

import com.onetuks.csphinxserver.adapter.out.persistence.entity.ProblemWorkbookEntity;
import com.onetuks.csphinxserver.adapter.out.persistence.entity.WorkbookEntity;
import com.onetuks.csphinxserver.domain.workbook.Workbook;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class WorkbookConverter {

  private final ProblemConverter problemConverter;

  public WorkbookConverter(ProblemConverter problemConverter) {
    this.problemConverter = problemConverter;
  }

  public WorkbookEntity toEntity(Workbook domain) {
    return new WorkbookEntity(
        domain.workbookId(),
        domain.title(),
        domain.description(),
        domain.collectionType(),
        domain.isActive());
  }

  public Workbook toDomain(WorkbookEntity entity) {
    return new Workbook(
        entity.getWorkbookId(),
        entity.getTitle(),
        entity.getDescription(),
        entity.getCollectionType(),
        List.of(),
        entity.getIsActive(),
        entity.getUpdatedAt());
  }

  public Workbook toDomain(WorkbookEntity entity, List<ProblemWorkbookEntity> mappedEntities) {
    return new Workbook(
        entity.getWorkbookId(),
        entity.getTitle(),
        entity.getDescription(),
        entity.getCollectionType(),
        mappedEntities.stream()
            .map(ProblemWorkbookEntity::getProblemEntity)
            .map(problemConverter::toDomain)
            .toList(),
        entity.getIsActive(),
        entity.getUpdatedAt());
  }
}
