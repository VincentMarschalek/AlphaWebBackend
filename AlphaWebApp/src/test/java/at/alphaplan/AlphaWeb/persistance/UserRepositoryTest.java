package at.alphaplan.AlphaWeb.persistance;

import at.alphaplan.AlphaWeb.config.MongoConfig;
import at.alphaplan.AlphaWeb.domain.user.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.annotation.Id;

import static at.alphaplan.AlphaWeb.domain.user.Role.USER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@DataMongoTest
@Import(MongoConfig.class)
public class UserRepositoryTest
{
    @Autowired
    private UserRepository userRepository;
    //saveUser
    @Test
    public void saveUser_shouldWork()
    {
        //Given
        var user = new User("test@spengergasse.at",
                new Profile("Vincent","Mar", new Address("Spengergasse","1","Vienna",1500,"Vienna","Austria")),
                new Account(),
                new ShoppingCart(),
                USER
                );
        //When
        var userSaved = userRepository.save(user);
        //Then
        assertThat(userSaved, notNullValue());
    }
    //findById
    //findByEmail
    //auditFields
    @Test
    public void auditFields_shouldBeSetAutomatically()
    {
        //Given
        var user = new User("test@spengergasse.at",
                new Profile("Vincent","Mar", new Address("Spengergasse","1","Vienna",1500,"Vienna","Austria")),
                new Account(),
                new ShoppingCart(),
                USER
        );

        //When
        var userSaved = userRepository.save(user);

        //Then
        assertThat(userSaved.getCreatedAt(), notNullValue());
        assertThat(userSaved.getLastModifiedAt(), notNullValue());
        assertThat(userSaved.getVersion(), notNullValue());
        assertThat(userSaved.getVersion(), equalTo(0L));
    }


}