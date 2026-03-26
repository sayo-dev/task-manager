package task.service;

import task.Task;
import task.TaskRepository;
import task.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;

public class TaskServiceImpl implements TaskService {

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private final TaskRepository taskRepository;

    @Override
    public String createTask(Task task) {

        taskRepository.save(task);
        return "Task created successfully";
    }

    @Override
    public Task getTask(Long id) {

        return getTaskOrNotFound(id);
    }

    @Override
    public String updateStatus(Long id, TaskStatus status) {
        Task task = getTaskOrNotFound(id);
        if (task.getStatus() == TaskStatus.TODO && status == TaskStatus.DONE) {
            throw new IllegalStateException("Cannot mark task as done.");
        }
        if (task.getStatus() == TaskStatus.DONE) {
            throw new IllegalStateException("Task already completed.");

        }
        task.setStatus(status);
        task.setUpdatedAt(LocalDateTime.now());


        return "Task updated successfully";
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public String deleteTask(Long id) {
        getTaskOrNotFound(id);
        taskRepository.delete(id);

        return "Task deleted successfully";
    }


    private Task getTaskOrNotFound(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found."));
    }

}
