package by.lawaksoft.tradebot.service.schedule.loader;

import by.lawaksoft.tradebot.service.schedule.task.ClientTask;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface TaskLoadService {

	Map<String, ClientTask> loadTasks();
}
