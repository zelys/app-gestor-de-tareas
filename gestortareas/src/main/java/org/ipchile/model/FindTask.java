package org.ipchile.model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.regex.PatternSyntaxException;

public class FindTask {

    private JTextField findField;
    private TableRowSorter<DefaultTableModel> sorter;

    public FindTask(JTextField findField, DefaultTableModel tableModel) {
        this.findField = findField;
        this.sorter = new TableRowSorter<>(tableModel);
    }

    public void findText() {
        String text = findField.getText();
        if (text.trim().isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            try {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            } catch (PatternSyntaxException e) {
                JOptionPane.showMessageDialog(findField, "Patrón de búsqueda inválido",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public TableRowSorter<DefaultTableModel> getSorter() {
        return sorter;
    }
}
