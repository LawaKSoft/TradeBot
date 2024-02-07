package by.lawaksoft.tradebot.repository;

import by.lawaksoft.tradebot.entity.AlgoInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlgoInstanceRepository extends JpaRepository<AlgoInstance, Long> {

    @Query("select al from algo_instances al where users.id = ?1 and instruments.instrumentId = ?2")
    Optional<List<AlgoInstance>> findAllByUserIdAndInstrumentId(long userId, String instrumentId);
}
