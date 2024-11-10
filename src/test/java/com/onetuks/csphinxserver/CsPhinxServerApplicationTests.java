package com.onetuks.csphinxserver;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class CsPhinxServerApplicationTests {

  @Test
  void contextLoads() {
    int a = 1;
    int b = 2;

    assertThat(a + b).isEqualTo(3);
  }
}
