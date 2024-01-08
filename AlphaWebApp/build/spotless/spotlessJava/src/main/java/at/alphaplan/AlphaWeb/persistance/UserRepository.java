package at.alphaplan.AlphaWeb.persistance;

import at.alphaplan.AlphaWeb.domain.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {}
