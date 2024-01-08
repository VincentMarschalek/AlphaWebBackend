package at.alphaplan.AlphaWeb.domain.user;

import at.alphaplan.AlphaWeb.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@ToString
@Document(collection = "user")
public class User extends BaseEntity<String> {

  @Indexed(unique = true)
  private String email;

  @Id private ObjectId userId;

  private String username;

  private Address address;

  private Account account;

  private shoppingCart shoppingCart;

  private Role role;

  protected User(String id) {
    super(id);
  }
}
