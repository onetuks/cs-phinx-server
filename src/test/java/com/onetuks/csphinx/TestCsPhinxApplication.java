package com.onetuks.csphinx;

import org.springframework.boot.SpringApplication;

public class TestCsPhinxApplication {

  public static void main(String[] args) {
    SpringApplication.from(CsPhinxApplication::main).with(TestcontainersConfiguration.class)
        .run(args);
  }

}
