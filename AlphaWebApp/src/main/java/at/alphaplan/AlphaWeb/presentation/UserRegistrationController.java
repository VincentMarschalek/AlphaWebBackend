package at.alphaplan.AlphaWeb.presentation;

import at.alphaplan.AlphaWeb.domain.user.User;
import at.alphaplan.AlphaWeb.presentation.commands.Commands;
import at.alphaplan.AlphaWeb.service.UserRegistrationService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/registration")
public class UserRegistrationController {
  // GET/POST/PUT/DELETE
  // Postman, curl
  // register(..)

  private final UserRegistrationService userRegistrationService;

  @PostMapping
  public ResponseEntity<User> register(@RequestBody Commands.UserRegistrationCommand command) {

    var registeredUser = userRegistrationService.register(command);

    String locationUri = "/api/user/" + registeredUser.getId();
    URI uri = URI.create(locationUri);

    ResponseEntity<User> userResponse = ResponseEntity.created(uri).body(registeredUser);

    return userResponse;
  }
}
