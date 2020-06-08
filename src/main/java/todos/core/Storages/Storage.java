package todos.core.Storages;

import todos.core.Observer.EventsManaged;
import todos.core.Outputs.Output;
import todos.core.Todos.Todo;

import java.util.List;

public interface Storage {

    void remove(int i);
    void add(Todo item);
    void add(Todo item, int i);
    List<Todo> loadTodo();

    int getSize();
    String getElementAt(int i, int j);
    String getColumnName(int i);
    int getColumnCount();


}
