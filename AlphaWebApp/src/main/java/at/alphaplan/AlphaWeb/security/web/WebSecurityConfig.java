package at.alphaplan.AlphaWeb.security.web;

import at.alphaplan.AlphaWeb.domain.user.Role;
import at.alphaplan.AlphaWeb.persistance.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

  // @Bean
  public UserDetailsService userDetailsService(UserRepository userRepository) {
    return new DaoUserDetailsService(userRepository);
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // AUTHENTICATION
    http.httpBasic(Customizer.withDefaults());
    http.formLogin(AbstractHttpConfigurer::disable);

    // AUTHORIZATION
    http.authorizeHttpRequests(
        (authorize) ->
            authorize
                .requestMatchers("/api/registration/**")
                .permitAll()
                .requestMatchers("/api/media/**")
                .permitAll()
                .requestMatchers("/api/user/**")
                .hasRole(Role.USER.toString())
                .anyRequest()
                .authenticated());

    // CSRF
    http.csrf(AbstractHttpConfigurer::disable);

    // CORS
    http.cors(AbstractHttpConfigurer::disable);

    // SESSIONS
    http.sessionManagement(
        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    DefaultSecurityFilterChain filterChain = http.build();
    return filterChain;
  }
}
