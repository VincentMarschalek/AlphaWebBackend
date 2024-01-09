package at.alphaplan.AlphaWeb.domain.user;

import org.springframework.data.annotation.Id;

public class LineItems {

  @Id private Id productID;
  private int price;
}
