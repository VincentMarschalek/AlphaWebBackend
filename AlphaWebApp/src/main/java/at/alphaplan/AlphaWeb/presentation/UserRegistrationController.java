package at.alphaplan.AlphaWeb.presentation;

import static at.alphaplan.AlphaWeb.presentation.commands.Commands.UserRegistrationCommand;
import static at.alphaplan.AlphaWeb.presentation.commands.Commands.UserVerificationCommand;

import at.alphaplan.AlphaWeb.domain.user.User;
import at.alphaplan.AlphaWeb.service.UserRegistrationService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/registration")
public class UserRegistrationController {
  // GET/POST/PUT/DELETE
  // Postman, curl
  // register(..)
  private final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationController.class);

  private final UserRegistrationService userRegistrationService;

  @PostMapping
  public ResponseEntity<User> register(@RequestBody UserRegistrationCommand command) {
    //    LOGGER.info("user registration controller");

    var registeredUser = userRegistrationService.register(command);

    URI uri = URI.create("/api/user/" + registeredUser.getId());

    return ResponseEntity.created(uri).body(registeredUser);
  }

  @GetMapping("/verify")
  public void verify(@ModelAttribute UserVerificationCommand command) {
    LOGGER.debug("User registration controller#verify {}", command);
    userRegistrationService.verify(command);
  }
}
