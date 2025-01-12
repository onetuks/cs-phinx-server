package com.onetuks.csphinxserver.adapter.in.web.dto;

import com.onetuks.csphinxserver.adapter.in.web.dto.OpenAiRequest.Message;
import java.util.List;

public record OpenAiResponse(List<Choice> choices) {

  public record Choice(Message message) {}
}
