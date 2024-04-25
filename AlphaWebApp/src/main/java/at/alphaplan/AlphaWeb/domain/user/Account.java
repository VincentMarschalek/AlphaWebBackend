package at.alphaplan.AlphaWeb.domain.user;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Account {

  private boolean enabled = false;
  private String tokenId = UUID.randomUUID().toString();
}
