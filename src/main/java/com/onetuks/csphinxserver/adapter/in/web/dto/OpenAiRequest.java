package com.onetuks.csphinxserver.adapter.in.web.dto;

import java.util.List;

public record OpenAiRequest(String model, List<Message> messages) {

  public record Message(String role, String content) {}
}
