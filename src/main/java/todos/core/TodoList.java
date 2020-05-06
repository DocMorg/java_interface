package todos.core;

import todos.core.Outputs.Output;
import todos.core.Readers.Readed;

public interface TodoList {


    void remove(int i);
    void add(Todo item);
    void add(Todo item, int i);
    void loadTodo(Readed readed);
    void saveTodo(Output output);

    int getSize();
    String getElementAt(int i, int j);
    String getColumnName(int i);
    int getColumnCount();


}
