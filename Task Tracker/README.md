
#  **Task Tracker**

The Task Tracker is a Java-based Command Line Interface (CLI) application designed to help users create, manage, update, and organize tasks efficiently. It uses simple text-based prompts and inputs, making it lightweight, fast, and easy to run directly from the terminal.


## 🎯 **Key Features**

### **1. Create New Tasks**

* Users can add a task by entering a **title**.
* Each task is automatically assigned:

  * a default status (`TODO`)
  * a creation timestamp (`createdAt`)
  * an update timestamp (`updatedAt`)

---

### **2. Update Task Status**

Users can change the status of a task to:

* `TODO`
* `IN_PROGRESS`
* `DONE`

---

### **3. Edit Existing Tasks**

* Users can modify the task **title**.
* Automatically refreshes the `updatedAt` timestamp.

---

### **4. Delete Tasks**

* Tasks can be removed permanently from the list using their index.

---

### **5. List All Tasks**

Displays all saved tasks with:

* ID
* Title
* Status
* Created time
* Updated time

---

### **6. Interactive CLI Menu**

The main loop provides numbered menu options:

1. Add Task
2. Update Task
3. Delete Task
4. List All Tasks
5. Search Task by Status
6. Print Task Details
7. Exit

