package at.alphaplan.AlphaWeb.security.token;

import java.time.Instant;

public abstract class Token
{
    private String encodedValue;
    private Instant createdAt;
    private Instant expiresAt;

}
