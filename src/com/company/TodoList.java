package com.company;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class TodoList extends Application {

    private ObservableList<String> list;

    @Override public void start(Stage stage) {
        this.list = FXCollections.observableArrayList();
        ListView<String> listView = new ListView<>(this.list);
        listView.setPrefWidth(250);
        listView.setPrefHeight(200);

        Button addButton = new Button();
        addButton.setText("Add");

        TableView tableView = new TableView();
        tableView.setPrefWidth(250);
        tableView.setPrefHeight(200);
        TableColumn<Todos, String> nameColumn = new TableColumn<Todos, String>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Todos, String>("name"));
//        ObservableList<String> addedToDos = listView.getItems();

        TextField inputField = new TextField();

        addButton.setOnAction(e -> {
            Todos todos = new Todos(inputField.getText());
            this.list.add(inputField.getText());
            inputField.setText("");
            inputField.requestFocus();
        });

        addButton.disableProperty()
                .bind(Bindings.isEmpty(inputField.textProperty()));

        Button removeButton = new Button();
        removeButton.setText("Remove");

        removeButton.setOnAction(e -> {
            final int idToRemove = listView.getSelectionModel().getSelectedIndex();
            listView.getItems().remove(idToRemove);
        });

        HBox entryBox = new HBox();
        entryBox.getChildren().addAll(inputField, addButton, removeButton);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(listView, tableView, entryBox);


//        TableColumn<addedToDos, String> nameColumn = new TableColumn<Person, String>("Name");


        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("todos");
        stage.show();
    }
}