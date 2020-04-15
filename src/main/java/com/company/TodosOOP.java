package com.company;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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

    public ObservableList<TodosOOP> loadFromFile(URL filename) throws IOException {
        return FXCollections.observableArrayList(
                Files.readAllLines(new File(filename.getPath()).toPath())
                        .stream()
                        .map(line -> {
                            String[] details = line.split(",");
                            return new TodosOOP(details[0],details[1],details[2]);
                        })
                        .collect(Collectors.toList()));
    }

    public String printline(TodosOOP line){
        return line.name.getValue() + "," + line.date.getValue() + "," + line.description.getValue() + "\n";
    }

    public ObservableList<String> fillNamesIntoList(ObservableList<TodosOOP> data){
        ObservableList<String> list_view_data = FXCollections.observableArrayList();
        for (TodosOOP value : data) {
            list_view_data.add(value.getName());
        }
        return list_view_data;
    }

}
