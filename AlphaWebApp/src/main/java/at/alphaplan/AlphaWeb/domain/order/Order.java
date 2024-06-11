package at.alphaplan.AlphaWeb.domain.order;

import at.alphaplan.AlphaWeb.domain.BaseEntity;
import java.time.Instant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@ToString(callSuper = true)
@Document(collection = "order")
public class Order extends BaseEntity<String> {

  private final String userId;
  private final String productId;
  private final Instant orderDate;
  // Setter für den Status
  @Setter private String status;

  @Builder
  public Order(String Id, String userId, String productId, Instant orderDate, String status) {
    super(Id);
    this.userId = userId;
    this.productId = productId;
    this.orderDate = orderDate != null ? orderDate : Instant.now();
    this.status = status;
  }
}
