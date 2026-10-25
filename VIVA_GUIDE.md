# 🎓 Viva Voce & Oral Examination Guide
## Task Management System Using Priority Queue (DSA & OOP)

A quick preparation cheatsheet for 2nd-year B.Tech CSE students facing project viva and external laboratory examinations.

---

## 📌 Section 1: Data Structures & Algorithms (DSA)

### Q1: What is a Priority Queue, and how is it different from a standard FIFO Queue?
* **Answer:**
  A standard **Queue** operates strictly on a **First-In, First-Out (FIFO)** basis &mdash; the first element inserted is always the first one removed.
  In a **Priority Queue**, each element is assigned a priority value. Elements with higher priority preempt lower-priority elements and are dequeued first. Elements with equal priority fall back to standard FIFO order.

---

### Q2: Why did you implement a custom Linked-List Priority Queue instead of using `java.util.PriorityQueue`?
* **Answer:**
  To demonstrate fundamental Data Structure principles from scratch:
  1. Pointer manipulation (`Node front`, `Node next`).
  2. Custom priority insertion logic with $O(1)$ preemption at head.
  3. Strict FIFO tie-breaking (built-in Java `PriorityQueue` does not guarantee FIFO order for elements with equal priority).

---

### Q3: What are the time complexities of your queue operations?
* **Answer:**
  * **Enqueue (Priority Insertion):**
    * **Best Case:** **$O(1)$** &mdash; when the new task has higher priority than the head node (it preempts directly to the front).
    * **Worst Case:** **$O(n)$** &mdash; when inserting at the rear after traversing the existing nodes.
  * **Dequeue (Processing Task):**
    * **$O(1)$** &mdash; always removed directly from the `front` pointer.
  * **Peek (Next Task Inspection):**
    * **$O(1)$** &mdash; direct access to `front.data`.

---

### Q4: How does your code handle tasks with equal priority (Tie-Breaking)?
* **Answer:**
  When traversing the linked list during `enqueue()`, the while loop continues traversing as long as:
  ```java
  current.next.data.getPriorityLevel() >= task.getPriorityLevel()
  ```
  Because of the `>=` operator, the new task is placed **after** all existing tasks of the same priority level, strictly preserving the arrival sequence (FIFO).

---

### Q5: How do you handle Queue Underflow?
* **Answer:**
  Before processing or peeking, the system calls `isEmpty()` (`front == null`). If empty, `dequeue()` safely returns `null`, and the GUI displays an informative dialog to prevent `NullPointerException`.

---

## ☕ Section 2: Object-Oriented Programming (OOP) in Java

### Q6: Where is Encapsulation applied in your project?
* **Answer:**
  In [`Task.java`](Task.java), all internal attributes (`taskId`, `taskName`, `priority`, `timeAdded`) are marked `private`. External classes can only read them through controlled public getter methods (`getTaskId()`, `getTaskName()`, etc.).

---

### Q7: Where is Polymorphism used?
* **Answer:**
  1. **Method Overriding:** In [`Task.java`](Task.java), we override the `toString()` method from `java.lang.Object` to customize how task data is formatted.
  2. **Interface Polymorphism:** In [`TaskUI.java`](TaskUI.java), the class implements the `ActionListener` interface and provides its own implementation of `actionPerformed(ActionEvent e)`. When a button is clicked, Java's event dispatch thread calls our method polymorphically.

---

### Q8: What is Abstraction in this project?
* **Answer:**
  [`TaskQueue.java`](TaskQueue.java) provides an abstract interface (`enqueue()`, `dequeue()`, `peek()`, `isEmpty()`). The GUI (`TaskUI.java`) interacts only with these high-level methods without needing to know anything about `Node` pointers or link traversal.

---

### Q9: How did you implement File Persistence and Exception Handling?
* **Answer:**
  In [`FileHandler.java`](FileHandler.java), we use Java's `try-with-resources` construct with `FileWriter` and `PrintWriter`:
  ```java
  try (FileWriter fw = new FileWriter("task_log.txt", true);
       PrintWriter pw = new PrintWriter(fw)) {
      pw.println(logEntry);
  } catch (IOException e) {
      System.err.println("Error writing to log file: " + e.getMessage());
  }
  ```
  This ensures file streams are automatically closed, preventing memory leaks, and handles `IOException` cleanly without crashing the GUI.

---

## 🖥️ Section 3: Java Swing GUI Architecture

### Q10: What are the main Swing components used in the GUI?
* **Answer:**
  * **`JFrame`**: The top-level window container.
  * **`JLabel`**: Displays text prompts, headers, and status counts.
  * **`JTextField`**: Captures user input for Task ID and Task Name.
  * **`JComboBox`**: Dropdown component to select priority tiers (Critical, High, Medium, Low).
  * **`JButton`**: Triggers actions (`Add Task`, `Process Next`, `Load Samples`, `Clear All`).
  * **`JTextArea` & `JScrollPane`**: Displays the active prioritized queue and the execution activity log with scrolling support.
  * **`ActionListener`**: Listens for button click events and routes execution.
