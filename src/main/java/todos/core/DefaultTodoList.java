package todos.core;

import todos.core.Observer.EventsManaged;

import java.util.LinkedList;
import java.util.List;

public class DefaultTodoList implements TodoList {

    public DefaultTodoList(){
        this.events = new EventsManaged("add", "remove");

    }
    public EventsManaged events;
    private final List<Todo> list = new LinkedList<>();

    public void remove(int i) {
        if (i >= 0 && i < this.list.size()) {
            this.list.remove(i);
        }
        events.notify("remove", i);
    }

    /**
     * @param item - {@link Todo} item to add to the list
     */
    public void add(Todo item) {
        this.list.add(item);
        events.notify("add", getSize() - 1);
    }

    public Todo getElementAt(int i) {
        return list.get(i);
    }

    public int getSize() {
        return list.size();
    }

    public Todo loadTodo() {
        return null;
    }

    public void saveTodo(Saved saved){
        for (int m = 0; m < getSize(); m++){
            getElementAt(m).saveTodo(saved);
        }
    }

}
