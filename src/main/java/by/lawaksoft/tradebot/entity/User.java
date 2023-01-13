package by.lawaksoft.tradebot.entity;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.cglib.core.GeneratorStrategy;
import org.yaml.snakeyaml.tokens.ScalarToken;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}


