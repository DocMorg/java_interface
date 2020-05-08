package todos.core.Todos;

import todos.core.Outputs.Output;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DefaultTodo implements Todo {
    private final String name;
    private final String date;

    public DefaultTodo(String name, String date) {
        this.name = name;
        this.date = date;
    }
//написать комментарий
    public DefaultTodo(String name) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        this.name = name;
        this.date = dateFormat.format(tomorrow);
    }

    public void saveTodo(Output output, int i) {
        output.addHeader(String.valueOf(i))
            .add("name", this.name)
            .add("date", this.date);
    }

}