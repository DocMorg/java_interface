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
        listView.setPrefWidth(235);
        listView.setPrefHeight(200);

        Button addButton = new Button();
        addButton.setText("Add");


        ObservableList<Todos> todos = FXCollections.observableArrayList(
                new Todos("dsadsa", "lalallala")
        );

        TableView<Todos> table = new TableView<Todos>(todos);
        table.setPrefWidth(235);
        table.setPrefHeight(200);

        TableColumn<Todos, String> nameColumn = new TableColumn<Todos,String>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.getColumns().add(nameColumn);

        TableColumn<Todos, String> timeColumn = new TableColumn<Todos,String>("Date");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        table.getColumns().add(timeColumn);

        TableColumn<Todos, String> descrColumn = new TableColumn<Todos,String>("Description");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        table.getColumns().add(descrColumn);

        TextField inputField = new TextField();

        addButton.setOnAction(e -> {
            todos.addAll(FXCollections.observableArrayList(new Todos(inputField.getText())));
            System.out.println(todos);
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
        vbox.getChildren().addAll(listView, table, entryBox);


//        TableColumn<addedToDos, String> nameColumn = new TableColumn<Person, String>("Name");


        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("todos");
        stage.show();
    }
}