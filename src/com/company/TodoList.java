package com.company;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TodoList extends Application {

    private ObservableList<String> todos;

    @Override public void start(Stage stage) {
        this.todos = FXCollections.observableArrayList();
        ListView<String> listView = new ListView<>(this.todos);

        Button addButton = new Button();
        addButton.setText("Add");

        TextField inputField = new TextField();

        TableView tableView = new TableView();

        addButton.setOnAction(e -> {
            this.todos.add(inputField.getText());
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
        vbox.getChildren().addAll(listView, entryBox);

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("todos");
        stage.show();
    }
}