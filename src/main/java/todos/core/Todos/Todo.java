package todos.core.Todos;

import todos.core.Outputs.Output;

    /**
     * Object which has abilities to save itself
     * using {@link Output} in object's methods
     */

public interface Todo {

    /**
     * Saves\prints object's fields' data to the output
     * @param output - {@link Output} object which saves data
     */

    void saveTodo(Output output);

}
