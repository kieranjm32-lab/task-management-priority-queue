/**
 * Node.java
 * -------------------------------------------------------------
 * Custom Node for the Linked-List Priority Queue.
 * Encapsulates the Task data and a reference pointer to the next Node.
 */

public class Node {
    public Task data;
    public Node next;

    public Node(Task data) {
        this.data = data;
        this.next = null;
    }
}
