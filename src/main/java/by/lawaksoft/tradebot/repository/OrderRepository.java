package by.lawaksoft.tradebot.repository;

import by.lawaksoft.tradebot.entity.Order;
import by.lawaksoft.tradebot.entity.enums.NecessarySynchronization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderIdAndUserId(String orderId, long userId);

    @Query("select o from orders o where o.user.id = ?1 and o.status = 'ACTIVE'")
    Optional<List<Order>> findAllByUserIdAndStatusActive(long userId);

    @Query("select o from orders o where o.user.id = ?1 and o.instrumentId = ?2 and o.status = 'ACTIVE'")
    Optional<List<Order>> findAllByUserIdAndInstrumentAndStatus(Long userId, String instrument);

    @Query("select o " +
            "from orders o " +
            "where o.user.id = ?1 and " +
            "   o.necessarySynchronization = 'ACCEPT' or " +
            "   o.necessarySynchronization = 'UPDATED' or " +
            "   o.necessarySynchronization = 'CLOSED'")
    Optional<List<Order>> findAllByAlgoInstanceIdAndNecessarySynchronizationIn(long algoId);

    @Modifying
    @Query(value = "update orders o set o.necessarySynchronization = :nesSync where o.instrumentId = :instrumentId")
    void updateNecessarySynchronizationByInstrumentId(String nesSync, String instrumentId);
}
