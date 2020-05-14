package todos.gui;

import javax.swing.*;
import javax.swing.table.TableModel;

public class TaskTable {

    private final JTable table;

    TaskTable(TableModel todoTableModel, DeleteTaskButton deleteButton){
        this.table = new JTable(todoTableModel);
        deleteButton.bind(table);
    }

    protected void bind(TaskListScrollPane taskListScrollPane) {
        taskListScrollPane.bind(table);
    }
}
