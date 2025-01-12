package com.onetuks.csphinxserver.adapter.in.web;

import com.onetuks.csphinxserver.adapter.in.web.dto.OpenAiRequest;
import com.onetuks.csphinxserver.adapter.in.web.dto.OpenAiRequest.Message;
import com.onetuks.csphinxserver.adapter.in.web.dto.OpenAiResponse;
import com.onetuks.csphinxserver.global.config.OpenAiConfig;
import com.onetuks.csphinxserver.global.util.URIBuilder;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class OpenAiGraderService {

  private final OpenAiConfig openAiConfig;
  private final WebClient webClient;
  private final URIBuilder uriBuilder;

  public OpenAiGraderService(
      OpenAiConfig openAiConfig, WebClient webClient, URIBuilder uriBuilder) {
    this.openAiConfig = openAiConfig;
    this.webClient = webClient;
    this.uriBuilder = uriBuilder;
  }

  public String getChatCompletion(String userInput) {
    OpenAiRequest request =
        new OpenAiRequest(
            OpenAiConfig.MODEL_NAME,
            List.of(
                new Message("system", "당신은 Computer Science 지식을 채점하는 채점 선생님입니다."),
                new Message("user", userInput)));

    OpenAiResponse response =
        webClient
            .post()
            .uri(uriBuilder.buildUri(openAiConfig.getBaseUrl()))
            .header("Authorization", "Bearer " + openAiConfig.getApiKey())
            .bodyValue(request)
            .retrieve()
            .bodyToMono(OpenAiResponse.class)
            .block();

    return Objects.requireNonNull(response).choices().getFirst().message().content();
  }
}
