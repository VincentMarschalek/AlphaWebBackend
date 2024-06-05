package at.alphaplan.AlphaWeb.security.token;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
public abstract class Token {
  private String encodedValue;
  private Instant createdAt;
  private Instant expiresAt;
}
