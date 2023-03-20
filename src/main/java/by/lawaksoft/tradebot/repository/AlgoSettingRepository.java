package by.lawaksoft.tradebot.repository;

import by.lawaksoft.tradebot.entity.AlgoSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlgoSettingRepository extends JpaRepository<AlgoSetting, Long> {

	Optional<AlgoSetting> findAlgoSettingByNameSetting(String name);
}
