package todos.core.File;

import todos.core.Outputs.Output;
import todos.core.Todos.Todo;

import java.util.List;

    /**
     * This object is for working with files. It helps
     * to save and load data from exact types of files
     * and maybe not only Files.
     */

public interface NewFile {

    /**
     * This method reads the data of the file to the
     * List of string and returns it.
     *
     * @return List<String[]>
     */

    List<String[]> read();

    /**
     * This method saves given List of objects into the file.
     *
     * @param list - list of Todo objects which needs to be save
     */

    void save(List<Todo> list);
}
