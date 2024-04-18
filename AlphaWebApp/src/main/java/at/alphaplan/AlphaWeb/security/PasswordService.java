package at.alphaplan.AlphaWeb.security;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

// outer class
public class PasswordService {


  public static final int ZXCVBN_STRENGHT_THRESHOLD = 3;
  private final Zxcvbn zxcvbn;
  private final PasswordEncoder passwordEncoder;

  public PasswordService(PasswordEncoder passwordEncoder) {
    this.zxcvbn = new Zxcvbn();
    this.passwordEncoder = passwordEncoder;
  }

  public EncodedPassword encoded(String rawPassword) {
    // 1.password strenght assessment
    Strength measure = zxcvbn.measure(rawPassword);
    System.out.println("Score:" + measure.getScore());
    if (measure.getScore() < ZXCVBN_STRENGHT_THRESHOLD) {
      throw new IllegalArgumentException("Password is weak! Score:" + measure.getScore());
    }

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
