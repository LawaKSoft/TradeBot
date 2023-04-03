package by.lawaksoft.tradebot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "algo_types")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlgoType {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Long id;
	private String name;
}
