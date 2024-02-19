package at.alphaplan.AlphaWeb.persistance;

import static at.alphaplan.AlphaWeb.domain.user.Role.USER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import at.alphaplan.AlphaWeb.config.MongoConfig;
import at.alphaplan.AlphaWeb.domain.user.*;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DuplicateKeyException;

@DataMongoTest
@Import(MongoConfig.class)
public class UserRepositoryTest {
  public static final String MAIL = "test@spengergasse.at";
  @Autowired private UserRepository userRepository;
  private User userSaved;

  @BeforeEach
  public void setup() {
    var user =
        new User(
            MAIL,
            new Profile(
                "Vincent",
                "Mar",
                new Address("Spengergasse", "1", "Vienna", 1500, "Vienna", "Austria")),
            new Account(),
            new ShoppingCart(),
            USER);
    userRepository.deleteAll();
    userSaved = userRepository.save(user);
  }

  // saveUser----------------------------------------------------------------------------
  @Test
  public void saveUser_shouldWork() {
    assertThat(userSaved, notNullValue());
  }

  // findByEmail--------------------------------------------------------------------------
  @Test
  public void findByEmail_shouldReturnUser_whenUserExists() {
    // When
    Optional<User> userFound = userRepository.findByEmail(userSaved.getEmail());

    // Then
    assertThat(userFound.isPresent(), equalTo(true));
    assertThat(userFound.get().getEmail(), equalTo(userSaved.getEmail()));
  }

  // findByID-------------------------------------------------------------------------------
  @Test
  public void findById_shouldReturnUser_whenUserExists() {
    var userFound = userRepository.findById(userSaved.getId());
    assertThat(userFound.isPresent(), equalTo(true));
    assertThat(userFound.get().getId(), equalTo(userSaved.getId()));
  }

  // auditFields------------------------------------------------------------------------------
  @Test
  public void auditFields_shouldBeSetAutomatically() {
    // Then
    assertThat(userSaved.getCreatedAt(), notNullValue());
    assertThat(userSaved.getLastModifiedAt(), notNullValue());
    assertThat(userSaved.getVersion(), notNullValue());
    assertThat(userSaved.getVersion(), equalTo(0L));
  }

  // ------------------------------------------------------------------------------------------
  @Test
  public void saveUser_shouldFail_withDuplicateEmail() {
    // GIVEN
    var profile =
        new Profile(
            "Vincent",
            "Mar",
            new Address("Spengergasse", "1", "Vienna", 1500, "Vienna", "Austria"));
    var duplicatedUser = new User(MAIL, profile, new Account(), new ShoppingCart(), USER);
    // WHEN
    assertThrows(DuplicateKeyException.class, () -> userRepository.save(duplicatedUser));
  }
}
