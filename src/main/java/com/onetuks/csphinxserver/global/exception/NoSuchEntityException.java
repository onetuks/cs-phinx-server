package com.onetuks.csphinxserver.global.exception;

public class NoSuchEntityException extends RuntimeException {

  public NoSuchEntityException() {
    super();
  }

  public NoSuchEntityException(String message) {
    super(message);
  }
}
