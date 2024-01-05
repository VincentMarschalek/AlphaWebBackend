package at.alphaplan.AlphaWeb.persistance;

import com.example.todo.domain.Todo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// stellt viele Aktionen out of the box zur Verfügung, create, delete, findBy usw.
@Repository
public interface TodoRepository extends MongoRepository<Todo, ObjectId> {

  public List<Todo> findByTitleContaining(String text);

  public List<Todo> findByTitleAndCompleted(String text, boolean completed);
}
