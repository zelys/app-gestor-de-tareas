package org.ipchile.model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.util.List;

public class TableModel {

    private JTable table;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private List<Task> tasks;

    public TableModel(JTable table, List<Task> tasks) {
        this.table = table;
        this.tasks = tasks;
        CustomTableModel();
    }

    public void CustomTableModel() {
        String[] COLUMN_NAMES = {"N°", "NOMBRE", "DESCRIPCIÓN", "FECHA LIMITE", "ESTADO"};
        tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setModel(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        setupColumnWidths();
    }

    private void setupColumnWidths() {
        int[] COLUMN_WIDTHS = {20, 150, 220, 100, 80};
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < COLUMN_WIDTHS.length; i++) {
            columnModel.getColumn(i).setPreferredWidth(COLUMN_WIDTHS[i]);
        }
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        if (tasks != null) {
            for (Task t : tasks) {
                tableModel.addRow(new Object[]{
                        t.getId(),
                        t.getName(),
                        t.getDescription(),
                        t.getFecha(),
                        t.getEstado()
                });
            }
        }
    }

    public int getIdRow(int index) {
        if (validateTableSelection()) {
            return (int) table.getValueAt(sorter.convertRowIndexToModel(index), 0);
        }
        return -1;
    }

    private boolean validateTableSelection() {
        if (table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(table, "La lista de tareas está vacía");
            return false;
        }
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(table, "Selecciona una tarea de la lista");
            return false;
        }
        return true;
    }

    public DefaultTableModel getModel() {
        return tableModel;
    }
}