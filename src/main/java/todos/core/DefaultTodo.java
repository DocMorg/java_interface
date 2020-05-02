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

    @Override
    public void saveTodo(Saver saver) {
        saver.add("name", this.name);
        saver.add("date", this.date);
        saver.save();
    }

//    @Override
//    public Todo loadTodo() {
//        return this;
//    }
    


//
//    public ObservableList<TodosOOP> loadFromFile(String filename) throws IOException {
//        return FXCollections.observableArrayList(
//                Files.readAllLines(file(filename).toPath())
//                        .stream()
//                        .map(line -> {
//                            String[] details = line.split(",");
//                            return new TodosOOP(details[0], details[1], details[2]);
//                        })
//                        .collect(Collectors.toList()));
//    }
//
//    private File file(String filename){
//        return new File((getClass().getClassLoader().getResource(filename).getFile()));
//    }
//
//    public JList<DefaultTodo> loadFromFile(String filename) throws IOException {
//        return new JList<DefaultTodo>(
//                (Vector<? extends DefaultTodo>) Files.readAllLines(file(filename).toPath())
//                        .stream()
//                        .map(line -> {
//                            String[] data = line.split(",");
//                            return new DefaultTodo(data[0], data[1], data[2]);
//                        })
//                        .collect(Collectors.toList()));
//    }
//
//    public void writeData(ObservableList<DefaultTodo> data, String filename) throws IOException {
//        if (data == null || filename == null){
//            throw new IllegalArgumentException("data не должна быть null");
//        }
//        Writer writer = new BufferedWriter(new FileWriter(file(filename)));
//        for (DefaultTodo line : data) {
//            String text = line.getName() + "," + line.getDate() + "," + line.getDescription() + "\n";
//            writer.write(text);
//        }
//        writer.flush();
//        writer.close();
//    }
//
//    public ObservableList<String> fillNamesIntoList(ObservableList<DefaultTodo> data){
//        ObservableList<String> list_view_data = FXCollections.observableArrayList();
//        for (DefaultTodo value : data) {
//            list_view_data.add(value.getName());
//        }
//        return list_view_data;
//    }

}