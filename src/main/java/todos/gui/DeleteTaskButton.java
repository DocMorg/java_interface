package todos.gui;

import todos.core.Storages.Storage;
import todos.gui.Listeners.TableRemoveListener;

import javax.swing.*;

public class DeleteTaskButton {

    public static final float CENTER_ALIGNMENT = 0.5F;
    private final JButton button;
    private final Storage storage;
    private JTable table;

    DeleteTaskButton(Storage storage){
        this.button = new JButton(" Delete");
        this.storage = storage;
        setUpButton();
    }

    private void setUpButton(){
        button.setAlignmentX(CENTER_ALIGNMENT);
    }

    public void withTableAndControls(JPanel listControls) {
        JButton button1 = new DeleteTaskButton(storage).button;
        button1.addActionListener(new TableRemoveListener(storage, this.table));
        listControls.add(button1);
    }

    public void withTable(JTable table){
        this.table = table;
        new DeleteTaskButton(storage).button.addActionListener(
                new TableRemoveListener(storage, this.table));
    }
}
