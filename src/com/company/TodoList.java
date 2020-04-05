package com.company;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.stream.Collectors;
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


public class TodoList extends Application {

    private String filename = "/save.txt";
    private ObservableList<Todos> table_view_data;

    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override public void start(Stage stage) throws IOException {
        URL path_to_file = getClass().getResource(this.filename);
        this.table_view_data = FXCollections.observableArrayList(
                Files.readAllLines(new File(path_to_file.getPath()).toPath())
                .stream()
                .map(line -> {
                    String[] details = line.split(",");
                    Todos cd = new Todos();
                    cd.setName(details[0]);
                    cd.setDate(details[1]);
                    cd.setDescription(details[2]);
                    return cd;
                })
                .collect(Collectors.toList()));

        ObservableList<String> list_view_data = FXCollections.observableArrayList();
        for (Todos value : this.table_view_data) {
            list_view_data.add(value.getName());
        }
        ListView<String> listView = new ListView<>(list_view_data);
        listView.setPrefWidth(235);
        listView.setPrefHeight(200);

        Button addButton = new Button();
        addButton.setText("Add");


        TableView<Todos> table = new TableView<>(this.table_view_data);
        table.setPrefWidth(235);
        table.setPrefHeight(200);

        TableColumn<Todos, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Todos, String> t) -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setName(t.getNewValue());
                    int row_id = t.getTableView().getSelectionModel().getSelectedIndex();
                    listView.getItems().set(row_id, t.getNewValue());
                });
        table.getColumns().add(nameColumn);

        TableColumn<Todos, String> timeColumn = new TableColumn<>("Date");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        timeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        timeColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Todos, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setDate(t.getNewValue()));
        table.getColumns().add(timeColumn);

        TableColumn<Todos, String> descrColumn = new TableColumn<>("Description");
        descrColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        descrColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descrColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Todos, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setDescription(t.getNewValue()));
        table.getColumns().add(descrColumn);

        TextField inputField = new TextField();

        addButton.setOnAction(e -> {
            this.table_view_data.addAll(FXCollections.observableArrayList(new Todos(inputField.getText())));
            list_view_data.add(inputField.getText());
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
        stage.setTitle("todos");
        stage.show();
    }

    @Override
    public void stop() throws IOException {
        Writer writer = null;
        try {
            URL path_to_file = getClass().getResource(this.filename);
            writer = new BufferedWriter(new FileWriter(new File(path_to_file.getPath())));
            for (Todos line : this.table_view_data) {
                String text = line.getName() + "," + line.getDate() + "," + line.getDescription() + "\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            assert writer != null;
            writer.flush();
            writer.close();
        }
    }
}