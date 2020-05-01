package todos.gui;


import todos.core.Exceptions.EmptyFieldException;
import todos.core.Observer.DefaultEventListener;
import todos.core.Todo;
import todos.core.TodoList;
import todos.core.DefaultTodoList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JList<Todo> taskList;
    private final TodoListModel todoListModel;
    private final TodoList todoList;

    public MainWindow() {
        this.todoList = new DefaultTodoList();
        this.todoListModel = new TodoListModel(this.todoList);
        ((DefaultTodoList) this.todoList).events.subscribe("add",
                new DefaultEventListener(this.todoListModel));
        ((DefaultTodoList) this.todoList).events.subscribe("remove",
                new DefaultEventListener(this.todoListModel));

        this.setContentPane(this.getMainContentPane());
        this.setTitle("Todo List");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

            this.addTaskButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    try {
                        todoList.add(NewTaskField().readText());
                        TaskList().setSelectedIndex(todoList.getSize() - 1);
                    } catch (EmptyFieldException ignored) {}
                }
            });
        }

        return this.addTaskButton;
    }

    private Component TasksListScrollPane() {
        if (this.taskListScrollPane == null) {
            this.taskListScrollPane = new JScrollPane(TaskList());
        }

        return this.taskListScrollPane;
    }

    private JList<Todo> TaskList() {
        if (this.taskList == null) {
            this.taskList = new JList<>();
            this.taskList.setModel(this.todoListModel);
        }

        return this.taskList;
    }

    private JButton DeleteButton() {
        if (this.deleteButton == null) {
            this.deleteButton = new JButton(" Delete");

            this.deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    todoList.remove(TaskList().getSelectedIndex());
                }
            });
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