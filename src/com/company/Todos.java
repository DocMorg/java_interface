package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Todos {
    private String name;
    private String date;
    private String description;

    public Todos(){
        this.name = "";
        this.date = "";
        this.description = "";
    }
    public Todos(String name, String date, String description) {
        this.name = name;
        this.date = date;
        this.description = description;
    }

    public Todos(String name) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        this.name = name;
        this.date = dateFormat.format(tomorrow);
        this.description = "";
    }

    public Todos(String name, String description){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        this.name = name;
        this.date = dateFormat.format(tomorrow);
        this.description = description;
    }

}
