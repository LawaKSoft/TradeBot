package by.lawaksoft.tradebot.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity(name = "algo-instances")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlgoInstance {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	private User user;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_instrument")
	private Instrument instrument;

	@OneToMany(mappedBy= "algoInstance",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<AlgoParam> parameters;
}
