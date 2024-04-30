package at.alphaplan.AlphaWeb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

//  @Bean
//  public PasswordService passwordService(PasswordEncoder passwordEncoder) {
//    return new PasswordService(passwordEncoder);
//  }
}
