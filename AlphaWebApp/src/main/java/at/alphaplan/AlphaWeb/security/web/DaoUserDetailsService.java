package at.alphaplan.AlphaWeb.security.web;

import at.alphaplan.AlphaWeb.persistance.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DaoUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  // username = email
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository
        .findByEmail(username)
        .map(SecurityUser::new)
        .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
  }
}
