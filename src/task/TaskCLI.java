package task;

import task.service.TaskService;
import task.service.TaskServiceImpl;
import util.Logger;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TaskCLI {

    final TaskRepository taskRepository = new TaskRepository();
    final TaskService taskService = new TaskServiceImpl(taskRepository);

    final Scanner scanner = new Scanner(System.in);

    public void start() {
        final String INVALID_OPTION = "INVALID OPTION";

        while (true) {
            Logger.header("WELCOME TO TASK MANAGER");
            Logger.info("\n1. Create Task\n2. All Tasks\n3. Get Task\n4. Update Task\n5. Delete Task\n6. Exit");
            Logger.prompt("Choose option:");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        Logger.prompt("Enter title:");
                        String title = scanner.nextLine();

                        Logger.prompt("Enter description:");
                        String description = scanner.nextLine();

                        String message = taskService.createTask(new Task(null, title, description));
                        Logger.info(message);
                    }

                    case 2 -> {
                        List<Task> tasks = taskService.getAllTasks();
                        tasks.forEach(task -> Logger.info(task.toString()));
                    }

                    case 3 -> {
                        Long id = getTaskId();

                        Task task = taskService.getTask(id);
                        if (task != null) {
                            Logger.info(task.toString());
                        } else {
                            Logger.warn("Task not found!");
                        }
                    }

                    case 4 -> {
                        Long id = getTaskId();

                        if (taskService.getTask(id) == null) {
                            Logger.warn("Task not found!");
                            continue;
                        }

                        TaskStatus status = null;

                        Logger.prompt("Select Status (I. IN_PROGRESS  D. DONE):");
                        switch (scanner.next()) {
                            case "I" -> status = TaskStatus.IN_PROGRESS;
                            case "D" -> status = TaskStatus.DONE;
                            default -> Logger.error(INVALID_OPTION);
                        }

                        if (status != null) {
                            String message = taskService.updateStatus(id, status);
                            Logger.info(message);
                        }
                    }

                    case 5 -> {
                        Long id = getTaskId();

                        String message = taskService.deleteTask(id);
                        Logger.info(message);
                    }
                    case 6 -> {
                        Logger.info("GOODBYE!");
                        return;
                    }
                    default -> Logger.error(INVALID_OPTION);
                }
            } catch (InputMismatchException ex) {
                Logger.warn("Oops! That wasn't a valid number. Please try again.");
                scanner.nextLine();
            } catch (Exception ex) {
                Logger.error(ex.getMessage());
            }

        }
    }

    private Long getTaskId() {
        Logger.prompt("Enter task ID:");
        return this.scanner.nextLong();
    }
}
