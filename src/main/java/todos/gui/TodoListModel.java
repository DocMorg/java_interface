package todos.gui;


import todos.core.TodoInterface;
import todos.core.TodoList;
import todos.core.Todos;

import javax.swing.*;
import java.util.Calendar;

public class TodoListModel extends AbstractListModel<Todos> {

    private final TodoInterface todoList;

    public TodoListModel(TodoInterface todoList) {
        this.todoList = todoList;
    }

    public void remove(int i) {
        this.fireIntervalRemoved(this, i, i);
    }

    public void add(int newSize) {
        this.fireIntervalAdded(this, newSize, newSize);
    }

    @Override
    public int getSize() {
        return this.todoList.getSize();
    }

    @Override
    public Todos getElementAt(int index) {
        return this.todoList.getElementAt(index);
    }
}
