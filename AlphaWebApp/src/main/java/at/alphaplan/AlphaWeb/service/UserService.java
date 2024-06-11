package at.alphaplan.AlphaWeb.service;

import static at.alphaplan.AlphaWeb.presentation.views.Views.LoginView;
import static at.alphaplan.AlphaWeb.presentation.views.Views.UserView;

import at.alphaplan.AlphaWeb.domain.order.Order;
import at.alphaplan.AlphaWeb.domain.user.User;
import at.alphaplan.AlphaWeb.persistance.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
  private final OrderRepository orderRepository;

  public LoginView login(User user) {

    List<Order> orders = orderRepository.findByUserId(user.getId());
    UserView userView = new UserView(user);
    return new LoginView(userView, orders);
  }
}
