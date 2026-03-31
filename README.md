# Task Manager

A simple console-based Java application for managing your daily tasks. This application demonstrates basic CRUD (Create,
Read, Update, Delete) operations in a simple command-line interface.

## Features

- **Create Task**: Add new tasks with a title and description.
- **View All Tasks**: List all existing tasks.
- **Get Task**: View details of a specific task using its ID.
- **Update Task Status**: Modify the status of a task to either `IN_PROGRESS` or `DONE`.
- **Delete Task**: Remove a task by its ID.
- **Pre-populated Data**: Starts with a few default tasks for testing purposes.

## Requirements

- **Java 21 or higher**: This project utilizes modern Java features such as unnamed classes and instance main methods (
  `void main()`), as well as enhanced `switch` expressions.

## Getting Started

1. Check out the project or simply download the source code.
2. Open your terminal or IDE (like IntelliJ IDEA).
3. If running from the terminal, compile and run the main file:
   ```bash
   # Navigate to the src directory
   cd src

   # Run the application (Java 22+ can run it directly, or enable preview flags on Java 21)
   java --enable-preview --source 21 TaskManager.java
   ```

## Usage

When you run the application, you will be presented with a menu. Enter the number corresponding to the action you want
to perform and follow the prompts.

```text
===WELCOME TO TASK MANAGER===
1. Create Task
2. All Tasks
3. Get Task
4. Update Task
5. Delete Task
6. Exit
Choose option: 
```

### Example: Creating a Task

1. Enter `1` at the main menu.
2. Provide a title (e.g., `Read Book`).
3. Provide a description (e.g., `Read at least 20 pages of the new book.`).
4. The system will confirm the successful creation of your task.

## Architecture

The application is structured simply with domain objects (`Task`, `TaskStatus`), a repository implementation for storing
tasks in memory (`TaskRepository`), and a service layer (`TaskService`, `TaskServiceImpl`) encapsulating the business
logic.
