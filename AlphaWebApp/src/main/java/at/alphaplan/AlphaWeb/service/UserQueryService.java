package at.alphaplan.AlphaWeb.service;

import at.alphaplan.AlphaWeb.domain.user.User;
import at.alphaplan.AlphaWeb.persistance.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryService {

  private final UserRepository userRepository;

  public User findById(String userId) {
    return userRepository.findById(userId).orElseThrow(() -> ofUserNotFound(userId));
  }

  public void checkEmailNotTaken(String email) {
    if (userRepository.existsByEmail(email)) throw ofEmailTaken(email);
  }

  // own Exceptions-----------------------------------------------------------
  private static RuntimeException ofUserNotFound(String userId) {
    return new IllegalArgumentException("User not found with id " + userId);
  }

  private static RuntimeException ofEmailTaken(String email) {
    return new IllegalArgumentException("Email already taken: " + email);
  }
}
