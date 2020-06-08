package todos.gui;

import todos.core.Observer.DefaultEventListened;
import todos.core.Observer.EventsManaged;
import todos.core.Storages.Storage;
import todos.core.Storages.XmlStorage;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow(Storage storage, EventsManaged events) {
        // linking model to our list using observer pattern realization
        TodoTableModel todoTableModel = new TodoTableModel(storage);
        events.subscribe("add",new DefaultEventListened(todoTableModel));
        events.subscribe("remove",new DefaultEventListened(todoTableModel));
        // Reading already written data
        storage.loadTodo();
        MainJPanel mainPanel = new MainJPanel(storage, todoTableModel);
        mainPanel.withFrame(this);
        this.setTitle("Todo List");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(320, 270));
        this.pack();
    }
}