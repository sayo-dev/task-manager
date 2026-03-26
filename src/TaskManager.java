import task.Task;
import task.TaskRepository;
import task.TaskStatus;
import task.service.TaskService;
import task.service.TaskServiceImpl;

void main() {

    final TaskRepository taskRepository = new TaskRepository();

    final TaskService taskService = new TaskServiceImpl(taskRepository);

    Task[] allTasks = {
            new Task(null, "Go to school", "Going to school"),
            new Task(null, "Go to church", "Going to school"),
            new Task(null, "Go to market", "Going to school"),
            new Task(null, "Sleep", "Going to school"),
            new Task(null, "fight", "Going to school"),
            new Task(null, "work", "Going to school"),
            new Task(null, "read", "Going to school"),
            new Task(null, "eat", "Going to school"),
            new Task(null, "bounce", "Going to school"),
            new Task(null, "play", "Going to school")
    };

    for (Task tsk : allTasks) {
        taskService.createTask(tsk);
    }




}
