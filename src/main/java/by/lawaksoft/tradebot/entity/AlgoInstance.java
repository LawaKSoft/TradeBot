package by.lawaksoft.tradebot.entity;

import jakarta.persistence.*;
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

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private AlgoType algoType;
}
