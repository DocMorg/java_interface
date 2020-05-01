package todos.core;

public interface Todo {

//    /**
//     * Object which has abilities to save and load itself
//     * using {@link Saver} object's methods
//     *
//     *
//     * @param loader - {@link Loader} which loads data
//     */
//
//    Todo loadTodo(Loader loader);

    /**
     *
     * @param saver - {@link Saver} which saves data
     */
    void saveTodo(Saver saver);

}
