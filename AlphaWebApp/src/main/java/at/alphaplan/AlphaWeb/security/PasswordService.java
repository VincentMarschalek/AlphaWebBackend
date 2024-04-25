package at.alphaplan.AlphaWeb.security;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// outer class
@Service
@RequiredArgsConstructor
public class PasswordService {

  public static final int ZXCVBN_STRENGHT_THRESHOLD = 3;
  private final Zxcvbn zxcvbn = new Zxcvbn();
  private final PasswordEncoder passwordEncoder;
  public EncodedPassword encode(String rawPassword) {
    // 1.password strenght assessment
    Strength measure = zxcvbn.measure(rawPassword);
    System.out.println("Score:" + measure.getScore());
    if (measure.getScore() < ZXCVBN_STRENGHT_THRESHOLD)
      throw new IllegalArgumentException("Password is weak! Score:" + measure.getScore());

    // 2.password hashing
    String hashedValue = passwordEncoder.encode(rawPassword);

    // 3.return custom type
    return new EncodedPassword(hashedValue);
  }

  // inner class
  // static or non-static
  public static class EncodedPassword {
    private final String hashedValue;

    // Constructor
    public EncodedPassword(String hashedValue) {
      this.hashedValue = hashedValue;
    }

    public String getHashedValue() {
      return hashedValue;
    }
  }
}
