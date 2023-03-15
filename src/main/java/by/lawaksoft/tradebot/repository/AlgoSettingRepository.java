package by.lawaksoft.tradebot.repository;

import by.lawaksoft.tradebot.entity.AlgoSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlgoSettingRepository extends JpaRepository<AlgoSetting, Long> {

	AlgoSetting findAlgoSettingByNameSetting(String name);
}
