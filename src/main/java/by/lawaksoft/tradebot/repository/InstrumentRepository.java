package by.lawaksoft.tradebot.repository;

import by.lawaksoft.tradebot.entity.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {

	Optional<Instrument> findByInstrumentId(String instrumentId);
}
