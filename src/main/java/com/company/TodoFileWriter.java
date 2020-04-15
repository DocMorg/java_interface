package com.company;

import javafx.collections.ObservableList;

import java.io.*;
import java.net.URL;

public class TodoFileWriter {
    private final URL path;

    public TodoFileWriter(String filename) {
        this.path = getClass().getClassLoader().getResource(filename);
    }

    protected void write(ObservableList<TodosOOP> data) throws IOException {
        Writer writer = new BufferedWriter(new FileWriter(new File(this.path.getPath())));
        for (TodosOOP line : data) {
            String text = line.printline(line);
            writer.write(text);
        }
        writer.flush();
        writer.close();
    }

}
