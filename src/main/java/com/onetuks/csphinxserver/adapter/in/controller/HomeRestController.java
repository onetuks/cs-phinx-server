package com.onetuks.csphinxserver.adapter.in.controller;

import com.onetuks.csphinxserver.adapter.in.web.OpenAiGraderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {

  private final OpenAiGraderService openAiGraderService;

  public HomeRestController(OpenAiGraderService openAiGraderService) {
    this.openAiGraderService = openAiGraderService;
  }

  @GetMapping(path = "/")
  public String getServerHealth() {
    return "Hello, this is CSphinx Server!";
  }

  //  @GetMapping(path = "/test")
  //  public ResponseEntity<String> test() {
  //    String chatCompletion = openAiGraderService.getChatCompletion("DB 인덱스에 대해서 설명해줘.");
  //    return ResponseEntity.ok(chatCompletion);
  //  }
}
