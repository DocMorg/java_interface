package todos.core.Storages;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import todos.core.File.CsvFile;
import todos.core.File.NewFile;
import todos.core.Outputs.CsvOutput;
import todos.core.Todos.DefaultTodo;
import todos.core.Observer.EventsManaged;
import todos.core.Outputs.DateOutput;
import todos.core.Outputs.NameOutput;
import todos.core.Outputs.Output;
import todos.core.Todos.Todo;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class CsvStorage implements Storage {

    private final NewFile csvFile;
    public EventsManaged events;
    String[] columnNames = {"Name", "Date"};

    public CsvStorage(String filename, EventsManaged events) {
        this.events = events;
        this.csvFile = new CsvFile(file(filename));
    }

    private File file(String filename){
        return new File((Objects.requireNonNull(getClass().getClassLoader().
                getResource(filename)).getFile()));
    }

    public void remove(int i) {
        if (i != -1) {
            List<Todo> list = loadTodo();
            list.remove(i);
            csvFile.save(list);
            events.notify("remove", i);
        }
        else{
            System.out.println("Nothing to remove, index is -1");
        }
    }

    /**
     * @param item - {@link Todo} item to add to the list
     */
    public void add(Todo item) {
        List<Todo> list = loadTodo();
        list.add(item);
        csvFile.save(list);
        events.notify("add", getSize() - 1);
    }

    /**
     * @param item - {@link Todo} item to add to the list to the exact place
     * @param i - index to which new item will be added
     */
    public void add(Todo item, int i) {
        List<Todo> list = loadTodo();
        list.add(i, item);
        csvFile.save(list);
        events.notify("add", i);
    }

    public String getElementAt(int i, int j) {
        Output output;
        if (j == 0){
            output = new NameOutput();
        } else {
            output = new DateOutput();
        }
        loadTodo().get(i).saveTodo(output);
        return output.toString();
    }

    public String getColumnName(int i){
        return  columnNames[i];
    }

    public int getColumnCount(){
        return  columnNames.length;
    }

    public int getSize() {
        return csvFile.read().size();
    }

    public List<Todo> loadTodo() {
        List<Todo> todolist = new LinkedList<>();
        for (String[] strings: csvFile.read()){
            todolist.add(new DefaultTodo(strings[0], strings[1]));
//            events.notify("add", getSize() - 1);
        }
        return todolist;
    }
}
