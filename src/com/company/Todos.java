package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Todos {
    private String name;
    private String time;
    private String description;

    public Todos(){
        this.name = "";
        this.time = "";
        this.description = "";
    }
    public Todos(String name, String time, String description) {
        this.name = name;
        this.time = time;
        this.description = description;
    }

    public Todos(String name) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        this.name = name;
        this.time = dateFormat.format(tomorrow);
        this.description = "";
    }

    public Todos(String name, String description){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        this.name = name;
        this.time = dateFormat.format(tomorrow);
        this.description = description;
    }

}
