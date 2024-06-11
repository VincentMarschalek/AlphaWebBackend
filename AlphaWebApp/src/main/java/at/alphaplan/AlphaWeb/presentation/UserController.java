package at.alphaplan.AlphaWeb.presentation;

import static at.alphaplan.AlphaWeb.presentation.views.Views.LoginView;

import at.alphaplan.AlphaWeb.security.web.SecurityUser;
import at.alphaplan.AlphaWeb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
  private final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationController.class);
  private final UserService userService;

  @GetMapping
  public LoginView login(@AuthenticationPrincipal SecurityUser principal) {
    LOGGER.debug("User controller#login {}", principal);

    return userService.login(principal.getUser());
  }
}
