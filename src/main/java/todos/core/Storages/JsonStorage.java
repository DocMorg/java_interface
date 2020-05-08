package todos.core.Storages;

import todos.core.File.JsonFile;
import todos.core.File.NewFile;
import todos.core.Observer.EventsManaged;
import todos.core.Outputs.DateOutput;
import todos.core.Outputs.NameOutput;
import todos.core.Outputs.Output;
import todos.core.Todos.DefaultTodo;
import todos.core.Todos.Todo;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class JsonStorage implements Storage {

    private final NewFile jsonFile;
    public EventsManaged events;
    String[] columnNames = {"Name", "Date"};

    public JsonStorage(String filename) {
        this.events = new EventsManaged("add", "remove");
        this.jsonFile = new JsonFile(file(filename));
    }

    @Override
    public void remove(int i) {
        if (i != -1) {
            List<Todo> list = loadTodo();
            list.remove(i);
            jsonFile.save(list);
            events.notify("remove", i);
        }
        else{
            System.out.println("Nothing to remove, index is -1");
        }
    }

    @Override
    public void add(Todo item) {
        List<Todo> list = loadTodo();
        list.add(item);
        jsonFile.save(list);
        events.notify("add", getSize() - 1);
    }

    @Override
    public void add(Todo item, int i) {
        List<Todo> list = loadTodo();
        list.add(i, item);
        jsonFile.save(list);
        events.notify("add", i);
    }

    @Override
    public List<Todo> loadTodo() {
        List<Todo> todolist = new LinkedList<>();
        for (String[] strings: jsonFile.read()){
            todolist.add(new DefaultTodo(strings[0], strings[1]));
//            events.notify("add", getSize() - 1);
        }
        return todolist;
    }

    @Override
    public int getSize() {
        return jsonFile.read().size();
    }

    @Override
    public String getElementAt(int i, int j) {
        Output output;
        if (j == 0){
            output = new NameOutput();
        } else {
            output = new DateOutput();
        }
        loadTodo().get(i).saveTodo(output, i);
        return output.toString();
    }

    @Override
    public String getColumnName(int i){
        return  columnNames[i];
    }

    @Override
    public int getColumnCount(){
        return  columnNames.length;
    }

    private File file(String filename){
        return new File((Objects.requireNonNull(getClass().getClassLoader().
                getResource(filename)).getFile()));
    }
}
