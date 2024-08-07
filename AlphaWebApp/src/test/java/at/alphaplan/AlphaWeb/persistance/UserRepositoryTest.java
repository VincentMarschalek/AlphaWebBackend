package at.alphaplan.AlphaWeb.persistance;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import at.alphaplan.AlphaWeb.config.MongoConfig;
import at.alphaplan.AlphaWeb.domain.user.User;
import at.alphaplan.AlphaWeb.fixture.UserFixture;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.OptimisticLockingFailureException;

@DataMongoTest
@Import(MongoConfig.class)
public class UserRepositoryTest {
  @Autowired private UserRepository userRepository;
  private User userSaved;

  @BeforeEach
  public void setup() {
    var user = UserFixture.createUser();
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
    var duplicatedUser = UserFixture.createUser();
    // WHEN & THEN
    assertThrows(DuplicateKeyException.class, () -> userRepository.save(duplicatedUser));
  }

  @Test
  public void saveUser_shouldFail_whenIdIsNotUpdated() {
    // When
    var userRead1 = userRepository.findById(userSaved.getId()).get();
    var userRead2 = userRepository.findById(userSaved.getId()).get();

    // When
    userRepository.save(userRead1);
    // Then
    assertThrows(OptimisticLockingFailureException.class, () -> userRepository.save(userRead2));
  }
}
