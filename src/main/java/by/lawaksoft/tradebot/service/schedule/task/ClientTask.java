package by.lawaksoft.tradebot.service.schedule.task;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.CompletableFuture;

@Getter
@Setter
public abstract class ClientTask implements Runnable{

	private CompletableFuture<Void> future;
}
