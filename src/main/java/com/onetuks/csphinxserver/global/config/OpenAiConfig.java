package com.onetuks.csphinxserver.global.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "openai")
public class OpenAiConfig {

  public static final String MODEL_NAME = "gpt-3.5-turbo";

  private String apiKey;
  private String baseUrl;
}
