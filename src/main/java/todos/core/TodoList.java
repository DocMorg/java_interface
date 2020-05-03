package todos.core;

public interface TodoList {


    void remove(int i);
    void add(Todo item);
    int getSize();
    Todo getElementAt(int i);
    Todo loadTodo();

    void saveTodo(Saved saved);



}
