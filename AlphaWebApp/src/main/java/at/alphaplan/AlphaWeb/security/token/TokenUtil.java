package at.alphaplan.AlphaWeb.security.token;

import static at.alphaplan.AlphaWeb.foundation.AssertUtil.isTrue;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public abstract class TokenUtil {
  // Bouncy Castle, Keccack-256

  private static final String PROVIDER_NAME = BouncyCastleProvider.PROVIDER_NAME;
  private static final String HASH_ALGO_NAME = "Keccak-256";
  private static final int TOKEN_LENGTH = 36;

  // Static initializer block ------------------------------------------------
  static {
    if (Security.getProvider(PROVIDER_NAME) == null)
      Security.addProvider(new BouncyCastleProvider());
  }

  // DTO------------------------------------------------------------------------
  public record GenerateTokenValues(String tokenId, String hashedTokenIdBase64) {}

  // Generate Token-------------------------------------------------------------
  protected static GenerateTokenValues generateToken() {
    // Generate 122-bit cryptographic random value as UUIDv4
    String tokenId = UUID.randomUUID().toString();

    // hash the tokenId with keccak-256
    byte[] hashedTokenId = hashTokenId(tokenId);

    // Convert the hashed tokenId to a base64 String
    String hashedTokenIdBase64 = Base64.getEncoder().encodeToString(hashedTokenId);

    // return the tokenId and its hashed tokenId
    return new GenerateTokenValues(tokenId, hashedTokenIdBase64);
  }

  // -------------------------VerifyToken-------------------------------------------------------
  public static void verifyToken(Token token, String tokenId) {
    // is the Token nonexpired?
    isTrue(isTokenNonExpired(token), "token is expired");

    // Do the hashes match?
    isTrue(areHashesEqual(token, tokenId), "token do not match");
  }

  // Überprüfung für verify()
  public static boolean areHashesEqual(Token token, String tokenId) {
    // hash tokenId with keccak-256
    byte[] hashedTokenId = hashTokenId(tokenId);

    // decode hashed tokenId in the token from Base64
    byte[] hashedTokenIdSaved = Base64.getDecoder().decode(token.getEncodedValue());

    // Check for equality
    return MessageDigest.isEqual(hashedTokenId, hashedTokenIdSaved);
  }

  public static boolean isTokenNonExpired(Token token) {
    return Instant.now().isBefore(token.getExpiresAt());
  }

  // ----------------------------HashTokenId-----------------------------------------------------

  private static byte[] hashTokenId(String tokenId) {
    try {
      // use keccak-256 from bouncy castle
      var md = MessageDigest.getInstance(HASH_ALGO_NAME, PROVIDER_NAME);

      // String -> byte[]
      byte[] tokenIdBytes = tokenId.getBytes(StandardCharsets.UTF_8);

      // hash(byte[]) and return
      byte[] hashedTokenId = md.digest(tokenIdBytes);

      return hashedTokenId;
    } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
      throw new RuntimeException("Hashing toking failed", e);
    }
  }
}
