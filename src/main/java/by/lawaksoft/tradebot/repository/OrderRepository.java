package by.lawaksoft.tradebot.repository;

import by.lawaksoft.tradebot.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderIdAndUserId(String orderId, long userId);

    @Query("select o from orders o where o.user.id = ?1 and o.status = 'ACTIVE'")
    Optional<List<Order>> findAllByUserIdAndStatusActive(long userId);
}
