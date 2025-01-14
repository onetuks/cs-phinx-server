package com.onetuks.csphinxserver.adapter.in.controller;

import com.onetuks.csphinxserver.application.AnswerService;
import com.onetuks.csphinxserver.application.command.AnswerCommand;
import com.onetuks.csphinxserver.application.port.in.AnswerUseCases;
import com.onetuks.csphinxserver.domain.answer.Answer;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/answers")
public class AnswerRestController {

  private final AnswerUseCases answerUseCases;
  private final AnswerService answerService;

  public AnswerRestController(AnswerUseCases answerUseCases, AnswerService answerService) {
    this.answerUseCases = answerUseCases;
    this.answerService = answerService;
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> postNewAnswer(@Valid @RequestBody AnswerCommand command) {
    Answer answer = answerUseCases.addAnswer(command);

    return ResponseEntity.created(URI.create("/api/answers/" + answer.answerId())).build();
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Answer> getAnswer(@RequestParam("problem-id") Long problemId) {
    Answer answer = answerUseCases.searchAnswer(problemId);

    return ResponseEntity.ok(answer);
  }

  @PatchMapping(path = "/{answer-id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> patchAnswer(
      @PathVariable("answer-id") Long answerId, @RequestBody AnswerCommand command) {
    answerUseCases.editAnswer(answerId, command);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(path = "/{answer-id}")
  public ResponseEntity<Void> deleteAnswer(@PathVariable("answer-id") Long answerId) {
    answerService.removeAnswer(answerId);

    return ResponseEntity.noContent().build();
  }
}
