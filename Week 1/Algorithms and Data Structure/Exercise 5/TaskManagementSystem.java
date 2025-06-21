import java.util.*;

class Task {
    int taskId;
    String taskName;
    String status;
    Task next;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
        this.next = null;
    }

    public void display() {
        System.out.println("Task ID: " + taskId + ", Name: " + taskName + ", Status: " + status);
    }
}

class TaskManager {
    Task head = null;

    public void addTask(int id, String name, String status) {
        Task newTask = new Task(id, name, status);
        if (head == null) {
            head = newTask;
        } else {
            Task current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newTask;
        }
    }

    public Task searchTask(int id) {
        Task current = head;
        while (current != null) {
            if (current.taskId == id) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void displayAllTasks() {
        if (head == null) {
            System.out.println("No tasks found.");
        } else {
            Task current = head;
            while (current != null) {
                current.display();
                current = current.next;
            }
        }
    }

    public boolean deleteTask(int id) {
        if (head == null) return false;
        if (head.taskId == id) {
            head = head.next;
            return true;
        }
        Task current = head;
        while (current.next != null) {
            if (current.next.taskId == id) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }
}

public class TaskManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager manager = new TaskManager();
        int choice;

        do {
            System.out.println("\n=== Task Management System ===");
            System.out.println("1. Add Task");
            System.out.println("2. Search Task");
            System.out.println("3. Show All Tasks");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Task ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Task Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Task Status: ");
                    String status = sc.nextLine();
                    manager.addTask(id, name, status);
                    System.out.println("Task added successfully.");
                    break;
                case 2:
                    System.out.print("Enter Task ID to search: ");
                    int searchId = sc.nextInt();
                    Task found = manager.searchTask(searchId);
                    if (found != null) {
                        found.display();
                    } else {
                        System.out.println("Task not found.");
                    }
                    break;
                case 3:
                    manager.displayAllTasks();
                    break;
                case 4:
                    System.out.print("Enter Task ID to delete: ");
                    int deleteId = sc.nextInt();
                    boolean deleted = manager.deleteTask(deleteId);
                    if (deleted) {
                        System.out.println("Task deleted successfully.");
                    } else {
                        System.out.println("Task not found.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);

        sc.close();
    }
}
