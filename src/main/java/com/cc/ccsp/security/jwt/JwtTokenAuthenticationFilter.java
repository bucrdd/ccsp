package com.cc.ccsp.security.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class JwtTokenAuthenticationFilter extends GenericFilter {

  private static final long serialVersionUID = 5153283122069738042L;

  private JwtTokenProvider jwtTokenProvider;

  public JwtTokenAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }


  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {
    String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
    log.info("request with token={}", token);
    if (token == null) {
      SecurityContextHolder.getContext().setAuthentication(null);
    }
    if (jwtTokenProvider.validateToken(token)) {
      Authentication auth = jwtTokenProvider.getAuthentication(token);
      if (auth != null) {
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    }
    filterChain.doFilter(request, response);
  }
}
