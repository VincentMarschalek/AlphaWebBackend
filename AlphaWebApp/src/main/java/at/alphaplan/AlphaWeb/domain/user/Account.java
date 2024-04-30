package at.alphaplan.AlphaWeb.domain.user;

import java.util.UUID;
import lombok.Getter;

@Getter
public class Account {

  private boolean enabled = true;
  private String tokenId = UUID.randomUUID().toString();
}
