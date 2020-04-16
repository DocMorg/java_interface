package com.company;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;


public class TodosOOP {
    private final SimpleStringProperty name;
    private final SimpleStringProperty date;
    private final SimpleStringProperty description;


    public String getName(){
        return name.get();
    }
    public String getDate(){
        return date.get();
    }
    public String getDescription(){
        return description.get();
    }

    public void setName(String value){
        name.set(value);
    }

    public void setDate(String value){
        date.set(value);
    }


    public void setDescription(String value){
        description.set(value);
    }


    public TodosOOP() {
        this.name = new SimpleStringProperty("");
        this.date = new SimpleStringProperty("");
        this.description = new SimpleStringProperty("");
    }

    public TodosOOP(String name, String date, String description) {
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleStringProperty(date);
        this.description = new SimpleStringProperty(description);
    }

    public TodosOOP(String name) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleStringProperty(dateFormat.format(tomorrow));
        this.description = new SimpleStringProperty(name);
    }

    private File file(String filename){
        return new File((getClass().getClassLoader().getResource(filename).getFile()));
    }

    public ObservableList<TodosOOP> loadFromFile(String filename) throws IOException {
        return FXCollections.observableArrayList(
                Files.readAllLines(file(filename).toPath())
                        .stream()
                        .map(line -> {
                            String[] details = line.split(",");
                            return new TodosOOP(details[0], details[1], details[2]);
                        })
                        .collect(Collectors.toList()));
    }

    public void writeData(ObservableList<TodosOOP> data, String filename) throws IOException {
        if (data == null || filename == null){
            throw new IllegalArgumentException("data не должна быть null");
        }
        Writer writer = new BufferedWriter(new FileWriter(file(filename)));
        for (TodosOOP line : data) {
            String text = line.name.getValue() + "," + line.date.getValue() + "," + line.description.getValue() + "\n";
            writer.write(text);
        }
        writer.flush();
        writer.close();
    }

    public ObservableList<String> fillNamesIntoList(@NotNull ObservableList<TodosOOP> data){
        ObservableList<String> list_view_data = FXCollections.observableArrayList();
        for (TodosOOP value : data) {
            list_view_data.add(value.getName());
        }
        return list_view_data;
    }

}
