package at.alphaplan.AlphaWeb.domain.order;

import at.alphaplan.AlphaWeb.domain.BaseEntity;
import at.alphaplan.AlphaWeb.domain.user.User;
import at.alphaplan.AlphaWeb.domain.user.lineItems;
import org.springframework.data.annotation.Id;

public class Order extends BaseEntity<String> {

  @Id private Id orderId;

  private User userID;

  private String status;

  private String address;

  private lineItems lineItems;

  protected Order(String id) {
    super(id);
  }
}
