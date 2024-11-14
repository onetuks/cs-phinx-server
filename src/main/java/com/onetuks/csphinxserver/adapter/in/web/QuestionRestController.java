package com.onetuks.csphinxserver.adapter.in.web;

import com.onetuks.csphinxserver.adapter.in.dto.Questions;
import com.onetuks.csphinxserver.application.command.question.QuestionPatchCommand;
import com.onetuks.csphinxserver.application.command.question.QuestionPostCommand;
import com.onetuks.csphinxserver.application.port.in.QuestionUseCases;
import com.onetuks.csphinxserver.domain.question.Question;
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
@RequestMapping(path = "/api/questions")
public class QuestionRestController {

  private final QuestionUseCases questionUseCases;

  public QuestionRestController(QuestionUseCases questionUseCases) {
    this.questionUseCases = questionUseCases;
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> postNewQuestion(@Valid @RequestBody QuestionPostCommand command) {
    String questionId = questionUseCases.addQuestion(command);

    return ResponseEntity.created(URI.create("/api/questions/" + questionId)).build();
  }

  @GetMapping(path = "/{question-id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Question> getQuestion(@PathVariable("question-id") String questionId) {
    Question question = questionUseCases.searchQuestion(questionId);

    return ResponseEntity.ok(question);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Questions> getQuestions(@PageableDefault Pageable pageable) {
    Page<Question> questions = questionUseCases.searchQuestions(pageable);

    return ResponseEntity.ok(new Questions(questions));
  }

  @PatchMapping(path = "/{question-id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> patchQuestion(
      @PathVariable("question-id") String questionId, @RequestBody QuestionPatchCommand command) {
    questionUseCases.editQuestion(questionId, command);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(path = "/{question-id}")
  public ResponseEntity<Void> deleteQuestion(@PathVariable("question-id") String questionId) {
    questionUseCases.removeQuestion(questionId);

    return ResponseEntity.noContent().build();
  }
}
