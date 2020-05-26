package todos.gui;

import todos.core.Storages.Storage;
import todos.gui.Listeners.TableRemoveListener;

import javax.swing.*;
import javax.swing.table.TableModel;

public class TaskTable {

    private final JTable table;
    private final TableModel tableModel;
    private final DeleteTaskButton deleteButton;
    private final Storage storage;

    TaskTable(TableModel todoTableModel, DeleteTaskButton deleteButton, Storage storage){
        this.tableModel = todoTableModel;
        this.deleteButton = deleteButton;
        this.table = new JTable(todoTableModel);
        this.storage = storage;
        deleteButton.withTable( new TableRemoveListener(storage, table));
    }

    protected void withPane(JScrollPane taskListScrollPane) {
        taskListScrollPane.setViewportView(new TaskTable(tableModel, deleteButton, storage).table);
    }
}
