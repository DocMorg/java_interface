package com.company;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;


public class TodoListOOP extends Application {

    private final String filename = "saveOOP.txt";
    private ObservableList<TodoListOOP> table_view_data;
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


    public TodoListOOP() {
        this.name = new SimpleStringProperty("");
        this.date = new SimpleStringProperty("");
        this.description = new SimpleStringProperty("");
    }

    public TodoListOOP(String name, String date, String description) {
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleStringProperty(date);
        this.description = new SimpleStringProperty(description);
    }

    public TodoListOOP(String name) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleStringProperty(dateFormat.format(tomorrow));
        this.description = new SimpleStringProperty(name);
    }


    private File file(String filename){
        return new File((getClass().getClassLoader().getResource(filename).getFile()));
    }

    public ObservableList<TodoListOOP> loadFromFile(String filename) throws IOException {
        return FXCollections.observableArrayList(
                Files.readAllLines(file(filename).toPath())
                        .stream()
                        .map(line -> {
                            String[] details = line.split(",");
                            return new TodoListOOP(details[0], details[1], details[2]);
                        })
                        .collect(Collectors.toList()));
    }

    public void writeData(ObservableList<TodoListOOP> data, String filename) throws IOException {
        if (data == null || filename == null){
            throw new IllegalArgumentException("data не должна быть null");
        }
        Writer writer = new BufferedWriter(new FileWriter(file(filename)));
        for (TodoListOOP line : data) {
            String text = line.name.getValue() + "," + line.date.getValue() + "," + line.description.getValue() + "\n";
            writer.write(text);
        }
        writer.flush();
        writer.close();
    }

    public ObservableList<String> fillNamesIntoList(@NotNull ObservableList<TodoListOOP> data){
        ObservableList<String> list_view_data = FXCollections.observableArrayList();
        for (TodoListOOP value : data) {
            list_view_data.add(value.getName());
        }
        return list_view_data;
    }

    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override 
    public void start(Stage stage) throws IOException {
        TodoListOOP todo = new TodoListOOP();
        if (getClass().getClassLoader().getResource(this.filename) == null){
            System.out.println("Error in filename");
            throw new FileNotFoundException("Data file was not found");
        }
        this.table_view_data = this.loadFromFile(this.filename);
        ListView<String> listView = new ListView<>(todo.fillNamesIntoList(this.table_view_data));

        listView.setPrefWidth(235);
        listView.setPrefHeight(200);

        Button addButton = new Button();
        addButton.setText("Add");


        TableView<TodoListOOP> table = new TableView<>(this.table_view_data);
        table.setPrefWidth(235);
        table.setPrefHeight(200);

        TableColumn<TodoListOOP, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<TodoListOOP, String> t) -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setName(t.getNewValue());
                    int row_id = t.getTableView().getSelectionModel().getSelectedIndex();
                    listView.getItems().set(row_id, t.getNewValue());
                });
        table.getColumns().add(nameColumn);

        TableColumn<TodoListOOP, String> timeColumn = new TableColumn<>("Date");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        timeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        timeColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<TodoListOOP, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setDate(t.getNewValue()));
        table.getColumns().add(timeColumn);

        TableColumn<TodoListOOP, String> descrColumn = new TableColumn<>("Description");
        descrColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        descrColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descrColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<TodoListOOP, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setDescription(t.getNewValue()));
        table.getColumns().add(descrColumn);

        TextField inputField = new TextField();

        addButton.setOnAction(e -> {
            this.table_view_data.addAll(FXCollections.observableArrayList(new TodoListOOP(inputField.getText())));
            listView.getItems().add(inputField.getText());
            inputField.setText("");
            inputField.requestFocus();
        });

        addButton.disableProperty()
                .bind(Bindings.isEmpty(inputField.textProperty()));

        Button removeButton = new Button();
        removeButton.setText("Remove");

        removeButton.setOnAction(e -> {
            if (table.getSelectionModel().getSelectedItem() != null ||
                    listView.getSelectionModel().getSelectedItem() != null){
                final int idToRemove = listView.getSelectionModel().getSelectedIndex();
                final int idToRemove1 = table.getSelectionModel().getSelectedIndex();
                if (idToRemove1 == -1) {
                    listView.getItems().remove(idToRemove);
                    table.getItems().remove(idToRemove);
                } else {
                    listView.getItems().remove(idToRemove1);
                    table.getItems().remove(idToRemove1);
                }
            }
        });
        table.setEditable(true);
        listView.setEditable(true);
        HBox entryBox = new HBox();
        entryBox.getChildren().addAll(inputField, addButton, removeButton);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(listView, table, entryBox);

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("TodoListOOP");
        stage.show();
    }

    @Override
    public void stop() throws IOException {
        if (this.table_view_data == null){
            System.exit(0);
        }
        TodoListOOP todos = new TodoListOOP();
        todos.writeData(this.table_view_data, this.filename);
    }
}