package by.lawaksoft.tradebot.service.schedule.task;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Getter
@Setter
public abstract class SystemTask implements Runnable {

	private CompletableFuture<Void> future;
}
