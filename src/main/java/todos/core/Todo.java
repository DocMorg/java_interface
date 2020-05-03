package todos.core;

public interface Todo {

//    /**
//     * Object which has abilities to save and load itself
//     * using {@link Saved} object's methods
//     *
//     *
//     * @param loader - {@link Loader} which loads data
//     */
//
//    Todo loadTodo(Loader loader);

    /**
     *
     * @param saved - {@link Saved} which saves data
     */
    void saveTodo(Saved saved);

}
