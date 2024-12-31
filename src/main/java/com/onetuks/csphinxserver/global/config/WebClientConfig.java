package com.onetuks.csphinxserver.global.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.LoggingCodecSupport;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Slf4j
@Configuration
public class WebClientConfig {

  /**
   * WebClient 설정
   *
   * <p>- HTTP 요청 커넥터: ReactorClientHttpConnector
   *
   * <p>- 데이터 직렬화/역직렬화 전략: exchangeStrategies
   *
   * <p>- 기본 HTTP 헤더: CONTENT_TYPE(media/application-json)
   *
   * <p>- 요청/응답 로깅 필터링
   */
  @Bean
  public WebClient webClient() {
    return WebClient.builder()
        .clientConnector(new ReactorClientHttpConnector(httpClient()))
        .exchangeStrategies(exchangeStrategies())
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .filter(clientRequestLoggingCodecConfigurer())
        .filter(clientResponseLoggingCodecConfigurer())
        .build();
  }

  /**
   * Netty 기반 HTTP 클라이언트 설정
   *
   * <p>- 비동기 HTTP 통신 처리
   *
   * <p>- 연결 타임아웃: 30초
   *
   * <p>- 응답 타임아웃: 60초
   *
   * <p>- 읽기/쓰기 타임아웃: 30초
   */
  @Bean
  public HttpClient httpClient() {
    return HttpClient.create(connectionProvider())
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1_000 * 30)
        .responseTimeout(Duration.ofSeconds(60))
        .doOnConnected(
            connection ->
                connection
                    .addHandlerLast(new ReadTimeoutHandler(30_000_000, TimeUnit.SECONDS))
                    .addHandlerLast(new WriteTimeoutHandler(30_000_000, TimeUnit.SECONDS)));
  }

  /**
   * 데이터 직렬화/역직렬화 전략 설정
   *
   * <p>- 메모리 최대크기: 50MB
   *
   * <p>- 요청/응답 로깅 지원
   */
  @Bean
  public ExchangeStrategies exchangeStrategies() {
    ExchangeStrategies exchangeStrategies =
        ExchangeStrategies.builder()
            .codecs(
                clientCodecConfigurer ->
                    clientCodecConfigurer.defaultCodecs().maxInMemorySize(1024 * 1024 * 50))
            .build();

    exchangeStrategies.messageWriters().stream()
        .filter(LoggingCodecSupport.class::isInstance)
        .forEach(
            httpMessageWriter ->
                ((LoggingCodecSupport) httpMessageWriter).setEnableLoggingRequestDetails(true));

    return exchangeStrategies;
  }

  /**
   * HTTP 커넥션 풀 관리 설정
   *
   * <p>
   *
   * <p>- 최대 연결 수: 5,000
   *
   * <p>- 대기시간: 최대 연결시간 45초
   *
   * <p>- 유휴시간: 8초
   *
   * <p>- 유지시간: 58초
   *
   * <p>- 연결전략: LIFO
   */
  @Bean
  public ConnectionProvider connectionProvider() {
    return ConnectionProvider.builder("http-pool")
        .maxConnections(5_000)
        .pendingAcquireTimeout(Duration.ofMillis(45_000))
        .pendingAcquireMaxCount(100)
        .maxIdleTime(Duration.ofMillis(8_000L))
        .maxLifeTime(Duration.ofMillis(58_000L))
        .lifo()
        .build();
  }

  @Bean
  public ExchangeFilterFunction clientRequestLoggingCodecConfigurer() {
    return ExchangeFilterFunction.ofRequestProcessor(
        clientRequest -> {
          log.debug("Request: {} {}", clientRequest.method(), clientRequest.url());
          clientRequest
              .headers()
              .forEach(
                  (name, values) -> values.forEach(value -> log.debug("{} : {}", name, value)));
          return Mono.just(clientRequest);
        });
  }

  @Bean
  public ExchangeFilterFunction clientResponseLoggingCodecConfigurer() {
    return ExchangeFilterFunction.ofResponseProcessor(
        clientResponse -> {
          clientResponse
              .headers()
              .asHttpHeaders()
              .forEach(
                  (name, values) -> values.forEach(value -> log.debug("{} : {}", name, value)));
          return Mono.just(clientResponse);
        });
  }
}
