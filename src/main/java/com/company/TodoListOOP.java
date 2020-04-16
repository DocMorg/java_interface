package com.company;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;


public class TodoListOOP extends Application {

    private final String filename = "saveOOP.txt";
    private ObservableList<TodosOOP> table_view_data;

    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override 
    public void start(Stage stage) throws IOException {
        TodosOOP todo = new TodosOOP();
        if (getClass().getClassLoader().getResource(this.filename) == null){
            System.out.println("Error in filename");
//            System.exit(1);
            throw new FileNotFoundException("Data file was not found");
        }
        this.table_view_data = todo.loadFromFile(this.filename);
        ListView<String> listView = new ListView<>(todo.fillNamesIntoList(this.table_view_data));

        listView.setPrefWidth(235);
        listView.setPrefHeight(200);

        Button addButton = new Button();
        addButton.setText("Add");


        TableView<TodosOOP> table = new TableView<>(this.table_view_data);
        table.setPrefWidth(235);
        table.setPrefHeight(200);

        TableColumn<TodosOOP, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<TodosOOP, String> t) -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setName(t.getNewValue());
                    int row_id = t.getTableView().getSelectionModel().getSelectedIndex();
                    listView.getItems().set(row_id, t.getNewValue());
                });
        table.getColumns().add(nameColumn);

        TableColumn<TodosOOP, String> timeColumn = new TableColumn<>("Date");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        timeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        timeColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<TodosOOP, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setDate(t.getNewValue()));
        table.getColumns().add(timeColumn);

        TableColumn<TodosOOP, String> descrColumn = new TableColumn<>("Description");
        descrColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        descrColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descrColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<TodosOOP, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setDescription(t.getNewValue()));
        table.getColumns().add(descrColumn);

        TextField inputField = new TextField();

        addButton.setOnAction(e -> {
            this.table_view_data.addAll(FXCollections.observableArrayList(new TodosOOP(inputField.getText())));
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
        stage.setTitle("TodosOOP");
        stage.show();
    }

    @Override
    public void stop() throws IOException {
        if (this.table_view_data == null){
            System.exit(0);
        }
        TodosOOP todos = new TodosOOP();
        todos.writeData(this.table_view_data, this.filename);
    }
}