package at.alphaplan.AlphaWeb.presentation;

import at.alphaplan.AlphaWeb.domain.order.Order;
import at.alphaplan.AlphaWeb.service.OrderService;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
  private final OrderService orderService;

  @GetMapping
  public ResponseEntity<List<Order>> getAllOrders() {
    List<Order> orders = orderService.getAllOrders();
    return ResponseEntity.ok(orders);
  }

  @GetMapping("/{Id}")
  public ResponseEntity<Order> getOrderById(@PathVariable String Id) {
    Order order = orderService.getOrderById(Id);
    return ResponseEntity.ok(order);
  }

  @PostMapping
  public ResponseEntity<Order> createOrder(
      @RequestParam String userId, @RequestParam String productId) {
    Order createdOrder = orderService.createOrder(userId, productId);
    URI uri = URI.create("/api/orders/" + createdOrder.getId());
    return ResponseEntity.created(uri).body(createdOrder);
  }

  @PutMapping("/{Id}")
  public ResponseEntity<Void> deleteOrder(@PathVariable String Id) {
    orderService.deleteOrder(Id);
    return ResponseEntity.noContent().build();
  }
}
