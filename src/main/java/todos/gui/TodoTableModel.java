package todos.gui;


import todos.core.DefaultTodo;
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
        return todoList.getSize();
    }

    @Override
    public int getColumnCount() {
        return todoList.getColumnCount();
    }

    @Override
    public Object getValueAt(int i, int j) {
        return todoList.getElementAt(i, j);
    }

    @Override
    public String getColumnName(int index) {
        return todoList.getColumnName(index);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        if(!isCellEditable(row, column)){
            return;
        }

        int col1 = 0;
        if (column != 1){
            col1 = 1;
        }
        String oldField = getValueAt(row, col1).toString();
        todoList.remove(row);
        DefaultTodo todo;
        if (col1 == 0){
            todo = new DefaultTodo(oldField, value.toString());
        } else {
            todo = new DefaultTodo(value.toString(), oldField);
        }
        todoList.add(todo, row);

//        }
    }
}
