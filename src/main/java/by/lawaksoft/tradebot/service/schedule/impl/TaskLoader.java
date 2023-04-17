package by.lawaksoft.tradebot.service.schedule.impl;

import by.lawaksoft.tradebot.service.schedule.loader.TaskLoadService;
import by.lawaksoft.tradebot.service.schedule.task.ClientTask;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TaskLoader implements TaskLoadService {
    @Override
    public Map<String, ClientTask> loadTasks() {
        return null;
    }
}
