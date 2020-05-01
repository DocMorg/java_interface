package todos.core;

import todos.core.Observer.EventManager;

import java.util.LinkedList;
import java.util.List;

public class DefaultTodoList implements TodoList {

    public DefaultTodoList(){
        this.events = new EventManager("add", "remove");

    }
    public EventManager events;
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
        events.notify("add", this.getSize() - 1);
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

    public void saveTodo(Saver saver) {

    }

}
