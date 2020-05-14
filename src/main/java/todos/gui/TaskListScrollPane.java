package todos.gui;

import javax.swing.*;

public class TaskListScrollPane {

    private final JScrollPane scrollPane;

    TaskListScrollPane(){
        this.scrollPane = new JScrollPane();
    }

    protected void bind(JPanel panel){
        panel.add(this.scrollPane);
    }

    protected void bind(JTable table){
        this.scrollPane.setViewportView(table);
    }

}
