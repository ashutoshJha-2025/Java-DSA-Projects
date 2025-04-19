import java.util.*;

public class Project3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<Ticket> ticketQueue = new LinkedList<>();
        HashMap<Integer, Ticket> pastTicketsMap = new HashMap<>();
        int ticketCounter = 1;

        System.out.println("======================================");
        System.out.println("       Welcome to Ticket System       ");
        System.out.println("======================================");

        while (true) {
            System.out.println("\n--------------------------------------");
            System.out.println("1. Log a new Ticket.");
            System.out.println("2. View Pending Tickets.");
            System.out.println("3. Resolve a Ticket.");
            System.out.println("4. View Ticket History.");
            System.out.println("5. Exit.");
            System.out.println("--------------------------------------");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    log(ticketQueue, sc, ticketCounter);
                    break;
                case 2:
                    viewPendingTickets(ticketQueue);
                    break;
                case 3:
                    resolveTicket(ticketQueue, sc, pastTicketsMap);
                    break;
                case 4:
                    viewTicketHistory(pastTicketsMap);
                    break;
                case 5:
                    System.out.println("\nThank you for using the Ticket System. Goodbye!");
                    return;
                default:
                    System.out.println("\nInvalid choice! Please try again.");
                    break;
            }
        }
    }

    private static void log(Queue<Ticket> ticketQueue, Scanner sc, int ticketCounter) {
        System.out.println("\n--- Log a New Ticket ---");
        while (true) {
            System.out.print("Enter issue (or 0 to exit): ");
            String issue = sc.nextLine();
            if (issue.equals("0")) {
                System.out.println("Returning to main menu...\n");
                return;
            }
            System.out.print("Enter Customer Name: ");
            String name = sc.nextLine();
            ticketQueue.add(new Ticket(ticketCounter, issue, name));
            System.out.println("Ticket added successfully! (Ticket ID: " + ticketCounter++ + ")\n");
        }
    }

    private static void viewPendingTickets(Queue<Ticket> ticketQueue) {
        System.out.println("\n--- Pending Tickets ---");
        if (ticketQueue.isEmpty()) {
            System.out.println("No pending tickets.\n");
            return;
        }
        for (Ticket ticket : ticketQueue) {
            System.out.println(
                    "Ticket ID: " + ticket.id + ", Issue: " + ticket.issue + ", Customer Name: " + ticket.customerName);
        }
        System.out.println();
    }

    private static void resolveTicket(Queue<Ticket> ticketQueue, Scanner sc, HashMap<Integer, Ticket> pastTicketsMap) {
        System.out.println("\n--- Resolve a Ticket ---");
        if (ticketQueue.isEmpty()) {
            System.out.println("No tickets to resolve.\n");
            return;
        }
        System.out.print("Enter Ticket ID to resolve: ");
        int resolveTicketNumber = sc.nextInt();
        sc.nextLine(); 

        for (Ticket ticket : ticketQueue) {
            if (ticket.id == resolveTicketNumber) {
                pastTicketsMap.put(ticket.id, ticket);
                ticketQueue.remove(ticket);
                System.out.println("Ticket resolved and moved to history. (Ticket ID: " + ticket.id + ")\n");
                return;
            }
        }
        System.out.println("Ticket ID not found. Please try again.\n");
    }

    private static void viewTicketHistory(HashMap<Integer, Ticket> pastTicketsMap) {
        System.out.println("\n--- Ticket History ---");
        if (pastTicketsMap.isEmpty()) {
            System.out.println("No tickets in history.\n");
            return;
        }
        for (int key : pastTicketsMap.keySet()) {
            Ticket temp = pastTicketsMap.get(key);
            System.out
                    .println("Ticket ID: " + key + ", Issue: " + temp.issue + ", Customer Name: " + temp.customerName);
        }
        System.out.println();
    }
}

class Ticket {
    int id;
    String issue;
    String customerName;

    public Ticket(int id, String issue, String customerName) {
        this.id = id;
        this.issue = issue;
        this.customerName = customerName;
    }
}
