package task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TaskRepository {
    private final Map<Long, Task> taskDB = new HashMap<>();
    private Long idCounter = 1L;


    public void save(Task task) {
        if (task.getId() == null) {
            task = new Task(idCounter++, task.getTitle(), task.getDescription());
        }
        taskDB.put(task.getId(), task);
    }

    public Optional<Task> findById(Long id) {

        return Optional.ofNullable(taskDB.get(id));

    }

    public List<Task> findAll() {

        return taskDB.values().stream().toList();
    }

    public void delete(Long id) {

        taskDB.remove(id);
    }

}
