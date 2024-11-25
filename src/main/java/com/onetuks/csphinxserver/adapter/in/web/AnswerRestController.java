package com.onetuks.csphinxserver.adapter.in.web;

import com.onetuks.csphinxserver.application.command.answer.ChoiceAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.DescriptiveAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.ShortAnswerAddCommand;
import com.onetuks.csphinxserver.application.port.in.AnswerUseCases;
import com.onetuks.csphinxserver.domain.answer.Answer;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/answers")
public class AnswerRestController {

  private final AnswerUseCases answerUseCases;

  public AnswerRestController(AnswerUseCases answerUseCases) {
    this.answerUseCases = answerUseCases;
  }

  @PostMapping(
      path = "/choices",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> postNewChoiceAnswer(@Valid @RequestBody ChoiceAnswerAddCommand command) {
    String answerId = answerUseCases.addChoiceAnswer(command);

    return ResponseEntity.created(URI.create("/api/answers/choices/" + answerId)).build();
  }

  @PostMapping(
      path = "/shorts",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> postNewShortAnswer(@Valid @RequestBody ShortAnswerAddCommand command) {
    String answerId = answerUseCases.addShortAnswer(command);

    return ResponseEntity.created(URI.create("/api/answers/shorts/" + answerId)).build();
  }

  @PostMapping(
      path = "/descriptions",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> postNewDescriptiveAnswer(@Valid @RequestBody DescriptiveAnswerAddCommand command) {
    String answerId = answerUseCases.addDescriptiveAnswer(command);

    return ResponseEntity.created(URI.create("/api/answers/descriptions/" + answerId)).build();
  }

  @GetMapping(path = "/choices/{answer-id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Answer> getChoiceAnswer(@PathVariable("answer-id") String answerId) {
    Answer answer = answerUseCases.searchChoiceAnswer(answerId);

    return ResponseEntity.ok(answer);
  }

  @GetMapping(path = "/shorts/{answer-id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Answer> getShortAnswer(@PathVariable("answer-id") String answerId) {
    Answer answer = answerUseCases.searchShortAnswer(answerId);

    return ResponseEntity.ok(answer);
  }

  @GetMapping(path = "/descriptions/{answer-id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Answer> getDescriptiveAnswer(@PathVariable("answer-id") String answerId) {
    Answer answer = answerUseCases.searchDescriptiveAnswer(answerId);

    return ResponseEntity.ok(answer);
  }
}
