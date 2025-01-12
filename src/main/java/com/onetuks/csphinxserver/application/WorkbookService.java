package com.onetuks.csphinxserver.application;

import com.onetuks.csphinxserver.application.command.WorkbookCommand;
import com.onetuks.csphinxserver.application.port.in.WorkbookUseCases;
import com.onetuks.csphinxserver.application.port.out.ProblemPort;
import com.onetuks.csphinxserver.application.port.out.WorkbookPort;
import com.onetuks.csphinxserver.domain.workbook.Workbook;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WorkbookService implements WorkbookUseCases {

  private final WorkbookPort workbookPort;
  private final ProblemPort problemPort;

  public WorkbookService(WorkbookPort workbookPort, ProblemPort problemPort) {
    this.workbookPort = workbookPort;
    this.problemPort = problemPort;
  }

  @Override
  @Transactional
  public Workbook addWorkbook(WorkbookCommand command) {
    return workbookPort.create(
        new Workbook(
            null,
            command.title(),
            command.description(),
            command.collectionType(),
            command.includedProblemIds().stream().map(problemPort::read).toList(),
            command.isActive(),
            LocalDateTime.now()));
  }

  @Override
  @Transactional(readOnly = true)
  public Workbook searchWorkbook(long workbookId) {
    return workbookPort.read(workbookId);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Workbook> searchAllWorkbooks(Pageable pageable) {
    return workbookPort.readAll(pageable);
  }

  @Override
  @Transactional
  public void editWorkbook(long workbookId, WorkbookCommand command) {
    workbookPort.update(
        new Workbook(
            workbookId,
            command.title(),
            command.description(),
            command.collectionType(),
            command.includedProblemIds().stream().map(problemPort::read).toList(),
            command.isActive(),
            LocalDateTime.now()));
  }

  @Override
  public void removeWorkbook(long workbookId) {
    workbookPort.delete(workbookId);
  }
}
