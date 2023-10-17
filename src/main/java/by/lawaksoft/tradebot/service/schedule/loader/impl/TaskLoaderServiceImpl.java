package by.lawaksoft.tradebot.service.schedule.loader.impl;

import by.lawaksoft.tradebot.service.schedule.loader.TaskLoadService;
import by.lawaksoft.tradebot.service.schedule.task.ClientTask;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class TaskLoaderServiceImpl implements TaskLoadService {

	@Override
	public Map<String, ClientTask> loadTasks() {

		return Collections.emptyMap();
	}
}
