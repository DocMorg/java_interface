package todos.core;

public interface Todo {

//    /**
//     * Object which has abilities to save and load itself
//     * using {@link Saved} object's methods
//     *
//     *  @param loaded - {@link Loaded} object which loads readed data
//     * @param readed - {@link Readed} object which reads data
//     */
//
//    void loadTodo(Loaded loaded, Readed readed);

    /**
     *
     * @param saved - {@link Saved} object which saves data
     */
    void saveTodo(Saved saved);

}
