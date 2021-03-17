package com.cc.ccsp.security.jwt;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {

  /**
   * Constructs an {@code AuthenticationException} with the specified message and no root cause.
   *
   * @param msg the detail message
   */
  public InvalidJwtAuthenticationException(String msg) {
    super(msg);
  }
}
