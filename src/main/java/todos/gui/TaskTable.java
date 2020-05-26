package todos.gui;

import todos.core.Storages.Storage;
import todos.gui.Listeners.TableRemoveListener;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;

public class TaskTable {

    private final JTable table;
    private final TableModel tableModel;
    private final DeleteTaskButton deleteButton;
    private final ActionListener listener;
    private final Storage storage;

    TaskTable(TableModel todoTableModel, DeleteTaskButton deleteButton, Storage storage){
        this.tableModel = todoTableModel;
        this.deleteButton = deleteButton;
        this.table = new JTable(todoTableModel);
        this.storage = storage;
        this.listener = new TableRemoveListener(storage, table);
        deleteButton.withTable(table);
    }

    protected void withPane(JScrollPane taskListScrollPane) {
        taskListScrollPane.setViewportView(new TaskTable(tableModel, deleteButton, storage).table);
    }
}
