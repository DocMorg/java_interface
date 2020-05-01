package todos.core;

public interface Saver {

    /**
     * Adds named portion of data to be accumulated for outputting. Invoke
     * {@link #save()} after last call of this method to indicate that all
     * necessary data is written.
     *
     * @param name name of the variable
     * @param value value needs to be saved
     */

    void add(String name, String value);

    /**
     * Saves added content to target destination in destination-appropriate form.
     */
    void save();

}
