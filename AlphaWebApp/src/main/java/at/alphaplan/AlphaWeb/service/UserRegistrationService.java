package at.alphaplan.AlphaWeb.service;

import static at.alphaplan.AlphaWeb.presentation.commands.Commands.*;
import static at.alphaplan.AlphaWeb.security.PasswordService.*;

import at.alphaplan.AlphaWeb.domain.user.Role;
import at.alphaplan.AlphaWeb.domain.user.User;
import at.alphaplan.AlphaWeb.email.EmailService;
import at.alphaplan.AlphaWeb.persistance.UserRepository;
import at.alphaplan.AlphaWeb.security.PasswordService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {
  private final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationService.class);

  private final PasswordService passwordService;
  private final EmailService emailService;
  private final UserRepository userRepository;

  public User register(UserRegistrationCommand command) {

    LOGGER.info("User registration with email {}", command.email());

    // 1.Check if email already exists
    if (userRepository.existsByEmail(command.email()))
      throw new RuntimeException("Email already taken! " + command.email());
    // 2.Check password strength/hash password with PasswordService.encode -> Encoded Password
    EncodedPassword encodedPassword = passwordService.encode(command.password());
    // 3.Instantiate a user (account=disabled)
    var user = new User(command.email(), Role.USER, encodedPassword);
    // 4.Send Email
    emailService.sendVerificationEmail(user);
    // 5.Save User
    var savedUser = userRepository.save(user);
    // 6.
    LOGGER.info("User registration with email {} successful", command.email());
    return savedUser;
  }

  public void verify(UserVerificationCommand command) {

    LOGGER.info("User verification with id {} and token {}", command.userId(), command.tokenId());

    // find userbyID, returned optional
    User user =
        userRepository
            .findById(command.userId())
            .orElseThrow(
                () -> new IllegalArgumentException("user not found with id" + command.userId()));
    user.getAccount().verifyToken(command.tokenId());
    user.getAccount().setEnabled(true);
    userRepository.save(user);
    LOGGER.info(
        "Successful user verification with id {} and token {}",
        command.userId(),
        command.tokenId());
  }
}
