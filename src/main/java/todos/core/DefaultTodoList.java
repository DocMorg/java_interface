package todos.core;

import todos.core.Observer.EventsManaged;
import todos.core.Outputs.DateOutput;
import todos.core.Outputs.NameOutput;
import todos.core.Outputs.Output;
import todos.core.Readers.Readed;

import java.util.LinkedList;
import java.util.List;

public class DefaultTodoList implements TodoList {

    public DefaultTodoList(){
        this.events = new EventsManaged("add", "remove");

    }
    public EventsManaged events;
    String[] columnNames = {"Name", "Date"};
    private final List<Todo> list = new LinkedList<>();

    public void remove(int i) {
        if (i >= 0 && i < this.list.size()) {
            this.list.remove(i);
        }
        events.notify("remove", i);
    }

    /**
     * @param item - {@link Todo} item to add to the list
     */
    public void add(Todo item) {
        this.list.add(item);
        events.notify("add", getSize() - 1);
    }

    /**
     * @param item - {@link Todo} item to add to the list to the exact place
     * @param i - index to which new item will be added
     */
    public void add(Todo item, int i) {
        this.list.add(i, item);
        events.notify("add", i);
    }

    public String getElementAt(int i, int j) {
        Output output;
        if (j == 0){
            output = new NameOutput();
        } else {
            output = new DateOutput();
        }
        list.get(i).saveTodo(output);
        return output.toString();
    }

    public String getColumnName(int i){
        return  columnNames[i];
    }

    public int getColumnCount(){
        return  columnNames.length;
    }

    public int getSize() {
        return list.size();
    }

    public void loadTodo(Readed readed) {
        for (String[] strings: readed.read()){
            this.list.add(new DefaultTodo(strings[0], strings[1]));
        }
    }

    public void saveTodo(Output output){
        for (int m = 0; m < getSize(); m++){
             list.get(m).saveTodo(output);
        }
    }

}
