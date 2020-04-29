package todos.core;

import todos.gui.TodoListModel;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TodoList implements TodoInterface {

    private final List<Todos> list = new LinkedList<>();

    public Iterator<Todos> iterator() {
        return list.iterator();
    }

    public void remove(int i) {
        if (i >= 0 && i < this.list.size()) {
            this.list.remove(i);
        }
    }

    public void add(Todos item) {
        this.list.add(item);
        TodoListModel todoListModel = TodoListModel.getInstance();
        todoListModel.add(item);
    }

    public Todos getElementAt(int i) {
        return list.get(i);
    }

    public int getSize() {
        return list.size();
    }

}
