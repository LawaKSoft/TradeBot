package by.lawaksoft.tradebot.entity;

import lombok.*;

import jakarta.persistence.*;

import java.util.Map;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private Map<String, Balance> balanceMap;
}
