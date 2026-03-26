package task.service;

import task.Task;
import task.TaskStatus;

import java.util.List;

public interface TaskService {

    String createTask(Task task);

    Task getTask(Long id);

    String updateStatus(Long id, TaskStatus status);

    List<Task> getAllTasks();

    String deleteTask(Long id);
}
