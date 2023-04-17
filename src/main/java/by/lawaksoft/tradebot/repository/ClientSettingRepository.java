package by.lawaksoft.tradebot.repository;

import by.lawaksoft.tradebot.entity.ClientSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientSettingRepository extends JpaRepository<ClientSetting, Long> {

	Optional<ClientSetting> findClientSettingByNameSetting(String name);
}
