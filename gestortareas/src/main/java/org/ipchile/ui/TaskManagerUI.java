package org.ipchile.ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TaskManagerUI extends JFrame {
    private JPanel contentPane;
    private JButton addTaskButton;
    private JButton completedTaskButton;
    private JButton deleteButton;
    private JButton showPendingTasksButton;
    private JButton showCompletedTaskButton;
    private JTable tableTasks;
    private JButton searchButton;
    private JTextField searchField;

    public TaskManagerUI(MediatorUI mediator) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setTitle("Gestor de Tareas");
        pack();

        addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {mediator.updateTable();}
        });

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {mediator.findTasks();}
            public void removeUpdate(DocumentEvent e) {mediator.findTasks();}
            public void changedUpdate(DocumentEvent e) {mediator.findTasks();}
        });

        addTaskButton.addActionListener(e -> mediator.showAddTaskUI());

        completedTaskButton.addActionListener(e -> mediator.taskCompleted());

        deleteButton.addActionListener(e -> mediator.deleteTask());

        showCompletedTaskButton.addActionListener(e -> mediator.showCompletedTasks());

        showPendingTasksButton.addActionListener(e -> mediator.showPendingTasks());

        searchButton.addActionListener(e -> mediator.findTasks());
    }

    public JTable getTableTasks() {
        return tableTasks;
    }

    public JTextField getSearchField() {
        return searchField;
    }
}
