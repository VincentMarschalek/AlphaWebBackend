package at.alphaplan.AlphaWeb.domain.user;

import static at.alphaplan.AlphaWeb.foundation.AssertUtil.isValidEmail;
import static at.alphaplan.AlphaWeb.foundation.EntityUtil.generateUUIDv4;
import static at.alphaplan.AlphaWeb.security.PasswordService.*;

import at.alphaplan.AlphaWeb.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
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
  @JsonCreator
  protected User(String id) {
    super(id);
  }

  // Ctr for me
  public User(String email, Role role, EncodedPassword encodedPassword) {

    super(generateUUIDv4());
    this.account = new Account();
    this.email = isValidEmail(email, "email");
   this.password = encodedPassword.getHashedValue();
    this.role = role;
  }
}
