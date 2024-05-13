package at.alphaplan.AlphaWeb.domain.user;

import java.util.UUID;

import at.alphaplan.AlphaWeb.foundation.AssertUtil;
import lombok.Getter;
import lombok.Setter;

import static at.alphaplan.AlphaWeb.foundation.AssertUtil.*;

@Getter
public class Account {

  @Setter
  private boolean enabled = false;
  private String tokenId = UUID.randomUUID().toString();

  public void verifyToken(String tokenId)
  {
    isNotNull(this.tokenId, "tokenId");
    ensureTokenMatches(tokenId, this.tokenId, "tokenId_CheckMatch");
  }

}
