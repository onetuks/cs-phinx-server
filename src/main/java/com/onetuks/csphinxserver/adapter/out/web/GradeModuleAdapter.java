package com.onetuks.csphinxserver.adapter.out.web;

import com.onetuks.csphinxserver.application.port.out.GradePort;
import com.onetuks.csphinxserver.domain.grader.Grade;
import com.onetuks.csphinxserver.global.config.GradeConfig;
import com.onetuks.csphinxserver.global.util.URIBuilder;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GradeModuleAdapter implements GradePort {

  private final GradeConfig gradeConfig;
  private final WebClient webClient;
  private final URIBuilder uriBuilder;

  public GradeModuleAdapter(GradeConfig gradeConfig, WebClient webClient, URIBuilder uriBuilder) {
    this.gradeConfig = gradeConfig;
    this.webClient = webClient;
    this.uriBuilder = uriBuilder;
  }

  @Override
  public Grade readGrade(String userAnswer, List<String> desirableAnswers) {
    return Objects.requireNonNull(
        webClient
            .put()
            .uri(uriBuilder.buildUri(gradeConfig.getBaseUrl()))
            .bodyValue(Grade.of(userAnswer, desirableAnswers))
            .retrieve()
            .bodyToMono(Grade.class)
            .block());
  }
}
