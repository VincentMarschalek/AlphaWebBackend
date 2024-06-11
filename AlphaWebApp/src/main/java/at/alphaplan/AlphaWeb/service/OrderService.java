package at.alphaplan.AlphaWeb.service;

import static at.alphaplan.AlphaWeb.foundation.EntityUtil.generateUUIDv4;

import at.alphaplan.AlphaWeb.domain.order.Order;
import at.alphaplan.AlphaWeb.persistance.OrderRepository;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;

  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  public Order getOrderById(String Id) {
    return orderRepository.findById(Id).orElseThrow(() -> new RuntimeException("Order not found!"));
  }

  public Order createOrder(String userId, String productId) {
    Order order =
        Order.builder()
            .Id(generateUUIDv4())
            .userId(userId)
            .productId(productId)
            .orderDate(Instant.now())
            .status("PENDING")
            .build();
    return orderRepository.save(order);
  }

  public void updateOrderStatus(String Id, String status) {
    Order order = getOrderById(Id);
    order.setStatus(status);
    orderRepository.save(order);
  }

  public void deleteOrder(String Id) {
    if (orderRepository.existsById(Id)) {
      orderRepository.deleteById(Id);
    } else {
      throw new RuntimeException("Order not found!");
    }
  }
}
