package todos.core;

import todos.gui.TodoListModel;

import javax.swing.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TodoList implements TodoInterface {

    public TodoList(){
        this.todoListModel = new TodoListModel(this);
    }

    private final TodoListModel todoListModel;
    private final List<Todos> list = new LinkedList<>();

    public Iterator<Todos> iterator() {
        return list.iterator();
    }

    public void remove(int i) {
        if (i >= 0 && i < this.list.size()) {
            this.list.remove(i);
        }
        this.todoListModel.remove(i);
    }

    public void add(Todos item) {
        this.list.add(item);
        this.todoListModel.add(this.getSize() - 1);
    }

    public Todos getElementAt(int i) {
        return list.get(i);
    }

    public int getSize() {
        return list.size();
    }

    @Override
    public ListModel<Todos> getModel() {
        return this.todoListModel;
    }

}
