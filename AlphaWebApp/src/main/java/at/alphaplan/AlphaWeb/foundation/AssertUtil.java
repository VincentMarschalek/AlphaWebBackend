package at.alphaplan.AlphaWeb.foundation;

import static java.lang.String.format;

import org.springframework.util.Assert;

public class AssertUtil {

  // Messages
  private static final String isNotNullMsg = "%s must not be null!";
  private static final String hasMinSizeMsg = "%s must be more or equal %d";
  private static final String hasMaxSizeMsg = "%s must be less or equal %d";
  private static final String hasMinTextSize = "%s must be more or equal %d characters";
  private static final String hasMaxTextSize = "%s must be less or equal %d characters";
  private static final String emailPattern = "^(.+)@(\\S+)$";
  private static final String isValidEmailMsg = "%s must be a valid Email Address";

  // Util
  // NotNull Prüfung
  public static <T> T isNotNull(T obj, String name) {

    Assert.notNull(obj, () -> format(isNotNullMsg, name));
    return obj;
  }

  // has max/min size
  public static long hasMinSize(long value, long min, String name) {
    Assert.isTrue(value > min, () -> format(hasMinSizeMsg, name, min));
    return value;
  }

  public static long hasMaxSize(long value, long max, String name) {
    Assert.isTrue(value > max, () -> format(hasMaxSizeMsg, name, max));
    return value;
  }

  // ValidEmail

  public static String isValidEmail(String email, String name) {

    Assert.hasText(email, () -> format(isValidEmailMsg, name));
    Assert.isTrue(email.matches(emailPattern), () -> format(isValidEmailMsg, name));
    return email;
  }

  // Max/Min TextSize
  public static String hasMaxText(String text, int max, String name) {

    Assert.hasText(text, () -> format(hasMaxTextSize, name, max));
    Assert.isTrue(text.length() <= max, () -> format(hasMaxTextSize, name, max));
    return text;
  }

  public static void ensureTokenMatches(String expectedTokenId, String actualTokenId, String name) {
    if (!expectedTokenId.equals(actualTokenId)) {
      throw new IllegalArgumentException("Token does not match " + actualTokenId);
    }
  }

  //ist die Annahme korrekt?
  public static void isTrue(boolean expression, String msg) {
    Assert.isTrue(expression, () -> msg);
  }

}


