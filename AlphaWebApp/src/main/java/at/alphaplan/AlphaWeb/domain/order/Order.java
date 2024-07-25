package at.alphaplan.AlphaWeb.domain.order;

import at.alphaplan.AlphaWeb.domain.BaseEntity;
import at.alphaplan.AlphaWeb.domain.media.Media;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.Instant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@ToString(callSuper = true)
@Document(collection = "order")
public class Order extends BaseEntity<String> {

  private String userId;
  private String productId;
  private String address;
  private String billingAddress;
  private Instant orderDate;
  private String message;
  private Media productPic;

  // Setter für den Status
  @Setter private String status;

  // Ctr for Spring
  @PersistenceCreator
  @JsonCreator
  protected Order(String id) {
    super(id);
  }

  @Builder
  public Order(
      String Id,
      String userId,
      String productId,
      String address,
      String billingAddress,
      String message,
      Media productPic,
      Instant orderDate,
      String status) {
    super(Id);
    this.userId = userId;
    this.productId = productId;
    this.address = address;
    this.billingAddress = billingAddress;
    this.productPic = productPic;
    this.message = message;
    this.orderDate = orderDate != null ? orderDate : Instant.now();
    this.status = status;
  }
}
