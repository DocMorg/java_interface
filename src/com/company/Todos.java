package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.*;


public class Todos {
    private SimpleStringProperty name;
    private SimpleStringProperty date;
    private SimpleStringProperty description;

    public String getName(){
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String value){
        name.set(value);
    }

    public StringProperty dateProperty() {
        return date;
    }

    public String getDate(){
        return date.get();
    }

    public void setDate(String value){
        date.set(value);
    }

    public String getDescription(){
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String value){
        description.set(value);
    }

    public Todos(){
        this.name = new SimpleStringProperty("");
        this.date = new SimpleStringProperty("");
        this.description = new SimpleStringProperty("");
    }
    public Todos(String name, String date, String description) {
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleStringProperty(date);
        this.description = new SimpleStringProperty(description);
    }

    public Todos(String name) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        this.name = new SimpleStringProperty(name);;
        this.date = new SimpleStringProperty(dateFormat.format(tomorrow));
        this.description = new SimpleStringProperty(name);
    }

    public Todos(String name, String description){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleStringProperty(dateFormat.format(tomorrow));
        this.description = new SimpleStringProperty(description);
    }

}
