/**
 * Task.java
 * -------------------------------------------------------------
 * OOP Concept: Encapsulation & Model Class
 * Represents a single Task scheduled in the priority queue.
 * 
 * Part of: Task Management System Using Priority Queue
 * Object-Oriented Programming (OOP) in Java Swing
 */

public class Task {
    // Encapsulated private attributes
    private String taskId;
    private String taskName;
    private String priority;      // "Critical", "High", "Medium", "Low"
    private int priorityLevel;    // 4 for Critical, 3 for High, 2 for Medium, 1 for Low
    private String timeAdded;

    // Constructor
    public Task(String taskId, String taskName, String priority, String timeAdded) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.timeAdded = timeAdded;
        this.priorityLevel = assignPriorityLevel(priority);
    }

    /**
     * Maps priority label to numerical weight for queue comparisons.
     */
    private int assignPriorityLevel(String p) {
        if (p == null) return 1;
        switch (p.trim().toLowerCase()) {
            case "critical":
                return 4;
            case "high":
                return 3;
            case "medium":
                return 2;
            case "low":
            default:
                return 1;
        }
    }

    // Getters (Encapsulation)
    public String getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getPriority() {
        return priority;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public String getTimeAdded() {
        return timeAdded;
    }

    // Overriding toString() from Object class (Polymorphism)
    @Override
    public String toString() {
        return String.format("[%s] %s : %s (Added: %s)", 
            priority.toUpperCase(), taskId, taskName, timeAdded);
    }
}
