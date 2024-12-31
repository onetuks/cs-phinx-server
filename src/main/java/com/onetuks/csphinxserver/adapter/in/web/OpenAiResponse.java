package com.onetuks.csphinxserver.adapter.in.web;

import com.onetuks.csphinxserver.adapter.in.web.OpenAiRequest.Message;
import java.util.List;

public record OpenAiResponse(List<Choice> choices) {

  public record Choice(Message message) {}
}
