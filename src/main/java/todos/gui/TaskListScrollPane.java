package todos.gui;

import javax.swing.*;
import java.awt.*;

public class TaskListScrollPane {

    private final JScrollPane scrollPane;
    private final TaskTable table;

    TaskListScrollPane(TaskTable table){
        this.scrollPane = new JScrollPane();
        this.table = table;
        table.withPane(scrollPane);
    }

    protected void withPanel(JPanel panel){
        panel.add(new TaskListScrollPane(this.table).scrollPane,
                BorderLayout.CENTER);
    }

}
