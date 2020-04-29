package todos.gui;


import todos.core.TodoInterface;
import todos.core.TodoList;
import todos.core.Todos;

import javax.swing.*;
import java.util.Calendar;

public class TodoListModel extends AbstractListModel<Todos> {
    private static final TodoListModel instance = new TodoListModel();
    private final TodoInterface todoList;

    private TodoListModel() {
        this.todoList = new TodoList();
    }

    public static TodoListModel getInstance() {
        return instance;
    }


    public void remove(int i) {
        this.todoList.remove(i);
        this.fireContentsChanged(this, i, i);
    }

    public void add(Todos item) {
        this.todoList.add(item);
        this.fireContentsChanged(this,
                this.todoList.getSize() - 1, this.todoList.getSize() - 1);
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
