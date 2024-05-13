package at.alphaplan.AlphaWeb.security.token;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.UUID;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public abstract class TokenUtil {
  // Bouncy Castle, Keccack-256

  private static final String PROVIDER_NAME = BouncyCastleProvider.PROVIDER_NAME;
  private static final String HASH_ALGO_NAME = "Keccak-256";
  private static final int TOKEN_LENGTH = 36;

  static {
    if (Security.getProvider(PROVIDER_NAME) == null)
      Security.addProvider(new BouncyCastleProvider());
  }

  public static void generateToken() throws NoSuchAlgorithmException, NoSuchProviderException {
    String tokenId = UUID.randomUUID().toString();
    byte[] hashedTokenId = hashTokenId(tokenId);
  }

  public static void verifyToken() {}

  private static byte[] hashTokenId(String tokenId)
      throws NoSuchAlgorithmException, NoSuchProviderException {

    // use keccak-256 from bouncy castle
    var md = MessageDigest.getInstance(HASH_ALGO_NAME, PROVIDER_NAME);

    // String -> byte[]
    byte[] tokenIdBytes = tokenId.getBytes(StandardCharsets.UTF_8);

    // hash(byte[]) and return
    return md.digest(tokenIdBytes);
  }
}
