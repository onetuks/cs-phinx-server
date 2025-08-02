package com.onetuks.csphinxserver.adapter.in.controller;

import com.onetuks.csphinxserver.application.port.in.GradeUseCases;
import com.onetuks.csphinxserver.domain.grader.Grade;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class GradeRestController {

  private final GradeUseCases gradeUseCases;

  public GradeRestController(GradeUseCases gradeUseCases) {
    this.gradeUseCases = gradeUseCases;
  }

  @PutMapping(path = "/answers/{answer-id}/grade", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Grade> putUserAnswer(
      @PathVariable("answer-id") Long answerId, @RequestBody String userAnswer) {
    Grade grade = gradeUseCases.gradeUserAnswer(answerId, userAnswer);

    return ResponseEntity.ok(grade);
  }
}
