package by.lawaksoft.tradebot.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "algo-settings")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlgoSetting {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Long id;
	private String nameSetting;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_algo_type")
	private AlgoType algoType;
}
