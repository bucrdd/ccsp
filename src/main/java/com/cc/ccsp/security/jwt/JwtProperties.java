package com.cc.ccsp.security.jwt;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(value = "jwt")
@Data
public class JwtProperties {

  private String secretKey = "secret";

  private long validityInMs = 3600000; // 1h
}
