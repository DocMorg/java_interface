package todos.gui;

import todos.core.Exceptions.EmptyFieldException;
import todos.core.Observer.DefaultEventListened;
import todos.core.Storages.CsvStorage;
import todos.core.Storages.JsonStorage;
import todos.core.Storages.Storage;
import todos.core.Storages.XmlStorage;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.Box.createVerticalStrut;

public class MainWindow extends JFrame {

    private JPanel mainContentPane;
    private JPanel newTaskControls;
    private JButton addTaskButton;
    private MyJTextField newTaskField;
    private JScrollPane taskListScrollPane;
    private JPanel taskListControls;
    private JButton deleteButton;
    private JTable table;
    private final TodoTableModel todoTableModel;
    private final Storage storage;

    public MainWindow() {
//        this.storage = new CsvStorage("backup.csv");
//        this.storage = new JsonStorage("backup.json");
        this.storage = new XmlStorage("backup.xml");

        // linking model to our list using observer pattern realization

        this.todoTableModel = new TodoTableModel(this.storage);
//        ((CsvStorage) this.storage).events.subscribe("add",
//                new DefaultEventListened(this.todoTableModel));
//        ((CsvStorage) this.storage).events.subscribe("remove",
//                new DefaultEventListened(this.todoTableModel));


        ((XmlStorage) this.storage).events.subscribe("add",
                new DefaultEventListened(this.todoTableModel));
        ((XmlStorage) this.storage).events.subscribe("remove",
                new DefaultEventListened(this.todoTableModel));

        ((XmlStorage) this.storage).events.subscribe("add",
                new DefaultEventListened(this.todoTableModel));
        ((XmlStorage) this.storage).events.subscribe("remove",
                new DefaultEventListened(this.todoTableModel));

        // Reading already written data
        storage.loadTodo();

        this.setContentPane(this.getMainContentPane());
        this.setTitle("Todo List");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.getRootPane().setDefaultButton(AddTaskButton());

        this.setMinimumSize(new Dimension(320, 270));
        this.pack();
    }

    private Container getMainContentPane() {
        if (mainContentPane == null) {
            this.mainContentPane = new JPanel();
            this.mainContentPane.setLayout(new BorderLayout());

            this.mainContentPane.add(NewTaskControls(), BorderLayout.NORTH);
            this.mainContentPane.add(TasksListScrollPane(), BorderLayout.CENTER);
            this.mainContentPane.add(TasksListControls(), BorderLayout.EAST);

        }
        return this.mainContentPane;
    }

    private Component NewTaskControls() {
        if (this.newTaskControls == null) {
            this.newTaskControls = new JPanel();

            BorderLayout layout = new BorderLayout();
            this.newTaskControls.setLayout(layout);
            layout.setHgap(5);
            this.newTaskControls.setBorder(createEmptyBorder(10, 0, 10, 10));

            this.newTaskControls.add(NewTaskField(), BorderLayout.CENTER);
            this.newTaskControls.add(AddTaskButton(), BorderLayout.EAST);
        }

        return this.newTaskControls;
    }

    private MyJTextField NewTaskField() {
        if (this.newTaskField == null) {
            this.newTaskField = new MyJTextField();
        }
        return this.newTaskField;
    }

    private JButton AddTaskButton() {
        if (this.addTaskButton == null) {
            this.addTaskButton = new JButton("  Add  ");

            this.addTaskButton.addActionListener(actionEvent -> {
                try {
                    storage.add(NewTaskField().readText());
                } catch (EmptyFieldException ignored) {}
            });
        }

        return this.addTaskButton;
    }

    private Component TasksListScrollPane() {
        if (this.taskListScrollPane == null) {
            this.taskListScrollPane = new JScrollPane(TaskTable());
        }

        return this.taskListScrollPane;
    }

    private JTable TaskTable() {
        if (table == null) {
            table = new JTable(todoTableModel);
        }
//        table.getDefaultEditor(String.class);
//        DefaultCellEditor
        return this.table;
    }

    private JButton DeleteButton() {
        if (this.deleteButton == null) {
            this.deleteButton = new JButton(" Delete");

            this.deleteButton.addActionListener(actionEvent ->
                    storage.remove(TaskTable().getSelectedRow()));
        }
        return this.deleteButton;
    }

    private Component TasksListControls() {
        if (this.taskListControls == null) {
            this.taskListControls = new JPanel();

            BoxLayout layout = new BoxLayout(this.taskListControls, BoxLayout.Y_AXIS);
            this.taskListControls.setLayout(layout);
            this.taskListControls.setBorder(createEmptyBorder(0, 5, 5, 5));

            JButton button = DeleteButton();
            button.setAlignmentX(CENTER_ALIGNMENT);
            this.taskListControls.add(button);

            this.taskListControls.add(createVerticalStrut(10));

        }

        return this.taskListControls;
    }
}