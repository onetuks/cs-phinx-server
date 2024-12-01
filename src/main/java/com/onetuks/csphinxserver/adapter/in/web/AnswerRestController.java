package com.onetuks.csphinxserver.adapter.in.web;

import com.onetuks.csphinxserver.adapter.in.dto.Answers;
import com.onetuks.csphinxserver.application.AnswerService;
import com.onetuks.csphinxserver.application.command.answer.ChoiceAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.ChoiceAnswerEditCommand;
import com.onetuks.csphinxserver.application.command.answer.DescriptiveAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.DescriptiveAnswerEditCommand;
import com.onetuks.csphinxserver.application.command.answer.ShortAnswerAddCommand;
import com.onetuks.csphinxserver.application.command.answer.ShortAnswerEditCommand;
import com.onetuks.csphinxserver.application.port.in.AnswerUseCases;
import com.onetuks.csphinxserver.domain.answer.Answer;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
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

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Answers> getAnswers(@RequestParam("question-id") String questionId) {
    List<Answer> answers = answerUseCases.searchAnswers(questionId);

    return ResponseEntity.ok(new Answers(answers));
  }

  @PatchMapping(path = "/choices/{answer-id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> patchChoiceAnswer(
      @PathVariable("answer-id") String answerId, @RequestBody ChoiceAnswerEditCommand command) {
    answerUseCases.editChoiceAnswer(answerId, command);

    return ResponseEntity.noContent().build();
  }

  @PatchMapping(path = "/shorts/{answer-id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> patchShortAnswer(
      @PathVariable("answer-id") String answerId, @RequestBody ShortAnswerEditCommand command) {
    answerUseCases.editShortAnswer(answerId, command);

    return ResponseEntity.noContent().build();
  }

  @PatchMapping(path = "/descriptions/{answer-id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> patchDescriptiveAnswer(
      @PathVariable("answer-id") String answerId, @RequestBody DescriptiveAnswerEditCommand command
  ) {
    answerService.editDescriptiveAnswer(answerId, command);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(path = "/{answer-id}")
  public ResponseEntity<Void> deleteAnswer(@PathVariable("answer-id") String answerId) {
    answerService.removeAnswer(answerId);

    return ResponseEntity.noContent().build();
  }
}
