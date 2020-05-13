package todos.core.Todos;

import todos.core.Outputs.Output;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DefaultTodo implements Todo {
    private final String name;
    private final String date;

    /**
     * Constructor usually used when data needs to be
     * edited, so it is created with already known date.
     *
     * @param name - name of notification
     * @param date - date of when it should be done
     */

    public DefaultTodo(String name, String date) {
        this.name = name;
        this.date = date;
    }

    /**
     * This is Default Constructor. Time of notification
     * automatically sets to tomorrow. After saving it
     * can be easily changed using UI.
     *
     * @param name - name of notification
     */

    public DefaultTodo(String name) {
        this.name = name;
        this.date = tomorrow();
    }

    /**
     * This method saves fields of the object to the given output.
     *
     * @param output - {@link Output} object which saves data
     */

    public void saveTodo(Output output) {
        output.addHeader("")
            .add("name", this.name)
            .add("date", this.date);
    }

    /**
     * Method for obtaining tomorrow's date in String format.
     *
     * @return String
     */
    private String tomorrow(){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        return dateFormat.format(tomorrow);
    }

}