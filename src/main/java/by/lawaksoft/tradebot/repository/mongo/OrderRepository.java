package by.lawaksoft.tradebot.repository.mongo;

import by.lawaksoft.tradebot.document.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    Optional<Order> findByOrderIdAndUserId(String orderId, long userId);
}
