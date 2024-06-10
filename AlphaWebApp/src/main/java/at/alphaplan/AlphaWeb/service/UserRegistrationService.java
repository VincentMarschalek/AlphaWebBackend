package at.alphaplan.AlphaWeb.service;

import static at.alphaplan.AlphaWeb.domain.user.Role.USER;
import static at.alphaplan.AlphaWeb.presentation.commands.Commands.UserRegistrationCommand;
import static at.alphaplan.AlphaWeb.presentation.commands.Commands.UserVerificationCommand;
import static at.alphaplan.AlphaWeb.security.password.PasswordService.EncodedPassword;

import at.alphaplan.AlphaWeb.domain.user.Profile;
import at.alphaplan.AlphaWeb.domain.user.User;
import at.alphaplan.AlphaWeb.email.EmailService;
import at.alphaplan.AlphaWeb.persistance.UserRepository;
import at.alphaplan.AlphaWeb.security.password.PasswordService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {
  private final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationService.class);
  private final UserQueryService userQueryService;
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
    var user = createUser(command, encodedPassword);
    user.getAccount().generateEmailTokenFor(command.email());

    // 4.Send Email
    emailService.sendVerificationEmail(user);

    // 5.Save User
    var savedUser = userRepository.save(user);

    // 6.
    LOGGER.info("User registration with email {} successful", command.email());
    return savedUser;
  }

  public void verify(UserVerificationCommand command) {
    LOGGER.info("User account verification with Id {}", command.userId());

    User user = userQueryService.findById(command.userId());

    verifyUser(command, user);

    userRepository.save(user);

    LOGGER.info("User verification with id {} successful", command.userId());
  }

  private User createUser(UserRegistrationCommand command, EncodedPassword password) {
    var profile = new Profile(command.firstName(), command.lastName());
    var user = new User(command.email(), USER, password, profile);
    user.getAccount().generateEmailTokenFor(command.email());
    return user;
  }

  private void verifyUser(UserVerificationCommand command, User user) {
    user.getAccount().verifyEmailTokenFor(command.tokenId());
    user.getAccount().setEnabled(true);
  }
}
