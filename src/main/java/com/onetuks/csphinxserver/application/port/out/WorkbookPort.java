package com.onetuks.csphinxserver.application.port.out;

import com.onetuks.csphinxserver.domain.workbook.Workbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkbookPort {

  Workbook create(Workbook workbook);

  Workbook read(long workbookId);

  Page<Workbook> readAll(Pageable pageable);

  void update(Workbook workbook);

  void delete(long workbookId);
}
