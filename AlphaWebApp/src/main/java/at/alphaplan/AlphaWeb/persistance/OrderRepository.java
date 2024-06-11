package at.alphaplan.AlphaWeb.persistance;

import at.alphaplan.AlphaWeb.domain.order.Order;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

  List<Order> findByUserId(String userId);
}
