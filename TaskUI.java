/**
 * TaskUI.java
 * -------------------------------------------------------------
 * Pure Java Swing GUI for the Task Management System Using Priority Queue.
 * Inspired directly by classic Swing architecture (like RunwayUI in airport-runway-scheduler).
 * 
 * DSA & OOP Concepts:
 *   - OOP: Encapsulation, Polymorphism, Event-Driven Programming (ActionListener)
 *   - DSA: Priority Queue scheduling, Preemption & FIFO tie-breaking
 *   - File I/O: Logging executions permanently to task_log.txt
 */

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class TaskUI implements ActionListener {

    // Main Window Frame
    private JFrame jf;

    // Form Input Components
    private JLabel lblHeader, lblTaskId, lblTaskName, lblPriority;
    private JTextField txtTaskId, txtTaskName;
    private JComboBox<String> cbPriority;

    // Action Buttons
    private JButton btnAdd, btnProcess, btnSamples, btnClear;

    // Queue & Log Displays
    private JLabel lblQueueTitle, lblLogTitle, lblStatus;
    private JTextArea taQueue, taLog;
    private JScrollPane spQueue, spLog;

    // Core Data Structure (Custom Linked-List Priority Queue)
    private TaskQueue taskQueue = new TaskQueue();

    // Operational Counters
    private int completedCount = 0;
    private int autoIdCounter = 101;

    public TaskUI() {
        // Initialize JFrame window
        jf = new JFrame("Task Management System (Priority Queue)");
        jf.setLayout(null);
        jf.setSize(850, 620);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setResizable(false);
        jf.getContentPane().setBackground(new Color(245, 247, 250));

        // 1. Header Banner
        lblHeader = new JLabel("TASK MANAGEMENT SYSTEM USING PRIORITY QUEUE");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 17));
        lblHeader.setForeground(new Color(30, 41, 59));
        lblHeader.setBounds(30, 15, 600, 25);
        jf.add(lblHeader);

        JLabel lblSub = new JLabel("DSA & OOP Mini-Project • Custom Linked-List Priority Queue (No Built-in Sorting)");
        lblSub.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSub.setForeground(new Color(100, 116, 139));
        lblSub.setBounds(30, 40, 600, 20);
        jf.add(lblSub);

        // 2. Input Form (Left Side)
        lblTaskId = new JLabel("Task ID:");
        lblTaskId.setFont(new Font("Arial", Font.BOLD, 12));
        lblTaskId.setBounds(30, 75, 90, 25);
        jf.add(lblTaskId);

        txtTaskId = new JTextField("TSK-" + autoIdCounter);
        txtTaskId.setFont(new Font("Arial", Font.PLAIN, 12));
        txtTaskId.setBounds(120, 75, 160, 25);
        jf.add(txtTaskId);

        lblTaskName = new JLabel("Task Name:");
        lblTaskName.setFont(new Font("Arial", Font.BOLD, 12));
        lblTaskName.setBounds(30, 110, 90, 25);
        jf.add(lblTaskName);

        txtTaskName = new JTextField();
        txtTaskName.setFont(new Font("Arial", Font.PLAIN, 12));
        txtTaskName.setBounds(120, 110, 160, 25);
        jf.add(txtTaskName);

        lblPriority = new JLabel("Priority:");
        lblPriority.setFont(new Font("Arial", Font.BOLD, 12));
        lblPriority.setBounds(30, 145, 90, 25);
        jf.add(lblPriority);

        String[] priorityOptions = {"Critical", "High", "Medium", "Low"};
        cbPriority = new JComboBox<>(priorityOptions);
        cbPriority.setSelectedIndex(1); // Default to High
        cbPriority.setBounds(120, 145, 160, 25);
        jf.add(cbPriority);

        // Buttons
        btnAdd = new JButton("Add Task");
        btnAdd.setFont(new Font("Arial", Font.BOLD, 12));
        btnAdd.setBounds(30, 185, 115, 30);
        btnAdd.setBackground(new Color(37, 99, 235));
        btnAdd.setForeground(Color.BLACK);
        btnAdd.addActionListener(this);
        jf.add(btnAdd);

        btnProcess = new JButton("Process Next");
        btnProcess.setFont(new Font("Arial", Font.BOLD, 12));
        btnProcess.setBounds(155, 185, 125, 30);
        btnProcess.setBackground(new Color(16, 185, 129));
        btnProcess.setForeground(Color.BLACK);
        btnProcess.addActionListener(this);
        jf.add(btnProcess);

        btnSamples = new JButton("Load Samples");
        btnSamples.setFont(new Font("Arial", Font.PLAIN, 11));
        btnSamples.setBounds(30, 225, 115, 26);
        btnSamples.addActionListener(this);
        jf.add(btnSamples);

        btnClear = new JButton("Clear All");
        btnClear.setFont(new Font("Arial", Font.PLAIN, 11));
        btnClear.setBounds(155, 225, 125, 26);
        btnClear.addActionListener(this);
        jf.add(btnClear);

        // 3. Status Bar
        lblStatus = new JLabel("Pending Tasks: 0  |  Completed: 0  |  Next to Execute: None");
        lblStatus.setFont(new Font("Arial", Font.BOLD, 12));
        lblStatus.setForeground(new Color(71, 85, 105));
        lblStatus.setBounds(30, 260, 780, 25);
        jf.add(lblStatus);

        // 4. Current Priority Queue Display (Top-Right / Center)
        lblQueueTitle = new JLabel("Current Priority Queue (Sorted by Priority & Arrival Order):");
        lblQueueTitle.setFont(new Font("Arial", Font.BOLD, 12));
        lblQueueTitle.setBounds(305, 75, 450, 20);
        jf.add(lblQueueTitle);

        taQueue = new JTextArea();
        taQueue.setEditable(false);
        taQueue.setFont(new Font("Monospaced", Font.PLAIN, 12));
        taQueue.setBackground(Color.WHITE);
        spQueue = new JScrollPane(taQueue);
        spQueue.setBounds(305, 100, 505, 185);
        jf.add(spQueue);

        // 5. Execution History Log Display (Bottom Full-Width)
        lblLogTitle = new JLabel("Execution & Activity Log (Persisted to task_log.txt):");
        lblLogTitle.setFont(new Font("Arial", Font.BOLD, 12));
        lblLogTitle.setBounds(30, 300, 450, 20);
        jf.add(lblLogTitle);

        taLog = new JTextArea();
        taLog.setEditable(false);
        taLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
        taLog.setBackground(Color.WHITE);
        spLog = new JScrollPane(taLog);
        spLog.setBounds(30, 325, 780, 235);
        jf.add(spLog);

        // Populate sample tasks so the GUI starts populated
        loadSampleTasks();

        // Make window visible
        jf.setVisible(true);
    }

    // =========================================================================
    // Core Operations
    // =========================================================================

    private void loadSampleTasks() {
        taskQueue.clear();
        completedCount = 0;
        autoIdCounter = 101;
        taLog.setText("");

        taskQueue.enqueue(new Task("TSK-101", "Kernel Security Hotfix", "Critical", getNowTime()));
        taskQueue.enqueue(new Task("TSK-102", "Database Cluster Failover", "High", getNowTime()));
        taskQueue.enqueue(new Task("TSK-103", "Disk Buffer Sync", "High", getNowTime()));
        taskQueue.enqueue(new Task("TSK-104", "Network Packet Dispatch", "Medium", getNowTime()));
        taskQueue.enqueue(new Task("TSK-105", "Generate Daily User Report", "Low", getNowTime()));

        autoIdCounter = 106;
        txtTaskId.setText("TSK-" + autoIdCounter);

        logAction("SYSTEM: Populated initial sample tasks in Priority Queue.");
        updateUI();
    }

    private void updateUI() {
        // Update Queue Text Area
        taQueue.setText(taskQueue.getDisplayList());
        taQueue.setCaretPosition(0);

        // Update Status Label
        Task nextTask = taskQueue.peek();
        String nextStr = (nextTask != null) 
            ? "[" + nextTask.getPriority().toUpperCase() + "] " + nextTask.getTaskId() + " - " + nextTask.getTaskName()
            : "None";

        lblStatus.setText(String.format("Pending Tasks: %d  |  Completed: %d  |  Next to Execute: %s",
            taskQueue.getSize(), completedCount, nextStr));
    }

    private void logAction(String message) {
        String logLine = "[" + getNowTime() + "] " + message;
        taLog.append(logLine + "\n");
        taLog.setCaretPosition(taLog.getDocument().getLength());

        // File Persistence via FileHandler
        FileHandler.logExecution(logLine);
    }

    private String getNowTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    // =========================================================================
    // ActionListener Event Handling (OOP: Polymorphism & Interfaces)
    // =========================================================================

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == btnAdd) {
            handleAddTask();
        } else if (src == btnProcess) {
            handleProcessTask();
        } else if (src == btnSamples) {
            loadSampleTasks();
        } else if (src == btnClear) {
            handleClear();
        }
    }

    private void handleAddTask() {
        String id = txtTaskId.getText().trim();
        String name = txtTaskName.getText().trim();
        String priority = (String) cbPriority.getSelectedItem();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(jf, "Please enter a Task Name.", "Missing Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (id.isEmpty()) {
            id = "TSK-" + autoIdCounter++;
        }

        Task newTask = new Task(id, name, priority, getNowTime());
        taskQueue.enqueue(newTask);

        logAction("ENQUEUE: Added [" + priority.toUpperCase() + "] " + id + " - " + name);

        // Auto increment ID for next task
        autoIdCounter++;
        txtTaskId.setText("TSK-" + autoIdCounter);
        txtTaskName.setText("");
        txtTaskName.requestFocus();

        updateUI();
    }

    private void handleProcessTask() {
        if (taskQueue.isEmpty()) {
            JOptionPane.showMessageDialog(jf, "No tasks pending in the priority queue.", "Queue Underflow", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Task cleared = taskQueue.dequeue();
        if (cleared != null) {
            completedCount++;
            logAction("DEQUEUE (PROCESSED): [" + cleared.getPriority().toUpperCase() + "] " + cleared.getTaskId() + " - " + cleared.getTaskName());
            updateUI();
        }
    }

    private void handleClear() {
        taskQueue.clear();
        logAction("SYSTEM: Queue cleared by user.");
        updateUI();
    }

    // =========================================================================
    // Entry Point
    // =========================================================================
    public static void main(String[] args) {
        // Set system look and feel for native OS appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> new TaskUI());
    }
}
