package com.onetuks.csphinxserver;

import org.springframework.boot.test.context.TestConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

  //  @Bean
  //  @ServiceConnection
  //  MongoDBContainer mongoDbContainer() {
  //    return new MongoDBContainer(DockerImageName.parse("mongo:latest"));
  //  }

  @Container
  MySQLContainer<?> mySQLContainer =
      new MySQLContainer(DockerImageName.parse("mysql:latest"))
          .withDatabaseName("csphinx")
          .withUsername("test")
          .withPassword("test");

  //  @Bean
  //  @ServiceConnection
  //  MySQLContainer<?> mysqlContainer() {
  //    return new MySQLContainer<>(DockerImageName.parse("mysql:latest"))
  //        .withDatabaseName("csphinx")
  //        .withUsername("myroot")
  //        .withPassword("secret");
  //  }
}
