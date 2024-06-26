package at.alphaplan.AlphaWeb.presentation;

import at.alphaplan.AlphaWeb.domain.order.Order;
import at.alphaplan.AlphaWeb.service.OrderService;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

  //  @GetMapping("/{Id}")
  //  public ResponseEntity<Order> getOrderById(@PathVariable String Id) {
  //    Order order = orderService.getOrderById(Id);
  //    return ResponseEntity.ok(order);
  //  }

  @GetMapping("/{Id}")
  public ResponseEntity<?> getOrderById(@PathVariable String Id) {
    try {
      Order order = orderService.getOrderById(Id);

      // Wenn die Bestellung nicht gefunden wird
      if (order == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Bestellung mit folgender Id nicht gefunden: " + Id);
      }

      // Erfolgreicher Abruf
      return ResponseEntity.ok(order);

    } catch (IllegalArgumentException e) {
      // Falls die ID ungültig ist oder nicht verarbeitet werden kann
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalides Id-Format: " + Id);

    } catch (RuntimeException e) {
      // Für alle anderen unerwarteten Fehler
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(
              "Ein unerwarteter Fehler beim Aufruf der Datenbank ist aufgetreten. Versuche es später erneut.");
    }
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
