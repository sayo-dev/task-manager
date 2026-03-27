import task.Task;
import task.TaskRepository;
import task.TaskStatus;
import task.service.TaskService;
import task.service.TaskServiceImpl;

void main() {

    final String INVALID_OPTION = "INVALID OPTION";

    final TaskRepository taskRepository = new TaskRepository();

    final TaskService taskService = new TaskServiceImpl(taskRepository);

    Scanner scanner = new Scanner(System.in);

    Task[] allTasks = {
            new Task(null, "Go to school", "Going to school"),
            new Task(null, "Go to church", "Going to church"),
            new Task(null, "Go to market", "Going to market"),
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

                    String message = taskService.createTask(new Task(null, title, description));
                    System.out.println(message);
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

                    if (taskService.getTask(id) == null) {
                        return;
                    }

                    TaskStatus status = null;

                    System.out.println("Select Status (I. IN_PROGRESS  D. DONE): ");
                    switch (scanner.next()) {
                        case "I" -> {
                            status = TaskStatus.IN_PROGRESS;
                        }
                        case "D" -> {
                            status = TaskStatus.DONE;
                        }
                        default -> {
                            System.out.println(INVALID_OPTION);
                        }
                    }

                    if (status != null) {
                        String message = taskService.updateStatus(id, status);
                        System.out.println(message);
                    }

                }

                case 5 -> {
                    Long id = getTaskId(scanner);

                    String message = taskService.deleteTask(id);
                    System.out.println(message);
                }
                case 6 -> {
                    System.out.println("GOODBYE!");
                    return;
                }
                default -> System.out.println(INVALID_OPTION);
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
