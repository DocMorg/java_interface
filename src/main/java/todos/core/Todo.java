package todos.core;

import todos.core.Outputs.Output;

public interface Todo {

//    /**
//     * Object which has abilities to save and load itself
//     * using {@link Output} object's methods
//     *
//     *  @param loaded - {@link Loaded} object which loads readed data
//     * @param readed - {@link Readed} object which reads data
//     */
//
//    void loadTodo(Loaded loaded, Readed readed);

    /**
     *
     * @param output - {@link Output} object which saves data
     */
    void saveTodo(Output output);

}
