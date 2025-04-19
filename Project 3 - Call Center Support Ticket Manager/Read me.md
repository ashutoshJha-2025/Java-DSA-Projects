It is a **console-based Ticket Management System** that simulates how a customer support or helpdesk might handle tickets. It allows logging, viewing, resolving, and reviewing support tickets using basic Java collections.

---

### 📋 **Core Functionality Overview:**

1. **Ticket Logging:**
   - Users can create new tickets by entering an issue and customer name.
   - Each ticket is automatically assigned a unique ID using `ticketCounter`.
   - New tickets are added to a **queue (`LinkedList`)**, maintaining a First-In-First-Out (FIFO) structure.

2. **Viewing Pending Tickets:**
   - Displays all current tickets in the queue.
   - Each ticket shows ID, issue description, and customer name.

3. **Resolving Tickets:**
   - Users can resolve a ticket by providing its ID.
   - If found, the ticket is removed from the pending queue and stored in a `HashMap` called `pastTicketsMap`, representing resolved tickets history.

4. **Ticket History:**
   - Shows all resolved tickets from the history map.

5. **User Interaction:**
   - Repeatedly displays a menu with options to interact with the system until the user chooses to exit.

---

### 🧱 **Data Structures Used:**

- `Queue<Ticket>`: Manages pending tickets (FIFO).
- `HashMap<Integer, Ticket>`: Stores resolved tickets by ID for quick lookup.
- `Scanner`: For taking user input.
- `Ticket` class: Holds individual ticket data (ID, issue, customer name).

---

### ✅ **Highlights:**
- Clean separation of concerns via methods: `log()`, `viewPendingTickets()`, `resolveTicket()`, `viewTicketHistory()`.
- Intuitive and interactive user flow.
- Dynamically grows and tracks ticket info in real time.

---

