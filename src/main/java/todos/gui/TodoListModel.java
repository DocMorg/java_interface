package todos.gui;


import todos.core.Todo;
import todos.core.TodoList;

import javax.swing.*;

public class TodoListModel extends AbstractListModel<Todo> {

    private final TodoList todoList;

    public TodoListModel(TodoList todoList) {
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
    public Todo getElementAt(int index) {
        return this.todoList.getElementAt(index);
    }
}
