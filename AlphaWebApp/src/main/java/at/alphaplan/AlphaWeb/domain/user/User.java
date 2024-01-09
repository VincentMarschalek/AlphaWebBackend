package at.alphaplan.AlphaWeb.domain.user;

import at.alphaplan.AlphaWeb.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import static at.alphaplan.AlphaWeb.foundation.AssertUtil.isValidEmail;
import static at.alphaplan.AlphaWeb.foundation.EntityUtil.generateUUIDv4;

@Getter
@ToString
@Document(collection = "user")
public class User extends BaseEntity<String> {

  @Indexed(unique = true)
  private String email;

  //@JsonIgnore
  private String password;

  private Profile profile;

  private Account account;

  private ShoppingCart shoppingCart;

  private Role role;

  //Cstr for Spring
  protected User(String id) {
    super(id);
  }

  //Cstr for me
  public User(String email,/* Passwort */Profile profile, Account account, ShoppingCart shoppingCart, Role role)
  {
      super(generateUUIDv4());

      this.email = isValidEmail(email,"email");
      //this.password
      this.profile = profile;
      this.role = role;
      this.account = new Account();
      this.shoppingCart = new ShoppingCart();
  }
}
