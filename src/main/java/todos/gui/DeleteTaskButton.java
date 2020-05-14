package todos.gui;

import todos.core.Storages.Storage;

import javax.swing.*;

public class DeleteTaskButton {

    public static final float CENTER_ALIGNMENT = 0.5F;
    private final JButton button;
    private final Storage storage;

    DeleteTaskButton(Storage storage){
        this.button = new JButton(" Delete");
        this.storage = storage;
        setUpButton();
    }

    private void setUpButton(){
        button.setAlignmentX(CENTER_ALIGNMENT);
    }

    //TODO CHECK IT
    public void bind(TasksListControls listControls) {
//        jPanel.add(button, BorderLayout.EAST);
        listControls.bind(button);
    }

    public void bind(JTable table){
        button.addActionListener(actionEvent ->
                storage.remove(table.getSelectedRow()));
    }
}
