package todos.core;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.util.stream.Collectors;
import javax.swing.*;

public class Todos {
    private final String name;
    private final String date;


    public Todos() {
        this.name = "";
        this.date = "";
    }

    public Todos(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public Todos(String name) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        this.name = name;
        this.date = dateFormat.format(tomorrow);
    }

    @Override
    public String toString() {
        return this.name + "    " + this.date;
    }

    //    private File file(String filename){
//        return new File((getClass().getClassLoader().getResource(filename).getFile()));
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
//    public JList<Todos> loadFromFile(String filename) throws IOException {
//        return new JList<Todos>(
//                (Vector<? extends Todos>) Files.readAllLines(file(filename).toPath())
//                        .stream()
//                        .map(line -> {
//                            String[] data = line.split(",");
//                            return new Todos(data[0], data[1], data[2]);
//                        })
//                        .collect(Collectors.toList()));
//    }
//
//    public void writeData(ObservableList<Todos> data, String filename) throws IOException {
//        if (data == null || filename == null){
//            throw new IllegalArgumentException("data не должна быть null");
//        }
//        Writer writer = new BufferedWriter(new FileWriter(file(filename)));
//        for (Todos line : data) {
//            String text = line.getName() + "," + line.getDate() + "," + line.getDescription() + "\n";
//            writer.write(text);
//        }
//        writer.flush();
//        writer.close();
//    }
//
//    public ObservableList<String> fillNamesIntoList(ObservableList<Todos> data){
//        ObservableList<String> list_view_data = FXCollections.observableArrayList();
//        for (Todos value : data) {
//            list_view_data.add(value.getName());
//        }
//        return list_view_data;
//    }

}