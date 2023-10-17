package by.lawaksoft.tradebot.service.schedule.impl;

import by.lawaksoft.tradebot.service.schedule.SchedulerManager;
import by.lawaksoft.tradebot.service.schedule.loader.TaskLoadService;
import by.lawaksoft.tradebot.service.schedule.task.ClientTask;
import by.lawaksoft.tradebot.service.schedule.task.SystemTask;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

//@Component
public class SchedulerManagerImpl implements SchedulerManager {

	private final Logger logger = LoggerFactory.getLogger(SchedulerManagerImpl.class);
	private final Map<String, ClientTask> clientTasks = new HashMap<>();
	private final Map<String, SystemTask> systemTasks;
	private final TaskLoadService taskLoadService;

	@Autowired
	public SchedulerManagerImpl(Map<String, SystemTask> systemTasks, TaskLoadService taskLoadService) {

		this.systemTasks = systemTasks;
		this.taskLoadService = taskLoadService;
	}

	@PostConstruct
	public void init() {

		runSystemTasks();
	}

	@PreDestroy
	public void destroy() {

		stopAllTasks();
	}

	@Override
	public void addClientTask(String name, ClientTask task) {

		CompletableFuture<Void> future = CompletableFuture.runAsync(task);
		task.setFuture(future);
		synchronized (clientTasks) {
			clientTasks.put(name, task);
		}
		logRunTaskMessage(name);
	}

	@Override
	public void removeTask(String name, ClientTask task) {

		task.getFuture().cancel(true);
		synchronized (clientTasks) {
			clientTasks.remove(name);
		}
		logStopTaskMessage(name);
	}

	@Override
	public void stopAllTasks() {

		systemTasks.forEach((name, task) -> {
			task.getFuture().cancel(true);
			logStopTaskMessage(name);
		});
		clientTasks.forEach(this::removeTask);
	}

	@Override
	public void restartAllTasks() {

		synchronized (clientTasks) {
			stopAllTasks();
			runSystemTasks();
			runClientTasks();
		}
	}

	@Override
	public void displayTasksStatus() {

		systemTasks.forEach((name, task) -> logStatusTask(name, task.getFuture()));
		clientTasks.forEach((name, task) -> logStatusTask(name, task.getFuture()));
	}

	private void runClientTasks() {

		Map<String, ClientTask> stringClientTaskMap = taskLoadService.loadTasks();
		stringClientTaskMap.forEach(this::addClientTask);
	}

	private void runSystemTasks() {

		systemTasks.forEach((name, task) -> {
			CompletableFuture<Void> future = CompletableFuture.runAsync(task);
			task.setFuture(future);
			logRunTaskMessage(name);
		});
	}

	private void logMessage(String pattern, String name) {

		logger.info(pattern, name);
	}

	private void logStopTaskMessage(String name) {

		logMessage("Task {0} is stopping", name);
	}

	private void logRunTaskMessage(String name) {

		logMessage("Task {0} is running", name);
	}

	private void logStatusTask(String name, CompletableFuture<Void> future) {

		String message;
		if (future.isDone()) {
			message = MessageFormat.format("Task {} is done" , name);
		} else if (future.isCancelled()) {
			message = MessageFormat.format("Task {} is cancelled" , name);
		} else if (future.isCompletedExceptionally()) {
			message = MessageFormat.format("Task {} is completed exceptionally" , name);
		} else {
			message = MessageFormat.format("Task {} is working" , name);
		}
		logger.info(message);
	}
}
