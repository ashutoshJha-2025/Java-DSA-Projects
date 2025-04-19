import java.time.*;
import java.util.*;

public class Project2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Task> Queue = new PriorityQueue<>((task1, task2) -> task1.deadline.compareTo(task2.deadline));

        Queue.add(new Task("Buy Groceries", LocalDate.of(2025, 4, 19)));
        Queue.add(new Task("Study", LocalDate.of(2025, 4, 20)));
        Queue.add(new Task("Weekly Contest", LocalDate.of(2025, 4, 19)));
        Queue.add(new Task("Complete Assignment", LocalDate.of(2025, 4, 21)));

        while (true) {
            System.out.println("======================================");
            System.out.println("           Task Management Menu       ");
            System.out.println("======================================");
            System.out.println("1. View Today's Tasks");
            System.out.println("2. View All Upcoming Tasks");
            System.out.println("3. Exit");
            System.out.println("======================================");
            System.out.print("Enter Your Choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    showDueTasksForToday(Queue);
                    break;
                case 2:
                    upcomingTasks(Queue);
                    break;
                case 3:
                    System.out.println("\nExiting the program. Have a productive day!");
                    System.out.println("======================================");
                    return; // Exit the program
                default:
                    System.out.println("\nERROR: Invalid choice. Please try again!");
                    System.out.println("--------------------------------------");
                    break;
            }
        }
    }

    private static void showDueTasksForToday(PriorityQueue<Task> Queue) {
        System.out.println("\n======================================");
        System.out.println("        Tasks Due Today (" + LocalDate.now() + ")       ");
        System.out.println("======================================");
        boolean hasTasks = false;
        for (Task task : Queue) {
            if (task.deadline.equals(LocalDate.now())) {
                System.out.println(task);
                hasTasks = true;
            }
        }
        if (!hasTasks) {
            System.out.println("No tasks are due today!");
        }
        System.out.println("--------------------------------------\n");
    }

    private static void upcomingTasks(PriorityQueue<Task> Queue) {
        System.out.println("\n======================================");
        System.out.println("      Upcoming Tasks in Deadline Order");
        System.out.println("======================================");
        PriorityQueue<Task> tempQueue = new PriorityQueue<>(Queue); // Create a copy to avoid modifying the original queue
        if (tempQueue.isEmpty()) {
            System.out.println("No upcoming tasks!");
        } else {
            while (!tempQueue.isEmpty()) {
                System.out.println(tempQueue.poll());
            }
        }
        System.out.println("--------------------------------------\n");
    }
}

class Task {
    String description;
    LocalDate deadline;

    Task(String description, LocalDate deadline) {
        this.deadline = deadline;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task: " + description + " | Deadline: " + deadline;
    }
}