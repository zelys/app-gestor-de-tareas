package org.ipchile.ui;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class AddTaskUI extends JFrame {
    private JPanel contentPane;
    private JTextField nameField;
    private JTextField descField;
    private JButton addButton;
    private JButton cancelButton;
    private JPanel datePane;
    private JDateChooser deadline;
    private Date dateNow;

    public AddTaskUI(MediatorUI mediator) {
        setContentPane(contentPane);
        setTitle("Agregar Tarea");
        pack();

        deadline = new JDateChooser();
        deadline.setDateFormatString(" dd/MM/yyyy  ");
        datePane.add(deadline);
        dateNow = new Date();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                clearFields();}
        });

        addButton.addActionListener(e -> mediator.addNewTask(nameField, descField, deadline));

        cancelButton.addActionListener(e -> {
            mediator.updateTable();
            clearFields();
            dispose();
        });
    }

    public void clearFields() {
        nameField.setText("");
        descField.setText("");
        deadline.setDate(null);
    }

    public Boolean validateFields() {
        if (nameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe ingresar al menos el nombre de la tarea");
            return false;
        }
        if (deadline.getDate() != null) {
            if (deadline.getDate().before(dateNow)) {
                JOptionPane.showMessageDialog(this, "La fecha ingresada ya a pasado");
                return false;
            }
        }
        return true;
    }
}
