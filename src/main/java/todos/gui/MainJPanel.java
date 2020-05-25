package todos.gui;

import todos.core.Storages.Storage;
import todos.gui.Listeners.AddListener;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class MainJPanel {

    private final JPanel panel;
    private final Storage storage;
    private final TableModel todoTableModel;

    MainJPanel(Storage storage, TableModel todoTableModel){
        this.panel = new JPanel();
        this.storage = storage;
        this.todoTableModel = todoTableModel;
        setUpPanel();
    }

    private void setUpPanel(){
        panel.setLayout(new BorderLayout());
        InputJTextField textField = new InputJTextField();
        AddTaskButton addTaskButton = new AddTaskButton(storage, textField,
                new AddListener(storage,textField));
        DeleteTaskButton deleteTaskButton = new DeleteTaskButton(storage);
        TaskTable taskTable = new TaskTable(todoTableModel, deleteTaskButton);



        NewTaskControlPanel controlPanel = new NewTaskControlPanel(storage);
        controlPanel.bind(panel);
        DeleteTaskButton deleteButton = new DeleteTaskButton(storage);
        TaskTable table = new TaskTable(todoTableModel, deleteButton);
        TaskListScrollPane scrollPane = new TaskListScrollPane();
        table.withPane(scrollPane);
        scrollPane.bind(panel);
        TasksListControls listControls = new TasksListControls(storage);
        listControls.bind(panel);
        deleteButton.bind(listControls);
    }

    public void bind(JFrame window){
        window.setContentPane(panel);
    }
}
