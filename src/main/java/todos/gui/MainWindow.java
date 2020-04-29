package todos.gui;


import todos.core.EmptyFieldException;
import todos.core.Todos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    private JList<Todos> taskList;

    private final TodoListModel todoListModel;

    public MainWindow() {
        this.todoListModel = TodoListModel.getInstance();

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

            this.addTaskButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                            todoListModel.add(NewTaskField().readText());
                            TaskList().setSelectedIndex(TaskList().getModel().getSize() - 1);
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

    private JList<Todos> TaskList() {
        if (this.taskList == null) {
            this.taskList = new JList<>();
            this.taskList.setModel(this.todoListModel);

//            this.taskList.setCellRenderer(CellRenderer());
        }

        return this.taskList;
    }


//    private ListCellRenderer<Object> CellRenderer() {
//        return new DefaultListCellRenderer() {
//            @Override
//            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
//                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
//                if (value instanceof Todos) {
//                    Todos nextTodo = (Todos) value;
//                    setText((String) value);
//                    if (isSelected) setBackground(getBackground().darker());
//                }
//                return c;
//            }
//        };
//    }

    private JButton DeleteButton() {
        if (this.deleteButton == null) {
            this.deleteButton = new JButton(" Delete");

            this.deleteButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    todoListModel.remove(TaskList().getSelectedIndex());
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