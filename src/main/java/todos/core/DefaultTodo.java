package todos.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DefaultTodo implements Todo {
    private final String name;
    private final String date;

    public DefaultTodo(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public DefaultTodo(String name) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        this.name = name;
        this.date = dateFormat.format(tomorrow);
    }

    @Override
    public String toString() {
        return this.name + "    " + this.date;
    }

//    public void loadTodo(Loaded loaded, Readed readed) {
//        for (String[] strings: readed.read()){
//            new DefaultTodo(strings[0], strings[1]);
//        }
//    }

    public void saveTodo(Saved saved) {
        saved.add("name", this.name);
        saved.add("date", this.date);
        saved.save();
    }

}