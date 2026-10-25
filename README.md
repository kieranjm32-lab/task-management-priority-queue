# ⚡ Task Management System Using Priority Queue

A clean, beginner-friendly **Data Structures & Algorithms (DSA)** and **Object-Oriented Programming (OOP)** college mini-project modeling an operating system task scheduler. Built using **Pure Java (OOP)**, a custom **Linked-List Priority Queue** (from scratch, no built-in Java sorting/collections), and a classic **Java Swing GUI** inspired by [`airport-runway-scheduler`](https://github.com/jvstin47/airport-runway-scheduler).

---

## 📌 Project Overview
Operating systems schedule tasks and background processes using priority queuing:
* **Priority Levels:** 4 operational tiers:
  * 🔴 **Critical (Weight 4):** Highest priority &mdash; pre-empts lower priority tasks.
  * 🟠 **High (Weight 3):** Urgent tasks (e.g., database transactions, disk sync).
  * 🔵 **Medium (Weight 2):** Normal tasks (e.g., network packet dispatch).
  * 🟢 **Low (Weight 1):** Lowest priority (e.g., telemetry, documentation indexing).
* **FIFO Tie-Breaking:** Tasks arriving with the same priority tier are strictly processed in the order they arrived (**First-In, First-Out**).
* **Data Persistence:** Every executed task is logged permanently to `task_log.txt`.

---

## 🛠️ Data Structures & Complexity (DSA)

Rather than relying on `java.util.PriorityQueue`, this project implements a custom **Singly Linked-List Priority Queue** from first principles:

```
[ FRONT / HEAD ]                                                      [ REAR / TAIL ]
       │                                                                     │
       ▼                                                                     ▼
┌──────────────┐     ┌──────────────┐     ┌──────────────┐            ┌──────────────┐
│ [CRITICAL]   │ ──> │ [HIGH]       │ ──> │ [HIGH]       │ ── ... ──> │ [LOW]        │ ──> null
│ TSK-101      │     │ TSK-102      │     │ TSK-103      │            │ TSK-105      │
└──────────────┘     └──────────────┘     └──────────────┘            └──────────────┘
```

| Operation | Method | Time Complexity | Description |
| :--- | :--- | :---: | :--- |
| **Priority Enqueue** | `enqueue(Task t)` | **$O(1)$** (best) / **$O(n)$** | Preempts line if higher priority; placed in order |
| **Process Dequeue** | `dequeue()` | **$O(1)$** | Removed directly from `front` (Head) |
| **Peek Next** | `peek()` | **$O(1)$** | Inspects highest priority task at `front` |
| **Display Traversal**| `getDisplayList()` | **$O(n)$** | Formats pending tasks for Swing text area |

---

## 👥 Object-Oriented Programming (OOP) Principles Applied

1. **Encapsulation:**
   - [`Task.java`](Task.java) protects task attributes (`taskId`, `taskName`, `priority`, `timeAdded`) using `private` access modifiers and provides public getter methods.
2. **Polymorphism:**
   - Overriding the `toString()` method from `java.lang.Object` for custom string representation.
   - Implementing `ActionListener` in [`TaskUI.java`](TaskUI.java) to handle button events via polymorphic dispatch.
3. **Abstraction:**
   - [`TaskQueue.java`](TaskQueue.java) hides the complex pointer manipulation and node-linking mechanics behind simple `enqueue()` and `dequeue()` interfaces.
4. **Exception Handling & File I/O:**
   - [`FileHandler.java`](FileHandler.java) uses `try-with-resources` (`FileWriter`, `PrintWriter`) and handles `IOException` to permanently write to `task_log.txt`.

---

## 📁 Source File Breakdown

| File | Module | Responsibility |
| :--- | :--- | :--- |
| **`Task.java`** | **OOP Model** | Encapsulates task attributes, constructor, getters, and `toString()`. |
| **`Node.java`** | **Linked Node** | Defines the singly linked node storing `Task data` and `Node next`. |
| **`TaskQueue.java`** | **DSA Queue** | Custom linked-list priority queue with preemption and FIFO tie-breaking. |
| **`TaskUI.java`** | **Swing GUI** | Classic Java Swing interface (`JFrame`, `JTextField`, `JComboBox`, `JTextArea`). |
| **`FileHandler.java`** | **File I/O** | Permanent execution logging via `FileWriter` with `try-catch` handling. |
| **`run.sh`** | **Build & Run** | One-command compilation and launch script. |

---

## 🚀 How to Run on Mac

### Option 1: Using the Helper Script
```bash
cd /Users/kieranjosephmathew/.gemini/antigravity/scratch/task-management-priority-queue
./run.sh
```

### Option 2: Manual Compilation
```bash
cd /Users/kieranjosephmathew/.gemini/antigravity/scratch/task-management-priority-queue
javac *.java
java TaskUI
```

---

## 📖 Viva Voce Reference
For comprehensive questions and answers tailored for your college oral examination, see [`VIVA_GUIDE.md`](VIVA_GUIDE.md).
