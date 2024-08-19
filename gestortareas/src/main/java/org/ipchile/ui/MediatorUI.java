package org.ipchile.ui;

import com.toedter.calendar.JDateChooser;
import org.ipchile.controller.TaskController;
import org.ipchile.model.FindTask;
import org.ipchile.model.TableModel;
import org.ipchile.model.Task;

import javax.swing.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class MediatorUI {

    private TaskManagerUI taskManagerUI;
    private AddTaskUI addTaskUI;
    private TableModel tableModel;
    private TaskController controller;
    private FindTask findTask;

    public MediatorUI(TaskController controller) {
        this.controller = controller;
    }

    public void showTaskManagerUI() {
        if (taskManagerUI == null) {
            taskManagerUI = new TaskManagerUI(this);
        }
        taskManagerUI.setLocationRelativeTo(null);
        taskManagerUI.setVisible(true);
    }

    public void showAddTaskUI() {
        if (addTaskUI == null) {
            addTaskUI = new AddTaskUI(this);
        }
        addTaskUI.setLocationRelativeTo(taskManagerUI);
        addTaskUI.setVisible(true);
    }

    public void updateTable() {
        tableModel = new TableModel(taskManagerUI.getTableTasks(), controller.getAllTask());
        tableModel.refreshTable();
    }

    public void addNewTask(JTextField name, JTextField desc, JDateChooser date) {
        if (addTaskUI.validateFields()) {
            controller.addTask(name.getText(), desc.getText(), convertToLocalDate(date));
            addTaskUI.dispose();
            addTaskUI.clearFields();
            updateTable();
        }
    }

    public void taskCompleted() {
        int i = getIndexSelection();
        var task = controller.getTask(i);
        if (!task.getComplete() && i != -1) {
            controller.taskIsComplete(i);
            updateTable();
            JOptionPane.showMessageDialog(taskManagerUI, "La tarea '" + task.getName() + "' se ha marcado como Completa");
        }
        updateTable();
    }

    public void deleteTask() {
        int i = getIndexSelection();
        if (i != -1) {
            var task = controller.getTask(i);
            int confirm = JOptionPane.showConfirmDialog(taskManagerUI,
                    "¿Está seguro de que desea eliminar la tarea: " + task.getName() + "?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == 0) {
                controller.removeTask(i);
                updateTable();
                JOptionPane.showMessageDialog(taskManagerUI, "La tarea ha sido eliminada");
            }
        }
        updateTable();
    }


    public void showPendingTasks() {
        var tareas = controller.getAllTask().stream().filter(tarea -> !tarea.getComplete()).toList();
        tableModel = new TableModel(taskManagerUI.getTableTasks(), tareas);
        tableModel.refreshTable();
    }

    public void showCompletedTasks() {
        var tareas = controller.getAllTask().stream().filter(Task::getComplete).toList();
        tableModel = new TableModel(taskManagerUI.getTableTasks(), tareas);
        tableModel.refreshTable();
    }

    public void findTasks() {
        findTask = new FindTask(taskManagerUI.getSearchField(), tableModel.getModel());
        findTask.findText();
        taskManagerUI.getTableTasks().setRowSorter(findTask.getSorter());
    }

    public int getIndexSelection() {
        return tableModel.getIdRow(taskManagerUI.getTableTasks().getSelectedRow());
    }

    private LocalDate convertToLocalDate(JDateChooser dateChooser) {
        Date date = dateChooser.getDate();
        if (date != null) {
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        return null;
    }
}
