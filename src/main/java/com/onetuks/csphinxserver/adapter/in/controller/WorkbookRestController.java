package com.onetuks.csphinxserver.adapter.in.controller;

import com.onetuks.csphinxserver.application.command.WorkbookCommand;
import com.onetuks.csphinxserver.application.port.in.WorkbookUseCases;
import com.onetuks.csphinxserver.domain.workbook.Workbook;
import java.net.URI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/workbooks")
public class WorkbookRestController {

  private final WorkbookUseCases workbookUseCases;

  public WorkbookRestController(WorkbookUseCases workbookUseCases) {
    this.workbookUseCases = workbookUseCases;
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Workbook> postNewWorkbook(@RequestBody WorkbookCommand command) {
    Workbook workbook = workbookUseCases.addWorkbook(command);

    return ResponseEntity.created(URI.create("/api/workbooks/" + workbook.workbookId())).build();
  }

  @GetMapping(path = "/{workbook-id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Workbook> getWorkbook(@PathVariable("workbook-id") Long workbookId) {
    Workbook workbook = workbookUseCases.searchWorkbook(workbookId);

    return ResponseEntity.ok(workbook);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<Workbook>> getWorkbooks(@PageableDefault Pageable pageable) {
    Page<Workbook> workbooks = workbookUseCases.searchAllWorkbooks(pageable);

    return ResponseEntity.ok(workbooks);
  }

  @PatchMapping(path = "/{workbook-id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> patchWorkbook(
      @PathVariable("workbook-id") Long workbookId, @RequestBody WorkbookCommand command) {
    workbookUseCases.editWorkbook(workbookId, command);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(path = "/{workbook-id}")
  public ResponseEntity<Void> deleteWorkbook(@PathVariable("workbook-id") Long workbookId) {
    workbookUseCases.removeWorkbook(workbookId);

    return ResponseEntity.noContent().build();
  }
}
