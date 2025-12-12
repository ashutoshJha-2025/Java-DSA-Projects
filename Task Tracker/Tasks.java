import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class Tasks{
    static Scanner sc = new Scanner(System.in);

    String title;
    Status status;

    enum Status {
        IN_PROGRESS, DONE, TODO
    }

    Date createdAt;
    Date updatedAt;

    public Tasks(String title) {
        this.title = title;
        this.status = Status.TODO;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public static void markInProgress(ArrayList<Tasks> task) {
        System.out.print("Id: ");
        int inProgressId = sc.nextInt();
        sc.nextLine();
        Tasks t = task.get(inProgressId - 1);
        t.status = Status.IN_PROGRESS;
        t.updatedAt = new Date();
    }

    public static void markDOne(ArrayList<Tasks> task) {
        System.out.print("Id: ");
        int doneId = sc.nextInt();
        sc.nextLine();
        Tasks t = task.get(doneId - 1);
        t.status = Status.DONE;
        t.updatedAt = new Date();
    }

    private static void addTask(ArrayList<Tasks> task) {
        while (true) {
            System.out.print("Add (or type '0' to exit): ");
            String title = sc.nextLine();

            if (title.equals("0") || title.isEmpty()) {
                return;
            }

            task.add(new Tasks(title));
            System.out.println("Task added successfully (ID: " + task.size() + " )");
        }
    }

    private static void updateTask(ArrayList<Tasks> task) {
        System.out.print("Update Id: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("New: ");
        String updatedTitle = sc.nextLine();

        Tasks t = task.get(id - 1);
        t.title = updatedTitle;
        t.updatedAt = new Date();

        System.out.println("updated " + id + " " + updatedTitle + " at " + t.updatedAt);
    }

    private static void deleteTask(ArrayList<Tasks> task) {
        System.out.print("Id: ");
        int id = sc.nextInt();
        sc.nextLine();

        task.remove(id - 1);
        System.out.println("deleted " + id);
    }

    private static void printTasks(ArrayList<Tasks> task) {
        for (int i = 0; i < task.size(); i++) {
            Tasks t = task.get(i);
            System.out.println((i + 1) + ". " + t.title + " [" + t.status + "] Created at: " + t.createdAt
                    + ", Updated at: " + t.updatedAt);
        }
    }

    public static void main(String[] args) {
        ArrayList<Tasks> task = new ArrayList<>();

        while (true) {
            System.out.println("\n===== Task Tracker =====");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. Edit Task");
            System.out.println("4. Mark Task In Progress");
            System.out.println("5. Mark Task Done");
            System.out.println("6. Display All Tasks");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addTask(task);
                    break;
                case 2:
                    deleteTask(task);
                    break;
                case 3:
                    updateTask(task);
                    break;
                case 4:
                    markInProgress(task);
                    break;
                case 5:
                    markDOne(task);
                    break;
                case 6:
                    printTasks(task);
                    break;
                case 7:
                    System.out.println("Exiting... Thank you for using the app!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
