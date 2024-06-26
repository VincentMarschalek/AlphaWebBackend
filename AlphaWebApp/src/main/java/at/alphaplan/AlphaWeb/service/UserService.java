package at.alphaplan.AlphaWeb.service;

import static at.alphaplan.AlphaWeb.presentation.views.Views.LoginView;
import static at.alphaplan.AlphaWeb.presentation.views.Views.UserView;

import at.alphaplan.AlphaWeb.domain.order.Order;
import at.alphaplan.AlphaWeb.domain.product.Product;
import at.alphaplan.AlphaWeb.domain.user.User;
import at.alphaplan.AlphaWeb.persistance.OrderRepository;
import at.alphaplan.AlphaWeb.persistance.ProductRepository;
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
  private final ProductRepository productRepository;

  public LoginView login(User user) {

    LOGGER.debug("logging in user {}", user.getEmail());

    List<Order> orders = orderRepository.findByUserId(user.getId());
    List<Product> products = productRepository.findAll();
    UserView userView = new UserView(user);

    LOGGER.info("logging in user {} successful", user.getEmail());
    return new LoginView(userView, products, orders);
  }
}
