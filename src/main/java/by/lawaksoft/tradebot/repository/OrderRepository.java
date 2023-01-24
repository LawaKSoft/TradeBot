package by.lawaksoft.tradebot.repository;

import by.lawaksoft.tradebot.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderIdAndUserId(String orderId, long userId);
}
