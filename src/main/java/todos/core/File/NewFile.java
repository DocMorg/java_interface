package todos.core.File;

import todos.core.Outputs.Output;
import todos.core.Todos.Todo;

import java.util.List;

public interface NewFile {
    List<String[]> read();
    void save(List<Todo> list);
}
