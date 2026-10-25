/**
 * TaskQueue.java
 * -------------------------------------------------------------
 * DSA & OOP Module: Custom Linked-List Priority Queue
 * Implemented from first principles without using java.util.PriorityQueue.
 * 
 * DSA Concepts:
 *   - Custom Singly Linked List with Priority Preemption
 *   - Operations: enqueue(), dequeue(), peek(), isEmpty(), getDisplayList()
 *   - Priority Order: Critical (4) > High (3) > Medium (2) > Low (1)
 *   - FIFO Tie-Breaking: Tasks with identical priority are processed 
 *     in strict First-In-First-Out order.
 */

public class TaskQueue {
    private Node front; // Head pointer of the priority queue
    private int size;   // Number of active tasks in queue

    public TaskQueue() {
        this.front = null;
        this.size = 0;
    }

    /**
     * Enqueue Operation (Priority Insertion)
     * Inserts task into its correct position based on priority.
     * If priority is equal, maintains FIFO arrival order.
     */
    public void enqueue(Task task) {
        Node newNode = new Node(task);

        // Case 1: Queue is empty OR new task has strictly higher priority than front
        if (front == null || task.getPriorityLevel() > front.data.getPriorityLevel()) {
            newNode.next = front;
            front = newNode;
            size++;
            return;
        }

        // Case 2: Traverse list to find correct priority position
        // For equal priority, we continue moving forward to maintain FIFO order
        Node current = front;
        while (current.next != null && current.next.data.getPriorityLevel() >= task.getPriorityLevel()) {
            current = current.next;
        }

        // Insert new node
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    /**
     * Dequeue Operation: O(1)
     * Removes and returns the highest priority task from the front.
     */
    public Task dequeue() {
        if (isEmpty()) {
            return null;
        }
        Task highestTask = front.data;
        front = front.next;
        size--;
        return highestTask;
    }

    /**
     * Peek Operation: O(1)
     * Returns the highest priority task at front without removing it.
     */
    public Task peek() {
        if (isEmpty()) {
            return null;
        }
        return front.data;
    }

    /**
     * Checks if the queue is empty.
     */
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * Returns total pending tasks in queue.
     */
    public int getSize() {
        return size;
    }

    /**
     * Clears all tasks from the queue.
     */
    public void clear() {
        front = null;
        size = 0;
    }

    /**
     * Returns a neatly formatted multiline string of all tasks currently in queue.
     * Perfect for display in a Swing JTextArea.
     */
    public String getDisplayList() {
        if (isEmpty()) {
            return "--- Queue is currently empty. No tasks pending. ---\n";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-5s | %-10s | %-10s | %-32s | %-10s\n", 
            "POS", "PRIORITY", "TASK ID", "TASK NAME", "TIME"));
        sb.append("--------------------------------------------------------------------------------\n");

        Node current = front;
        int position = 1;

        while (current != null) {
            Task t = current.data;
            sb.append(String.format("#%-4d | %-10s | %-10s | %-32s | %-10s\n",
                position,
                t.getPriority().toUpperCase(),
                t.getTaskId(),
                t.getTaskName().length() > 30 ? t.getTaskName().substring(0, 28) + ".." : t.getTaskName(),
                t.getTimeAdded()
            ));
            current = current.next;
            position++;
        }

        return sb.toString();
    }
}
