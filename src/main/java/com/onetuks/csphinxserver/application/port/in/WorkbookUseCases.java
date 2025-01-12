package com.onetuks.csphinxserver.application.port.in;

import com.onetuks.csphinxserver.application.command.WorkbookCommand;
import com.onetuks.csphinxserver.domain.workbook.Workbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WorkbookUseCases {

  Workbook addWorkbook(WorkbookCommand command);

  Workbook searchWorkbook(long workbookId);

  Page<Workbook> searchAllWorkbooks(Pageable pageable);

  void editWorkbook(long workbookId, WorkbookCommand command);

  void removeWorkbook(long workbookId);
}
