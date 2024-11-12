package com.onetuks.csphinxserver.adapter.in.web;

import com.onetuks.csphinxserver.application.command.QuestionAddCommand;
import com.onetuks.csphinxserver.application.port.in.QuestionUseCase;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/questions")
public class QuestionRestController {

  private final QuestionUseCase questionUseCase;

  public QuestionRestController(QuestionUseCase questionUseCase) {
    this.questionUseCase = questionUseCase;
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> createQuestion(@Valid @RequestBody QuestionAddCommand command) {
    String questionId = questionUseCase.addQuestion(command);

    return ResponseEntity.created(URI.create("/api/questions/" + questionId)).build();
  }
}
