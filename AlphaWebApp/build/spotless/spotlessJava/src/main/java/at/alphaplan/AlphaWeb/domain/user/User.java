package at.alphaplan.AlphaWeb.domain.user;

import static at.alphaplan.AlphaWeb.foundation.AssertUtil.isValidEmail;
import static at.alphaplan.AlphaWeb.foundation.EntityUtil.generateUUIDv4;
import static at.alphaplan.AlphaWeb.security.PasswordService.*;

import at.alphaplan.AlphaWeb.domain.BaseEntity;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@ToString
@Document(collection = "user")
public class User extends BaseEntity<String> {

  @Indexed(unique = true)
  private String email;

  // @JsonIgnore
  private String password;

  private Profile profile;

  private Account account;

  private ShoppingCart shoppingCart;

  private Role role;

  // Ctr for Spring
  @PersistenceCreator
  protected User(String id) {
    super(id);
  }

  // Ctr for me
  public User(String email, Role role, EncodedPassword EncodedPassword) {

    super(generateUUIDv4());

    this.email = isValidEmail(email, "email");
    this.password = EncodedPassword.getHashedValue();
    this.role = role;
  }
}
