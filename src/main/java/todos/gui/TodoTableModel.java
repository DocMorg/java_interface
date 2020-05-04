package todos.gui;


import todos.core.Todo;
import todos.core.TodoList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class TodoTableModel extends AbstractTableModel {

    private final TodoList todoList;

    public TodoTableModel(TodoList todoList) {
        this.todoList = todoList;
    }

    public void remove(int i) {
        this.fireTableRowsDeleted(i, i);
    }

    public void add(int newSize) {
        this.fireTableRowsInserted(newSize, newSize);
    }

    @Override
    public int getRowCount() {
        return this.todoList.getSize();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        return this.todoList.getElementAt(i);
    }
}
