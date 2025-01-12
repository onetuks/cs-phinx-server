package com.onetuks.csphinxserver.adapter.in.controller;

import com.onetuks.csphinxserver.application.command.ProblemCommand;
import com.onetuks.csphinxserver.application.port.in.ProblemUseCases;
import com.onetuks.csphinxserver.domain.problem.Problem;
import jakarta.validation.Valid;
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
@RequestMapping(path = "/api/problems")
public class ProblemRestController {

  private final ProblemUseCases problemUseCases;

  public ProblemRestController(ProblemUseCases problemUseCases) {
    this.problemUseCases = problemUseCases;
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> postNewProblem(@Valid @RequestBody ProblemCommand command) {
    Problem problem = problemUseCases.addProblem(command);

    return ResponseEntity.created(URI.create("/api/problems/" + problem.problemId())).build();
  }

  @GetMapping(path = "/{problem-id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Problem> getProblem(@PathVariable("problem-id") Long problemId) {
    Problem problem = problemUseCases.searchProblem(problemId);

    return ResponseEntity.ok(problem);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<Problem>> getProblems(@PageableDefault Pageable pageable) {
    Page<Problem> problems = problemUseCases.searchProblems(pageable);

    return ResponseEntity.ok(problems);
  }

  @PatchMapping(path = "/{problem-id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> patchProblem(
      @PathVariable("problem-id") Long problemId, @RequestBody ProblemCommand command) {
    problemUseCases.editProblem(problemId, command);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(path = "/{problem-id}")
  public ResponseEntity<Void> deleteProblem(@PathVariable("problem-id") Long problemId) {
    problemUseCases.removeProblem(problemId);

    return ResponseEntity.noContent().build();
  }
}
