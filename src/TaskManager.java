import task.Task;
import task.TaskRepository;
import task.TaskStatus;
import task.service.TaskService;
import task.service.TaskServiceImpl;

void main() {

    final TaskRepository taskRepository = new TaskRepository();

    final TaskService taskService = new TaskServiceImpl(taskRepository);

    Scanner scanner = new Scanner(System.in);

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

    while (true) {

        System.out.println("===WELCOME TO TASK MANAGER===");
        System.out.println("1. Create Task\n2. All Tasks\n3. Get Task\n4. Update Task\n5. Delete Task\n6. Exit");
        System.out.println("Choose option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter title: ");
                    String title = scanner.nextLine();

                    System.out.println("Enter description: ");
                    String description = scanner.nextLine();

                    taskService.createTask(new Task(null, title, description));
                    System.out.println("Task saved successfully");
                }

                case 2 -> {
                    List<Task> tasks = taskService.getAllTasks();
                    tasks.forEach(System.out::println);
                }

                case 3 -> {
                    Long id = getTaskId(scanner);

                    Task task = taskService.getTask(id);
                    System.out.println(task);
                }

                case 4 -> {
                    Long id = getTaskId(scanner);

                    System.out.println("Status (IN_PROGRESS, DONE): ");
                    TaskStatus status = TaskStatus.valueOf(scanner.next());

                    taskService.updateStatus(id, status);
                }

                case 5 -> {
                    Long id = getTaskId(scanner);

                    taskService.deleteTask(id);
                }
                case 6 -> {
                    System.out.println("GOODBYE!");
                    return;
                }
                default -> System.out.println("INVALID OPTION");
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

    }

}

private Long getTaskId(Scanner scanner) {
    System.out.println("Enter task ID: ");

    return scanner.nextLong();
}
