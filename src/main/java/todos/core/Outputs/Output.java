package todos.core.Outputs;

public interface Output {


    /**
     * Used to specify data for serialized object header.
     *
     * @param header - type-tag name provided
     */
    Output addHeader(String header);

    /**
     * Adds named portion of data to be accumulated for outputting. Invoke
     * {@link #save()} after last call of this method to indicate that all
     * necessary data is written.
     *
     * @param name name of the variable
     * @param value value needs to be saved
     */

    Output add(String name, String value);

    /**
     * Saves added content to target destination in destination-appropriate form.
     */
    void save();

}
