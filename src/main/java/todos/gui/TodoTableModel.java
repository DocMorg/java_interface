package todos.gui;


import todos.core.Todos.DefaultTodo;
import todos.core.Storages.Storage;

import javax.swing.table.AbstractTableModel;

public class TodoTableModel extends AbstractTableModel {

    private final Storage storage;

    public TodoTableModel(Storage storage) {
        this.storage = storage;
    }

    public void remove(int i) {
        this.fireTableRowsDeleted(i, i);
    }

    public void add(int newSize) {
        this.fireTableRowsInserted(newSize, newSize);
    }

    @Override
    public int getRowCount() {
        return storage.getSize();
    }

    @Override
    public int getColumnCount() {
        return storage.getColumnCount();
    }

    @Override
    public Object getValueAt(int i, int j) {
        return storage.getElementAt(i, j);
    }

    @Override
    public String getColumnName(int index) {
        return storage.getColumnName(index);
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
        storage.remove(row);
        DefaultTodo todo;
        if (col1 == 0){
            todo = new DefaultTodo(oldField, value.toString());
        } else {
            todo = new DefaultTodo(value.toString(), oldField);
        }
        storage.add(todo, row);

//        }
    }
}
