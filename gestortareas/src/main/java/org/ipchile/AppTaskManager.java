package org.ipchile;

import org.ipchile.controller.TaskController;
import org.ipchile.ui.MediatorUI;

import javax.swing.*;

public class AppTaskManager {
    public static void main(String[] args) {
        TaskController tareaController = new TaskController();
        MediatorUI mediator = new MediatorUI(tareaController);
        SwingUtilities.invokeLater(mediator::showTaskManagerUI);
    }
}
