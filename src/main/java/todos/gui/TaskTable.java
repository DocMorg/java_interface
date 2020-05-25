package todos.gui;

import todos.gui.Listeners.TableAddListener;

import javax.swing.*;
import javax.swing.table.TableModel;

public class TaskTable {

    private final JTable table;
    private final TableModel tableModel;
    private final DeleteTaskButton deleteButton;

    TaskTable(TableModel todoTableModel, DeleteTaskButton deleteButton){
        this.tableModel = todoTableModel;
        this.deleteButton = deleteButton;
        this.table = new JTable(todoTableModel);
    }

    protected void withPane(JScrollPane taskListScrollPane) {
        taskListScrollPane.setViewportView(new TaskTable(tableModel, deleteButton).table);
    }
}
