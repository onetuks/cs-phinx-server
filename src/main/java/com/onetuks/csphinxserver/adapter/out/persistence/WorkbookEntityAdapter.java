package com.onetuks.csphinxserver.adapter.out.persistence;

import com.onetuks.csphinxserver.adapter.out.persistence.converter.ProblemConverter;
import com.onetuks.csphinxserver.adapter.out.persistence.converter.WorkbookConverter;
import com.onetuks.csphinxserver.adapter.out.persistence.entity.ProblemWorkbookEntity;
import com.onetuks.csphinxserver.adapter.out.persistence.entity.WorkbookEntity;
import com.onetuks.csphinxserver.adapter.out.persistence.repository.ProblemWorkbookJpaRepository;
import com.onetuks.csphinxserver.adapter.out.persistence.repository.WorkbookEntityJpaRepository;
import com.onetuks.csphinxserver.application.port.out.WorkbookPort;
import com.onetuks.csphinxserver.domain.workbook.Workbook;
import com.onetuks.csphinxserver.global.exception.NoSuchEntityException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class WorkbookEntityAdapter implements WorkbookPort {

  private final WorkbookEntityJpaRepository workbookRepository;
  private final ProblemWorkbookJpaRepository problemWorkbookRepository;
  private final WorkbookConverter workbookConverter;
  private final ProblemConverter problemConverter;

  public WorkbookEntityAdapter(
      WorkbookEntityJpaRepository workbookRepository,
      ProblemWorkbookJpaRepository problemWorkbookRepository,
      WorkbookConverter workbookConverter,
      ProblemConverter problemConverter) {
    this.workbookRepository = workbookRepository;
    this.problemWorkbookRepository = problemWorkbookRepository;
    this.workbookConverter = workbookConverter;
    this.problemConverter = problemConverter;
  }

  @Override
  public Workbook create(Workbook workbook) {
    WorkbookEntity workbookEntity = workbookRepository.save(workbookConverter.toEntity(workbook));
    List<ProblemWorkbookEntity> problemWorkbookEntities =
        workbook.includedProblems().stream()
            .map(
                problem ->
                    problemWorkbookRepository.save(
                        new ProblemWorkbookEntity(
                            null, workbookEntity, problemConverter.toEntity(problem))))
            .toList();
    return workbookConverter.toDomain(workbookEntity, problemWorkbookEntities);
  }

  @Override
  public Workbook read(long workbookId) {
    return workbookConverter.toDomain(
        workbookRepository.findById(workbookId).orElseThrow(NoSuchEntityException::new),
        problemWorkbookRepository.findByWorkbookEntityWorkbookId(workbookId));
  }

  @Override
  public Page<Workbook> readAll(Pageable pageable) {
    return workbookRepository.findAll(pageable).map(workbookConverter::toDomain);
  }

  @Override
  public void update(Workbook workbook) {
    WorkbookEntity workbookEntity = workbookRepository.save(workbookConverter.toEntity(workbook));
    workbook
        .includedProblems()
        .forEach(
            problem ->
                problemWorkbookRepository.save(
                    new ProblemWorkbookEntity(
                        null, workbookEntity, problemConverter.toEntity(problem))));
  }

  @Override
  public void delete(long workbookId) {
    workbookRepository.deleteById(workbookId);
  }
}
