package by.lawaksoft.tradebot.service.schedule;

import by.lawaksoft.tradebot.service.schedule.task.ClientTask;
import org.springframework.stereotype.Component;

@Component
public interface SchedulerManager {

	void addClientTask(String name, ClientTask task);

	void removeTask(String name, ClientTask task);

	void stopAllTasks();

	void restartAllTasks();

	void displayTasksStatus();
}
