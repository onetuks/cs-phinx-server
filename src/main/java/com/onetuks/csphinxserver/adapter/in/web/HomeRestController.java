package com.onetuks.csphinxserver.adapter.in.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {

  @GetMapping(path = "/")
  public String getServerHealth() {
    return "Hello, this is CSphinx Server!";
  }
}
