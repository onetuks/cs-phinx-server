package com.onetuks.csphinxserver;

import org.springframework.boot.SpringApplication;

public class TestCsPhinxApplication {

  public static void main(String[] args) {
    SpringApplication.from(CsPhinxServerApplication::main)
        .with(TestcontainersConfiguration.class)
        .run(args);
  }
}
